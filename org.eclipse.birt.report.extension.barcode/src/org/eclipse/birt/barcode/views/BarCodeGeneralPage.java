/*******************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.barcode.views;

import java.util.List;

import org.eclipse.birt.barcode.BarCodeItem;
import org.eclipse.birt.report.designer.ui.dialogs.ExpressionBuilder;
import org.eclipse.birt.report.designer.ui.dialogs.ExpressionProvider;
import org.eclipse.birt.report.designer.ui.views.attributes.AttributesUtil;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * RotatedTextGeneralPage
 */
public class BarCodeGeneralPage extends AttributesUtil.PageWrapper
{

	protected FormToolkit toolkit;
	protected Object input;
	protected Composite contentpane;

	private Text txtText, txtAngle;

	@Override
	public void buildUI( Composite parent )
	{
		if ( toolkit == null )
		{
			toolkit = new FormToolkit( Display.getCurrent( ) );
			toolkit.setBorderStyle( SWT.NULL );
		}

		Control[] children = parent.getChildren( );

		if ( children != null && children.length > 0 )
		{
			contentpane = (Composite) children[children.length - 1];

			GridLayout layout = new GridLayout( 3, false );
			layout.marginLeft = 8;
			layout.verticalSpacing = 12;
			contentpane.setLayout( layout );

			toolkit.createLabel( contentpane, "Text Content:" ); //$NON-NLS-1$
			txtText = toolkit.createText( contentpane, "" ); //$NON-NLS-1$
			GridData gd = new GridData( );
			gd.widthHint = 200;

			// XXX comment for expression support
			// gd.horizontalSpan = 2;

			txtText.setLayoutData( gd );
			txtText.addFocusListener( new FocusAdapter( ) {

				@Override
				public void focusLost( org.eclipse.swt.events.FocusEvent e )
				{
					updateModel( BarCodeItem.BAR_CODE );
				};
			} );

			// XXX uncomment this block for expression support
			// /*
			Button btnExp = toolkit.createButton( contentpane, "...", SWT.PUSH ); //$NON-NLS-1$
			btnExp.setToolTipText( "Invoke Expression Builder" ); //$NON-NLS-1$
			btnExp.addSelectionListener( new SelectionAdapter( ) {

				@Override
				public void widgetSelected( SelectionEvent e )
				{
					openExpression( txtText );
				}
			} );
			// */

			toolkit.createLabel( contentpane, "Rotation Angle:" ); //$NON-NLS-1$
			txtAngle = toolkit.createText( contentpane, "" ); //$NON-NLS-1$
			gd = new GridData( );
			gd.widthHint = 200;
			gd.horizontalSpan = 2;
			txtAngle.setLayoutData( gd );
			txtAngle.addFocusListener( new FocusAdapter( ) {

				@Override
				public void focusLost( org.eclipse.swt.events.FocusEvent e )
				{
					updateModel( BarCodeItem.BAR_CODE_TYPE_PROP );
				};
			} );

		}
	}

	private void openExpression( Text textControl )
	{
		BarCodeItem item = getItem( );

		if ( item != null )
		{
			String oldValue = textControl.getText( );

			ExpressionBuilder eb = new ExpressionBuilder( textControl.getShell( ),
					oldValue );
			eb.setExpressionProvier( new ExpressionProvider( item.getModelHandle( ) ) );

			String result = oldValue;

			if ( eb.open( ) == Window.OK )
			{
				result = eb.getResult( );
			}

			if ( !oldValue.equals( result ) )
			{
				textControl.setText( result );

				updateModel( BarCodeItem.BAR_CODE );
			}
		}
	}

	@Override
	public void setInput( Object input )
	{
		this.input = input;
	}

	@Override
	public void dispose( )
	{
		if ( toolkit != null )
		{
			toolkit.dispose( );
		}
	}

	private void adaptFormStyle( Composite comp )
	{
		Control[] children = comp.getChildren( );
		for ( int i = 0; i < children.length; i++ )
		{
			if ( children[i] instanceof Composite )
			{
				adaptFormStyle( (Composite) children[i] );
			}
		}

		toolkit.paintBordersFor( comp );
		toolkit.adapt( comp );
	}

	protected BarCodeItem getItem( )
	{
		Object element = input;

		if ( input instanceof List && ( (List) input ).size( ) > 0 )
		{
			element = ( (List) input ).get( 0 );
		}

		if ( element instanceof ExtendedItemHandle )
		{
			try
			{
				return (BarCodeItem) ( (ExtendedItemHandle) element ).getReportItem( );
			}
			catch ( Exception e )
			{
				e.printStackTrace( );
			}
		}

		return null;
	}

	@Override
	public void refresh( )
	{
		if ( contentpane != null && !contentpane.isDisposed( ) )
		{
			if ( toolkit == null )
			{
				toolkit = new FormToolkit( Display.getCurrent( ) );
				toolkit.setBorderStyle( SWT.NULL );
			}

			adaptFormStyle( contentpane );

			updateUI( );
		}
	}

	@Override
	public void postElementEvent( )
	{
		if ( contentpane != null && !contentpane.isDisposed( ) )
		{
			updateUI( );
		}
	}

	private void updateModel( String prop )
	{
		BarCodeItem item = getItem( );

		if ( item != null )
		{
			try
			{
				if ( BarCodeItem.BAR_CODE_TYPE_PROP.equals( prop ) )
				{
					item.setBarCodeType( txtAngle.getText( ) );
				}
				else if ( BarCodeItem.BAR_CODE.equals( prop ) )
				{
					item.setBarCode( txtText.getText( ) );
				}
			}
			catch ( Exception e )
			{
				e.printStackTrace( );
			}
		}
	}

	protected void updateUI( )
	{
		BarCodeItem item = getItem( );

		if ( item != null )
		{
			String text = item.getBarCode();
			txtText.setText( text == null ? "" : text ); //$NON-NLS-1$

			txtAngle.setText("1234567890");
		}
	}
}