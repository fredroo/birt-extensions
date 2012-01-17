package org.eclipse.birt.report.extension.barcode.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.impl.codabar.CodabarBean;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.EAN128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.datamatrix.DataMatrixBean;
import org.krysalis.barcode4j.impl.int2of5.ITF14Bean;
import org.krysalis.barcode4j.impl.int2of5.Interleaved2Of5Bean;
import org.krysalis.barcode4j.impl.postnet.POSTNETBean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.impl.upcean.EAN8Bean;
import org.krysalis.barcode4j.impl.upcean.UPCABean;
import org.krysalis.barcode4j.impl.upcean.UPCEBean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class BarCodeGenerator {

	public static final String INTERLEAVED="Interleaved 2 of 5";
	public static final String ITF_14="ITF-14";
	public static final String CODE_39="Code 39";
	public static final String CODE_128="Code 128";
	public static final String CODABAR="Codabar";
	public static final String UPC_A="UPC-A";
	public static final String UPC_E="UPC-E";
	public static final String EAN_13="EAN-13";
	public static final String EAN_8="EAN-8";
	public static final String EAN_128="EAN-128";
	public static final String POSTNET="POSTNET";
	public static final String ROYAL_MAIL="Royal Mail Customer Barcode";
	public static final String USPS="USPS Intelligent Mail";
	public static final String PDF417="PDF417";
	public static final String DATAMATRIX_SQUARE="DataMatrix (square)";
	public static final String DATAMATRIX_RECTANGULAR="DataMatrix (rectangular)";

	public ByteArrayInputStream generateBarCodeByteArray(String barCodeType, String barCode, float componentHeight) throws IOException {
		final int dpi=200;
		boolean antiAlias=false;
		int orientation=0;

		AbstractBarcodeBean bean=generateBarCodeBean(barCodeType);
		// Configure the barcode generator
		bean.setModuleWidth(UnitConv.in2mm(8.0f / dpi)); // makes a dot/module exactly eight pixels
		bean.setBarHeight(UnitConv.in2mm(componentHeight / dpi));
		bean.doQuietZone(false);
		// Set up the canvas provider to create a monochrome bitmap
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		BitmapCanvasProvider canvas=new BitmapCanvasProvider(out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, antiAlias, orientation);
		bean.generateBarcode(canvas, barCode);
		canvas.finish();
		return new ByteArrayInputStream(out.toByteArray());
	}

	private AbstractBarcodeBean generateBarCodeBean(String barCodeType) {
		AbstractBarcodeBean bean=null;
		if (barCodeType.equals(INTERLEAVED)) {
			bean=new Interleaved2Of5Bean();
		} else if (barCodeType.equals(ITF_14)) {
			bean=new ITF14Bean();
		} else if (barCodeType.equals(CODABAR)) {
			bean=new CodabarBean();
		} else if (barCodeType.equals(CODE_39)) {
			bean=new Code39Bean();
		} else if (barCodeType.equals(CODE_128)) {
			bean=new Code128Bean();
		} else if (barCodeType.equals(UPC_A)) {
			bean=new UPCABean();
		} else if (barCodeType.equals(UPC_E)) {
			bean=new UPCEBean();
		} else if (barCodeType.equals(EAN_13)) {
			bean=new EAN13Bean();
		} else if (barCodeType.equals(EAN_8)) {
			bean=new EAN8Bean();
		} else if (barCodeType.equals(EAN_128)) {
			bean=new EAN128Bean();
		} else if (barCodeType.equals(POSTNET)) {
			bean=new POSTNETBean();
		} else if (barCodeType.equals(DATAMATRIX_SQUARE)) {
			bean=new DataMatrixBean();
		} else if (barCodeType.equals(DATAMATRIX_RECTANGULAR)) {
			bean=new DataMatrixBean();
		}
		return bean;
	}
}
