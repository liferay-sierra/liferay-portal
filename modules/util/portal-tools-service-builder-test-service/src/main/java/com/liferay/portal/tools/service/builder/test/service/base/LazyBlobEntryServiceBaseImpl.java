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
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.tools.service.builder.test.model.LazyBlobEntry;
import com.liferay.portal.tools.service.builder.test.service.LazyBlobEntryService;
import com.liferay.portal.tools.service.builder.test.service.LazyBlobEntryServiceUtil;
import com.liferay.portal.tools.service.builder.test.service.persistence.LazyBlobEntryPersistence;

import java.lang.reflect.Field;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the lazy blob entry remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.tools.service.builder.test.service.impl.LazyBlobEntryServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.tools.service.builder.test.service.impl.LazyBlobEntryServiceImpl
 * @generated
 */
public abstract class LazyBlobEntryServiceBaseImpl
	extends BaseServiceImpl
	implements IdentifiableOSGiService, LazyBlobEntryService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>LazyBlobEntryService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>LazyBlobEntryServiceUtil</code>.
	 */

	/**
	 * Returns the lazy blob entry local service.
	 *
	 * @return the lazy blob entry local service
	 */
	public com.liferay.portal.tools.service.builder.test.service.
		LazyBlobEntryLocalService getLazyBlobEntryLocalService() {

		return lazyBlobEntryLocalService;
	}

	/**
	 * Sets the lazy blob entry local service.
	 *
	 * @param lazyBlobEntryLocalService the lazy blob entry local service
	 */
	public void setLazyBlobEntryLocalService(
		com.liferay.portal.tools.service.builder.test.service.
			LazyBlobEntryLocalService lazyBlobEntryLocalService) {

		this.lazyBlobEntryLocalService = lazyBlobEntryLocalService;
	}

	/**
	 * Returns the lazy blob entry remote service.
	 *
	 * @return the lazy blob entry remote service
	 */
	public LazyBlobEntryService getLazyBlobEntryService() {
		return lazyBlobEntryService;
	}

	/**
	 * Sets the lazy blob entry remote service.
	 *
	 * @param lazyBlobEntryService the lazy blob entry remote service
	 */
	public void setLazyBlobEntryService(
		LazyBlobEntryService lazyBlobEntryService) {

		this.lazyBlobEntryService = lazyBlobEntryService;
	}

	/**
	 * Returns the lazy blob entry persistence.
	 *
	 * @return the lazy blob entry persistence
	 */
	public LazyBlobEntryPersistence getLazyBlobEntryPersistence() {
		return lazyBlobEntryPersistence;
	}

	/**
	 * Sets the lazy blob entry persistence.
	 *
	 * @param lazyBlobEntryPersistence the lazy blob entry persistence
	 */
	public void setLazyBlobEntryPersistence(
		LazyBlobEntryPersistence lazyBlobEntryPersistence) {

		this.lazyBlobEntryPersistence = lazyBlobEntryPersistence;
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
		_setServiceUtilService(lazyBlobEntryService);
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
		return LazyBlobEntryService.class.getName();
	}

	protected Class<?> getModelClass() {
		return LazyBlobEntry.class;
	}

	protected String getModelClassName() {
		return LazyBlobEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = lazyBlobEntryPersistence.getDataSource();

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
		LazyBlobEntryService lazyBlobEntryService) {

		try {
			Field field = LazyBlobEntryServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, lazyBlobEntryService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.portal.tools.service.builder.test.service.LazyBlobEntryLocalService.class
	)
	protected com.liferay.portal.tools.service.builder.test.service.
		LazyBlobEntryLocalService lazyBlobEntryLocalService;

	@BeanReference(type = LazyBlobEntryService.class)
	protected LazyBlobEntryService lazyBlobEntryService;

	@BeanReference(type = LazyBlobEntryPersistence.class)
	protected LazyBlobEntryPersistence lazyBlobEntryPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		LazyBlobEntryServiceBaseImpl.class);

}