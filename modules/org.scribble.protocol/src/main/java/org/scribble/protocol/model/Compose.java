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
 * This class represents the Compose construct.
 * 
 */
public abstract class Compose extends ModelInclude {

	private static final long serialVersionUID = -166094008252739879L;

	/**
	 * This is the default constructor.
	 * 
	 */
	public Compose() {
	}
	
	/**
	 * This method returns the associated protocol.
	 * 
	 * @return The protocol
	 */
	public Protocol getDefinition() {
		Protocol ret=m_inlineDefinition;
		
		if (ret == null && m_reference != null) {

			/*
			// Check if local reference
			if (m_reference.isInner()) {
				
				// Search in containing conversation
				ModelObject cur=getParent();
				
				while (ret == null && cur != null) {
					if (cur instanceof Protocol) {
						Protocol prot=(Protocol)cur;
						
						// Check if conversation contains
						// sub-conversation of interest
						for (int i=0; ret == null && i < 
								prot.getBlock().getContents().size(); i++) {
							if (prot.getBlock().getContents().get(i) instanceof Protocol) {
								Protocol subprot=(Protocol)
									prot.getBlock().getContents().get(i);
		
								if (subprot.getLocatedName() != null &&
										subprot.getLocatedName().getName() != null &&
										m_reference.getAlias().equals(
											subprot.getLocatedName().getName()) &&
									((m_reference.getLocatedRole() == null &&
											subprot.getLocatedName().getRole() == null) ||
									((m_reference.getLocatedRole() != null &&
											subprot.getLocatedName().getRole() != null &&
											m_reference.getLocatedRole().equals(
												subprot.getLocatedName().getRole().getName()))))) {
									
									ret = subprot;
								}
							}
						}
					}
					
					cur = cur.getParent();
				}
				
			} else {
				ModelRepository mrep=(ModelRepository)
							RegistryFactory.getRegistry().getExtension(
										ModelRepository.class, null);
				
				if (mrep != null) {
					java.util.List<ModelInfo> models=
							mrep.getModels(m_reference,
									new DefaultModelListener());
					
					for (int i=0; ret == null &&
									i < models.size(); i++) {
						if (models.get(i).getModel() instanceof ProtocolModel) {
							ret = ((ProtocolModel)models.get(i).getModel()).getProtocol();
						}
					}
				}
			}
			*/
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the protocol reference associated
	 * with the run construct.
	 * 
	 * @return The protocol reference, or null if not defined
	 */
	@Reference(containment=true)
	public ProtocolReference getReference() {
		return(m_reference);
	}
	
	/**
	 * This method sets the protocol reference associated
	 * with the run construct.
	 * 
	 * @param ref The protocol reference
	 */
	public void setReference(ProtocolReference ref) {
		
		if (m_reference != null) {
			m_reference.setParent(null);
		}
		
		m_reference = ref;
		
		if (m_reference != null) {
			m_reference.setParent(this);
		}
	}
	
	/**
	 * This method indicates whether the model include is
	 * an inline definition.
	 * 
	 * @return Whether an inline definition
	 */
	public boolean isInline() {
		return(getInlineDefinition() != null);
	}

	/**
	 * This method returns the inline definition associated
	 * with the model include construct.
	 * 
	 * @return The inline definition, or null if not defined
	 */
	@Reference(containment=true)
	public Protocol getInlineDefinition() {
		return(m_inlineDefinition);
	}
	
	/**
	 * This method sets the inline definition associated
	 * with the run construct.
	 * 
	 * @param definition The inner definition
	 */
	public void setInlineDefinition(Protocol definition) {
		
		if (m_inlineDefinition != null) {
			m_inlineDefinition.setParent(null);
		}
		
		m_inlineDefinition = definition;
		
		if (m_inlineDefinition != null) {
			m_inlineDefinition.setParent(this);
		}
	}

	private ProtocolReference m_reference=null;
	private Protocol m_inlineDefinition=null;
}
