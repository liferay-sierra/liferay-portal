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

import ClayAlert from '@clayui/alert';
import ClayButton from '@clayui/button';
import ClayForm from '@clayui/form';
import ClayModal, {ClayModalProvider, useModal} from '@clayui/modal';
import {Observer} from '@clayui/modal/lib/types';
import {
	API,
	FormError,
	Input,
	Select,
	useForm,
} from '@liferay/object-js-components-web';
import React, {useEffect, useState} from 'react';

import {
	firstLetterUppercase,
	removeAllSpecialCharacters,
} from '../utils/string';

const normalizeName: TNormalizeName = (str) => {
	const split = str.split(' ');
	const capitalizeFirstLetters = split.map((str: string) =>
		firstLetterUppercase(str)
	);
	const join = capitalizeFirstLetters.join('');

	return removeAllSpecialCharacters(join);
};

const ModalAddObjectDefinition: React.FC<IProps> = ({
	apiURL,
	observer,
	onClose,
	storageTypes,
}) => {
	const initialValues: TInitialValues = {
		label: '',
		name: undefined,
		pluralLabel: '',
		storageType: storageTypes[0],
	};
	const [error, setError] = useState<string>('');

	const onSubmit = async ({
		label,
		name,
		pluralLabel,
		storageType,
	}: TInitialValues) => {
		const defaultLanguageId = Liferay.ThemeDisplay.getDefaultLanguageId();
		const objectDefinition: ObjectDefinition = {
			label: {
				[defaultLanguageId]: label,
			},
			name: name || normalizeName(label),
			objectFields: [],
			pluralLabel: {
				[defaultLanguageId]: pluralLabel,
			},
			scope: 'company',
		};

		if (Liferay.FeatureFlags['LPS-135430']) {
			objectDefinition.storageType = storageType.toLowerCase();
		}
		try {
			await API.save(apiURL, objectDefinition, 'POST');

			onClose();
			window.location.reload();
		}
		catch (error) {
			setError((error as Error).message);
		}
	};

	const validate = (values: TInitialValues) => {
		const errors: FormError<TInitialValues> = {};

		if (!values.label) {
			errors.label = Liferay.Language.get('required');
		}
		if (!(values.name ?? values.label)) {
			errors.name = Liferay.Language.get('required');
		}
		if (!values.pluralLabel) {
			errors.pluralLabel = Liferay.Language.get('required');
		}

		return errors;
	};

	const {errors, handleChange, handleSubmit, setValues, values} = useForm({
		initialValues,
		onSubmit,
		validate,
	});

	const selectedStorageType = (storageType: string) => {
		return storageTypes.find(
			(item) => item?.toLowerCase() === storageType?.toLowerCase()
		);
	};

	return (
		<ClayModal observer={observer}>
			<ClayForm onSubmit={handleSubmit}>
				<ClayModal.Header>
					{Liferay.Language.get('new-custom-object')}
				</ClayModal.Header>

				<ClayModal.Body>
					{error && (
						<ClayAlert displayType="danger">{error}</ClayAlert>
					)}

					<Input
						error={errors.label}
						id="objectDefinitionLabel"
						label={Liferay.Language.get('label')}
						name="label"
						onChange={handleChange}
						required
						value={values.label}
					/>

					<Input
						error={errors.pluralLabel}
						id="objectDefinitionPluralLabel"
						label={Liferay.Language.get('plural-label')}
						name="pluralLabel"
						onChange={handleChange}
						required
						value={values.pluralLabel}
					/>

					<Input
						error={errors.name}
						id="objectDefinitionName"
						label={Liferay.Language.get('object-name')}
						name="name"
						onChange={handleChange}
						required
						value={values.name ?? normalizeName(values.label)}
					/>

					{Liferay.FeatureFlags['LPS-135430'] && (
						<Select
							label={Liferay.Language.get('storage-type')}
							name="storageType"
							onChange={({target: {value}}) => {
								setValues({
									...values,
									storageType: storageTypes.find(
										(storageType) => storageType === value
									),
								});
							}}
							options={storageTypes.map((storageType) => {
								return {label: storageType};
							})}
							tooltip={Liferay.Language.get(
								'object-definition-storage-type-tooltip'
							)}
							value={selectedStorageType(values.storageType)}
						/>
					)}
				</ClayModal.Body>

				<ClayModal.Footer
					last={
						<ClayButton.Group key={1} spaced>
							<ClayButton
								displayType="secondary"
								onClick={() => onClose()}
							>
								{Liferay.Language.get('cancel')}
							</ClayButton>

							<ClayButton displayType="primary" type="submit">
								{Liferay.Language.get('save')}
							</ClayButton>
						</ClayButton.Group>
					}
				/>
			</ClayForm>
		</ClayModal>
	);
};

interface IProps extends React.HTMLAttributes<HTMLElement> {
	apiURL: string;
	observer: Observer;
	onClose: () => void;
	storageTypes: string[];
}

type TInitialValues = {
	label: string;
	name?: string;
	pluralLabel: string;
	storageType: string;
};

type ObjectDefinition = {
	label: {[key: string]: string};
	name?: string;
	objectFields?: unknown[];
	pluralLabel: {[key: string]: string};
	scope: string;
	storageType?: string;
};

type TNormalizeName = (str: string) => string;

const ModalWithProvider: React.FC<IProps> = ({apiURL, storageTypes}) => {
	const [visibleModal, setVisibleModal] = useState<boolean>(false);
	const {observer, onClose} = useModal({
		onClose: () => setVisibleModal(false),
	});

	useEffect(() => {
		Liferay.on('addObjectDefinition', () => setVisibleModal(true));

		return () => {
			Liferay.detach('addObjectDefinition');
		};
	}, []);

	return (
		<ClayModalProvider>
			{visibleModal && (
				<ModalAddObjectDefinition
					apiURL={apiURL}
					observer={observer}
					onClose={onClose}
					storageTypes={storageTypes}
				/>
			)}
		</ClayModalProvider>
	);
};

export default ModalWithProvider;
