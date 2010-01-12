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
 * This class represents the protocol notation.
 */
public class Protocol extends Activity {
	
	private static final long serialVersionUID = 5352253730676464788L;

	/**
	 * The default constructor.
	 */
	public Protocol() {
	}
	
	/**
	 * This method returns whether the activity represents
	 * a scope.
	 * 
	 * @return Whether activity represents a scope
	 */
	@Override
	public boolean isScope() {
		return(true);
	}
	
	/**
	 * This method returns the name of the definition.
	 * 
	 * @return The name
	 */
	public LocatedName getLocatedName() {
		return(m_locatedName);
	}
	
	/**
	 * This method sets the name of the definition.
	 * 
	 * @param name The name
	 */
	public void setLocatedName(LocatedName name) {
		
		if (m_locatedName != null) {
			m_locatedName.setParent(null);
		}
		
		m_locatedName = name;
		
		if (m_locatedName != null) {
			m_locatedName.setParent(this);
		}
	}
	
	/**
	 * This method determines whether the definition is stateless.
	 * 
	 * @return Whether the definition is stateless
	 */
	public boolean isStateless() {
		return(m_stateless);
	}
	
	/**
	 * This method sets whether the definition is stateless.
	 * 
	 * @param stateless Whether the definition is stateless
	 */
	public void setStateless(boolean stateless) {
		m_stateless = stateless;
	}
	
	/**
	 * This method returns the list of models that this definition
	 * must conform to.
	 * 
	 * @return The list of models that must be conformed to
	 */
	public java.util.List<ConformanceReference> getConformsTo() {
		return(m_conformsTo);
	}
	
	/**
	 * This method returns the list of models that this definition
	 * implements.
	 * 
	 * @return The list of models this definition implements
	 */
	public java.util.List<ImplementsReference> getImplements() {
		return(m_implements);
	}
	
	/**
	 * This method returns the block of activities associated
	 * with the definition.
	 * 
	 * @return The block of activities
	 */
	public Block getBlock() {
		
		if (m_contents == null) {
			m_contents = new Block();
			m_contents.setParent(this);
		}
		
		return(m_contents);
	}
	
	/**
	 * This method sets the block of activities associated
	 * with the definition.
	 * 
	 * @param block The block of activities
	 */
	public void setBlock(Block block) {
		if (m_contents != null) {
			m_contents.setParent(null);
		}
		
		m_contents = block;
		
		if (m_contents != null) {
			m_contents.setParent(this);
		}
	}
	
	/**
	 * This method returns the model in which this definition
	 * is contained.
	 * 
	 * @return The model, or null if not contained within
	 * 					a model
	 */
	public ProtocolModel getModel() {
		ProtocolModel ret=null;
		ModelObject cur=this;
		
		while (ret == null && cur != null) {
			if (cur instanceof ProtocolModel) {
				ret = (ProtocolModel)cur;
			} else {
				cur = cur.getParent();
			}
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the protocol in which this
	 * activity is contained.
	 * 
	 * @return The protocol, or null if not found
	 */
	@Override
	public Protocol enclosingProtocol() {
		return(this);
	}
	
	/**
	 * This method returns the top level protocol.
	 * 
	 * @return The top level protocol
	 */
	public Protocol getTopLevelProtocol() {
		Protocol ret=this;
		
		if (getParent() instanceof Protocol) {
			ret = ((Protocol)getParent()).getTopLevelProtocol();
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the sub-protocol associated
	 * with the supplied name.
	 * 
	 * @param name The name
	 * @return The sub-protocol for the supplied name,
	 * 				or null if not found
	 */
	public Protocol getSubProtocol(String name) {
		Protocol ret=null;
	
		for (int i=0; ret == null &&
				i < getBlock().getContents().size(); i++) {
			Activity act=getBlock().getContents().get(i);
			
			if (act instanceof Protocol &&
					((Protocol)act).getLocatedName().getName().equals(name)) {
				ret = (Protocol)act;
			}
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the sub-protocol associated
	 * with the supplied path.
	 * 
	 * @param subPath The sub path
	 * @return The sub-protocol for the supplied path,
	 * 				or null if not found
	 */
	public Protocol getSubProtocol(SubProtocolPath subPath) {
		Protocol ret=this;
	
		for (int i=0; ret != null && 
					i < subPath.getPathElementCount(); i++) {
			ret = ret.getSubProtocol(subPath.getPathElement(i));
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the optional located role
	 * associated with the enclosing definition.
	 * 
	 * @return The located role, or null if the
	 * 			enclosing definition is not located
	 */
	@Override
	protected Participant locatedParticipant() {
		Participant ret=null;
		
		if (getLocatedName() != null) {
			ret = getLocatedName().getParticipant();
		}
		
		return(ret);
	}

	/**
	 * This method returns the list of participants defined at
	 * the top level of the definition.
	 * 
	 * @return The list of participants
	 */
	public java.util.List<Participant> getParticipants() {
		java.util.List<Participant> ret=new java.util.Vector<Participant>();
		
		for (int i=0; i < getBlock().getContents().size(); i++) {
		
			if (getBlock().getContents().get(i) instanceof ParticipantList) {
				ret.addAll(((ParticipantList)getBlock().getContents().get(i)).getParticipants());
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
		visitor.startProtocol(this);
		
		if (getLocatedName() != null) {
			getLocatedName().visit(visitor);
		}
		
		if (getBlock() != null) {
			getBlock().visit(visitor);
		}
		
		visitor.endProtocol(this);
	}
	
	private LocatedName m_locatedName=null;
	private boolean m_stateless=false;
	private java.util.List<ConformanceReference> m_conformsTo=
			new ContainmentList<ConformanceReference>(this, ConformanceReference.class);
	private java.util.List<ImplementsReference> m_implements=
			new ContainmentList<ImplementsReference>(this, ImplementsReference.class);
	private Block m_contents=null;
}
