package org.scribble.core.logger;

public interface ScribbleLogger {

	public void error(org.scribble.core.model.ModelObject modelObject,
					String issue);
	
	public void warning(org.scribble.core.model.ModelObject modelObject,
			String issue);

	public void info(org.scribble.core.model.ModelObject modelObject,
			String issue);

	public void debug(org.scribble.core.model.ModelObject modelObject,
			String issue);

	public void trace(org.scribble.core.model.ModelObject modelObject,
			String issue);

}
