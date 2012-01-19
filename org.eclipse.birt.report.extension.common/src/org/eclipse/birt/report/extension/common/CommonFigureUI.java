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

import org.eclipse.birt.report.designer.ui.extensions.ReportItemFigureProvider;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.birt.report.model.api.extension.IReportItem;
import org.eclipse.draw2d.IFigure;

/**
 * RotatedTextFigureUI
 */
public abstract class CommonFigureUI extends ReportItemFigureProvider
{

	@Override
	public IFigure createFigure( ExtendedItemHandle handle )
	{
		try
		{
			//			IReportItem item = handle.getReportItem( );

			return handle(handle);
		}
		catch ( ExtendedElementException e )
		{
			e.printStackTrace( );
		}
		return null;
	}

	protected abstract CommonFigure handle(ExtendedItemHandle handle) throws ExtendedElementException;

	//		if ( item instanceof RectangleItem )
	//		{
	//			return new RectangleFigure( (RectangleItem) item );
	//		}
	//	}

	@Override
	public void updateFigure( ExtendedItemHandle handle, IFigure figure )
	{
		try
		{
			IReportItem item = handle.getReportItem( );

			if ( item instanceof CommonItem )
			{
				CommonFigure fig=(CommonFigure) figure;

				fig.setExtendedItemHandle(handle);
				//				fig.setSize(ExtendedItemHandleUtil.getDimension(handle));
				//
				//				fig.setFont(ExtendedItemHandleUtil.getFont(handle));
				//
				//				fig.setItem((CommonItem) item);
				//				fig.setBarCodeItem( (RectangleItem) item );
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
		((CommonFigure) figure).dispose();
	}

}
