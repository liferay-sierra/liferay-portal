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

package com.liferay.client.extension.internal.upgrade.v3_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Brian Wing Shun Chan
 */
public class ClientExtensionEntryUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasColumn("RemoteAppEntry", "remoteAppEntryId")) {
			alterColumnName(
				"RemoteAppEntry", "remoteAppEntryId", "clientExtensionEntryId");
		}

		if (hasTable("RemoteAppEntry")) {
			runSQL("DROP_TABLE_IF_EXISTS(ClientExtensionEntry)");

			runSQL("alter_table_name RemoteAppEntry ClientExtensionEntry");
		}

		runSQL(
			"delete from ServiceComponent where buildNamespace = 'RemoteApp'");
	}

}