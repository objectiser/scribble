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
package org.scribble.designer.osgi;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.scribble.core.validation.DefaultValidationManager;
import org.scribble.core.validation.ValidationManager;
import org.scribble.designer.DesignerServices;
import org.scribble.designer.editor.*;
import org.scribble.designer.editor.lang.*;
import org.scribble.designer.editor.util.*;
import org.scribble.protocol.parser.ProtocolParser;

import java.util.logging.*;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.rules.RuleBasedScanner;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.scribble.designer";

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		// TODO: Obtain validation manager pre-initialised
		
		ValidationManager vm=new DefaultValidationManager();
		vm.addValidator(new org.scribble.protocol.validation.ProtocolComponentValidator());
		
		DesignerServices.setValidationManager(vm);
		
		// Obtain reference to protocol parser
		ServiceReference sr=context.getServiceReference(ProtocolParser.class.getName());
	
		if (sr != null) {
			DesignerServices.setProtocolParser((ProtocolParser)context.getService(sr));				
		} else {
			
	        ServiceListener sl1 = new ServiceListener() {
	        	public void serviceChanged(ServiceEvent ev) {
	        		ServiceReference sr = ev.getServiceReference();
	        		switch(ev.getType()) {
	        		case ServiceEvent.REGISTERED:
	        			ProtocolParser pp=
	        				(ProtocolParser)context.getService(sr);
	        			DesignerServices.setProtocolParser(pp);
	        			break;
	        		case ServiceEvent.UNREGISTERING:
	        			break;
	        		}
	        	}
	        };
	              
	        String filter1 = "(objectclass=" + ProtocolParser.class.getName() + ")";
	        
	        try {
	        	context.addServiceListener(sl1, filter1);
	        } catch(Exception e) {
	        	e.printStackTrace();
	        }
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * This method logs an error against the plugin.
	 * 
	 * @param mesg The error message
	 * @param t The optional exception
	 */
	public static void logError(String mesg, Throwable t) {
		
		if (getDefault() != null) {
			Status status=new Status(IStatus.ERROR,
					PLUGIN_ID, 0, mesg, t);
			
			getDefault().getLog().log(status);
		}
		
		logger.severe("LOG ERROR: "+mesg+
				(t == null ? "" : ": "+t));
	}
	
	/**
	 * Return a scanner for creating Java partitions.
	 * 
	 * @return a scanner for creating Java partitions
	 */
	 public ScribblePartitionScanner getScribblePartitionScanner() {
		if (fPartitionScanner == null)
			fPartitionScanner= new ScribblePartitionScanner();
		return fPartitionScanner;
	}
	
	/**
	 * Returns the singleton Java code scanner.
	 * 
	 * @return the singleton Java code scanner
	 */
	 public RuleBasedScanner getScribbleCodeScanner() {
	 	if (fCodeScanner == null)
			fCodeScanner= new ScribbleCodeScanner(getScribbleColorProvider());
		return fCodeScanner;
	}
	
	/**
	 * Returns the singleton Java color provider.
	 * 
	 * @return the singleton Java color provider
	 */
	 public ScribbleColorProvider getScribbleColorProvider() {
	 	if (fColorProvider == null)
			fColorProvider= new ScribbleColorProvider();
		return fColorProvider;
	}
	
	private static Logger logger = Logger.getLogger("org.scribble.designer.eclipse");

	public final static String SCRIBBLE_PARTITIONING= "__scribble_partitioning";   //$NON-NLS-1$
	
	private ScribblePartitionScanner fPartitionScanner;
	private ScribbleColorProvider fColorProvider;
	private ScribbleCodeScanner fCodeScanner;
}
