package org.scribble.command.parse;

public class ParseCommand implements org.scribble.command.Command {

	public String getName() {
		return("parse");
	}
	
	public String getDescription() {
		return("Parse a Scribble description");
	}
	
	public boolean execute(String args[]) {
		boolean ret=false;
		
		if (args.length == 1) {
			System.out.println("PARSE "+args[0]);
			ret = true;
		} else {
			System.err.println("PARSE EXPECTING 1 PARAMETER");
		}
		
		return(ret);
	}

}
