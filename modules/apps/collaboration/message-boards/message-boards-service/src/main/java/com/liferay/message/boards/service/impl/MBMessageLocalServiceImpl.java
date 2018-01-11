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

package com.liferay.message.boards.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.message.boards.internal.util.MBUtil;
import com.liferay.message.boards.kernel.constants.MBConstants;
import com.liferay.message.boards.kernel.exception.MessageBodyException;
import com.liferay.message.boards.kernel.exception.MessageSubjectException;
import com.liferay.message.boards.kernel.exception.NoSuchThreadException;
import com.liferay.message.boards.kernel.exception.RequiredMessageException;
import com.liferay.message.boards.kernel.model.MBCategory;
import com.liferay.message.boards.kernel.model.MBCategoryConstants;
import com.liferay.message.boards.kernel.model.MBDiscussion;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.model.MBMessageConstants;
import com.liferay.message.boards.kernel.model.MBThread;
import com.liferay.message.boards.kernel.model.MBThreadConstants;
import com.liferay.message.boards.model.MBMessageDisplay;
import com.liferay.message.boards.model.impl.MBMessageDisplayImpl;
import com.liferay.message.boards.settings.MBGroupServiceSettings;
import com.liferay.message.boards.util.comparator.MessageCreateDateComparator;
import com.liferay.message.boards.util.comparator.MessageThreadComparator;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.sanitizer.Sanitizer;
import com.liferay.portal.kernel.sanitizer.SanitizerUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.social.SocialActivityManagerUtil;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.linkback.LinkbackProducerUtil;
import com.liferay.portal.util.LayoutURLUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.messageboards.model.impl.MBCategoryImpl;
import com.liferay.portlet.messageboards.service.base.MBMessageLocalServiceBaseImpl;
import com.liferay.portlet.messageboards.social.MBActivityKeys;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.trash.kernel.util.TrashUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Mika Koivisto
 * @author Jorge Ferrer
 * @author Juan Fernández
 * @author Shuyang Zhou
 */
public class MBMessageLocalServiceImpl extends MBMessageLocalServiceBaseImpl {

	@Override
	public MBMessage addDiscussionMessage(
			long userId, String userName, long groupId, String className,
			long classPK, int workflowAction)
		throws PortalException {

		return null;
	}

	@Override
	public MBMessage addDiscussionMessage(
			long userId, String userName, long groupId, String className,
			long classPK, long threadId, long parentMessageId, String subject,
			String body, ServiceContext serviceContext)
		throws PortalException {

		return null;
	}

	@Override
	public MBMessage addMessage(
			long userId, String userName, long groupId, long categoryId,
			long threadId, long parentMessageId, String subject, String body,
			String format,
			List<ObjectValuePair<String, InputStream>> inputStreamOVPs,
			boolean anonymous, double priority, boolean allowPingbacks,
			ServiceContext serviceContext)
		throws PortalException {

		// Message

		User user = userPersistence.findByPrimaryKey(userId);

		userName = user.isDefaultUser() ? userName : user.getFullName();

		subject = ModelHintsUtil.trimString(
			MBMessage.class.getName(), "subject", subject);

		if (!com.liferay.message.boards.util.MBUtil.isValidMessageFormat(
				format)) {

			format = "html";
		}

		MBGroupServiceSettings mbGroupServiceSettings =
			MBGroupServiceSettings.getInstance(groupId);

		if (mbGroupServiceSettings != null) {
			if (!mbGroupServiceSettings.isAllowAnonymousPosting()) {
				if (anonymous || user.isDefaultUser()) {
					throw new PrincipalException.MustHavePermission(
						userId, ActionKeys.ADD_MESSAGE);
				}
			}
		}

		if (user.isDefaultUser()) {
			anonymous = true;
		}

		Date now = new Date();

		long messageId = counterLocalService.increment();

		subject = getSubject(subject, body);

		body = getBody(subject, body);

		Map<String, Object> options = new HashMap<>();

		boolean discussion = false;

		if (categoryId == MBCategoryConstants.DISCUSSION_CATEGORY_ID) {
			discussion = true;
		}

		options.put("discussion", discussion);

		body = SanitizerUtil.sanitize(
			user.getCompanyId(), groupId, userId, MBMessage.class.getName(),
			messageId, "text/" + format, Sanitizer.MODE_ALL, body, options);

		validate(subject, body);

		MBMessage message = mbMessagePersistence.create(messageId);

		message.setUuid(serviceContext.getUuid());
		message.setGroupId(groupId);
		message.setCompanyId(user.getCompanyId());
		message.setUserId(user.getUserId());
		message.setUserName(userName);
		message.setCreateDate(serviceContext.getCreateDate(now));
		message.setModifiedDate(serviceContext.getModifiedDate(now));

		if (threadId > 0) {
			message.setThreadId(threadId);
		}

		if (priority != MBThreadConstants.PRIORITY_NOT_GIVEN) {
			message.setPriority(priority);
		}

		message.setAllowPingbacks(allowPingbacks);
		message.setStatus(WorkflowConstants.STATUS_DRAFT);
		message.setStatusByUserId(user.getUserId());
		message.setStatusByUserName(userName);
		message.setStatusDate(serviceContext.getModifiedDate(now));

		// Thread

		if (parentMessageId != MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID) {
			MBMessage parentMessage = mbMessagePersistence.fetchByPrimaryKey(
				parentMessageId);

			if (parentMessage == null) {
				parentMessageId = MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID;
			}
		}

		MBThread thread = null;

		if (threadId > 0) {
			thread = mbThreadPersistence.fetchByPrimaryKey(threadId);
		}

		if (thread == null) {
			if (parentMessageId ==
					MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID) {

				thread = mbThreadLocalService.addThread(
					categoryId, message, serviceContext);
			}
			else {
				throw new NoSuchThreadException("{threadId=" + threadId + "}");
			}
		}

		if ((priority != MBThreadConstants.PRIORITY_NOT_GIVEN) &&
			(thread.getPriority() != priority)) {

			thread.setPriority(priority);

			mbThreadPersistence.update(thread);

			updatePriorities(thread.getThreadId(), priority);
		}

		// Message

		message.setCategoryId(categoryId);
		message.setThreadId(thread.getThreadId());
		message.setRootMessageId(thread.getRootMessageId());
		message.setParentMessageId(parentMessageId);
		message.setSubject(subject);
		message.setBody(body);
		message.setFormat(format);
		message.setAnonymous(anonymous);

		if (message.isDiscussion()) {
			long classNameId = classNameLocalService.getClassNameId(
				(String)serviceContext.getAttribute("className"));
			long classPK = ParamUtil.getLong(serviceContext, "classPK");

			message.setClassNameId(classNameId);
			message.setClassPK(classPK);
		}

		message.setExpandoBridgeAttributes(serviceContext);

		mbMessagePersistence.update(message);

		// Resources

		if ((parentMessageId !=
				MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID) &&
			GetterUtil.getBoolean(
				serviceContext.getAttribute("propagatePermissions"))) {

			MBUtil.propagatePermissions(
				message.getCompanyId(), groupId, parentMessageId,
				serviceContext);
		}

		if (!message.isDiscussion()) {
			if (user.isDefaultUser()) {
				addMessageResources(message, true, true);
			}
			else if (serviceContext.isAddGroupPermissions() ||
					 serviceContext.isAddGuestPermissions()) {

				addMessageResources(
					message, serviceContext.isAddGroupPermissions(),
					serviceContext.isAddGuestPermissions());
			}
			else {
				addMessageResources(
					message, serviceContext.getModelPermissions());
			}
		}

		// Attachments

		if (ListUtil.isNotEmpty(inputStreamOVPs)) {
			Folder folder = message.addAttachmentsFolder();

			PortletFileRepositoryUtil.addPortletFileEntries(
				message.getGroupId(), userId, MBMessage.class.getName(),
				message.getMessageId(), MBConstants.SERVICE_NAME,
				folder.getFolderId(), inputStreamOVPs);
		}

		// Asset

		updateAsset(
			userId, message, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds(),
			serviceContext.isAssetEntryVisible());

		// Workflow

		startWorkflowInstance(userId, message, serviceContext);

		return message;
	}

