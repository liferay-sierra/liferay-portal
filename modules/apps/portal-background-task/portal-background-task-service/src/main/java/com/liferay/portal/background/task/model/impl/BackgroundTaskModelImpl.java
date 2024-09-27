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

package com.liferay.portal.background.task.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.background.task.model.BackgroundTask;
import com.liferay.portal.background.task.model.BackgroundTaskModel;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
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
 * The base model implementation for the BackgroundTask service. Represents a row in the &quot;BackgroundTask&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>BackgroundTaskModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BackgroundTaskImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BackgroundTaskImpl
 * @generated
 */
@JSON(strict = true)
public class BackgroundTaskModelImpl
	extends BaseModelImpl<BackgroundTask> implements BackgroundTaskModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a background task model instance should use the <code>BackgroundTask</code> interface instead.
	 */
	public static final String TABLE_NAME = "BackgroundTask";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"backgroundTaskId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"servletContextNames", Types.VARCHAR},
		{"taskExecutorClassName", Types.VARCHAR},
		{"taskContextMap", Types.CLOB}, {"completed", Types.BOOLEAN},
		{"completionDate", Types.TIMESTAMP}, {"status", Types.INTEGER},
		{"statusMessage", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("backgroundTaskId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("servletContextNames", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("taskExecutorClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("taskContextMap", Types.CLOB);
		TABLE_COLUMNS_MAP.put("completed", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("completionDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusMessage", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE =
		"create table BackgroundTask (mvccVersion LONG default 0 not null,backgroundTaskId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(255) null,servletContextNames VARCHAR(255) null,taskExecutorClassName VARCHAR(200) null,taskContextMap TEXT null,completed BOOLEAN,completionDate DATE null,status INTEGER,statusMessage TEXT null)";

	public static final String TABLE_SQL_DROP = "drop table BackgroundTask";

	public static final String ORDER_BY_JPQL =
		" ORDER BY backgroundTask.createDate ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY BackgroundTask.createDate ASC";

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
	public static final long COMPLETED_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STATUS_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TASKEXECUTORCLASSNAME_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 64L;

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

	public BackgroundTaskModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _backgroundTaskId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBackgroundTaskId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _backgroundTaskId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return BackgroundTask.class;
	}

	@Override
	public String getModelClassName() {
		return BackgroundTask.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<BackgroundTask, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<BackgroundTask, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BackgroundTask, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((BackgroundTask)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<BackgroundTask, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<BackgroundTask, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(BackgroundTask)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<BackgroundTask, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<BackgroundTask, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<BackgroundTask, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<BackgroundTask, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<BackgroundTask, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<BackgroundTask, Object>>();
		Map<String, BiConsumer<BackgroundTask, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<BackgroundTask, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", BackgroundTask::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<BackgroundTask, Long>)BackgroundTask::setMvccVersion);
		attributeGetterFunctions.put(
			"backgroundTaskId", BackgroundTask::getBackgroundTaskId);
		attributeSetterBiConsumers.put(
			"backgroundTaskId",
			(BiConsumer<BackgroundTask, Long>)
				BackgroundTask::setBackgroundTaskId);
		attributeGetterFunctions.put("groupId", BackgroundTask::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<BackgroundTask, Long>)BackgroundTask::setGroupId);
		attributeGetterFunctions.put("companyId", BackgroundTask::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<BackgroundTask, Long>)BackgroundTask::setCompanyId);
		attributeGetterFunctions.put("userId", BackgroundTask::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<BackgroundTask, Long>)BackgroundTask::setUserId);
		attributeGetterFunctions.put("userName", BackgroundTask::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<BackgroundTask, String>)BackgroundTask::setUserName);
		attributeGetterFunctions.put(
			"createDate", BackgroundTask::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<BackgroundTask, Date>)BackgroundTask::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", BackgroundTask::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<BackgroundTask, Date>)BackgroundTask::setModifiedDate);
		attributeGetterFunctions.put("name", BackgroundTask::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<BackgroundTask, String>)BackgroundTask::setName);
		attributeGetterFunctions.put(
			"servletContextNames", BackgroundTask::getServletContextNames);
		attributeSetterBiConsumers.put(
			"servletContextNames",
			(BiConsumer<BackgroundTask, String>)
				BackgroundTask::setServletContextNames);
		attributeGetterFunctions.put(
			"taskExecutorClassName", BackgroundTask::getTaskExecutorClassName);
		attributeSetterBiConsumers.put(
			"taskExecutorClassName",
			(BiConsumer<BackgroundTask, String>)
				BackgroundTask::setTaskExecutorClassName);
		attributeGetterFunctions.put(
			"taskContextMap", BackgroundTask::getTaskContextMap);
		attributeSetterBiConsumers.put(
			"taskContextMap",
			(BiConsumer<BackgroundTask, Map<String, Serializable>>)
				BackgroundTask::setTaskContextMap);
		attributeGetterFunctions.put("completed", BackgroundTask::getCompleted);
		attributeSetterBiConsumers.put(
			"completed",
			(BiConsumer<BackgroundTask, Boolean>)BackgroundTask::setCompleted);
		attributeGetterFunctions.put(
			"completionDate", BackgroundTask::getCompletionDate);
		attributeSetterBiConsumers.put(
			"completionDate",
			(BiConsumer<BackgroundTask, Date>)
				BackgroundTask::setCompletionDate);
		attributeGetterFunctions.put("status", BackgroundTask::getStatus);
		attributeSetterBiConsumers.put(
			"status",
			(BiConsumer<BackgroundTask, Integer>)BackgroundTask::setStatus);
		attributeGetterFunctions.put(
			"statusMessage", BackgroundTask::getStatusMessage);
		attributeSetterBiConsumers.put(
			"statusMessage",
			(BiConsumer<BackgroundTask, String>)
				BackgroundTask::setStatusMessage);

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
	public long getBackgroundTaskId() {
		return _backgroundTaskId;
	}

	@Override
	public void setBackgroundTaskId(long backgroundTaskId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_backgroundTaskId = backgroundTaskId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalName() {
		return getColumnOriginalValue("name");
	}

	@JSON
	@Override
	public String getServletContextNames() {
		if (_servletContextNames == null) {
			return "";
		}
		else {
			return _servletContextNames;
		}
	}

	@Override
	public void setServletContextNames(String servletContextNames) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_servletContextNames = servletContextNames;
	}

	@JSON
	@Override
	public String getTaskExecutorClassName() {
		if (_taskExecutorClassName == null) {
			return "";
		}
		else {
			return _taskExecutorClassName;
		}
	}

	@Override
	public void setTaskExecutorClassName(String taskExecutorClassName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_taskExecutorClassName = taskExecutorClassName;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalTaskExecutorClassName() {
		return getColumnOriginalValue("taskExecutorClassName");
	}

	@JSON
	@Override
	public Map<String, Serializable> getTaskContextMap() {
		return _taskContextMap;
	}

	@Override
	public void setTaskContextMap(Map<String, Serializable> taskContextMap) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_taskContextMap = taskContextMap;
	}

	@JSON
	@Override
	public boolean getCompleted() {
		return _completed;
	}

	@JSON
	@Override
	public boolean isCompleted() {
		return _completed;
	}

	@Override
	public void setCompleted(boolean completed) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_completed = completed;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalCompleted() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("completed"));
	}

	@JSON
	@Override
	public Date getCompletionDate() {
		return _completionDate;
	}

	@Override
	public void setCompletionDate(Date completionDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_completionDate = completionDate;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_status = status;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalStatus() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("status"));
	}

	@JSON
	@Override
	public String getStatusMessage() {
		if (_statusMessage == null) {
			return "";
		}
		else {
			return _statusMessage;
		}
	}

	@Override
	public void setStatusMessage(String statusMessage) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_statusMessage = statusMessage;
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
			getCompanyId(), BackgroundTask.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public BackgroundTask toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, BackgroundTask>
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
		BackgroundTaskImpl backgroundTaskImpl = new BackgroundTaskImpl();

		backgroundTaskImpl.setMvccVersion(getMvccVersion());
		backgroundTaskImpl.setBackgroundTaskId(getBackgroundTaskId());
		backgroundTaskImpl.setGroupId(getGroupId());
		backgroundTaskImpl.setCompanyId(getCompanyId());
		backgroundTaskImpl.setUserId(getUserId());
		backgroundTaskImpl.setUserName(getUserName());
		backgroundTaskImpl.setCreateDate(getCreateDate());
		backgroundTaskImpl.setModifiedDate(getModifiedDate());
		backgroundTaskImpl.setName(getName());
		backgroundTaskImpl.setServletContextNames(getServletContextNames());
		backgroundTaskImpl.setTaskExecutorClassName(getTaskExecutorClassName());
		backgroundTaskImpl.setTaskContextMap(getTaskContextMap());
		backgroundTaskImpl.setCompleted(isCompleted());
		backgroundTaskImpl.setCompletionDate(getCompletionDate());
		backgroundTaskImpl.setStatus(getStatus());
		backgroundTaskImpl.setStatusMessage(getStatusMessage());

		backgroundTaskImpl.resetOriginalValues();

		return backgroundTaskImpl;
	}

	@Override
	public BackgroundTask cloneWithOriginalValues() {
		BackgroundTaskImpl backgroundTaskImpl = new BackgroundTaskImpl();

		backgroundTaskImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		backgroundTaskImpl.setBackgroundTaskId(
			this.<Long>getColumnOriginalValue("backgroundTaskId"));
		backgroundTaskImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		backgroundTaskImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		backgroundTaskImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		backgroundTaskImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		backgroundTaskImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		backgroundTaskImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		backgroundTaskImpl.setName(this.<String>getColumnOriginalValue("name"));
		backgroundTaskImpl.setServletContextNames(
			this.<String>getColumnOriginalValue("servletContextNames"));
		backgroundTaskImpl.setTaskExecutorClassName(
			this.<String>getColumnOriginalValue("taskExecutorClassName"));
		backgroundTaskImpl.setTaskContextMap(
			this.<Map>getColumnOriginalValue("taskContextMap"));
		backgroundTaskImpl.setCompleted(
			this.<Boolean>getColumnOriginalValue("completed"));
		backgroundTaskImpl.setCompletionDate(
			this.<Date>getColumnOriginalValue("completionDate"));
		backgroundTaskImpl.setStatus(
			this.<Integer>getColumnOriginalValue("status"));
		backgroundTaskImpl.setStatusMessage(
			this.<String>getColumnOriginalValue("statusMessage"));

		return backgroundTaskImpl;
	}

	@Override
	public int compareTo(BackgroundTask backgroundTask) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), backgroundTask.getCreateDate());

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

		if (!(object instanceof BackgroundTask)) {
			return false;
		}

		BackgroundTask backgroundTask = (BackgroundTask)object;

		long primaryKey = backgroundTask.getPrimaryKey();

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
	public CacheModel<BackgroundTask> toCacheModel() {
		BackgroundTaskCacheModel backgroundTaskCacheModel =
			new BackgroundTaskCacheModel();

		backgroundTaskCacheModel.mvccVersion = getMvccVersion();

		backgroundTaskCacheModel.backgroundTaskId = getBackgroundTaskId();

		backgroundTaskCacheModel.groupId = getGroupId();

		backgroundTaskCacheModel.companyId = getCompanyId();

		backgroundTaskCacheModel.userId = getUserId();

		backgroundTaskCacheModel.userName = getUserName();

		String userName = backgroundTaskCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			backgroundTaskCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			backgroundTaskCacheModel.createDate = createDate.getTime();
		}
		else {
			backgroundTaskCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			backgroundTaskCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			backgroundTaskCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		backgroundTaskCacheModel.name = getName();

		String name = backgroundTaskCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			backgroundTaskCacheModel.name = null;
		}

		backgroundTaskCacheModel.servletContextNames = getServletContextNames();

		String servletContextNames =
			backgroundTaskCacheModel.servletContextNames;

		if ((servletContextNames != null) &&
			(servletContextNames.length() == 0)) {

			backgroundTaskCacheModel.servletContextNames = null;
		}

		backgroundTaskCacheModel.taskExecutorClassName =
			getTaskExecutorClassName();

		String taskExecutorClassName =
			backgroundTaskCacheModel.taskExecutorClassName;

		if ((taskExecutorClassName != null) &&
			(taskExecutorClassName.length() == 0)) {

			backgroundTaskCacheModel.taskExecutorClassName = null;
		}

		backgroundTaskCacheModel.taskContextMap = getTaskContextMap();

		backgroundTaskCacheModel.completed = isCompleted();

		Date completionDate = getCompletionDate();

		if (completionDate != null) {
			backgroundTaskCacheModel.completionDate = completionDate.getTime();
		}
		else {
			backgroundTaskCacheModel.completionDate = Long.MIN_VALUE;
		}

		backgroundTaskCacheModel.status = getStatus();

		backgroundTaskCacheModel.statusMessage = getStatusMessage();

		String statusMessage = backgroundTaskCacheModel.statusMessage;

		if ((statusMessage != null) && (statusMessage.length() == 0)) {
			backgroundTaskCacheModel.statusMessage = null;
		}

		return backgroundTaskCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<BackgroundTask, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<BackgroundTask, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BackgroundTask, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((BackgroundTask)this);

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

		private static final Function<InvocationHandler, BackgroundTask>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					BackgroundTask.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _backgroundTaskId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _servletContextNames;
	private String _taskExecutorClassName;
	private Map<String, Serializable> _taskContextMap;
	private boolean _completed;
	private Date _completionDate;
	private int _status;
	private String _statusMessage;

	public <T> T getColumnValue(String columnName) {
		Function<BackgroundTask, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((BackgroundTask)this);
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
		_columnOriginalValues.put("backgroundTaskId", _backgroundTaskId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("servletContextNames", _servletContextNames);
		_columnOriginalValues.put(
			"taskExecutorClassName", _taskExecutorClassName);
		_columnOriginalValues.put("taskContextMap", _taskContextMap);
		_columnOriginalValues.put("completed", _completed);
		_columnOriginalValues.put("completionDate", _completionDate);
		_columnOriginalValues.put("status", _status);
		_columnOriginalValues.put("statusMessage", _statusMessage);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("backgroundTaskId", 2L);

		columnBitmasks.put("groupId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("name", 256L);

		columnBitmasks.put("servletContextNames", 512L);

		columnBitmasks.put("taskExecutorClassName", 1024L);

		columnBitmasks.put("taskContextMap", 2048L);

		columnBitmasks.put("completed", 4096L);

		columnBitmasks.put("completionDate", 8192L);

		columnBitmasks.put("status", 16384L);

		columnBitmasks.put("statusMessage", 32768L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private BackgroundTask _escapedModel;

}