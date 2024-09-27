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

package com.liferay.headless.commerce.admin.channel.internal.resource.v1_0;

import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRelQualifier;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelQualifierService;
import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOptionQualifier;
import com.liferay.commerce.shipping.engine.fixed.service.CommerceShippingFixedOptionQualifierService;
import com.liferay.headless.commerce.admin.channel.dto.v1_0.PaymentMethodGroupRelTerm;
import com.liferay.headless.commerce.admin.channel.dto.v1_0.ShippingFixedOptionTerm;
import com.liferay.headless.commerce.admin.channel.dto.v1_0.Term;
import com.liferay.headless.commerce.admin.channel.internal.dto.v1_0.converter.TermDTOConverter;
import com.liferay.headless.commerce.admin.channel.resource.v1_0.TermResource;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldSupport;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Riccardo Alberti
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/term.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {NestedFieldSupport.class, TermResource.class}
)
public class TermResourceImpl
	extends BaseTermResourceImpl implements NestedFieldSupport {

	@NestedField(parentClass = PaymentMethodGroupRelTerm.class, value = "term")
	@Override
	public Term getPaymentMethodGroupRelTermTerm(Long id) throws Exception {
		CommercePaymentMethodGroupRelQualifier
			commercePaymentMethodGroupRelQualifier =
				_commercePaymentMethodGroupRelQualifierService.
					getCommercePaymentMethodGroupRelQualifier(id);

		return _termDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				commercePaymentMethodGroupRelQualifier.getClassPK(),
				contextAcceptLanguage.getPreferredLocale()));
	}

	@NestedField(parentClass = ShippingFixedOptionTerm.class, value = "term")
	@Override
	public Term getShippingFixedOptionTermTerm(Long id) throws Exception {
		CommerceShippingFixedOptionQualifier
			commerceShippingFixedOptionQualifier =
				_commerceShippingFixedOptionQualifierService.
					getCommerceShippingFixedOptionQualifier(id);

		return _termDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				commerceShippingFixedOptionQualifier.getClassPK(),
				contextAcceptLanguage.getPreferredLocale()));
	}

	@Reference
	private CommercePaymentMethodGroupRelQualifierService
		_commercePaymentMethodGroupRelQualifierService;

	@Reference
	private CommerceShippingFixedOptionQualifierService
		_commerceShippingFixedOptionQualifierService;

	@Reference
	private TermDTOConverter _termDTOConverter;

}