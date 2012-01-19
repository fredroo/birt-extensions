package org.eclipse.birt.report.extension.barcode;

import org.eclipse.birt.report.extension.barcode.util.BarCodeGenerator;
import org.eclipse.birt.report.extension.common.CommonPresentationImpl;
import org.eclipse.birt.report.extension.common.ExtendedItemHandleUtil;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.swt.graphics.Image;

public class BarCodePresentationImpl extends CommonPresentationImpl {
	//	private BarCodeItem barCodeItem;

	//	private final BarCodeGenerator barCodeGenerator=new BarCodeGenerator();

	//	@Override
	//	public void setModelObject(ExtendedItemHandle modelHandle) {
	//		try {
	//			barCodeItem=(BarCodeItem) modelHandle.getReportItem();
	//		} catch (ExtendedElementException e) {
	//			e.printStackTrace();
	//		}
	//	}
	//
	//	@Override
	//	public int getOutputType() {
	//		return OUTPUT_AS_IMAGE;
	//	}
	//
	//	@Override
	//	public Object onRowSets(IRowSet[] rowSets) throws BirtException
	//
	//	{
	//		if (barCodeItem == null) {
	//			return null;
	//		}
	//
	//		BarCodeGenerator type=barCodeItem.getBarCodeType();
	//		String barCode=barCodeItem.getBarCode();
	//		ByteArrayInputStream bis=null;
	//		try {
	//			ImageIO.setUseCache(false);
	//			bis=type.generateBarCodeByteArray(barCode, 30);
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//		return bis;
	//	}

	@Override
	protected Image createImage(ExtendedItemHandle item) throws ExtendedElementException {
		BarCodeGenerator type=((BarCodeItem) item.getReportItem()).getBarCodeType();
		return type.generateBarCodeByteArray(item, ExtendedItemHandleUtil.getDimension(item));
	}
}
