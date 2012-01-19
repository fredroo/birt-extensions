package org.eclipse.birt.report.extension.rectangle;

import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.IMessages;
import org.eclipse.birt.report.model.api.extension.IReportItem;
import org.eclipse.birt.report.model.api.extension.ReportItemFactory;

public class RectangleItemFactory extends ReportItemFactory {

	@Override
	public IReportItem newReportItem(DesignElementHandle modelHanlde) {
		if ( modelHanlde instanceof ExtendedItemHandle
				&& RectangleItem.EXTENSION_NAME.equals( ( (ExtendedItemHandle) modelHanlde ).getExtensionName( ) ) )
		{
			return new RectangleItem( (ExtendedItemHandle) modelHanlde );
		}
		return null;
	}

	@Override
	public IMessages getMessages() {
		return null;
	}

}
