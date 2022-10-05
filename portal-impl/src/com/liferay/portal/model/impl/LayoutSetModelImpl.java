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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutSetModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
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
 * The base model implementation for the LayoutSet service. Represents a row in the &quot;LayoutSet&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LayoutSetModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutSetImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetImpl
 * @generated
 */
@JSON(strict = true)
public class LayoutSetModelImpl
	extends BaseModelImpl<LayoutSet> implements LayoutSetModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout set model instance should use the <code>LayoutSet</code> interface instead.
	 */
	public static final String TABLE_NAME = "LayoutSet";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"layoutSetId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"privateLayout", Types.BOOLEAN},
		{"logoId", Types.BIGINT}, {"themeId", Types.VARCHAR},
		{"colorSchemeId", Types.VARCHAR}, {"faviconFileEntryId", Types.BIGINT},
		{"css", Types.CLOB}, {"settings_", Types.CLOB},
		{"layoutSetPrototypeUuid", Types.VARCHAR},
		{"layoutSetPrototypeLinkEnabled", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("layoutSetId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("privateLayout", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("logoId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("themeId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("colorSchemeId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("faviconFileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("css", Types.CLOB);
		TABLE_COLUMNS_MAP.put("settings_", Types.CLOB);
		TABLE_COLUMNS_MAP.put("layoutSetPrototypeUuid", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("layoutSetPrototypeLinkEnabled", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LayoutSet (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,layoutSetId LONG not null,groupId LONG,companyId LONG,createDate DATE null,modifiedDate DATE null,privateLayout BOOLEAN,logoId LONG,themeId VARCHAR(75) null,colorSchemeId VARCHAR(75) null,faviconFileEntryId LONG,css TEXT null,settings_ TEXT null,layoutSetPrototypeUuid VARCHAR(75) null,layoutSetPrototypeLinkEnabled BOOLEAN,primary key (layoutSetId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table LayoutSet";

	public static final String ORDER_BY_JPQL =
		" ORDER BY layoutSet.layoutSetId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY LayoutSet.layoutSetId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LAYOUTSETPROTOTYPEUUID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LOGOID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PRIVATELAYOUT_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LAYOUTSETID_COLUMN_BITMASK = 32L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.LayoutSet"));

	public LayoutSetModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _layoutSetId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLayoutSetId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutSetId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutSet.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutSet.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LayoutSet, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LayoutSet, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutSet, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((LayoutSet)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LayoutSet, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LayoutSet, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LayoutSet)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LayoutSet, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LayoutSet, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<LayoutSet, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<LayoutSet, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<LayoutSet, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<LayoutSet, Object>>();
		Map<String, BiConsumer<LayoutSet, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<LayoutSet, ?>>();

		attributeGetterFunctions.put("mvccVersion", LayoutSet::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<LayoutSet, Long>)LayoutSet::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", LayoutSet::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<LayoutSet, Long>)LayoutSet::setCtCollectionId);
		attributeGetterFunctions.put("layoutSetId", LayoutSet::getLayoutSetId);
		attributeSetterBiConsumers.put(
			"layoutSetId",
			(BiConsumer<LayoutSet, Long>)LayoutSet::setLayoutSetId);
		attributeGetterFunctions.put("groupId", LayoutSet::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<LayoutSet, Long>)LayoutSet::setGroupId);
		attributeGetterFunctions.put("companyId", LayoutSet::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<LayoutSet, Long>)LayoutSet::setCompanyId);
		attributeGetterFunctions.put("createDate", LayoutSet::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<LayoutSet, Date>)LayoutSet::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", LayoutSet::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<LayoutSet, Date>)LayoutSet::setModifiedDate);
		attributeGetterFunctions.put(
			"privateLayout", LayoutSet::getPrivateLayout);
		attributeSetterBiConsumers.put(
			"privateLayout",
			(BiConsumer<LayoutSet, Boolean>)LayoutSet::setPrivateLayout);
		attributeGetterFunctions.put("logoId", LayoutSet::getLogoId);
		attributeSetterBiConsumers.put(
			"logoId", (BiConsumer<LayoutSet, Long>)LayoutSet::setLogoId);
		attributeGetterFunctions.put("themeId", LayoutSet::getThemeId);
		attributeSetterBiConsumers.put(
			"themeId", (BiConsumer<LayoutSet, String>)LayoutSet::setThemeId);
		attributeGetterFunctions.put(
			"colorSchemeId", LayoutSet::getColorSchemeId);
		attributeSetterBiConsumers.put(
			"colorSchemeId",
			(BiConsumer<LayoutSet, String>)LayoutSet::setColorSchemeId);
		attributeGetterFunctions.put(
			"faviconFileEntryId", LayoutSet::getFaviconFileEntryId);
		attributeSetterBiConsumers.put(
			"faviconFileEntryId",
			(BiConsumer<LayoutSet, Long>)LayoutSet::setFaviconFileEntryId);
		attributeGetterFunctions.put("css", LayoutSet::getCss);
		attributeSetterBiConsumers.put(
			"css", (BiConsumer<LayoutSet, String>)LayoutSet::setCss);
		attributeGetterFunctions.put("settings", LayoutSet::getSettings);
		attributeSetterBiConsumers.put(
			"settings", (BiConsumer<LayoutSet, String>)LayoutSet::setSettings);
		attributeGetterFunctions.put(
			"layoutSetPrototypeUuid", LayoutSet::getLayoutSetPrototypeUuid);
		attributeSetterBiConsumers.put(
			"layoutSetPrototypeUuid",
			(BiConsumer<LayoutSet, String>)
				LayoutSet::setLayoutSetPrototypeUuid);
		attributeGetterFunctions.put(
			"layoutSetPrototypeLinkEnabled",
			LayoutSet::getLayoutSetPrototypeLinkEnabled);
		attributeSetterBiConsumers.put(
			"layoutSetPrototypeLinkEnabled",
			(BiConsumer<LayoutSet, Boolean>)
				LayoutSet::setLayoutSetPrototypeLinkEnabled);

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
	public long getLayoutSetId() {
		return _layoutSetId;
	}

	@Override
	public void setLayoutSetId(long layoutSetId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_layoutSetId = layoutSetId;
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
	public boolean getPrivateLayout() {
		return _privateLayout;
	}

	@JSON
	@Override
	public boolean isPrivateLayout() {
		return _privateLayout;
	}

	@Override
	public void setPrivateLayout(boolean privateLayout) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_privateLayout = privateLayout;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalPrivateLayout() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("privateLayout"));
	}

	@JSON
	@Override
	public long getLogoId() {
		return _logoId;
	}

	@Override
	public void setLogoId(long logoId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_logoId = logoId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalLogoId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("logoId"));
	}

	@JSON
	@Override
	public String getThemeId() {
		if (_themeId == null) {
			return "";
		}
		else {
			return _themeId;
		}
	}

	@Override
	public void setThemeId(String themeId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_themeId = themeId;
	}

	@JSON
	@Override
	public String getColorSchemeId() {
		if (_colorSchemeId == null) {
			return "";
		}
		else {
			return _colorSchemeId;
		}
	}

	@Override
	public void setColorSchemeId(String colorSchemeId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_colorSchemeId = colorSchemeId;
	}

	@JSON
	@Override
	public long getFaviconFileEntryId() {
		return _faviconFileEntryId;
	}

	@Override
	public void setFaviconFileEntryId(long faviconFileEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_faviconFileEntryId = faviconFileEntryId;
	}

	@JSON
	@Override
	public String getCss() {
		if (_css == null) {
			return "";
		}
		else {
			return _css;
		}
	}

	@Override
	public void setCss(String css) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_css = css;
	}

	@JSON
	@Override
	public String getSettings() {
		if (_settings == null) {
			return "";
		}
		else {
			return _settings;
		}
	}

	@Override
	public void setSettings(String settings) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_settings = settings;
	}

	@JSON
	@Override
	public String getLayoutSetPrototypeUuid() {
		if (_layoutSetPrototypeUuid == null) {
			return "";
		}
		else {
			return _layoutSetPrototypeUuid;
		}
	}

	@Override
	public void setLayoutSetPrototypeUuid(String layoutSetPrototypeUuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_layoutSetPrototypeUuid = layoutSetPrototypeUuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalLayoutSetPrototypeUuid() {
		return getColumnOriginalValue("layoutSetPrototypeUuid");
	}

	@JSON
	@Override
	public boolean getLayoutSetPrototypeLinkEnabled() {
		return _layoutSetPrototypeLinkEnabled;
	}

	@JSON
	@Override
	public boolean isLayoutSetPrototypeLinkEnabled() {
		return _layoutSetPrototypeLinkEnabled;
	}

	@Override
	public void setLayoutSetPrototypeLinkEnabled(
		boolean layoutSetPrototypeLinkEnabled) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_layoutSetPrototypeLinkEnabled = layoutSetPrototypeLinkEnabled;
	}

	public String getCompanyFallbackVirtualHostname() {
		return null;
	}

	public void setCompanyFallbackVirtualHostname(
		String companyFallbackVirtualHostname) {
	}

	public java.util.TreeMap<String, String> getVirtualHostnames() {
		return null;
	}

	public void setVirtualHostnames(
		java.util.TreeMap<String, String> virtualHostnames) {
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
			getCompanyId(), LayoutSet.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LayoutSet toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LayoutSet>
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
		LayoutSetImpl layoutSetImpl = new LayoutSetImpl();

		layoutSetImpl.setMvccVersion(getMvccVersion());
		layoutSetImpl.setCtCollectionId(getCtCollectionId());
		layoutSetImpl.setLayoutSetId(getLayoutSetId());
		layoutSetImpl.setGroupId(getGroupId());
		layoutSetImpl.setCompanyId(getCompanyId());
		layoutSetImpl.setCreateDate(getCreateDate());
		layoutSetImpl.setModifiedDate(getModifiedDate());
		layoutSetImpl.setPrivateLayout(isPrivateLayout());
		layoutSetImpl.setLogoId(getLogoId());
		layoutSetImpl.setThemeId(getThemeId());
		layoutSetImpl.setColorSchemeId(getColorSchemeId());
		layoutSetImpl.setFaviconFileEntryId(getFaviconFileEntryId());
		layoutSetImpl.setCss(getCss());
		layoutSetImpl.setSettings(getSettings());
		layoutSetImpl.setLayoutSetPrototypeUuid(getLayoutSetPrototypeUuid());
		layoutSetImpl.setLayoutSetPrototypeLinkEnabled(
			isLayoutSetPrototypeLinkEnabled());

		layoutSetImpl.resetOriginalValues();

		return layoutSetImpl;
	}

	@Override
	public LayoutSet cloneWithOriginalValues() {
		LayoutSetImpl layoutSetImpl = new LayoutSetImpl();

		layoutSetImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		layoutSetImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		layoutSetImpl.setLayoutSetId(
			this.<Long>getColumnOriginalValue("layoutSetId"));
		layoutSetImpl.setGroupId(this.<Long>getColumnOriginalValue("groupId"));
		layoutSetImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		layoutSetImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		layoutSetImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		layoutSetImpl.setPrivateLayout(
			this.<Boolean>getColumnOriginalValue("privateLayout"));
		layoutSetImpl.setLogoId(this.<Long>getColumnOriginalValue("logoId"));
		layoutSetImpl.setThemeId(
			this.<String>getColumnOriginalValue("themeId"));
		layoutSetImpl.setColorSchemeId(
			this.<String>getColumnOriginalValue("colorSchemeId"));
		layoutSetImpl.setFaviconFileEntryId(
			this.<Long>getColumnOriginalValue("faviconFileEntryId"));
		layoutSetImpl.setCss(this.<String>getColumnOriginalValue("css"));
		layoutSetImpl.setSettings(
			this.<String>getColumnOriginalValue("settings_"));
		layoutSetImpl.setLayoutSetPrototypeUuid(
			this.<String>getColumnOriginalValue("layoutSetPrototypeUuid"));
		layoutSetImpl.setLayoutSetPrototypeLinkEnabled(
			this.<Boolean>getColumnOriginalValue(
				"layoutSetPrototypeLinkEnabled"));

		return layoutSetImpl;
	}

	@Override
	public int compareTo(LayoutSet layoutSet) {
		long primaryKey = layoutSet.getPrimaryKey();

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

		if (!(object instanceof LayoutSet)) {
			return false;
		}

		LayoutSet layoutSet = (LayoutSet)object;

		long primaryKey = layoutSet.getPrimaryKey();

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
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		setCompanyFallbackVirtualHostname(null);

		setVirtualHostnames(null);

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<LayoutSet> toCacheModel() {
		LayoutSetCacheModel layoutSetCacheModel = new LayoutSetCacheModel();

		layoutSetCacheModel.mvccVersion = getMvccVersion();

		layoutSetCacheModel.ctCollectionId = getCtCollectionId();

		layoutSetCacheModel.layoutSetId = getLayoutSetId();

		layoutSetCacheModel.groupId = getGroupId();

		layoutSetCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			layoutSetCacheModel.createDate = createDate.getTime();
		}
		else {
			layoutSetCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			layoutSetCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			layoutSetCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		layoutSetCacheModel.privateLayout = isPrivateLayout();

		layoutSetCacheModel.logoId = getLogoId();

		layoutSetCacheModel.themeId = getThemeId();

		String themeId = layoutSetCacheModel.themeId;

		if ((themeId != null) && (themeId.length() == 0)) {
			layoutSetCacheModel.themeId = null;
		}

		layoutSetCacheModel.colorSchemeId = getColorSchemeId();

		String colorSchemeId = layoutSetCacheModel.colorSchemeId;

		if ((colorSchemeId != null) && (colorSchemeId.length() == 0)) {
			layoutSetCacheModel.colorSchemeId = null;
		}

		layoutSetCacheModel.faviconFileEntryId = getFaviconFileEntryId();

		layoutSetCacheModel.css = getCss();

		String css = layoutSetCacheModel.css;

		if ((css != null) && (css.length() == 0)) {
			layoutSetCacheModel.css = null;
		}

		layoutSetCacheModel.settings = getSettings();

		String settings = layoutSetCacheModel.settings;

		if ((settings != null) && (settings.length() == 0)) {
			layoutSetCacheModel.settings = null;
		}

		layoutSetCacheModel.layoutSetPrototypeUuid =
			getLayoutSetPrototypeUuid();

		String layoutSetPrototypeUuid =
			layoutSetCacheModel.layoutSetPrototypeUuid;

		if ((layoutSetPrototypeUuid != null) &&
			(layoutSetPrototypeUuid.length() == 0)) {

			layoutSetCacheModel.layoutSetPrototypeUuid = null;
		}

		layoutSetCacheModel.layoutSetPrototypeLinkEnabled =
			isLayoutSetPrototypeLinkEnabled();

		setCompanyFallbackVirtualHostname(null);

		layoutSetCacheModel._companyFallbackVirtualHostname =
			getCompanyFallbackVirtualHostname();

		setVirtualHostnames(null);

		layoutSetCacheModel._virtualHostnames = getVirtualHostnames();

		return layoutSetCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LayoutSet, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LayoutSet, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutSet, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((LayoutSet)this);

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

		private static final Function<InvocationHandler, LayoutSet>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					LayoutSet.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _layoutSetId;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private boolean _privateLayout;
	private long _logoId;
	private String _themeId;
	private String _colorSchemeId;
	private long _faviconFileEntryId;
	private String _css;
	private String _settings;
	private String _layoutSetPrototypeUuid;
	private boolean _layoutSetPrototypeLinkEnabled;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<LayoutSet, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((LayoutSet)this);
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
		_columnOriginalValues.put("layoutSetId", _layoutSetId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("privateLayout", _privateLayout);
		_columnOriginalValues.put("logoId", _logoId);
		_columnOriginalValues.put("themeId", _themeId);
		_columnOriginalValues.put("colorSchemeId", _colorSchemeId);
		_columnOriginalValues.put("faviconFileEntryId", _faviconFileEntryId);
		_columnOriginalValues.put("css", _css);
		_columnOriginalValues.put("settings_", _settings);
		_columnOriginalValues.put(
			"layoutSetPrototypeUuid", _layoutSetPrototypeUuid);
		_columnOriginalValues.put(
			"layoutSetPrototypeLinkEnabled", _layoutSetPrototypeLinkEnabled);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("settings_", "settings");

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

		columnBitmasks.put("layoutSetId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("privateLayout", 128L);

		columnBitmasks.put("logoId", 256L);

		columnBitmasks.put("themeId", 512L);

		columnBitmasks.put("colorSchemeId", 1024L);

		columnBitmasks.put("faviconFileEntryId", 2048L);

		columnBitmasks.put("css", 4096L);

		columnBitmasks.put("settings_", 8192L);

		columnBitmasks.put("layoutSetPrototypeUuid", 16384L);

		columnBitmasks.put("layoutSetPrototypeLinkEnabled", 32768L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private LayoutSet _escapedModel;

}