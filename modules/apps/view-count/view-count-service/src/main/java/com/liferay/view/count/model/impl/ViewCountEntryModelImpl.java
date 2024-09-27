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

package com.liferay.view.count.model.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.view.count.model.ViewCountEntry;
import com.liferay.view.count.model.ViewCountEntryModel;
import com.liferay.view.count.service.persistence.ViewCountEntryPK;

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
 * The base model implementation for the ViewCountEntry service. Represents a row in the &quot;ViewCountEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ViewCountEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ViewCountEntryImpl}.
 * </p>
 *
 * @author Preston Crary
 * @see ViewCountEntryImpl
 * @generated
 */
public class ViewCountEntryModelImpl
	extends BaseModelImpl<ViewCountEntry> implements ViewCountEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a view count entry model instance should use the <code>ViewCountEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "ViewCountEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"companyId", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"viewCount", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("viewCount", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ViewCountEntry (companyId LONG not null,classNameId LONG not null,classPK LONG not null,viewCount LONG,primary key (companyId, classNameId, classPK))";

	public static final String TABLE_SQL_DROP = "drop table ViewCountEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY viewCountEntry.id.companyId ASC, viewCountEntry.id.classNameId ASC, viewCountEntry.id.classPK ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ViewCountEntry.companyId ASC, ViewCountEntry.classNameId ASC, ViewCountEntry.classPK ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSNAMEID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSPK_COLUMN_BITMASK = 4L;

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

	public ViewCountEntryModelImpl() {
	}

	@Override
	public ViewCountEntryPK getPrimaryKey() {
		return new ViewCountEntryPK(_companyId, _classNameId, _classPK);
	}

	@Override
	public void setPrimaryKey(ViewCountEntryPK primaryKey) {
		setCompanyId(primaryKey.companyId);
		setClassNameId(primaryKey.classNameId);
		setClassPK(primaryKey.classPK);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new ViewCountEntryPK(_companyId, _classNameId, _classPK);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((ViewCountEntryPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return ViewCountEntry.class;
	}

	@Override
	public String getModelClassName() {
		return ViewCountEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ViewCountEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ViewCountEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ViewCountEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ViewCountEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ViewCountEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ViewCountEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ViewCountEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ViewCountEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ViewCountEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<ViewCountEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ViewCountEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ViewCountEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ViewCountEntry, Object>>();
		Map<String, BiConsumer<ViewCountEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ViewCountEntry, ?>>();

		attributeGetterFunctions.put("companyId", ViewCountEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<ViewCountEntry, Long>)ViewCountEntry::setCompanyId);
		attributeGetterFunctions.put(
			"classNameId", ViewCountEntry::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<ViewCountEntry, Long>)ViewCountEntry::setClassNameId);
		attributeGetterFunctions.put("classPK", ViewCountEntry::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<ViewCountEntry, Long>)ViewCountEntry::setClassPK);
		attributeGetterFunctions.put("viewCount", ViewCountEntry::getViewCount);
		attributeSetterBiConsumers.put(
			"viewCount",
			(BiConsumer<ViewCountEntry, Long>)ViewCountEntry::setViewCount);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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

	@Override
	public long getViewCount() {
		return _viewCount;
	}

	@Override
	public void setViewCount(long viewCount) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_viewCount = viewCount;
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
	public ViewCountEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ViewCountEntry>
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
		ViewCountEntryImpl viewCountEntryImpl = new ViewCountEntryImpl();

		viewCountEntryImpl.setCompanyId(getCompanyId());
		viewCountEntryImpl.setClassNameId(getClassNameId());
		viewCountEntryImpl.setClassPK(getClassPK());
		viewCountEntryImpl.setViewCount(getViewCount());

		viewCountEntryImpl.resetOriginalValues();

		return viewCountEntryImpl;
	}

	@Override
	public ViewCountEntry cloneWithOriginalValues() {
		ViewCountEntryImpl viewCountEntryImpl = new ViewCountEntryImpl();

		viewCountEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		viewCountEntryImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		viewCountEntryImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));
		viewCountEntryImpl.setViewCount(
			this.<Long>getColumnOriginalValue("viewCount"));

		return viewCountEntryImpl;
	}

	@Override
	public int compareTo(ViewCountEntry viewCountEntry) {
		ViewCountEntryPK primaryKey = viewCountEntry.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ViewCountEntry)) {
			return false;
		}

		ViewCountEntry viewCountEntry = (ViewCountEntry)object;

		ViewCountEntryPK primaryKey = viewCountEntry.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
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
	public CacheModel<ViewCountEntry> toCacheModel() {
		ViewCountEntryCacheModel viewCountEntryCacheModel =
			new ViewCountEntryCacheModel();

		viewCountEntryCacheModel.viewCountEntryPK = getPrimaryKey();

		viewCountEntryCacheModel.companyId = getCompanyId();

		viewCountEntryCacheModel.classNameId = getClassNameId();

		viewCountEntryCacheModel.classPK = getClassPK();

		viewCountEntryCacheModel.viewCount = getViewCount();

		return viewCountEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ViewCountEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ViewCountEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ViewCountEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((ViewCountEntry)this);

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

		private static final Function<InvocationHandler, ViewCountEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					ViewCountEntry.class, ModelWrapper.class);

	}

	private long _companyId;
	private long _classNameId;
	private long _classPK;
	private long _viewCount;

	public <T> T getColumnValue(String columnName) {
		Function<ViewCountEntry, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((ViewCountEntry)this);
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

		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("viewCount", _viewCount);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("companyId", 1L);

		columnBitmasks.put("classNameId", 2L);

		columnBitmasks.put("classPK", 4L);

		columnBitmasks.put("viewCount", 8L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private ViewCountEntry _escapedModel;

}