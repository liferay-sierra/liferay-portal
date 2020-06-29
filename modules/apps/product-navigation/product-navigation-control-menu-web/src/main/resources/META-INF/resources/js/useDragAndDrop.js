/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import {openToast} from 'frontend-js-web';
import React, {
	useCallback,
	useContext,
	useEffect,
	useMemo,
	useRef,
	useState,
} from 'react';
import {useDrag, useDrop} from 'react-dnd';
import {getEmptyImage} from 'react-dnd-html5-backend';

import {LAYOUT_DATA_ITEM_TYPES, updateUsedWidget} from './AddPanel';
import {useSetWidgetsContext, useWidgetsContext} from './AddPanelContext';

const DROP_OVER_CLASS = 'yui3-dd-drop-over';
const POSITION = {
	bottom: 'bottom',
	top: 'top',
};

const initialDragDrop = {
	dragIndicatorPosition: {},
	dropTargetColumn: null,
	dropTargetItem: null,
	setDragIndicatorPosition: () => null,
	setDropTargetColumn: () => null,
	setDropTargetItem: () => null,
};

const DragAndDropContext = React.createContext(initialDragDrop);
export const DragAndDropProvider = DragAndDropContext.Provider;

export function useDragItem(sourceItem) {
	const getSourceItem = useCallback(() => sourceItem, [sourceItem]);
	const sourceRef = useRef(null);

	const [{isDraggingSource}, handlerRef, previewRef] = useDrag({
		collect: (monitor) => ({
			isDraggingSource: monitor.isDragging(),
		}),

		item: {
			data: sourceItem.data,
			disabled: sourceItem.disabled,
			getSourceItem,
			icon: sourceItem.icon,
			itemId: sourceItem.itemId,
			name: sourceItem.name,
			type: sourceItem.type,
		},
	});

	useEffect(() => {
		previewRef(getEmptyImage(), {captureDraggingState: true});
	}, [previewRef]);

	return {
		handlerRef,
		isDraggingSource,
		sourceRef,
	};
}

export function useDragSymbol({data, icon, label, portletId, type}) {
	const sourceItem = useMemo(
		() => ({
			data,
			icon,
			isSymbol: true,
			itemId: portletId,
			name: label,
			type,
		}),
		[data, icon, label, portletId, type]
	);

	const {handlerRef, isDraggingSource, sourceRef} = useDragItem(sourceItem);

	const symbolRef = (element) => {
		sourceRef.current = element;
		handlerRef(element);
	};

	return {
		isDraggingSource,
		sourceRef: symbolRef,
	};
}

export function useDropClear(targetItem) {
	const {dropTargetColumn, setDropTargetColumn} = useContext(
		DragAndDropContext
	);

	const [, setDropClearRef] = useDrop({
		accept: Object.values(LAYOUT_DATA_ITEM_TYPES),
		hover(item, monitor) {
			if (!monitor.isOver({shallow: true})) {
				return;
			}

			if (dropTargetColumn) {
				dropTargetColumn.classList.remove(DROP_OVER_CLASS);
			}

			setDropTargetColumn(null);
		},
	});

	setDropClearRef(targetItem);
}

