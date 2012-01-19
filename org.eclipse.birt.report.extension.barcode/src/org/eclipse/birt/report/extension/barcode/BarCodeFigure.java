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

	//	private Image cachedImage;

	//	private BarCodeItem barCodeItem;
	//	private int height;

	//	private final Dimension size=new Dimension();

	//	private final BarCodeGenerator barCodeGenerator=new BarCodeGenerator();
	//	private static final Logger LOG=Logger.getLogger(BarCodeFigure.class.getName());

	BarCodeFigure(ExtendedItemHandle handle) {
		super(handle);
		//		this.barCodeItem=barCodeItem;

		//		addMouseListener(new MouseListener.Stub() {
		//
		//			@Override
		//			public void mousePressed(MouseEvent me) {
		//				if (me.button == 2) {
		//					System.out.println("Button 2 Pressed");
		//				}
		//			}
		//		});
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
	//	public void setPreferredSize(Dimension size) {
	//		// TODO Auto-generated method stub
	//		super.setPreferredSize(size);
	//	}
	//
	//	@Override
	//	public void setSize(int w, int h) {
	//		//		System.out.println("BarCodeFigure.setSize()");
	//		super.setSize(w, h);
	//	}
	//
	//	@Override
	//	public Dimension getMinimumSize(int wHint, int hHint) {
	//		return getPreferredSize(wHint, hHint);
	//	}

	//	@Override
	//	public Dimension getPreferredSize(int wHint, int hHint) {
	//		System.out.println("BarCodeFigure.getPreferredSize() " + hHint);
	//		//		Display display=Display.getCurrent();
	//
	//		//		GC gc=null;
	//
	//		//		try {
	//		//			String barCode=barCodeItem.getBarCode();
	//		//			String barCodeType=barCodeItem.getBarCodeType();
	//		//
	//		//			gc=new GC(display);
	//
	//		return new Dimension(wHint, Math.max(size.height, 30));
	//		//		} finally {
	//		//			if (gc != null && !gc.isDisposed()) {
	//		//				gc.dispose();
	//		//			}
	//		//		}
	//	}
	//
	//	@Override
	//	public void setSize(int w, int h) {
	//		super.setSize(w, h);
	//		size.width=w;
	//		size.height=h;
	//	}

	//	@Override
	//	protected void paintClientArea(Graphics graphics) {
	//	@Override
	//	protected void paintFigure(Graphics graphics) {
	//		final Rectangle r=getClientArea().getCopy();
	//
	//		String barCode=barCodeItem.getBarCode();
	//		BarCodeGenerator barCodeType=barCodeItem.getBarCodeType();
	//
	//		if (barCode == null) {
	//			barCode="";
	//		}
	//
	//		if ((!barCode.equals(lastBarCode)) || (!barCodeType.equals(lastBarCodeType)) || cachedImage == null || cachedImage.isDisposed()) {
	//			lastBarCode=barCode;
	//			lastBarCodeType=barCodeType;
	//
	//			if (cachedImage != null && !cachedImage.isDisposed()) {
	//				cachedImage.dispose();
	//			}
	//			Display display=Display.getCurrent();
	//			try {
	//				cachedImage=new Image(display, barCodeType.generateBarCodeByteArray(barCode, 30));
	//			} catch (IOException e) {
	//				LOG.log(Level.SEVERE, "Error generating Barcode", e);
	//			}
	//		}
	//
	//		if (cachedImage != null && !cachedImage.isDisposed()) {
	//			graphics.drawImage(cachedImage, r.x, r.y);
	//		}
	//	}
	//
	//	void setBarCodeItem(BarCodeItem item) {
	//		this.barCodeItem=item;
	//	}
	//
	//	void dispose() {
	//		if (cachedImage != null && !cachedImage.isDisposed()) {
	//			cachedImage.dispose();
	//		}
	//	}

	//	@Override
	//	public Border getOutsideBorder() {
	//		return getBorder();
	//	}
}
