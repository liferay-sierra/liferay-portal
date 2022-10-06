/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.segments.experiment.web.internal.display.context;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.segments.constants.SegmentsExperimentConstants;
import com.liferay.segments.constants.SegmentsPortletKeys;
import com.liferay.segments.manager.SegmentsExperienceManager;
import com.liferay.segments.model.SegmentsExperiment;
import com.liferay.segments.service.SegmentsExperimentService;

import java.util.Map;
import java.util.Optional;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eduardo García
 * @author Sarai Díaz
 */
public class SegmentsExperimentDisplayContext {

	public SegmentsExperimentDisplayContext(
		HttpServletRequest httpServletRequest,
		LayoutLocalService layoutLocalService, Portal portal,
		SegmentsExperienceManager segmentsExperienceManager,
		SegmentsExperimentService segmentsExperimentService) {

		_httpServletRequest = httpServletRequest;
		_layoutLocalService = layoutLocalService;
		_portal = portal;
		_segmentsExperienceManager = segmentsExperienceManager;
		_segmentsExperimentService = segmentsExperimentService;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public Map<String, Object> getData() throws Exception {
		if (_data != null) {
			return _data;
		}

		_data = HashMapBuilder.<String, Object>put(
			"context", _getContext()
		).build();

		return _data;
	}

	private Optional<SegmentsExperiment> _getActiveSegmentsExperimentOptional(
			long segmentsExperienceId)
		throws Exception {

		Layout layout = _themeDisplay.getLayout();

		return Optional.ofNullable(
			_segmentsExperimentService.fetchSegmentsExperiment(
				segmentsExperienceId, _portal.getClassNameId(Layout.class),
				layout.getPlid(),
				SegmentsExperimentConstants.Status.getExclusiveStatusValues()));
	}

	private Map<String, Object> _getContext() throws Exception {
		return HashMapBuilder.<String, Object>put(
			"namespace",
			_portal.getPortletNamespace(
				SegmentsPortletKeys.SEGMENTS_EXPERIMENT)
		).put(
			"segmentExperimentDataURL", _getSegmentExperimentDataURL()
		).build();
	}

	private String _getRedirect() throws Exception {
		Layout draftLayout = _layoutLocalService.fetchDraftLayout(
			_themeDisplay.getPlid());

		if (draftLayout == null) {
			return StringPool.BLANK;
		}

		String layoutFullURL = PortalUtil.getLayoutFullURL(
			draftLayout, _themeDisplay);

		String layoutURL = _portal.getLayoutURL(_themeDisplay);

		long segmentsExperienceId = _getSegmentsExperienceId();

		if (segmentsExperienceId != -1) {
			layoutURL = HttpComponentsUtil.setParameter(
				layoutURL, "segmentsExperienceId", segmentsExperienceId);
		}

		layoutFullURL = HttpComponentsUtil.setParameter(
			layoutFullURL, "p_l_back_url", layoutURL);

		layoutFullURL = HttpComponentsUtil.setParameter(
			layoutFullURL, "p_l_mode", Constants.EDIT);
		layoutFullURL = HttpComponentsUtil.setParameter(
			layoutFullURL, "redirect", layoutFullURL);

		return layoutFullURL;
	}

	private String _getSegmentExperimentDataURL() throws Exception {
		String layoutURL = _portal.getLayoutURL(_themeDisplay);

		long segmentsExperienceId = _getSegmentsExperienceId();

		if (segmentsExperienceId != -1) {
			layoutURL = HttpComponentsUtil.setParameter(
				layoutURL, "segmentsExperienceId", segmentsExperienceId);
		}

		ResourceURL resourceURL = (ResourceURL)PortletURLBuilder.create(
			_portal.getControlPanelPortletURL(
				_httpServletRequest, _themeDisplay.getScopeGroup(),
				SegmentsPortletKeys.SEGMENTS_EXPERIMENT, 0, 0,
				PortletRequest.RESOURCE_PHASE)
		).setRedirect(
			_getRedirect()
		).setBackURL(
			layoutURL
		).setParameter(
			"plid", _themeDisplay.getPlid()
		).setParameter(
			"segmentsExperienceId", _getSelectedSegmentsExperienceId()
		).buildPortletURL();

		resourceURL.setResourceID("/segments_experiment/get_data");

		return resourceURL.toString();
	}

	private long _getSegmentsExperienceId() throws Exception {
		SegmentsExperiment segmentsExperiment = _getSegmentsExperiment();

		if (segmentsExperiment != null) {
			return segmentsExperiment.getSegmentsExperienceId();
		}

		return _getSelectedSegmentsExperienceId();
	}

	private SegmentsExperiment _getSegmentsExperiment() throws Exception {
		if (_segmentsExperiment != null) {
			return _segmentsExperiment;
		}

		_segmentsExperiment = _getActiveSegmentsExperimentOptional(
			_getSelectedSegmentsExperienceId()
		).orElse(
			null
		);

		return _segmentsExperiment;
	}

	private long _getSelectedSegmentsExperienceId() {
		if (Validator.isNotNull(_segmentsExperienceId)) {
			return _segmentsExperienceId;
		}

		HttpServletRequest originalHttpServletRequest =
			_portal.getOriginalServletRequest(_httpServletRequest);

		long selectedSegmentsExperienceId = ParamUtil.getLong(
			originalHttpServletRequest, "segmentsExperienceId", -1);

		if (selectedSegmentsExperienceId != -1) {
			_segmentsExperienceId = selectedSegmentsExperienceId;
		}
		else {
			_segmentsExperienceId =
				_segmentsExperienceManager.getSegmentsExperienceId(
					_httpServletRequest);
		}

		return _segmentsExperienceId;
	}

	private Map<String, Object> _data;
	private final HttpServletRequest _httpServletRequest;
	private final LayoutLocalService _layoutLocalService;
	private final Portal _portal;
	private Long _segmentsExperienceId;
	private final SegmentsExperienceManager _segmentsExperienceManager;
	private SegmentsExperiment _segmentsExperiment;
	private final SegmentsExperimentService _segmentsExperimentService;
	private final ThemeDisplay _themeDisplay;

}