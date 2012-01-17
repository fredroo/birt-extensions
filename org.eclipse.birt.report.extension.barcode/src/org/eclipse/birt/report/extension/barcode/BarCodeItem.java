package org.eclipse.birt.report.extension.barcode;

import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.extension.ReportItem;

public class BarCodeItem extends ReportItem
{
	public static final String EXTENSION_NAME = "BarCode"; //$NON-NLS-1$
	public static final String BAR_CODE_TYPE_PROP = "barCodeType"; //$NON-NLS-1$
	public static final String BAR_CODE = "barCode"; //$NON-NLS-1$
	
	private final ExtendedItemHandle modelHandle;

	public BarCodeItem( ExtendedItemHandle modelHandle ) {
		this.modelHandle = modelHandle;
	}

	public ExtendedItemHandle getModelHandle( )
	{
		return modelHandle;
	}
	
	public String getBarCodeType()
	{
		return modelHandle.getStringProperty( BAR_CODE_TYPE_PROP );
	}

	public void setBarCodeType( String value ) throws SemanticException
	{
		modelHandle.setProperty( BAR_CODE_TYPE_PROP, value );
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