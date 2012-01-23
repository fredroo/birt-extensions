package org.eclipse.birt.report.extension.rectangle;

import org.eclipse.birt.report.extension.common.CommonItem;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;

public class RectangleItem extends CommonItem
{
	public static final String EXTENSION_NAME=getExtensionName(RectangleActivator.getDefault().getBundle());// = "BarCode"; //$NON-NLS-1$
	public static final String ARC_SIZE_PROP="arcSize"; //$NON-NLS-1$
	public static final String TEXT_PROP="textRect"; //$NON-NLS-1$

	public RectangleItem( ExtendedItemHandle modelHandle ) {
		super(modelHandle);
	}

	public int getArcSize() {
		return modelHandle.getIntProperty(ARC_SIZE_PROP);
	}

	public void setArcSize(int value) throws SemanticException {
		modelHandle.setProperty(ARC_SIZE_PROP, value);
	}

	public String getText()
	{
		return modelHandle.getStringProperty( TEXT_PROP );
	}

	public void setText(String value) throws SemanticException
	{
		modelHandle.setProperty( TEXT_PROP, value );
	}
}