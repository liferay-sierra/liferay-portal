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

package com.liferay.dynamic.data.mapping.internal.search.permission;

import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.portal.search.permission.SearchPermissionFilterContributor;

import java.util.Optional;

import org.osgi.service.component.annotations.Component;

/**
 * @author Lino Alves
 */
@Component(service = SearchPermissionFilterContributor.class)
public class DDMFormInstanceRecordSearchPermissionFilterContributor
	implements SearchPermissionFilterContributor {

	@Override
	public Optional<String> getParentEntryClassNameOptional(
		String entryClassName) {

		if (entryClassName.equals(DDMFormInstanceRecord.class.getName())) {
			return Optional.of(DDMFormInstance.class.getName());
		}

		return Optional.empty();
	}

}