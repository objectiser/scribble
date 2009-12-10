package org.scribble.command.validate;

import java.util.List;

import org.scribble.core.model.Definition;

public class ValidateCommand implements org.scribble.command.Command {

	public String getName() {
		return("validate");
	}
	
	public String getDescription() {
		return("Validate a Scribble description");
	}
	
	public boolean execute(String args[]) {
		boolean ret=false;
		
		if (args.length == 1) {
			System.out.println("VALIDATE "+args[0]);
			
			org.scribble.core.model.Model model=
				new org.scribble.core.model.Model() {

					@Override
					public List<Definition> getDefinitions() {
						// TODO Auto-generated method stub
						return null;
					}			
			};
			
			
			
			ret = true;
		} else {
			System.err.println("VALIDATE EXPECTING 1 PARAMETER");
		}
		
		return(ret);
	}

}
