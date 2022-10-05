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

package com.liferay.commerce.pricing.model.impl;

import com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel;
import com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRelModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommercePricingClassCPDefinitionRel service. Represents a row in the &quot;CPricingClassCPDefinitionRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommercePricingClassCPDefinitionRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommercePricingClassCPDefinitionRelImpl}.
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommercePricingClassCPDefinitionRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommercePricingClassCPDefinitionRelModelImpl
	extends BaseModelImpl<CommercePricingClassCPDefinitionRel>
	implements CommercePricingClassCPDefinitionRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce pricing class cp definition rel model instance should use the <code>CommercePricingClassCPDefinitionRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CPricingClassCPDefinitionRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"CPricingClassCPDefinitionRelId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"commercePricingClassId", Types.BIGINT},
		{"CPDefinitionId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CPricingClassCPDefinitionRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commercePricingClassId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CPDefinitionId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CPricingClassCPDefinitionRel (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,CPricingClassCPDefinitionRelId LONG not null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commercePricingClassId LONG,CPDefinitionId LONG,primary key (CPricingClassCPDefinitionRelId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table CPricingClassCPDefinitionRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commercePricingClassCPDefinitionRel.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CPricingClassCPDefinitionRel.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CPDEFINITIONID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEPRICINGCLASSID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public CommercePricingClassCPDefinitionRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CommercePricingClassCPDefinitionRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommercePricingClassCPDefinitionRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CommercePricingClassCPDefinitionRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommercePricingClassCPDefinitionRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommercePricingClassCPDefinitionRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommercePricingClassCPDefinitionRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry
				<String, Function<CommercePricingClassCPDefinitionRel, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePricingClassCPDefinitionRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommercePricingClassCPDefinitionRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommercePricingClassCPDefinitionRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommercePricingClassCPDefinitionRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommercePricingClassCPDefinitionRel)this,
					entry.getValue());
			}
		}
	}

	public Map<String, Function<CommercePricingClassCPDefinitionRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommercePricingClassCPDefinitionRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map
		<String, Function<CommercePricingClassCPDefinitionRel, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommercePricingClassCPDefinitionRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommercePricingClassCPDefinitionRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String,
					 Function<CommercePricingClassCPDefinitionRel, Object>>();
		Map<String, BiConsumer<CommercePricingClassCPDefinitionRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String,
					 BiConsumer<CommercePricingClassCPDefinitionRel, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", CommercePricingClassCPDefinitionRel::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<CommercePricingClassCPDefinitionRel, Long>)
				CommercePricingClassCPDefinitionRel::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId",
			CommercePricingClassCPDefinitionRel::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<CommercePricingClassCPDefinitionRel, Long>)
				CommercePricingClassCPDefinitionRel::setCtCollectionId);
		attributeGetterFunctions.put(
			"CommercePricingClassCPDefinitionRelId",
			CommercePricingClassCPDefinitionRel::
				getCommercePricingClassCPDefinitionRelId);
		attributeSetterBiConsumers.put(
			"CommercePricingClassCPDefinitionRelId",
			(BiConsumer<CommercePricingClassCPDefinitionRel, Long>)
				CommercePricingClassCPDefinitionRel::
					setCommercePricingClassCPDefinitionRelId);
		attributeGetterFunctions.put(
			"companyId", CommercePricingClassCPDefinitionRel::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommercePricingClassCPDefinitionRel, Long>)
				CommercePricingClassCPDefinitionRel::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommercePricingClassCPDefinitionRel::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommercePricingClassCPDefinitionRel, Long>)
				CommercePricingClassCPDefinitionRel::setUserId);
		attributeGetterFunctions.put(
			"userName", CommercePricingClassCPDefinitionRel::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommercePricingClassCPDefinitionRel, String>)
				CommercePricingClassCPDefinitionRel::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommercePricingClassCPDefinitionRel::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommercePricingClassCPDefinitionRel, Date>)
				CommercePricingClassCPDefinitionRel::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate",
			CommercePricingClassCPDefinitionRel::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommercePricingClassCPDefinitionRel, Date>)
				CommercePricingClassCPDefinitionRel::setModifiedDate);
		attributeGetterFunctions.put(
			"commercePricingClassId",
			CommercePricingClassCPDefinitionRel::getCommercePricingClassId);
		attributeSetterBiConsumers.put(
			"commercePricingClassId",
			(BiConsumer<CommercePricingClassCPDefinitionRel, Long>)
				CommercePricingClassCPDefinitionRel::setCommercePricingClassId);
		attributeGetterFunctions.put(
			"CPDefinitionId",
			CommercePricingClassCPDefinitionRel::getCPDefinitionId);
		attributeSetterBiConsumers.put(
			"CPDefinitionId",
			(BiConsumer<CommercePricingClassCPDefinitionRel, Long>)
				CommercePricingClassCPDefinitionRel::setCPDefinitionId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ctCollectionId = ctCollectionId;
	}

	@JSON
	@Override
	public long getCommercePricingClassCPDefinitionRelId() {
		return _CommercePricingClassCPDefinitionRelId;
	}

	@Override
	public void setCommercePricingClassCPDefinitionRelId(
		long CommercePricingClassCPDefinitionRelId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CommercePricingClassCPDefinitionRelId =
			CommercePricingClassCPDefinitionRelId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getCommercePricingClassId() {
		return _commercePricingClassId;
	}

	@Override
	public void setCommercePricingClassId(long commercePricingClassId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commercePricingClassId = commercePricingClassId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommercePricingClassId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commercePricingClassId"));
	}

	@JSON
	@Override
	public long getCPDefinitionId() {
		return _CPDefinitionId;
	}

	@Override
	public void setCPDefinitionId(long CPDefinitionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CPDefinitionId = CPDefinitionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCPDefinitionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("CPDefinitionId"));
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommercePricingClassCPDefinitionRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommercePricingClassCPDefinitionRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommercePricingClassCPDefinitionRel>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommercePricingClassCPDefinitionRelImpl
			commercePricingClassCPDefinitionRelImpl =
				new CommercePricingClassCPDefinitionRelImpl();

		commercePricingClassCPDefinitionRelImpl.setMvccVersion(
			getMvccVersion());
		commercePricingClassCPDefinitionRelImpl.setCtCollectionId(
			getCtCollectionId());
		commercePricingClassCPDefinitionRelImpl.
			setCommercePricingClassCPDefinitionRelId(
				getCommercePricingClassCPDefinitionRelId());
		commercePricingClassCPDefinitionRelImpl.setCompanyId(getCompanyId());
		commercePricingClassCPDefinitionRelImpl.setUserId(getUserId());
		commercePricingClassCPDefinitionRelImpl.setUserName(getUserName());
		commercePricingClassCPDefinitionRelImpl.setCreateDate(getCreateDate());
		commercePricingClassCPDefinitionRelImpl.setModifiedDate(
			getModifiedDate());
		commercePricingClassCPDefinitionRelImpl.setCommercePricingClassId(
			getCommercePricingClassId());
		commercePricingClassCPDefinitionRelImpl.setCPDefinitionId(
			getCPDefinitionId());

		commercePricingClassCPDefinitionRelImpl.resetOriginalValues();

		return commercePricingClassCPDefinitionRelImpl;
	}

	@Override
	public CommercePricingClassCPDefinitionRel cloneWithOriginalValues() {
		CommercePricingClassCPDefinitionRelImpl
			commercePricingClassCPDefinitionRelImpl =
				new CommercePricingClassCPDefinitionRelImpl();

		commercePricingClassCPDefinitionRelImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commercePricingClassCPDefinitionRelImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		commercePricingClassCPDefinitionRelImpl.
			setCommercePricingClassCPDefinitionRelId(
				this.<Long>getColumnOriginalValue(
					"CPricingClassCPDefinitionRelId"));
		commercePricingClassCPDefinitionRelImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commercePricingClassCPDefinitionRelImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commercePricingClassCPDefinitionRelImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commercePricingClassCPDefinitionRelImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commercePricingClassCPDefinitionRelImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commercePricingClassCPDefinitionRelImpl.setCommercePricingClassId(
			this.<Long>getColumnOriginalValue("commercePricingClassId"));
		commercePricingClassCPDefinitionRelImpl.setCPDefinitionId(
			this.<Long>getColumnOriginalValue("CPDefinitionId"));

		return commercePricingClassCPDefinitionRelImpl;
	}

	@Override
	public int compareTo(
		CommercePricingClassCPDefinitionRel
			commercePricingClassCPDefinitionRel) {

		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(),
			commercePricingClassCPDefinitionRel.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommercePricingClassCPDefinitionRel)) {
			return false;
		}

		CommercePricingClassCPDefinitionRel
			commercePricingClassCPDefinitionRel =
				(CommercePricingClassCPDefinitionRel)object;

		long primaryKey = commercePricingClassCPDefinitionRel.getPrimaryKey();

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

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CommercePricingClassCPDefinitionRel> toCacheModel() {
		CommercePricingClassCPDefinitionRelCacheModel
			commercePricingClassCPDefinitionRelCacheModel =
				new CommercePricingClassCPDefinitionRelCacheModel();

		commercePricingClassCPDefinitionRelCacheModel.mvccVersion =
			getMvccVersion();

		commercePricingClassCPDefinitionRelCacheModel.ctCollectionId =
			getCtCollectionId();

		commercePricingClassCPDefinitionRelCacheModel.
			CommercePricingClassCPDefinitionRelId =
				getCommercePricingClassCPDefinitionRelId();

		commercePricingClassCPDefinitionRelCacheModel.companyId =
			getCompanyId();

		commercePricingClassCPDefinitionRelCacheModel.userId = getUserId();

		commercePricingClassCPDefinitionRelCacheModel.userName = getUserName();

		String userName =
			commercePricingClassCPDefinitionRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commercePricingClassCPDefinitionRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commercePricingClassCPDefinitionRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commercePricingClassCPDefinitionRelCacheModel.createDate =
				Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commercePricingClassCPDefinitionRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commercePricingClassCPDefinitionRelCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commercePricingClassCPDefinitionRelCacheModel.commercePricingClassId =
			getCommercePricingClassId();

		commercePricingClassCPDefinitionRelCacheModel.CPDefinitionId =
			getCPDefinitionId();

		return commercePricingClassCPDefinitionRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommercePricingClassCPDefinitionRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry
				<String, Function<CommercePricingClassCPDefinitionRel, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePricingClassCPDefinitionRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommercePricingClassCPDefinitionRel)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommercePricingClassCPDefinitionRel>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						CommercePricingClassCPDefinitionRel.class,
						ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _CommercePricingClassCPDefinitionRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commercePricingClassId;
	private long _CPDefinitionId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommercePricingClassCPDefinitionRel, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommercePricingClassCPDefinitionRel)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put("ctCollectionId", _ctCollectionId);
		_columnOriginalValues.put(
			"CPricingClassCPDefinitionRelId",
			_CommercePricingClassCPDefinitionRelId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"commercePricingClassId", _commercePricingClassId);
		_columnOriginalValues.put("CPDefinitionId", _CPDefinitionId);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put(
			"CPricingClassCPDefinitionRelId",
			"CommercePricingClassCPDefinitionRelId");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("CPricingClassCPDefinitionRelId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("commercePricingClassId", 256L);

		columnBitmasks.put("CPDefinitionId", 512L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommercePricingClassCPDefinitionRel _escapedModel;

}