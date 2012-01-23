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

package org.eclipse.birt.report.extension.rectangle.views;

import org.eclipse.birt.report.extension.common.views.CommonGeneralPage;
import org.eclipse.birt.report.extension.rectangle.RectangleItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * RotatedTextGeneralPage
 */
public class RectangleGeneralPage extends CommonGeneralPage
{
	private Text txtText;
	private Text txtAngle;

	@Override
	protected void createControl(Composite parent) {
		txtText=addExpressionField(parent, "Text Content:", RectangleItem.TEXT_PROP);
		txtAngle=addStringField(parent, "Rotation Angle:", RectangleItem.ARC_SIZE_PROP);
	}

	@Override
	protected void updateModel(String prop)
	{
		RectangleItem item=(RectangleItem) getItem();

		if ( item != null )
		{
			try
			{
				if (RectangleItem.ARC_SIZE_PROP.equals(prop)) {
					item.setArcSize(Integer.parseInt(txtAngle.getText()));
				} else if (RectangleItem.TEXT_PROP.equals(prop)) {
					item.setText(txtText.getText());
				}
			}
			catch ( Exception e )
			{
				e.printStackTrace( );
			}
		}
	}

	@Override
	protected void updateUI( )
	{
		RectangleItem item=(RectangleItem) getItem();

		if ( item != null )
		{
			String text = item.getText();
			txtText.setText( text == null ? "" : text ); //$NON-NLS-1$
			txtAngle.setText(Integer.toString(item.getArcSize()));
		}
	}
}
