package org.eclipse.birt.report.extension.barcode;

import org.eclipse.birt.report.extension.barcode.util.BarCodeGenerator;
import org.eclipse.birt.report.extension.common.CommonItem;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;

public class BarCodeItem extends CommonItem
{
	public static final String EXTENSION_NAME=getExtensionName(BarCodeActivator.getDefault().getBundle()); //$NON-NLS-1$
	public static final String BAR_CODE_TYPE_PROP = "barCodeType"; //$NON-NLS-1$
	public static final String BAR_CODE_PROP = "barCode"; //$NON-NLS-1$

	public BarCodeItem( ExtendedItemHandle modelHandle ) {
		super(modelHandle);
	}

	public BarCodeGenerator getBarCodeType()
	{
		return BarCodeGenerator.valueOf(modelHandle.getStringProperty(BAR_CODE_TYPE_PROP));
	}

	public void setBarCodeType(BarCodeGenerator value) throws SemanticException
	{
		modelHandle.setProperty(BAR_CODE_TYPE_PROP, value.name());
	}

	public String getBarCode()
	{
		return modelHandle.getStringProperty( BAR_CODE_PROP );
	}

	public void setBarCode( String value ) throws SemanticException
	{
		modelHandle.setProperty( BAR_CODE_PROP, value );
	}
}