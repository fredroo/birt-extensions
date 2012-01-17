package org.eclipse.birt.report.extension.barcode;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.extension.IRowSet;
import org.eclipse.birt.report.engine.extension.ReportItemPresentationBase;
import org.eclipse.birt.report.extension.barcode.util.BarCodeGenerator;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;

public class BarCodePresentationImpl extends ReportItemPresentationBase {
	private BarCodeItem barCodeItem;
	private final BarCodeGenerator barCodeGenerator=new BarCodeGenerator();

	@Override
	public void setModelObject(ExtendedItemHandle modelHandle) {
		try {
			barCodeItem=(BarCodeItem) modelHandle.getReportItem();
		} catch (ExtendedElementException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getOutputType() {
		return OUTPUT_AS_IMAGE;
	}

	@Override
	public Object onRowSets(IRowSet[] rowSets) throws BirtException

	{
		if (barCodeItem == null) {
			return null;
		}

		String type=barCodeItem.getBarCodeType();
		String barCode=barCodeItem.getBarCode();
		ByteArrayInputStream bis=null;
		try {
			ImageIO.setUseCache(false);
			bis=barCodeGenerator.generateBarCodeByteArray(type, barCode, 30);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bis;
	}
}