export function useDropTarget(targetItem) {
	const {
		dropTargetColumn,
		dropTargetItem,
		setDragIndicatorPosition,
		setDropTargetColumn,
		setDropTargetItem,
	} = useContext(DragAndDropContext);

	const [windowScrollPosition, setWindowScrollPosition] = useState(0);
	const [targetPosition, setTargetPosition] = useState(null);

	const setWidgets = useSetWidgetsContext();
	const widgets = useWidgetsContext();

	useEffect(() => {
		const handleWindowScroll = () => {
			setWindowScrollPosition(window.scrollY);
		};

		window.addEventListener('scroll', handleWindowScroll);

		return () => {
			window.removeEventListener('scroll', handleWindowScroll);
		};
	}, []);

	const [, setDropTargetRef] = useDrop({
		accept: Object.values(LAYOUT_DATA_ITEM_TYPES),
		drop(item, monitor) {
			setDropTargetColumn(null);

			if (!monitor.isOver({shallow: true})) {
				return;
			}

			if (!item.disabled) {
				dropTargetColumn.classList.remove(DROP_OVER_CLASS);

				addPortlet({item, targetItem, targetPosition});

				if (!item.data.instanceable) {
					const updatedWidgets = updateUsedWidget({
						item,
						widgets,
					});
					setWidgets(updatedWidgets);
				}
			}
		},
		hover(item, monitor) {
			if (!monitor.isOver({shallow: true})) {
				return;
			}

			const itemIsDropzone = targetItem.classList.contains(
				'portlet-dropzone'
			);

			const parentTargetItem = itemIsDropzone
				? targetItem.parentElement
				: targetItem.parentElement.parentElement;

			if (dropTargetColumn !== parentTargetItem) {
				if (dropTargetColumn) {
					dropTargetColumn.classList.remove(DROP_OVER_CLASS);
				}
				setDropTargetColumn(parentTargetItem);
				parentTargetItem.classList.add(DROP_OVER_CLASS);
			}

			const dropPosition = getDropPosition({
				monitor,
				targetItem,
			});
			setTargetPosition(dropPosition);

			if (
				dropTargetItem !== targetItem ||
				dropPosition !== targetPosition
			) {
				setDropTargetItem(targetItem);

				const dropIndicatorPosition = getDropIndicatorPosition({
					monitor,
					targetItem,
					windowScrollPosition,
				});

				setDragIndicatorPosition(dropIndicatorPosition);
			}
		},
	});

	setDropTargetRef(targetItem);
}

const addLoadingAnimation = (targetItem, targetPosition) => {
	const itemIsDropzone = targetItem.classList.contains('portlet-dropzone');
	const loading = document.createElement('div');
	loading.classList.add('loading-animation');

	if (itemIsDropzone) {
		targetItem.appendChild(loading);
	}
	else {
		const parent = targetItem.parentElement;
		const item =
			targetPosition === POSITION.top
				? targetItem
				: targetItem.nextSibling;

		parent.insertBefore(loading, item);
	}

	return loading;
};

export const addPortlet = ({item, targetItem, targetPosition}) => {
	const loading = addLoadingAnimation(targetItem, targetPosition);

	openToast({
		message: Liferay.Language.get('the-application-was-added-to-the-page'),
		type: 'success',
	});

	const portletData =
		item.type === LAYOUT_DATA_ITEM_TYPES.widget
			? ''
			: `${item.data.classPK},${item.data.className}`;

	Liferay.Portlet.add({
		beforePortletLoaded: () => null,
		placeHolder: loading,
		plid: themeDisplay.getPlid(),
		portletData,
		portletId: item.data.portletId,
		portletItemId: '',
	});
};

const getHoverPosition = (monitor, targetItem) => {
	const clientOffset = monitor.getClientOffset();
	const targetItemBoundingRect = targetItem.getBoundingClientRect();
	const targetItemHeight = targetItem.getBoundingClientRect().height;

	const hoverTopLimit = targetItemHeight / 2;
	const hoverClientY = clientOffset.y - targetItemBoundingRect.top;

	return {hoverClientY, hoverTopLimit, targetItemBoundingRect};
};

const getDropIndicatorPosition = ({
	monitor,
	targetItem,
	windowScrollPosition,
}) => {
	const {
		hoverClientY,
		hoverTopLimit,
		targetItemBoundingRect,
	} = getHoverPosition(monitor, targetItem);

	const positionY =
		hoverClientY < hoverTopLimit
			? targetItemBoundingRect.top
			: targetItemBoundingRect.bottom;

	return {
		clientX: targetItemBoundingRect.left,
		clientY: positionY + windowScrollPosition,
		width: targetItemBoundingRect.width,
	};
};

const getDropPosition = ({monitor, targetItem}) => {
	const {hoverClientY, hoverTopLimit} = getHoverPosition(monitor, targetItem);

	return hoverClientY < hoverTopLimit ? POSITION.top : POSITION.bottom;
};
