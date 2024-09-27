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

package com.liferay.portal.service.base;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.UserTrackerPath;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.UserTrackerPathLocalService;
import com.liferay.portal.kernel.service.UserTrackerPathLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.UserTrackerPathPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the user tracker path local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.UserTrackerPathLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.UserTrackerPathLocalServiceImpl
 * @generated
 */
public abstract class UserTrackerPathLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, UserTrackerPathLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>UserTrackerPathLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>UserTrackerPathLocalServiceUtil</code>.
	 */

	/**
	 * Adds the user tracker path to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserTrackerPathLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userTrackerPath the user tracker path
	 * @return the user tracker path that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public UserTrackerPath addUserTrackerPath(UserTrackerPath userTrackerPath) {
		userTrackerPath.setNew(true);

		return userTrackerPathPersistence.update(userTrackerPath);
	}

	/**
	 * Creates a new user tracker path with the primary key. Does not add the user tracker path to the database.
	 *
	 * @param userTrackerPathId the primary key for the new user tracker path
	 * @return the new user tracker path
	 */
	@Override
	@Transactional(enabled = false)
	public UserTrackerPath createUserTrackerPath(long userTrackerPathId) {
		return userTrackerPathPersistence.create(userTrackerPathId);
	}

	/**
	 * Deletes the user tracker path with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserTrackerPathLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userTrackerPathId the primary key of the user tracker path
	 * @return the user tracker path that was removed
	 * @throws PortalException if a user tracker path with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public UserTrackerPath deleteUserTrackerPath(long userTrackerPathId)
		throws PortalException {

		return userTrackerPathPersistence.remove(userTrackerPathId);
	}

	/**
	 * Deletes the user tracker path from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserTrackerPathLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userTrackerPath the user tracker path
	 * @return the user tracker path that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public UserTrackerPath deleteUserTrackerPath(
		UserTrackerPath userTrackerPath) {

		return userTrackerPathPersistence.remove(userTrackerPath);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return userTrackerPathPersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			UserTrackerPath.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return userTrackerPathPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.UserTrackerPathModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return userTrackerPathPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.UserTrackerPathModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return userTrackerPathPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return userTrackerPathPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return userTrackerPathPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public UserTrackerPath fetchUserTrackerPath(long userTrackerPathId) {
		return userTrackerPathPersistence.fetchByPrimaryKey(userTrackerPathId);
	}

	/**
	 * Returns the user tracker path with the primary key.
	 *
	 * @param userTrackerPathId the primary key of the user tracker path
	 * @return the user tracker path
	 * @throws PortalException if a user tracker path with the primary key could not be found
	 */
	@Override
	public UserTrackerPath getUserTrackerPath(long userTrackerPathId)
		throws PortalException {

		return userTrackerPathPersistence.findByPrimaryKey(userTrackerPathId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(userTrackerPathLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(UserTrackerPath.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("userTrackerPathId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			userTrackerPathLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(UserTrackerPath.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"userTrackerPathId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(userTrackerPathLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(UserTrackerPath.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("userTrackerPathId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return userTrackerPathPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement UserTrackerPathLocalServiceImpl#deleteUserTrackerPath(UserTrackerPath) to avoid orphaned data");
		}

		return userTrackerPathLocalService.deleteUserTrackerPath(
			(UserTrackerPath)persistedModel);
	}

	@Override
	public BasePersistence<UserTrackerPath> getBasePersistence() {
		return userTrackerPathPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return userTrackerPathPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the user tracker paths.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.UserTrackerPathModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user tracker paths
	 * @param end the upper bound of the range of user tracker paths (not inclusive)
	 * @return the range of user tracker paths
	 */
	@Override
	public List<UserTrackerPath> getUserTrackerPaths(int start, int end) {
		return userTrackerPathPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of user tracker paths.
	 *
	 * @return the number of user tracker paths
	 */
	@Override
	public int getUserTrackerPathsCount() {
		return userTrackerPathPersistence.countAll();
	}

	/**
	 * Updates the user tracker path in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserTrackerPathLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userTrackerPath the user tracker path
	 * @return the user tracker path that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public UserTrackerPath updateUserTrackerPath(
		UserTrackerPath userTrackerPath) {

		return userTrackerPathPersistence.update(userTrackerPath);
	}

	/**
	 * Returns the user tracker path local service.
	 *
	 * @return the user tracker path local service
	 */
	public UserTrackerPathLocalService getUserTrackerPathLocalService() {
		return userTrackerPathLocalService;
	}

	/**
	 * Sets the user tracker path local service.
	 *
	 * @param userTrackerPathLocalService the user tracker path local service
	 */
	public void setUserTrackerPathLocalService(
		UserTrackerPathLocalService userTrackerPathLocalService) {

		this.userTrackerPathLocalService = userTrackerPathLocalService;
	}

	/**
	 * Returns the user tracker path persistence.
	 *
	 * @return the user tracker path persistence
	 */
	public UserTrackerPathPersistence getUserTrackerPathPersistence() {
		return userTrackerPathPersistence;
	}

	/**
	 * Sets the user tracker path persistence.
	 *
	 * @param userTrackerPathPersistence the user tracker path persistence
	 */
	public void setUserTrackerPathPersistence(
		UserTrackerPathPersistence userTrackerPathPersistence) {

		this.userTrackerPathPersistence = userTrackerPathPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.portal.kernel.model.UserTrackerPath",
			userTrackerPathLocalService);

		_setLocalServiceUtilService(userTrackerPathLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.kernel.model.UserTrackerPath");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return UserTrackerPathLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return UserTrackerPath.class;
	}

	protected String getModelClassName() {
		return UserTrackerPath.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = userTrackerPathPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setLocalServiceUtilService(
		UserTrackerPathLocalService userTrackerPathLocalService) {

		try {
			Field field =
				UserTrackerPathLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, userTrackerPathLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = UserTrackerPathLocalService.class)
	protected UserTrackerPathLocalService userTrackerPathLocalService;

	@BeanReference(type = UserTrackerPathPersistence.class)
	protected UserTrackerPathPersistence userTrackerPathPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		UserTrackerPathLocalServiceBaseImpl.class);

	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}