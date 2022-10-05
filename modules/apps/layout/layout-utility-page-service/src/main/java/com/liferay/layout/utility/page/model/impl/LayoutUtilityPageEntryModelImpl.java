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

package com.liferay.layout.utility.page.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.layout.utility.page.model.LayoutUtilityPageEntry;
import com.liferay.layout.utility.page.model.LayoutUtilityPageEntryModel;
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
 * The base model implementation for the LayoutUtilityPageEntry service. Represents a row in the &quot;LayoutUtilityPageEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LayoutUtilityPageEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutUtilityPageEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutUtilityPageEntryImpl
 * @generated
 */
@JSON(strict = true)
public class LayoutUtilityPageEntryModelImpl
	extends BaseModelImpl<LayoutUtilityPageEntry>
	implements LayoutUtilityPageEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout utility page entry model instance should use the <code>LayoutUtilityPageEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "LayoutUtilityPageEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"externalReferenceCode", Types.VARCHAR},
		{"LayoutUtilityPageEntryId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"plid", Types.BIGINT},
		{"defaultLayoutUtilityPageEntry", Types.BOOLEAN},
		{"name", Types.VARCHAR}, {"type_", Types.INTEGER},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("LayoutUtilityPageEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("plid", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("defaultLayoutUtilityPageEntry", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LayoutUtilityPageEntry (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,LayoutUtilityPageEntryId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,plid LONG,defaultLayoutUtilityPageEntry BOOLEAN,name VARCHAR(75) null,type_ INTEGER,lastPublishDate DATE null,primary key (LayoutUtilityPageEntryId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table LayoutUtilityPageEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY layoutUtilityPageEntry.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY LayoutUtilityPageEntry.name ASC";

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
	public static final long DEFAULTLAYOUTUTILITYPAGEENTRY_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 64L;

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

	public LayoutUtilityPageEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _LayoutUtilityPageEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLayoutUtilityPageEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _LayoutUtilityPageEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutUtilityPageEntry.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutUtilityPageEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LayoutUtilityPageEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LayoutUtilityPageEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutUtilityPageEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((LayoutUtilityPageEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LayoutUtilityPageEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LayoutUtilityPageEntry, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LayoutUtilityPageEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LayoutUtilityPageEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LayoutUtilityPageEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<LayoutUtilityPageEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<LayoutUtilityPageEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<LayoutUtilityPageEntry, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<LayoutUtilityPageEntry, Object>>();
		Map<String, BiConsumer<LayoutUtilityPageEntry, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<LayoutUtilityPageEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", LayoutUtilityPageEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<LayoutUtilityPageEntry, Long>)
				LayoutUtilityPageEntry::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", LayoutUtilityPageEntry::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<LayoutUtilityPageEntry, Long>)
				LayoutUtilityPageEntry::setCtCollectionId);
		attributeGetterFunctions.put("uuid", LayoutUtilityPageEntry::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<LayoutUtilityPageEntry, String>)
				LayoutUtilityPageEntry::setUuid);
		attributeGetterFunctions.put(
			"externalReferenceCode",
			LayoutUtilityPageEntry::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<LayoutUtilityPageEntry, String>)
				LayoutUtilityPageEntry::setExternalReferenceCode);
		attributeGetterFunctions.put(
			"LayoutUtilityPageEntryId",
			LayoutUtilityPageEntry::getLayoutUtilityPageEntryId);
		attributeSetterBiConsumers.put(
			"LayoutUtilityPageEntryId",
			(BiConsumer<LayoutUtilityPageEntry, Long>)
				LayoutUtilityPageEntry::setLayoutUtilityPageEntryId);
		attributeGetterFunctions.put(
			"groupId", LayoutUtilityPageEntry::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<LayoutUtilityPageEntry, Long>)
				LayoutUtilityPageEntry::setGroupId);
		attributeGetterFunctions.put(
			"companyId", LayoutUtilityPageEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<LayoutUtilityPageEntry, Long>)
				LayoutUtilityPageEntry::setCompanyId);
		attributeGetterFunctions.put(
			"userId", LayoutUtilityPageEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<LayoutUtilityPageEntry, Long>)
				LayoutUtilityPageEntry::setUserId);
		attributeGetterFunctions.put(
			"userName", LayoutUtilityPageEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<LayoutUtilityPageEntry, String>)
				LayoutUtilityPageEntry::setUserName);
		attributeGetterFunctions.put(
			"createDate", LayoutUtilityPageEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<LayoutUtilityPageEntry, Date>)
				LayoutUtilityPageEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", LayoutUtilityPageEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<LayoutUtilityPageEntry, Date>)
				LayoutUtilityPageEntry::setModifiedDate);
		attributeGetterFunctions.put("plid", LayoutUtilityPageEntry::getPlid);
		attributeSetterBiConsumers.put(
			"plid",
			(BiConsumer<LayoutUtilityPageEntry, Long>)
				LayoutUtilityPageEntry::setPlid);
		attributeGetterFunctions.put(
			"defaultLayoutUtilityPageEntry",
			LayoutUtilityPageEntry::getDefaultLayoutUtilityPageEntry);
		attributeSetterBiConsumers.put(
			"defaultLayoutUtilityPageEntry",
			(BiConsumer<LayoutUtilityPageEntry, Boolean>)
				LayoutUtilityPageEntry::setDefaultLayoutUtilityPageEntry);
		attributeGetterFunctions.put("name", LayoutUtilityPageEntry::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<LayoutUtilityPageEntry, String>)
				LayoutUtilityPageEntry::setName);
		attributeGetterFunctions.put("type", LayoutUtilityPageEntry::getType);
		attributeSetterBiConsumers.put(
			"type",
			(BiConsumer<LayoutUtilityPageEntry, Integer>)
				LayoutUtilityPageEntry::setType);
		attributeGetterFunctions.put(
			"lastPublishDate", LayoutUtilityPageEntry::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<LayoutUtilityPageEntry, Date>)
				LayoutUtilityPageEntry::setLastPublishDate);

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
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_externalReferenceCode = externalReferenceCode;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalExternalReferenceCode() {
		return getColumnOriginalValue("externalReferenceCode");
	}

	@JSON
	@Override
	public long getLayoutUtilityPageEntryId() {
		return _LayoutUtilityPageEntryId;
	}

	@Override
	public void setLayoutUtilityPageEntryId(long LayoutUtilityPageEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_LayoutUtilityPageEntryId = LayoutUtilityPageEntryId;
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
	public long getPlid() {
		return _plid;
	}

	@Override
	public void setPlid(long plid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_plid = plid;
	}

	@JSON
	@Override
	public boolean getDefaultLayoutUtilityPageEntry() {
		return _defaultLayoutUtilityPageEntry;
	}

	@JSON
	@Override
	public boolean isDefaultLayoutUtilityPageEntry() {
		return _defaultLayoutUtilityPageEntry;
	}

	@Override
	public void setDefaultLayoutUtilityPageEntry(
		boolean defaultLayoutUtilityPageEntry) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_defaultLayoutUtilityPageEntry = defaultLayoutUtilityPageEntry;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalDefaultLayoutUtilityPageEntry() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue(
				"defaultLayoutUtilityPageEntry"));
	}

	@JSON
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
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalType() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("type_"));
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
			PortalUtil.getClassNameId(LayoutUtilityPageEntry.class.getName()));
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
			getCompanyId(), LayoutUtilityPageEntry.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LayoutUtilityPageEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LayoutUtilityPageEntry>
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
		LayoutUtilityPageEntryImpl layoutUtilityPageEntryImpl =
			new LayoutUtilityPageEntryImpl();

		layoutUtilityPageEntryImpl.setMvccVersion(getMvccVersion());
		layoutUtilityPageEntryImpl.setCtCollectionId(getCtCollectionId());
		layoutUtilityPageEntryImpl.setUuid(getUuid());
		layoutUtilityPageEntryImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		layoutUtilityPageEntryImpl.setLayoutUtilityPageEntryId(
			getLayoutUtilityPageEntryId());
		layoutUtilityPageEntryImpl.setGroupId(getGroupId());
		layoutUtilityPageEntryImpl.setCompanyId(getCompanyId());
		layoutUtilityPageEntryImpl.setUserId(getUserId());
		layoutUtilityPageEntryImpl.setUserName(getUserName());
		layoutUtilityPageEntryImpl.setCreateDate(getCreateDate());
		layoutUtilityPageEntryImpl.setModifiedDate(getModifiedDate());
		layoutUtilityPageEntryImpl.setPlid(getPlid());
		layoutUtilityPageEntryImpl.setDefaultLayoutUtilityPageEntry(
			isDefaultLayoutUtilityPageEntry());
		layoutUtilityPageEntryImpl.setName(getName());
		layoutUtilityPageEntryImpl.setType(getType());
		layoutUtilityPageEntryImpl.setLastPublishDate(getLastPublishDate());

		layoutUtilityPageEntryImpl.resetOriginalValues();

		return layoutUtilityPageEntryImpl;
	}

	@Override
	public LayoutUtilityPageEntry cloneWithOriginalValues() {
		LayoutUtilityPageEntryImpl layoutUtilityPageEntryImpl =
			new LayoutUtilityPageEntryImpl();

		layoutUtilityPageEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		layoutUtilityPageEntryImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		layoutUtilityPageEntryImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		layoutUtilityPageEntryImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		layoutUtilityPageEntryImpl.setLayoutUtilityPageEntryId(
			this.<Long>getColumnOriginalValue("LayoutUtilityPageEntryId"));
		layoutUtilityPageEntryImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		layoutUtilityPageEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		layoutUtilityPageEntryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		layoutUtilityPageEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		layoutUtilityPageEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		layoutUtilityPageEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		layoutUtilityPageEntryImpl.setPlid(
			this.<Long>getColumnOriginalValue("plid"));
		layoutUtilityPageEntryImpl.setDefaultLayoutUtilityPageEntry(
			this.<Boolean>getColumnOriginalValue(
				"defaultLayoutUtilityPageEntry"));
		layoutUtilityPageEntryImpl.setName(
			this.<String>getColumnOriginalValue("name"));
		layoutUtilityPageEntryImpl.setType(
			this.<Integer>getColumnOriginalValue("type_"));
		layoutUtilityPageEntryImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return layoutUtilityPageEntryImpl;
	}

	@Override
	public int compareTo(LayoutUtilityPageEntry layoutUtilityPageEntry) {
		int value = 0;

		value = getName().compareTo(layoutUtilityPageEntry.getName());

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

		if (!(object instanceof LayoutUtilityPageEntry)) {
			return false;
		}

		LayoutUtilityPageEntry layoutUtilityPageEntry =
			(LayoutUtilityPageEntry)object;

		long primaryKey = layoutUtilityPageEntry.getPrimaryKey();

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
	public CacheModel<LayoutUtilityPageEntry> toCacheModel() {
		LayoutUtilityPageEntryCacheModel layoutUtilityPageEntryCacheModel =
			new LayoutUtilityPageEntryCacheModel();

		layoutUtilityPageEntryCacheModel.mvccVersion = getMvccVersion();

		layoutUtilityPageEntryCacheModel.ctCollectionId = getCtCollectionId();

		layoutUtilityPageEntryCacheModel.uuid = getUuid();

		String uuid = layoutUtilityPageEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			layoutUtilityPageEntryCacheModel.uuid = null;
		}

		layoutUtilityPageEntryCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			layoutUtilityPageEntryCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			layoutUtilityPageEntryCacheModel.externalReferenceCode = null;
		}

		layoutUtilityPageEntryCacheModel.LayoutUtilityPageEntryId =
			getLayoutUtilityPageEntryId();

		layoutUtilityPageEntryCacheModel.groupId = getGroupId();

		layoutUtilityPageEntryCacheModel.companyId = getCompanyId();

		layoutUtilityPageEntryCacheModel.userId = getUserId();

		layoutUtilityPageEntryCacheModel.userName = getUserName();

		String userName = layoutUtilityPageEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			layoutUtilityPageEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			layoutUtilityPageEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			layoutUtilityPageEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			layoutUtilityPageEntryCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			layoutUtilityPageEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		layoutUtilityPageEntryCacheModel.plid = getPlid();

		layoutUtilityPageEntryCacheModel.defaultLayoutUtilityPageEntry =
			isDefaultLayoutUtilityPageEntry();

		layoutUtilityPageEntryCacheModel.name = getName();

		String name = layoutUtilityPageEntryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			layoutUtilityPageEntryCacheModel.name = null;
		}

		layoutUtilityPageEntryCacheModel.type = getType();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			layoutUtilityPageEntryCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			layoutUtilityPageEntryCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return layoutUtilityPageEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LayoutUtilityPageEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LayoutUtilityPageEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutUtilityPageEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(LayoutUtilityPageEntry)this);

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

		private static final Function<InvocationHandler, LayoutUtilityPageEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					LayoutUtilityPageEntry.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private String _externalReferenceCode;
	private long _LayoutUtilityPageEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _plid;
	private boolean _defaultLayoutUtilityPageEntry;
	private String _name;
	private int _type;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<LayoutUtilityPageEntry, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((LayoutUtilityPageEntry)this);
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
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put(
			"LayoutUtilityPageEntryId", _LayoutUtilityPageEntryId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("plid", _plid);
		_columnOriginalValues.put(
			"defaultLayoutUtilityPageEntry", _defaultLayoutUtilityPageEntry);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("type_", "type");

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

		columnBitmasks.put("externalReferenceCode", 8L);

		columnBitmasks.put("LayoutUtilityPageEntryId", 16L);

		columnBitmasks.put("groupId", 32L);

		columnBitmasks.put("companyId", 64L);

		columnBitmasks.put("userId", 128L);

		columnBitmasks.put("userName", 256L);

		columnBitmasks.put("createDate", 512L);

		columnBitmasks.put("modifiedDate", 1024L);

		columnBitmasks.put("plid", 2048L);

		columnBitmasks.put("defaultLayoutUtilityPageEntry", 4096L);

		columnBitmasks.put("name", 8192L);

		columnBitmasks.put("type_", 16384L);

		columnBitmasks.put("lastPublishDate", 32768L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private LayoutUtilityPageEntry _escapedModel;

}