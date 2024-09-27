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

package com.liferay.portlet.announcements.service.base;

import com.liferay.announcements.kernel.model.AnnouncementsDelivery;
import com.liferay.announcements.kernel.service.AnnouncementsDeliveryLocalService;
import com.liferay.announcements.kernel.service.AnnouncementsDeliveryLocalServiceUtil;
import com.liferay.announcements.kernel.service.persistence.AnnouncementsDeliveryPersistence;
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
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the announcements delivery local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.announcements.service.impl.AnnouncementsDeliveryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.announcements.service.impl.AnnouncementsDeliveryLocalServiceImpl
 * @generated
 */
public abstract class AnnouncementsDeliveryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AnnouncementsDeliveryLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>AnnouncementsDeliveryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>AnnouncementsDeliveryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the announcements delivery to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnnouncementsDeliveryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param announcementsDelivery the announcements delivery
	 * @return the announcements delivery that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AnnouncementsDelivery addAnnouncementsDelivery(
		AnnouncementsDelivery announcementsDelivery) {

		announcementsDelivery.setNew(true);

		return announcementsDeliveryPersistence.update(announcementsDelivery);
	}

	/**
	 * Creates a new announcements delivery with the primary key. Does not add the announcements delivery to the database.
	 *
	 * @param deliveryId the primary key for the new announcements delivery
	 * @return the new announcements delivery
	 */
	@Override
	@Transactional(enabled = false)
	public AnnouncementsDelivery createAnnouncementsDelivery(long deliveryId) {
		return announcementsDeliveryPersistence.create(deliveryId);
	}

	/**
	 * Deletes the announcements delivery with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnnouncementsDeliveryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param deliveryId the primary key of the announcements delivery
	 * @return the announcements delivery that was removed
	 * @throws PortalException if a announcements delivery with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AnnouncementsDelivery deleteAnnouncementsDelivery(long deliveryId)
		throws PortalException {

		return announcementsDeliveryPersistence.remove(deliveryId);
	}

	/**
	 * Deletes the announcements delivery from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnnouncementsDeliveryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param announcementsDelivery the announcements delivery
	 * @return the announcements delivery that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AnnouncementsDelivery deleteAnnouncementsDelivery(
		AnnouncementsDelivery announcementsDelivery) {

		return announcementsDeliveryPersistence.remove(announcementsDelivery);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return announcementsDeliveryPersistence.dslQuery(dslQuery);
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
			AnnouncementsDelivery.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return announcementsDeliveryPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.announcements.model.impl.AnnouncementsDeliveryModelImpl</code>.
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

		return announcementsDeliveryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.announcements.model.impl.AnnouncementsDeliveryModelImpl</code>.
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

		return announcementsDeliveryPersistence.findWithDynamicQuery(
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
		return announcementsDeliveryPersistence.countWithDynamicQuery(
			dynamicQuery);
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

		return announcementsDeliveryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public AnnouncementsDelivery fetchAnnouncementsDelivery(long deliveryId) {
		return announcementsDeliveryPersistence.fetchByPrimaryKey(deliveryId);
	}

	/**
	 * Returns the announcements delivery with the primary key.
	 *
	 * @param deliveryId the primary key of the announcements delivery
	 * @return the announcements delivery
	 * @throws PortalException if a announcements delivery with the primary key could not be found
	 */
	@Override
	public AnnouncementsDelivery getAnnouncementsDelivery(long deliveryId)
		throws PortalException {

		return announcementsDeliveryPersistence.findByPrimaryKey(deliveryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			announcementsDeliveryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AnnouncementsDelivery.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("deliveryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			announcementsDeliveryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			AnnouncementsDelivery.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("deliveryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			announcementsDeliveryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AnnouncementsDelivery.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("deliveryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return announcementsDeliveryPersistence.create(
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
				"Implement AnnouncementsDeliveryLocalServiceImpl#deleteAnnouncementsDelivery(AnnouncementsDelivery) to avoid orphaned data");
		}

		return announcementsDeliveryLocalService.deleteAnnouncementsDelivery(
			(AnnouncementsDelivery)persistedModel);
	}

	@Override
	public BasePersistence<AnnouncementsDelivery> getBasePersistence() {
		return announcementsDeliveryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return announcementsDeliveryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the announcements deliveries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.announcements.model.impl.AnnouncementsDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of announcements deliveries
	 * @param end the upper bound of the range of announcements deliveries (not inclusive)
	 * @return the range of announcements deliveries
	 */
	@Override
	public List<AnnouncementsDelivery> getAnnouncementsDeliveries(
		int start, int end) {

		return announcementsDeliveryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of announcements deliveries.
	 *
	 * @return the number of announcements deliveries
	 */
	@Override
	public int getAnnouncementsDeliveriesCount() {
		return announcementsDeliveryPersistence.countAll();
	}

	/**
	 * Updates the announcements delivery in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnnouncementsDeliveryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param announcementsDelivery the announcements delivery
	 * @return the announcements delivery that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AnnouncementsDelivery updateAnnouncementsDelivery(
		AnnouncementsDelivery announcementsDelivery) {

		return announcementsDeliveryPersistence.update(announcementsDelivery);
	}

	/**
	 * Returns the announcements delivery local service.
	 *
	 * @return the announcements delivery local service
	 */
	public AnnouncementsDeliveryLocalService
		getAnnouncementsDeliveryLocalService() {

		return announcementsDeliveryLocalService;
	}

	/**
	 * Sets the announcements delivery local service.
	 *
	 * @param announcementsDeliveryLocalService the announcements delivery local service
	 */
	public void setAnnouncementsDeliveryLocalService(
		AnnouncementsDeliveryLocalService announcementsDeliveryLocalService) {

		this.announcementsDeliveryLocalService =
			announcementsDeliveryLocalService;
	}

	/**
	 * Returns the announcements delivery persistence.
	 *
	 * @return the announcements delivery persistence
	 */
	public AnnouncementsDeliveryPersistence
		getAnnouncementsDeliveryPersistence() {

		return announcementsDeliveryPersistence;
	}

	/**
	 * Sets the announcements delivery persistence.
	 *
	 * @param announcementsDeliveryPersistence the announcements delivery persistence
	 */
	public void setAnnouncementsDeliveryPersistence(
		AnnouncementsDeliveryPersistence announcementsDeliveryPersistence) {

		this.announcementsDeliveryPersistence =
			announcementsDeliveryPersistence;
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
			"com.liferay.announcements.kernel.model.AnnouncementsDelivery",
			announcementsDeliveryLocalService);

		_setLocalServiceUtilService(announcementsDeliveryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.announcements.kernel.model.AnnouncementsDelivery");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AnnouncementsDeliveryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return AnnouncementsDelivery.class;
	}

	protected String getModelClassName() {
		return AnnouncementsDelivery.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				announcementsDeliveryPersistence.getDataSource();

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
		AnnouncementsDeliveryLocalService announcementsDeliveryLocalService) {

		try {
			Field field =
				AnnouncementsDeliveryLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, announcementsDeliveryLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = AnnouncementsDeliveryLocalService.class)
	protected AnnouncementsDeliveryLocalService
		announcementsDeliveryLocalService;

	@BeanReference(type = AnnouncementsDeliveryPersistence.class)
	protected AnnouncementsDeliveryPersistence announcementsDeliveryPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		AnnouncementsDeliveryLocalServiceBaseImpl.class);

	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}