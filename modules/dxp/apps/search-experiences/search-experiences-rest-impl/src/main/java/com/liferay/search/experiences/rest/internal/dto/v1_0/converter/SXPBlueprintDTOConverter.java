/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.search.experiences.rest.internal.dto.v1_0.converter;

import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;
import com.liferay.search.experiences.rest.dto.v1_0.SXPBlueprint;
import com.liferay.search.experiences.rest.dto.v1_0.util.ConfigurationUtil;
import com.liferay.search.experiences.rest.dto.v1_0.util.ElementInstanceUtil;
import com.liferay.search.experiences.service.SXPBlueprintLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryan Engler
 */
@Component(
	enabled = false,
	property = "dto.class.name=com.liferay.search.experiences.model.SXPBlueprint",
	service = {DTOConverter.class, SXPBlueprintDTOConverter.class}
)
public class SXPBlueprintDTOConverter
	implements DTOConverter
		<com.liferay.search.experiences.model.SXPBlueprint, SXPBlueprint> {

	@Override
	public String getContentType() {
		return SXPBlueprint.class.getSimpleName();
	}

	@Override
	public SXPBlueprint toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		com.liferay.search.experiences.model.SXPBlueprint sxpBlueprint =
			_sxpBlueprintLocalService.getSXPBlueprint(
				(Long)dtoConverterContext.getId());

		return toDTO(dtoConverterContext, sxpBlueprint);
	}

	@Override
	public SXPBlueprint toDTO(
			DTOConverterContext dtoConverterContext,
			com.liferay.search.experiences.model.SXPBlueprint sxpBlueprint)
		throws Exception {

		return new SXPBlueprint() {
			{
				configuration = ConfigurationUtil.toConfiguration(
					sxpBlueprint.getConfigurationJSON());
				description = sxpBlueprint.getDescription(
					dtoConverterContext.getLocale());
				description_i18n = LocalizedMapUtil.getI18nMap(
					dtoConverterContext.isAcceptAllLanguages(),
					sxpBlueprint.getDescriptionMap());
				elementInstances = ElementInstanceUtil.toElementInstances(
					sxpBlueprint.getElementInstancesJSON());
				id = sxpBlueprint.getSXPBlueprintId();
				title = sxpBlueprint.getTitle(dtoConverterContext.getLocale());
				title_i18n = LocalizedMapUtil.getI18nMap(
					dtoConverterContext.isAcceptAllLanguages(),
					sxpBlueprint.getTitleMap());
			}
		};
	}

	@Override
	public SXPBlueprint toDTO(
		com.liferay.search.experiences.model.SXPBlueprint sxpBlueprint) {

		return new SXPBlueprint() {
			{
				configuration = ConfigurationUtil.toConfiguration(
					sxpBlueprint.getConfigurationJSON());
				description = sxpBlueprint.getDescription();
				description_i18n = LocalizedMapUtil.getI18nMap(
					true, sxpBlueprint.getDescriptionMap());
				elementInstances = ElementInstanceUtil.toElementInstances(
					sxpBlueprint.getElementInstancesJSON());
				id = sxpBlueprint.getSXPBlueprintId();
				title = sxpBlueprint.getTitle();
				title_i18n = LocalizedMapUtil.getI18nMap(
					true, sxpBlueprint.getTitleMap());
			}
		};
	}

	@Reference
	private SXPBlueprintLocalService _sxpBlueprintLocalService;

}