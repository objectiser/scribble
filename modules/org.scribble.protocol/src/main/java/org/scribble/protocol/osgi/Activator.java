package org.scribble.protocol.osgi;

import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.scribble.core.validation.Validator;
import org.scribble.protocol.validator.ProtocolValidator;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		
		// Register protocol validator
        Properties props = new Properties();

        context.registerService(Validator.class.getName(), 
				new ProtocolValidator(), props);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		
		//context.ungetService(arg0);
	}

}
