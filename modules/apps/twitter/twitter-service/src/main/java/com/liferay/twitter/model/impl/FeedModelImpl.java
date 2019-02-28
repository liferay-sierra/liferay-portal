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

package com.liferay.twitter.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.twitter.model.Feed;
import com.liferay.twitter.model.FeedModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Feed service. Represents a row in the &quot;Twitter_Feed&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>FeedModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FeedImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedImpl
 * @generated
 */
@ProviderType
public class FeedModelImpl extends BaseModelImpl<Feed> implements FeedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a feed model instance should use the <code>Feed</code> interface instead.
	 */
	public static final String TABLE_NAME = "Twitter_Feed";

	public static final Object[][] TABLE_COLUMNS = {
		{"feedId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"twitterUserId", Types.BIGINT}, {"twitterScreenName", Types.VARCHAR},
		{"lastStatusId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("feedId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("twitterUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("twitterScreenName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastStatusId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Twitter_Feed (feedId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,twitterUserId LONG,twitterScreenName VARCHAR(75) null,lastStatusId LONG)";

	public static final String TABLE_SQL_DROP = "drop table Twitter_Feed";

	public static final String ORDER_BY_JPQL = " ORDER BY feed.feedId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Twitter_Feed.feedId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.twitter.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.twitter.model.Feed"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.twitter.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.twitter.model.Feed"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.twitter.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.twitter.model.Feed"),
		true);

	public static final long TWITTERSCREENNAME_COLUMN_BITMASK = 1L;

	public static final long USERID_COLUMN_BITMASK = 2L;

	public static final long FEEDID_COLUMN_BITMASK = 4L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.twitter.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.twitter.model.Feed"));

	public FeedModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _feedId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFeedId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _feedId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Feed.class;
	}

	@Override
	public String getModelClassName() {
		return Feed.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Feed, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Feed, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Feed, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Feed)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Feed, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Feed, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((Feed)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Feed, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Feed, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<Feed, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Feed, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Feed, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Feed, Object>>();
		Map<String, BiConsumer<Feed, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Feed, ?>>();

		attributeGetterFunctions.put(
			"feedId",
			new Function<Feed, Object>() {

				@Override
				public Object apply(Feed feed) {
					return feed.getFeedId();
				}

			});
		attributeSetterBiConsumers.put(
			"feedId",
			new BiConsumer<Feed, Object>() {

				@Override
				public void accept(Feed feed, Object feedId) {
					feed.setFeedId((Long)feedId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<Feed, Object>() {

				@Override
				public Object apply(Feed feed) {
					return feed.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<Feed, Object>() {

				@Override
				public void accept(Feed feed, Object companyId) {
					feed.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<Feed, Object>() {

				@Override
				public Object apply(Feed feed) {
					return feed.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<Feed, Object>() {

				@Override
				public void accept(Feed feed, Object userId) {
					feed.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<Feed, Object>() {

				@Override
				public Object apply(Feed feed) {
					return feed.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<Feed, Object>() {

				@Override
				public void accept(Feed feed, Object userName) {
					feed.setUserName((String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<Feed, Object>() {

				@Override
				public Object apply(Feed feed) {
					return feed.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<Feed, Object>() {

				@Override
				public void accept(Feed feed, Object createDate) {
					feed.setCreateDate((Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<Feed, Object>() {

				@Override
				public Object apply(Feed feed) {
					return feed.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<Feed, Object>() {

				@Override
				public void accept(Feed feed, Object modifiedDate) {
					feed.setModifiedDate((Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"twitterUserId",
			new Function<Feed, Object>() {

				@Override
				public Object apply(Feed feed) {
					return feed.getTwitterUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"twitterUserId",
			new BiConsumer<Feed, Object>() {

				@Override
				public void accept(Feed feed, Object twitterUserId) {
					feed.setTwitterUserId((Long)twitterUserId);
				}

			});
		attributeGetterFunctions.put(
			"twitterScreenName",
			new Function<Feed, Object>() {

				@Override
				public Object apply(Feed feed) {
					return feed.getTwitterScreenName();
				}

			});
		attributeSetterBiConsumers.put(
			"twitterScreenName",
			new BiConsumer<Feed, Object>() {

				@Override
				public void accept(Feed feed, Object twitterScreenName) {
					feed.setTwitterScreenName((String)twitterScreenName);
				}

			});
		attributeGetterFunctions.put(
			"lastStatusId",
			new Function<Feed, Object>() {

				@Override
				public Object apply(Feed feed) {
					return feed.getLastStatusId();
				}

			});
		attributeSetterBiConsumers.put(
			"lastStatusId",
			new BiConsumer<Feed, Object>() {

				@Override
				public void accept(Feed feed, Object lastStatusId) {
					feed.setLastStatusId((Long)lastStatusId);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getFeedId() {
		return _feedId;
	}

	@Override
	public void setFeedId(long feedId) {
		_feedId = feedId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
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
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
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

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getTwitterUserId() {
		return _twitterUserId;
	}

	@Override
	public void setTwitterUserId(long twitterUserId) {
		_twitterUserId = twitterUserId;
	}

	@Override
	public String getTwitterUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getTwitterUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setTwitterUserUuid(String twitterUserUuid) {
	}

	@Override
	public String getTwitterScreenName() {
		if (_twitterScreenName == null) {
			return "";
		}
		else {
			return _twitterScreenName;
		}
	}

	@Override
	public void setTwitterScreenName(String twitterScreenName) {
		_columnBitmask |= TWITTERSCREENNAME_COLUMN_BITMASK;

		if (_originalTwitterScreenName == null) {
			_originalTwitterScreenName = _twitterScreenName;
		}

		_twitterScreenName = twitterScreenName;
	}

	public String getOriginalTwitterScreenName() {
		return GetterUtil.getString(_originalTwitterScreenName);
	}

	@Override
	public long getLastStatusId() {
		return _lastStatusId;
	}

	@Override
	public void setLastStatusId(long lastStatusId) {
		_lastStatusId = lastStatusId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Feed.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Feed toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Feed)ProxyUtil.newProxyInstance(
				_classLoader, _escapedModelInterfaces,
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		FeedImpl feedImpl = new FeedImpl();

		feedImpl.setFeedId(getFeedId());
		feedImpl.setCompanyId(getCompanyId());
		feedImpl.setUserId(getUserId());
		feedImpl.setUserName(getUserName());
		feedImpl.setCreateDate(getCreateDate());
		feedImpl.setModifiedDate(getModifiedDate());
		feedImpl.setTwitterUserId(getTwitterUserId());
		feedImpl.setTwitterScreenName(getTwitterScreenName());
		feedImpl.setLastStatusId(getLastStatusId());

		feedImpl.resetOriginalValues();

		return feedImpl;
	}

	@Override
	public int compareTo(Feed feed) {
		long primaryKey = feed.getPrimaryKey();

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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Feed)) {
			return false;
		}

		Feed feed = (Feed)obj;

		long primaryKey = feed.getPrimaryKey();

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

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		FeedModelImpl feedModelImpl = this;

		feedModelImpl._originalUserId = feedModelImpl._userId;

		feedModelImpl._setOriginalUserId = false;

		feedModelImpl._setModifiedDate = false;

		feedModelImpl._originalTwitterScreenName =
			feedModelImpl._twitterScreenName;

		feedModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Feed> toCacheModel() {
		FeedCacheModel feedCacheModel = new FeedCacheModel();

		feedCacheModel.feedId = getFeedId();

		feedCacheModel.companyId = getCompanyId();

		feedCacheModel.userId = getUserId();

		feedCacheModel.userName = getUserName();

		String userName = feedCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			feedCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			feedCacheModel.createDate = createDate.getTime();
		}
		else {
			feedCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			feedCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			feedCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		feedCacheModel.twitterUserId = getTwitterUserId();

		feedCacheModel.twitterScreenName = getTwitterScreenName();

		String twitterScreenName = feedCacheModel.twitterScreenName;

		if ((twitterScreenName != null) && (twitterScreenName.length() == 0)) {
			feedCacheModel.twitterScreenName = null;
		}

		feedCacheModel.lastStatusId = getLastStatusId();

		return feedCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Feed, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Feed, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Feed, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Feed)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<Feed, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Feed, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Feed, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Feed)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Feed.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
		Feed.class, ModelWrapper.class
	};

	private long _feedId;
	private long _companyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _twitterUserId;
	private String _twitterScreenName;
	private String _originalTwitterScreenName;
	private long _lastStatusId;
	private long _columnBitmask;
	private Feed _escapedModel;

}