	@Override
	public MBMessage addMessage(
			long userId, String userName, long groupId, long categoryId,
			String subject, String body, ServiceContext serviceContext)
		throws PortalException {

		List<ObjectValuePair<String, InputStream>> inputStreamOVPs =
			Collections.emptyList();

		return addMessage(
			userId, userName, groupId, categoryId, 0, 0, subject, body,
			MBMessageConstants.DEFAULT_FORMAT, inputStreamOVPs, false, 0.0,
			false, serviceContext);
	}

	@Override
	public MBMessage addMessage(
			long userId, String userName, long groupId, long categoryId,
			String subject, String body, String format,
			List<ObjectValuePair<String, InputStream>> inputStreamOVPs,
			boolean anonymous, double priority, boolean allowPingbacks,
			ServiceContext serviceContext)
		throws PortalException {

		long threadId = 0;
		long parentMessageId = MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID;

		return addMessage(
			userId, userName, groupId, categoryId, threadId, parentMessageId,
			subject, body, format, inputStreamOVPs, anonymous, priority,
			allowPingbacks, serviceContext);
	}

	@Override
	public MBMessage addMessage(
			long userId, String userName, long groupId, long categoryId,
			String subject, String body, String format, String fileName,
			File file, boolean anonymous, double priority,
			boolean allowPingbacks, ServiceContext serviceContext)
		throws FileNotFoundException, PortalException {

		List<ObjectValuePair<String, InputStream>> inputStreamOVPs =
			new ArrayList<>(1);

		InputStream inputStream = new FileInputStream(file);

		ObjectValuePair<String, InputStream> inputStreamOVP =
			new ObjectValuePair<>(fileName, inputStream);

		inputStreamOVPs.add(inputStreamOVP);

		return addMessage(
			userId, userName, groupId, categoryId, 0, 0, subject, body, format,
			inputStreamOVPs, anonymous, priority, allowPingbacks,
			serviceContext);
	}

	@Override
	public void addMessageAttachment(
			long userId, long messageId, String fileName, File file,
			String mimeType)
		throws PortalException {

		MBMessage message = mbMessagePersistence.findByPrimaryKey(messageId);

		Folder folder = message.addAttachmentsFolder();

		PortletFileRepositoryUtil.addPortletFileEntry(
			message.getGroupId(), userId, MBMessage.class.getName(),
			message.getMessageId(), MBConstants.SERVICE_NAME,
			folder.getFolderId(), file, fileName, mimeType, true);
	}

