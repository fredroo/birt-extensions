package org.eclipse.birt.report.extension.barcode;

import org.eclipse.birt.report.designer.ui.extensions.IReportItemLabelProvider;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.birt.report.model.api.extension.IReportItem;

public class BarCodeLabelUI implements IReportItemLabelProvider
{
	@Override
	public String getLabel( ExtendedItemHandle handle )
	{
		try
		{
			IReportItem item = handle.getReportItem( );
			if ( item instanceof BarCodeItem )
			{
				return ( (BarCodeItem) item ).getBarCode();
			}
		}
		catch ( ExtendedElementException e )
		{
			e.printStackTrace( );
		}
		return null;
	}
}
