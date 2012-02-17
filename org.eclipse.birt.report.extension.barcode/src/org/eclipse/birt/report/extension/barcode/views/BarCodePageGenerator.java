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

import org.eclipse.birt.report.designer.ui.views.attributes.AttributesUtil.PageWrapper;
import org.eclipse.birt.report.extension.common.views.CommonPageGenerator;

/**
 * RotatedTextPageGenerator
 */
public class BarCodePageGenerator extends CommonPageGenerator
{

	@Override
	protected PageWrapper createGeneralPage() {
		return new BarCodeGeneralPage();
	}

}
