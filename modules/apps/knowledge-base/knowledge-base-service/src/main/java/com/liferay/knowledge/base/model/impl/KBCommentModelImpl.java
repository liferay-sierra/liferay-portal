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

package com.liferay.knowledge.base.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.knowledge.base.model.KBComment;
import com.liferay.knowledge.base.model.KBCommentModel;
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
import com.liferay.portal.kernel.util.DateUtil;
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
 * The base model implementation for the KBComment service. Represents a row in the &quot;KBComment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>KBCommentModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KBCommentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBCommentImpl
 * @generated
 */
@JSON(strict = true)
public class KBCommentModelImpl
	extends BaseModelImpl<KBComment> implements KBCommentModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kb comment model instance should use the <code>KBComment</code> interface instead.
	 */
	public static final String TABLE_NAME = "KBComment";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"kbCommentId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"content", Types.VARCHAR},
		{"userRating", Types.INTEGER}, {"lastPublishDate", Types.TIMESTAMP},
		{"status", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("kbCommentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("content", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userRating", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table KBComment (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,kbCommentId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,content STRING null,userRating INTEGER,lastPublishDate DATE null,status INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table KBComment";

	public static final String ORDER_BY_JPQL =
		" ORDER BY kbComment.modifiedDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY KBComment.modifiedDate DESC";

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
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STATUS_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 32L;

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
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 128L;

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

	public KBCommentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kbCommentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKbCommentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kbCommentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KBComment.class;
	}

	@Override
	public String getModelClassName() {
		return KBComment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<KBComment, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<KBComment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KBComment, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((KBComment)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<KBComment, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<KBComment, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(KBComment)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<KBComment, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<KBComment, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<KBComment, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<KBComment, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<KBComment, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<KBComment, Object>>();
		Map<String, BiConsumer<KBComment, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<KBComment, ?>>();

		attributeGetterFunctions.put("mvccVersion", KBComment::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<KBComment, Long>)KBComment::setMvccVersion);
		attributeGetterFunctions.put("uuid", KBComment::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<KBComment, String>)KBComment::setUuid);
		attributeGetterFunctions.put("kbCommentId", KBComment::getKbCommentId);
		attributeSetterBiConsumers.put(
			"kbCommentId",
			(BiConsumer<KBComment, Long>)KBComment::setKbCommentId);
		attributeGetterFunctions.put("groupId", KBComment::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<KBComment, Long>)KBComment::setGroupId);
		attributeGetterFunctions.put("companyId", KBComment::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<KBComment, Long>)KBComment::setCompanyId);
		attributeGetterFunctions.put("userId", KBComment::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<KBComment, Long>)KBComment::setUserId);
		attributeGetterFunctions.put("userName", KBComment::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<KBComment, String>)KBComment::setUserName);
		attributeGetterFunctions.put("createDate", KBComment::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<KBComment, Date>)KBComment::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", KBComment::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<KBComment, Date>)KBComment::setModifiedDate);
		attributeGetterFunctions.put("classNameId", KBComment::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<KBComment, Long>)KBComment::setClassNameId);
		attributeGetterFunctions.put("classPK", KBComment::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK", (BiConsumer<KBComment, Long>)KBComment::setClassPK);
		attributeGetterFunctions.put("content", KBComment::getContent);
		attributeSetterBiConsumers.put(
			"content", (BiConsumer<KBComment, String>)KBComment::setContent);
		attributeGetterFunctions.put("userRating", KBComment::getUserRating);
		attributeSetterBiConsumers.put(
			"userRating",
			(BiConsumer<KBComment, Integer>)KBComment::setUserRating);
		attributeGetterFunctions.put(
			"lastPublishDate", KBComment::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<KBComment, Date>)KBComment::setLastPublishDate);
		attributeGetterFunctions.put("status", KBComment::getStatus);
		attributeSetterBiConsumers.put(
			"status", (BiConsumer<KBComment, Integer>)KBComment::setStatus);

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
	public long getKbCommentId() {
		return _kbCommentId;
	}

	@Override
	public void setKbCommentId(long kbCommentId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kbCommentId = kbCommentId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
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

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public String getContent() {
		if (_content == null) {
			return "";
		}
		else {
			return _content;
		}
	}

	@Override
	public void setContent(String content) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_content = content;
	}

	@JSON
	@Override
	public int getUserRating() {
		return _userRating;
	}

	@Override
	public void setUserRating(int userRating) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userRating = userRating;
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

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(KBComment.class.getName()),
			getClassNameId());
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
			getCompanyId(), KBComment.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KBComment toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, KBComment>
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
		KBCommentImpl kbCommentImpl = new KBCommentImpl();

		kbCommentImpl.setMvccVersion(getMvccVersion());
		kbCommentImpl.setUuid(getUuid());
		kbCommentImpl.setKbCommentId(getKbCommentId());
		kbCommentImpl.setGroupId(getGroupId());
		kbCommentImpl.setCompanyId(getCompanyId());
		kbCommentImpl.setUserId(getUserId());
		kbCommentImpl.setUserName(getUserName());
		kbCommentImpl.setCreateDate(getCreateDate());
		kbCommentImpl.setModifiedDate(getModifiedDate());
		kbCommentImpl.setClassNameId(getClassNameId());
		kbCommentImpl.setClassPK(getClassPK());
		kbCommentImpl.setContent(getContent());
		kbCommentImpl.setUserRating(getUserRating());
		kbCommentImpl.setLastPublishDate(getLastPublishDate());
		kbCommentImpl.setStatus(getStatus());

		kbCommentImpl.resetOriginalValues();

		return kbCommentImpl;
	}

	@Override
	public KBComment cloneWithOriginalValues() {
		KBCommentImpl kbCommentImpl = new KBCommentImpl();

		kbCommentImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		kbCommentImpl.setUuid(this.<String>getColumnOriginalValue("uuid_"));
		kbCommentImpl.setKbCommentId(
			this.<Long>getColumnOriginalValue("kbCommentId"));
		kbCommentImpl.setGroupId(this.<Long>getColumnOriginalValue("groupId"));
		kbCommentImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		kbCommentImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		kbCommentImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		kbCommentImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		kbCommentImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		kbCommentImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		kbCommentImpl.setClassPK(this.<Long>getColumnOriginalValue("classPK"));
		kbCommentImpl.setContent(
			this.<String>getColumnOriginalValue("content"));
		kbCommentImpl.setUserRating(
			this.<Integer>getColumnOriginalValue("userRating"));
		kbCommentImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));
		kbCommentImpl.setStatus(this.<Integer>getColumnOriginalValue("status"));

		return kbCommentImpl;
	}

	@Override
	public int compareTo(KBComment kbComment) {
		int value = 0;

		value = DateUtil.compareTo(
			getModifiedDate(), kbComment.getModifiedDate());

		value = value * -1;

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

		if (!(object instanceof KBComment)) {
			return false;
		}

		KBComment kbComment = (KBComment)object;

		long primaryKey = kbComment.getPrimaryKey();

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
	public CacheModel<KBComment> toCacheModel() {
		KBCommentCacheModel kbCommentCacheModel = new KBCommentCacheModel();

		kbCommentCacheModel.mvccVersion = getMvccVersion();

		kbCommentCacheModel.uuid = getUuid();

		String uuid = kbCommentCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			kbCommentCacheModel.uuid = null;
		}

		kbCommentCacheModel.kbCommentId = getKbCommentId();

		kbCommentCacheModel.groupId = getGroupId();

		kbCommentCacheModel.companyId = getCompanyId();

		kbCommentCacheModel.userId = getUserId();

		kbCommentCacheModel.userName = getUserName();

		String userName = kbCommentCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kbCommentCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kbCommentCacheModel.createDate = createDate.getTime();
		}
		else {
			kbCommentCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kbCommentCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kbCommentCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kbCommentCacheModel.classNameId = getClassNameId();

		kbCommentCacheModel.classPK = getClassPK();

		kbCommentCacheModel.content = getContent();

		String content = kbCommentCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			kbCommentCacheModel.content = null;
		}

		kbCommentCacheModel.userRating = getUserRating();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			kbCommentCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			kbCommentCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		kbCommentCacheModel.status = getStatus();

		return kbCommentCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<KBComment, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<KBComment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KBComment, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((KBComment)this);

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

		private static final Function<InvocationHandler, KBComment>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					KBComment.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _uuid;
	private long _kbCommentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _classPK;
	private String _content;
	private int _userRating;
	private Date _lastPublishDate;
	private int _status;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<KBComment, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((KBComment)this);
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
		_columnOriginalValues.put("kbCommentId", _kbCommentId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("content", _content);
		_columnOriginalValues.put("userRating", _userRating);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
		_columnOriginalValues.put("status", _status);
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

		columnBitmasks.put("kbCommentId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("classNameId", 512L);

		columnBitmasks.put("classPK", 1024L);

		columnBitmasks.put("content", 2048L);

		columnBitmasks.put("userRating", 4096L);

		columnBitmasks.put("lastPublishDate", 8192L);

		columnBitmasks.put("status", 16384L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private KBComment _escapedModel;

}