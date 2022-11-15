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

package com.liferay.site.navigation.menu.item.display.page.internal.type;

import com.liferay.info.item.InfoItemClassDetails;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.InfoItemServiceRegistry;
import com.liferay.info.item.provider.InfoItemDetailsProvider;
import com.liferay.info.item.provider.InfoItemFormVariationsProvider;
import com.liferay.layout.display.page.LayoutDisplayPageInfoItemFieldValuesProvider;
import com.liferay.layout.display.page.LayoutDisplayPageInfoItemFieldValuesProviderRegistry;
import com.liferay.layout.display.page.LayoutDisplayPageMultiSelectionProvider;
import com.liferay.layout.display.page.LayoutDisplayPageMultiSelectionProviderTracker;
import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.layout.display.page.LayoutDisplayPageProvider;
import com.liferay.layout.display.page.LayoutDisplayPageProviderTracker;

import java.util.Locale;
import java.util.Optional;

/**
 * @author Lourdes Fernández Besada
 */
public class DisplayPageTypeContext {

	public DisplayPageTypeContext(
		String className, InfoItemServiceRegistry infoItemServiceRegistry,
		LayoutDisplayPageInfoItemFieldValuesProviderRegistry
			layoutDisplayPageInfoItemFieldValuesProviderRegistry,
		LayoutDisplayPageMultiSelectionProviderTracker
			layoutDisplayPageMultiSelectionProviderTracker,
		LayoutDisplayPageProviderTracker layoutDisplayPageProviderTracker) {

		_className = className;
		_infoItemServiceRegistry = infoItemServiceRegistry;
		_layoutDisplayPageInfoItemFieldValuesProviderRegistry =
			layoutDisplayPageInfoItemFieldValuesProviderRegistry;
		_layoutDisplayPageMultiSelectionProviderTracker =
			layoutDisplayPageMultiSelectionProviderTracker;
		_layoutDisplayPageProviderTracker = layoutDisplayPageProviderTracker;
	}

	public String getClassName() {
		return _className;
	}

	public InfoItemClassDetails getInfoItemClassDetails() {
		InfoItemDetailsProvider<?> infoItemDetailsProvider =
			_infoItemServiceRegistry.getFirstInfoItemService(
				InfoItemDetailsProvider.class, _className);

		if (infoItemDetailsProvider == null) {
			return null;
		}

		return infoItemDetailsProvider.getInfoItemClassDetails();
	}

	public InfoItemFormVariationsProvider<?>
		getInfoItemFormVariationsProvider() {

		return _infoItemServiceRegistry.getFirstInfoItemService(
			InfoItemFormVariationsProvider.class, _className);
	}

	public String getLabel(Locale locale) {
		InfoItemClassDetails infoItemClassDetails = getInfoItemClassDetails();

		if (infoItemClassDetails == null) {
			return null;
		}

		return infoItemClassDetails.getLabel(locale);
	}

	public Optional<LayoutDisplayPageInfoItemFieldValuesProvider<?>>
		getLayoutDisplayPageInfoItemFieldValuesProviderOptional() {

		return Optional.ofNullable(
			_layoutDisplayPageInfoItemFieldValuesProviderRegistry.
				getLayoutDisplayPageInfoItemFieldValuesProvider(_className));
	}

	public Optional<LayoutDisplayPageMultiSelectionProvider<?>>
		getLayoutDisplayPageMultiSelectionProviderOptional() {

		return Optional.ofNullable(
			_layoutDisplayPageMultiSelectionProviderTracker.
				getLayoutDisplayPageMultiSelectionProvider(_className));
	}

	public LayoutDisplayPageObjectProvider<?>
		getLayoutDisplayPageObjectProvider(long classPK) {

		LayoutDisplayPageProvider<?> layoutDisplayPageProvider =
			getLayoutDisplayPageProvider();

		if (layoutDisplayPageProvider == null) {
			return null;
		}

		return layoutDisplayPageProvider.getLayoutDisplayPageObjectProvider(
			new InfoItemReference(_className, classPK));
	}

	public LayoutDisplayPageProvider<?> getLayoutDisplayPageProvider() {
		return _layoutDisplayPageProviderTracker.
			getLayoutDisplayPageProviderByClassName(_className);
	}

	public boolean isAvailable() {
		InfoItemDetailsProvider<?> infoItemDetailsProvider =
			_infoItemServiceRegistry.getFirstInfoItemService(
				InfoItemDetailsProvider.class, _className);

		if (infoItemDetailsProvider == null) {
			return false;
		}

		return true;
	}

	private final String _className;
	private final InfoItemServiceRegistry _infoItemServiceRegistry;
	private final LayoutDisplayPageInfoItemFieldValuesProviderRegistry
		_layoutDisplayPageInfoItemFieldValuesProviderRegistry;
	private final LayoutDisplayPageMultiSelectionProviderTracker
		_layoutDisplayPageMultiSelectionProviderTracker;
	private final LayoutDisplayPageProviderTracker
		_layoutDisplayPageProviderTracker;

}