package org.eclipse.birt.report.extension.rectangle.util;

import org.eclipse.birt.report.designer.util.ColorManager;
import org.eclipse.birt.report.designer.util.DEUtil;
import org.eclipse.birt.report.extension.common.ExtendedItemHandleUtil;
import org.eclipse.birt.report.extension.rectangle.RectangleItem;
import org.eclipse.birt.report.model.api.ColorHandle;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
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

	public static Image createRoundedRectangleImage(ExtendedItemHandle handle, Dimension dimension)
			throws ExtendedElementException {

		Font ft=ExtendedItemHandleUtil.getFont(handle);
		RectangleItem item=(RectangleItem) handle.getReportItem();
		String text=item.getText();
		int arcSize=item.getArcSize();
		GC gc=null;
		try {
			StyleHandle style=handle.getPrivateStyle();

			Display display=Display.getCurrent();

			gc=new GC(display);

			Image dest=null;
			GC gc1=null;

			try {
				dest=new Image(Display.getCurrent(), dimension.width, dimension.height);
				gc1=new GC(dest);

				gc1.setBackground(getColor(style.getBackgroundColor(), display.getSystemColor(SWT.COLOR_WHITE)));
				gc1.fillRoundRectangle(0, 0, dimension.width, dimension.height, arcSize, arcSize);
				if (!DesignChoiceConstants.LINE_STYLE_NONE.equals(style.getBorderTopStyle())) {
					gc1.setForeground(getColor(style.getBorderTopColor(), display.getSystemColor(SWT.COLOR_BLACK)));
					gc1.setLineWidth((int) DEUtil.convertoToPixel(style.getBorderTopWidth()));
					gc1.setLineStyle(toSwtStyle(style.getBorderTopStyle()));
					gc1.drawRoundRectangle(0, 0, dimension.width - 1, dimension.height - 1, arcSize, arcSize);
				}

				if (text != null && text.trim().length() > 0) {
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
					gc1.setForeground(getColor(style.getColor(), display.getSystemColor(SWT.COLOR_BLACK)));
					tl.draw(gc1, 0, (dimension.height - pt.y) / 2);
				}

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
		return null;
	}

	protected static Color getColor(ColorHandle handle, Color defaultColor) {
		Color color=ColorManager.getColor(handle.getRGB());
		return color == null ? defaultColor : color;
	}

	private static int toSwtStyle(String borderTopStyle) {
		//		SWT.LINE_SOLID, SWT.LINE_DASH, SWT.LINE_DOT, SWT.LINE_DASHDOT or SWT.LINE_DASHDOTDOT
		if (DesignChoiceConstants.LINE_STYLE_SOLID.equals(borderTopStyle))
			return SWT.LINE_SOLID;
		if (DesignChoiceConstants.LINE_STYLE_DASHED.equals(borderTopStyle))
			return SWT.LINE_DASH;
		if (DesignChoiceConstants.LINE_STYLE_DOTTED.equals(borderTopStyle))
			return SWT.LINE_DOT;
		//		if(DesignChoiceConstants.LINE_STYLE_SOLID.equals(borderTopStyle))
		//			return SWT.LINE_DASHDOT;
		//		if(DesignChoiceConstants.LINE_STYLE_SOLID.equals(borderTopStyle))
		//			return SWT.LINE_DASHDOTDOT;

		return SWT.LINE_SOLID;
	}
}