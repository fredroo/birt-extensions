package org.eclipse.birt.report.extension.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.eclipse.birt.report.designer.ui.preferences.DateSetPreferencePage;
import org.eclipse.birt.report.model.api.DataSetHandle;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.ReportItemHandle;
import org.eclipse.birt.report.model.api.extension.ReportItem;
import org.eclipse.birt.report.model.elements.interfaces.IReportItemModel;
import org.osgi.framework.Bundle;

public class CommonItem extends ReportItem
{
	protected final ExtendedItemHandle modelHandle;

	protected static String getExtensionName(Bundle bundle) {
		Properties props=new Properties();
		InputStream stream=null;
		try {
			URL url=bundle.getResource("plugin.properties");
			stream=url.openStream();
			props.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (stream != null)
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return props.getProperty("extension.name", "");
	}

	public CommonItem( ExtendedItemHandle modelHandle ) {
		this.modelHandle = modelHandle;
	}

	public ExtendedItemHandle getModelHandle( )
	{
		return modelHandle;
	}
	
	public DataSetHandle getDataSet() {
		return modelHandle.getDataSet();
	}
}