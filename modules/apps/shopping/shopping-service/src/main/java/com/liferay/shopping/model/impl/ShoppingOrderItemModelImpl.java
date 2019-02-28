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

package com.liferay.shopping.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.shopping.model.ShoppingOrderItem;
import com.liferay.shopping.model.ShoppingOrderItemModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the ShoppingOrderItem service. Represents a row in the &quot;ShoppingOrderItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ShoppingOrderItemModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ShoppingOrderItemImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingOrderItemImpl
 * @generated
 */
@ProviderType
public class ShoppingOrderItemModelImpl
	extends BaseModelImpl<ShoppingOrderItem> implements ShoppingOrderItemModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a shopping order item model instance should use the <code>ShoppingOrderItem</code> interface instead.
	 */
	public static final String TABLE_NAME = "ShoppingOrderItem";

	public static final Object[][] TABLE_COLUMNS = {
		{"orderItemId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"orderId", Types.BIGINT}, {"itemId", Types.CLOB},
		{"sku", Types.VARCHAR}, {"name", Types.VARCHAR},
		{"description", Types.VARCHAR}, {"properties", Types.VARCHAR},
		{"price", Types.DOUBLE}, {"quantity", Types.INTEGER},
		{"shippedDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("orderItemId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("orderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("itemId", Types.CLOB);
		TABLE_COLUMNS_MAP.put("sku", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("properties", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("price", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("quantity", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("shippedDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ShoppingOrderItem (orderItemId LONG not null primary key,companyId LONG,orderId LONG,itemId TEXT null,sku VARCHAR(75) null,name VARCHAR(200) null,description STRING null,properties STRING null,price DOUBLE,quantity INTEGER,shippedDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table ShoppingOrderItem";

	public static final String ORDER_BY_JPQL =
		" ORDER BY shoppingOrderItem.name ASC, shoppingOrderItem.description ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ShoppingOrderItem.name ASC, ShoppingOrderItem.description ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.shopping.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.shopping.model.ShoppingOrderItem"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.shopping.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.shopping.model.ShoppingOrderItem"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.shopping.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.shopping.model.ShoppingOrderItem"),
		true);

	public static final long ORDERID_COLUMN_BITMASK = 1L;

	public static final long NAME_COLUMN_BITMASK = 2L;

	public static final long DESCRIPTION_COLUMN_BITMASK = 4L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.shopping.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.shopping.model.ShoppingOrderItem"));

	public ShoppingOrderItemModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _orderItemId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOrderItemId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _orderItemId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ShoppingOrderItem.class;
	}

	@Override
	public String getModelClassName() {
		return ShoppingOrderItem.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ShoppingOrderItem, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ShoppingOrderItem, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ShoppingOrderItem, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ShoppingOrderItem)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ShoppingOrderItem, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ShoppingOrderItem, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ShoppingOrderItem)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ShoppingOrderItem, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ShoppingOrderItem, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<ShoppingOrderItem, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ShoppingOrderItem, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ShoppingOrderItem, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<ShoppingOrderItem, Object>>();
		Map<String, BiConsumer<ShoppingOrderItem, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<ShoppingOrderItem, ?>>();

		attributeGetterFunctions.put(
			"orderItemId",
			new Function<ShoppingOrderItem, Object>() {

				@Override
				public Object apply(ShoppingOrderItem shoppingOrderItem) {
					return shoppingOrderItem.getOrderItemId();
				}

			});
		attributeSetterBiConsumers.put(
			"orderItemId",
			new BiConsumer<ShoppingOrderItem, Object>() {

				@Override
				public void accept(
					ShoppingOrderItem shoppingOrderItem, Object orderItemId) {

					shoppingOrderItem.setOrderItemId((Long)orderItemId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<ShoppingOrderItem, Object>() {

				@Override
				public Object apply(ShoppingOrderItem shoppingOrderItem) {
					return shoppingOrderItem.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<ShoppingOrderItem, Object>() {

				@Override
				public void accept(
					ShoppingOrderItem shoppingOrderItem, Object companyId) {

					shoppingOrderItem.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"orderId",
			new Function<ShoppingOrderItem, Object>() {

				@Override
				public Object apply(ShoppingOrderItem shoppingOrderItem) {
					return shoppingOrderItem.getOrderId();
				}

			});
		attributeSetterBiConsumers.put(
			"orderId",
			new BiConsumer<ShoppingOrderItem, Object>() {

				@Override
				public void accept(
					ShoppingOrderItem shoppingOrderItem, Object orderId) {

					shoppingOrderItem.setOrderId((Long)orderId);
				}

			});
		attributeGetterFunctions.put(
			"itemId",
			new Function<ShoppingOrderItem, Object>() {

				@Override
				public Object apply(ShoppingOrderItem shoppingOrderItem) {
					return shoppingOrderItem.getItemId();
				}

			});
		attributeSetterBiConsumers.put(
			"itemId",
			new BiConsumer<ShoppingOrderItem, Object>() {

				@Override
				public void accept(
					ShoppingOrderItem shoppingOrderItem, Object itemId) {

					shoppingOrderItem.setItemId((String)itemId);
				}

			});
		attributeGetterFunctions.put(
			"sku",
			new Function<ShoppingOrderItem, Object>() {

				@Override
				public Object apply(ShoppingOrderItem shoppingOrderItem) {
					return shoppingOrderItem.getSku();
				}

			});
		attributeSetterBiConsumers.put(
			"sku",
			new BiConsumer<ShoppingOrderItem, Object>() {

				@Override
				public void accept(
					ShoppingOrderItem shoppingOrderItem, Object sku) {

					shoppingOrderItem.setSku((String)sku);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<ShoppingOrderItem, Object>() {

				@Override
				public Object apply(ShoppingOrderItem shoppingOrderItem) {
					return shoppingOrderItem.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<ShoppingOrderItem, Object>() {

				@Override
				public void accept(
					ShoppingOrderItem shoppingOrderItem, Object name) {

					shoppingOrderItem.setName((String)name);
				}

			});
		attributeGetterFunctions.put(
			"description",
			new Function<ShoppingOrderItem, Object>() {

				@Override
				public Object apply(ShoppingOrderItem shoppingOrderItem) {
					return shoppingOrderItem.getDescription();
				}

			});
		attributeSetterBiConsumers.put(
			"description",
			new BiConsumer<ShoppingOrderItem, Object>() {

				@Override
				public void accept(
					ShoppingOrderItem shoppingOrderItem, Object description) {

					shoppingOrderItem.setDescription((String)description);
				}

			});
		attributeGetterFunctions.put(
			"properties",
			new Function<ShoppingOrderItem, Object>() {

				@Override
				public Object apply(ShoppingOrderItem shoppingOrderItem) {
					return shoppingOrderItem.getProperties();
				}

			});
		attributeSetterBiConsumers.put(
			"properties",
			new BiConsumer<ShoppingOrderItem, Object>() {

				@Override
				public void accept(
					ShoppingOrderItem shoppingOrderItem, Object properties) {

					shoppingOrderItem.setProperties((String)properties);
				}

			});
		attributeGetterFunctions.put(
			"price",
			new Function<ShoppingOrderItem, Object>() {

				@Override
				public Object apply(ShoppingOrderItem shoppingOrderItem) {
					return shoppingOrderItem.getPrice();
				}

			});
		attributeSetterBiConsumers.put(
			"price",
			new BiConsumer<ShoppingOrderItem, Object>() {

				@Override
				public void accept(
					ShoppingOrderItem shoppingOrderItem, Object price) {

					shoppingOrderItem.setPrice((Double)price);
				}

			});
		attributeGetterFunctions.put(
			"quantity",
			new Function<ShoppingOrderItem, Object>() {

				@Override
				public Object apply(ShoppingOrderItem shoppingOrderItem) {
					return shoppingOrderItem.getQuantity();
				}

			});
		attributeSetterBiConsumers.put(
			"quantity",
			new BiConsumer<ShoppingOrderItem, Object>() {

				@Override
				public void accept(
					ShoppingOrderItem shoppingOrderItem, Object quantity) {

					shoppingOrderItem.setQuantity((Integer)quantity);
				}

			});
		attributeGetterFunctions.put(
			"shippedDate",
			new Function<ShoppingOrderItem, Object>() {

				@Override
				public Object apply(ShoppingOrderItem shoppingOrderItem) {
					return shoppingOrderItem.getShippedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"shippedDate",
			new BiConsumer<ShoppingOrderItem, Object>() {

				@Override
				public void accept(
					ShoppingOrderItem shoppingOrderItem, Object shippedDate) {

					shoppingOrderItem.setShippedDate((Date)shippedDate);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getOrderItemId() {
		return _orderItemId;
	}

	@Override
	public void setOrderItemId(long orderItemId) {
		_orderItemId = orderItemId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public long getOrderId() {
		return _orderId;
	}

	@Override
	public void setOrderId(long orderId) {
		_columnBitmask |= ORDERID_COLUMN_BITMASK;

		if (!_setOriginalOrderId) {
			_setOriginalOrderId = true;

			_originalOrderId = _orderId;
		}

		_orderId = orderId;
	}

	public long getOriginalOrderId() {
		return _originalOrderId;
	}

	@Override
	public String getItemId() {
		if (_itemId == null) {
			return "";
		}
		else {
			return _itemId;
		}
	}

	@Override
	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	@Override
	public String getSku() {
		if (_sku == null) {
			return "";
		}
		else {
			return _sku;
		}
	}

	@Override
	public void setSku(String sku) {
		_sku = sku;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		_name = name;
	}

	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_columnBitmask = -1L;

		_description = description;
	}

	@Override
	public String getProperties() {
		if (_properties == null) {
			return "";
		}
		else {
			return _properties;
		}
	}

	@Override
	public void setProperties(String properties) {
		_properties = properties;
	}

	@Override
	public double getPrice() {
		return _price;
	}

	@Override
	public void setPrice(double price) {
		_price = price;
	}

	@Override
	public int getQuantity() {
		return _quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	@Override
	public Date getShippedDate() {
		return _shippedDate;
	}

	@Override
	public void setShippedDate(Date shippedDate) {
		_shippedDate = shippedDate;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), ShoppingOrderItem.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ShoppingOrderItem toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ShoppingOrderItem)ProxyUtil.newProxyInstance(
				_classLoader, _escapedModelInterfaces,
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ShoppingOrderItemImpl shoppingOrderItemImpl =
			new ShoppingOrderItemImpl();

		shoppingOrderItemImpl.setOrderItemId(getOrderItemId());
		shoppingOrderItemImpl.setCompanyId(getCompanyId());
		shoppingOrderItemImpl.setOrderId(getOrderId());
		shoppingOrderItemImpl.setItemId(getItemId());
		shoppingOrderItemImpl.setSku(getSku());
		shoppingOrderItemImpl.setName(getName());
		shoppingOrderItemImpl.setDescription(getDescription());
		shoppingOrderItemImpl.setProperties(getProperties());
		shoppingOrderItemImpl.setPrice(getPrice());
		shoppingOrderItemImpl.setQuantity(getQuantity());
		shoppingOrderItemImpl.setShippedDate(getShippedDate());

		shoppingOrderItemImpl.resetOriginalValues();

		return shoppingOrderItemImpl;
	}

	@Override
	public int compareTo(ShoppingOrderItem shoppingOrderItem) {
		int value = 0;

		value = getName().compareTo(shoppingOrderItem.getName());

		if (value != 0) {
			return value;
		}

		value = getDescription().compareTo(shoppingOrderItem.getDescription());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ShoppingOrderItem)) {
			return false;
		}

		ShoppingOrderItem shoppingOrderItem = (ShoppingOrderItem)obj;

		long primaryKey = shoppingOrderItem.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		ShoppingOrderItemModelImpl shoppingOrderItemModelImpl = this;

		shoppingOrderItemModelImpl._originalOrderId =
			shoppingOrderItemModelImpl._orderId;

		shoppingOrderItemModelImpl._setOriginalOrderId = false;

		shoppingOrderItemModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ShoppingOrderItem> toCacheModel() {
		ShoppingOrderItemCacheModel shoppingOrderItemCacheModel =
			new ShoppingOrderItemCacheModel();

		shoppingOrderItemCacheModel.orderItemId = getOrderItemId();

		shoppingOrderItemCacheModel.companyId = getCompanyId();

		shoppingOrderItemCacheModel.orderId = getOrderId();

		shoppingOrderItemCacheModel.itemId = getItemId();

		String itemId = shoppingOrderItemCacheModel.itemId;

		if ((itemId != null) && (itemId.length() == 0)) {
			shoppingOrderItemCacheModel.itemId = null;
		}

		shoppingOrderItemCacheModel.sku = getSku();

		String sku = shoppingOrderItemCacheModel.sku;

		if ((sku != null) && (sku.length() == 0)) {
			shoppingOrderItemCacheModel.sku = null;
		}

		shoppingOrderItemCacheModel.name = getName();

		String name = shoppingOrderItemCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			shoppingOrderItemCacheModel.name = null;
		}

		shoppingOrderItemCacheModel.description = getDescription();

		String description = shoppingOrderItemCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			shoppingOrderItemCacheModel.description = null;
		}

		shoppingOrderItemCacheModel.properties = getProperties();

		String properties = shoppingOrderItemCacheModel.properties;

		if ((properties != null) && (properties.length() == 0)) {
			shoppingOrderItemCacheModel.properties = null;
		}

		shoppingOrderItemCacheModel.price = getPrice();

		shoppingOrderItemCacheModel.quantity = getQuantity();

		Date shippedDate = getShippedDate();

		if (shippedDate != null) {
			shoppingOrderItemCacheModel.shippedDate = shippedDate.getTime();
		}
		else {
			shoppingOrderItemCacheModel.shippedDate = Long.MIN_VALUE;
		}

		return shoppingOrderItemCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ShoppingOrderItem, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ShoppingOrderItem, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ShoppingOrderItem, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ShoppingOrderItem)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<ShoppingOrderItem, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ShoppingOrderItem, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ShoppingOrderItem, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ShoppingOrderItem)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader =
		ShoppingOrderItem.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
		ShoppingOrderItem.class, ModelWrapper.class
	};

	private long _orderItemId;
	private long _companyId;
	private long _orderId;
	private long _originalOrderId;
	private boolean _setOriginalOrderId;
	private String _itemId;
	private String _sku;
	private String _name;
	private String _description;
	private String _properties;
	private double _price;
	private int _quantity;
	private Date _shippedDate;
	private long _columnBitmask;
	private ShoppingOrderItem _escapedModel;

}