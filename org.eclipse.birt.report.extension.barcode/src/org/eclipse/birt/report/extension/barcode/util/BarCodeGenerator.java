package org.eclipse.birt.report.extension.barcode.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.birt.report.extension.barcode.BarCodeItem;
import org.eclipse.birt.report.extension.common.ExtendedItemHandleUtil;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.impl.codabar.CodabarBean;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.EAN128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.datamatrix.DataMatrixBean;
import org.krysalis.barcode4j.impl.datamatrix.SymbolShapeHint;
import org.krysalis.barcode4j.impl.int2of5.ITF14Bean;
import org.krysalis.barcode4j.impl.int2of5.Interleaved2Of5Bean;
import org.krysalis.barcode4j.impl.postnet.POSTNETBean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.impl.upcean.EAN8Bean;
import org.krysalis.barcode4j.impl.upcean.UPCABean;
import org.krysalis.barcode4j.impl.upcean.UPCEBean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public enum BarCodeGenerator {
	INTERLEAVED("Interleaved 2 of 5", Interleaved2Of5Bean.class), ITF_14("ITF-14", ITF14Bean.class), CODE_39("Code 39", Code39Bean.class), CODE_128("Code 128", Code128Bean.class),
	CODABAR("Codabar", CodabarBean.class), UPC_A("UPC-A", UPCABean.class), UPC_E(
			"UPC-E", UPCEBean.class), EAN_13(
			"EAN-13", EAN13Bean.class), EAN_8("EAN-8", EAN8Bean.class), EAN_128("EAN-128", EAN128Bean.class), POSTNET("POSTNET", POSTNETBean.class),
//			ROYAL_MAIL("Royal Mail Customer Barcode"), USPS("USPS Intelligent Mail"), PDF417("PDF417"),
	DATAMATRIX_SQUARE("DataMatrix (square)", DataMatrixBean.class) {
		@Override
		protected AbstractBarcodeBean generateBarCodeBean() {
			AbstractBarcodeBean result=super.generateBarCodeBean();
			((DataMatrixBean) result).setShape(SymbolShapeHint.FORCE_SQUARE);
			return result;
		}
	}
,
	DATAMATRIX_RECTANGULAR("DataMatrix (rectangular)", DataMatrixBean.class) {
		@Override
		protected AbstractBarcodeBean generateBarCodeBean() {
			AbstractBarcodeBean result=super.generateBarCodeBean();
			((DataMatrixBean) result).setShape(SymbolShapeHint.FORCE_RECTANGLE);
			return result;
		}
	};

	private static final int DPI=200;
	private static final boolean antiAlias=false;
	private static final int orientation=0;

	private final String label;
	private final Class<? extends AbstractBarcodeBean> clazz;

	private BarCodeGenerator(String label, Class<? extends AbstractBarcodeBean> clazz) {
		this.label=label;
		this.clazz=clazz;
	}

	public String getLabel() {
		return label;
	}

	public Image generateBarCodeByteArray(ExtendedItemHandle handle, Dimension dimension /* String barCode, float componentHeight */)
			throws ExtendedElementException {
		if (dimension.height > 0 && dimension.width > 0) {
			try {
				BarCodeItem item=(BarCodeItem) handle.getReportItem();
				Font font=ExtendedItemHandleUtil.getFont(handle);

				AbstractBarcodeBean bean=generateBarCodeBean();
				// Configure the barcode generator
				String barCode=item.getBarCode();
				double oldModuleWidth=bean.getModuleWidth();
				bean.setModuleWidth(UnitConv.in2mm(oldModuleWidth * dimension.width / bean.calcDimensions(barCode).getWidth() / DPI)); // makes a dot/module exactly eight pixels
				if (font.getFontData() != null && font.getFontData().length > 0) {
					bean.setFontName(font.getFontData()[0].getName());
					bean.setFontSize(UnitConv.in2mm(font.getFontData()[0].getHeight() * 2.0 / DPI));
				}
				bean.setHeight(UnitConv.in2mm(dimension.preciseHeight() / DPI));
				bean.doQuietZone(false);
				// Set up the canvas provider to create a monochrome bitmap
				ByteArrayOutputStream out=new ByteArrayOutputStream();
				BitmapCanvasProvider canvas=new BitmapCanvasProvider(out, "image/x-png", DPI, BufferedImage.TYPE_BYTE_BINARY, antiAlias, orientation);
				bean.generateBarcode(canvas, barCode);
				canvas.finish();
				return new Image(Display.getCurrent(), new ByteArrayInputStream(out.toByteArray()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	protected AbstractBarcodeBean generateBarCodeBean() {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
//		AbstractBarcodeBean bean=null;
//		if (barCodeType.equals(INTERLEAVED)) {
//			bean=new Interleaved2Of5Bean();
//		} else if (barCodeType.equals(ITF_14)) {
//			bean=new ITF14Bean();
//		} else if (barCodeType.equals(CODABAR)) {
//			bean=new CodabarBean();
//		} else if (barCodeType.equals(CODE_39)) {
//			bean=new Code39Bean();
//		} else if (barCodeType.equals(CODE_128)) {
//			bean=new Code128Bean();
//		} else if (barCodeType.equals(UPC_A)) {
//			bean=new UPCABean();
//		} else if (barCodeType.equals(UPC_E)) {
//			bean=new UPCEBean();
//		} else if (barCodeType.equals(EAN_13)) {
//			bean=new EAN13Bean();
//		} else if (barCodeType.equals(EAN_8)) {
//			bean=new EAN8Bean();
//		} else if (barCodeType.equals(EAN_128)) {
//			bean=new EAN128Bean();
//		} else if (barCodeType.equals(POSTNET)) {
//			bean=new POSTNETBean();
//		} else if (barCodeType.equals(DATAMATRIX_SQUARE)) {
//			bean=new DataMatrixBean().set;
//		} else if (barCodeType.equals(DATAMATRIX_RECTANGULAR)) {
//			bean=new DataMatrixBean();
//		}
//		return bean;
	}
}
