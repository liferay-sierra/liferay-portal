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

/**
 * The Storage Formatter Utility
 *
 * @deprecated As of Athanasius (7.3.x), replaced by Liferay.Util.formatStorage
 * @module liferay-storage-formatter
 */

AUI.add(
	'liferay-storage-formatter',
	(A) => {
		const Lang = A.Lang;

		const StorageFormatter = function () {};

		StorageFormatter.NAME = 'storageformatter';

		StorageFormatter.ATTRS = {
			addSpaceBeforeSuffix: {
				validator: Lang.isBoolean,
				value: false,
			},

			decimalSeparator: {
				validator: Lang.isString,
				value: '.',
			},

			denominator: {
				validator: Lang.isNumber,
				value: 1024.0,
			},

			suffixGB: {
				validator: Lang.isString,
				value: 'GB',
			},

			suffixKB: {
				validator: Lang.isString,
				value: 'KB',
			},

			suffixMB: {
				validator: Lang.isString,
				value: 'MB',
			},
		};

		StorageFormatter.prototype = {
			formatStorage(size) {
				const instance = this;

				const addSpaceBeforeSuffix = instance.get(
					'addSpaceBeforeSuffix'
				);
				const decimalSeparator = instance.get('decimalSeparator');
				const denominator = instance.get('denominator');
				const suffixGB = instance.get('suffixGB');
				const suffixKB = instance.get('suffixKB');
				const suffixMB = instance.get('suffixMB');

				return Liferay.Util.formatStorage(size, {
					addSpaceBeforeSuffix,
					decimalSeparator,
					denominator,
					suffixGB,
					suffixKB,
					suffixMB,
				});
			},
		};

		Liferay.StorageFormatter = StorageFormatter;
	},
	'',
	{
		requires: ['aui-base', 'datatype-number-format'],
	}
);
