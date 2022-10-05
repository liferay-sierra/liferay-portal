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

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceAvailabilityEstimate;
import com.liferay.commerce.model.CommerceAvailabilityEstimateModel;
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
 * The base model implementation for the CommerceAvailabilityEstimate service. Represents a row in the &quot;CommerceAvailabilityEstimate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceAvailabilityEstimateModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceAvailabilityEstimateImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceAvailabilityEstimateImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceAvailabilityEstimateModelImpl
	extends BaseModelImpl<CommerceAvailabilityEstimate>
	implements CommerceAvailabilityEstimateModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce availability estimate model instance should use the <code>CommerceAvailabilityEstimate</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceAvailabilityEstimate";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"commerceAvailabilityEstimateId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"title", Types.VARCHAR},
		{"priority", Types.DOUBLE}, {"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceAvailabilityEstimateId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("priority", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceAvailabilityEstimate (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,commerceAvailabilityEstimateId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,title STRING null,priority DOUBLE,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceAvailabilityEstimate";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceAvailabilityEstimate.title ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceAvailabilityEstimate.title ASC";

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
	public static final long UUID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TITLE_COLUMN_BITMASK = 4L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.model.CommerceAvailabilityEstimate"));

	public CommerceAvailabilityEstimateModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceAvailabilityEstimateId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceAvailabilityEstimateId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceAvailabilityEstimateId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceAvailabilityEstimate.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceAvailabilityEstimate.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceAvailabilityEstimate, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceAvailabilityEstimate, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAvailabilityEstimate, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceAvailabilityEstimate)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceAvailabilityEstimate, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceAvailabilityEstimate, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceAvailabilityEstimate)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceAvailabilityEstimate, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceAvailabilityEstimate, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map
		<String, Function<CommerceAvailabilityEstimate, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceAvailabilityEstimate, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceAvailabilityEstimate, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommerceAvailabilityEstimate, Object>>();
		Map<String, BiConsumer<CommerceAvailabilityEstimate, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceAvailabilityEstimate, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", CommerceAvailabilityEstimate::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<CommerceAvailabilityEstimate, Long>)
				CommerceAvailabilityEstimate::setMvccVersion);
		attributeGetterFunctions.put(
			"uuid", CommerceAvailabilityEstimate::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<CommerceAvailabilityEstimate, String>)
				CommerceAvailabilityEstimate::setUuid);
		attributeGetterFunctions.put(
			"commerceAvailabilityEstimateId",
			CommerceAvailabilityEstimate::getCommerceAvailabilityEstimateId);
		attributeSetterBiConsumers.put(
			"commerceAvailabilityEstimateId",
			(BiConsumer<CommerceAvailabilityEstimate, Long>)
				CommerceAvailabilityEstimate::
					setCommerceAvailabilityEstimateId);
		attributeGetterFunctions.put(
			"companyId", CommerceAvailabilityEstimate::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceAvailabilityEstimate, Long>)
				CommerceAvailabilityEstimate::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommerceAvailabilityEstimate::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceAvailabilityEstimate, Long>)
				CommerceAvailabilityEstimate::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceAvailabilityEstimate::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceAvailabilityEstimate, String>)
				CommerceAvailabilityEstimate::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceAvailabilityEstimate::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceAvailabilityEstimate, Date>)
				CommerceAvailabilityEstimate::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceAvailabilityEstimate::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceAvailabilityEstimate, Date>)
				CommerceAvailabilityEstimate::setModifiedDate);
		attributeGetterFunctions.put(
			"title", CommerceAvailabilityEstimate::getTitle);
		attributeSetterBiConsumers.put(
			"title",
			(BiConsumer<CommerceAvailabilityEstimate, String>)
				CommerceAvailabilityEstimate::setTitle);
		attributeGetterFunctions.put(
			"priority", CommerceAvailabilityEstimate::getPriority);
		attributeSetterBiConsumers.put(
			"priority",
			(BiConsumer<CommerceAvailabilityEstimate, Double>)
				CommerceAvailabilityEstimate::setPriority);
		attributeGetterFunctions.put(
			"lastPublishDate",
			CommerceAvailabilityEstimate::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<CommerceAvailabilityEstimate, Date>)
				CommerceAvailabilityEstimate::setLastPublishDate);

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
	public long getCommerceAvailabilityEstimateId() {
		return _commerceAvailabilityEstimateId;
	}

	@Override
	public void setCommerceAvailabilityEstimateId(
		long commerceAvailabilityEstimateId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceAvailabilityEstimateId = commerceAvailabilityEstimateId;
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
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public String getTitle(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId);
	}

	@Override
	public String getTitle(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId, useDefault);
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getTitle(), languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _titleCurrentLanguageId;
	}

	@JSON
	@Override
	public String getTitleCurrentValue() {
		Locale locale = getLocale(_titleCurrentLanguageId);

		return getTitle(locale);
	}

	@Override
	public Map<Locale, String> getTitleMap() {
		return LocalizationUtil.getLocalizationMap(getTitle());
	}

	@Override
	public void setTitle(String title) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_title = title;
	}

	@Override
	public void setTitle(String title, Locale locale) {
		setTitle(title, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setTitle(String title, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(title)) {
			setTitle(
				LocalizationUtil.updateLocalization(
					getTitle(), "Title", title, languageId, defaultLanguageId));
		}
		else {
			setTitle(
				LocalizationUtil.removeLocalization(
					getTitle(), "Title", languageId));
		}
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_titleCurrentLanguageId = languageId;
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap) {
		setTitleMap(titleMap, LocaleUtil.getDefault());
	}

	@Override
	public void setTitleMap(
		Map<Locale, String> titleMap, Locale defaultLocale) {

		if (titleMap == null) {
			return;
		}

		setTitle(
			LocalizationUtil.updateLocalization(
				titleMap, getTitle(), "Title",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public double getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(double priority) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_priority = priority;
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
			PortalUtil.getClassNameId(
				CommerceAvailabilityEstimate.class.getName()));
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
			getCompanyId(), CommerceAvailabilityEstimate.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> titleMap = getTitleMap();

		for (Map.Entry<Locale, String> entry : titleMap.entrySet()) {
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
		String xml = getTitle();

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
			CommerceAvailabilityEstimate.class.getName(), getPrimaryKey(),
			defaultLocale, availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String title = getTitle(defaultLocale);

		if (Validator.isNull(title)) {
			setTitle(getTitle(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setTitle(getTitle(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public CommerceAvailabilityEstimate toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceAvailabilityEstimate>
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
		CommerceAvailabilityEstimateImpl commerceAvailabilityEstimateImpl =
			new CommerceAvailabilityEstimateImpl();

		commerceAvailabilityEstimateImpl.setMvccVersion(getMvccVersion());
		commerceAvailabilityEstimateImpl.setUuid(getUuid());
		commerceAvailabilityEstimateImpl.setCommerceAvailabilityEstimateId(
			getCommerceAvailabilityEstimateId());
		commerceAvailabilityEstimateImpl.setCompanyId(getCompanyId());
		commerceAvailabilityEstimateImpl.setUserId(getUserId());
		commerceAvailabilityEstimateImpl.setUserName(getUserName());
		commerceAvailabilityEstimateImpl.setCreateDate(getCreateDate());
		commerceAvailabilityEstimateImpl.setModifiedDate(getModifiedDate());
		commerceAvailabilityEstimateImpl.setTitle(getTitle());
		commerceAvailabilityEstimateImpl.setPriority(getPriority());
		commerceAvailabilityEstimateImpl.setLastPublishDate(
			getLastPublishDate());

		commerceAvailabilityEstimateImpl.resetOriginalValues();

		return commerceAvailabilityEstimateImpl;
	}

	@Override
	public CommerceAvailabilityEstimate cloneWithOriginalValues() {
		CommerceAvailabilityEstimateImpl commerceAvailabilityEstimateImpl =
			new CommerceAvailabilityEstimateImpl();

		commerceAvailabilityEstimateImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commerceAvailabilityEstimateImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		commerceAvailabilityEstimateImpl.setCommerceAvailabilityEstimateId(
			this.<Long>getColumnOriginalValue(
				"commerceAvailabilityEstimateId"));
		commerceAvailabilityEstimateImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commerceAvailabilityEstimateImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commerceAvailabilityEstimateImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commerceAvailabilityEstimateImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commerceAvailabilityEstimateImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commerceAvailabilityEstimateImpl.setTitle(
			this.<String>getColumnOriginalValue("title"));
		commerceAvailabilityEstimateImpl.setPriority(
			this.<Double>getColumnOriginalValue("priority"));
		commerceAvailabilityEstimateImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return commerceAvailabilityEstimateImpl;
	}

	@Override
	public int compareTo(
		CommerceAvailabilityEstimate commerceAvailabilityEstimate) {

		int value = 0;

		value = getTitle().compareTo(commerceAvailabilityEstimate.getTitle());

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

		if (!(object instanceof CommerceAvailabilityEstimate)) {
			return false;
		}

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			(CommerceAvailabilityEstimate)object;

		long primaryKey = commerceAvailabilityEstimate.getPrimaryKey();

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
	public CacheModel<CommerceAvailabilityEstimate> toCacheModel() {
		CommerceAvailabilityEstimateCacheModel
			commerceAvailabilityEstimateCacheModel =
				new CommerceAvailabilityEstimateCacheModel();

		commerceAvailabilityEstimateCacheModel.mvccVersion = getMvccVersion();

		commerceAvailabilityEstimateCacheModel.uuid = getUuid();

		String uuid = commerceAvailabilityEstimateCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceAvailabilityEstimateCacheModel.uuid = null;
		}

		commerceAvailabilityEstimateCacheModel.commerceAvailabilityEstimateId =
			getCommerceAvailabilityEstimateId();

		commerceAvailabilityEstimateCacheModel.companyId = getCompanyId();

		commerceAvailabilityEstimateCacheModel.userId = getUserId();

		commerceAvailabilityEstimateCacheModel.userName = getUserName();

		String userName = commerceAvailabilityEstimateCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceAvailabilityEstimateCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceAvailabilityEstimateCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceAvailabilityEstimateCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceAvailabilityEstimateCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceAvailabilityEstimateCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commerceAvailabilityEstimateCacheModel.title = getTitle();

		String title = commerceAvailabilityEstimateCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			commerceAvailabilityEstimateCacheModel.title = null;
		}

		commerceAvailabilityEstimateCacheModel.priority = getPriority();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			commerceAvailabilityEstimateCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			commerceAvailabilityEstimateCacheModel.lastPublishDate =
				Long.MIN_VALUE;
		}

		return commerceAvailabilityEstimateCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceAvailabilityEstimate, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceAvailabilityEstimate, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAvailabilityEstimate, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommerceAvailabilityEstimate)this);

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
			<InvocationHandler, CommerceAvailabilityEstimate>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						CommerceAvailabilityEstimate.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _uuid;
	private long _commerceAvailabilityEstimateId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _title;
	private String _titleCurrentLanguageId;
	private double _priority;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceAvailabilityEstimate, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceAvailabilityEstimate)this);
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
			"commerceAvailabilityEstimateId", _commerceAvailabilityEstimateId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("title", _title);
		_columnOriginalValues.put("priority", _priority);
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

		columnBitmasks.put("commerceAvailabilityEstimateId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("title", 256L);

		columnBitmasks.put("priority", 512L);

		columnBitmasks.put("lastPublishDate", 1024L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceAvailabilityEstimate _escapedModel;

}