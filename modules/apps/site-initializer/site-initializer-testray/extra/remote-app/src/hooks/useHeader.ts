/* eslint-disable @typescript-eslint/no-unused-vars */
/* eslint-disable no-console */
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

import {useCallback, useContext, useEffect} from 'react';

import {
	HeaderContext,
	HeaderTabs,
	HeaderTitle,
	HeaderTypes,
	initialState,
} from '../context/HeaderContext';

type UseHeader = {
	shouldUpdate?: boolean;
	timeout?: number;
	useHeading?: HeaderTitle[];
	useTabs?: HeaderTabs[];
};

const DEFAULT_TIMEOUT = 0;

const useHeader = ({
	shouldUpdate = true,
	timeout = DEFAULT_TIMEOUT,
	useHeading = initialState.heading,
	useTabs = initialState.tabs,
}: UseHeader = {}) => {
	const [, dispatch] = useContext(HeaderContext);

	const useHeadingString = JSON.stringify(useHeading);
	const useTabsString = JSON.stringify(useTabs);

	const setHeading = useCallback(
		(newHeading: HeaderTitle[] = [], append?: boolean) => {
			dispatch({
				payload: {append, heading: newHeading},
				type: HeaderTypes.SET_HEADING,
			});
		},
		[dispatch]
	);

	const setTabs = useCallback(
		(newTabs: HeaderTabs[] = []) =>
			dispatch({payload: newTabs, type: HeaderTypes.SET_TABS}),
		[dispatch]
	);

	useEffect(() => {
		if (shouldUpdate && useHeadingString) {
			setTimeout(() => {
				setHeading(JSON.parse(useHeadingString));
			}, timeout);
		}
	}, [setHeading, shouldUpdate, timeout, useHeadingString]);

	useEffect(() => {
		if (shouldUpdate && useTabsString) {
			setTimeout(() => {
				setTabs(JSON.parse(useTabsString));
			}, timeout);
		}
	}, [setTabs, shouldUpdate, timeout, useTabsString]);

	return {
		setHeading,
		setTabs,
	};
};

export default useHeader;
