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

package com.liferay.util.poi;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.InputStream;

import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author Mirco Tamburini
 */
public class XLSTextStripper {

	public XLSTextStripper(InputStream inputStream) {
		String text = null;

		try {
			StringBundler sb = new StringBundler();

			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

			int numOfSheets = workbook.getNumberOfSheets();

			for (int i = 0; i < numOfSheets; i++) {
				HSSFSheet sheet = workbook.getSheetAt(i);

				Iterator<Row> rowIterator = sheet.rowIterator();

				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();

					Iterator<Cell> cellIterator = row.cellIterator();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						String cellStringValue = null;

						if (cell.getCellType() == CellType.BOOLEAN) {
							boolean booleanValue = cell.getBooleanCellValue();

							cellStringValue = String.valueOf(booleanValue);
						}
						else if (cell.getCellType() == CellType.NUMERIC) {
							double doubleValue = cell.getNumericCellValue();

							cellStringValue = String.valueOf(doubleValue);
						}
						else if (cell.getCellType() == CellType.STRING) {
							RichTextString richTextString =
								cell.getRichStringCellValue();

							cellStringValue = richTextString.getString();
						}

						if (cellStringValue != null) {
							sb.append(cellStringValue);
							sb.append("\t");
						}
					}

					sb.append("\n");
				}
			}

			text = sb.toString();
		}
		catch (Exception exception) {
			_log.error(exception);
		}

		_text = text;
	}

	public String getText() {
		return _text;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		XLSTextStripper.class);

	private final String _text;

}