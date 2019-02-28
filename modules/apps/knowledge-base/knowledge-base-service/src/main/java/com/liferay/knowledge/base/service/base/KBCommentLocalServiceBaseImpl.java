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

package com.liferay.knowledge.base.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.knowledge.base.model.KBComment;
import com.liferay.knowledge.base.service.KBCommentLocalService;
import com.liferay.knowledge.base.service.persistence.KBArticleFinder;
import com.liferay.knowledge.base.service.persistence.KBArticlePersistence;
import com.liferay.knowledge.base.service.persistence.KBCommentPersistence;
import com.liferay.knowledge.base.service.persistence.KBFolderFinder;
import com.liferay.knowledge.base.service.persistence.KBFolderPersistence;
import com.liferay.knowledge.base.service.persistence.KBTemplatePersistence;
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
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.ratings.kernel.service.persistence.RatingsEntryPersistence;
import com.liferay.social.kernel.service.persistence.SocialActivityPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the kb comment local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.knowledge.base.service.impl.KBCommentLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.knowledge.base.service.impl.KBCommentLocalServiceImpl
 * @generated
 */
@ProviderType
public abstract class KBCommentLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements KBCommentLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>KBCommentLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.knowledge.base.service.KBCommentLocalServiceUtil</code>.
	 */

	/**
	 * Adds the kb comment to the database. Also notifies the appropriate model listeners.
	 *
	 * @param kbComment the kb comment
	 * @return the kb comment that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public KBComment addKBComment(KBComment kbComment) {
		kbComment.setNew(true);

		return kbCommentPersistence.update(kbComment);
	}

	/**
	 * Creates a new kb comment with the primary key. Does not add the kb comment to the database.
	 *
	 * @param kbCommentId the primary key for the new kb comment
	 * @return the new kb comment
	 */
	@Override
	@Transactional(enabled = false)
	public KBComment createKBComment(long kbCommentId) {
		return kbCommentPersistence.create(kbCommentId);
	}

	/**
	 * Deletes the kb comment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kbCommentId the primary key of the kb comment
	 * @return the kb comment that was removed
	 * @throws PortalException if a kb comment with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public KBComment deleteKBComment(long kbCommentId) throws PortalException {
		return kbCommentPersistence.remove(kbCommentId);
	}

	/**
	 * Deletes the kb comment from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kbComment the kb comment
	 * @return the kb comment that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public KBComment deleteKBComment(KBComment kbComment)
		throws PortalException {

		return kbCommentPersistence.remove(kbComment);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			KBComment.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return kbCommentPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.knowledge.base.model.impl.KBCommentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return kbCommentPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.knowledge.base.model.impl.KBCommentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return kbCommentPersistence.findWithDynamicQuery(
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
		return kbCommentPersistence.countWithDynamicQuery(dynamicQuery);
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

		return kbCommentPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public KBComment fetchKBComment(long kbCommentId) {
		return kbCommentPersistence.fetchByPrimaryKey(kbCommentId);
	}

	/**
	 * Returns the kb comment matching the UUID and group.
	 *
	 * @param uuid the kb comment's UUID
	 * @param groupId the primary key of the group
	 * @return the matching kb comment, or <code>null</code> if a matching kb comment could not be found
	 */
	@Override
	public KBComment fetchKBCommentByUuidAndGroupId(String uuid, long groupId) {
		return kbCommentPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the kb comment with the primary key.
	 *
	 * @param kbCommentId the primary key of the kb comment
	 * @return the kb comment
	 * @throws PortalException if a kb comment with the primary key could not be found
	 */
	@Override
	public KBComment getKBComment(long kbCommentId) throws PortalException {
		return kbCommentPersistence.findByPrimaryKey(kbCommentId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(kbCommentLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(KBComment.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("kbCommentId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			kbCommentLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(KBComment.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"kbCommentId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(kbCommentLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(KBComment.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("kbCommentId");
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

					StagedModelType stagedModelType =
						exportActionableDynamicQuery.getStagedModelType();

					long referrerClassNameId =
						stagedModelType.getReferrerClassNameId();

					Property classNameIdProperty = PropertyFactoryUtil.forName(
						"classNameId");

					if ((referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ALL) &&
						(referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ANY)) {

						dynamicQuery.add(
							classNameIdProperty.eq(
								stagedModelType.getReferrerClassNameId()));
					}
					else if (referrerClassNameId ==
								StagedModelType.REFERRER_CLASS_NAME_ID_ANY) {

						dynamicQuery.add(classNameIdProperty.isNotNull());
					}
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<KBComment>() {

				@Override
				public void performAction(KBComment kbComment)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, kbComment);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(KBComment.class.getName()),
				StagedModelType.REFERRER_CLASS_NAME_ID_ALL));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return kbCommentLocalService.deleteKBComment((KBComment)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return kbCommentPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the kb comments matching the UUID and company.
	 *
	 * @param uuid the UUID of the kb comments
	 * @param companyId the primary key of the company
	 * @return the matching kb comments, or an empty list if no matches were found
	 */
	@Override
	public List<KBComment> getKBCommentsByUuidAndCompanyId(
		String uuid, long companyId) {

		return kbCommentPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of kb comments matching the UUID and company.
	 *
	 * @param uuid the UUID of the kb comments
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of kb comments
	 * @param end the upper bound of the range of kb comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching kb comments, or an empty list if no matches were found
	 */
	@Override
	public List<KBComment> getKBCommentsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<KBComment> orderByComparator) {

		return kbCommentPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the kb comment matching the UUID and group.
	 *
	 * @param uuid the kb comment's UUID
	 * @param groupId the primary key of the group
	 * @return the matching kb comment
	 * @throws PortalException if a matching kb comment could not be found
	 */
	@Override
	public KBComment getKBCommentByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return kbCommentPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the kb comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.knowledge.base.model.impl.KBCommentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kb comments
	 * @param end the upper bound of the range of kb comments (not inclusive)
	 * @return the range of kb comments
	 */
	@Override
	public List<KBComment> getKBComments(int start, int end) {
		return kbCommentPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of kb comments.
	 *
	 * @return the number of kb comments
	 */
	@Override
	public int getKBCommentsCount() {
		return kbCommentPersistence.countAll();
	}

	/**
	 * Updates the kb comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param kbComment the kb comment
	 * @return the kb comment that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public KBComment updateKBComment(KBComment kbComment) {
		return kbCommentPersistence.update(kbComment);
	}

	/**
	 * Returns the kb article local service.
	 *
	 * @return the kb article local service
	 */
	public com.liferay.knowledge.base.service.KBArticleLocalService
		getKBArticleLocalService() {

		return kbArticleLocalService;
	}

	/**
	 * Sets the kb article local service.
	 *
	 * @param kbArticleLocalService the kb article local service
	 */
	public void setKBArticleLocalService(
		com.liferay.knowledge.base.service.KBArticleLocalService
			kbArticleLocalService) {

		this.kbArticleLocalService = kbArticleLocalService;
	}

	/**
	 * Returns the kb article persistence.
	 *
	 * @return the kb article persistence
	 */
	public KBArticlePersistence getKBArticlePersistence() {
		return kbArticlePersistence;
	}

	/**
	 * Sets the kb article persistence.
	 *
	 * @param kbArticlePersistence the kb article persistence
	 */
	public void setKBArticlePersistence(
		KBArticlePersistence kbArticlePersistence) {

		this.kbArticlePersistence = kbArticlePersistence;
	}

	/**
	 * Returns the kb article finder.
	 *
	 * @return the kb article finder
	 */
	public KBArticleFinder getKBArticleFinder() {
		return kbArticleFinder;
	}

	/**
	 * Sets the kb article finder.
	 *
	 * @param kbArticleFinder the kb article finder
	 */
	public void setKBArticleFinder(KBArticleFinder kbArticleFinder) {
		this.kbArticleFinder = kbArticleFinder;
	}

	/**
	 * Returns the kb comment local service.
	 *
	 * @return the kb comment local service
	 */
	public KBCommentLocalService getKBCommentLocalService() {
		return kbCommentLocalService;
	}

	/**
	 * Sets the kb comment local service.
	 *
	 * @param kbCommentLocalService the kb comment local service
	 */
	public void setKBCommentLocalService(
		KBCommentLocalService kbCommentLocalService) {

		this.kbCommentLocalService = kbCommentLocalService;
	}

	/**
	 * Returns the kb comment persistence.
	 *
	 * @return the kb comment persistence
	 */
	public KBCommentPersistence getKBCommentPersistence() {
		return kbCommentPersistence;
	}

	/**
	 * Sets the kb comment persistence.
	 *
	 * @param kbCommentPersistence the kb comment persistence
	 */
	public void setKBCommentPersistence(
		KBCommentPersistence kbCommentPersistence) {

		this.kbCommentPersistence = kbCommentPersistence;
	}

	/**
	 * Returns the kb folder local service.
	 *
	 * @return the kb folder local service
	 */
	public com.liferay.knowledge.base.service.KBFolderLocalService
		getKBFolderLocalService() {

		return kbFolderLocalService;
	}

	/**
	 * Sets the kb folder local service.
	 *
	 * @param kbFolderLocalService the kb folder local service
	 */
	public void setKBFolderLocalService(
		com.liferay.knowledge.base.service.KBFolderLocalService
			kbFolderLocalService) {

		this.kbFolderLocalService = kbFolderLocalService;
	}

	/**
	 * Returns the kb folder persistence.
	 *
	 * @return the kb folder persistence
	 */
	public KBFolderPersistence getKBFolderPersistence() {
		return kbFolderPersistence;
	}

	/**
	 * Sets the kb folder persistence.
	 *
	 * @param kbFolderPersistence the kb folder persistence
	 */
	public void setKBFolderPersistence(
		KBFolderPersistence kbFolderPersistence) {

		this.kbFolderPersistence = kbFolderPersistence;
	}

	/**
	 * Returns the kb folder finder.
	 *
	 * @return the kb folder finder
	 */
	public KBFolderFinder getKBFolderFinder() {
		return kbFolderFinder;
	}

	/**
	 * Sets the kb folder finder.
	 *
	 * @param kbFolderFinder the kb folder finder
	 */
	public void setKBFolderFinder(KBFolderFinder kbFolderFinder) {
		this.kbFolderFinder = kbFolderFinder;
	}

	/**
	 * Returns the kb template local service.
	 *
	 * @return the kb template local service
	 */
	public com.liferay.knowledge.base.service.KBTemplateLocalService
		getKBTemplateLocalService() {

		return kbTemplateLocalService;
	}

	/**
	 * Sets the kb template local service.
	 *
	 * @param kbTemplateLocalService the kb template local service
	 */
	public void setKBTemplateLocalService(
		com.liferay.knowledge.base.service.KBTemplateLocalService
			kbTemplateLocalService) {

		this.kbTemplateLocalService = kbTemplateLocalService;
	}

	/**
	 * Returns the kb template persistence.
	 *
	 * @return the kb template persistence
	 */
	public KBTemplatePersistence getKBTemplatePersistence() {
		return kbTemplatePersistence;
	}

	/**
	 * Sets the kb template persistence.
	 *
	 * @param kbTemplatePersistence the kb template persistence
	 */
	public void setKBTemplatePersistence(
		KBTemplatePersistence kbTemplatePersistence) {

		this.kbTemplatePersistence = kbTemplatePersistence;
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

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService
		getClassNameLocalService() {

		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService
			classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {

		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the portlet preferences local service.
	 *
	 * @return the portlet preferences local service
	 */
	public com.liferay.portal.kernel.service.PortletPreferencesLocalService
		getPortletPreferencesLocalService() {

		return portletPreferencesLocalService;
	}

	/**
	 * Sets the portlet preferences local service.
	 *
	 * @param portletPreferencesLocalService the portlet preferences local service
	 */
	public void setPortletPreferencesLocalService(
		com.liferay.portal.kernel.service.PortletPreferencesLocalService
			portletPreferencesLocalService) {

		this.portletPreferencesLocalService = portletPreferencesLocalService;
	}

	/**
	 * Returns the portlet preferences persistence.
	 *
	 * @return the portlet preferences persistence
	 */
	public PortletPreferencesPersistence getPortletPreferencesPersistence() {
		return portletPreferencesPersistence;
	}

	/**
	 * Sets the portlet preferences persistence.
	 *
	 * @param portletPreferencesPersistence the portlet preferences persistence
	 */
	public void setPortletPreferencesPersistence(
		PortletPreferencesPersistence portletPreferencesPersistence) {

		this.portletPreferencesPersistence = portletPreferencesPersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

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
	 * Returns the ratings entry local service.
	 *
	 * @return the ratings entry local service
	 */
	public com.liferay.ratings.kernel.service.RatingsEntryLocalService
		getRatingsEntryLocalService() {

		return ratingsEntryLocalService;
	}

	/**
	 * Sets the ratings entry local service.
	 *
	 * @param ratingsEntryLocalService the ratings entry local service
	 */
	public void setRatingsEntryLocalService(
		com.liferay.ratings.kernel.service.RatingsEntryLocalService
			ratingsEntryLocalService) {

		this.ratingsEntryLocalService = ratingsEntryLocalService;
	}

	/**
	 * Returns the ratings entry persistence.
	 *
	 * @return the ratings entry persistence
	 */
	public RatingsEntryPersistence getRatingsEntryPersistence() {
		return ratingsEntryPersistence;
	}

	/**
	 * Sets the ratings entry persistence.
	 *
	 * @param ratingsEntryPersistence the ratings entry persistence
	 */
	public void setRatingsEntryPersistence(
		RatingsEntryPersistence ratingsEntryPersistence) {

		this.ratingsEntryPersistence = ratingsEntryPersistence;
	}

	/**
	 * Returns the social activity local service.
	 *
	 * @return the social activity local service
	 */
	public com.liferay.social.kernel.service.SocialActivityLocalService
		getSocialActivityLocalService() {

		return socialActivityLocalService;
	}

	/**
	 * Sets the social activity local service.
	 *
	 * @param socialActivityLocalService the social activity local service
	 */
	public void setSocialActivityLocalService(
		com.liferay.social.kernel.service.SocialActivityLocalService
			socialActivityLocalService) {

		this.socialActivityLocalService = socialActivityLocalService;
	}

	/**
	 * Returns the social activity persistence.
	 *
	 * @return the social activity persistence
	 */
	public SocialActivityPersistence getSocialActivityPersistence() {
		return socialActivityPersistence;
	}

	/**
	 * Sets the social activity persistence.
	 *
	 * @param socialActivityPersistence the social activity persistence
	 */
	public void setSocialActivityPersistence(
		SocialActivityPersistence socialActivityPersistence) {

		this.socialActivityPersistence = socialActivityPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.knowledge.base.model.KBComment",
			kbCommentLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.knowledge.base.model.KBComment");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return KBCommentLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return KBComment.class;
	}

	protected String getModelClassName() {
		return KBComment.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = kbCommentPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(
		type = com.liferay.knowledge.base.service.KBArticleLocalService.class
	)
	protected com.liferay.knowledge.base.service.KBArticleLocalService
		kbArticleLocalService;

	@BeanReference(type = KBArticlePersistence.class)
	protected KBArticlePersistence kbArticlePersistence;

	@BeanReference(type = KBArticleFinder.class)
	protected KBArticleFinder kbArticleFinder;

	@BeanReference(type = KBCommentLocalService.class)
	protected KBCommentLocalService kbCommentLocalService;

	@BeanReference(type = KBCommentPersistence.class)
	protected KBCommentPersistence kbCommentPersistence;

	@BeanReference(
		type = com.liferay.knowledge.base.service.KBFolderLocalService.class
	)
	protected com.liferay.knowledge.base.service.KBFolderLocalService
		kbFolderLocalService;

	@BeanReference(type = KBFolderPersistence.class)
	protected KBFolderPersistence kbFolderPersistence;

	@BeanReference(type = KBFolderFinder.class)
	protected KBFolderFinder kbFolderFinder;

	@BeanReference(
		type = com.liferay.knowledge.base.service.KBTemplateLocalService.class
	)
	protected com.liferay.knowledge.base.service.KBTemplateLocalService
		kbTemplateLocalService;

	@BeanReference(type = KBTemplatePersistence.class)
	protected KBTemplatePersistence kbTemplatePersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.PortletPreferencesLocalService.class
	)
	protected com.liferay.portal.kernel.service.PortletPreferencesLocalService
		portletPreferencesLocalService;

	@ServiceReference(type = PortletPreferencesPersistence.class)
	protected PortletPreferencesPersistence portletPreferencesPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@ServiceReference(
		type = com.liferay.ratings.kernel.service.RatingsEntryLocalService.class
	)
	protected com.liferay.ratings.kernel.service.RatingsEntryLocalService
		ratingsEntryLocalService;

	@ServiceReference(type = RatingsEntryPersistence.class)
	protected RatingsEntryPersistence ratingsEntryPersistence;

	@ServiceReference(
		type = com.liferay.social.kernel.service.SocialActivityLocalService.class
	)
	protected com.liferay.social.kernel.service.SocialActivityLocalService
		socialActivityLocalService;

	@ServiceReference(type = SocialActivityPersistence.class)
	protected SocialActivityPersistence socialActivityPersistence;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}