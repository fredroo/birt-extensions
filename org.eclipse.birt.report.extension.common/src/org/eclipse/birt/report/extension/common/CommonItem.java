package org.eclipse.birt.report.extension.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ReportItem;
import org.osgi.framework.Bundle;

public class CommonItem extends ReportItem
{
	//	public static final String EXTENSION_NAME;// = "BarCode"; //$NON-NLS-1$
	public static final String BAR_CODE_TYPE_PROP="barCodeType"; //$NON-NLS-1$
	public static final String BAR_CODE = "barCode"; //$NON-NLS-1$

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

	//	public String getBarCodeType() {
	//		return modelHandle.getStringProperty(BAR_CODE_TYPE_PROP);
	//	}
	//
	//	public void setBarCodeType(String value) throws SemanticException {
	//		modelHandle.setProperty(BAR_CODE_TYPE_PROP, value);
	//	}
	//
	//	public String getBarCode()
	//	{
	//		return modelHandle.getStringProperty( BAR_CODE );
	//	}
	//
	//	public void setBarCode( String value ) throws SemanticException
	//	{
	//		modelHandle.setProperty( BAR_CODE, value );
	//	}
}