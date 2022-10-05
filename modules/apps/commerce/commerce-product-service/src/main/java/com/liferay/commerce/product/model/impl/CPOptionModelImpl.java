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

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
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
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CPOption service. Represents a row in the &quot;CPOption&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CPOptionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CPOptionImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CPOptionImpl
 * @generated
 */
@JSON(strict = true)
public class CPOptionModelImpl
	extends BaseModelImpl<CPOption> implements CPOptionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp option model instance should use the <code>CPOption</code> interface instead.
	 */
	public static final String TABLE_NAME = "CPOption";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"externalReferenceCode", Types.VARCHAR},
		{"CPOptionId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"DDMFormFieldTypeName", Types.VARCHAR}, {"facetable", Types.BOOLEAN},
		{"required", Types.BOOLEAN}, {"skuContributor", Types.BOOLEAN},
		{"key_", Types.VARCHAR}, {"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CPOptionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("DDMFormFieldTypeName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("facetable", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("required", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("skuContributor", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("key_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CPOption (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,CPOptionId LONG not null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name STRING null,description STRING null,DDMFormFieldTypeName VARCHAR(75) null,facetable BOOLEAN,required BOOLEAN,skuContributor BOOLEAN,key_ VARCHAR(75) null,lastPublishDate DATE null,primary key (CPOptionId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table CPOption";

	public static final String ORDER_BY_JPQL = " ORDER BY cpOption.name ASC";

	public static final String ORDER_BY_SQL = " ORDER BY CPOption.name ASC";

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
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KEY_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 16L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.product.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.product.model.CPOption"));

	public CPOptionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CPOptionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCPOptionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CPOptionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CPOption.class;
	}

	@Override
	public String getModelClassName() {
		return CPOption.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CPOption, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CPOption, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPOption, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((CPOption)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CPOption, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CPOption, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CPOption)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CPOption, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CPOption, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<CPOption, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CPOption, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CPOption, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<CPOption, Object>>();
		Map<String, BiConsumer<CPOption, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<CPOption, ?>>();

		attributeGetterFunctions.put("mvccVersion", CPOption::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<CPOption, Long>)CPOption::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", CPOption::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<CPOption, Long>)CPOption::setCtCollectionId);
		attributeGetterFunctions.put("uuid", CPOption::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<CPOption, String>)CPOption::setUuid);
		attributeGetterFunctions.put(
			"externalReferenceCode", CPOption::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<CPOption, String>)CPOption::setExternalReferenceCode);
		attributeGetterFunctions.put("CPOptionId", CPOption::getCPOptionId);
		attributeSetterBiConsumers.put(
			"CPOptionId", (BiConsumer<CPOption, Long>)CPOption::setCPOptionId);
		attributeGetterFunctions.put("companyId", CPOption::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<CPOption, Long>)CPOption::setCompanyId);
		attributeGetterFunctions.put("userId", CPOption::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<CPOption, Long>)CPOption::setUserId);
		attributeGetterFunctions.put("userName", CPOption::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<CPOption, String>)CPOption::setUserName);
		attributeGetterFunctions.put("createDate", CPOption::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<CPOption, Date>)CPOption::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", CPOption::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CPOption, Date>)CPOption::setModifiedDate);
		attributeGetterFunctions.put("name", CPOption::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<CPOption, String>)CPOption::setName);
		attributeGetterFunctions.put("description", CPOption::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<CPOption, String>)CPOption::setDescription);
		attributeGetterFunctions.put(
			"DDMFormFieldTypeName", CPOption::getDDMFormFieldTypeName);
		attributeSetterBiConsumers.put(
			"DDMFormFieldTypeName",
			(BiConsumer<CPOption, String>)CPOption::setDDMFormFieldTypeName);
		attributeGetterFunctions.put("facetable", CPOption::getFacetable);
		attributeSetterBiConsumers.put(
			"facetable", (BiConsumer<CPOption, Boolean>)CPOption::setFacetable);
		attributeGetterFunctions.put("required", CPOption::getRequired);
		attributeSetterBiConsumers.put(
			"required", (BiConsumer<CPOption, Boolean>)CPOption::setRequired);
		attributeGetterFunctions.put(
			"skuContributor", CPOption::getSkuContributor);
		attributeSetterBiConsumers.put(
			"skuContributor",
			(BiConsumer<CPOption, Boolean>)CPOption::setSkuContributor);
		attributeGetterFunctions.put("key", CPOption::getKey);
		attributeSetterBiConsumers.put(
			"key", (BiConsumer<CPOption, String>)CPOption::setKey);
		attributeGetterFunctions.put(
			"lastPublishDate", CPOption::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<CPOption, Date>)CPOption::setLastPublishDate);

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
	public long getCPOptionId() {
		return _CPOptionId;
	}

	@Override
	public void setCPOptionId(long CPOptionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CPOptionId = CPOptionId;
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
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getName(), languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(
				LocalizationUtil.updateLocalization(
					getName(), "Name", name, languageId, defaultLanguageId));
		}
		else {
			setName(
				LocalizationUtil.removeLocalization(
					getName(), "Name", languageId));
		}
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getDefault());
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		setName(
			LocalizationUtil.updateLocalization(
				nameMap, getName(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
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
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getDescription(), languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@JSON
	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_description = description;
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setDescription(
		String description, Locale locale, Locale defaultLocale) {

		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(
				LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(
				LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getDefault());
	}

	@Override
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale) {

		if (descriptionMap == null) {
			return;
		}

		setDescription(
			LocalizationUtil.updateLocalization(
				descriptionMap, getDescription(), "Description",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getDDMFormFieldTypeName() {
		if (_DDMFormFieldTypeName == null) {
			return "";
		}
		else {
			return _DDMFormFieldTypeName;
		}
	}

	@Override
	public void setDDMFormFieldTypeName(String DDMFormFieldTypeName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_DDMFormFieldTypeName = DDMFormFieldTypeName;
	}

	@JSON
	@Override
	public boolean getFacetable() {
		return _facetable;
	}

	@JSON
	@Override
	public boolean isFacetable() {
		return _facetable;
	}

	@Override
	public void setFacetable(boolean facetable) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_facetable = facetable;
	}

	@JSON
	@Override
	public boolean getRequired() {
		return _required;
	}

	@JSON
	@Override
	public boolean isRequired() {
		return _required;
	}

	@Override
	public void setRequired(boolean required) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_required = required;
	}

	@JSON
	@Override
	public boolean getSkuContributor() {
		return _skuContributor;
	}

	@JSON
	@Override
	public boolean isSkuContributor() {
		return _skuContributor;
	}

	@Override
	public void setSkuContributor(boolean skuContributor) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_skuContributor = skuContributor;
	}

	@JSON
	@Override
	public String getKey() {
		if (_key == null) {
			return "";
		}
		else {
			return _key;
		}
	}

	@Override
	public void setKey(String key) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_key = key;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalKey() {
		return getColumnOriginalValue("key_");
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
			PortalUtil.getClassNameId(CPOption.class.getName()));
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
			getCompanyId(), CPOption.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(
			new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getName();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(
			getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(
			CPOption.class.getName(), getPrimaryKey(), defaultLocale,
			availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(
				getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(
				getDescription(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public CPOption toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CPOption>
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
		CPOptionImpl cpOptionImpl = new CPOptionImpl();

		cpOptionImpl.setMvccVersion(getMvccVersion());
		cpOptionImpl.setCtCollectionId(getCtCollectionId());
		cpOptionImpl.setUuid(getUuid());
		cpOptionImpl.setExternalReferenceCode(getExternalReferenceCode());
		cpOptionImpl.setCPOptionId(getCPOptionId());
		cpOptionImpl.setCompanyId(getCompanyId());
		cpOptionImpl.setUserId(getUserId());
		cpOptionImpl.setUserName(getUserName());
		cpOptionImpl.setCreateDate(getCreateDate());
		cpOptionImpl.setModifiedDate(getModifiedDate());
		cpOptionImpl.setName(getName());
		cpOptionImpl.setDescription(getDescription());
		cpOptionImpl.setDDMFormFieldTypeName(getDDMFormFieldTypeName());
		cpOptionImpl.setFacetable(isFacetable());
		cpOptionImpl.setRequired(isRequired());
		cpOptionImpl.setSkuContributor(isSkuContributor());
		cpOptionImpl.setKey(getKey());
		cpOptionImpl.setLastPublishDate(getLastPublishDate());

		cpOptionImpl.resetOriginalValues();

		return cpOptionImpl;
	}

	@Override
	public CPOption cloneWithOriginalValues() {
		CPOptionImpl cpOptionImpl = new CPOptionImpl();

		cpOptionImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		cpOptionImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		cpOptionImpl.setUuid(this.<String>getColumnOriginalValue("uuid_"));
		cpOptionImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		cpOptionImpl.setCPOptionId(
			this.<Long>getColumnOriginalValue("CPOptionId"));
		cpOptionImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		cpOptionImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		cpOptionImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		cpOptionImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		cpOptionImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		cpOptionImpl.setName(this.<String>getColumnOriginalValue("name"));
		cpOptionImpl.setDescription(
			this.<String>getColumnOriginalValue("description"));
		cpOptionImpl.setDDMFormFieldTypeName(
			this.<String>getColumnOriginalValue("DDMFormFieldTypeName"));
		cpOptionImpl.setFacetable(
			this.<Boolean>getColumnOriginalValue("facetable"));
		cpOptionImpl.setRequired(
			this.<Boolean>getColumnOriginalValue("required"));
		cpOptionImpl.setSkuContributor(
			this.<Boolean>getColumnOriginalValue("skuContributor"));
		cpOptionImpl.setKey(this.<String>getColumnOriginalValue("key_"));
		cpOptionImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return cpOptionImpl;
	}

	@Override
	public int compareTo(CPOption cpOption) {
		int value = 0;

		value = getName().compareTo(cpOption.getName());

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

		if (!(object instanceof CPOption)) {
			return false;
		}

		CPOption cpOption = (CPOption)object;

		long primaryKey = cpOption.getPrimaryKey();

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

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CPOption> toCacheModel() {
		CPOptionCacheModel cpOptionCacheModel = new CPOptionCacheModel();

		cpOptionCacheModel.mvccVersion = getMvccVersion();

		cpOptionCacheModel.ctCollectionId = getCtCollectionId();

		cpOptionCacheModel.uuid = getUuid();

		String uuid = cpOptionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			cpOptionCacheModel.uuid = null;
		}

		cpOptionCacheModel.externalReferenceCode = getExternalReferenceCode();

		String externalReferenceCode = cpOptionCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			cpOptionCacheModel.externalReferenceCode = null;
		}

		cpOptionCacheModel.CPOptionId = getCPOptionId();

		cpOptionCacheModel.companyId = getCompanyId();

		cpOptionCacheModel.userId = getUserId();

		cpOptionCacheModel.userName = getUserName();

		String userName = cpOptionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			cpOptionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			cpOptionCacheModel.createDate = createDate.getTime();
		}
		else {
			cpOptionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			cpOptionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			cpOptionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		cpOptionCacheModel.name = getName();

		String name = cpOptionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			cpOptionCacheModel.name = null;
		}

		cpOptionCacheModel.description = getDescription();

		String description = cpOptionCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			cpOptionCacheModel.description = null;
		}

		cpOptionCacheModel.DDMFormFieldTypeName = getDDMFormFieldTypeName();

		String DDMFormFieldTypeName = cpOptionCacheModel.DDMFormFieldTypeName;

		if ((DDMFormFieldTypeName != null) &&
			(DDMFormFieldTypeName.length() == 0)) {

			cpOptionCacheModel.DDMFormFieldTypeName = null;
		}

		cpOptionCacheModel.facetable = isFacetable();

		cpOptionCacheModel.required = isRequired();

		cpOptionCacheModel.skuContributor = isSkuContributor();

		cpOptionCacheModel.key = getKey();

		String key = cpOptionCacheModel.key;

		if ((key != null) && (key.length() == 0)) {
			cpOptionCacheModel.key = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			cpOptionCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			cpOptionCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return cpOptionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CPOption, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CPOption, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPOption, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((CPOption)this);

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

		private static final Function<InvocationHandler, CPOption>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CPOption.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private String _externalReferenceCode;
	private long _CPOptionId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _DDMFormFieldTypeName;
	private boolean _facetable;
	private boolean _required;
	private boolean _skuContributor;
	private String _key;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CPOption, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CPOption)this);
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
		_columnOriginalValues.put("CPOptionId", _CPOptionId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("description", _description);
		_columnOriginalValues.put(
			"DDMFormFieldTypeName", _DDMFormFieldTypeName);
		_columnOriginalValues.put("facetable", _facetable);
		_columnOriginalValues.put("required", _required);
		_columnOriginalValues.put("skuContributor", _skuContributor);
		_columnOriginalValues.put("key_", _key);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("key_", "key");

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

		columnBitmasks.put("CPOptionId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("modifiedDate", 512L);

		columnBitmasks.put("name", 1024L);

		columnBitmasks.put("description", 2048L);

		columnBitmasks.put("DDMFormFieldTypeName", 4096L);

		columnBitmasks.put("facetable", 8192L);

		columnBitmasks.put("required", 16384L);

		columnBitmasks.put("skuContributor", 32768L);

		columnBitmasks.put("key_", 65536L);

		columnBitmasks.put("lastPublishDate", 131072L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CPOption _escapedModel;

}