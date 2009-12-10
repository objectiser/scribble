package org.scribble.command.osgi;

import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.scribble.command.parse.ParseCommand;
import org.scribble.command.validate.ValidateCommand;
import org.scribble.command.*;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) {
        Properties props = new Properties();

        context.registerService(Command.class.getName(), 
				new ParseCommand(), props);
        
        ValidateCommand vc=new ValidateCommand();
        
        // Find ValidationManager
        //context.
        
        context.registerService(Command.class.getName(), 
				vc, props);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) {
 	}

}
