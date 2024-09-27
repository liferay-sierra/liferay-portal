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
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.GroupPersistence;
import com.liferay.portal.kernel.service.persistence.OrganizationPersistence;
import com.liferay.portal.kernel.service.persistence.RolePersistence;
import com.liferay.portal.kernel.service.persistence.TeamPersistence;
import com.liferay.portal.kernel.service.persistence.UserFinder;
import com.liferay.portal.kernel.service.persistence.UserGroupPersistence;
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
 * Provides the base implementation for the user local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.UserLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.UserLocalServiceImpl
 * @generated
 */
public abstract class UserLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, UserLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>UserLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>UserLocalServiceUtil</code>.
	 */

	/**
	 * Adds the user to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param user the user
	 * @return the user that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public User addUser(User user) {
		user.setNew(true);

		return userPersistence.update(user);
	}

	/**
	 * Creates a new user with the primary key. Does not add the user to the database.
	 *
	 * @param userId the primary key for the new user
	 * @return the new user
	 */
	@Override
	@Transactional(enabled = false)
	public User createUser(long userId) {
		return userPersistence.create(userId);
	}

	/**
	 * Deletes the user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userId the primary key of the user
	 * @return the user that was removed
	 * @throws PortalException if a user with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public User deleteUser(long userId) throws PortalException {
		return userPersistence.remove(userId);
	}

	/**
	 * Deletes the user from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param user the user
	 * @return the user that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public User deleteUser(User user) throws PortalException {
		return userPersistence.remove(user);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return userPersistence.dslQuery(dslQuery);
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
			User.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return userPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.UserModelImpl</code>.
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

		return userPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.UserModelImpl</code>.
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

		return userPersistence.findWithDynamicQuery(
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
		return userPersistence.countWithDynamicQuery(dynamicQuery);
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

		return userPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public User fetchUser(long userId) {
		return userPersistence.fetchByPrimaryKey(userId);
	}

	/**
	 * Returns the user with the matching UUID and company.
	 *
	 * @param uuid the user's UUID
	 * @param companyId the primary key of the company
	 * @return the matching user, or <code>null</code> if a matching user could not be found
	 */
	@Override
	public User fetchUserByUuidAndCompanyId(String uuid, long companyId) {
		return userPersistence.fetchByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns the user with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the user's external reference code
	 * @return the matching user, or <code>null</code> if a matching user could not be found
	 */
	@Override
	public User fetchUserByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		return userPersistence.fetchByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #fetchUserByExternalReferenceCode(long, String)}
	 */
	@Deprecated
	@Override
	public User fetchUserByReferenceCode(
		long companyId, String externalReferenceCode) {

		return fetchUserByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the user with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the user's external reference code
	 * @return the matching user
	 * @throws PortalException if a matching user could not be found
	 */
	@Override
	public User getUserByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		return userPersistence.findByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the user with the primary key.
	 *
	 * @param userId the primary key of the user
	 * @return the user
	 * @throws PortalException if a user with the primary key could not be found
	 */
	@Override
	public User getUser(long userId) throws PortalException {
		return userPersistence.findByPrimaryKey(userId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(userLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(User.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("userId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(userLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(User.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("userId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(userLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(User.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("userId");
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
			new ActionableDynamicQuery.PerformActionMethod<User>() {

				@Override
				public void performAction(User user) throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, user);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(User.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return userPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement UserLocalServiceImpl#deleteUser(User) to avoid orphaned data");
		}

		return userLocalService.deleteUser((User)persistedModel);
	}

	@Override
	public BasePersistence<User> getBasePersistence() {
		return userPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return userPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the user with the matching UUID and company.
	 *
	 * @param uuid the user's UUID
	 * @param companyId the primary key of the company
	 * @return the matching user
	 * @throws PortalException if a matching user could not be found
	 */
	@Override
	public User getUserByUuidAndCompanyId(String uuid, long companyId)
		throws PortalException {

		return userPersistence.findByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns a range of all the users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.UserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of users
	 * @param end the upper bound of the range of users (not inclusive)
	 * @return the range of users
	 */
	@Override
	public List<User> getUsers(int start, int end) {
		return userPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of users.
	 *
	 * @return the number of users
	 */
	@Override
	public int getUsersCount() {
		return userPersistence.countAll();
	}

	/**
	 * Updates the user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param user the user
	 * @return the user that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public User updateUser(User user) {
		return userPersistence.update(user);
	}

	/**
	 */
	@Override
	public void addGroupUser(long groupId, long userId) {
		groupPersistence.addUser(groupId, userId);
	}

	/**
	 */
	@Override
	public void addGroupUser(long groupId, User user) {
		groupPersistence.addUser(groupId, user);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void addGroupUsers(long groupId, long[] userIds)
		throws PortalException {

		groupPersistence.addUsers(groupId, userIds);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void addGroupUsers(long groupId, List<User> users)
		throws PortalException {

		groupPersistence.addUsers(groupId, users);
	}

	/**
	 */
	@Override
	public void clearGroupUsers(long groupId) {
		groupPersistence.clearUsers(groupId);
	}

	/**
	 */
	@Override
	public void deleteGroupUser(long groupId, long userId) {
		groupPersistence.removeUser(groupId, userId);
	}

	/**
	 */
	@Override
	public void deleteGroupUser(long groupId, User user) {
		groupPersistence.removeUser(groupId, user);
	}

	/**
	 */
	@Override
	public void deleteGroupUsers(long groupId, long[] userIds) {
		groupPersistence.removeUsers(groupId, userIds);
	}

	/**
	 */
	@Override
	public void deleteGroupUsers(long groupId, List<User> users) {
		groupPersistence.removeUsers(groupId, users);
	}

	/**
	 * Returns the groupIds of the groups associated with the user.
	 *
	 * @param userId the userId of the user
	 * @return long[] the groupIds of groups associated with the user
	 */
	@Override
	public long[] getGroupPrimaryKeys(long userId) {
		return userPersistence.getGroupPrimaryKeys(userId);
	}

	/**
	 */
	@Override
	public List<User> getGroupUsers(long groupId) {
		return groupPersistence.getUsers(groupId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public List<User> getGroupUsers(long groupId, int start, int end)
		throws PortalException {

		return groupPersistence.getUsers(groupId, start, end);
	}

	/**
	 */
	@Override
	public List<User> getGroupUsers(
		long groupId, int start, int end,
		OrderByComparator<User> orderByComparator) {

		return groupPersistence.getUsers(
			groupId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getGroupUsersCount(long groupId) {
		return groupPersistence.getUsersSize(groupId);
	}

	/**
	 */
	@Override
	public boolean hasGroupUser(long groupId, long userId) {
		return groupPersistence.containsUser(groupId, userId);
	}

	/**
	 */
	@Override
	public boolean hasGroupUsers(long groupId) {
		return groupPersistence.containsUsers(groupId);
	}

	/**
	 */
	@Override
	public void setGroupUsers(long groupId, long[] userIds) {
		groupPersistence.setUsers(groupId, userIds);
	}

	/**
	 */
	@Override
	public void addOrganizationUser(long organizationId, long userId) {
		organizationPersistence.addUser(organizationId, userId);
	}

	/**
	 */
	@Override
	public void addOrganizationUser(long organizationId, User user) {
		organizationPersistence.addUser(organizationId, user);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void addOrganizationUsers(long organizationId, long[] userIds)
		throws PortalException {

		organizationPersistence.addUsers(organizationId, userIds);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void addOrganizationUsers(long organizationId, List<User> users)
		throws PortalException {

		organizationPersistence.addUsers(organizationId, users);
	}

	/**
	 */
	@Override
	public void clearOrganizationUsers(long organizationId) {
		organizationPersistence.clearUsers(organizationId);
	}

	/**
	 */
	@Override
	public void deleteOrganizationUser(long organizationId, long userId) {
		organizationPersistence.removeUser(organizationId, userId);
	}

	/**
	 */
	@Override
	public void deleteOrganizationUser(long organizationId, User user) {
		organizationPersistence.removeUser(organizationId, user);
	}

	/**
	 */
	@Override
	public void deleteOrganizationUsers(long organizationId, long[] userIds) {
		organizationPersistence.removeUsers(organizationId, userIds);
	}

	/**
	 */
	@Override
	public void deleteOrganizationUsers(long organizationId, List<User> users) {
		organizationPersistence.removeUsers(organizationId, users);
	}

	/**
	 * Returns the organizationIds of the organizations associated with the user.
	 *
	 * @param userId the userId of the user
	 * @return long[] the organizationIds of organizations associated with the user
	 */
	@Override
	public long[] getOrganizationPrimaryKeys(long userId) {
		return userPersistence.getOrganizationPrimaryKeys(userId);
	}

	/**
	 */
	@Override
	public List<User> getOrganizationUsers(long organizationId) {
		return organizationPersistence.getUsers(organizationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public List<User> getOrganizationUsers(
			long organizationId, int start, int end)
		throws PortalException {

		return organizationPersistence.getUsers(organizationId, start, end);
	}

	/**
	 */
	@Override
	public List<User> getOrganizationUsers(
		long organizationId, int start, int end,
		OrderByComparator<User> orderByComparator) {

		return organizationPersistence.getUsers(
			organizationId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getOrganizationUsersCount(long organizationId) {
		return organizationPersistence.getUsersSize(organizationId);
	}

	/**
	 */
	@Override
	public boolean hasOrganizationUser(long organizationId, long userId) {
		return organizationPersistence.containsUser(organizationId, userId);
	}

	/**
	 */
	@Override
	public boolean hasOrganizationUsers(long organizationId) {
		return organizationPersistence.containsUsers(organizationId);
	}

	/**
	 */
	@Override
	public void setOrganizationUsers(long organizationId, long[] userIds) {
		organizationPersistence.setUsers(organizationId, userIds);
	}

	/**
	 */
	@Override
	public void addRoleUser(long roleId, long userId) {
		rolePersistence.addUser(roleId, userId);
	}

	/**
	 */
	@Override
	public void addRoleUser(long roleId, User user) {
		rolePersistence.addUser(roleId, user);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void addRoleUsers(long roleId, long[] userIds)
		throws PortalException {

		rolePersistence.addUsers(roleId, userIds);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void addRoleUsers(long roleId, List<User> users)
		throws PortalException {

		rolePersistence.addUsers(roleId, users);
	}

	/**
	 */
	@Override
	public void clearRoleUsers(long roleId) {
		rolePersistence.clearUsers(roleId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void deleteRoleUser(long roleId, long userId)
		throws PortalException {

		rolePersistence.removeUser(roleId, userId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void deleteRoleUser(long roleId, User user) throws PortalException {
		rolePersistence.removeUser(roleId, user);
	}

	/**
	 */
	@Override
	public void deleteRoleUsers(long roleId, long[] userIds) {
		rolePersistence.removeUsers(roleId, userIds);
	}

	/**
	 */
	@Override
	public void deleteRoleUsers(long roleId, List<User> users) {
		rolePersistence.removeUsers(roleId, users);
	}

	/**
	 * Returns the roleIds of the roles associated with the user.
	 *
	 * @param userId the userId of the user
	 * @return long[] the roleIds of roles associated with the user
	 */
	@Override
	public long[] getRolePrimaryKeys(long userId) {
		return userPersistence.getRolePrimaryKeys(userId);
	}

	/**
	 */
	@Override
	public List<User> getRoleUsers(long roleId) {
		return rolePersistence.getUsers(roleId);
	}

	/**
	 */
	@Override
	public List<User> getRoleUsers(long roleId, int start, int end) {
		return rolePersistence.getUsers(roleId, start, end);
	}

	/**
	 */
	@Override
	public List<User> getRoleUsers(
		long roleId, int start, int end,
		OrderByComparator<User> orderByComparator) {

		return rolePersistence.getUsers(roleId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getRoleUsersCount(long roleId) {
		return rolePersistence.getUsersSize(roleId);
	}

	/**
	 */
	@Override
	public boolean hasRoleUser(long roleId, long userId) {
		return rolePersistence.containsUser(roleId, userId);
	}

	/**
	 */
	@Override
	public boolean hasRoleUsers(long roleId) {
		return rolePersistence.containsUsers(roleId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void setRoleUsers(long roleId, long[] userIds)
		throws PortalException {

		rolePersistence.setUsers(roleId, userIds);
	}

	/**
	 */
	@Override
	public void addTeamUser(long teamId, long userId) {
		teamPersistence.addUser(teamId, userId);
	}

	/**
	 */
	@Override
	public void addTeamUser(long teamId, User user) {
		teamPersistence.addUser(teamId, user);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void addTeamUsers(long teamId, long[] userIds)
		throws PortalException {

		teamPersistence.addUsers(teamId, userIds);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void addTeamUsers(long teamId, List<User> users)
		throws PortalException {

		teamPersistence.addUsers(teamId, users);
	}

	/**
	 */
	@Override
	public void clearTeamUsers(long teamId) {
		teamPersistence.clearUsers(teamId);
	}

	/**
	 */
	@Override
	public void deleteTeamUser(long teamId, long userId) {
		teamPersistence.removeUser(teamId, userId);
	}

	/**
	 */
	@Override
	public void deleteTeamUser(long teamId, User user) {
		teamPersistence.removeUser(teamId, user);
	}

	/**
	 */
	@Override
	public void deleteTeamUsers(long teamId, long[] userIds) {
		teamPersistence.removeUsers(teamId, userIds);
	}

	/**
	 */
	@Override
	public void deleteTeamUsers(long teamId, List<User> users) {
		teamPersistence.removeUsers(teamId, users);
	}

	/**
	 * Returns the teamIds of the teams associated with the user.
	 *
	 * @param userId the userId of the user
	 * @return long[] the teamIds of teams associated with the user
	 */
	@Override
	public long[] getTeamPrimaryKeys(long userId) {
		return userPersistence.getTeamPrimaryKeys(userId);
	}

	/**
	 */
	@Override
	public List<User> getTeamUsers(long teamId) {
		return teamPersistence.getUsers(teamId);
	}

	/**
	 */
	@Override
	public List<User> getTeamUsers(long teamId, int start, int end) {
		return teamPersistence.getUsers(teamId, start, end);
	}

	/**
	 */
	@Override
	public List<User> getTeamUsers(
		long teamId, int start, int end,
		OrderByComparator<User> orderByComparator) {

		return teamPersistence.getUsers(teamId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getTeamUsersCount(long teamId) {
		return teamPersistence.getUsersSize(teamId);
	}

	/**
	 */
	@Override
	public boolean hasTeamUser(long teamId, long userId) {
		return teamPersistence.containsUser(teamId, userId);
	}

	/**
	 */
	@Override
	public boolean hasTeamUsers(long teamId) {
		return teamPersistence.containsUsers(teamId);
	}

	/**
	 */
	@Override
	public void setTeamUsers(long teamId, long[] userIds) {
		teamPersistence.setUsers(teamId, userIds);
	}

	/**
	 */
	@Override
	public void addUserGroupUser(long userGroupId, long userId) {
		userGroupPersistence.addUser(userGroupId, userId);
	}

	/**
	 */
	@Override
	public void addUserGroupUser(long userGroupId, User user) {
		userGroupPersistence.addUser(userGroupId, user);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void addUserGroupUsers(long userGroupId, long[] userIds)
		throws PortalException {

		userGroupPersistence.addUsers(userGroupId, userIds);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void addUserGroupUsers(long userGroupId, List<User> users)
		throws PortalException {

		userGroupPersistence.addUsers(userGroupId, users);
	}

	/**
	 */
	@Override
	public void clearUserGroupUsers(long userGroupId) {
		userGroupPersistence.clearUsers(userGroupId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void deleteUserGroupUser(long userGroupId, long userId)
		throws PortalException {

		userGroupPersistence.removeUser(userGroupId, userId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void deleteUserGroupUser(long userGroupId, User user)
		throws PortalException {

		userGroupPersistence.removeUser(userGroupId, user);
	}

	/**
	 */
	@Override
	public void deleteUserGroupUsers(long userGroupId, long[] userIds) {
		userGroupPersistence.removeUsers(userGroupId, userIds);
	}

	/**
	 */
	@Override
	public void deleteUserGroupUsers(long userGroupId, List<User> users) {
		userGroupPersistence.removeUsers(userGroupId, users);
	}

	/**
	 * Returns the userGroupIds of the user groups associated with the user.
	 *
	 * @param userId the userId of the user
	 * @return long[] the userGroupIds of user groups associated with the user
	 */
	@Override
	public long[] getUserGroupPrimaryKeys(long userId) {
		return userPersistence.getUserGroupPrimaryKeys(userId);
	}

	/**
	 */
	@Override
	public List<User> getUserGroupUsers(long userGroupId) {
		return userGroupPersistence.getUsers(userGroupId);
	}

	/**
	 */
	@Override
	public List<User> getUserGroupUsers(long userGroupId, int start, int end) {
		return userGroupPersistence.getUsers(userGroupId, start, end);
	}

	/**
	 */
	@Override
	public List<User> getUserGroupUsers(
		long userGroupId, int start, int end,
		OrderByComparator<User> orderByComparator) {

		return userGroupPersistence.getUsers(
			userGroupId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getUserGroupUsersCount(long userGroupId) {
		return userGroupPersistence.getUsersSize(userGroupId);
	}

	/**
	 */
	@Override
	public boolean hasUserGroupUser(long userGroupId, long userId) {
		return userGroupPersistence.containsUser(userGroupId, userId);
	}

	/**
	 */
	@Override
	public boolean hasUserGroupUsers(long userGroupId) {
		return userGroupPersistence.containsUsers(userGroupId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void setUserGroupUsers(long userGroupId, long[] userIds)
		throws PortalException {

		userGroupPersistence.setUsers(userGroupId, userIds);
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
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
			"com.liferay.portal.kernel.model.User", userLocalService);

		_setLocalServiceUtilService(userLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.kernel.model.User");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return UserLocalService.class.getName();
	}

	@Override
	public CTPersistence<User> getCTPersistence() {
		return userPersistence;
	}

	@Override
	public Class<User> getModelClass() {
		return User.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<User>, R, E> updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(userPersistence);
	}

	protected String getModelClassName() {
		return User.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = userPersistence.getDataSource();

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
		UserLocalService userLocalService) {

		try {
			Field field = UserLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, userLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;

	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;

	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;

	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;

	@BeanReference(type = TeamPersistence.class)
	protected TeamPersistence teamPersistence;

	@BeanReference(type = UserGroupPersistence.class)
	protected UserGroupPersistence userGroupPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		UserLocalServiceBaseImpl.class);

	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}