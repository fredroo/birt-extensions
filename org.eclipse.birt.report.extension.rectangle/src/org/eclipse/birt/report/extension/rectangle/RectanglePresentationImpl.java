package org.eclipse.birt.report.extension.rectangle;

import org.eclipse.birt.report.extension.common.CommonPresentationImpl;
import org.eclipse.birt.report.extension.common.ExtendedItemHandleUtil;
import org.eclipse.birt.report.extension.rectangle.util.SwtGraphicsUtil;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.swt.graphics.Image;

public class RectanglePresentationImpl extends CommonPresentationImpl
{
	@Override
	protected Image createImage(ExtendedItemHandle item) throws ExtendedElementException {
		return SwtGraphicsUtil.createRoundedRectangleImage(item, ExtendedItemHandleUtil.getDimension(item));
	}
}
