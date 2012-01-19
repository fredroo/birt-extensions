package org.eclipse.birt.report.extension.common;

import org.eclipse.birt.report.designer.internal.ui.util.UIUtil;
import org.eclipse.birt.report.designer.util.DEUtil;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Font;

public class ExtendedItemHandleUtil {
	private ExtendedItemHandleUtil() {
	}

	public static Dimension getDimension(ExtendedItemHandle handle) {
		return new Dimension((int) DEUtil.convertoToPixel(handle.getWidth()), (int) DEUtil.convertoToPixel(handle.getHeight()));
	}

	public static Font getFont(ExtendedItemHandle handle) {
		return UIUtil.getFont(handle);
	}
}
