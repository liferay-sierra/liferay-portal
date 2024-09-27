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

package com.liferay.depot.model.impl;

import com.liferay.depot.model.DepotEntryGroupRel;
import com.liferay.depot.model.DepotEntryGroupRelModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
 * The base model implementation for the DepotEntryGroupRel service. Represents a row in the &quot;DepotEntryGroupRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DepotEntryGroupRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DepotEntryGroupRelImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DepotEntryGroupRelImpl
 * @generated
 */
@JSON(strict = true)
public class DepotEntryGroupRelModelImpl
	extends BaseModelImpl<DepotEntryGroupRel>
	implements DepotEntryGroupRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a depot entry group rel model instance should use the <code>DepotEntryGroupRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "DepotEntryGroupRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"depotEntryGroupRelId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"ddmStructuresAvailable", Types.BOOLEAN},
		{"depotEntryId", Types.BIGINT}, {"searchable", Types.BOOLEAN},
		{"toGroupId", Types.BIGINT}, {"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("depotEntryGroupRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("ddmStructuresAvailable", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("depotEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("searchable", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("toGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DepotEntryGroupRel (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,depotEntryGroupRelId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,ddmStructuresAvailable BOOLEAN,depotEntryId LONG,searchable BOOLEAN,toGroupId LONG,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table DepotEntryGroupRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY depotEntryGroupRel.depotEntryGroupRelId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DepotEntryGroupRel.depotEntryGroupRelId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DDMSTRUCTURESAVAILABLE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DEPOTENTRYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SEARCHABLE_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TOGROUPID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DEPOTENTRYGROUPRELID_COLUMN_BITMASK = 128L;

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

	public DepotEntryGroupRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _depotEntryGroupRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDepotEntryGroupRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _depotEntryGroupRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DepotEntryGroupRel.class;
	}

	@Override
	public String getModelClassName() {
		return DepotEntryGroupRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DepotEntryGroupRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DepotEntryGroupRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DepotEntryGroupRel, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DepotEntryGroupRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DepotEntryGroupRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DepotEntryGroupRel, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DepotEntryGroupRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DepotEntryGroupRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DepotEntryGroupRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<DepotEntryGroupRel, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DepotEntryGroupRel, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DepotEntryGroupRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<DepotEntryGroupRel, Object>>();
		Map<String, BiConsumer<DepotEntryGroupRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<DepotEntryGroupRel, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", DepotEntryGroupRel::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<DepotEntryGroupRel, Long>)
				DepotEntryGroupRel::setMvccVersion);
		attributeGetterFunctions.put("uuid", DepotEntryGroupRel::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<DepotEntryGroupRel, String>)
				DepotEntryGroupRel::setUuid);
		attributeGetterFunctions.put(
			"depotEntryGroupRelId",
			DepotEntryGroupRel::getDepotEntryGroupRelId);
		attributeSetterBiConsumers.put(
			"depotEntryGroupRelId",
			(BiConsumer<DepotEntryGroupRel, Long>)
				DepotEntryGroupRel::setDepotEntryGroupRelId);
		attributeGetterFunctions.put("groupId", DepotEntryGroupRel::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<DepotEntryGroupRel, Long>)
				DepotEntryGroupRel::setGroupId);
		attributeGetterFunctions.put(
			"companyId", DepotEntryGroupRel::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<DepotEntryGroupRel, Long>)
				DepotEntryGroupRel::setCompanyId);
		attributeGetterFunctions.put("userId", DepotEntryGroupRel::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<DepotEntryGroupRel, Long>)
				DepotEntryGroupRel::setUserId);
		attributeGetterFunctions.put(
			"userName", DepotEntryGroupRel::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<DepotEntryGroupRel, String>)
				DepotEntryGroupRel::setUserName);
		attributeGetterFunctions.put(
			"createDate", DepotEntryGroupRel::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<DepotEntryGroupRel, Date>)
				DepotEntryGroupRel::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", DepotEntryGroupRel::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<DepotEntryGroupRel, Date>)
				DepotEntryGroupRel::setModifiedDate);
		attributeGetterFunctions.put(
			"ddmStructuresAvailable",
			DepotEntryGroupRel::getDdmStructuresAvailable);
		attributeSetterBiConsumers.put(
			"ddmStructuresAvailable",
			(BiConsumer<DepotEntryGroupRel, Boolean>)
				DepotEntryGroupRel::setDdmStructuresAvailable);
		attributeGetterFunctions.put(
			"depotEntryId", DepotEntryGroupRel::getDepotEntryId);
		attributeSetterBiConsumers.put(
			"depotEntryId",
			(BiConsumer<DepotEntryGroupRel, Long>)
				DepotEntryGroupRel::setDepotEntryId);
		attributeGetterFunctions.put(
			"searchable", DepotEntryGroupRel::getSearchable);
		attributeSetterBiConsumers.put(
			"searchable",
			(BiConsumer<DepotEntryGroupRel, Boolean>)
				DepotEntryGroupRel::setSearchable);
		attributeGetterFunctions.put(
			"toGroupId", DepotEntryGroupRel::getToGroupId);
		attributeSetterBiConsumers.put(
			"toGroupId",
			(BiConsumer<DepotEntryGroupRel, Long>)
				DepotEntryGroupRel::setToGroupId);
		attributeGetterFunctions.put(
			"lastPublishDate", DepotEntryGroupRel::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<DepotEntryGroupRel, Date>)
				DepotEntryGroupRel::setLastPublishDate);

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

	@JSON
	@Override
	public long getDepotEntryGroupRelId() {
		return _depotEntryGroupRelId;
	}

	@Override
	public void setDepotEntryGroupRelId(long depotEntryGroupRelId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_depotEntryGroupRelId = depotEntryGroupRelId;
	}

	@JSON
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
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
	public boolean getDdmStructuresAvailable() {
		return _ddmStructuresAvailable;
	}

	@JSON
	@Override
	public boolean isDdmStructuresAvailable() {
		return _ddmStructuresAvailable;
	}

	@Override
	public void setDdmStructuresAvailable(boolean ddmStructuresAvailable) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ddmStructuresAvailable = ddmStructuresAvailable;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalDdmStructuresAvailable() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("ddmStructuresAvailable"));
	}

	@JSON
	@Override
	public long getDepotEntryId() {
		return _depotEntryId;
	}

	@Override
	public void setDepotEntryId(long depotEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_depotEntryId = depotEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalDepotEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("depotEntryId"));
	}

	@JSON
	@Override
	public boolean getSearchable() {
		return _searchable;
	}

	@JSON
	@Override
	public boolean isSearchable() {
		return _searchable;
	}

	@Override
	public void setSearchable(boolean searchable) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_searchable = searchable;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalSearchable() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("searchable"));
	}

	@JSON
	@Override
	public long getToGroupId() {
		return _toGroupId;
	}

	@Override
	public void setToGroupId(long toGroupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_toGroupId = toGroupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalToGroupId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("toGroupId"));
	}

	@JSON
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
			PortalUtil.getClassNameId(DepotEntryGroupRel.class.getName()));
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
			getCompanyId(), DepotEntryGroupRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DepotEntryGroupRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DepotEntryGroupRel>
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
		DepotEntryGroupRelImpl depotEntryGroupRelImpl =
			new DepotEntryGroupRelImpl();

		depotEntryGroupRelImpl.setMvccVersion(getMvccVersion());
		depotEntryGroupRelImpl.setUuid(getUuid());
		depotEntryGroupRelImpl.setDepotEntryGroupRelId(
			getDepotEntryGroupRelId());
		depotEntryGroupRelImpl.setGroupId(getGroupId());
		depotEntryGroupRelImpl.setCompanyId(getCompanyId());
		depotEntryGroupRelImpl.setUserId(getUserId());
		depotEntryGroupRelImpl.setUserName(getUserName());
		depotEntryGroupRelImpl.setCreateDate(getCreateDate());
		depotEntryGroupRelImpl.setModifiedDate(getModifiedDate());
		depotEntryGroupRelImpl.setDdmStructuresAvailable(
			isDdmStructuresAvailable());
		depotEntryGroupRelImpl.setDepotEntryId(getDepotEntryId());
		depotEntryGroupRelImpl.setSearchable(isSearchable());
		depotEntryGroupRelImpl.setToGroupId(getToGroupId());
		depotEntryGroupRelImpl.setLastPublishDate(getLastPublishDate());

		depotEntryGroupRelImpl.resetOriginalValues();

		return depotEntryGroupRelImpl;
	}

	@Override
	public DepotEntryGroupRel cloneWithOriginalValues() {
		DepotEntryGroupRelImpl depotEntryGroupRelImpl =
			new DepotEntryGroupRelImpl();

		depotEntryGroupRelImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		depotEntryGroupRelImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		depotEntryGroupRelImpl.setDepotEntryGroupRelId(
			this.<Long>getColumnOriginalValue("depotEntryGroupRelId"));
		depotEntryGroupRelImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		depotEntryGroupRelImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		depotEntryGroupRelImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		depotEntryGroupRelImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		depotEntryGroupRelImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		depotEntryGroupRelImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		depotEntryGroupRelImpl.setDdmStructuresAvailable(
			this.<Boolean>getColumnOriginalValue("ddmStructuresAvailable"));
		depotEntryGroupRelImpl.setDepotEntryId(
			this.<Long>getColumnOriginalValue("depotEntryId"));
		depotEntryGroupRelImpl.setSearchable(
			this.<Boolean>getColumnOriginalValue("searchable"));
		depotEntryGroupRelImpl.setToGroupId(
			this.<Long>getColumnOriginalValue("toGroupId"));
		depotEntryGroupRelImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return depotEntryGroupRelImpl;
	}

	@Override
	public int compareTo(DepotEntryGroupRel depotEntryGroupRel) {
		long primaryKey = depotEntryGroupRel.getPrimaryKey();

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

		if (!(object instanceof DepotEntryGroupRel)) {
			return false;
		}

		DepotEntryGroupRel depotEntryGroupRel = (DepotEntryGroupRel)object;

		long primaryKey = depotEntryGroupRel.getPrimaryKey();

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
	public CacheModel<DepotEntryGroupRel> toCacheModel() {
		DepotEntryGroupRelCacheModel depotEntryGroupRelCacheModel =
			new DepotEntryGroupRelCacheModel();

		depotEntryGroupRelCacheModel.mvccVersion = getMvccVersion();

		depotEntryGroupRelCacheModel.uuid = getUuid();

		String uuid = depotEntryGroupRelCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			depotEntryGroupRelCacheModel.uuid = null;
		}

		depotEntryGroupRelCacheModel.depotEntryGroupRelId =
			getDepotEntryGroupRelId();

		depotEntryGroupRelCacheModel.groupId = getGroupId();

		depotEntryGroupRelCacheModel.companyId = getCompanyId();

		depotEntryGroupRelCacheModel.userId = getUserId();

		depotEntryGroupRelCacheModel.userName = getUserName();

		String userName = depotEntryGroupRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			depotEntryGroupRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			depotEntryGroupRelCacheModel.createDate = createDate.getTime();
		}
		else {
			depotEntryGroupRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			depotEntryGroupRelCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			depotEntryGroupRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		depotEntryGroupRelCacheModel.ddmStructuresAvailable =
			isDdmStructuresAvailable();

		depotEntryGroupRelCacheModel.depotEntryId = getDepotEntryId();

		depotEntryGroupRelCacheModel.searchable = isSearchable();

		depotEntryGroupRelCacheModel.toGroupId = getToGroupId();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			depotEntryGroupRelCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			depotEntryGroupRelCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return depotEntryGroupRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DepotEntryGroupRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DepotEntryGroupRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DepotEntryGroupRel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(DepotEntryGroupRel)this);

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

		private static final Function<InvocationHandler, DepotEntryGroupRel>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					DepotEntryGroupRel.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _uuid;
	private long _depotEntryGroupRelId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private boolean _ddmStructuresAvailable;
	private long _depotEntryId;
	private boolean _searchable;
	private long _toGroupId;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<DepotEntryGroupRel, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((DepotEntryGroupRel)this);
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
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"depotEntryGroupRelId", _depotEntryGroupRelId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"ddmStructuresAvailable", _ddmStructuresAvailable);
		_columnOriginalValues.put("depotEntryId", _depotEntryId);
		_columnOriginalValues.put("searchable", _searchable);
		_columnOriginalValues.put("toGroupId", _toGroupId);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

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

		columnBitmasks.put("uuid_", 2L);

		columnBitmasks.put("depotEntryGroupRelId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("ddmStructuresAvailable", 512L);

		columnBitmasks.put("depotEntryId", 1024L);

		columnBitmasks.put("searchable", 2048L);

		columnBitmasks.put("toGroupId", 4096L);

		columnBitmasks.put("lastPublishDate", 8192L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private DepotEntryGroupRel _escapedModel;

}