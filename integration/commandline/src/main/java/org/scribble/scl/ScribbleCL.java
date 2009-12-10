package org.scribble.scl;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.apache.felix.framework.Felix;
import org.apache.felix.framework.util.FelixConstants;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.apache.felix.main.AutoProcessor;
import org.apache.felix.main.Main;

import org.scribble.command.*;

public class ScribbleCL
{
    private HostActivator m_activator = null;
    private Felix m_felix = null;
    private ServiceTracker m_tracker = null;

    public static void main(String[] args) {
    	ScribbleCL scl=new ScribbleCL();
    	
    	try {
    		if (scl.execute(args[0], args[1]) == false) {
    			System.err.println("Command not executed");
    		}
    		
    		scl.shutdownApplication();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public ScribbleCL()
    {
        // Create a configuration property map.
        //Map configMap = new HashMap();
        Main.loadSystemProperties();

        java.util.Properties configProps = Main.loadConfigProperties();
        if (configProps == null)
        {
            System.err.println("No config.properties found.");
            configProps = new java.util.Properties();
        }

        Main.copySystemProperties(configProps);

        // Export the host provided service interface package.
        configProps.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA,
        			"org.scribble.command");
    				//"tutorial.example2.service; version=1.0.0");
        
        // Create host activator;
        m_activator = new HostActivator();
        List list = new ArrayList();
        list.add(m_activator);
        configProps.put(FelixConstants.SYSTEMBUNDLE_ACTIVATORS_PROP, list);

        try
        {
            // Now create an instance of the framework with
            // our configuration properties.
            m_felix = new Felix(configProps);

            m_felix.init();
            
        	AutoProcessor.process(configProps, m_felix.getBundleContext());
        	
            // Now start Felix instance.
            m_felix.start();
        }
        catch (Exception ex)
        {
            System.err.println("Could not create framework: " + ex);
            ex.printStackTrace();
        }
        
        /*
        ServiceTrackerCustomizer stc=
        		new ServiceTrackerCustomizer() {

					@Override
					public Object addingService(ServiceReference arg0) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public void modifiedService(ServiceReference arg0,
							Object arg1) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void removedService(ServiceReference arg0,
							Object arg1) {
						// TODO Auto-generated method stub
						
					}
        	
        };
*/

        m_tracker = new ServiceTracker(
                m_activator.getContext(), org.scribble.command.Command.class.getName(), null);
        //m_activator.getContext(), tutorial.example2.service.DictionaryService.class.getName(), null);
               m_tracker.open();
    }

    public boolean execute(String name, String commandline)
    {
        // See if any of the currently tracked command services
        // match the specified command name, if so then execute it.
        Object[] services = m_tracker.getServices();
        for (int i = 0; (services != null) && (i < services.length); i++)
        {
            try
            {
            	//((tutorial.example2.service.DictionaryService)services[i]).checkWord("hello");
                if (((Command) services[i]).getName().equals(name))
                {
                    return ((Command) services[i]).execute(commandline);
                }
            }
            catch (Exception ex)
            {
                // Since the services returned by the tracker could become
                // invalid at any moment, we will catch all exceptions, log
                // a message, and then ignore faulty services.
                System.err.println(ex);
            }
        }
        return false;
    }

    public void shutdownApplication() throws Exception
    {
        // Shut down the felix framework when stopping the
        // host application.
        m_felix.stop();
        m_felix.waitForStop(5000);
    }
}