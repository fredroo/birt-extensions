package org.eclipse.birt.report.extension.barcode;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.extension.IQueryResultSet;
import org.eclipse.birt.report.engine.extension.IRowSet;
import org.eclipse.birt.report.extension.barcode.util.BarCodeGenerator;
import org.eclipse.birt.report.extension.common.CommonItem;
import org.eclipse.birt.report.extension.common.CommonPresentationImpl;
import org.eclipse.birt.report.extension.common.ExtendedItemHandleUtil;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.swt.graphics.Image;

public class BarCodePresentationImpl extends CommonPresentationImpl {
//		private BarCodeItem barCodeItem;

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
//		if ( rowSets != null && rowSets.length > 0 )
//		{
//			if ( rowSets[0] instanceof IQueryResultSet && ( (IQueryResultSet) rowSets[0] ).isBeforeFirst( ) )
//			{
//				( (IQueryResultSet) rowSets[0] ).next( );
//			}
//			barCode = String.valueOf( rowSets[0].evaluate( barCode ) );
//		}
//		else
//		{
//			barCode = String.valueOf( context.evaluate( barCode ) );
//		}
//		return null;
//	}

	@Override
	protected Image createImage(ExtendedItemHandle item) throws ExtendedElementException {
		BarCodeGenerator type=((BarCodeItem) item.getReportItem()).getBarCodeType();
		return type.generateBarCodeByteArray(item, ExtendedItemHandleUtil.getDimension(item));
	}

	@Override
	protected void evalueateExpression(CommonItem commonItem, IRowSet[] rowSets) throws BirtException {
		if (commonItem instanceof BarCodeItem) {
			String barCode = ((BarCodeItem) commonItem).getBarCode();
			if ( rowSets != null && rowSets.length > 0 )
			{
				if ( rowSets[0] instanceof IQueryResultSet && ( (IQueryResultSet) rowSets[0] ).isBeforeFirst( ) )
				{
					( (IQueryResultSet) rowSets[0] ).next( );
				}
				barCode = String.valueOf( rowSets[0].evaluate( barCode ) );
			}
			else
			{
				barCode = String.valueOf( context.evaluate( barCode ) );
			}
			((BarCodeItem) commonItem).setBarCode(barCode);
		}
		
	}
	
	
}
