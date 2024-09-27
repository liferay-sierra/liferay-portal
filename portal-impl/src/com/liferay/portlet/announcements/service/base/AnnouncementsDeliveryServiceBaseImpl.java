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
import com.liferay.announcements.kernel.service.AnnouncementsDeliveryService;
import com.liferay.announcements.kernel.service.AnnouncementsDeliveryServiceUtil;
import com.liferay.announcements.kernel.service.persistence.AnnouncementsDeliveryPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import java.lang.reflect.Field;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the announcements delivery remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.announcements.service.impl.AnnouncementsDeliveryServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.announcements.service.impl.AnnouncementsDeliveryServiceImpl
 * @generated
 */
public abstract class AnnouncementsDeliveryServiceBaseImpl
	extends BaseServiceImpl
	implements AnnouncementsDeliveryService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>AnnouncementsDeliveryService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>AnnouncementsDeliveryServiceUtil</code>.
	 */

	/**
	 * Returns the announcements delivery local service.
	 *
	 * @return the announcements delivery local service
	 */
	public
		com.liferay.announcements.kernel.service.
			AnnouncementsDeliveryLocalService
				getAnnouncementsDeliveryLocalService() {

		return announcementsDeliveryLocalService;
	}

	/**
	 * Sets the announcements delivery local service.
	 *
	 * @param announcementsDeliveryLocalService the announcements delivery local service
	 */
	public void setAnnouncementsDeliveryLocalService(
		com.liferay.announcements.kernel.service.
			AnnouncementsDeliveryLocalService
				announcementsDeliveryLocalService) {

		this.announcementsDeliveryLocalService =
			announcementsDeliveryLocalService;
	}

	/**
	 * Returns the announcements delivery remote service.
	 *
	 * @return the announcements delivery remote service
	 */
	public AnnouncementsDeliveryService getAnnouncementsDeliveryService() {
		return announcementsDeliveryService;
	}

	/**
	 * Sets the announcements delivery remote service.
	 *
	 * @param announcementsDeliveryService the announcements delivery remote service
	 */
	public void setAnnouncementsDeliveryService(
		AnnouncementsDeliveryService announcementsDeliveryService) {

		this.announcementsDeliveryService = announcementsDeliveryService;
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
		_setServiceUtilService(announcementsDeliveryService);
	}

	public void destroy() {
		_setServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AnnouncementsDeliveryService.class.getName();
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

	private void _setServiceUtilService(
		AnnouncementsDeliveryService announcementsDeliveryService) {

		try {
			Field field =
				AnnouncementsDeliveryServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, announcementsDeliveryService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.announcements.kernel.service.AnnouncementsDeliveryLocalService.class
	)
	protected
		com.liferay.announcements.kernel.service.
			AnnouncementsDeliveryLocalService announcementsDeliveryLocalService;

	@BeanReference(type = AnnouncementsDeliveryService.class)
	protected AnnouncementsDeliveryService announcementsDeliveryService;

	@BeanReference(type = AnnouncementsDeliveryPersistence.class)
	protected AnnouncementsDeliveryPersistence announcementsDeliveryPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		AnnouncementsDeliveryServiceBaseImpl.class);

}