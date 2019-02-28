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

package com.liferay.portal.security.wedeploy.auth.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the WeDeployAuthApp service. Represents a row in the &quot;WeDeployAuth_WeDeployAuthApp&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.security.wedeploy.auth.model.impl.WeDeployAuthAppModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.security.wedeploy.auth.model.impl.WeDeployAuthAppImpl</code>.
 * </p>
 *
 * @author Supritha Sundaram
 * @see WeDeployAuthApp
 * @generated
 */
@ProviderType
public interface WeDeployAuthAppModel
	extends AuditedModel, BaseModel<WeDeployAuthApp>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a we deploy auth app model instance should use the {@link WeDeployAuthApp} interface instead.
	 */

	/**
	 * Returns the primary key of this we deploy auth app.
	 *
	 * @return the primary key of this we deploy auth app
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this we deploy auth app.
	 *
	 * @param primaryKey the primary key of this we deploy auth app
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the we deploy auth app ID of this we deploy auth app.
	 *
	 * @return the we deploy auth app ID of this we deploy auth app
	 */
	public long getWeDeployAuthAppId();

	/**
	 * Sets the we deploy auth app ID of this we deploy auth app.
	 *
	 * @param weDeployAuthAppId the we deploy auth app ID of this we deploy auth app
	 */
	public void setWeDeployAuthAppId(long weDeployAuthAppId);

	/**
	 * Returns the company ID of this we deploy auth app.
	 *
	 * @return the company ID of this we deploy auth app
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this we deploy auth app.
	 *
	 * @param companyId the company ID of this we deploy auth app
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this we deploy auth app.
	 *
	 * @return the user ID of this we deploy auth app
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this we deploy auth app.
	 *
	 * @param userId the user ID of this we deploy auth app
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this we deploy auth app.
	 *
	 * @return the user uuid of this we deploy auth app
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this we deploy auth app.
	 *
	 * @param userUuid the user uuid of this we deploy auth app
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this we deploy auth app.
	 *
	 * @return the user name of this we deploy auth app
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this we deploy auth app.
	 *
	 * @param userName the user name of this we deploy auth app
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this we deploy auth app.
	 *
	 * @return the create date of this we deploy auth app
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this we deploy auth app.
	 *
	 * @param createDate the create date of this we deploy auth app
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this we deploy auth app.
	 *
	 * @return the modified date of this we deploy auth app
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this we deploy auth app.
	 *
	 * @param modifiedDate the modified date of this we deploy auth app
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this we deploy auth app.
	 *
	 * @return the name of this we deploy auth app
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this we deploy auth app.
	 *
	 * @param name the name of this we deploy auth app
	 */
	public void setName(String name);

	/**
	 * Returns the redirect uri of this we deploy auth app.
	 *
	 * @return the redirect uri of this we deploy auth app
	 */
	@AutoEscape
	public String getRedirectURI();

	/**
	 * Sets the redirect uri of this we deploy auth app.
	 *
	 * @param redirectURI the redirect uri of this we deploy auth app
	 */
	public void setRedirectURI(String redirectURI);

	/**
	 * Returns the client ID of this we deploy auth app.
	 *
	 * @return the client ID of this we deploy auth app
	 */
	@AutoEscape
	public String getClientId();

	/**
	 * Sets the client ID of this we deploy auth app.
	 *
	 * @param clientId the client ID of this we deploy auth app
	 */
	public void setClientId(String clientId);

	/**
	 * Returns the client secret of this we deploy auth app.
	 *
	 * @return the client secret of this we deploy auth app
	 */
	@AutoEscape
	public String getClientSecret();

	/**
	 * Sets the client secret of this we deploy auth app.
	 *
	 * @param clientSecret the client secret of this we deploy auth app
	 */
	public void setClientSecret(String clientSecret);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(WeDeployAuthApp weDeployAuthApp);

	@Override
	public int hashCode();

	@Override
	public CacheModel<WeDeployAuthApp> toCacheModel();

	@Override
	public WeDeployAuthApp toEscapedModel();

	@Override
	public WeDeployAuthApp toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}