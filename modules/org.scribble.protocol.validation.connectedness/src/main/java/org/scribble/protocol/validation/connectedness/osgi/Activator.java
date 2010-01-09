package org.scribble.protocol.validation.connectedness.osgi;

import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.scribble.protocol.validation.ProtocolValidator;
import org.scribble.protocol.validation.connectedness.ConnectednessValidator;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
        Properties props = new Properties();
        
        ProtocolValidator validator=new ConnectednessValidator();
        
        context.registerService(ProtocolValidator.class.getName(), 
				validator, props);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
	}

}
