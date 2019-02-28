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
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ShoppingOrderItem service. Represents a row in the &quot;ShoppingOrderItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.shopping.model.impl.ShoppingOrderItemModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.shopping.model.impl.ShoppingOrderItemImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingOrderItem
 * @generated
 */
@ProviderType
public interface ShoppingOrderItemModel
	extends BaseModel<ShoppingOrderItem>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a shopping order item model instance should use the {@link ShoppingOrderItem} interface instead.
	 */

	/**
	 * Returns the primary key of this shopping order item.
	 *
	 * @return the primary key of this shopping order item
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this shopping order item.
	 *
	 * @param primaryKey the primary key of this shopping order item
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the order item ID of this shopping order item.
	 *
	 * @return the order item ID of this shopping order item
	 */
	public long getOrderItemId();

	/**
	 * Sets the order item ID of this shopping order item.
	 *
	 * @param orderItemId the order item ID of this shopping order item
	 */
	public void setOrderItemId(long orderItemId);

	/**
	 * Returns the company ID of this shopping order item.
	 *
	 * @return the company ID of this shopping order item
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this shopping order item.
	 *
	 * @param companyId the company ID of this shopping order item
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the order ID of this shopping order item.
	 *
	 * @return the order ID of this shopping order item
	 */
	public long getOrderId();

	/**
	 * Sets the order ID of this shopping order item.
	 *
	 * @param orderId the order ID of this shopping order item
	 */
	public void setOrderId(long orderId);

	/**
	 * Returns the item ID of this shopping order item.
	 *
	 * @return the item ID of this shopping order item
	 */
	@AutoEscape
	public String getItemId();

	/**
	 * Sets the item ID of this shopping order item.
	 *
	 * @param itemId the item ID of this shopping order item
	 */
	public void setItemId(String itemId);

	/**
	 * Returns the sku of this shopping order item.
	 *
	 * @return the sku of this shopping order item
	 */
	@AutoEscape
	public String getSku();

	/**
	 * Sets the sku of this shopping order item.
	 *
	 * @param sku the sku of this shopping order item
	 */
	public void setSku(String sku);

	/**
	 * Returns the name of this shopping order item.
	 *
	 * @return the name of this shopping order item
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this shopping order item.
	 *
	 * @param name the name of this shopping order item
	 */
	public void setName(String name);

	/**
	 * Returns the description of this shopping order item.
	 *
	 * @return the description of this shopping order item
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this shopping order item.
	 *
	 * @param description the description of this shopping order item
	 */
	public void setDescription(String description);

	/**
	 * Returns the properties of this shopping order item.
	 *
	 * @return the properties of this shopping order item
	 */
	@AutoEscape
	public String getProperties();

	/**
	 * Sets the properties of this shopping order item.
	 *
	 * @param properties the properties of this shopping order item
	 */
	public void setProperties(String properties);

	/**
	 * Returns the price of this shopping order item.
	 *
	 * @return the price of this shopping order item
	 */
	public double getPrice();

	/**
	 * Sets the price of this shopping order item.
	 *
	 * @param price the price of this shopping order item
	 */
	public void setPrice(double price);

	/**
	 * Returns the quantity of this shopping order item.
	 *
	 * @return the quantity of this shopping order item
	 */
	public int getQuantity();

	/**
	 * Sets the quantity of this shopping order item.
	 *
	 * @param quantity the quantity of this shopping order item
	 */
	public void setQuantity(int quantity);

	/**
	 * Returns the shipped date of this shopping order item.
	 *
	 * @return the shipped date of this shopping order item
	 */
	public Date getShippedDate();

	/**
	 * Sets the shipped date of this shopping order item.
	 *
	 * @param shippedDate the shipped date of this shopping order item
	 */
	public void setShippedDate(Date shippedDate);

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
	public int compareTo(ShoppingOrderItem shoppingOrderItem);

	@Override
	public int hashCode();

	@Override
	public CacheModel<ShoppingOrderItem> toCacheModel();

	@Override
	public ShoppingOrderItem toEscapedModel();

	@Override
	public ShoppingOrderItem toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}