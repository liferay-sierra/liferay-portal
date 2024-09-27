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

package com.liferay.commerce.shop.by.diagram.service.base;

import com.liferay.commerce.shop.by.diagram.model.CSDiagramSetting;
import com.liferay.commerce.shop.by.diagram.service.CSDiagramSettingLocalService;
import com.liferay.commerce.shop.by.diagram.service.CSDiagramSettingLocalServiceUtil;
import com.liferay.commerce.shop.by.diagram.service.persistence.CSDiagramSettingPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
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
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.change.tracking.CTService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the cs diagram setting local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.shop.by.diagram.service.impl.CSDiagramSettingLocalServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.shop.by.diagram.service.impl.CSDiagramSettingLocalServiceImpl
 * @generated
 */
public abstract class CSDiagramSettingLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, CSDiagramSettingLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CSDiagramSettingLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CSDiagramSettingLocalServiceUtil</code>.
	 */

	/**
	 * Adds the cs diagram setting to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CSDiagramSettingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param csDiagramSetting the cs diagram setting
	 * @return the cs diagram setting that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CSDiagramSetting addCSDiagramSetting(
		CSDiagramSetting csDiagramSetting) {

		csDiagramSetting.setNew(true);

		return csDiagramSettingPersistence.update(csDiagramSetting);
	}

	/**
	 * Creates a new cs diagram setting with the primary key. Does not add the cs diagram setting to the database.
	 *
	 * @param CSDiagramSettingId the primary key for the new cs diagram setting
	 * @return the new cs diagram setting
	 */
	@Override
	@Transactional(enabled = false)
	public CSDiagramSetting createCSDiagramSetting(long CSDiagramSettingId) {
		return csDiagramSettingPersistence.create(CSDiagramSettingId);
	}

	/**
	 * Deletes the cs diagram setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CSDiagramSettingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param CSDiagramSettingId the primary key of the cs diagram setting
	 * @return the cs diagram setting that was removed
	 * @throws PortalException if a cs diagram setting with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CSDiagramSetting deleteCSDiagramSetting(long CSDiagramSettingId)
		throws PortalException {

		return csDiagramSettingPersistence.remove(CSDiagramSettingId);
	}

	/**
	 * Deletes the cs diagram setting from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CSDiagramSettingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param csDiagramSetting the cs diagram setting
	 * @return the cs diagram setting that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CSDiagramSetting deleteCSDiagramSetting(
		CSDiagramSetting csDiagramSetting) {

		return csDiagramSettingPersistence.remove(csDiagramSetting);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return csDiagramSettingPersistence.dslQuery(dslQuery);
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
			CSDiagramSetting.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return csDiagramSettingPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shop.by.diagram.model.impl.CSDiagramSettingModelImpl</code>.
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

		return csDiagramSettingPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shop.by.diagram.model.impl.CSDiagramSettingModelImpl</code>.
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

		return csDiagramSettingPersistence.findWithDynamicQuery(
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
		return csDiagramSettingPersistence.countWithDynamicQuery(dynamicQuery);
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

		return csDiagramSettingPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CSDiagramSetting fetchCSDiagramSetting(long CSDiagramSettingId) {
		return csDiagramSettingPersistence.fetchByPrimaryKey(
			CSDiagramSettingId);
	}

	/**
	 * Returns the cs diagram setting with the matching UUID and company.
	 *
	 * @param uuid the cs diagram setting's UUID
	 * @param companyId the primary key of the company
	 * @return the matching cs diagram setting, or <code>null</code> if a matching cs diagram setting could not be found
	 */
	@Override
	public CSDiagramSetting fetchCSDiagramSettingByUuidAndCompanyId(
		String uuid, long companyId) {

		return csDiagramSettingPersistence.fetchByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns the cs diagram setting with the primary key.
	 *
	 * @param CSDiagramSettingId the primary key of the cs diagram setting
	 * @return the cs diagram setting
	 * @throws PortalException if a cs diagram setting with the primary key could not be found
	 */
	@Override
	public CSDiagramSetting getCSDiagramSetting(long CSDiagramSettingId)
		throws PortalException {

		return csDiagramSettingPersistence.findByPrimaryKey(CSDiagramSettingId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			csDiagramSettingLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CSDiagramSetting.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("CSDiagramSettingId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			csDiagramSettingLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CSDiagramSetting.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"CSDiagramSettingId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			csDiagramSettingLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CSDiagramSetting.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("CSDiagramSettingId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<CSDiagramSetting>() {

				@Override
				public void performAction(CSDiagramSetting csDiagramSetting)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, csDiagramSetting);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(CSDiagramSetting.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return csDiagramSettingPersistence.create(
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
				"Implement CSDiagramSettingLocalServiceImpl#deleteCSDiagramSetting(CSDiagramSetting) to avoid orphaned data");
		}

		return csDiagramSettingLocalService.deleteCSDiagramSetting(
			(CSDiagramSetting)persistedModel);
	}

	@Override
	public BasePersistence<CSDiagramSetting> getBasePersistence() {
		return csDiagramSettingPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return csDiagramSettingPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the cs diagram setting with the matching UUID and company.
	 *
	 * @param uuid the cs diagram setting's UUID
	 * @param companyId the primary key of the company
	 * @return the matching cs diagram setting
	 * @throws PortalException if a matching cs diagram setting could not be found
	 */
	@Override
	public CSDiagramSetting getCSDiagramSettingByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return csDiagramSettingPersistence.findByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns a range of all the cs diagram settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shop.by.diagram.model.impl.CSDiagramSettingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cs diagram settings
	 * @param end the upper bound of the range of cs diagram settings (not inclusive)
	 * @return the range of cs diagram settings
	 */
	@Override
	public List<CSDiagramSetting> getCSDiagramSettings(int start, int end) {
		return csDiagramSettingPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of cs diagram settings.
	 *
	 * @return the number of cs diagram settings
	 */
	@Override
	public int getCSDiagramSettingsCount() {
		return csDiagramSettingPersistence.countAll();
	}

	/**
	 * Updates the cs diagram setting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CSDiagramSettingLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param csDiagramSetting the cs diagram setting
	 * @return the cs diagram setting that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CSDiagramSetting updateCSDiagramSetting(
		CSDiagramSetting csDiagramSetting) {

		return csDiagramSettingPersistence.update(csDiagramSetting);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CSDiagramSettingLocalService.class, IdentifiableOSGiService.class,
			CTService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		csDiagramSettingLocalService = (CSDiagramSettingLocalService)aopProxy;

		_setLocalServiceUtilService(csDiagramSettingLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CSDiagramSettingLocalService.class.getName();
	}

	@Override
	public CTPersistence<CSDiagramSetting> getCTPersistence() {
		return csDiagramSettingPersistence;
	}

	@Override
	public Class<CSDiagramSetting> getModelClass() {
		return CSDiagramSetting.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<CSDiagramSetting>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(csDiagramSettingPersistence);
	}

	protected String getModelClassName() {
		return CSDiagramSetting.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = csDiagramSettingPersistence.getDataSource();

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
		CSDiagramSettingLocalService csDiagramSettingLocalService) {

		try {
			Field field =
				CSDiagramSettingLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, csDiagramSettingLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected CSDiagramSettingLocalService csDiagramSettingLocalService;

	@Reference
	protected CSDiagramSettingPersistence csDiagramSettingPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		CSDiagramSettingLocalServiceBaseImpl.class);

}