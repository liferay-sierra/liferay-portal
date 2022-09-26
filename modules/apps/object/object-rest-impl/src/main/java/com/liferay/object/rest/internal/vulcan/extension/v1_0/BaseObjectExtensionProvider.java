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

package com.liferay.object.rest.internal.vulcan.extension.v1_0;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.vulcan.dto.converter.DTOMapper;
import com.liferay.portal.vulcan.extension.ExtensionProvider;

import java.util.Collection;
import java.util.Collections;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Correa
 */
public abstract class BaseObjectExtensionProvider implements ExtensionProvider {

	@Override
	public Collection<String> getFilteredPropertyNames(
		long companyId, Object entity) {

		return Collections.emptyList();
	}

	@Override
	public boolean isApplicableExtension(long companyId, String className) {
		ObjectDefinition objectDefinition = getObjectDefinition(
			companyId, className);

		if ((objectDefinition != null) && objectDefinition.isSystem()) {
			return true;
		}

		return false;
	}

	protected ObjectDefinition getObjectDefinition(
		long companyId, String className) {

		String internalDTOClassName = dtoMapper.toInternalDTOClassName(
			className);

		if (internalDTOClassName == null) {
			return null;
		}

		try {
			return objectDefinitionLocalService.
				fetchObjectDefinitionByClassName(
					companyId, internalDTOClassName);
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}

			return null;
		}
	}

	protected long getPrimaryKey(Object entity) throws PortalException {
		JSONObject jsonObject = jsonFactory.createJSONObject(
			jsonFactory.looseSerializeDeep(entity));

		return jsonObject.getLong("id");
	}

	@Reference
	protected DTOMapper dtoMapper;

	@Reference
	protected JSONFactory jsonFactory;

	@Reference
	protected ObjectDefinitionLocalService objectDefinitionLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseObjectExtensionProvider.class);

}