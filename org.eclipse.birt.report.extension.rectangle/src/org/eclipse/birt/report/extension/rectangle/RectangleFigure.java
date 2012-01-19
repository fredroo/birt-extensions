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
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;

/**
 * RotatedTextFigure
 */
public class RectangleFigure extends CommonFigure {

	private String lastBarCode;
	private String lastBarCodeType;

	//	private Image cachedImage;
	//
	//	private final RectangleItem barCodeItem;
	//	private int height;
	//	private int lastWidth;
	//	private int lastHeight;

	RectangleFigure(ExtendedItemHandle handle) {
		super(handle);
		//		super();
		//
		//		this.barCodeItem=barCodeItem;
	}

	//	@Override
	//	public void setSize(int w, int h) {
	//		height=h;
	//		super.setSize(w, h);
	//	}
	//
	//	@Override
	//	public Dimension getPreferredSize(int wHint, int hHint) {
	//		return new Dimension(Math.max(wHint, 30), Math.max(height, 30));
	//	}
	//	@Override
	//	protected void paintFigure(Graphics graphics) {
	//		final Rectangle r=getClientArea().getCopy();
	//
	//		String barCode=barCodeItem.getBarCode();
	//		String barCodeType=barCodeItem.getBarCodeType();
	//
	//		if (barCode == null)
	//			barCode=""; //$NON-NLS-1$
	//
	//		if (!barCode.equals(lastBarCode) || barCodeType != lastBarCodeType || lastWidth != getSize().width || lastHeight != getSize().height
	//				|| cachedImage == null || cachedImage.isDisposed()) {
	//			lastBarCode=barCode;
	//			lastBarCodeType=barCodeType;
	//			lastWidth=getSize().width;
	//			lastHeight=getSize().height;
	//
	//			if (cachedImage != null && !cachedImage.isDisposed())
	//				cachedImage.dispose();
	//
	//			//			Dimension dimension=shrink(shrink(getSize(), getInsets()), getBorder().getInsets(this));
	//			cachedImage=SwtGraphicsUtil.createRotatedTextImage(barCode, 10, getFont(), r.getSize());
	//		}
	//
	//		if (cachedImage != null && !cachedImage.isDisposed()) {
	//			System.out.println("RectangleFigure.paintFigure() " + r);
	//			graphics.drawImage(cachedImage, r.x, r.y);
	//			//			graphics.setBackgroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_CYAN));
	//			//			graphics.fillRectangle(r.x, r.y, r.width, r.height);
	//		}
	//	}
	//
	//	//	private Dimension shrink(Dimension size, Insets insets) {
	//	//		return size.shrink(insets.getWidth(), insets.getHeight());
	//	//	}
	//
	//	void setBarCodeItem(RectangleItem item) {
	//		this.barCodeItem=item;
	//	}
	//
	//	void dispose() {
	//		if (cachedImage != null && !cachedImage.isDisposed()) {
	//			cachedImage.dispose();
	//		}
	//	}
	@Override
	protected boolean hasChanged() {
		String barCode=getBarCodeItem().getBarCode();
		String barCodeType=getBarCodeItem().getBarCodeType();

		if (barCode == null)
			barCode=""; //$NON-NLS-1$

		return (!barCode.equals(lastBarCode)) || (barCodeType != lastBarCodeType);
	}

	private RectangleItem getBarCodeItem() {
		return (RectangleItem) getItem();
	}

	@Override
	protected Image createImage(Dimension dimension, ExtendedItemHandle extendedItemHandle) throws ExtendedElementException {
		return SwtGraphicsUtil.createRotatedTextImage(extendedItemHandle, dimension);
	}
}
