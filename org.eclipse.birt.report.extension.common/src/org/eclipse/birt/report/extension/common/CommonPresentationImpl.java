package org.eclipse.birt.report.extension.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.extension.IRowSet;
import org.eclipse.birt.report.engine.extension.ReportItemPresentationBase;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

public abstract class CommonPresentationImpl extends ReportItemPresentationBase {
	private CommonItem commonItem;
	private ExtendedItemHandle modelHandle;

	@Override
	public void setModelObject( ExtendedItemHandle modelHandle )
	{
		this.modelHandle=modelHandle;
		try
		{
			commonItem=(CommonItem) modelHandle.getReportItem();
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
	public Object onRowSets(IRowSet[] rowSets) throws BirtException {
		if (commonItem == null)
			return null;

		ImageLoader imageLoader=new ImageLoader();
		imageLoader.data=new ImageData[] { createImage(modelHandle).getImageData() };
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		imageLoader.save(baos, SWT.IMAGE_PNG);
		return new ByteArrayInputStream(baos.toByteArray());
	}

	protected abstract Image createImage(ExtendedItemHandle item) throws ExtendedElementException;
}
