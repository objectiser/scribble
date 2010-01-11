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
package org.scribble.protocol.conformance.impl;

import org.scribble.protocol.model.*;

/**
 * This class provides a list mechanism for serialising the
 * behaviours associated with a definition. This makes it
 * easier to compare the behaviours between definitions.
 */
public class BehaviourList extends Behaviour
						implements NameMap {

	private static final long serialVersionUID = -7039686106355918961L;

	/**
	 * This method returns the top level behaviour list associated
	 * with the supplied block.
	 * 
	 * @param defn The block
	 * @return The top level behaviour list
	 */
	public static BehaviourList createBehaviourList(Block defn) {
		BehaviourList ret=new BehaviourList(defn);
		
		ret = getTopLevelBehaviourList(ret);
		
		return(ret);
	}
	
	/**
	 * This constructor initializes the behaviour list with the
	 * supplied block.
	 * 
	 * @param defn The block
	 */
	protected BehaviourList(Block defn) {
		m_block = defn;
		
		initialize();
	}

	/**
	 * This constructor initializes the behaviour list with the
	 * supplied block and model inclusion.
	 * 
	 * @param defn The block
	 * @param include The model inclusion construct
	 */
	protected BehaviourList(Block defn, org.scribble.protocol.model.ModelInclude include) {
		m_block = defn;
		m_include = include;
		
		initialize();
	}
	
	protected BehaviourList() {
		// Create a dummy block associated with the
		// behaviour list - required for test purposes,
		// to report as target of errors if comparison
		// detects a missing activity at the end of the
		// block.
		m_block = new Block();
	}
	
	protected void initialize() {
		derivedFrom(m_block);
		m_block.visit(new BehaviourListVisitor());
	}
	
	/**
	 * This method sets the name map associated with the behaviour
	 * list.
	 * 
	 * @param map The name map
	 */
	public void setNameMap(java.util.Map<String,String> map) {
		m_nameMap = map;
	}
	
	/**
	 * This method returns the name that has been mapped
	 * to the supplied name. If the supplied name has
	 * no mapping, then the same name will be returned.
	 * 
	 * @param name The current name
	 * @return The original name
	 */
	public String getName(String name) {
		return(m_nameMap == null || m_nameMap.containsKey(name) == false
					? name : m_nameMap.get(name));
	}
	
	/**
	 * This method adds the supplied behaviour to the list.
	 * 
	 * @param behaviour The behaviour
	 */
	protected void addToList(Behaviour behaviour) {
		boolean f_added=false;
		
		if (m_list.size() > 0) {
			// Check behaviour at the end of the list, to determine
			// whether the behaviour to be added to be added to
			// a contained single or multi path behaviour
			Behaviour prev=m_list.get(m_list.size()-1);
			
			if (prev instanceof BehaviourListPaths &&
					((BehaviourListPaths)prev).isStrictScope() == false) {
				BehaviourListPaths blpaths=(BehaviourListPaths)prev;
				
				// Place the entry to be added in each of the paths
				for (int i=0; i < blpaths.getPaths().size(); i++) {
					
					// Recursively perform the add-to-list, so if
					// another non-strict scope construct gets added
					// then subsequent activities will be contained
					// within it, rather than the outer one
					blpaths.getPaths().get(i).addToList(behaviour);
					
					f_added = true;
				}
			}
		}
		
		if (f_added == false) {
			m_list.add(behaviour);
		}
	}
	
	/**
	 * This method returns the name map associated with the supplied
	 * binding information. The returned name map links the bound name
	 * back to the original declaration name.
	 * 
	 * @param bindings The bindings
	 * @return The name map
	 */
	protected java.util.Map<String,String> getNameMap(java.util.List<DeclarationBinding> bindings) {
		java.util.Map<String,String> ret=new java.util.Hashtable<String,String>();
		
		java.util.Iterator<DeclarationBinding> iter=bindings.iterator();
		while (iter.hasNext()) {
			DeclarationBinding db=iter.next();
			
			ret.put(db.getBoundName(), db.getLocalName());
		}
		
		return(ret);
	}
	
	public BehaviourListIterator getIterator() {
		return(new BehaviourListIterator(this));
	}

	public java.util.List<Behaviour> getBehaviourList() {
		return(m_list);
	}
	
	/**
	 * This method returns the optional model include
	 * associated with the contained behaviour activities.
	 * 
	 * @return The optional model include
	 */
	public ModelInclude getModelInclude() {
		return(m_include);
	}
	
	/**
	 * This method returns the model object associated with the
	 * list.
	 * 
	 * @return The model object
	 */
	public Block getBlock() {
		return(m_block);
	}
	
	/**
	 * This method helps to locate the relevant top level behaviour
	 * list, where comparison should start from.
	 * 
	 * @param bl The proposed behaviour list
	 * @return The actual top level behaviour list of interest
	 */
	protected static BehaviourList getTopLevelBehaviourList(BehaviourList bl) {
		BehaviourList ret=bl;
		
		if (ret.getBehaviourList().size() == 1) {
			
			if (ret.getBehaviourList().get(0) instanceof BehaviourList) {
				ret = getTopLevelBehaviourList((BehaviourList)
								ret.getBehaviourList().get(0));
			} else if (ret.getBehaviourList().get(0) instanceof
							BehaviourListPaths) {
				BehaviourListPaths blps=(BehaviourListPaths)
							ret.getBehaviourList().get(0);
				
				if (blps.getPaths().size() == 1) {
					ret = getTopLevelBehaviourList(blps.getPaths().get(0));
				}
			}
		}
		
		return(ret);
	}

	@Override
	public void visit(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}
	
	private Block m_block=null;
	private ModelInclude m_include=null;
	private java.util.Map<String,String> m_nameMap=null;
	private java.util.List<Behaviour> m_list=new java.util.Vector<Behaviour>();
	
	public class BehaviourListVisitor extends DefaultVisitor {
		
		public BehaviourListVisitor() {
			super();
		}
		
		public boolean startChoice(Choice elem) {
			visitMultiPath(elem);
			
			return(false);
		}
		
		public boolean startParallel(Parallel elem) {
			visitMultiPath(elem);
			
			return(false);
		}
		
		public boolean startTryEscape(TryEscape elem) {
			visitMultiPath(elem);
			
			return(false);
		}
		
		protected void visitMultiPath(MultiPathBehaviour mpb) {
			java.util.List<BehaviourList> bllist=
					new java.util.Vector<BehaviourList>();
			
			for (int i=0; i < mpb.getPaths().size(); i++) {
				Block block=mpb.getPaths().get(i);
				
				BehaviourList bl=new BehaviourList(block);
				
				//bl.derivedFrom(block);
				
				bl.setNameMap(m_nameMap);
				
				//block.visit(bl);
				
				bllist.add(bl);
			}
			
			BehaviourListPaths blpaths=new BehaviourListPaths((Behaviour)mpb,
									bllist);
			
			if (blpaths.isVisible()) {
				addToList(blpaths);
			}
		}
		
		public boolean startRepeat(Repeat elem) {
			visitSinglePath(elem);
			
			return(false);
		}
		
		public boolean startRecursion(Recursion elem) {
			visitSinglePath(elem);
			
			return(false);
		}
		
		protected void visitSinglePath(SinglePathBehaviour spb) {
			java.util.List<BehaviourList> bllist=
				new java.util.Vector<BehaviourList>();
		
			BehaviourList bl=new BehaviourList(spb.getBlock());
				
			//bl.derivedFrom(path.getBlock());
				
			bl.setNameMap(m_nameMap);
				
			//path.getBlock().visit(bl);
				
			bllist.add(bl);
			
			BehaviourListPaths blpaths=new BehaviourListPaths((Behaviour)spb,
									bllist);
			
			if (blpaths.isVisible()) {
				addToList(blpaths);
			}
		}
		
		public boolean startBlock(Block elem) {
			return(true);
		}
		
		public void visitInteraction(Interaction elem) {
			addToList(elem);
		}
		
		public void visitRun(Run run) {
			Protocol defn=
				((ModelInclude)run).getProtocol();
		
			if (defn != null) {
				BehaviourList list=null;
				
				// Check if external model - if so, then
				// need to supply reference to list, so can be
				// used in error reporting
				if (run.getReference() != null &&
							run.getReference().isInner() == false) {
					list = new BehaviourList(defn.getBlock(), run);
				} else {
					list = new BehaviourList(defn.getBlock());
				}
				
				list.setNameMap(getNameMap(run.getBindings()));
				
	//			list.derivedFrom(include);
				
	//			defn.visit(list);
			
				// Add list, if async (to identify existence of
				// async composed conversation), or if the list
				// has elements
				if (run.isAsynchronous()) {
					
					// If async, then need to add 'behaviour
					// list path' to isolate the behaviour
					// list, to ensure it does not get merged
					// into the outer list (as with the
					// sync run)
					java.util.List<BehaviourList> bllist=
						new java.util.Vector<BehaviourList>();
				
					bllist.add(list);
				
					BehaviourListPaths blpaths=new BehaviourListPaths(run,
										bllist);
				
					addToList(blpaths);
					
				} else if (list.getBehaviourList().size() > 0) {
					addToList(list);					
				}
			}			
		}

		/*
		public boolean visit(ModelObject obj) {
			boolean ret=false;
			
			if (obj instanceof MultiPathBehaviour) {
				MultiPathBehaviour paths=
						(MultiPathBehaviour)obj;
				java.util.List<BehaviourList> bllist=
						new java.util.Vector<BehaviourList>();
				
				for (int i=0; i < paths.getPaths().size(); i++) {
					Block block=paths.getPaths().get(i);
					
					BehaviourList bl=new BehaviourList(block);
					
					//bl.derivedFrom(block);
					
					bl.setNameMap(m_nameMap);
					
					//block.visit(bl);
					
					bllist.add(bl);
				}
				
				BehaviourListPaths blpaths=new BehaviourListPaths((Behaviour)obj,
										bllist);
				
				if (blpaths.isVisible()) {
					addToList(blpaths);
				}

			} else if (obj instanceof SinglePathBehaviour) {
				SinglePathBehaviour path=
						(SinglePathBehaviour)obj;
				java.util.List<BehaviourList> bllist=
						new java.util.Vector<BehaviourList>();
				
				BehaviourList bl=new BehaviourList(path.getBlock());
					
				//bl.derivedFrom(path.getBlock());
					
				bl.setNameMap(m_nameMap);
					
				//path.getBlock().visit(bl);
					
				bllist.add(bl);
				
				BehaviourListPaths blpaths=new BehaviourListPaths((Behaviour)obj,
										bllist);
				
				if (blpaths.isVisible()) {
					addToList(blpaths);
				}

			} else if (obj instanceof ModelInclude) {
				ModelInclude include=
						(ModelInclude)obj;
				Protocol defn=
						((ModelInclude)obj).getProtocol();
				
				if (defn != null) {
					BehaviourList list=null;
					
					// Check if external model - if so, then
					// need to supply reference to list, so can be
					// used in error reporting
					if (include.getReference() != null &&
								include.getReference().isInner() == false) {
						list = new BehaviourList(defn.getBlock(), include);
					} else {
						list = new BehaviourList(defn.getBlock());
					}
					
					list.setNameMap(getNameMap(include.getBindings()));
					
//					list.derivedFrom(include);
					
//					defn.visit(list);
				
					// Add list, if async (to identify existence of
					// async composed conversation), or if the list
					// has elements
					if (include.isAsynchronous()) {
						
						// If async, then need to add 'behaviour
						// list path' to isolate the behaviour
						// list, to ensure it does not get merged
						// into the outer list (as with the
						// sync run)
						java.util.List<BehaviourList> bllist=
							new java.util.Vector<BehaviourList>();
					
						bllist.add(list);
					
						BehaviourListPaths blpaths=new BehaviourListPaths((Behaviour)obj,
											bllist);
					
						addToList(blpaths);
						
					} else if (list.getBehaviourList().size() > 0) {
						addToList(list);					
					}
				}
				
			} else if (obj instanceof Block) {
				
				// Iterate through each activity checking its a behaviour
				ret = true;
				
			} else if (obj instanceof Behaviour) {
				addToList((Behaviour)obj);
			
				// Only recursively check for behaviours
				// inside other behaviours. This will ensure
				// inner protocols will not be indirectly
				// processed - only if invoked as part of
				// a run statement.
				ret = true;
				
			} else if (obj == m_block) {
				// Visiting the main definition, so visit contents
				ret = true;
			}
			
			return(ret);
		}
		*/
	}
}
