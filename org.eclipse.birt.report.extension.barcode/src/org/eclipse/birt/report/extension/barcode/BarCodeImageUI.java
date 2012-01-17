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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.birt.report.designer.ui.extensions.IReportItemImageProvider;
import org.eclipse.birt.report.extension.barcode.util.BarCodeGenerator;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.birt.report.model.api.extension.IReportItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * RotatedTextImageUI
 */
public class BarCodeImageUI implements IReportItemImageProvider {
	private static final Logger LOG=Logger.getLogger(BarCodeImageUI.class.getName());
	private final BarCodeGenerator barCodeGenerator=new BarCodeGenerator();

	@Override
	public void disposeImage(ExtendedItemHandle handle, Image image) {
		if (image != null && !image.isDisposed()) {
			image.dispose();
		}

	}

	@Override
	public Image getImage(ExtendedItemHandle handle) {
		try {
			IReportItem item=handle.getReportItem();

			if (item instanceof BarCodeItem) {
				String type=((BarCodeItem) item).getBarCodeType();
				String barCode=((BarCodeItem) item).getBarCode();
				Display display=Display.getCurrent();
				try {
					return new Image(display, barCodeGenerator.generateBarCodeByteArray(type, barCode,30));
				} catch (IOException e) {
					LOG.log(Level.SEVERE, "Error generating barcode", e);
					return null;
				}
			}
		} catch (ExtendedElementException e) {
			e.printStackTrace();
		}
		return null;
	}

}
