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

package org.eclipse.birt.report.extension.rectangle;

import org.eclipse.birt.report.extension.common.CommonFigure;
import org.eclipse.birt.report.extension.common.CommonFigureUI;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;

public class RectangleFigureUI extends CommonFigureUI
{
	@Override
	protected CommonFigure handle(ExtendedItemHandle handle) throws ExtendedElementException {
		return new RectangleFigure(handle);
	}
}
