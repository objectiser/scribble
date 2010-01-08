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
package org.scribble.protocol.osgi;

import java.util.Properties;
import java.util.logging.Logger;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.scribble.core.logger.ScribbleLogger;
import org.scribble.core.logger.ConsoleScribbleLogger;
import org.scribble.protocol.validation.DefaultValidationManager;
import org.scribble.protocol.validation.ValidationManager;
import org.scribble.protocol.validation.ProtocolComponentValidator;
import org.scribble.protocol.validation.Validator;

public class Activator implements BundleActivator {

	private static final Logger _log=Logger.getLogger(Activator.class.getName());

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {

        Properties props = new Properties();

        final ValidationManager vm=new DefaultValidationManager();
        
        context.registerService(ValidationManager.class.getName(), 
							vm, props);
        
        _log.fine("Registered Validation Manager");
        
        context.registerService(ScribbleLogger.class.getName(), 
				new ConsoleScribbleLogger(), props);

        m_tracker = new ServiceTracker(context,
        		org.scribble.protocol.validation.Validator.class.getName(),
        				null) {
        	
			public Object addingService(ServiceReference ref) {
				Object ret=super.addingService(ref);
				
				_log.fine("Validator has been added: "+ret);
				
				vm.addValidator((Validator)ret);
				
				return(ret);
			}
        };
        
        m_tracker.open();

        
		
		// Register protocol validator
        props = new Properties();

        context.registerService(Validator.class.getName(), 
				new ProtocolComponentValidator(), props);        
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		
		//context.ungetService(arg0);
	}

	private org.osgi.util.tracker.ServiceTracker m_tracker=null;
}
