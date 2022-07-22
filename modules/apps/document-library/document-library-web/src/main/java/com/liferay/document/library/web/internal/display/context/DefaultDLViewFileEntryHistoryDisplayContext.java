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

package com.liferay.document.library.web.internal.display.context;

import com.liferay.document.library.display.context.DLViewFileEntryHistoryDisplayContext;
import com.liferay.document.library.kernel.versioning.VersioningStrategy;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.document.library.web.internal.display.context.helper.DLPortletInstanceSettingsHelper;
import com.liferay.document.library.web.internal.display.context.helper.DLRequestHelper;
import com.liferay.document.library.web.internal.display.context.logic.UIItemsBuilder;
import com.liferay.document.library.web.internal.helper.DLTrashHelper;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mauro Mariuzzo
 */
public class DefaultDLViewFileEntryHistoryDisplayContext
	implements DLViewFileEntryHistoryDisplayContext {

	public DefaultDLViewFileEntryHistoryDisplayContext(
		HttpServletRequest httpServletRequest, FileVersion fileVersion,
		ResourceBundle resourceBundle, DLTrashHelper dlTrashHelper,
		VersioningStrategy versioningStrategy, DLURLHelper dlURLHelper) {

		_resourceBundle = resourceBundle;

		DLRequestHelper dlRequestHelper = new DLRequestHelper(
			httpServletRequest);

		_dlPortletInstanceSettingsHelper = new DLPortletInstanceSettingsHelper(
			dlRequestHelper);

		_uiItemsBuilder = new UIItemsBuilder(
			httpServletRequest, fileVersion, _resourceBundle, dlTrashHelper,
			versioningStrategy, dlURLHelper);
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() throws PortalException {
		if (!_dlPortletInstanceSettingsHelper.isShowActions()) {
			return null;
		}

		return DropdownItemListBuilder.addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(
					DropdownItemListBuilder.add(
						_uiItemsBuilder::isDownloadActionAvailable,
						_uiItemsBuilder.createDownloadDropdownItem()
					).add(
						_uiItemsBuilder::isViewVersionActionAvailable,
						_uiItemsBuilder.createViewVersionDropdownItem()
					).add(
						_uiItemsBuilder::isRevertToVersionActionAvailable,
						_uiItemsBuilder.createRevertVersionDropdownItem()
					).add(
						_uiItemsBuilder::isCompareToActionAvailable,
						_uiItemsBuilder.createCompareToDropdownItem()
					).build());
				dropdownGroupItem.setSeparator(true);
			}
		).addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(
					DropdownItemListBuilder.add(
						_uiItemsBuilder::isDeleteVersionActionAvailable,
						_uiItemsBuilder.createDeleteVersionDropdownItem()
					).build());
				dropdownGroupItem.setSeparator(true);
			}
		).build();
	}

	@Override
	public Menu getMenu() throws PortalException {
		Menu menu = new Menu();

		menu.setDirection("left-side");
		menu.setMenuItems(_getMenuItems());
		menu.setScroll(false);
		menu.setShowWhenSingleIcon(true);

		return menu;
	}

	@Override
	public UUID getUuid() {
		return _UUID;
	}

	private List<MenuItem> _getMenuItems() throws PortalException {
		List<MenuItem> menuItems = new ArrayList<>();

		if (_dlPortletInstanceSettingsHelper.isShowActions()) {
			_uiItemsBuilder.addDownloadMenuItem(menuItems);

			_uiItemsBuilder.addViewVersionMenuItem(menuItems);

			_uiItemsBuilder.addRevertToVersionMenuItem(menuItems);

			_uiItemsBuilder.addDeleteVersionMenuItem(menuItems);

			_uiItemsBuilder.addCompareToMenuItem(menuItems);
		}

		return menuItems;
	}

	private static final UUID _UUID = UUID.fromString(
		"8f4f3c55-3e93-41c5-a363-57d00161f274");

	private final DLPortletInstanceSettingsHelper
		_dlPortletInstanceSettingsHelper;
	private final ResourceBundle _resourceBundle;
	private final UIItemsBuilder _uiItemsBuilder;

}