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

import org.eclipse.birt.report.designer.ui.extensions.IReportItemImageProvider;
import org.eclipse.birt.report.extension.barcode.util.SwtGraphicsUtil;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.birt.report.model.api.extension.IReportItem;
import org.eclipse.swt.graphics.Image;

/**
 * RotatedTextImageUI
 */
public class BarCodeImageUI implements IReportItemImageProvider
{

	@Override
	public void disposeImage( ExtendedItemHandle handle, Image image )
	{
		if ( image != null && !image.isDisposed( ) )
		{
			image.dispose( );
		}

	}

	@Override
	public Image getImage( ExtendedItemHandle handle )
	{
		try
		{
			IReportItem item = handle.getReportItem( );

			if ( item instanceof BarCodeItem )
			{
				String type = ( (BarCodeItem) item ).getBarCodeType();
				String barCode = ( (BarCodeItem) item ).getBarCode();

				return SwtGraphicsUtil.createRotatedTextImage( barCode,
						0,
						null );
			}
		}
		catch ( ExtendedElementException e )
		{
			e.printStackTrace( );
		}
		return null;
	}

}
