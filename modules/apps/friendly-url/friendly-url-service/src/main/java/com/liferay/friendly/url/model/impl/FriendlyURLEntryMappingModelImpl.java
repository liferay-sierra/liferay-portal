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

package com.liferay.friendly.url.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.friendly.url.model.FriendlyURLEntryMapping;
import com.liferay.friendly.url.model.FriendlyURLEntryMappingModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the FriendlyURLEntryMapping service. Represents a row in the &quot;FriendlyURLEntryMapping&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>FriendlyURLEntryMappingModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FriendlyURLEntryMappingImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FriendlyURLEntryMappingImpl
 * @generated
 */
public class FriendlyURLEntryMappingModelImpl
	extends BaseModelImpl<FriendlyURLEntryMapping>
	implements FriendlyURLEntryMappingModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a friendly url entry mapping model instance should use the <code>FriendlyURLEntryMapping</code> interface instead.
	 */
	public static final String TABLE_NAME = "FriendlyURLEntryMapping";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"friendlyURLEntryMappingId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"friendlyURLEntryId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("friendlyURLEntryMappingId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("friendlyURLEntryId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table FriendlyURLEntryMapping (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,friendlyURLEntryMappingId LONG not null,companyId LONG,classNameId LONG,classPK LONG,friendlyURLEntryId LONG,primary key (friendlyURLEntryMappingId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table FriendlyURLEntryMapping";

	public static final String ORDER_BY_JPQL =
		" ORDER BY friendlyURLEntryMapping.friendlyURLEntryMappingId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY FriendlyURLEntryMapping.friendlyURLEntryMappingId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FRIENDLYURLENTRYMAPPINGID_COLUMN_BITMASK = 4L;

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

	public FriendlyURLEntryMappingModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _friendlyURLEntryMappingId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFriendlyURLEntryMappingId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _friendlyURLEntryMappingId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return FriendlyURLEntryMapping.class;
	}

	@Override
	public String getModelClassName() {
		return FriendlyURLEntryMapping.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<FriendlyURLEntryMapping, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<FriendlyURLEntryMapping, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FriendlyURLEntryMapping, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((FriendlyURLEntryMapping)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<FriendlyURLEntryMapping, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<FriendlyURLEntryMapping, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(FriendlyURLEntryMapping)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<FriendlyURLEntryMapping, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<FriendlyURLEntryMapping, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<FriendlyURLEntryMapping, Object>>
		_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<FriendlyURLEntryMapping, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<FriendlyURLEntryMapping, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<FriendlyURLEntryMapping, Object>>();
		Map<String, BiConsumer<FriendlyURLEntryMapping, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<FriendlyURLEntryMapping, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", FriendlyURLEntryMapping::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<FriendlyURLEntryMapping, Long>)
				FriendlyURLEntryMapping::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", FriendlyURLEntryMapping::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<FriendlyURLEntryMapping, Long>)
				FriendlyURLEntryMapping::setCtCollectionId);
		attributeGetterFunctions.put(
			"friendlyURLEntryMappingId",
			FriendlyURLEntryMapping::getFriendlyURLEntryMappingId);
		attributeSetterBiConsumers.put(
			"friendlyURLEntryMappingId",
			(BiConsumer<FriendlyURLEntryMapping, Long>)
				FriendlyURLEntryMapping::setFriendlyURLEntryMappingId);
		attributeGetterFunctions.put(
			"companyId", FriendlyURLEntryMapping::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<FriendlyURLEntryMapping, Long>)
				FriendlyURLEntryMapping::setCompanyId);
		attributeGetterFunctions.put(
			"classNameId", FriendlyURLEntryMapping::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<FriendlyURLEntryMapping, Long>)
				FriendlyURLEntryMapping::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", FriendlyURLEntryMapping::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<FriendlyURLEntryMapping, Long>)
				FriendlyURLEntryMapping::setClassPK);
		attributeGetterFunctions.put(
			"friendlyURLEntryId",
			FriendlyURLEntryMapping::getFriendlyURLEntryId);
		attributeSetterBiConsumers.put(
			"friendlyURLEntryId",
			(BiConsumer<FriendlyURLEntryMapping, Long>)
				FriendlyURLEntryMapping::setFriendlyURLEntryId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

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

	@Override
	public long getFriendlyURLEntryMappingId() {
		return _friendlyURLEntryMappingId;
	}

	@Override
	public void setFriendlyURLEntryMappingId(long friendlyURLEntryMappingId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_friendlyURLEntryMappingId = friendlyURLEntryMappingId;
	}

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

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classNameId = classNameId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassNameId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("classNameId"));
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classPK = classPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassPK() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("classPK"));
	}

	@Override
	public long getFriendlyURLEntryId() {
		return _friendlyURLEntryId;
	}

	@Override
	public void setFriendlyURLEntryId(long friendlyURLEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_friendlyURLEntryId = friendlyURLEntryId;
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
			getCompanyId(), FriendlyURLEntryMapping.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public FriendlyURLEntryMapping toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, FriendlyURLEntryMapping>
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
		FriendlyURLEntryMappingImpl friendlyURLEntryMappingImpl =
			new FriendlyURLEntryMappingImpl();

		friendlyURLEntryMappingImpl.setMvccVersion(getMvccVersion());
		friendlyURLEntryMappingImpl.setCtCollectionId(getCtCollectionId());
		friendlyURLEntryMappingImpl.setFriendlyURLEntryMappingId(
			getFriendlyURLEntryMappingId());
		friendlyURLEntryMappingImpl.setCompanyId(getCompanyId());
		friendlyURLEntryMappingImpl.setClassNameId(getClassNameId());
		friendlyURLEntryMappingImpl.setClassPK(getClassPK());
		friendlyURLEntryMappingImpl.setFriendlyURLEntryId(
			getFriendlyURLEntryId());

		friendlyURLEntryMappingImpl.resetOriginalValues();

		return friendlyURLEntryMappingImpl;
	}

	@Override
	public FriendlyURLEntryMapping cloneWithOriginalValues() {
		FriendlyURLEntryMappingImpl friendlyURLEntryMappingImpl =
			new FriendlyURLEntryMappingImpl();

		friendlyURLEntryMappingImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		friendlyURLEntryMappingImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		friendlyURLEntryMappingImpl.setFriendlyURLEntryMappingId(
			this.<Long>getColumnOriginalValue("friendlyURLEntryMappingId"));
		friendlyURLEntryMappingImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		friendlyURLEntryMappingImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		friendlyURLEntryMappingImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));
		friendlyURLEntryMappingImpl.setFriendlyURLEntryId(
			this.<Long>getColumnOriginalValue("friendlyURLEntryId"));

		return friendlyURLEntryMappingImpl;
	}

	@Override
	public int compareTo(FriendlyURLEntryMapping friendlyURLEntryMapping) {
		long primaryKey = friendlyURLEntryMapping.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FriendlyURLEntryMapping)) {
			return false;
		}

		FriendlyURLEntryMapping friendlyURLEntryMapping =
			(FriendlyURLEntryMapping)object;

		long primaryKey = friendlyURLEntryMapping.getPrimaryKey();

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

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<FriendlyURLEntryMapping> toCacheModel() {
		FriendlyURLEntryMappingCacheModel friendlyURLEntryMappingCacheModel =
			new FriendlyURLEntryMappingCacheModel();

		friendlyURLEntryMappingCacheModel.mvccVersion = getMvccVersion();

		friendlyURLEntryMappingCacheModel.ctCollectionId = getCtCollectionId();

		friendlyURLEntryMappingCacheModel.friendlyURLEntryMappingId =
			getFriendlyURLEntryMappingId();

		friendlyURLEntryMappingCacheModel.companyId = getCompanyId();

		friendlyURLEntryMappingCacheModel.classNameId = getClassNameId();

		friendlyURLEntryMappingCacheModel.classPK = getClassPK();

		friendlyURLEntryMappingCacheModel.friendlyURLEntryId =
			getFriendlyURLEntryId();

		return friendlyURLEntryMappingCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<FriendlyURLEntryMapping, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<FriendlyURLEntryMapping, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FriendlyURLEntryMapping, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(FriendlyURLEntryMapping)this);

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
			<InvocationHandler, FriendlyURLEntryMapping>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						FriendlyURLEntryMapping.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _friendlyURLEntryMappingId;
	private long _companyId;
	private long _classNameId;
	private long _classPK;
	private long _friendlyURLEntryId;

	public <T> T getColumnValue(String columnName) {
		Function<FriendlyURLEntryMapping, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((FriendlyURLEntryMapping)this);
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
			"friendlyURLEntryMappingId", _friendlyURLEntryMappingId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("friendlyURLEntryId", _friendlyURLEntryId);
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

		columnBitmasks.put("friendlyURLEntryMappingId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("classNameId", 16L);

		columnBitmasks.put("classPK", 32L);

		columnBitmasks.put("friendlyURLEntryId", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private FriendlyURLEntryMapping _escapedModel;

}