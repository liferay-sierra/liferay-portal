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

package com.liferay.dynamic.data.mapping.service.base;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureService;
import com.liferay.dynamic.data.mapping.service.persistence.DDMDataProviderInstanceLinkPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureFinder;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureLayoutPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureLinkPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructurePersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureVersionPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateFinder;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplatePersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.GroupPersistence;
import com.liferay.portal.kernel.service.persistence.SystemEventPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the ddm structure remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.dynamic.data.mapping.service.impl.DDMStructureServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMStructureServiceImpl
 * @generated
 */
public abstract class DDMStructureServiceBaseImpl
	extends BaseServiceImpl
	implements DDMStructureService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>DDMStructureService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.dynamic.data.mapping.service.DDMStructureServiceUtil</code>.
	 */

	/**
	 * Returns the ddm structure local service.
	 *
	 * @return the ddm structure local service
	 */
	public com.liferay.dynamic.data.mapping.service.DDMStructureLocalService
		getDDMStructureLocalService() {

		return ddmStructureLocalService;
	}

	/**
	 * Sets the ddm structure local service.
	 *
	 * @param ddmStructureLocalService the ddm structure local service
	 */
	public void setDDMStructureLocalService(
		com.liferay.dynamic.data.mapping.service.DDMStructureLocalService
			ddmStructureLocalService) {

		this.ddmStructureLocalService = ddmStructureLocalService;
	}

	/**
	 * Returns the ddm structure remote service.
	 *
	 * @return the ddm structure remote service
	 */
	public DDMStructureService getDDMStructureService() {
		return ddmStructureService;
	}

	/**
	 * Sets the ddm structure remote service.
	 *
	 * @param ddmStructureService the ddm structure remote service
	 */
	public void setDDMStructureService(
		DDMStructureService ddmStructureService) {

		this.ddmStructureService = ddmStructureService;
	}

	/**
	 * Returns the ddm structure persistence.
	 *
	 * @return the ddm structure persistence
	 */
	public DDMStructurePersistence getDDMStructurePersistence() {
		return ddmStructurePersistence;
	}

	/**
	 * Sets the ddm structure persistence.
	 *
	 * @param ddmStructurePersistence the ddm structure persistence
	 */
	public void setDDMStructurePersistence(
		DDMStructurePersistence ddmStructurePersistence) {

		this.ddmStructurePersistence = ddmStructurePersistence;
	}

	/**
	 * Returns the ddm structure finder.
	 *
	 * @return the ddm structure finder
	 */
	public DDMStructureFinder getDDMStructureFinder() {
		return ddmStructureFinder;
	}

	/**
	 * Sets the ddm structure finder.
	 *
	 * @param ddmStructureFinder the ddm structure finder
	 */
	public void setDDMStructureFinder(DDMStructureFinder ddmStructureFinder) {
		this.ddmStructureFinder = ddmStructureFinder;
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
	 * Returns the ddm data provider instance link local service.
	 *
	 * @return the ddm data provider instance link local service
	 */
	public com.liferay.dynamic.data.mapping.service.
		DDMDataProviderInstanceLinkLocalService
			getDDMDataProviderInstanceLinkLocalService() {

		return ddmDataProviderInstanceLinkLocalService;
	}

	/**
	 * Sets the ddm data provider instance link local service.
	 *
	 * @param ddmDataProviderInstanceLinkLocalService the ddm data provider instance link local service
	 */
	public void setDDMDataProviderInstanceLinkLocalService(
		com.liferay.dynamic.data.mapping.service.
			DDMDataProviderInstanceLinkLocalService
				ddmDataProviderInstanceLinkLocalService) {

		this.ddmDataProviderInstanceLinkLocalService =
			ddmDataProviderInstanceLinkLocalService;
	}

	/**
	 * Returns the ddm data provider instance link persistence.
	 *
	 * @return the ddm data provider instance link persistence
	 */
	public DDMDataProviderInstanceLinkPersistence
		getDDMDataProviderInstanceLinkPersistence() {

		return ddmDataProviderInstanceLinkPersistence;
	}

	/**
	 * Sets the ddm data provider instance link persistence.
	 *
	 * @param ddmDataProviderInstanceLinkPersistence the ddm data provider instance link persistence
	 */
	public void setDDMDataProviderInstanceLinkPersistence(
		DDMDataProviderInstanceLinkPersistence
			ddmDataProviderInstanceLinkPersistence) {

		this.ddmDataProviderInstanceLinkPersistence =
			ddmDataProviderInstanceLinkPersistence;
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
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService
		getClassNameService() {

		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {

		this.classNameService = classNameService;
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
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public com.liferay.portal.kernel.service.GroupLocalService
		getGroupLocalService() {

		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(
		com.liferay.portal.kernel.service.GroupLocalService groupLocalService) {

		this.groupLocalService = groupLocalService;
	}

	/**
	 * Returns the group remote service.
	 *
	 * @return the group remote service
	 */
	public com.liferay.portal.kernel.service.GroupService getGroupService() {
		return groupService;
	}

	/**
	 * Sets the group remote service.
	 *
	 * @param groupService the group remote service
	 */
	public void setGroupService(
		com.liferay.portal.kernel.service.GroupService groupService) {

		this.groupService = groupService;
	}

	/**
	 * Returns the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
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
	 * Returns the system event local service.
	 *
	 * @return the system event local service
	 */
	public com.liferay.portal.kernel.service.SystemEventLocalService
		getSystemEventLocalService() {

		return systemEventLocalService;
	}

	/**
	 * Sets the system event local service.
	 *
	 * @param systemEventLocalService the system event local service
	 */
	public void setSystemEventLocalService(
		com.liferay.portal.kernel.service.SystemEventLocalService
			systemEventLocalService) {

		this.systemEventLocalService = systemEventLocalService;
	}

	/**
	 * Returns the system event persistence.
	 *
	 * @return the system event persistence
	 */
	public SystemEventPersistence getSystemEventPersistence() {
		return systemEventPersistence;
	}

	/**
	 * Sets the system event persistence.
	 *
	 * @param systemEventPersistence the system event persistence
	 */
	public void setSystemEventPersistence(
		SystemEventPersistence systemEventPersistence) {

		this.systemEventPersistence = systemEventPersistence;
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
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {

		this.userService = userService;
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
	 * Returns the ddm structure layout local service.
	 *
	 * @return the ddm structure layout local service
	 */
	public
		com.liferay.dynamic.data.mapping.service.DDMStructureLayoutLocalService
			getDDMStructureLayoutLocalService() {

		return ddmStructureLayoutLocalService;
	}

	/**
	 * Sets the ddm structure layout local service.
	 *
	 * @param ddmStructureLayoutLocalService the ddm structure layout local service
	 */
	public void setDDMStructureLayoutLocalService(
		com.liferay.dynamic.data.mapping.service.DDMStructureLayoutLocalService
			ddmStructureLayoutLocalService) {

		this.ddmStructureLayoutLocalService = ddmStructureLayoutLocalService;
	}

	/**
	 * Returns the ddm structure layout persistence.
	 *
	 * @return the ddm structure layout persistence
	 */
	public DDMStructureLayoutPersistence getDDMStructureLayoutPersistence() {
		return ddmStructureLayoutPersistence;
	}

	/**
	 * Sets the ddm structure layout persistence.
	 *
	 * @param ddmStructureLayoutPersistence the ddm structure layout persistence
	 */
	public void setDDMStructureLayoutPersistence(
		DDMStructureLayoutPersistence ddmStructureLayoutPersistence) {

		this.ddmStructureLayoutPersistence = ddmStructureLayoutPersistence;
	}

	/**
	 * Returns the ddm structure link local service.
	 *
	 * @return the ddm structure link local service
	 */
	public com.liferay.dynamic.data.mapping.service.DDMStructureLinkLocalService
		getDDMStructureLinkLocalService() {

		return ddmStructureLinkLocalService;
	}

	/**
	 * Sets the ddm structure link local service.
	 *
	 * @param ddmStructureLinkLocalService the ddm structure link local service
	 */
	public void setDDMStructureLinkLocalService(
		com.liferay.dynamic.data.mapping.service.DDMStructureLinkLocalService
			ddmStructureLinkLocalService) {

		this.ddmStructureLinkLocalService = ddmStructureLinkLocalService;
	}

	/**
	 * Returns the ddm structure link persistence.
	 *
	 * @return the ddm structure link persistence
	 */
	public DDMStructureLinkPersistence getDDMStructureLinkPersistence() {
		return ddmStructureLinkPersistence;
	}

	/**
	 * Sets the ddm structure link persistence.
	 *
	 * @param ddmStructureLinkPersistence the ddm structure link persistence
	 */
	public void setDDMStructureLinkPersistence(
		DDMStructureLinkPersistence ddmStructureLinkPersistence) {

		this.ddmStructureLinkPersistence = ddmStructureLinkPersistence;
	}

	/**
	 * Returns the ddm structure version local service.
	 *
	 * @return the ddm structure version local service
	 */
	public
		com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalService
			getDDMStructureVersionLocalService() {

		return ddmStructureVersionLocalService;
	}

	/**
	 * Sets the ddm structure version local service.
	 *
	 * @param ddmStructureVersionLocalService the ddm structure version local service
	 */
	public void setDDMStructureVersionLocalService(
		com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalService
			ddmStructureVersionLocalService) {

		this.ddmStructureVersionLocalService = ddmStructureVersionLocalService;
	}

	/**
	 * Returns the ddm structure version remote service.
	 *
	 * @return the ddm structure version remote service
	 */
	public com.liferay.dynamic.data.mapping.service.DDMStructureVersionService
		getDDMStructureVersionService() {

		return ddmStructureVersionService;
	}

	/**
	 * Sets the ddm structure version remote service.
	 *
	 * @param ddmStructureVersionService the ddm structure version remote service
	 */
	public void setDDMStructureVersionService(
		com.liferay.dynamic.data.mapping.service.DDMStructureVersionService
			ddmStructureVersionService) {

		this.ddmStructureVersionService = ddmStructureVersionService;
	}

	/**
	 * Returns the ddm structure version persistence.
	 *
	 * @return the ddm structure version persistence
	 */
	public DDMStructureVersionPersistence getDDMStructureVersionPersistence() {
		return ddmStructureVersionPersistence;
	}

	/**
	 * Sets the ddm structure version persistence.
	 *
	 * @param ddmStructureVersionPersistence the ddm structure version persistence
	 */
	public void setDDMStructureVersionPersistence(
		DDMStructureVersionPersistence ddmStructureVersionPersistence) {

		this.ddmStructureVersionPersistence = ddmStructureVersionPersistence;
	}

	/**
	 * Returns the ddm template local service.
	 *
	 * @return the ddm template local service
	 */
	public com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService
		getDDMTemplateLocalService() {

		return ddmTemplateLocalService;
	}

	/**
	 * Sets the ddm template local service.
	 *
	 * @param ddmTemplateLocalService the ddm template local service
	 */
	public void setDDMTemplateLocalService(
		com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService
			ddmTemplateLocalService) {

		this.ddmTemplateLocalService = ddmTemplateLocalService;
	}

	/**
	 * Returns the ddm template remote service.
	 *
	 * @return the ddm template remote service
	 */
	public com.liferay.dynamic.data.mapping.service.DDMTemplateService
		getDDMTemplateService() {

		return ddmTemplateService;
	}

	/**
	 * Sets the ddm template remote service.
	 *
	 * @param ddmTemplateService the ddm template remote service
	 */
	public void setDDMTemplateService(
		com.liferay.dynamic.data.mapping.service.DDMTemplateService
			ddmTemplateService) {

		this.ddmTemplateService = ddmTemplateService;
	}

	/**
	 * Returns the ddm template persistence.
	 *
	 * @return the ddm template persistence
	 */
	public DDMTemplatePersistence getDDMTemplatePersistence() {
		return ddmTemplatePersistence;
	}

	/**
	 * Sets the ddm template persistence.
	 *
	 * @param ddmTemplatePersistence the ddm template persistence
	 */
	public void setDDMTemplatePersistence(
		DDMTemplatePersistence ddmTemplatePersistence) {

		this.ddmTemplatePersistence = ddmTemplatePersistence;
	}

	/**
	 * Returns the ddm template finder.
	 *
	 * @return the ddm template finder
	 */
	public DDMTemplateFinder getDDMTemplateFinder() {
		return ddmTemplateFinder;
	}

	/**
	 * Sets the ddm template finder.
	 *
	 * @param ddmTemplateFinder the ddm template finder
	 */
	public void setDDMTemplateFinder(DDMTemplateFinder ddmTemplateFinder) {
		this.ddmTemplateFinder = ddmTemplateFinder;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DDMStructureService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DDMStructure.class;
	}

	protected String getModelClassName() {
		return DDMStructure.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ddmStructurePersistence.getDataSource();

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
		type = com.liferay.dynamic.data.mapping.service.DDMStructureLocalService.class
	)
	protected com.liferay.dynamic.data.mapping.service.DDMStructureLocalService
		ddmStructureLocalService;

	@BeanReference(type = DDMStructureService.class)
	protected DDMStructureService ddmStructureService;

	@BeanReference(type = DDMStructurePersistence.class)
	protected DDMStructurePersistence ddmStructurePersistence;

	@BeanReference(type = DDMStructureFinder.class)
	protected DDMStructureFinder ddmStructureFinder;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(
		type = com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLinkLocalService.class
	)
	protected com.liferay.dynamic.data.mapping.service.
		DDMDataProviderInstanceLinkLocalService
			ddmDataProviderInstanceLinkLocalService;

	@BeanReference(type = DDMDataProviderInstanceLinkPersistence.class)
	protected DDMDataProviderInstanceLinkPersistence
		ddmDataProviderInstanceLinkPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.GroupLocalService.class
	)
	protected com.liferay.portal.kernel.service.GroupLocalService
		groupLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.GroupService.class
	)
	protected com.liferay.portal.kernel.service.GroupService groupService;

	@ServiceReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.SystemEventLocalService.class
	)
	protected com.liferay.portal.kernel.service.SystemEventLocalService
		systemEventLocalService;

	@ServiceReference(type = SystemEventPersistence.class)
	protected SystemEventPersistence systemEventPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserService.class
	)
	protected com.liferay.portal.kernel.service.UserService userService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@BeanReference(
		type = com.liferay.dynamic.data.mapping.service.DDMStructureLayoutLocalService.class
	)
	protected
		com.liferay.dynamic.data.mapping.service.DDMStructureLayoutLocalService
			ddmStructureLayoutLocalService;

	@BeanReference(type = DDMStructureLayoutPersistence.class)
	protected DDMStructureLayoutPersistence ddmStructureLayoutPersistence;

	@BeanReference(
		type = com.liferay.dynamic.data.mapping.service.DDMStructureLinkLocalService.class
	)
	protected
		com.liferay.dynamic.data.mapping.service.DDMStructureLinkLocalService
			ddmStructureLinkLocalService;

	@BeanReference(type = DDMStructureLinkPersistence.class)
	protected DDMStructureLinkPersistence ddmStructureLinkPersistence;

	@BeanReference(
		type = com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalService.class
	)
	protected
		com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalService
			ddmStructureVersionLocalService;

	@BeanReference(
		type = com.liferay.dynamic.data.mapping.service.DDMStructureVersionService.class
	)
	protected
		com.liferay.dynamic.data.mapping.service.DDMStructureVersionService
			ddmStructureVersionService;

	@BeanReference(type = DDMStructureVersionPersistence.class)
	protected DDMStructureVersionPersistence ddmStructureVersionPersistence;

	@BeanReference(
		type = com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService.class
	)
	protected com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService
		ddmTemplateLocalService;

	@BeanReference(
		type = com.liferay.dynamic.data.mapping.service.DDMTemplateService.class
	)
	protected com.liferay.dynamic.data.mapping.service.DDMTemplateService
		ddmTemplateService;

	@BeanReference(type = DDMTemplatePersistence.class)
	protected DDMTemplatePersistence ddmTemplatePersistence;

	@BeanReference(type = DDMTemplateFinder.class)
	protected DDMTemplateFinder ddmTemplateFinder;

}