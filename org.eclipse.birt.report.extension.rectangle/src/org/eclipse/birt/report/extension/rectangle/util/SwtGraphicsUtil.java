package org.eclipse.birt.report.extension.rectangle.util;

import org.eclipse.birt.report.extension.common.ExtendedItemHandleUtil;
import org.eclipse.birt.report.extension.rectangle.RectangleItem;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.widgets.Display;

/**
 * SwtGraphicsUtil
 */
public class SwtGraphicsUtil {

	public static Image createRotatedTextImage(ExtendedItemHandle handle/* String text, int angle, Font ft*/, Dimension dimension)
			throws ExtendedElementException {
		//		Dimension dimension=ExtendedItemHandleUtil.getDimension(handle);
		//		if (dimension.width > 0) {
			Font ft=ExtendedItemHandleUtil.getFont(handle);
			RectangleItem item=(RectangleItem) handle.getReportItem();
			String text=item.getBarCode();
			int angle=10;
			GC gc=null;
			try {
				if (text == null || text.trim().length() == 0) {
					return null;
				}

				Display display=Display.getCurrent();

				gc=new GC(display);
				if (ft != null) {
					gc.setFont(ft);
				}

				Point pt=gc.textExtent(text);

				gc.dispose();

				TextLayout tl=new TextLayout(display);
				if (ft != null) {
					tl.setFont(ft);
				}
				tl.setWidth(dimension.width);
				tl.setAlignment(SWT.CENTER);
				tl.setText(text);

				Image dest=null;
				GC gc1=null;

				try {
					dest=new Image(Display.getCurrent(), dimension.width, dimension.height);
					gc1=new GC(dest);

					gc1.fillRoundRectangle(0, 0, dimension.width, dimension.height, angle, angle);
					gc1.drawRoundRectangle(0, 0, dimension.width - 1, dimension.height - 1, angle, angle);

					tl.draw(gc1, 0, (dimension.height - pt.y) / 2);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (gc1 != null && !gc1.isDisposed()) {
						gc1.dispose();
					}
				}

				return dest;
			} catch (Exception e) {
				e.printStackTrace();

				if (gc != null && !gc.isDisposed()) {
					gc.dispose();
				}
			}
		//		}

		return null;
	}
}