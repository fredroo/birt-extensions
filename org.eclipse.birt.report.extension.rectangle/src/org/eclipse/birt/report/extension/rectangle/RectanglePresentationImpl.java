package org.eclipse.birt.report.extension.rectangle;

import org.eclipse.birt.report.extension.common.CommonPresentationImpl;
import org.eclipse.birt.report.extension.common.ExtendedItemHandleUtil;
import org.eclipse.birt.report.extension.rectangle.util.SwtGraphicsUtil;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.swt.graphics.Image;

public class RectanglePresentationImpl extends CommonPresentationImpl
{
	//	private RectangleItem barCodeItem;
	//
	//	@Override
	//	public void setModelObject( ExtendedItemHandle modelHandle )
	//	{
	//		try
	//		{
	//			barCodeItem = (RectangleItem) modelHandle.getReportItem( );
	//		}
	//		catch ( ExtendedElementException e )
	//		{
	//			e.printStackTrace( );
	//		}
	//	}
	//
	//	@Override
	//	public int getOutputType( )
	//	{
	//		return OUTPUT_AS_IMAGE;
	//	}
	//
	//	@Override
	//	public Object onRowSets( IRowSet[] rowSets ) throws BirtException
	//
	//	{
	//		if ( barCodeItem == null )
	//		{
	//			return null;
	//		}
	//
	//		//TODO: Generate BarCode
	//		// String type = barCodeItem.getBarCodeType();
	//		// String barCode = barCodeItem.getBarCode( );
	//		//		BufferedImage rotatedImage = SwingGraphicsUtil.createRotatedTextImage( barCode, 0, new Font( "Default", 0, 12 ) ); //$NON-NLS-1$
	//		// ByteArrayInputStream bis = null;
	//
	//		// try
	//		// {
	//		// ImageIO.setUseCache( false );
	//		// ByteArrayOutputStream baos = new ByteArrayOutputStream( );
	//		// ImageOutputStream ios = ImageIO.createImageOutputStream( baos );
	//		//			ImageIO.write( rotatedImage, "png", ios ); //$NON-NLS-1$
	//		// ios.flush( );
	//		// ios.close( );
	//		// bis = new ByteArrayInputStream( baos.toByteArray( ) );
	//		// }
	//		// catch ( IOException e )
	//		// {
	//		// e.printStackTrace( );
	//		// }
	//		ImageLoader imageLoader=new ImageLoader();
	//		//		GC gc=new GC(Display.getCurrent());
	//		//		gc.
	//		imageLoader.data=new ImageData[] { SwtGraphicsUtil.createRotatedTextImage(barCodeItem.getBarCode(), 10, null, null).getImageData() };
	//		ByteArrayOutputStream baos=new ByteArrayOutputStream();
	//		imageLoader.save(baos, SWT.IMAGE_PNG);
	//		// gc.getsetClipping(region)
	//		// gc.drawRoundRectangle(x, y, width, height, arcWidth, arcHeight);
	//		// gc.copyArea(image, x, y)
	//		return new ByteArrayInputStream(baos.toByteArray());
	//	}
	@Override
	protected Image createImage(ExtendedItemHandle item) throws ExtendedElementException {
		return SwtGraphicsUtil.createRotatedTextImage(item, ExtendedItemHandleUtil.getDimension(item));
	}
}
