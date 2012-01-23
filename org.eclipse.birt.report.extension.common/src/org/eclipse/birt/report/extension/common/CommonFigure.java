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

package org.eclipse.birt.report.extension.common;

import org.eclipse.birt.report.designer.internal.ui.editors.schematic.figures.ReportElementFigure;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

/**
 * RotatedTextFigure
 */
public abstract class CommonFigure extends ReportElementFigure {

	private Image cachedImage;

	private int lastWidth;
	private int lastHeight;

	private ExtendedItemHandle handle;

	protected CommonFigure(ExtendedItemHandle handle) {
		this.handle=handle;
	}

	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		return new Dimension(Math.max(wHint, getMinimumWidth()), Math.max(ExtendedItemHandleUtil.getDimension(getHandle()).height, getMinimumHeight()));
	}

	protected int getMinimumWidth() {
		return 30;
	}

	protected int getMinimumHeight() {
		return 30;
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		final Rectangle r=getClientArea().getCopy();

		if (hasChanged() /* !barCode.equals(lastBarCode) || barCodeType != lastBarCodeType */|| lastWidth != getSize().width || lastHeight != getSize().height
				|| cachedImage == null || cachedImage.isDisposed()) {
			//			lastBarCode=barCode;
			//			lastBarCodeType=barCodeType;
			lastWidth=getSize().width;
			lastHeight=getSize().height;

			if (cachedImage != null && !cachedImage.isDisposed())
				cachedImage.dispose();

			//			Dimension dimension=shrink(shrink(getSize(), getInsets()), getBorder().getInsets(this));
			try {
				cachedImage=createImage(getClientArea().getCopy().getSize(), getHandle());
			} catch (ExtendedElementException e) {
				e.printStackTrace();
			}
		}

		if (cachedImage != null && !cachedImage.isDisposed()) {
			//			System.out.println("RectangleFigure.paintFigure() " + r);
			graphics.drawImage(cachedImage, r.x, r.y);
			//			graphics.setBackgroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_CYAN));
			//			graphics.fillRectangle(r.x, r.y, r.width, r.height);
		}
	}

	protected abstract Image createImage(Dimension dimension, ExtendedItemHandle extendedItemHandle) throws ExtendedElementException;

	protected abstract boolean hasChanged();

	void dispose() {
		if (cachedImage != null && !cachedImage.isDisposed()) {
			cachedImage.dispose();
		}
	}

	public CommonItem getItem() {
		try {
			return (CommonItem) handle.getReportItem();
		} catch (ExtendedElementException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* package */ExtendedItemHandle getHandle() {
		return handle;
	}

	/* package */void setExtendedItemHandle(ExtendedItemHandle handle) {
		this.handle=handle;
	}
}
