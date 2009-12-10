package org.scribble.core.validation;

public class ValidationManagerFactory {

	public static ValidationManager getValidationManager() {
		return(m_instance);
	}
	
	private static ValidationManager m_instance=
			new org.scribble.core.internal.validation.ValidationManagerImpl();
}
