package org.scribble.scl;

public interface OLDCommand {

	public String getName();
	
	public String getDescription();
	
	public boolean execute(String args);
	
}