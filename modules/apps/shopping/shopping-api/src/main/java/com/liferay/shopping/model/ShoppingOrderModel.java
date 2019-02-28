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

package com.liferay.shopping.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ShoppingOrder service. Represents a row in the &quot;ShoppingOrder&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.shopping.model.impl.ShoppingOrderModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.shopping.model.impl.ShoppingOrderImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingOrder
 * @generated
 */
@ProviderType
public interface ShoppingOrderModel
	extends BaseModel<ShoppingOrder>, GroupedModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a shopping order model instance should use the {@link ShoppingOrder} interface instead.
	 */

	/**
	 * Returns the primary key of this shopping order.
	 *
	 * @return the primary key of this shopping order
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this shopping order.
	 *
	 * @param primaryKey the primary key of this shopping order
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the order ID of this shopping order.
	 *
	 * @return the order ID of this shopping order
	 */
	public long getOrderId();

	/**
	 * Sets the order ID of this shopping order.
	 *
	 * @param orderId the order ID of this shopping order
	 */
	public void setOrderId(long orderId);

	/**
	 * Returns the group ID of this shopping order.
	 *
	 * @return the group ID of this shopping order
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this shopping order.
	 *
	 * @param groupId the group ID of this shopping order
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this shopping order.
	 *
	 * @return the company ID of this shopping order
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this shopping order.
	 *
	 * @param companyId the company ID of this shopping order
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this shopping order.
	 *
	 * @return the user ID of this shopping order
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this shopping order.
	 *
	 * @param userId the user ID of this shopping order
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this shopping order.
	 *
	 * @return the user uuid of this shopping order
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this shopping order.
	 *
	 * @param userUuid the user uuid of this shopping order
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this shopping order.
	 *
	 * @return the user name of this shopping order
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this shopping order.
	 *
	 * @param userName the user name of this shopping order
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this shopping order.
	 *
	 * @return the create date of this shopping order
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this shopping order.
	 *
	 * @param createDate the create date of this shopping order
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this shopping order.
	 *
	 * @return the modified date of this shopping order
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this shopping order.
	 *
	 * @param modifiedDate the modified date of this shopping order
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the number of this shopping order.
	 *
	 * @return the number of this shopping order
	 */
	@AutoEscape
	public String getNumber();

	/**
	 * Sets the number of this shopping order.
	 *
	 * @param number the number of this shopping order
	 */
	public void setNumber(String number);

	/**
	 * Returns the tax of this shopping order.
	 *
	 * @return the tax of this shopping order
	 */
	public double getTax();

	/**
	 * Sets the tax of this shopping order.
	 *
	 * @param tax the tax of this shopping order
	 */
	public void setTax(double tax);

	/**
	 * Returns the shipping of this shopping order.
	 *
	 * @return the shipping of this shopping order
	 */
	public double getShipping();

	/**
	 * Sets the shipping of this shopping order.
	 *
	 * @param shipping the shipping of this shopping order
	 */
	public void setShipping(double shipping);

	/**
	 * Returns the alt shipping of this shopping order.
	 *
	 * @return the alt shipping of this shopping order
	 */
	@AutoEscape
	public String getAltShipping();

	/**
	 * Sets the alt shipping of this shopping order.
	 *
	 * @param altShipping the alt shipping of this shopping order
	 */
	public void setAltShipping(String altShipping);

	/**
	 * Returns the requires shipping of this shopping order.
	 *
	 * @return the requires shipping of this shopping order
	 */
	public boolean getRequiresShipping();

	/**
	 * Returns <code>true</code> if this shopping order is requires shipping.
	 *
	 * @return <code>true</code> if this shopping order is requires shipping; <code>false</code> otherwise
	 */
	public boolean isRequiresShipping();

	/**
	 * Sets whether this shopping order is requires shipping.
	 *
	 * @param requiresShipping the requires shipping of this shopping order
	 */
	public void setRequiresShipping(boolean requiresShipping);

	/**
	 * Returns the insure of this shopping order.
	 *
	 * @return the insure of this shopping order
	 */
	public boolean getInsure();

	/**
	 * Returns <code>true</code> if this shopping order is insure.
	 *
	 * @return <code>true</code> if this shopping order is insure; <code>false</code> otherwise
	 */
	public boolean isInsure();

	/**
	 * Sets whether this shopping order is insure.
	 *
	 * @param insure the insure of this shopping order
	 */
	public void setInsure(boolean insure);

	/**
	 * Returns the insurance of this shopping order.
	 *
	 * @return the insurance of this shopping order
	 */
	public double getInsurance();

	/**
	 * Sets the insurance of this shopping order.
	 *
	 * @param insurance the insurance of this shopping order
	 */
	public void setInsurance(double insurance);

	/**
	 * Returns the coupon codes of this shopping order.
	 *
	 * @return the coupon codes of this shopping order
	 */
	@AutoEscape
	public String getCouponCodes();

	/**
	 * Sets the coupon codes of this shopping order.
	 *
	 * @param couponCodes the coupon codes of this shopping order
	 */
	public void setCouponCodes(String couponCodes);

	/**
	 * Returns the coupon discount of this shopping order.
	 *
	 * @return the coupon discount of this shopping order
	 */
	public double getCouponDiscount();

	/**
	 * Sets the coupon discount of this shopping order.
	 *
	 * @param couponDiscount the coupon discount of this shopping order
	 */
	public void setCouponDiscount(double couponDiscount);

	/**
	 * Returns the billing first name of this shopping order.
	 *
	 * @return the billing first name of this shopping order
	 */
	@AutoEscape
	public String getBillingFirstName();

	/**
	 * Sets the billing first name of this shopping order.
	 *
	 * @param billingFirstName the billing first name of this shopping order
	 */
	public void setBillingFirstName(String billingFirstName);

	/**
	 * Returns the billing last name of this shopping order.
	 *
	 * @return the billing last name of this shopping order
	 */
	@AutoEscape
	public String getBillingLastName();

	/**
	 * Sets the billing last name of this shopping order.
	 *
	 * @param billingLastName the billing last name of this shopping order
	 */
	public void setBillingLastName(String billingLastName);

	/**
	 * Returns the billing email address of this shopping order.
	 *
	 * @return the billing email address of this shopping order
	 */
	@AutoEscape
	public String getBillingEmailAddress();

	/**
	 * Sets the billing email address of this shopping order.
	 *
	 * @param billingEmailAddress the billing email address of this shopping order
	 */
	public void setBillingEmailAddress(String billingEmailAddress);

	/**
	 * Returns the billing company of this shopping order.
	 *
	 * @return the billing company of this shopping order
	 */
	@AutoEscape
	public String getBillingCompany();

	/**
	 * Sets the billing company of this shopping order.
	 *
	 * @param billingCompany the billing company of this shopping order
	 */
	public void setBillingCompany(String billingCompany);

	/**
	 * Returns the billing street of this shopping order.
	 *
	 * @return the billing street of this shopping order
	 */
	@AutoEscape
	public String getBillingStreet();

	/**
	 * Sets the billing street of this shopping order.
	 *
	 * @param billingStreet the billing street of this shopping order
	 */
	public void setBillingStreet(String billingStreet);

	/**
	 * Returns the billing city of this shopping order.
	 *
	 * @return the billing city of this shopping order
	 */
	@AutoEscape
	public String getBillingCity();

	/**
	 * Sets the billing city of this shopping order.
	 *
	 * @param billingCity the billing city of this shopping order
	 */
	public void setBillingCity(String billingCity);

	/**
	 * Returns the billing state of this shopping order.
	 *
	 * @return the billing state of this shopping order
	 */
	@AutoEscape
	public String getBillingState();

	/**
	 * Sets the billing state of this shopping order.
	 *
	 * @param billingState the billing state of this shopping order
	 */
	public void setBillingState(String billingState);

	/**
	 * Returns the billing zip of this shopping order.
	 *
	 * @return the billing zip of this shopping order
	 */
	@AutoEscape
	public String getBillingZip();

	/**
	 * Sets the billing zip of this shopping order.
	 *
	 * @param billingZip the billing zip of this shopping order
	 */
	public void setBillingZip(String billingZip);

	/**
	 * Returns the billing country of this shopping order.
	 *
	 * @return the billing country of this shopping order
	 */
	@AutoEscape
	public String getBillingCountry();

	/**
	 * Sets the billing country of this shopping order.
	 *
	 * @param billingCountry the billing country of this shopping order
	 */
	public void setBillingCountry(String billingCountry);

	/**
	 * Returns the billing phone of this shopping order.
	 *
	 * @return the billing phone of this shopping order
	 */
	@AutoEscape
	public String getBillingPhone();

	/**
	 * Sets the billing phone of this shopping order.
	 *
	 * @param billingPhone the billing phone of this shopping order
	 */
	public void setBillingPhone(String billingPhone);

	/**
	 * Returns the ship to billing of this shopping order.
	 *
	 * @return the ship to billing of this shopping order
	 */
	public boolean getShipToBilling();

	/**
	 * Returns <code>true</code> if this shopping order is ship to billing.
	 *
	 * @return <code>true</code> if this shopping order is ship to billing; <code>false</code> otherwise
	 */
	public boolean isShipToBilling();

	/**
	 * Sets whether this shopping order is ship to billing.
	 *
	 * @param shipToBilling the ship to billing of this shopping order
	 */
	public void setShipToBilling(boolean shipToBilling);

	/**
	 * Returns the shipping first name of this shopping order.
	 *
	 * @return the shipping first name of this shopping order
	 */
	@AutoEscape
	public String getShippingFirstName();

	/**
	 * Sets the shipping first name of this shopping order.
	 *
	 * @param shippingFirstName the shipping first name of this shopping order
	 */
	public void setShippingFirstName(String shippingFirstName);

	/**
	 * Returns the shipping last name of this shopping order.
	 *
	 * @return the shipping last name of this shopping order
	 */
	@AutoEscape
	public String getShippingLastName();

	/**
	 * Sets the shipping last name of this shopping order.
	 *
	 * @param shippingLastName the shipping last name of this shopping order
	 */
	public void setShippingLastName(String shippingLastName);

	/**
	 * Returns the shipping email address of this shopping order.
	 *
	 * @return the shipping email address of this shopping order
	 */
	@AutoEscape
	public String getShippingEmailAddress();

	/**
	 * Sets the shipping email address of this shopping order.
	 *
	 * @param shippingEmailAddress the shipping email address of this shopping order
	 */
	public void setShippingEmailAddress(String shippingEmailAddress);

	/**
	 * Returns the shipping company of this shopping order.
	 *
	 * @return the shipping company of this shopping order
	 */
	@AutoEscape
	public String getShippingCompany();

	/**
	 * Sets the shipping company of this shopping order.
	 *
	 * @param shippingCompany the shipping company of this shopping order
	 */
	public void setShippingCompany(String shippingCompany);

	/**
	 * Returns the shipping street of this shopping order.
	 *
	 * @return the shipping street of this shopping order
	 */
	@AutoEscape
	public String getShippingStreet();

	/**
	 * Sets the shipping street of this shopping order.
	 *
	 * @param shippingStreet the shipping street of this shopping order
	 */
	public void setShippingStreet(String shippingStreet);

	/**
	 * Returns the shipping city of this shopping order.
	 *
	 * @return the shipping city of this shopping order
	 */
	@AutoEscape
	public String getShippingCity();

	/**
	 * Sets the shipping city of this shopping order.
	 *
	 * @param shippingCity the shipping city of this shopping order
	 */
	public void setShippingCity(String shippingCity);

	/**
	 * Returns the shipping state of this shopping order.
	 *
	 * @return the shipping state of this shopping order
	 */
	@AutoEscape
	public String getShippingState();

	/**
	 * Sets the shipping state of this shopping order.
	 *
	 * @param shippingState the shipping state of this shopping order
	 */
	public void setShippingState(String shippingState);

	/**
	 * Returns the shipping zip of this shopping order.
	 *
	 * @return the shipping zip of this shopping order
	 */
	@AutoEscape
	public String getShippingZip();

	/**
	 * Sets the shipping zip of this shopping order.
	 *
	 * @param shippingZip the shipping zip of this shopping order
	 */
	public void setShippingZip(String shippingZip);

	/**
	 * Returns the shipping country of this shopping order.
	 *
	 * @return the shipping country of this shopping order
	 */
	@AutoEscape
	public String getShippingCountry();

	/**
	 * Sets the shipping country of this shopping order.
	 *
	 * @param shippingCountry the shipping country of this shopping order
	 */
	public void setShippingCountry(String shippingCountry);

	/**
	 * Returns the shipping phone of this shopping order.
	 *
	 * @return the shipping phone of this shopping order
	 */
	@AutoEscape
	public String getShippingPhone();

	/**
	 * Sets the shipping phone of this shopping order.
	 *
	 * @param shippingPhone the shipping phone of this shopping order
	 */
	public void setShippingPhone(String shippingPhone);

	/**
	 * Returns the cc name of this shopping order.
	 *
	 * @return the cc name of this shopping order
	 */
	@AutoEscape
	public String getCcName();

	/**
	 * Sets the cc name of this shopping order.
	 *
	 * @param ccName the cc name of this shopping order
	 */
	public void setCcName(String ccName);

	/**
	 * Returns the cc type of this shopping order.
	 *
	 * @return the cc type of this shopping order
	 */
	@AutoEscape
	public String getCcType();

	/**
	 * Sets the cc type of this shopping order.
	 *
	 * @param ccType the cc type of this shopping order
	 */
	public void setCcType(String ccType);

	/**
	 * Returns the cc number of this shopping order.
	 *
	 * @return the cc number of this shopping order
	 */
	@AutoEscape
	public String getCcNumber();

	/**
	 * Sets the cc number of this shopping order.
	 *
	 * @param ccNumber the cc number of this shopping order
	 */
	public void setCcNumber(String ccNumber);

	/**
	 * Returns the cc exp month of this shopping order.
	 *
	 * @return the cc exp month of this shopping order
	 */
	public int getCcExpMonth();

	/**
	 * Sets the cc exp month of this shopping order.
	 *
	 * @param ccExpMonth the cc exp month of this shopping order
	 */
	public void setCcExpMonth(int ccExpMonth);

	/**
	 * Returns the cc exp year of this shopping order.
	 *
	 * @return the cc exp year of this shopping order
	 */
	public int getCcExpYear();

	/**
	 * Sets the cc exp year of this shopping order.
	 *
	 * @param ccExpYear the cc exp year of this shopping order
	 */
	public void setCcExpYear(int ccExpYear);

	/**
	 * Returns the cc ver number of this shopping order.
	 *
	 * @return the cc ver number of this shopping order
	 */
	@AutoEscape
	public String getCcVerNumber();

	/**
	 * Sets the cc ver number of this shopping order.
	 *
	 * @param ccVerNumber the cc ver number of this shopping order
	 */
	public void setCcVerNumber(String ccVerNumber);

	/**
	 * Returns the comments of this shopping order.
	 *
	 * @return the comments of this shopping order
	 */
	@AutoEscape
	public String getComments();

	/**
	 * Sets the comments of this shopping order.
	 *
	 * @param comments the comments of this shopping order
	 */
	public void setComments(String comments);

	/**
	 * Returns the pp txn ID of this shopping order.
	 *
	 * @return the pp txn ID of this shopping order
	 */
	@AutoEscape
	public String getPpTxnId();

	/**
	 * Sets the pp txn ID of this shopping order.
	 *
	 * @param ppTxnId the pp txn ID of this shopping order
	 */
	public void setPpTxnId(String ppTxnId);

	/**
	 * Returns the pp payment status of this shopping order.
	 *
	 * @return the pp payment status of this shopping order
	 */
	@AutoEscape
	public String getPpPaymentStatus();

	/**
	 * Sets the pp payment status of this shopping order.
	 *
	 * @param ppPaymentStatus the pp payment status of this shopping order
	 */
	public void setPpPaymentStatus(String ppPaymentStatus);

	/**
	 * Returns the pp payment gross of this shopping order.
	 *
	 * @return the pp payment gross of this shopping order
	 */
	public double getPpPaymentGross();

	/**
	 * Sets the pp payment gross of this shopping order.
	 *
	 * @param ppPaymentGross the pp payment gross of this shopping order
	 */
	public void setPpPaymentGross(double ppPaymentGross);

	/**
	 * Returns the pp receiver email of this shopping order.
	 *
	 * @return the pp receiver email of this shopping order
	 */
	@AutoEscape
	public String getPpReceiverEmail();

	/**
	 * Sets the pp receiver email of this shopping order.
	 *
	 * @param ppReceiverEmail the pp receiver email of this shopping order
	 */
	public void setPpReceiverEmail(String ppReceiverEmail);

	/**
	 * Returns the pp payer email of this shopping order.
	 *
	 * @return the pp payer email of this shopping order
	 */
	@AutoEscape
	public String getPpPayerEmail();

	/**
	 * Sets the pp payer email of this shopping order.
	 *
	 * @param ppPayerEmail the pp payer email of this shopping order
	 */
	public void setPpPayerEmail(String ppPayerEmail);

	/**
	 * Returns the send order email of this shopping order.
	 *
	 * @return the send order email of this shopping order
	 */
	public boolean getSendOrderEmail();

	/**
	 * Returns <code>true</code> if this shopping order is send order email.
	 *
	 * @return <code>true</code> if this shopping order is send order email; <code>false</code> otherwise
	 */
	public boolean isSendOrderEmail();

	/**
	 * Sets whether this shopping order is send order email.
	 *
	 * @param sendOrderEmail the send order email of this shopping order
	 */
	public void setSendOrderEmail(boolean sendOrderEmail);

	/**
	 * Returns the send shipping email of this shopping order.
	 *
	 * @return the send shipping email of this shopping order
	 */
	public boolean getSendShippingEmail();

	/**
	 * Returns <code>true</code> if this shopping order is send shipping email.
	 *
	 * @return <code>true</code> if this shopping order is send shipping email; <code>false</code> otherwise
	 */
	public boolean isSendShippingEmail();

	/**
	 * Sets whether this shopping order is send shipping email.
	 *
	 * @param sendShippingEmail the send shipping email of this shopping order
	 */
	public void setSendShippingEmail(boolean sendShippingEmail);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(ShoppingOrder shoppingOrder);

	@Override
	public int hashCode();

	@Override
	public CacheModel<ShoppingOrder> toCacheModel();

	@Override
	public ShoppingOrder toEscapedModel();

	@Override
	public ShoppingOrder toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}