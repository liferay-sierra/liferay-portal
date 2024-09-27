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

package com.liferay.commerce.shop.by.diagram.model.impl;

import com.liferay.commerce.shop.by.diagram.model.CSDiagramPin;
import com.liferay.commerce.shop.by.diagram.model.CSDiagramPinModel;
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
 * The base model implementation for the CSDiagramPin service. Represents a row in the &quot;CSDiagramPin&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CSDiagramPinModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CSDiagramPinImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CSDiagramPinImpl
 * @generated
 */
@JSON(strict = true)
public class CSDiagramPinModelImpl
	extends BaseModelImpl<CSDiagramPin> implements CSDiagramPinModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cs diagram pin model instance should use the <code>CSDiagramPin</code> interface instead.
	 */
	public static final String TABLE_NAME = "CSDiagramPin";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"CSDiagramPinId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"CPDefinitionId", Types.BIGINT}, {"positionX", Types.DOUBLE},
		{"positionY", Types.DOUBLE}, {"sequence", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CSDiagramPinId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CPDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("positionX", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("positionY", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("sequence", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CSDiagramPin (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,CSDiagramPinId LONG not null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CPDefinitionId LONG,positionX DOUBLE,positionY DOUBLE,sequence VARCHAR(75) null,primary key (CSDiagramPinId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table CSDiagramPin";

	public static final String ORDER_BY_JPQL =
		" ORDER BY csDiagramPin.sequence ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CSDiagramPin.sequence ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CPDEFINITIONID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SEQUENCE_COLUMN_BITMASK = 2L;

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

	public CSDiagramPinModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CSDiagramPinId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCSDiagramPinId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CSDiagramPinId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CSDiagramPin.class;
	}

	@Override
	public String getModelClassName() {
		return CSDiagramPin.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CSDiagramPin, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CSDiagramPin, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CSDiagramPin, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CSDiagramPin)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CSDiagramPin, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CSDiagramPin, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CSDiagramPin)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CSDiagramPin, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CSDiagramPin, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<CSDiagramPin, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CSDiagramPin, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CSDiagramPin, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<CSDiagramPin, Object>>();
		Map<String, BiConsumer<CSDiagramPin, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<CSDiagramPin, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", CSDiagramPin::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<CSDiagramPin, Long>)CSDiagramPin::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", CSDiagramPin::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<CSDiagramPin, Long>)CSDiagramPin::setCtCollectionId);
		attributeGetterFunctions.put(
			"CSDiagramPinId", CSDiagramPin::getCSDiagramPinId);
		attributeSetterBiConsumers.put(
			"CSDiagramPinId",
			(BiConsumer<CSDiagramPin, Long>)CSDiagramPin::setCSDiagramPinId);
		attributeGetterFunctions.put("companyId", CSDiagramPin::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CSDiagramPin, Long>)CSDiagramPin::setCompanyId);
		attributeGetterFunctions.put("userId", CSDiagramPin::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<CSDiagramPin, Long>)CSDiagramPin::setUserId);
		attributeGetterFunctions.put("userName", CSDiagramPin::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CSDiagramPin, String>)CSDiagramPin::setUserName);
		attributeGetterFunctions.put("createDate", CSDiagramPin::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CSDiagramPin, Date>)CSDiagramPin::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CSDiagramPin::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CSDiagramPin, Date>)CSDiagramPin::setModifiedDate);
		attributeGetterFunctions.put(
			"CPDefinitionId", CSDiagramPin::getCPDefinitionId);
		attributeSetterBiConsumers.put(
			"CPDefinitionId",
			(BiConsumer<CSDiagramPin, Long>)CSDiagramPin::setCPDefinitionId);
		attributeGetterFunctions.put("positionX", CSDiagramPin::getPositionX);
		attributeSetterBiConsumers.put(
			"positionX",
			(BiConsumer<CSDiagramPin, Double>)CSDiagramPin::setPositionX);
		attributeGetterFunctions.put("positionY", CSDiagramPin::getPositionY);
		attributeSetterBiConsumers.put(
			"positionY",
			(BiConsumer<CSDiagramPin, Double>)CSDiagramPin::setPositionY);
		attributeGetterFunctions.put("sequence", CSDiagramPin::getSequence);
		attributeSetterBiConsumers.put(
			"sequence",
			(BiConsumer<CSDiagramPin, String>)CSDiagramPin::setSequence);

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
	public long getCSDiagramPinId() {
		return _CSDiagramPinId;
	}

	@Override
	public void setCSDiagramPinId(long CSDiagramPinId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CSDiagramPinId = CSDiagramPinId;
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

	@JSON
	@Override
	public double getPositionX() {
		return _positionX;
	}

	@Override
	public void setPositionX(double positionX) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_positionX = positionX;
	}

	@JSON
	@Override
	public double getPositionY() {
		return _positionY;
	}

	@Override
	public void setPositionY(double positionY) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_positionY = positionY;
	}

	@JSON
	@Override
	public String getSequence() {
		if (_sequence == null) {
			return "";
		}
		else {
			return _sequence;
		}
	}

	@Override
	public void setSequence(String sequence) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sequence = sequence;
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
			getCompanyId(), CSDiagramPin.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CSDiagramPin toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CSDiagramPin>
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
		CSDiagramPinImpl csDiagramPinImpl = new CSDiagramPinImpl();

		csDiagramPinImpl.setMvccVersion(getMvccVersion());
		csDiagramPinImpl.setCtCollectionId(getCtCollectionId());
		csDiagramPinImpl.setCSDiagramPinId(getCSDiagramPinId());
		csDiagramPinImpl.setCompanyId(getCompanyId());
		csDiagramPinImpl.setUserId(getUserId());
		csDiagramPinImpl.setUserName(getUserName());
		csDiagramPinImpl.setCreateDate(getCreateDate());
		csDiagramPinImpl.setModifiedDate(getModifiedDate());
		csDiagramPinImpl.setCPDefinitionId(getCPDefinitionId());
		csDiagramPinImpl.setPositionX(getPositionX());
		csDiagramPinImpl.setPositionY(getPositionY());
		csDiagramPinImpl.setSequence(getSequence());

		csDiagramPinImpl.resetOriginalValues();

		return csDiagramPinImpl;
	}

	@Override
	public CSDiagramPin cloneWithOriginalValues() {
		CSDiagramPinImpl csDiagramPinImpl = new CSDiagramPinImpl();

		csDiagramPinImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		csDiagramPinImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		csDiagramPinImpl.setCSDiagramPinId(
			this.<Long>getColumnOriginalValue("CSDiagramPinId"));
		csDiagramPinImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		csDiagramPinImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		csDiagramPinImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		csDiagramPinImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		csDiagramPinImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		csDiagramPinImpl.setCPDefinitionId(
			this.<Long>getColumnOriginalValue("CPDefinitionId"));
		csDiagramPinImpl.setPositionX(
			this.<Double>getColumnOriginalValue("positionX"));
		csDiagramPinImpl.setPositionY(
			this.<Double>getColumnOriginalValue("positionY"));
		csDiagramPinImpl.setSequence(
			this.<String>getColumnOriginalValue("sequence"));

		return csDiagramPinImpl;
	}

	@Override
	public int compareTo(CSDiagramPin csDiagramPin) {
		int value = 0;

		value = getSequence().compareTo(csDiagramPin.getSequence());

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

		if (!(object instanceof CSDiagramPin)) {
			return false;
		}

		CSDiagramPin csDiagramPin = (CSDiagramPin)object;

		long primaryKey = csDiagramPin.getPrimaryKey();

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
	public CacheModel<CSDiagramPin> toCacheModel() {
		CSDiagramPinCacheModel csDiagramPinCacheModel =
			new CSDiagramPinCacheModel();

		csDiagramPinCacheModel.mvccVersion = getMvccVersion();

		csDiagramPinCacheModel.ctCollectionId = getCtCollectionId();

		csDiagramPinCacheModel.CSDiagramPinId = getCSDiagramPinId();

		csDiagramPinCacheModel.companyId = getCompanyId();

		csDiagramPinCacheModel.userId = getUserId();

		csDiagramPinCacheModel.userName = getUserName();

		String userName = csDiagramPinCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			csDiagramPinCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			csDiagramPinCacheModel.createDate = createDate.getTime();
		}
		else {
			csDiagramPinCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			csDiagramPinCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			csDiagramPinCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		csDiagramPinCacheModel.CPDefinitionId = getCPDefinitionId();

		csDiagramPinCacheModel.positionX = getPositionX();

		csDiagramPinCacheModel.positionY = getPositionY();

		csDiagramPinCacheModel.sequence = getSequence();

		String sequence = csDiagramPinCacheModel.sequence;

		if ((sequence != null) && (sequence.length() == 0)) {
			csDiagramPinCacheModel.sequence = null;
		}

		return csDiagramPinCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CSDiagramPin, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CSDiagramPin, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CSDiagramPin, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((CSDiagramPin)this);

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

		private static final Function<InvocationHandler, CSDiagramPin>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CSDiagramPin.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _CSDiagramPinId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _CPDefinitionId;
	private double _positionX;
	private double _positionY;
	private String _sequence;

	public <T> T getColumnValue(String columnName) {
		Function<CSDiagramPin, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CSDiagramPin)this);
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
		_columnOriginalValues.put("CSDiagramPinId", _CSDiagramPinId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("CPDefinitionId", _CPDefinitionId);
		_columnOriginalValues.put("positionX", _positionX);
		_columnOriginalValues.put("positionY", _positionY);
		_columnOriginalValues.put("sequence", _sequence);
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

		columnBitmasks.put("CSDiagramPinId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("CPDefinitionId", 256L);

		columnBitmasks.put("positionX", 512L);

		columnBitmasks.put("positionY", 1024L);

		columnBitmasks.put("sequence", 2048L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CSDiagramPin _escapedModel;

}