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
package org.scribble.core.model;

/**
 * This class represents the base class for all Scribble definitions.
 */
public abstract class Definition extends Activity {

	private static final long serialVersionUID = 6381278356995297737L;

	/**
	 * This is the default constructor for the definition.
	 * 
	 */
	public Definition() {
	}
	
	/**
	 * This method returns the name of the definition.
	 * 
	 * @return The name
	 */
	@Reference(containment=true)
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
	@Reference(containment=true)
	public java.util.List<ConformanceReference> getConformsTo() {
		return(m_conformsTo);
	}
	
	/**
	 * This method returns the list of models that this definition
	 * implements.
	 * 
	 * @return The list of models this definition implements
	 */
	@Reference(containment=true)
	public java.util.List<ImplementsReference> getImplements() {
		return(m_implements);
	}
	
	/**
	 * This method returns the block of activities associated
	 * with the definition.
	 * 
	 * @return The block of activities
	 */
	@Reference(containment=true)
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
	public Model<?> getModel() {
		Model<?> ret=null;
		ModelObject cur=this;
		
		while (ret == null && cur != null) {
			if (cur instanceof Model<?>) {
				ret = (Model<?>)cur;
			} else {
				cur = cur.getParent();
			}
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the definition in which this
	 * activity is contained.
	 * 
	 * @return The definition, or null if not found
	 */
	@Override
	public Definition getEnclosingDefinition() {
		return(this);
	}
	
	/**
	 * This method returns the top level definition.
	 * 
	 * @return The top level definition
	 */
	public Definition getTopLevelDefinition() {
		Definition ret=this;
		
		if (getParent() instanceof Definition) {
			ret = ((Definition)getParent()).getTopLevelDefinition();
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the sub-definition associated
	 * with the supplied name.
	 * 
	 * @param name The name
	 * @return The sub-definition for the supplied name,
	 * 				or null if not found
	 */
	public Definition getSubDefinition(String name) {
		Definition ret=null;
	
		for (int i=0; ret == null &&
				i < getBlock().getContents().size(); i++) {
			Activity act=getBlock().getContents().get(i);
			
			if (act instanceof Definition &&
					((Definition)act).getLocatedName().getName().equals(name)) {
				ret = (Definition)act;
			}
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the sub-definition associated
	 * with the supplied path.
	 * 
	 * @param subPath The sub path
	 * @return The sub-definition for the supplied path,
	 * 				or null if not found
	 */
	public Definition getSubDefinition(SubDefinitionPath subPath) {
		Definition ret=this;
	
		for (int i=0; ret != null && 
					i < subPath.getPathElementCount(); i++) {
			ret = ret.getSubDefinition(subPath.getPathElement(i));
		}
		
		return(ret);
	}
	
	/**
	 * This method return the top level declarations associated
	 * with the definition.
	 * 
	 * @return The declarations
	 */
	public java.util.Set<Declaration> getDeclarations() {
		java.util.Set<Declaration> ret=new java.util.HashSet<Declaration>();
		
		// Check if definition has a located role
		if (getLocatedName().getRole() != null) {
			ret.add(getLocatedName().getRole());
		}
		
		// Check activities for suitable declarations
		java.util.Iterator<Activity> iter=getBlock().getContents().iterator();
		
		while (iter.hasNext()) {
			Activity act=iter.next();
			
			if (act instanceof RoleList) {
				ret.addAll(((RoleList)act).getRoles());
			} else if (act instanceof ChannelList) {
				ret.addAll(((ChannelList)act).getChannels());
			}
		}
		
		return(ret);
	}
		
	/**
	 * This method returns the named top level declaration,
	 * associated with this definition.
	 * 
	 * @param name The declaration name
	 * @return The declaration, or null if not found
	 */
	public Declaration getDeclaration(String name) {
		Declaration ret=null;
		
		java.util.Iterator<Declaration> iter=getDeclarations().iterator();
		while (ret == null && iter.hasNext()) {
			ret = iter.next();
			
			if (ret.getName().equals(name) == false) {
				ret = null;
			}
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the list of roles defined at
	 * the top level of the definition.
	 * 
	 * @return The list of roles
	 */
	public java.util.List<Role> getRoles() {
		java.util.List<Role> ret=new java.util.Vector<Role>();
		
		for (int i=0; i < getBlock().getContents().size(); i++) {
		
			if (getBlock().getContents().get(i) instanceof RoleList) {
				ret.addAll(((RoleList)getBlock().getContents().get(i)).getRoles());
			}
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
	protected Role getLocatedRole() {
		Role ret=null;
		
		if (getLocatedName() != null) {
			ret = getLocatedName().getRole();
		}
		
		return(ret);
	}

	private LocatedName m_locatedName=null;
	private boolean m_stateless=false;
	private java.util.List<ConformanceReference> m_conformsTo=
			new ContainmentList<ConformanceReference>(this, ConformanceReference.class);
	private java.util.List<ImplementsReference> m_implements=
			new ContainmentList<ImplementsReference>(this, ImplementsReference.class);
	private Block m_contents=null;
}
