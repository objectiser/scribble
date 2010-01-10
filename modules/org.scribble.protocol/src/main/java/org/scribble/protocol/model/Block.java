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
package org.scribble.protocol.model;

/**
 * This class represents a group of activities.
 * 
 */
public class Block extends Activity {

	private static final long serialVersionUID = 6629455247154922281L;

	/**
	 * This method returns the contents associated with
	 * the block.
	 * 
	 * @return The contents
	 */
	@Reference(containment=true)
	public java.util.List<Activity> getContents() {
		return(m_contents);
	}
	
	/**
	 * This method returns the list of roles that are
	 * responsible for initiating the activity. This can
	 * be used to determine whether the model is
	 * consistent in terms of decision makers subsequently
	 * initiating actions.
	 * 
	 * @return The list of initiator roles
	 */
	@Override
	public java.util.List<Participant> initiatorParticipants() {
		java.util.List<Participant> ret=super.initiatorParticipants();
		boolean f_end=false;
		
		for (int i=0; f_end == false &&
						i < getContents().size(); i++) {
			
			if (getContents().get(i) instanceof Behaviour) {
				java.util.List<Participant> roleList=
					getContents().get(i).initiatorParticipants();
				
				for (int j=0; j < roleList.size(); j++) {
					if (ret.contains(roleList.get(j)) == false) {
						ret.add(roleList.get(j));
					}
				}
				
				if (getContents().get(i).isConditional() == false) {
					f_end = true;
				}
			}
		}
		
		return(ret);
	}

	/**
	 * This method visits the model object using the supplied
	 * visitor.
	 * 
	 * @param visitor The visitor
	 */
	public void visit(Visitor visitor) {
		
		if (visitor.startBlock(this)) {
		
			for (int i=0; i < getContents().size(); i++) {
				getContents().get(i).visit(visitor);
			}
		}
		
		visitor.endBlock(this);
	}
	
	private java.util.List<Activity> m_contents=
		new ContainmentList<Activity>(this, Activity.class);
}
