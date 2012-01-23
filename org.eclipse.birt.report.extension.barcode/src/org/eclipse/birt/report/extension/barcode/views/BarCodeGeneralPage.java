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

package org.eclipse.birt.report.extension.barcode.views;

import org.eclipse.birt.report.extension.barcode.BarCodeItem;
import org.eclipse.birt.report.extension.barcode.util.BarCodeGenerator;
import org.eclipse.birt.report.extension.common.views.CommonGeneralPage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * RotatedTextGeneralPage
 */
public class BarCodeGeneralPage extends CommonGeneralPage
{
	private Text txtText;
	private ComboViewer barCodeType;

	@Override
	protected void createControl(Composite parent) {
		txtText=addExpressionField(parent, "Text Content:", BarCodeItem.BAR_CODE_PROP);
		barCodeType=addComboField(parent, "Bar Code Type:", BarCodeItem.BAR_CODE_TYPE_PROP);
	}

	private ComboViewer addComboField(Composite parent, String string, final String propName) {
		toolkit.createLabel(parent, string);
		ComboViewer viewer=new ComboViewer(parent, SWT.FLAT | SWT.READ_ONLY);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((BarCodeGenerator) element).getLabel();
			}
		});
		viewer.setInput(BarCodeGenerator.values());
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				updateModel(propName);
			}
		});
		toolkit.createLabel(parent, "");
		return viewer;
	}

	@Override
	protected void updateModel(String prop) {
		BarCodeItem item=(BarCodeItem) getItem();

		if (item != null) {
			try {
				if (BarCodeItem.BAR_CODE_TYPE_PROP.equals(prop)) {
					item.setBarCodeType((BarCodeGenerator) ((IStructuredSelection) barCodeType.getSelection()).getFirstElement());
				} else if (BarCodeItem.BAR_CODE_PROP.equals(prop)) {
					item.setBarCode(txtText.getText());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void updateUI() {
		BarCodeItem item=(BarCodeItem) getItem();

		if (item != null) {
			String text=item.getBarCode();
			txtText.setText(text == null ? "" : text); //$NON-NLS-1$
			barCodeType.setSelection(new StructuredSelection(item.getBarCodeType()));
		}
	}
}
