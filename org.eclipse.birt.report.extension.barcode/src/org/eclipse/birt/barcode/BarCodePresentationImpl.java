package org.eclipse.birt.barcode;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.eclipse.birt.barcode.util.SwingGraphicsUtil;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.extension.IRowSet;
import org.eclipse.birt.report.engine.extension.ReportItemPresentationBase;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;

public class BarCodePresentationImpl extends ReportItemPresentationBase
{
	private BarCodeItem barCodeItem;
	
	@Override
	public void setModelObject( ExtendedItemHandle modelHandle )
	{
		try
		{
			barCodeItem = (BarCodeItem) modelHandle.getReportItem( );
		}
		catch ( ExtendedElementException e )
		{
			e.printStackTrace( );
		}
	}
	
	@Override
	public int getOutputType( )
	{
		return OUTPUT_AS_IMAGE;
	}
	
	@Override
	public Object onRowSets( IRowSet[] rowSets ) throws BirtException

	{
		if ( barCodeItem == null )
		{
			return null;
		}
		
		//TODO: Generate BarCode
		String type = barCodeItem.getBarCodeType();
		String barCode = barCodeItem.getBarCode( );
		BufferedImage rotatedImage = SwingGraphicsUtil.createRotatedTextImage( barCode, 0, new Font( "Default", 0, 12 ) ); //$NON-NLS-1$
		ByteArrayInputStream bis = null;
		
		try
		{
			ImageIO.setUseCache( false );
			ByteArrayOutputStream baos = new ByteArrayOutputStream( );
			ImageOutputStream ios = ImageIO.createImageOutputStream( baos );
			ImageIO.write( rotatedImage, "png", ios ); //$NON-NLS-1$
			ios.flush( );
			ios.close( );
			bis = new ByteArrayInputStream( baos.toByteArray( ) );
		}
		catch ( IOException e )
		{
			e.printStackTrace( );
		}
		return new ByteArrayInputStream(new byte[1]);
	}
}
