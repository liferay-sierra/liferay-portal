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

import ClayForm, {ClaySelect} from '@clayui/form';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {
	HEADLESS_BATCH_PLANNER_URL,
	HEADLESS_ENDPOINT_POLICY_NAME,
	NULL_TEMPLATE_VALUE,
	TEMPLATE_CREATED,
	TEMPLATE_SELECTED_EVENT,
	TEMPLATE_SOILED,
} from './constants';
import {fireTemplateSelectionEvent} from './getMappingFromTemplate';

const TemplateSelect = ({
	initialTemplate,
	initialTemplateOptions = [],
	portletNamespace,
}) => {
	const [templateOptions, setTemplateOptions] = useState(
		initialTemplateOptions
	);
	const [selectedTemplateId, setTemplate] = useState(
		initialTemplateOptions.find((option) => option.selected)?.value
	);

	useEffect(() => {
		function handleTemplateCreated({batchPlannerPlanId, name}) {
			setTemplate(batchPlannerPlanId);

			setTemplateOptions((options) => [
				{label: name, value: batchPlannerPlanId},
				...options,
			]);

			fireTemplateSelectionEvent(
				batchPlannerPlanId,
				NULL_TEMPLATE_VALUE,
				TEMPLATE_SELECTED_EVENT,
				HEADLESS_BATCH_PLANNER_URL,
				HEADLESS_ENDPOINT_POLICY_NAME
			);
		}

		Liferay.on(TEMPLATE_CREATED, handleTemplateCreated);

		return () => Liferay.detach(TEMPLATE_CREATED);
	}, []);

	useEffect(() => {
		if (initialTemplate) {
			Liferay.fire(TEMPLATE_SELECTED_EVENT, {
				template: initialTemplate,
			});
		}
	}, [initialTemplate]);

	useEffect(() => {
		function handleTemplateDirty() {
			setTemplate(NULL_TEMPLATE_VALUE);
		}

		Liferay.on(TEMPLATE_SOILED, handleTemplateDirty);

		return () => {
			Liferay.detach(TEMPLATE_SOILED, handleTemplateDirty);
		};
	}, []);

	const onChange = (event) => {
		const newTemplateId = event.target.value;

		setTemplate(newTemplateId);

		fireTemplateSelectionEvent(
			newTemplateId,
			NULL_TEMPLATE_VALUE,
			TEMPLATE_SELECTED_EVENT,
			HEADLESS_BATCH_PLANNER_URL,
			HEADLESS_ENDPOINT_POLICY_NAME
		);
	};

	const selectId = `${portletNamespace}templateName`;

	return (
		<ClayForm.Group>
			<label htmlFor={selectId}>{Liferay.Language.get('template')}</label>

			<ClaySelect
				id={selectId}
				name={selectId}
				onChange={onChange}
				value={selectedTemplateId}
			>
				<ClaySelect.Option value={NULL_TEMPLATE_VALUE} />

				{templateOptions.map((option) => (
					<ClaySelect.Option
						key={option.value}
						label={option.label}
						value={option.value}
					/>
				))}
			</ClaySelect>
		</ClayForm.Group>
	);
};

TemplateSelect.propTypes = {
	portletNamespace: PropTypes.string.isRequired,
	selectedTemplateClassName: PropTypes.string,
	selectedTemplateHeadlessEndpoint: PropTypes.string,
	selectedTemplateMapping: PropTypes.object,
	templatesOptions: PropTypes.arrayOf(PropTypes.object),
};

export default TemplateSelect;
