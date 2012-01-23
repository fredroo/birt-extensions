package org.eclipse.birt.report.extension.barcode.views;

import org.eclipse.birt.report.extension.common.views.CommonPageGeneratorFactory;

public class BarCodePageGeneratorFactory extends CommonPageGeneratorFactory {
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		return new BarCodePageGenerator();
	}
}