	@Override
	public void addMessageResources(
			long messageId, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException {

		MBMessage message = mbMessagePersistence.findByPrimaryKey(messageId);

		addMessageResources(message, addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addMessageResources(
			long messageId, ModelPermissions modelPermissions)
		throws PortalException {

		MBMessage message = mbMessagePersistence.findByPrimaryKey(messageId);

		addMessageResources(message, modelPermissions);
	}

	@Override
	public void addMessageResources(
			MBMessage message, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException {

		resourceLocalService.addResources(
			message.getCompanyId(), message.getGroupId(), message.getUserId(),
			MBMessage.class.getName(), message.getMessageId(), false,
			addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addMessageResources(
			MBMessage message, ModelPermissions modelPermissions)
		throws PortalException {

		resourceLocalService.addModelResources(
			message.getCompanyId(), message.getGroupId(), message.getUserId(),
			MBMessage.class.getName(), message.getMessageId(),
			modelPermissions);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public MBMessage deleteDiscussionMessage(long messageId)
		throws PortalException {

		MBMessage message = mbMessagePersistence.findByPrimaryKey(messageId);

		SocialActivityManagerUtil.deleteActivities(message);

		return mbMessageLocalService.deleteMessage(messageId);
	}

	@Override
	public void deleteDiscussionMessages(String className, long classPK)
		throws PortalException {
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public MBMessage deleteMessage(long messageId) throws PortalException {
		MBMessage message = mbMessagePersistence.findByPrimaryKey(messageId);

		return mbMessageLocalService.deleteMessage(message);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public MBMessage deleteMessage(MBMessage message) throws PortalException {

		// Attachments

		long folderId = message.getAttachmentsFolderId();

		if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			PortletFileRepositoryUtil.deletePortletFolder(folderId);
		}

		// Thread

		int count = mbMessagePersistence.countByThreadId(message.getThreadId());

		if (count == 1) {

			// Attachments

			long threadAttachmentsFolderId =
				message.getThreadAttachmentsFolderId();

			if (threadAttachmentsFolderId !=
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

				PortletFileRepositoryUtil.deletePortletFolder(
					threadAttachmentsFolderId);
			}

			// Thread

			MBThread thread = mbThreadPersistence.findByPrimaryKey(
				message.getThreadId());

			mbThreadPersistence.remove(thread);

			// Category

			if ((message.getCategoryId() !=
					MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) &&
				(message.getCategoryId() !=
					MBCategoryConstants.DISCUSSION_CATEGORY_ID)) {

				MBUtil.updateCategoryStatistics(message.getCategoryId());
			}

			// Indexer

			Indexer<MBThread> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				MBThread.class);

			indexer.delete(thread);
		}
		else {
			MBThread thread = mbThreadPersistence.findByPrimaryKey(
				message.getThreadId());

			// Message is a root message

			if (thread.getRootMessageId() == message.getMessageId()) {
				List<MBMessage> childrenMessages =
					mbMessagePersistence.findByT_P(
						message.getThreadId(), message.getMessageId());

				if (childrenMessages.size() > 1) {
					throw new RequiredMessageException(
						String.valueOf(message.getMessageId()));
				}
				else if (childrenMessages.size() == 1) {
					MBMessage childMessage = childrenMessages.get(0);

					childMessage.setRootMessageId(childMessage.getMessageId());
					childMessage.setParentMessageId(
						MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID);

					mbMessagePersistence.update(childMessage);

					List<MBMessage> repliesMessages =
						mbMessagePersistence.findByThreadReplies(
							message.getThreadId());

					for (MBMessage repliesMessage : repliesMessages) {
						repliesMessage.setRootMessageId(
							childMessage.getMessageId());

						mbMessagePersistence.update(repliesMessage);
					}

					thread.setRootMessageId(childMessage.getMessageId());
					thread.setRootMessageUserId(childMessage.getUserId());

					mbThreadPersistence.update(thread);
				}
			}
			else {

				// Message is a child message

				List<MBMessage> childrenMessages =
					mbMessagePersistence.findByT_P(
						message.getThreadId(), message.getMessageId());

				// Message has children messages

				if (!childrenMessages.isEmpty()) {
					for (MBMessage childMessage : childrenMessages) {
						childMessage.setParentMessageId(
							message.getParentMessageId());

						mbMessagePersistence.update(childMessage);
					}
				}
				else if (message.getStatus() ==
							WorkflowConstants.STATUS_APPROVED) {

					MessageCreateDateComparator comparator =
						new MessageCreateDateComparator(true);

					MBMessage[] prevAndNextMessages =
						mbMessagePersistence.findByT_S_PrevAndNext(
							message.getMessageId(), thread.getThreadId(),
							WorkflowConstants.STATUS_APPROVED, comparator);

					if (prevAndNextMessages[2] == null) {
						thread.setLastPostByUserId(
							prevAndNextMessages[0].getUserId());
						thread.setLastPostDate(
							prevAndNextMessages[0].getModifiedDate());

						mbThreadPersistence.update(thread);
					}
				}
			}

			// Thread

			if (message.isApproved()) {
				MBUtil.updateThreadMessageCount(thread.getThreadId());
			}

			// Category

			if ((message.getCategoryId() !=
					MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) &&
				(message.getCategoryId() !=
					MBCategoryConstants.DISCUSSION_CATEGORY_ID) &&
				!message.isDraft()) {

				MBUtil.updateCategoryMessageCount(message.getCategoryId());
			}

			// Indexer

			Indexer<MBThread> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				MBThread.class);

			indexer.reindex(thread);
		}

		// Asset

		assetEntryLocalService.deleteEntry(
			message.getWorkflowClassName(), message.getMessageId());

		// Expando

		expandoRowLocalService.deleteRows(message.getMessageId());

		// Ratings

		ratingsStatsLocalService.deleteStats(
			message.getWorkflowClassName(), message.getMessageId());

		// Resources

		if (!message.isDiscussion()) {
			resourceLocalService.deleteResource(
				message.getCompanyId(), message.getWorkflowClassName(),
				ResourceConstants.SCOPE_INDIVIDUAL, message.getMessageId());
		}

		// Message

		mbMessagePersistence.remove(message);

		// Workflow

		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			message.getCompanyId(), message.getGroupId(),
			message.getWorkflowClassName(), message.getMessageId());

		return message;
	}

	@Override
	public void deleteMessageAttachment(long messageId, String fileName)
		throws PortalException {

		MBMessage message = getMessage(messageId);

		long folderId = message.getAttachmentsFolderId();

		if (folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return;
		}

		PortletFileRepositoryUtil.deletePortletFileEntry(
			message.getGroupId(), folderId, fileName);
	}

	@Override
	public void deleteMessageAttachments(long messageId)
		throws PortalException {

		MBMessage message = getMessage(messageId);

		long folderId = message.getAttachmentsFolderId();

		if (folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return;
		}

		PortletFileRepositoryUtil.deletePortletFileEntries(
			message.getGroupId(), folderId);
	}

	@Override
	public void emptyMessageAttachments(long messageId) throws PortalException {
		MBMessage message = getMessage(messageId);

		long folderId = message.getAttachmentsFolderId();

		if (folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return;
		}

		PortletFileRepositoryUtil.deletePortletFileEntries(
			message.getGroupId(), folderId, WorkflowConstants.STATUS_IN_TRASH);
	}

	@Override
	public MBMessage fetchFirstMessage(long threadId, long parentMessageId)
		throws PortalException {

		return mbMessagePersistence.fetchByT_P_First(
			threadId, parentMessageId, null);
	}

	@Override
	public List<MBMessage> getCategoryMessages(
		long groupId, long categoryId, int status, int start, int end) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.findByG_C(
				groupId, categoryId, start, end);
		}
		else {
			return mbMessagePersistence.findByG_C_S(
				groupId, categoryId, status, start, end);
		}
	}

	@Override
	public List<MBMessage> getCategoryMessages(
		long groupId, long categoryId, int status, int start, int end,
		OrderByComparator<MBMessage> obc) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.findByG_C(
				groupId, categoryId, start, end, obc);
		}
		else {
			return mbMessagePersistence.findByG_C_S(
				groupId, categoryId, status, start, end, obc);
		}
	}

	@Override
	public int getCategoryMessagesCount(
		long groupId, long categoryId, int status) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.countByG_C(groupId, categoryId);
		}
		else {
			return mbMessagePersistence.countByG_C_S(
				groupId, categoryId, status);
		}
	}

	@Override
	public List<MBMessage> getCompanyMessages(
		long companyId, int status, int start, int end) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.findByCompanyId(companyId, start, end);
		}
		else {
			return mbMessagePersistence.findByC_S(
				companyId, status, start, end);
		}
	}

	@Override
	public List<MBMessage> getCompanyMessages(
		long companyId, int status, int start, int end,
		OrderByComparator<MBMessage> obc) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.findByCompanyId(
				companyId, start, end, obc);
		}
		else {
			return mbMessagePersistence.findByC_S(
				companyId, status, start, end, obc);
		}
	}

	@Override
	public int getCompanyMessagesCount(long companyId, int status) {
		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.countByCompanyId(companyId);
		}
		else {
			return mbMessagePersistence.countByC_S(companyId, status);
		}
	}

	@Override
	public MBMessageDisplay getDiscussionMessageDisplay(
			long userId, long groupId, String className, long classPK,
			int status)
		throws PortalException {

		return null;
	}

	@Override
	public MBMessageDisplay getDiscussionMessageDisplay(
			long userId, long groupId, String className, long classPK,
			int status, Comparator<MBMessage> comparator)
		throws PortalException {

		return null;
	}

	@Override
	public int getDiscussionMessagesCount(
		long classNameId, long classPK, int status) {

		return 0;
	}

	@Override
	public int getDiscussionMessagesCount(
		String className, long classPK, int status) {

		return 0;
	}

	@Override
	public List<MBDiscussion> getDiscussions(String className) {
		return Collections.emptyList();
	}

	@Override
	public MBMessage getFirstMessage(long threadId, long parentMessageId)
		throws PortalException {

		return mbMessagePersistence.findByT_P_First(
			threadId, parentMessageId, null);
	}

	@Override
	public List<MBMessage> getGroupMessages(
		long groupId, int status, int start, int end) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.findByGroupId(groupId, start, end);
		}
		else {
			return mbMessagePersistence.findByG_S(groupId, status, start, end);
		}
	}

	@Override
	public List<MBMessage> getGroupMessages(
		long groupId, int status, int start, int end,
		OrderByComparator<MBMessage> obc) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.findByGroupId(groupId, start, end, obc);
		}
		else {
			return mbMessagePersistence.findByG_S(
				groupId, status, start, end, obc);
		}
	}

	@Override
	public List<MBMessage> getGroupMessages(
		long groupId, long userId, int status, int start, int end) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.findByG_U(groupId, userId, start, end);
		}
		else {
			return mbMessagePersistence.findByG_U_S(
				groupId, userId, status, start, end);
		}
	}

	@Override
	public List<MBMessage> getGroupMessages(
		long groupId, long userId, int status, int start, int end,
		OrderByComparator<MBMessage> obc) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.findByG_U(
				groupId, userId, start, end, obc);
		}
		else {
			return mbMessagePersistence.findByG_U_S(
				groupId, userId, status, start, end, obc);
		}
	}

	@Override
	public int getGroupMessagesCount(long groupId, int status) {
		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.countByGroupId(groupId);
		}
		else {
			return mbMessagePersistence.countByG_S(groupId, status);
		}
	}

	@Override
	public int getGroupMessagesCount(long groupId, long userId, int status) {
		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.countByG_U(groupId, userId);
		}
		else {
			return mbMessagePersistence.countByG_U_S(groupId, userId, status);
		}
	}

	@Override
	public MBMessage getMessage(long messageId) throws PortalException {
		return mbMessagePersistence.findByPrimaryKey(messageId);
	}

	@Override
	public MBMessageDisplay getMessageDisplay(
			long userId, long messageId, int status)
		throws PortalException {

		MBMessage message = getMessage(messageId);

		return getMessageDisplay(userId, message, status);
	}

	@Override
	public MBMessageDisplay getMessageDisplay(
			long userId, MBMessage message, int status)
		throws PortalException {

		return getMessageDisplay(
			userId, message, status, new MessageThreadComparator());
	}

	@Override
	public MBMessageDisplay getMessageDisplay(
			long userId, MBMessage message, int status,
			Comparator<MBMessage> comparator)
		throws PortalException {

		MBCategory category = null;

		if ((message.getCategoryId() !=
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) &&
			(message.getCategoryId() !=
				MBCategoryConstants.DISCUSSION_CATEGORY_ID)) {

			category = mbCategoryPersistence.findByPrimaryKey(
				message.getCategoryId());
		}
		else {
			category = new MBCategoryImpl();

			category.setCategoryId(message.getCategoryId());
			category.setDisplayStyle(MBCategoryConstants.DEFAULT_DISPLAY_STYLE);
		}

		MBMessage parentMessage = null;

		if (message.isReply()) {
			parentMessage = mbMessagePersistence.findByPrimaryKey(
				message.getParentMessageId());
		}

		MBThread thread = mbThreadPersistence.findByPrimaryKey(
			message.getThreadId());

		if (message.isApproved() && !message.isDiscussion()) {
			mbThreadLocalService.incrementViewCounter(thread.getThreadId(), 1);

			SocialActivityManagerUtil.addActivity(
				userId, thread, SocialActivityConstants.TYPE_VIEW,
				StringPool.BLANK, 0);
		}

		return new MBMessageDisplayImpl(
			userId, message, parentMessage, category, thread, status, this,
			comparator);
	}

	@Override
	public List<MBMessage> getMessages(
		String className, long classPK, int status) {

		long classNameId = classNameLocalService.getClassNameId(className);

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.findByC_C(classNameId, classPK);
		}
		else {
			return mbMessagePersistence.findByC_C_S(
				classNameId, classPK, status);
		}
	}

	@Override
	public List<MBMessage> getNoAssetMessages() {
		return mbMessageFinder.findByNoAssets();
	}

	@Override
	public int getPositionInThread(long messageId) throws PortalException {
		MBMessage message = mbMessagePersistence.findByPrimaryKey(messageId);

		return mbMessageFinder.countByC_T(
			message.getCreateDate(), message.getThreadId());
	}

	@Override
	public List<MBMessage> getThreadMessages(long threadId, int status) {
		return getThreadMessages(
			threadId, status, new MessageThreadComparator());
	}

	@Override
	public List<MBMessage> getThreadMessages(
		long threadId, int status, Comparator<MBMessage> comparator) {

		List<MBMessage> messages = null;

		if (status == WorkflowConstants.STATUS_ANY) {
			messages = mbMessagePersistence.findByThreadId(threadId);
		}
		else {
			messages = mbMessagePersistence.findByT_S(threadId, status);
		}

		return ListUtil.sort(messages, comparator);
	}

	@Override
	public List<MBMessage> getThreadMessages(
		long threadId, int status, int start, int end) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.findByThreadId(threadId, start, end);
		}
		else {
			return mbMessagePersistence.findByT_S(threadId, status, start, end);
		}
	}

	@Override
	public List<MBMessage> getThreadMessages(
		long userId, long threadId, int status, int start, int end,
		Comparator<MBMessage> comparator) {

		if (status == WorkflowConstants.STATUS_ANY) {
			OrderByComparator<MBMessage> orderByComparator = null;

			if (comparator instanceof OrderByComparator) {
				orderByComparator = (OrderByComparator<MBMessage>)comparator;
			}

			List<MBMessage> messages = mbMessagePersistence.findByT_notS(
				threadId, WorkflowConstants.STATUS_IN_TRASH, start, end,
				orderByComparator);

			if (!(comparator instanceof OrderByComparator)) {
				messages = ListUtil.sort(messages, comparator);
			}

			return messages;
		}

		QueryDefinition<MBMessage> queryDefinition = new QueryDefinition<>(
			status, userId, true, start, end, null);

		if (comparator instanceof OrderByComparator) {
			queryDefinition.setOrderByComparator(
				(OrderByComparator<MBMessage>)comparator);
		}

		List<MBMessage> messages = mbMessageFinder.findByThreadId(
			threadId, queryDefinition);

		if (!(comparator instanceof OrderByComparator)) {
			messages = ListUtil.sort(messages, comparator);
		}

		return messages;
	}

	@Override
	public int getThreadMessagesCount(long threadId, int status) {
		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.countByThreadId(threadId);
		}
		else {
			return mbMessagePersistence.countByT_S(threadId, status);
		}
	}

	@Override
	public List<MBMessage> getThreadRepliesMessages(
		long threadId, int status, int start, int end) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.findByThreadReplies(
				threadId, start, end);
		}
		else {
			return mbMessagePersistence.findByTR_S(
				threadId, status, start, end);
		}
	}

	@Override
	public List<MBMessage> getUserDiscussionMessages(
		long userId, long classNameId, long classPK, int status, int start,
		int end, OrderByComparator<MBMessage> obc) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.findByU_C_C(
				userId, classNameId, classPK, start, end, obc);
		}
		else {
			return mbMessagePersistence.findByU_C_C_S(
				userId, classNameId, classPK, status, start, end, obc);
		}
	}

	@Override
	public List<MBMessage> getUserDiscussionMessages(
		long userId, long[] classNameIds, int status, int start, int end,
		OrderByComparator<MBMessage> obc) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.findByU_C(
				userId, classNameIds, start, end, obc);
		}
		else {
			return mbMessagePersistence.findByU_C_S(
				userId, classNameIds, status, start, end, obc);
		}
	}

	@Override
	public List<MBMessage> getUserDiscussionMessages(
		long userId, String className, long classPK, int status, int start,
		int end, OrderByComparator<MBMessage> obc) {

		long classNameId = classNameLocalService.getClassNameId(className);

		return getUserDiscussionMessages(
			userId, classNameId, classPK, status, start, end, obc);
	}

	@Override
	public int getUserDiscussionMessagesCount(
		long userId, long classNameId, long classPK, int status) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.countByU_C_C(
				userId, classNameId, classPK);
		}
		else {
			return mbMessagePersistence.countByU_C_C_S(
				userId, classNameId, classPK, status);
		}
	}

	@Override
	public int getUserDiscussionMessagesCount(
		long userId, long[] classNameIds, int status) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return mbMessagePersistence.countByU_C(userId, classNameIds);
		}
		else {
			return mbMessagePersistence.countByU_C_S(
				userId, classNameIds, status);
		}
	}

	@Override
	public int getUserDiscussionMessagesCount(
		long userId, String className, long classPK, int status) {

		long classNameId = classNameLocalService.getClassNameId(className);

		return getUserDiscussionMessagesCount(
			userId, classNameId, classPK, status);
	}

	@Override
	public long moveMessageAttachmentToTrash(
			long userId, long messageId, String fileName)
		throws PortalException {

		MBMessage message = getMessage(messageId);

		long folderId = message.getAttachmentsFolderId();

		FileEntry fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(
			message.getGroupId(), folderId, fileName);

		PortletFileRepositoryUtil.movePortletFileEntryToTrash(
			userId, fileEntry.getFileEntryId());

		return fileEntry.getFileEntryId();
	}

	@Override
	public void restoreMessageAttachmentFromTrash(
			long userId, long messageId, String deletedFileName)
		throws PortalException {

		MBMessage message = getMessage(messageId);

		Folder folder = message.addAttachmentsFolder();

		PortletFileRepositoryUtil.restorePortletFileEntryFromTrash(
			message.getGroupId(), userId, folder.getFolderId(),
			deletedFileName);
	}

	@Override
	public void subscribeMessage(long userId, long messageId)
		throws PortalException {
	}

	@Override
	public void unsubscribeMessage(long userId, long messageId)
		throws PortalException {
	}

	@Override
	public void updateAnswer(long messageId, boolean answer, boolean cascade)
		throws PortalException {

		MBMessage message = mbMessagePersistence.findByPrimaryKey(messageId);

		updateAnswer(message, answer, cascade);
	}

	@Override
	public void updateAnswer(MBMessage message, boolean answer, boolean cascade)
		throws PortalException {

		if (message.isAnswer() != answer) {
			message.setAnswer(answer);

			mbMessagePersistence.update(message);
		}

		if (cascade) {
			List<MBMessage> messages = mbMessagePersistence.findByT_P(
				message.getThreadId(), message.getMessageId());

			for (MBMessage curMessage : messages) {
				updateAnswer(curMessage, answer, cascade);
			}
		}
	}

	@Override
	public void updateAsset(
			long userId, MBMessage message, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds)
		throws PortalException {

		updateAsset(
			userId, message, assetCategoryIds, assetTagNames, assetLinkEntryIds,
			true);
	}

	@Override
	public MBMessage updateDiscussionMessage(
			long userId, long messageId, String className, long classPK,
			String subject, String body, ServiceContext serviceContext)
		throws PortalException {

		subject = getDiscussionMessageSubject(subject, body);
		List<ObjectValuePair<String, InputStream>> inputStreamOVPs = null;
		List<String> existingFiles = null;
		double priority = 0.0;
		boolean allowPingbacks = false;

		serviceContext.setAttribute("className", className);
		serviceContext.setAttribute("classPK", String.valueOf(classPK));

		return mbMessageLocalService.updateMessage(
			userId, messageId, subject, body, inputStreamOVPs, existingFiles,
			priority, allowPingbacks, serviceContext);
	}

	@Override
	public MBMessage updateMessage(
			long userId, long messageId, String body,
			ServiceContext serviceContext)
		throws PortalException {

		MBMessage message = mbMessagePersistence.findByPrimaryKey(messageId);

		return mbMessageLocalService.updateMessage(
			userId, messageId, message.getSubject(), body, null, null,
			message.getPriority(), message.isAllowPingbacks(), serviceContext);
	}

	@Override
	public MBMessage updateMessage(
			long userId, long messageId, String subject, String body,
			List<ObjectValuePair<String, InputStream>> inputStreamOVPs,
			List<String> existingFiles, double priority, boolean allowPingbacks,
			ServiceContext serviceContext)
		throws PortalException {

		// Message

		MBMessage message = mbMessagePersistence.findByPrimaryKey(messageId);

		int oldStatus = message.getStatus();

		Date modifiedDate = serviceContext.getModifiedDate(null);
		subject = ModelHintsUtil.trimString(
			MBMessage.class.getName(), "subject", subject);

		subject = getSubject(subject, body);

		body = getBody(subject, body);

		Map<String, Object> options = new HashMap<>();

		options.put("discussion", message.isDiscussion());

		body = SanitizerUtil.sanitize(
			message.getCompanyId(), message.getGroupId(), userId,
			MBMessage.class.getName(), messageId, "text/" + message.getFormat(),
			Sanitizer.MODE_ALL, body, options);

		validate(subject, body);

		message.setModifiedDate(modifiedDate);
		message.setSubject(subject);
		message.setBody(body);
		message.setAllowPingbacks(allowPingbacks);

		if (priority != MBThreadConstants.PRIORITY_NOT_GIVEN) {
			message.setPriority(priority);
		}

		MBThread thread = mbThreadPersistence.findByPrimaryKey(
			message.getThreadId());

		if (serviceContext.getWorkflowAction() ==
				WorkflowConstants.ACTION_SAVE_DRAFT) {

			if (!message.isDraft() && !message.isPending()) {
				message.setStatus(WorkflowConstants.STATUS_DRAFT);

				// Thread

				User user = userPersistence.findByPrimaryKey(userId);

				updateThreadStatus(
					thread, message, user, oldStatus, modifiedDate);

				// Asset

				assetEntryLocalService.updateVisible(
					message.getWorkflowClassName(), message.getMessageId(),
					false);

				if (!message.isDiscussion()) {

					// Indexer

					Indexer<MBMessage> indexer =
						IndexerRegistryUtil.nullSafeGetIndexer(MBMessage.class);

					indexer.delete(message);
				}
			}
		}

		// Attachments

		if ((inputStreamOVPs != null) || (existingFiles != null)) {
			if (ListUtil.isNotEmpty(inputStreamOVPs) ||
				ListUtil.isNotEmpty(existingFiles)) {

				List<FileEntry> fileEntries =
					message.getAttachmentsFileEntries();

				for (FileEntry fileEntry : fileEntries) {
					String fileEntryId = String.valueOf(
						fileEntry.getFileEntryId());

					if (!existingFiles.contains(fileEntryId)) {
						if (!TrashUtil.isTrashEnabled(message.getGroupId())) {
							deleteMessageAttachment(
								messageId, fileEntry.getTitle());
						}
						else {
							moveMessageAttachmentToTrash(
								userId, messageId, fileEntry.getTitle());
						}
					}
				}

				Folder folder = message.addAttachmentsFolder();

				PortletFileRepositoryUtil.addPortletFileEntries(
					message.getGroupId(), userId, MBMessage.class.getName(),
					message.getMessageId(), MBConstants.SERVICE_NAME,
					folder.getFolderId(), inputStreamOVPs);
			}
			else {
				if (TrashUtil.isTrashEnabled(message.getGroupId())) {
					List<FileEntry> fileEntries =
						message.getAttachmentsFileEntries();

					for (FileEntry fileEntry : fileEntries) {
						moveMessageAttachmentToTrash(
							userId, messageId, fileEntry.getTitle());
					}
				}
				else {
					deleteMessageAttachments(message.getMessageId());
				}
			}
		}

		message.setExpandoBridgeAttributes(serviceContext);

		mbMessagePersistence.update(message);

		// Thread

		if ((priority != MBThreadConstants.PRIORITY_NOT_GIVEN) &&
			(thread.getPriority() != priority)) {

			thread.setPriority(priority);

			mbThreadPersistence.update(thread);

			updatePriorities(thread.getThreadId(), priority);
		}

		// Asset

		updateAsset(
			userId, message, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds());

		// Workflow

		startWorkflowInstance(userId, message, serviceContext);

		return message;
	}

	@Override
	public MBMessage updateStatus(
			long userId, long messageId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		// Message

		MBMessage message = getMessage(messageId);

		int oldStatus = message.getStatus();

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		Date modifiedDate = serviceContext.getModifiedDate(now);

		message.setStatus(status);
		message.setStatusByUserId(userId);
		message.setStatusByUserName(user.getFullName());
		message.setStatusDate(modifiedDate);

		mbMessagePersistence.update(message);

		// Thread

		MBThread thread = mbThreadPersistence.findByPrimaryKey(
			message.getThreadId());

		updateThreadStatus(thread, message, user, oldStatus, modifiedDate);

		Indexer<MBMessage> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			MBMessage.class);

		if (status == WorkflowConstants.STATUS_APPROVED) {
			if (oldStatus != WorkflowConstants.STATUS_APPROVED) {

				// Asset

				if (serviceContext.isAssetEntryVisible() &&
					((message.getClassNameId() == 0) ||
					 (message.getParentMessageId() != 0))) {

					Date publishDate = null;

					AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
						message.getWorkflowClassName(), message.getMessageId());

					if ((assetEntry != null) &&
						(assetEntry.getPublishDate() != null)) {

						publishDate = assetEntry.getPublishDate();
					}
					else {
						publishDate = now;

						serviceContext.setCommand(Constants.ADD);
					}

					assetEntryLocalService.updateEntry(
						message.getWorkflowClassName(), message.getMessageId(),
						publishDate, null, true, true);
				}

				if (serviceContext.isCommandAdd()) {

					// Social

					JSONObject extraDataJSONObject =
						JSONFactoryUtil.createJSONObject();

					String title = message.getSubject();

					if (message.isDiscussion()) {
						title = HtmlUtil.stripHtml(title);
					}

					extraDataJSONObject.put("title", title);

					if (!message.isDiscussion()) {
						if (!message.isAnonymous() && !user.isDefaultUser()) {
							long receiverUserId = 0;

							MBMessage parentMessage =
								mbMessagePersistence.fetchByPrimaryKey(
									message.getParentMessageId());

							if (parentMessage != null) {
								receiverUserId = parentMessage.getUserId();
							}

							SocialActivityManagerUtil.addActivity(
								message.getUserId(), message,
								MBActivityKeys.ADD_MESSAGE,
								extraDataJSONObject.toString(), receiverUserId);

							if ((parentMessage != null) &&
								(receiverUserId != message.getUserId())) {

								SocialActivityManagerUtil.addActivity(
									message.getUserId(), parentMessage,
									MBActivityKeys.REPLY_MESSAGE,
									extraDataJSONObject.toString(), 0);
							}
						}
					}
					else {
						String className = (String)serviceContext.getAttribute(
							"className");
						long classPK = ParamUtil.getLong(
							serviceContext, "classPK");
						long parentMessageId = message.getParentMessageId();

						if (parentMessageId !=
								MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID) {

							AssetEntry assetEntry =
								assetEntryLocalService.fetchEntry(
									className, classPK);

							if (assetEntry != null) {
								extraDataJSONObject.put(
									"messageId", message.getMessageId());

								SocialActivityManagerUtil.addActivity(
									message.getUserId(), assetEntry,
									SocialActivityConstants.TYPE_ADD_COMMENT,
									extraDataJSONObject.toString(),
									assetEntry.getUserId());
							}
						}
					}
				}
			}

			// Indexer

			indexer.reindex(message);

			// Ping

			pingPingback(message, serviceContext);
		}
		else if (oldStatus == WorkflowConstants.STATUS_APPROVED) {

			// Asset

			assetEntryLocalService.updateVisible(
				message.getWorkflowClassName(), message.getMessageId(), false);

			// Indexer

			indexer.delete(message);
		}

		return message;
	}

	@Override
	public void updateUserName(long userId, String userName) {
		List<MBMessage> messages = mbMessagePersistence.findByUserId(userId);

		for (MBMessage message : messages) {
			message.setUserName(userName);

			mbMessagePersistence.update(message);
		}
	}

	protected String getBody(String subject, String body) {
		if (Validator.isNull(body)) {
			return subject;
		}

		return body;
	}

	protected String getDiscussionMessageSubject(String subject, String body)
		throws MessageBodyException {

		if (Validator.isNotNull(subject)) {
			return subject;
		}

		if (Validator.isNull(body)) {
			throw new MessageBodyException("Body is null");
		}

		subject = HtmlUtil.extractText(body);

		if (subject.length() <= MBMessageConstants.MESSAGE_SUBJECT_MAX_LENGTH) {
			return subject;
		}

		String subjectSubstring = subject.substring(
			0, MBMessageConstants.MESSAGE_SUBJECT_MAX_LENGTH);

		return subjectSubstring + StringPool.TRIPLE_PERIOD;
	}

	protected String getMessageURL(
			MBMessage message, ServiceContext serviceContext)
		throws PortalException {

		String entryURL = GetterUtil.getString(
			serviceContext.getAttribute("entryURL"));

		if (Validator.isNotNull(entryURL)) {
			return entryURL;
		}

		HttpServletRequest request = serviceContext.getRequest();

		if (request == null) {
			if (Validator.isNull(serviceContext.getLayoutFullURL())) {
				return StringPool.BLANK;
			}

			return StringBundler.concat(
				serviceContext.getLayoutFullURL(),
				Portal.FRIENDLY_URL_SEPARATOR, "message_boards/view_message/",
				String.valueOf(message.getMessageId()));
		}

		String portletId = PortletProviderUtil.getPortletId(
			MBMessage.class.getName(), PortletProvider.Action.VIEW);

		String layoutURL = LayoutURLUtil.getLayoutURL(
			message.getGroupId(), portletId, serviceContext);

		if (Validator.isNotNull(layoutURL)) {
			return StringBundler.concat(
				layoutURL, Portal.FRIENDLY_URL_SEPARATOR,
				"message_boards/view_message/",
				String.valueOf(message.getMessageId()));
		}
		else {
			Group group = groupLocalService.fetchGroup(message.getGroupId());

			portletId = PortletProviderUtil.getPortletId(
				MBMessage.class.getName(), PortletProvider.Action.MANAGE);

			PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
				request, group, portletId, 0, 0, PortletRequest.RENDER_PHASE);

			portletURL.setParameter(
				"mvcRenderCommandName", "/message_boards/view_message");
			portletURL.setParameter(
				"messageId", String.valueOf(message.getMessageId()));

			return portletURL.toString();
		}
	}

	protected String getSubject(String subject, String body) {
		if (Validator.isNull(subject)) {
			return StringUtil.shorten(body);
		}

		return subject;
	}

	protected void pingPingback(
		MBMessage message, ServiceContext serviceContext) {

		if (!PropsValues.BLOGS_PINGBACK_ENABLED ||
			!message.isAllowPingbacks() || !message.isApproved()) {

			return;
		}

		String layoutFullURL = serviceContext.getLayoutFullURL();

		if (Validator.isNull(layoutFullURL)) {
			return;
		}

		String sourceUri = StringBundler.concat(
			layoutFullURL, Portal.FRIENDLY_URL_SEPARATOR,
			"message_boards/view_message/",
			String.valueOf(message.getMessageId()));

		Source source = new Source(message.getBody(message.isFormatBBCode()));

		List<StartTag> startTags = source.getAllStartTags("a");

		for (StartTag startTag : startTags) {
			String targetUri = startTag.getAttributeValue("href");

			if (Validator.isNotNull(targetUri)) {
				try {
					LinkbackProducerUtil.sendPingback(sourceUri, targetUri);
				}
				catch (Exception e) {
					_log.error("Error while sending pingback " + targetUri, e);
				}
			}
		}
	}

	protected void startWorkflowInstance(
			long userId, MBMessage message, ServiceContext serviceContext)
		throws PortalException {

		Map<String, Serializable> workflowContext = new HashMap<>();

		workflowContext.put(
			WorkflowConstants.CONTEXT_URL,
			getMessageURL(message, serviceContext));

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			message.getCompanyId(), message.getGroupId(), userId,
			message.getWorkflowClassName(), message.getMessageId(), message,
			serviceContext, workflowContext);
	}

	protected void updateAsset(
			long userId, MBMessage message, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds,
			boolean assetEntryVisible)
		throws PortalException {

		boolean visible = false;
		Date publishDate = null;

		if (assetEntryVisible && message.isApproved() &&
			((message.getClassNameId() == 0) ||
			 (message.getParentMessageId() != 0))) {

			visible = true;
			publishDate = message.getModifiedDate();
		}

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
			userId, message.getGroupId(), message.getCreateDate(),
			message.getModifiedDate(), message.getWorkflowClassName(),
			message.getMessageId(), message.getUuid(), 0, assetCategoryIds,
			assetTagNames, true, visible, null, null, publishDate, null,
			ContentTypes.TEXT_HTML, message.getSubject(), null, null, null,
			null, 0, 0, message.getPriority());

		assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);
	}

	protected void updatePriorities(long threadId, double priority) {
		List<MBMessage> messages = mbMessagePersistence.findByThreadId(
			threadId);

		for (MBMessage message : messages) {
			if (message.getPriority() != priority) {
				message.setPriority(priority);

				mbMessagePersistence.update(message);
			}
		}
	}

	protected void updateThreadStatus(
			MBThread thread, MBMessage message, User user, int oldStatus,
			Date modifiedDate)
		throws PortalException {

		MBCategory category = null;

		int status = message.getStatus();

		if ((thread.getCategoryId() !=
				MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) &&
			(thread.getCategoryId() !=
				MBCategoryConstants.DISCUSSION_CATEGORY_ID)) {

			category = mbCategoryPersistence.findByPrimaryKey(
				thread.getCategoryId());
		}

		if ((thread.getRootMessageId() == message.getMessageId()) &&
			(oldStatus != status)) {

			thread.setModifiedDate(modifiedDate);
			thread.setStatus(status);
			thread.setStatusByUserId(user.getUserId());
			thread.setStatusByUserName(user.getFullName());
			thread.setStatusDate(modifiedDate);
		}

		if (status == oldStatus) {
			return;
		}

		if (status == WorkflowConstants.STATUS_APPROVED) {
			if (message.isAnonymous()) {
				thread.setLastPostByUserId(0);
			}
			else {
				thread.setLastPostByUserId(message.getUserId());
			}

			thread.setLastPostDate(modifiedDate);

			if (category != null) {
				category.setLastPostDate(modifiedDate);

				category = mbCategoryPersistence.update(category);
			}
		}

		if ((oldStatus == WorkflowConstants.STATUS_APPROVED) ||
			(status == WorkflowConstants.STATUS_APPROVED)) {

			// Thread

			MBUtil.updateThreadMessageCount(thread.getThreadId());

			// Category

			if ((category != null) &&
				(thread.getRootMessageId() == message.getMessageId())) {

				MBUtil.updateCategoryStatistics(category.getCategoryId());
			}

			if ((category != null) &&
				!(thread.getRootMessageId() == message.getMessageId())) {

				MBUtil.updateCategoryMessageCount(category.getCategoryId());
			}
		}

		// Indexer

		Indexer<MBThread> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			MBThread.class);

		indexer.reindex(thread);

		mbThreadPersistence.update(thread);
	}

	protected void validate(String subject, String body)
		throws PortalException {

		if (Validator.isNull(subject) && Validator.isNull(body)) {
			throw new MessageSubjectException("Subject and body are null");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MBMessageLocalServiceImpl.class);

}