package org.eclipse.birt.report.extension.rectangle;

import org.eclipse.birt.report.extension.common.CommonItem;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;

public class RectangleItem extends CommonItem
{
	public static final String EXTENSION_NAME=getExtensionName(RectangleActivator.getDefault().getBundle());// = "BarCode"; //$NON-NLS-1$
	public static final String BAR_CODE_TYPE_PROP="barCodeType"; //$NON-NLS-1$
	public static final String BAR_CODE = "barCode"; //$NON-NLS-1$

	//	private final ExtendedItemHandle modelHandle;

	//	static {
//		Properties props=new Properties();
//		InputStream stream=null;
//		try {
//			URL url=RectangleActivator.getDefault().getBundle().getResource("plugin.properties");
//			stream=url.openStream();
//			props.load(stream);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (stream != null)
//				try {
//					stream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//		}
//		EXTENSION_NAME=props.getProperty("extension.name", "");
//	}

	public RectangleItem( ExtendedItemHandle modelHandle ) {
		super(modelHandle);
	}

	//	@Override
	//	public ExtendedItemHandle getModelHandle( )
	//	{
	//		return modelHandle;
	//	}

	public String getBarCodeType() {
		return modelHandle.getStringProperty(BAR_CODE_TYPE_PROP);
	}

	public void setBarCodeType(String value) throws SemanticException {
		modelHandle.setProperty(BAR_CODE_TYPE_PROP, value);
	}

	public String getBarCode()
	{
		return modelHandle.getStringProperty( BAR_CODE );
	}

	public void setBarCode( String value ) throws SemanticException
	{
		modelHandle.setProperty( BAR_CODE, value );
	}
}