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

import {gql} from '@apollo/client';

export const getTestrayRoutines = gql`
	query getTestrayRoutines(
		$filter: String
		$page: Int = 1
		$pageSize: Int = 20
		$scopeKey: String
	) {
		c {
			testrayRoutines(
				filter: $filter
				page: $page
				pageSize: $pageSize
				scopeKey: $scopeKey
			) {
				items {
					name
					testrayRoutineId
				}
				lastPage
				page
				pageSize
				totalCount
			}
		}
	}
`;

export const getTestrayRoutine = gql`
	query getTestrayRoutine($testrayRoutineId: Long!) {
		c {
			testrayRoutine(testrayRoutineId: $testrayRoutineId) {
				autoanalyze
				name
				testrayRoutineId
			}
		}
	}
`;
