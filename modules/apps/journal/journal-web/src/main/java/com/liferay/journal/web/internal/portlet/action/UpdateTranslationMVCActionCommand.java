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

package com.liferay.journal.web.internal.portlet.action;

import com.liferay.info.field.InfoField;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.field.type.TextInfoFieldType;
import com.liferay.info.item.InfoItemClassPKReference;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.translation.info.item.updater.InfoItemFieldValuesUpdater;

import java.util.Collection;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alicia Garcia
 */
@Component(
	property = {
		"javax.portlet.name=" + JournalPortletKeys.JOURNAL,
		"mvc.command.name=/journal/update_translation"
	},
	service = MVCActionCommand.class
)
public class UpdateTranslationMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			JournalArticle article = ActionUtil.getArticle(actionRequest);
			UnicodeProperties infoFieldUnicodeProperties =
				PropertiesParamUtil.getProperties(actionRequest, "infoField--");

			InfoItemFieldValues infoItemFieldValues = new InfoItemFieldValues(
				new InfoItemClassPKReference(
					JournalArticle.class.getName(),
					article.getResourcePrimKey()));

			for (InfoFieldValue<Object> infoFieldValue :
					_getInfoFieldValues(article)) {

				InfoField infoField = infoFieldValue.getInfoField();

				if (infoField == null) {
					continue;
				}

				if (infoFieldUnicodeProperties.get(infoField.getName()) ==
						null) {

					continue;
				}

				infoItemFieldValues.add(
					_createInfoFieldValue(
						infoField.getName(),
						_getTargetLocale(actionRequest),
						infoFieldUnicodeProperties.get(infoField.getName())));
			}

			_journalArticleInfoItemFieldValuesUpdater.
				updateFromInfoItemFieldValues(article, infoItemFieldValues);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			SessionErrors.add(actionRequest, exception.getClass(), exception);

			actionResponse.setRenderParameter(
				"mvcRenderCommandName", "/journal/translate");

			hideDefaultSuccessMessage(actionRequest);
		}
	}

	private InfoFieldValue<Object> _createInfoFieldValue(
		String fieldName, Locale locale, String value) {

		InfoLocalizedValue<String> infoLocalizedValue =
			InfoLocalizedValue.builder(
			).addValue(
				locale, fieldName
			).build();

		InfoField infoField = new InfoField(
			TextInfoFieldType.INSTANCE, infoLocalizedValue, true, fieldName);

		return new InfoFieldValue<>(infoField, value);
	}

	private Collection<InfoFieldValue<Object>> _getInfoFieldValues(
		JournalArticle article) {

		InfoItemFieldValuesProvider<JournalArticle>
			infoItemFieldValuesProvider =
				_infoItemServiceTracker.getFirstInfoItemService(
					InfoItemFieldValuesProvider.class,
					JournalArticle.class.getName());

		InfoItemFieldValues infoItemFieldValues =
			infoItemFieldValuesProvider.getInfoItemFieldValues(article);

		return infoItemFieldValues.getInfoFieldValues();
	}

	private Locale _getTargetLocale(ActionRequest actionRequest) {
		String targetLanguageId = ParamUtil.getString(
			actionRequest, "targetLanguageId");

		return LocaleUtil.fromLanguageId(targetLanguageId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpdateTranslationMVCActionCommand.class);

	@Reference
	private InfoItemServiceTracker _infoItemServiceTracker;

	@Reference(
		target = "(item.class.name=com.liferay.journal.model.JournalArticle)"
	)
	private InfoItemFieldValuesUpdater<JournalArticle>
		_journalArticleInfoItemFieldValuesUpdater;

	@Reference
	private JournalArticleService _journalArticleService;

}