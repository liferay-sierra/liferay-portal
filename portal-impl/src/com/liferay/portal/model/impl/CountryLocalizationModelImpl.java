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
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.CountryLocalization;
import com.liferay.portal.kernel.model.CountryLocalizationModel;
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
 * The base model implementation for the CountryLocalization service. Represents a row in the &quot;CountryLocalization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CountryLocalizationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CountryLocalizationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CountryLocalizationImpl
 * @generated
 */
public class CountryLocalizationModelImpl
	extends BaseModelImpl<CountryLocalization>
	implements CountryLocalizationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a country localization model instance should use the <code>CountryLocalization</code> interface instead.
	 */
	public static final String TABLE_NAME = "CountryLocalization";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"countryLocalizationId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"countryId", Types.BIGINT},
		{"languageId", Types.VARCHAR}, {"title", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("countryLocalizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("countryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("languageId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CountryLocalization (mvccVersion LONG default 0 not null,countryLocalizationId LONG not null primary key,companyId LONG,countryId LONG,languageId VARCHAR(75) null,title VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table CountryLocalization";

	public static final String ORDER_BY_JPQL =
		" ORDER BY countryLocalization.countryLocalizationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CountryLocalization.countryLocalizationId ASC";

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
	public static final long COUNTRYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LANGUAGEID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COUNTRYLOCALIZATIONID_COLUMN_BITMASK = 4L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.CountryLocalization"));

	public CountryLocalizationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _countryLocalizationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCountryLocalizationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _countryLocalizationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CountryLocalization.class;
	}

	@Override
	public String getModelClassName() {
		return CountryLocalization.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CountryLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CountryLocalization, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CountryLocalization, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CountryLocalization)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CountryLocalization, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CountryLocalization, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CountryLocalization)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CountryLocalization, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CountryLocalization, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<CountryLocalization, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CountryLocalization, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CountryLocalization, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CountryLocalization, Object>>();
		Map<String, BiConsumer<CountryLocalization, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<CountryLocalization, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", CountryLocalization::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<CountryLocalization, Long>)
				CountryLocalization::setMvccVersion);
		attributeGetterFunctions.put(
			"countryLocalizationId",
			CountryLocalization::getCountryLocalizationId);
		attributeSetterBiConsumers.put(
			"countryLocalizationId",
			(BiConsumer<CountryLocalization, Long>)
				CountryLocalization::setCountryLocalizationId);
		attributeGetterFunctions.put(
			"companyId", CountryLocalization::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CountryLocalization, Long>)
				CountryLocalization::setCompanyId);
		attributeGetterFunctions.put(
			"countryId", CountryLocalization::getCountryId);
		attributeSetterBiConsumers.put(
			"countryId",
			(BiConsumer<CountryLocalization, Long>)
				CountryLocalization::setCountryId);
		attributeGetterFunctions.put(
			"languageId", CountryLocalization::getLanguageId);
		attributeSetterBiConsumers.put(
			"languageId",
			(BiConsumer<CountryLocalization, String>)
				CountryLocalization::setLanguageId);
		attributeGetterFunctions.put("title", CountryLocalization::getTitle);
		attributeSetterBiConsumers.put(
			"title",
			(BiConsumer<CountryLocalization, String>)
				CountryLocalization::setTitle);

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
	public long getCountryLocalizationId() {
		return _countryLocalizationId;
	}

	@Override
	public void setCountryLocalizationId(long countryLocalizationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_countryLocalizationId = countryLocalizationId;
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
	public long getCountryId() {
		return _countryId;
	}

	@Override
	public void setCountryId(long countryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_countryId = countryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCountryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("countryId"));
	}

	@Override
	public String getLanguageId() {
		if (_languageId == null) {
			return "";
		}
		else {
			return _languageId;
		}
	}

	@Override
	public void setLanguageId(String languageId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_languageId = languageId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalLanguageId() {
		return getColumnOriginalValue("languageId");
	}

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
	public void setTitle(String title) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_title = title;
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
			getCompanyId(), CountryLocalization.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CountryLocalization toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CountryLocalization>
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
		CountryLocalizationImpl countryLocalizationImpl =
			new CountryLocalizationImpl();

		countryLocalizationImpl.setMvccVersion(getMvccVersion());
		countryLocalizationImpl.setCountryLocalizationId(
			getCountryLocalizationId());
		countryLocalizationImpl.setCompanyId(getCompanyId());
		countryLocalizationImpl.setCountryId(getCountryId());
		countryLocalizationImpl.setLanguageId(getLanguageId());
		countryLocalizationImpl.setTitle(getTitle());

		countryLocalizationImpl.resetOriginalValues();

		return countryLocalizationImpl;
	}

	@Override
	public CountryLocalization cloneWithOriginalValues() {
		CountryLocalizationImpl countryLocalizationImpl =
			new CountryLocalizationImpl();

		countryLocalizationImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		countryLocalizationImpl.setCountryLocalizationId(
			this.<Long>getColumnOriginalValue("countryLocalizationId"));
		countryLocalizationImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		countryLocalizationImpl.setCountryId(
			this.<Long>getColumnOriginalValue("countryId"));
		countryLocalizationImpl.setLanguageId(
			this.<String>getColumnOriginalValue("languageId"));
		countryLocalizationImpl.setTitle(
			this.<String>getColumnOriginalValue("title"));

		return countryLocalizationImpl;
	}

	@Override
	public int compareTo(CountryLocalization countryLocalization) {
		long primaryKey = countryLocalization.getPrimaryKey();

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

		if (!(object instanceof CountryLocalization)) {
			return false;
		}

		CountryLocalization countryLocalization = (CountryLocalization)object;

		long primaryKey = countryLocalization.getPrimaryKey();

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

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CountryLocalization> toCacheModel() {
		CountryLocalizationCacheModel countryLocalizationCacheModel =
			new CountryLocalizationCacheModel();

		countryLocalizationCacheModel.mvccVersion = getMvccVersion();

		countryLocalizationCacheModel.countryLocalizationId =
			getCountryLocalizationId();

		countryLocalizationCacheModel.companyId = getCompanyId();

		countryLocalizationCacheModel.countryId = getCountryId();

		countryLocalizationCacheModel.languageId = getLanguageId();

		String languageId = countryLocalizationCacheModel.languageId;

		if ((languageId != null) && (languageId.length() == 0)) {
			countryLocalizationCacheModel.languageId = null;
		}

		countryLocalizationCacheModel.title = getTitle();

		String title = countryLocalizationCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			countryLocalizationCacheModel.title = null;
		}

		return countryLocalizationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CountryLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CountryLocalization, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CountryLocalization, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CountryLocalization)this);

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

		private static final Function<InvocationHandler, CountryLocalization>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CountryLocalization.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _countryLocalizationId;
	private long _companyId;
	private long _countryId;
	private String _languageId;
	private String _title;

	public <T> T getColumnValue(String columnName) {
		Function<CountryLocalization, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CountryLocalization)this);
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
		_columnOriginalValues.put(
			"countryLocalizationId", _countryLocalizationId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("countryId", _countryId);
		_columnOriginalValues.put("languageId", _languageId);
		_columnOriginalValues.put("title", _title);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("countryLocalizationId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("countryId", 8L);

		columnBitmasks.put("languageId", 16L);

		columnBitmasks.put("title", 32L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CountryLocalization _escapedModel;

}