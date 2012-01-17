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

package org.eclipse.birt.barcode;

import org.eclipse.birt.report.designer.ui.extensions.ReportItemFigureProvider;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.birt.report.model.api.extension.IReportItem;
import org.eclipse.draw2d.IFigure;

/**
 * RotatedTextFigureUI
 */
public class BarCodeFigureUI extends ReportItemFigureProvider
{

	@Override
	public IFigure createFigure( ExtendedItemHandle handle )
	{
		try
		{
			IReportItem item = handle.getReportItem( );

			if ( item instanceof BarCodeItem )
			{
				return new BarCodeFigure( (BarCodeItem) item );
			}
		}
		catch ( ExtendedElementException e )
		{
			e.printStackTrace( );
		}
		return null;
	}

	@Override
	public void updateFigure( ExtendedItemHandle handle, IFigure figure )
	{
		try
		{
			IReportItem item = handle.getReportItem( );

			if ( item instanceof BarCodeItem )
			{
				BarCodeFigure fig = (BarCodeFigure) figure;

				fig.setBarCodeItem( (BarCodeItem) item );
			}
		}
		catch ( ExtendedElementException e )
		{
			e.printStackTrace( );
		}
	}

	@Override
	public void disposeFigure( ExtendedItemHandle handle, IFigure figure )
	{
		( (BarCodeFigure) figure ).dispose( );
	}

}
