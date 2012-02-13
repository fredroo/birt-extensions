package org.eclipse.birt.report.extension.common.views;

import org.eclipse.birt.report.designer.ui.views.IPageGenerator;
import org.eclipse.core.runtime.IAdapterFactory;

public abstract class CommonPageGeneratorFactory implements IAdapterFactory {
	@Override
	public abstract Object getAdapter(Object adaptableObject, Class adapterType);

	@Override
	public Class[] getAdapterList( )
	{
		return new Class[]{
			IPageGenerator.class
		};
	}
}
