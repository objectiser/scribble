/*
 * Copyright 2009 Scribble.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.scribble.command.osgi;

import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import org.scribble.command.parse.ParseCommand;
import org.scribble.command.validate.ValidateCommand;
import org.scribble.command.*;
import org.scribble.core.logger.ScribbleLogger;
import org.scribble.core.validation.ValidationManager;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(final BundleContext context) {
		Properties props = new Properties();

		// Register parse command
		final ParseCommand pc=new ParseCommand();
		
        context.registerService(Command.class.getName(), 
				pc, props);
        
        // Register validate command
        final ValidateCommand vc=new ValidateCommand();
        
        context.registerService(Command.class.getName(), 
				vc, props);

        // Register service listeners to establish dependent
        // components
        ServiceListener sl1 = new ServiceListener() {
        	public void serviceChanged(ServiceEvent ev) {
        		ServiceReference sr = ev.getServiceReference();
        		switch(ev.getType()) {
        		case ServiceEvent.REGISTERED:
        			ValidationManager vm=
        				(ValidationManager)context.getService(sr);
        			vc.setValidationManager(vm);
        			break;
        		case ServiceEvent.UNREGISTERING:
        			break;
        		}
        	}
        };
              
        ServiceListener sl2 = new ServiceListener() {
        	public void serviceChanged(ServiceEvent ev) {
        		ServiceReference sr = ev.getServiceReference();
        		switch(ev.getType()) {
        		case ServiceEvent.REGISTERED:
        			ScribbleLogger sl=
        				(ScribbleLogger)context.getService(sr);
        			pc.setLogger(sl);
        			vc.setLogger(sl);
        			break;
        		case ServiceEvent.UNREGISTERING:
        			break;
        		}
        	}
        };
              
        String filter1 = "(objectclass=" + ValidationManager.class.getName() + ")";
        String filter2 = "(objectclass=" + ScribbleLogger.class.getName() + ")";
        
        try {
        	context.addServiceListener(sl1, filter1);
        	context.addServiceListener(sl2, filter2);
        } catch(Exception e) {
        	e.printStackTrace();
        }
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) {
 	}

}
