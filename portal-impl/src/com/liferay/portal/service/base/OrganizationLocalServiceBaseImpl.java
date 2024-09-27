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

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.function.UnsafeFunction;
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
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.GroupPersistence;
import com.liferay.portal.kernel.service.persistence.OrganizationFinder;
import com.liferay.portal.kernel.service.persistence.OrganizationPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the organization local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.OrganizationLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.OrganizationLocalServiceImpl
 * @generated
 */
public abstract class OrganizationLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, OrganizationLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>OrganizationLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>OrganizationLocalServiceUtil</code>.
	 */

	/**
	 * Adds the organization to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrganizationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param organization the organization
	 * @return the organization that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Organization addOrganization(Organization organization) {
		organization.setNew(true);

		return organizationPersistence.update(organization);
	}

	/**
	 * Creates a new organization with the primary key. Does not add the organization to the database.
	 *
	 * @param organizationId the primary key for the new organization
	 * @return the new organization
	 */
	@Override
	@Transactional(enabled = false)
	public Organization createOrganization(long organizationId) {
		return organizationPersistence.create(organizationId);
	}

	/**
	 * Deletes the organization with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrganizationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param organizationId the primary key of the organization
	 * @return the organization that was removed
	 * @throws PortalException if a organization with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Organization deleteOrganization(long organizationId)
		throws PortalException {

		return organizationPersistence.remove(organizationId);
	}

	/**
	 * Deletes the organization from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrganizationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param organization the organization
	 * @return the organization that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Organization deleteOrganization(Organization organization)
		throws PortalException {

		return organizationPersistence.remove(organization);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return organizationPersistence.dslQuery(dslQuery);
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
			Organization.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return organizationPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.OrganizationModelImpl</code>.
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

		return organizationPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.OrganizationModelImpl</code>.
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

		return organizationPersistence.findWithDynamicQuery(
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
		return organizationPersistence.countWithDynamicQuery(dynamicQuery);
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

		return organizationPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public Organization fetchOrganization(long organizationId) {
		return organizationPersistence.fetchByPrimaryKey(organizationId);
	}

	/**
	 * Returns the organization with the matching UUID and company.
	 *
	 * @param uuid the organization's UUID
	 * @param companyId the primary key of the company
	 * @return the matching organization, or <code>null</code> if a matching organization could not be found
	 */
	@Override
	public Organization fetchOrganizationByUuidAndCompanyId(
		String uuid, long companyId) {

		return organizationPersistence.fetchByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns the organization with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the organization's external reference code
	 * @return the matching organization, or <code>null</code> if a matching organization could not be found
	 */
	@Override
	public Organization fetchOrganizationByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		return organizationPersistence.fetchByC_ERC(
			companyId, externalReferenceCode);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #fetchOrganizationByExternalReferenceCode(long, String)}
	 */
	@Deprecated
	@Override
	public Organization fetchOrganizationByReferenceCode(
		long companyId, String externalReferenceCode) {

		return fetchOrganizationByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the organization with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the organization's external reference code
	 * @return the matching organization
	 * @throws PortalException if a matching organization could not be found
	 */
	@Override
	public Organization getOrganizationByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		return organizationPersistence.findByC_ERC(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the organization with the primary key.
	 *
	 * @param organizationId the primary key of the organization
	 * @return the organization
	 * @throws PortalException if a organization with the primary key could not be found
	 */
	@Override
	public Organization getOrganization(long organizationId)
		throws PortalException {

		return organizationPersistence.findByPrimaryKey(organizationId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(organizationLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Organization.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("organizationId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			organizationLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Organization.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"organizationId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(organizationLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Organization.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("organizationId");
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
			new ActionableDynamicQuery.PerformActionMethod<Organization>() {

				@Override
				public void performAction(Organization organization)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, organization);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(Organization.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return organizationPersistence.create(
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
				"Implement OrganizationLocalServiceImpl#deleteOrganization(Organization) to avoid orphaned data");
		}

		return organizationLocalService.deleteOrganization(
			(Organization)persistedModel);
	}

	@Override
	public BasePersistence<Organization> getBasePersistence() {
		return organizationPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return organizationPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the organization with the matching UUID and company.
	 *
	 * @param uuid the organization's UUID
	 * @param companyId the primary key of the company
	 * @return the matching organization
	 * @throws PortalException if a matching organization could not be found
	 */
	@Override
	public Organization getOrganizationByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return organizationPersistence.findByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns a range of all the organizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.OrganizationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of organizations
	 * @param end the upper bound of the range of organizations (not inclusive)
	 * @return the range of organizations
	 */
	@Override
	public List<Organization> getOrganizations(int start, int end) {
		return organizationPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of organizations.
	 *
	 * @return the number of organizations
	 */
	@Override
	public int getOrganizationsCount() {
		return organizationPersistence.countAll();
	}

	/**
	 * Updates the organization in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OrganizationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param organization the organization
	 * @return the organization that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Organization updateOrganization(Organization organization) {
		return organizationPersistence.update(organization);
	}

	/**
	 */
	@Override
	public void addGroupOrganization(long groupId, long organizationId) {
		groupPersistence.addOrganization(groupId, organizationId);
	}

	/**
	 */
	@Override
	public void addGroupOrganization(long groupId, Organization organization) {
		groupPersistence.addOrganization(groupId, organization);
	}

	/**
	 */
	@Override
	public void addGroupOrganizations(long groupId, long[] organizationIds) {
		groupPersistence.addOrganizations(groupId, organizationIds);
	}

	/**
	 */
	@Override
	public void addGroupOrganizations(
		long groupId, List<Organization> organizations) {

		groupPersistence.addOrganizations(groupId, organizations);
	}

	/**
	 */
	@Override
	public void clearGroupOrganizations(long groupId) {
		groupPersistence.clearOrganizations(groupId);
	}

	/**
	 */
	@Override
	public void deleteGroupOrganization(long groupId, long organizationId) {
		groupPersistence.removeOrganization(groupId, organizationId);
	}

	/**
	 */
	@Override
	public void deleteGroupOrganization(
		long groupId, Organization organization) {

		groupPersistence.removeOrganization(groupId, organization);
	}

	/**
	 */
	@Override
	public void deleteGroupOrganizations(long groupId, long[] organizationIds) {
		groupPersistence.removeOrganizations(groupId, organizationIds);
	}

	/**
	 */
	@Override
	public void deleteGroupOrganizations(
		long groupId, List<Organization> organizations) {

		groupPersistence.removeOrganizations(groupId, organizations);
	}

	/**
	 * Returns the groupIds of the groups associated with the organization.
	 *
	 * @param organizationId the organizationId of the organization
	 * @return long[] the groupIds of groups associated with the organization
	 */
	@Override
	public long[] getGroupPrimaryKeys(long organizationId) {
		return organizationPersistence.getGroupPrimaryKeys(organizationId);
	}

	/**
	 */
	@Override
	public List<Organization> getGroupOrganizations(long groupId) {
		return groupPersistence.getOrganizations(groupId);
	}

	/**
	 */
	@Override
	public List<Organization> getGroupOrganizations(
		long groupId, int start, int end) {

		return groupPersistence.getOrganizations(groupId, start, end);
	}

	/**
	 */
	@Override
	public List<Organization> getGroupOrganizations(
		long groupId, int start, int end,
		OrderByComparator<Organization> orderByComparator) {

		return groupPersistence.getOrganizations(
			groupId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getGroupOrganizationsCount(long groupId) {
		return groupPersistence.getOrganizationsSize(groupId);
	}

	/**
	 */
	@Override
	public boolean hasGroupOrganization(long groupId, long organizationId) {
		return groupPersistence.containsOrganization(groupId, organizationId);
	}

	/**
	 */
	@Override
	public boolean hasGroupOrganizations(long groupId) {
		return groupPersistence.containsOrganizations(groupId);
	}

	/**
	 */
	@Override
	public void setGroupOrganizations(long groupId, long[] organizationIds) {
		groupPersistence.setOrganizations(groupId, organizationIds);
	}

	/**
	 */
	@Override
	public void addUserOrganization(long userId, long organizationId) {
		userPersistence.addOrganization(userId, organizationId);
	}

	/**
	 */
	@Override
	public void addUserOrganization(long userId, Organization organization) {
		userPersistence.addOrganization(userId, organization);
	}

	/**
	 */
	@Override
	public void addUserOrganizations(long userId, long[] organizationIds) {
		userPersistence.addOrganizations(userId, organizationIds);
	}

	/**
	 */
	@Override
	public void addUserOrganizations(
		long userId, List<Organization> organizations) {

		userPersistence.addOrganizations(userId, organizations);
	}

	/**
	 */
	@Override
	public void clearUserOrganizations(long userId) {
		userPersistence.clearOrganizations(userId);
	}

	/**
	 */
	@Override
	public void deleteUserOrganization(long userId, long organizationId) {
		userPersistence.removeOrganization(userId, organizationId);
	}

	/**
	 */
	@Override
	public void deleteUserOrganization(long userId, Organization organization) {
		userPersistence.removeOrganization(userId, organization);
	}

	/**
	 */
	@Override
	public void deleteUserOrganizations(long userId, long[] organizationIds) {
		userPersistence.removeOrganizations(userId, organizationIds);
	}

	/**
	 */
	@Override
	public void deleteUserOrganizations(
		long userId, List<Organization> organizations) {

		userPersistence.removeOrganizations(userId, organizations);
	}

	/**
	 * Returns the userIds of the users associated with the organization.
	 *
	 * @param organizationId the organizationId of the organization
	 * @return long[] the userIds of users associated with the organization
	 */
	@Override
	public long[] getUserPrimaryKeys(long organizationId) {
		return organizationPersistence.getUserPrimaryKeys(organizationId);
	}

	/**
	 */
	@Override
	public List<Organization> getUserOrganizations(long userId) {
		return userPersistence.getOrganizations(userId);
	}

	/**
	 */
	@Override
	public List<Organization> getUserOrganizations(
		long userId, int start, int end) {

		return userPersistence.getOrganizations(userId, start, end);
	}

	/**
	 */
	@Override
	public List<Organization> getUserOrganizations(
		long userId, int start, int end,
		OrderByComparator<Organization> orderByComparator) {

		return userPersistence.getOrganizations(
			userId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getUserOrganizationsCount(long userId) {
		return userPersistence.getOrganizationsSize(userId);
	}

	/**
	 */
	@Override
	public boolean hasUserOrganization(long userId, long organizationId) {
		return userPersistence.containsOrganization(userId, organizationId);
	}

	/**
	 */
	@Override
	public boolean hasUserOrganizations(long userId) {
		return userPersistence.containsOrganizations(userId);
	}

	/**
	 */
	@Override
	public void setUserOrganizations(long userId, long[] organizationIds) {
		userPersistence.setOrganizations(userId, organizationIds);
	}

	/**
	 * Returns the organization local service.
	 *
	 * @return the organization local service
	 */
	public OrganizationLocalService getOrganizationLocalService() {
		return organizationLocalService;
	}

	/**
	 * Sets the organization local service.
	 *
	 * @param organizationLocalService the organization local service
	 */
	public void setOrganizationLocalService(
		OrganizationLocalService organizationLocalService) {

		this.organizationLocalService = organizationLocalService;
	}

	/**
	 * Returns the organization persistence.
	 *
	 * @return the organization persistence
	 */
	public OrganizationPersistence getOrganizationPersistence() {
		return organizationPersistence;
	}

	/**
	 * Sets the organization persistence.
	 *
	 * @param organizationPersistence the organization persistence
	 */
	public void setOrganizationPersistence(
		OrganizationPersistence organizationPersistence) {

		this.organizationPersistence = organizationPersistence;
	}

	/**
	 * Returns the organization finder.
	 *
	 * @return the organization finder
	 */
	public OrganizationFinder getOrganizationFinder() {
		return organizationFinder;
	}

	/**
	 * Sets the organization finder.
	 *
	 * @param organizationFinder the organization finder
	 */
	public void setOrganizationFinder(OrganizationFinder organizationFinder) {
		this.organizationFinder = organizationFinder;
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
			"com.liferay.portal.kernel.model.Organization",
			organizationLocalService);

		_setLocalServiceUtilService(organizationLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.kernel.model.Organization");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return OrganizationLocalService.class.getName();
	}

	@Override
	public CTPersistence<Organization> getCTPersistence() {
		return organizationPersistence;
	}

	@Override
	public Class<Organization> getModelClass() {
		return Organization.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<Organization>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(organizationPersistence);
	}

	protected String getModelClassName() {
		return Organization.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = organizationPersistence.getDataSource();

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
		OrganizationLocalService organizationLocalService) {

		try {
			Field field = OrganizationLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, organizationLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = OrganizationLocalService.class)
	protected OrganizationLocalService organizationLocalService;

	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;

	@BeanReference(type = OrganizationFinder.class)
	protected OrganizationFinder organizationFinder;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;

	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		OrganizationLocalServiceBaseImpl.class);

	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}