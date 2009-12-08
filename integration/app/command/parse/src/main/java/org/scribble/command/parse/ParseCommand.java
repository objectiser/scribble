package org.scribble.command.parse;

public class ParseCommand implements org.scribble.scl.Command {

	public String getName() {
		return("parse");
	}
	
	public String getDescription() {
		return("Parse a Scribble description");
	}
	
	public boolean execute(String args) {
		System.out.println("PARSE "+args);
		return(true);
	}

}
