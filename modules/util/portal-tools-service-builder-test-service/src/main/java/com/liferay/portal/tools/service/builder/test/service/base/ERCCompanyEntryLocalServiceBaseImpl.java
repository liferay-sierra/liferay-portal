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

package com.liferay.portal.tools.service.builder.test.service.base;

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
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.tools.service.builder.test.model.ERCCompanyEntry;
import com.liferay.portal.tools.service.builder.test.service.ERCCompanyEntryLocalService;
import com.liferay.portal.tools.service.builder.test.service.ERCCompanyEntryLocalServiceUtil;
import com.liferay.portal.tools.service.builder.test.service.persistence.ERCCompanyEntryPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the erc company entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.tools.service.builder.test.service.impl.ERCCompanyEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.tools.service.builder.test.service.impl.ERCCompanyEntryLocalServiceImpl
 * @generated
 */
public abstract class ERCCompanyEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements ERCCompanyEntryLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>ERCCompanyEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>ERCCompanyEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the erc company entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ERCCompanyEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ercCompanyEntry the erc company entry
	 * @return the erc company entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ERCCompanyEntry addERCCompanyEntry(ERCCompanyEntry ercCompanyEntry) {
		ercCompanyEntry.setNew(true);

		return ercCompanyEntryPersistence.update(ercCompanyEntry);
	}

	/**
	 * Creates a new erc company entry with the primary key. Does not add the erc company entry to the database.
	 *
	 * @param ercCompanyEntryId the primary key for the new erc company entry
	 * @return the new erc company entry
	 */
	@Override
	@Transactional(enabled = false)
	public ERCCompanyEntry createERCCompanyEntry(long ercCompanyEntryId) {
		return ercCompanyEntryPersistence.create(ercCompanyEntryId);
	}

	/**
	 * Deletes the erc company entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ERCCompanyEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ercCompanyEntryId the primary key of the erc company entry
	 * @return the erc company entry that was removed
	 * @throws PortalException if a erc company entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ERCCompanyEntry deleteERCCompanyEntry(long ercCompanyEntryId)
		throws PortalException {

		return ercCompanyEntryPersistence.remove(ercCompanyEntryId);
	}

	/**
	 * Deletes the erc company entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ERCCompanyEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ercCompanyEntry the erc company entry
	 * @return the erc company entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ERCCompanyEntry deleteERCCompanyEntry(
		ERCCompanyEntry ercCompanyEntry) {

		return ercCompanyEntryPersistence.remove(ercCompanyEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return ercCompanyEntryPersistence.dslQuery(dslQuery);
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
			ERCCompanyEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return ercCompanyEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.ERCCompanyEntryModelImpl</code>.
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

		return ercCompanyEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.ERCCompanyEntryModelImpl</code>.
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

		return ercCompanyEntryPersistence.findWithDynamicQuery(
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
		return ercCompanyEntryPersistence.countWithDynamicQuery(dynamicQuery);
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

		return ercCompanyEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public ERCCompanyEntry fetchERCCompanyEntry(long ercCompanyEntryId) {
		return ercCompanyEntryPersistence.fetchByPrimaryKey(ercCompanyEntryId);
	}

	/**
	 * Returns the erc company entry with the matching UUID and company.
	 *
	 * @param uuid the erc company entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching erc company entry, or <code>null</code> if a matching erc company entry could not be found
	 */
	@Override
	public ERCCompanyEntry fetchERCCompanyEntryByUuidAndCompanyId(
		String uuid, long companyId) {

		return ercCompanyEntryPersistence.fetchByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns the erc company entry with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the erc company entry's external reference code
	 * @return the matching erc company entry, or <code>null</code> if a matching erc company entry could not be found
	 */
	@Override
	public ERCCompanyEntry fetchERCCompanyEntryByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		return ercCompanyEntryPersistence.fetchByC_ERC(
			companyId, externalReferenceCode);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #fetchERCCompanyEntryByExternalReferenceCode(long, String)}
	 */
	@Deprecated
	@Override
	public ERCCompanyEntry fetchERCCompanyEntryByReferenceCode(
		long companyId, String externalReferenceCode) {

		return fetchERCCompanyEntryByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the erc company entry with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the erc company entry's external reference code
	 * @return the matching erc company entry
	 * @throws PortalException if a matching erc company entry could not be found
	 */
	@Override
	public ERCCompanyEntry getERCCompanyEntryByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		return ercCompanyEntryPersistence.findByC_ERC(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the erc company entry with the primary key.
	 *
	 * @param ercCompanyEntryId the primary key of the erc company entry
	 * @return the erc company entry
	 * @throws PortalException if a erc company entry with the primary key could not be found
	 */
	@Override
	public ERCCompanyEntry getERCCompanyEntry(long ercCompanyEntryId)
		throws PortalException {

		return ercCompanyEntryPersistence.findByPrimaryKey(ercCompanyEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(ercCompanyEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ERCCompanyEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("ercCompanyEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			ercCompanyEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(ERCCompanyEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"ercCompanyEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(ercCompanyEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ERCCompanyEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("ercCompanyEntryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ercCompanyEntryPersistence.create(
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
				"Implement ERCCompanyEntryLocalServiceImpl#deleteERCCompanyEntry(ERCCompanyEntry) to avoid orphaned data");
		}

		return ercCompanyEntryLocalService.deleteERCCompanyEntry(
			(ERCCompanyEntry)persistedModel);
	}

	@Override
	public BasePersistence<ERCCompanyEntry> getBasePersistence() {
		return ercCompanyEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ercCompanyEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the erc company entry with the matching UUID and company.
	 *
	 * @param uuid the erc company entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching erc company entry
	 * @throws PortalException if a matching erc company entry could not be found
	 */
	@Override
	public ERCCompanyEntry getERCCompanyEntryByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return ercCompanyEntryPersistence.findByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns a range of all the erc company entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.ERCCompanyEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of erc company entries
	 * @param end the upper bound of the range of erc company entries (not inclusive)
	 * @return the range of erc company entries
	 */
	@Override
	public List<ERCCompanyEntry> getERCCompanyEntries(int start, int end) {
		return ercCompanyEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of erc company entries.
	 *
	 * @return the number of erc company entries
	 */
	@Override
	public int getERCCompanyEntriesCount() {
		return ercCompanyEntryPersistence.countAll();
	}

	/**
	 * Updates the erc company entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ERCCompanyEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ercCompanyEntry the erc company entry
	 * @return the erc company entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ERCCompanyEntry updateERCCompanyEntry(
		ERCCompanyEntry ercCompanyEntry) {

		return ercCompanyEntryPersistence.update(ercCompanyEntry);
	}

	/**
	 * Returns the erc company entry local service.
	 *
	 * @return the erc company entry local service
	 */
	public ERCCompanyEntryLocalService getERCCompanyEntryLocalService() {
		return ercCompanyEntryLocalService;
	}

	/**
	 * Sets the erc company entry local service.
	 *
	 * @param ercCompanyEntryLocalService the erc company entry local service
	 */
	public void setERCCompanyEntryLocalService(
		ERCCompanyEntryLocalService ercCompanyEntryLocalService) {

		this.ercCompanyEntryLocalService = ercCompanyEntryLocalService;
	}

	/**
	 * Returns the erc company entry persistence.
	 *
	 * @return the erc company entry persistence
	 */
	public ERCCompanyEntryPersistence getERCCompanyEntryPersistence() {
		return ercCompanyEntryPersistence;
	}

	/**
	 * Sets the erc company entry persistence.
	 *
	 * @param ercCompanyEntryPersistence the erc company entry persistence
	 */
	public void setERCCompanyEntryPersistence(
		ERCCompanyEntryPersistence ercCompanyEntryPersistence) {

		this.ercCompanyEntryPersistence = ercCompanyEntryPersistence;
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
			"com.liferay.portal.tools.service.builder.test.model.ERCCompanyEntry",
			ercCompanyEntryLocalService);

		_setLocalServiceUtilService(ercCompanyEntryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.tools.service.builder.test.model.ERCCompanyEntry");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ERCCompanyEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ERCCompanyEntry.class;
	}

	protected String getModelClassName() {
		return ERCCompanyEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ercCompanyEntryPersistence.getDataSource();

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
		ERCCompanyEntryLocalService ercCompanyEntryLocalService) {

		try {
			Field field =
				ERCCompanyEntryLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, ercCompanyEntryLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = ERCCompanyEntryLocalService.class)
	protected ERCCompanyEntryLocalService ercCompanyEntryLocalService;

	@BeanReference(type = ERCCompanyEntryPersistence.class)
	protected ERCCompanyEntryPersistence ercCompanyEntryPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		ERCCompanyEntryLocalServiceBaseImpl.class);

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}