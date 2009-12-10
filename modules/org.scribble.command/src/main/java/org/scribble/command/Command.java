package org.scribble.command;

public interface Command {

	public String getName();
	
	public String getDescription();
	
	public boolean execute(String args[]);
	
}