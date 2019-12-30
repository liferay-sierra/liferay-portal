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

package com.liferay.talend.tliferaybatchoutput;

import com.liferay.talend.BasePropertiesTestCase;
import com.liferay.talend.common.schema.constants.BatchSchemaConstants;
import com.liferay.talend.properties.batch.LiferayBatchOutputProperties;

import java.util.Objects;

import org.apache.avro.Schema;

import org.junit.Assert;
import org.junit.Test;

import org.talend.components.api.component.Connector;
import org.talend.components.api.component.PropertyPathConnector;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.common.SchemaProperties;

/**
 * @author Igor Beslic
 */
public class TLiferayBatchOutputPropertiesTest extends BasePropertiesTestCase {

	@Test
	public void testComponentConnectors() throws Exception {
		TLiferayBatchOutputDefinition tLiferayBatchOutputDefinition =
			new TLiferayBatchOutputDefinition();

		Class<? extends ComponentProperties> propertyClass =
			tLiferayBatchOutputDefinition.getPropertyClass();

		Assert.assertEquals(
			"Component properties implementation class",
			LiferayBatchOutputProperties.class, propertyClass);

		ComponentProperties componentProperties =
			getDefaultInitializedComponentPropertiesInstance(propertyClass);

		for (Connector outputConnector :
				componentProperties.getPossibleConnectors(true)) {

			PropertyPathConnector propertyPathConnector =
				(PropertyPathConnector)outputConnector;

			Schema expectedSchema = SchemaProperties.EMPTY_SCHEMA;

			if (Objects.equals(
					outputConnector.getName(), Connector.MAIN_NAME)) {

				expectedSchema = BatchSchemaConstants.SCHEMA;
			}

			assertEquals(
				componentProperties, propertyPathConnector.getPropertyPath(),
				expectedSchema);
		}

		for (Connector inputConnector :
				componentProperties.getPossibleConnectors(false)) {

			PropertyPathConnector propertyPathConnector =
				(PropertyPathConnector)inputConnector;

			Schema expectedSchema = SchemaProperties.EMPTY_SCHEMA;

			if (Objects.equals(
					inputConnector.getName(), Connector.MAIN_NAME + "_INPUT")) {

				expectedSchema = BatchSchemaConstants.SCHEMA;
			}

			assertEquals(
				componentProperties, propertyPathConnector.getPropertyPath(),
				expectedSchema);
		}
	}

}