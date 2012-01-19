package org.eclipse.birt.report.extension.rectangle;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class RectangleActivator extends AbstractUIPlugin {
	private static RectangleActivator plugin;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin=this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin=null;
		super.stop(context);
	}

	public static RectangleActivator getDefault() {
		return plugin;
	}
}
