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

import {useModal} from '@clayui/modal';
import {act, cleanup, render, renderHook} from '@testing-library/react';
import {vi} from 'vitest';

import Modal from './Modal';

describe('Modal', () => {
	beforeAll(() => {
		vi.resetAllMocks();
		vi.useFakeTimers();
	});

	afterEach(() => {
		cleanup();
		vi.clearAllTimers();
	});

	it('render with success', async () => {
		const {asFragment, container, rerender} = render(
			<Modal observer={null} visible={false}>
				<span>Content...</span>
			</Modal>
		);

		const {result} = renderHook(() => useModal());

		expect(asFragment()).toMatchSnapshot();
		expect(document.querySelector('.modal-open')).toBeFalsy();
		expect(container.querySelector('span')).toBeFalsy();

		rerender(
			<Modal observer={result.current.observer} visible>
				<span>Content...</span>
			</Modal>
		);

		await act(async () => {
			await vi.runAllTimers();
		});

		expect(document.querySelector('.modal-open')).toBeTruthy();
	});
});
