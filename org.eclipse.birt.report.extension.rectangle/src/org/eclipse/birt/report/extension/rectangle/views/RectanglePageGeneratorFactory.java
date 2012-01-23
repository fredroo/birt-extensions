package org.eclipse.birt.report.extension.rectangle.views;

import org.eclipse.birt.report.extension.common.views.CommonPageGeneratorFactory;

public class RectanglePageGeneratorFactory extends CommonPageGeneratorFactory //implements IAdapterFactory
{
	@Override
	public Object getAdapter( Object adaptableObject, Class adapterType )
	{
		return new RectanglePageGenerator( );
	}
}
