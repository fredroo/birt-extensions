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

import org.eclipse.birt.report.extension.barcode.util.BarCodeGenerator;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * RotatedTextFigure
 */
public class BarCodeFigure extends Figure {

	private String lastBarCode;
	private String lastBarCodeType;

	private Image cachedImage;

	private BarCodeItem barCodeItem;

	private final BarCodeGenerator barCodeGenerator=new BarCodeGenerator();
	private static final Logger LOG=Logger.getLogger(BarCodeFigure.class.getName());

	BarCodeFigure(BarCodeItem barCodeItem) {
		super();

		this.barCodeItem=barCodeItem;

		addMouseListener(new MouseListener.Stub() {

			@Override
			public void mousePressed(MouseEvent me) {
				if (me.button == 2) {
					System.out.println("Button 2 Pressed");
				}
			}
		});
	}

	@Override
	public Dimension getMinimumSize(int hint, int hint2) {
		return getPreferredSize(hint, hint2);
	}

	@Override
	public Dimension getPreferredSize(int hint, int hint2) {
		Display display=Display.getCurrent();

		GC gc=null;

		try {
			String barCode=barCodeItem.getBarCode();
			String barCodeType=barCodeItem.getBarCodeType();

			gc=new GC(display);

			return new Dimension(hint, 30);
		} finally {
			if (gc != null && !gc.isDisposed()) {
				gc.dispose();
			}
		}
	}

	@Override
	protected void paintClientArea(Graphics graphics) {
		final Rectangle r=getClientArea().getCopy();

		String barCode=barCodeItem.getBarCode();
		String barCodeType=barCodeItem.getBarCodeType();

		if (barCode == null) {
			barCode="";
		}

		if (!barCode.equals(lastBarCode) || barCodeType != lastBarCodeType || cachedImage == null || cachedImage.isDisposed()) {
			lastBarCode=barCode;
			lastBarCodeType=barCodeType;

			if (cachedImage != null && !cachedImage.isDisposed()) {
				cachedImage.dispose();
			}
			Display display=Display.getCurrent();
			try {
				cachedImage=new Image(display, barCodeGenerator.generateBarCodeByteArray(barCodeType, barCode, 30));
			} catch (IOException e) {
				LOG.log(Level.SEVERE, "Error generating Barcode", e);
			}
		}

		if (cachedImage != null && !cachedImage.isDisposed()) {
			graphics.drawImage(cachedImage, r.x, r.y);
		}
	}

	void setBarCodeItem(BarCodeItem item) {
		this.barCodeItem=item;
	}

	void dispose() {
		if (cachedImage != null && !cachedImage.isDisposed()) {
			cachedImage.dispose();
		}
	}
}
