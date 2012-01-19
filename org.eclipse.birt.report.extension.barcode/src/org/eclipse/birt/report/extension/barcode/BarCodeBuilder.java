/*******************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.report.extension.barcode;

import org.eclipse.birt.report.designer.ui.dialogs.ExpressionBuilder;
import org.eclipse.birt.report.designer.ui.dialogs.ExpressionProvider;
import org.eclipse.birt.report.designer.ui.extensions.ReportItemBuilderUI;
import org.eclipse.birt.report.extension.barcode.util.BarCodeGenerator;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.IReportItem;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * RotatedTextBuilder
 */
public class BarCodeBuilder extends ReportItemBuilderUI {

	@Override
	public int open(ExtendedItemHandle handle) {
		try {
			IReportItem item=handle.getReportItem();

			if (item instanceof BarCodeItem) {
				// XXX change to RotatedTextEditor2 for expression support
				BarCodeEditor editor=new BarCodeEditor2(Display.getCurrent().getActiveShell(), (BarCodeItem) item);

				return editor.open();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Window.CANCEL;
	}
}

/**
 * RotatedTextEditor
 */
class BarCodeEditor extends TrayDialog {

	protected BarCodeItem barCodeItem;

	protected Text barCode;
	protected ComboViewer barCodeType;

	//	private static final List<String> BAR_CODE_TYPES=Arrays.asList(BarCodeGenerator.INTERLEAVED, BarCodeGenerator.ITF_14, BarCodeGenerator.CODABAR,
	//			BarCodeGenerator.CODE_39, BarCodeGenerator.CODE_128, BarCodeGenerator.UPC_A, BarCodeGenerator.UPC_E, BarCodeGenerator.EAN_13,
	//			BarCodeGenerator.EAN_8, BarCodeGenerator.POSTNET, BarCodeGenerator.DATAMATRIX_SQUARE, BarCodeGenerator.DATAMATRIX_RECTANGULAR);

	protected BarCodeEditor(Shell shell, BarCodeItem barCodeItem) {
		super(shell);

		this.barCodeItem=barCodeItem;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);

		newShell.setText("Barcode Builder"); //$NON-NLS-1$
	}

	protected void createTextArea(Composite parent) {
		Label lb=new Label(parent, SWT.None);
		lb.setText("Barcode:"); //$NON-NLS-1$

		barCode=new Text(parent, SWT.BORDER);
		GridData gd=new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan=2;
		barCode.setLayoutData(gd);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite=new Composite(parent, SWT.NONE);
		GridLayout layout=new GridLayout(3, false);
		layout.marginHeight=convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth=convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.verticalSpacing=convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.horizontalSpacing=convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		createTextArea(composite);

		Label lb=new Label(composite, SWT.None);
		lb.setText("Barcode Type:"); //$NON-NLS-1$

		barCodeType=new ComboViewer(composite, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		//		barCodeType.add("");
		//		for (String bcType : BAR_CODE_TYPES) {
		//			barCodeType.add(bcType);
		//		}
		barCodeType.setContentProvider(ArrayContentProvider.getInstance());
		barCodeType.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((BarCodeGenerator) element).getLabel();
			}
		});
		barCodeType.setInput(BarCodeGenerator.values());
		GridData gd=new GridData();
		gd.widthHint=100;
		barCodeType.getControl().setLayoutData(gd);

		applyDialogFont(composite);

		initValues();

		return composite;
	}

	private void initValues() {
		barCode.setText(barCodeItem.getBarCode());
		barCodeType.setSelection(new StructuredSelection(BarCodeGenerator.CODABAR));//barCodeItem.getBarCodeType() != null ? barCodeItem.getBarCodeType() : "");
	}

	@Override
	protected void okPressed() {

		try {
			barCodeItem.setBarCode(barCode.getText());
			barCodeItem.setBarCodeType((BarCodeGenerator) ((IStructuredSelection) barCodeType.getSelection()).getFirstElement());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		super.okPressed();
	}
}

/**
 * RotatedTextEditor2
 */
class BarCodeEditor2 extends BarCodeEditor {

	protected BarCodeEditor2(Shell shell, BarCodeItem barCodeItem) {
		super(shell, barCodeItem);
	}

	@Override
	protected void createTextArea(Composite parent) {
		Label lb=new Label(parent, SWT.None);
		lb.setText("Barcode:"); //$NON-NLS-1$

		barCode=new Text(parent, SWT.BORDER);
		GridData gd=new GridData(GridData.FILL_HORIZONTAL);
		barCode.setLayoutData(gd);

		Button btnExp=new Button(parent, SWT.PUSH);
		btnExp.setText("..."); //$NON-NLS-1$
		btnExp.setToolTipText("Invoke Expression Builder"); //$NON-NLS-1$

		btnExp.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent event) {
				openExpression(barCode);
			}
		});

	}

	private void openExpression(Text textControl) {
		String oldValue=textControl.getText();

		ExpressionBuilder eb=new ExpressionBuilder(textControl.getShell(), oldValue);
		eb.setExpressionProvier(new ExpressionProvider(barCodeItem.getModelHandle()));

		String result=oldValue;

		if (eb.open() == Window.OK) {
			result=eb.getResult();
		}

		if (!oldValue.equals(result)) {
			textControl.setText(result);
		}
	}

}
