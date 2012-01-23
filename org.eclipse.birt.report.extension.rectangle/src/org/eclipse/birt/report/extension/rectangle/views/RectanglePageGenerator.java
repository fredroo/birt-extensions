package org.eclipse.birt.report.extension.rectangle.views;

import org.eclipse.birt.report.designer.ui.views.attributes.AttributesUtil.PageWrapper;
import org.eclipse.birt.report.extension.common.views.CommonPageGenerator;

public class RectanglePageGenerator extends CommonPageGenerator {
	@Override
	protected PageWrapper createGeneralPage() {
		return new RectangleGeneralPage();
	}
}
