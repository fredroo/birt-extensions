/*******************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.report.extension.barcode;

import org.eclipse.birt.report.extension.barcode.util.BarCodeGenerator;
import org.eclipse.birt.report.extension.common.CommonFigure;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;

/**
 * RotatedTextFigure
 */
public class BarCodeFigure extends CommonFigure {

	private String lastBarCode;
	private BarCodeGenerator lastBarCodeType;

	BarCodeFigure(ExtendedItemHandle handle) {
		super(handle);
	}

	@Override
	protected Image createImage(Dimension dimension, ExtendedItemHandle handle) throws ExtendedElementException {
		BarCodeItem item=(BarCodeItem) handle.getReportItem();
		return item.getBarCodeType().generateBarCodeByteArray(handle, dimension);
	}

	@Override
	protected boolean hasChanged() {
		String barCode=getBarCodeItem().getBarCode();
		BarCodeGenerator barCodeType=getBarCodeItem().getBarCodeType();

		if (barCode == null) {
			barCode="";
		}

		return (!barCode.equals(lastBarCode)) || (!barCodeType.equals(lastBarCodeType));
	}

	private BarCodeItem getBarCodeItem() {
		return (BarCodeItem) getItem();
	}

	@Override
	protected int getMinimumHeight() {
		return 100;
	}
}
