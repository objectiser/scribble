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
package org.scribble.protocol.conformance;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.scribble.common.logger.ScribbleLogger;
import org.scribble.protocol.model.Definition;
import org.scribble.protocol.model.Interaction;
import org.scribble.protocol.model.ModelObject;
import org.scribble.protocol.model.Participant;

/**
 * This class provides the Interaction comparator rule.
 */
public class InteractionComparatorRule implements ComparatorRule {

	/**
	 * This method determines whether the comparison rule is
	 * associated with the supplied type.
	 * 
	 * @param obj The object to check
	 * @return Whether the object is of a type supported by the
	 * 					comparison rule
	 */
	public boolean isTypeSupported(ModelObject obj) {
		return(obj instanceof Interaction);
	}
	
	/**
	 * This method determines whether the comparison rule is
	 * appropriate for the supplied model objects.
	 * 
	 * @param main The main model object to be compared
	 * @param ref The reference model object to be compared against
	 * @return Whether the rule is relevant for the
	 * 				model objects
	 */
	public boolean isComparisonSupported(ModelObject main, ModelObject ref) {
		return(main instanceof Interaction &&
				ref instanceof Interaction);
	}
	
	/**
	 * This method compares a model object against a reference
	 * component to determine if they are equal.
	 * 
	 * @param context The context
	 * @param main The main model object
	 * @param reference The reference model object
	 * @param l The model listener
	 * @param deep Perform a deep compare
	 * @return Whether the model objects are comparable
	 */
	public boolean compare(ComparatorContext context, ModelObject main,
					ModelObject reference, ScribbleLogger l, boolean deep) {
		boolean ret=false;
		Interaction maini=(Interaction)main;
		Interaction refi=(Interaction)reference;
		//boolean f_roleCompared=false;
		
		// Check for compatible message signatures
		ret = context.compare(maini.getMessageSignature(),
						refi.getMessageSignature(), l, deep);
		
		if (ret) {

			Participant fromRoleMain=findParticipant(maini, true);
			Participant fromRoleRef=findParticipant(refi, true);
			Participant toRoleMain=findParticipant(maini, false);
			Participant toRoleRef=findParticipant(refi, false);
			
			// Check if 'to' and 'from' roles are compatible			
			if (ret && fromRoleMain != null &&
					fromRoleRef != null) {
				
				ret = context.compare(fromRoleMain,
						fromRoleRef, l, deep);
				
				if (logger.isLoggable(Level.FINE)) {
					logger.fine("Interaction "+this+": compare 'from' roles: "+
						fromRoleMain+" against "+fromRoleRef+" = "+ret);
				}

				//f_roleCompared = true;
			}
			
			if (ret && toRoleMain != null &&
							toRoleRef != null) {
				ret = context.compare(toRoleMain,
						toRoleRef, l, deep);
				
				if (logger.isLoggable(Level.FINE)) {
					logger.fine("Interaction "+this+": compare 'to' roles: "+
						toRoleMain+" against "+toRoleRef+" = "+ret);
				}
				
				//f_roleCompared = true;
			}
			
			/* TODO: Determine if mismatch direction is still
			 * required
			 * 
			// Check if 'to' and 'from' roles are compatible			
			if (ret && maini.getFromRole() != null &&
					refi.getFromRole() != null) {
				ret = context.compare(maini.getFromRole(),
						refi.getFromRole(), l, deep);
				
				f_roleCompared = true;
			}
			
			if (ret && maini.getToRole() != null &&
							refi.getToRole() != null) {
				ret = context.compare(maini.getToRole(),
						refi.getToRole(), l, deep);
				
				f_roleCompared = true;
			}
			
			if (f_roleCompared == false) {
				boolean f_directionMismatch=false;
				
				if (maini.getFromRole() != null &&
						refi.getToRole() != null) {
					f_directionMismatch = context.compare(maini.getFromRole(),
							refi.getToRole(), l, deep);
				}
				
				if (!f_directionMismatch && maini.getToRole() != null &&
						refi.getFromRole() != null) {
					f_directionMismatch = context.compare(maini.getToRole(),
							refi.getFromRole(), l, deep);
				}
				
				if (f_directionMismatch) {
					String mesg=org.scribble.util.MessageUtil.format(
							java.util.PropertyResourceBundle.getBundle(
							"org.scribble.protocol.conformance.Messagess").
							getString("_INTERACTION_DIRECTION_MISMATCH"),
									new String[]{});
					
					l.error(new ModelIssue(main, mesg));
		
					ret = false;
				}
			}
			*/
		}

		return(ret);
	}
	
	protected Participant findParticipant(Interaction interaction, boolean from) {
		Participant ret=null;

		if (from) {
			ret = interaction.getFromParticipant();
			
			if (ret == null && interaction.getChannel() != null) {
				ret = interaction.getChannel().getFromParticipant();
			}
			
			// If no 'from' role found, and if a 'to' role has
			// been explicitly defined, then check if 'from' role
			// can be inferred from the definition's located role
			if (ret == null) {
				Participant other=interaction.getToParticipant();

				if (other == null && interaction.getChannel() != null) {
					other = interaction.getChannel().getToParticipant();
				}
				
				if (other != null) {
					Definition defn=interaction.enclosingDefinition();
					
					if (defn != null && defn.getLocatedName() != null) {
						ret = defn.getLocatedName().getParticipant();
						
						// Check that role is not the same as the 'to'
						// role
						if (ret != null && ret.equals(other)) {
							ret = null;
						}
					}
				}
			}
		} else {
			ret = interaction.getToParticipant();
			
			if (ret == null && interaction.getChannel() != null) {
				ret = interaction.getChannel().getToParticipant();
			}
			
			// If no 'to' role found, and if a 'from' role has
			// been explicitly defined, then check if 'to' role
			// can be inferred from the definition's located role
			if (ret == null) {
				Participant other=interaction.getFromParticipant();

				if (other == null && interaction.getChannel() != null) {
					other = interaction.getChannel().getFromParticipant();
				}
				
				if (other != null) {
					Definition defn=interaction.enclosingDefinition();
					
					if (defn != null && defn.getLocatedName() != null) {
						ret = defn.getLocatedName().getParticipant();
						
						// Check that role is not the same as the 'from'
						// role
						if (ret != null && ret.equals(other)) {
							ret = null;
						}
					}
				}
			}
		}

		return(ret);
	}

	private static Logger logger = Logger.getLogger("org.scribble.comparator");
}
