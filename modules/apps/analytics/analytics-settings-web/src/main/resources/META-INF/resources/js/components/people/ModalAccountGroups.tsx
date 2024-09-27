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

import React from 'react';

import {fetchAccountGroups} from '../../utils/api';
import Modal, {ICommonModalProps} from './Modal';
import {useAddItems} from './utils';

const ModalAccountGroups: React.FC<ICommonModalProps> = ({
	observer,
	onCloseModal,
	syncAllAccounts,
	syncAllContacts,
}) => {
	const addItems = useAddItems({
		name: 'syncedAccountGroupIds',
		onCloseModal,
		syncAllAccounts,
		syncAllContacts,
	});

	return (
		<Modal
			columns={[
				{
					expanded: true,
					label: Liferay.Language.get('account-group'),
					value: 'name',
				},
			]}
			emptyStateTitle={Liferay.Language.get(
				'there-are-no-account-groups'
			)}
			fetchFn={fetchAccountGroups}
			noResultsTitle={Liferay.Language.get(
				'no-account-groups-were-found'
			)}
			observer={observer}
			onAddItems={addItems}
			onCloseModal={onCloseModal}
			title={Liferay.Language.get('add-account-groups')}
		/>
	);
};

export default ModalAccountGroups;
