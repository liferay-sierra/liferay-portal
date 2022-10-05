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

package com.liferay.asset.list.model.impl;

import com.liferay.asset.list.model.AssetListEntrySegmentsEntryRel;
import com.liferay.asset.list.model.AssetListEntrySegmentsEntryRelModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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
 * The base model implementation for the AssetListEntrySegmentsEntryRel service. Represents a row in the &quot;AssetListEntrySegmentsEntryRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AssetListEntrySegmentsEntryRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AssetListEntrySegmentsEntryRelImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListEntrySegmentsEntryRelImpl
 * @generated
 */
public class AssetListEntrySegmentsEntryRelModelImpl
	extends BaseModelImpl<AssetListEntrySegmentsEntryRel>
	implements AssetListEntrySegmentsEntryRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a asset list entry segments entry rel model instance should use the <code>AssetListEntrySegmentsEntryRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "AssetListEntrySegmentsEntryRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"alEntrySegmentsEntryRelId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"assetListEntryId", Types.BIGINT}, {"priority", Types.INTEGER},
		{"segmentsEntryId", Types.BIGINT}, {"typeSettings", Types.CLOB},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("alEntrySegmentsEntryRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("assetListEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("priority", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("segmentsEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("typeSettings", Types.CLOB);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AssetListEntrySegmentsEntryRel (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,alEntrySegmentsEntryRelId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,assetListEntryId LONG,priority INTEGER,segmentsEntryId LONG,typeSettings TEXT null,lastPublishDate DATE null,primary key (alEntrySegmentsEntryRelId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table AssetListEntrySegmentsEntryRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY assetListEntrySegmentsEntryRel.assetListEntrySegmentsEntryRelId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY AssetListEntrySegmentsEntryRel.alEntrySegmentsEntryRelId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ASSETLISTENTRYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SEGMENTSENTRYID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ASSETLISTENTRYSEGMENTSENTRYRELID_COLUMN_BITMASK =
		32L;

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

	public AssetListEntrySegmentsEntryRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _assetListEntrySegmentsEntryRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAssetListEntrySegmentsEntryRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetListEntrySegmentsEntryRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AssetListEntrySegmentsEntryRel.class;
	}

	@Override
	public String getModelClassName() {
		return AssetListEntrySegmentsEntryRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AssetListEntrySegmentsEntryRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AssetListEntrySegmentsEntryRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetListEntrySegmentsEntryRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(AssetListEntrySegmentsEntryRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AssetListEntrySegmentsEntryRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AssetListEntrySegmentsEntryRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AssetListEntrySegmentsEntryRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AssetListEntrySegmentsEntryRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AssetListEntrySegmentsEntryRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map
		<String, Function<AssetListEntrySegmentsEntryRel, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<AssetListEntrySegmentsEntryRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<AssetListEntrySegmentsEntryRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String,
					 Function<AssetListEntrySegmentsEntryRel, Object>>();
		Map<String, BiConsumer<AssetListEntrySegmentsEntryRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<AssetListEntrySegmentsEntryRel, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", AssetListEntrySegmentsEntryRel::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<AssetListEntrySegmentsEntryRel, Long>)
				AssetListEntrySegmentsEntryRel::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId",
			AssetListEntrySegmentsEntryRel::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<AssetListEntrySegmentsEntryRel, Long>)
				AssetListEntrySegmentsEntryRel::setCtCollectionId);
		attributeGetterFunctions.put(
			"uuid", AssetListEntrySegmentsEntryRel::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<AssetListEntrySegmentsEntryRel, String>)
				AssetListEntrySegmentsEntryRel::setUuid);
		attributeGetterFunctions.put(
			"assetListEntrySegmentsEntryRelId",
			AssetListEntrySegmentsEntryRel::
				getAssetListEntrySegmentsEntryRelId);
		attributeSetterBiConsumers.put(
			"assetListEntrySegmentsEntryRelId",
			(BiConsumer<AssetListEntrySegmentsEntryRel, Long>)
				AssetListEntrySegmentsEntryRel::
					setAssetListEntrySegmentsEntryRelId);
		attributeGetterFunctions.put(
			"groupId", AssetListEntrySegmentsEntryRel::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<AssetListEntrySegmentsEntryRel, Long>)
				AssetListEntrySegmentsEntryRel::setGroupId);
		attributeGetterFunctions.put(
			"companyId", AssetListEntrySegmentsEntryRel::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<AssetListEntrySegmentsEntryRel, Long>)
				AssetListEntrySegmentsEntryRel::setCompanyId);
		attributeGetterFunctions.put(
			"userId", AssetListEntrySegmentsEntryRel::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<AssetListEntrySegmentsEntryRel, Long>)
				AssetListEntrySegmentsEntryRel::setUserId);
		attributeGetterFunctions.put(
			"userName", AssetListEntrySegmentsEntryRel::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<AssetListEntrySegmentsEntryRel, String>)
				AssetListEntrySegmentsEntryRel::setUserName);
		attributeGetterFunctions.put(
			"createDate", AssetListEntrySegmentsEntryRel::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<AssetListEntrySegmentsEntryRel, Date>)
				AssetListEntrySegmentsEntryRel::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", AssetListEntrySegmentsEntryRel::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<AssetListEntrySegmentsEntryRel, Date>)
				AssetListEntrySegmentsEntryRel::setModifiedDate);
		attributeGetterFunctions.put(
			"assetListEntryId",
			AssetListEntrySegmentsEntryRel::getAssetListEntryId);
		attributeSetterBiConsumers.put(
			"assetListEntryId",
			(BiConsumer<AssetListEntrySegmentsEntryRel, Long>)
				AssetListEntrySegmentsEntryRel::setAssetListEntryId);
		attributeGetterFunctions.put(
			"priority", AssetListEntrySegmentsEntryRel::getPriority);
		attributeSetterBiConsumers.put(
			"priority",
			(BiConsumer<AssetListEntrySegmentsEntryRel, Integer>)
				AssetListEntrySegmentsEntryRel::setPriority);
		attributeGetterFunctions.put(
			"segmentsEntryId",
			AssetListEntrySegmentsEntryRel::getSegmentsEntryId);
		attributeSetterBiConsumers.put(
			"segmentsEntryId",
			(BiConsumer<AssetListEntrySegmentsEntryRel, Long>)
				AssetListEntrySegmentsEntryRel::setSegmentsEntryId);
		attributeGetterFunctions.put(
			"typeSettings", AssetListEntrySegmentsEntryRel::getTypeSettings);
		attributeSetterBiConsumers.put(
			"typeSettings",
			(BiConsumer<AssetListEntrySegmentsEntryRel, String>)
				AssetListEntrySegmentsEntryRel::setTypeSettings);
		attributeGetterFunctions.put(
			"lastPublishDate",
			AssetListEntrySegmentsEntryRel::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<AssetListEntrySegmentsEntryRel, Date>)
				AssetListEntrySegmentsEntryRel::setLastPublishDate);

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
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@Override
	public long getAssetListEntrySegmentsEntryRelId() {
		return _assetListEntrySegmentsEntryRelId;
	}

	@Override
	public void setAssetListEntrySegmentsEntryRelId(
		long assetListEntrySegmentsEntryRelId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_assetListEntrySegmentsEntryRelId = assetListEntrySegmentsEntryRelId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_groupId = groupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("groupId"));
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

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

	@Override
	public long getAssetListEntryId() {
		return _assetListEntryId;
	}

	@Override
	public void setAssetListEntryId(long assetListEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_assetListEntryId = assetListEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalAssetListEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("assetListEntryId"));
	}

	@Override
	public int getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(int priority) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_priority = priority;
	}

	@Override
	public long getSegmentsEntryId() {
		return _segmentsEntryId;
	}

	@Override
	public void setSegmentsEntryId(long segmentsEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_segmentsEntryId = segmentsEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSegmentsEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("segmentsEntryId"));
	}

	@Override
	public String getTypeSettings() {
		if (_typeSettings == null) {
			return "";
		}
		else {
			return _typeSettings;
		}
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_typeSettings = typeSettings;
	}

	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(
				AssetListEntrySegmentsEntryRel.class.getName()));
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
			getCompanyId(), AssetListEntrySegmentsEntryRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AssetListEntrySegmentsEntryRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AssetListEntrySegmentsEntryRel>
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
		AssetListEntrySegmentsEntryRelImpl assetListEntrySegmentsEntryRelImpl =
			new AssetListEntrySegmentsEntryRelImpl();

		assetListEntrySegmentsEntryRelImpl.setMvccVersion(getMvccVersion());
		assetListEntrySegmentsEntryRelImpl.setCtCollectionId(
			getCtCollectionId());
		assetListEntrySegmentsEntryRelImpl.setUuid(getUuid());
		assetListEntrySegmentsEntryRelImpl.setAssetListEntrySegmentsEntryRelId(
			getAssetListEntrySegmentsEntryRelId());
		assetListEntrySegmentsEntryRelImpl.setGroupId(getGroupId());
		assetListEntrySegmentsEntryRelImpl.setCompanyId(getCompanyId());
		assetListEntrySegmentsEntryRelImpl.setUserId(getUserId());
		assetListEntrySegmentsEntryRelImpl.setUserName(getUserName());
		assetListEntrySegmentsEntryRelImpl.setCreateDate(getCreateDate());
		assetListEntrySegmentsEntryRelImpl.setModifiedDate(getModifiedDate());
		assetListEntrySegmentsEntryRelImpl.setAssetListEntryId(
			getAssetListEntryId());
		assetListEntrySegmentsEntryRelImpl.setPriority(getPriority());
		assetListEntrySegmentsEntryRelImpl.setSegmentsEntryId(
			getSegmentsEntryId());
		assetListEntrySegmentsEntryRelImpl.setTypeSettings(getTypeSettings());
		assetListEntrySegmentsEntryRelImpl.setLastPublishDate(
			getLastPublishDate());

		assetListEntrySegmentsEntryRelImpl.resetOriginalValues();

		return assetListEntrySegmentsEntryRelImpl;
	}

	@Override
	public AssetListEntrySegmentsEntryRel cloneWithOriginalValues() {
		AssetListEntrySegmentsEntryRelImpl assetListEntrySegmentsEntryRelImpl =
			new AssetListEntrySegmentsEntryRelImpl();

		assetListEntrySegmentsEntryRelImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		assetListEntrySegmentsEntryRelImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		assetListEntrySegmentsEntryRelImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		assetListEntrySegmentsEntryRelImpl.setAssetListEntrySegmentsEntryRelId(
			this.<Long>getColumnOriginalValue("alEntrySegmentsEntryRelId"));
		assetListEntrySegmentsEntryRelImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		assetListEntrySegmentsEntryRelImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		assetListEntrySegmentsEntryRelImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		assetListEntrySegmentsEntryRelImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		assetListEntrySegmentsEntryRelImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		assetListEntrySegmentsEntryRelImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		assetListEntrySegmentsEntryRelImpl.setAssetListEntryId(
			this.<Long>getColumnOriginalValue("assetListEntryId"));
		assetListEntrySegmentsEntryRelImpl.setPriority(
			this.<Integer>getColumnOriginalValue("priority"));
		assetListEntrySegmentsEntryRelImpl.setSegmentsEntryId(
			this.<Long>getColumnOriginalValue("segmentsEntryId"));
		assetListEntrySegmentsEntryRelImpl.setTypeSettings(
			this.<String>getColumnOriginalValue("typeSettings"));
		assetListEntrySegmentsEntryRelImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return assetListEntrySegmentsEntryRelImpl;
	}

	@Override
	public int compareTo(
		AssetListEntrySegmentsEntryRel assetListEntrySegmentsEntryRel) {

		long primaryKey = assetListEntrySegmentsEntryRel.getPrimaryKey();

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

		if (!(object instanceof AssetListEntrySegmentsEntryRel)) {
			return false;
		}

		AssetListEntrySegmentsEntryRel assetListEntrySegmentsEntryRel =
			(AssetListEntrySegmentsEntryRel)object;

		long primaryKey = assetListEntrySegmentsEntryRel.getPrimaryKey();

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
	public CacheModel<AssetListEntrySegmentsEntryRel> toCacheModel() {
		AssetListEntrySegmentsEntryRelCacheModel
			assetListEntrySegmentsEntryRelCacheModel =
				new AssetListEntrySegmentsEntryRelCacheModel();

		assetListEntrySegmentsEntryRelCacheModel.mvccVersion = getMvccVersion();

		assetListEntrySegmentsEntryRelCacheModel.ctCollectionId =
			getCtCollectionId();

		assetListEntrySegmentsEntryRelCacheModel.uuid = getUuid();

		String uuid = assetListEntrySegmentsEntryRelCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			assetListEntrySegmentsEntryRelCacheModel.uuid = null;
		}

		assetListEntrySegmentsEntryRelCacheModel.
			assetListEntrySegmentsEntryRelId =
				getAssetListEntrySegmentsEntryRelId();

		assetListEntrySegmentsEntryRelCacheModel.groupId = getGroupId();

		assetListEntrySegmentsEntryRelCacheModel.companyId = getCompanyId();

		assetListEntrySegmentsEntryRelCacheModel.userId = getUserId();

		assetListEntrySegmentsEntryRelCacheModel.userName = getUserName();

		String userName = assetListEntrySegmentsEntryRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			assetListEntrySegmentsEntryRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			assetListEntrySegmentsEntryRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			assetListEntrySegmentsEntryRelCacheModel.createDate =
				Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			assetListEntrySegmentsEntryRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			assetListEntrySegmentsEntryRelCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		assetListEntrySegmentsEntryRelCacheModel.assetListEntryId =
			getAssetListEntryId();

		assetListEntrySegmentsEntryRelCacheModel.priority = getPriority();

		assetListEntrySegmentsEntryRelCacheModel.segmentsEntryId =
			getSegmentsEntryId();

		assetListEntrySegmentsEntryRelCacheModel.typeSettings =
			getTypeSettings();

		String typeSettings =
			assetListEntrySegmentsEntryRelCacheModel.typeSettings;

		if ((typeSettings != null) && (typeSettings.length() == 0)) {
			assetListEntrySegmentsEntryRelCacheModel.typeSettings = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			assetListEntrySegmentsEntryRelCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			assetListEntrySegmentsEntryRelCacheModel.lastPublishDate =
				Long.MIN_VALUE;
		}

		return assetListEntrySegmentsEntryRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AssetListEntrySegmentsEntryRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AssetListEntrySegmentsEntryRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetListEntrySegmentsEntryRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(AssetListEntrySegmentsEntryRel)this);

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
			<InvocationHandler, AssetListEntrySegmentsEntryRel>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						AssetListEntrySegmentsEntryRel.class,
						ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _assetListEntrySegmentsEntryRelId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _assetListEntryId;
	private int _priority;
	private long _segmentsEntryId;
	private String _typeSettings;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<AssetListEntrySegmentsEntryRel, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((AssetListEntrySegmentsEntryRel)this);
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
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"alEntrySegmentsEntryRelId", _assetListEntrySegmentsEntryRelId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("assetListEntryId", _assetListEntryId);
		_columnOriginalValues.put("priority", _priority);
		_columnOriginalValues.put("segmentsEntryId", _segmentsEntryId);
		_columnOriginalValues.put("typeSettings", _typeSettings);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put(
			"alEntrySegmentsEntryRelId", "assetListEntrySegmentsEntryRelId");

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

		columnBitmasks.put("uuid_", 4L);

		columnBitmasks.put("alEntrySegmentsEntryRelId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("modifiedDate", 512L);

		columnBitmasks.put("assetListEntryId", 1024L);

		columnBitmasks.put("priority", 2048L);

		columnBitmasks.put("segmentsEntryId", 4096L);

		columnBitmasks.put("typeSettings", 8192L);

		columnBitmasks.put("lastPublishDate", 16384L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private AssetListEntrySegmentsEntryRel _escapedModel;

}