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

package org.eclipse.birt.report.extension.rectangle;

import org.eclipse.birt.report.extension.common.CommonFigure;
import org.eclipse.birt.report.extension.rectangle.util.SwtGraphicsUtil;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;

/**
 * RotatedTextFigure
 */
public class RectangleFigure extends CommonFigure {

	private String lastText;
	private int lastArcSize;

	RectangleFigure(ExtendedItemHandle handle) {
		super(handle);
	}

	@Override
	protected boolean hasChanged() {
		String text=getBarCodeItem().getText();
		int arcSize=getBarCodeItem().getArcSize();

		if (text == null)
			text=""; //$NON-NLS-1$

		return (!text.equals(lastText)) || (arcSize != lastArcSize);
	}

	private RectangleItem getBarCodeItem() {
		return (RectangleItem) getItem();
	}

	@Override
	protected Image createImage(Dimension dimension, ExtendedItemHandle extendedItemHandle) throws ExtendedElementException {
		return SwtGraphicsUtil.createRoundedRectangleImage(extendedItemHandle, dimension);
	}

	@Override
	protected void paintBorder(Graphics graphics) {
		// NO-OP
	}
}
