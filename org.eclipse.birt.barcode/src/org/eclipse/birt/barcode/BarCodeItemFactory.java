package org.eclipse.birt.barcode;

import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.IMessages;
import org.eclipse.birt.report.model.api.extension.IReportItem;
import org.eclipse.birt.report.model.api.extension.ReportItemFactory;

public class BarCodeItemFactory extends ReportItemFactory {

	@Override
	public IReportItem newReportItem(DesignElementHandle modelHanlde) {
		if ( modelHanlde instanceof ExtendedItemHandle
				&& BarCodeItem.EXTENSION_NAME.equals( ( (ExtendedItemHandle) modelHanlde ).getExtensionName( ) ) )
		{
			return new BarCodeItem( (ExtendedItemHandle) modelHanlde );
		}
		return null;
	}

	@Override
	public IMessages getMessages() {
		return null;
	}

}
