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
package org.scribble.conformance;

import java.io.Serializable;

import org.scribble.core.logger.CachedScribbleLogger;
import org.scribble.core.logger.ScribbleLogger;
import org.scribble.protocol.model.Behaviour;
import org.scribble.protocol.model.Definition;
import org.scribble.protocol.model.ModelObject;
import org.scribble.protocol.model.MultiPathBehaviour;

public class BehaviourListComparatorRule implements ComparatorRule {

	/**
	 * This method determines whether the comparison rule is
	 * associated with the supplied type.
	 * 
	 * @param obj The object to check
	 * @return Whether the object is of a type supported by the
	 * 					comparison rule
	 */
	public boolean isTypeSupported(ModelObject obj) {
		return(obj instanceof BehaviourList);
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
		return(main instanceof BehaviourList &&
				ref instanceof BehaviourList);
	}
	
	public boolean compare(ComparatorContext context, ModelObject main,
			ModelObject reference, ScribbleLogger l, boolean deep) {
		boolean ret=false;
		BehaviourList mainbl=(BehaviourList)main;
		BehaviourList refbl=(BehaviourList)reference;
		
		if (context.compare(mainbl.getBlock(), refbl.getBlock(),
							l, false)) {
		
			ret = true;
			
			// Get iterators for the two lists
			BehaviourListIterator mainiter=mainbl.getIterator();
			BehaviourListIterator refiter=refbl.getIterator();
			
			SyncPoint syncPoint=null;
			
			while ((syncPoint=findNextSyncPoint(context, mainiter, refiter,
								true, null)) != null) {
				
				// Process behaviours between previous and new sync point
				if (syncPoint.isGap()) {
					BehaviourListIterator innerMainIter=mainiter.snapshot();
					BehaviourListIterator innerRefIter=refiter.snapshot();
					
					SyncPoint innerSyncPoint=null;
					
					while ((innerSyncPoint=findNextSyncPoint(context, innerMainIter,
									innerRefIter, false, syncPoint)) != null) {
											
						if (innerSyncPoint.isGap()) {
							int mainCount=innerSyncPoint.getMainCount();
							int refCount=innerSyncPoint.getReferenceCount();
							Behaviour mainPoint=null;
							
							while (mainCount > 0) {
								mainPoint = innerMainIter.next();
							
								if (isConditionalDecisionMaker(mainPoint) == false) {
									String mesg=org.scribble.core.util.MessageUtil.format(
										java.util.PropertyResourceBundle.getBundle(
										"org.scribble.conformance.Messages").
										getString("_UNEXPECTED_BEHAVIOUR"), null);
								
									//ModelIssue issue=new ModelIssue(mainPoint, mesg);
								
									//issue.getResolutions().add(new DeleteFromModel());
								
									//l.error(issue);
									
									java.util.Map<String,java.io.Serializable> props=
											new java.util.HashMap<String,java.io.Serializable>();
									l.error(mesg, props);
								
									ret = false;
									
									// Exit loop
									mainCount = 0;
								} else {
									mainCount--;
								}
							}
							
							// Get main activity to be compared
							mainPoint = innerMainIter.next();
							
							Behaviour refPoint=null;
							
							while (refCount > 0) {
								refPoint = innerRefIter.next();
		
								if (isConditionalDecisionMaker(refPoint) == false) {
									
									// TODO: Check if 'refPoint' is a conditional
									// activity that is located at the projected role
									// and if so, then don't report as an expected
									// activity error
									
									String mesg=org.scribble.core.util.MessageUtil.format(
											java.util.PropertyResourceBundle.getBundle(
											"org.scribble.conformance.Messages").
											getString("_EXPECTING_ACTIVITY"), null);
										
									/*
									ModelIssue issue=null;
									
									if (mainPoint != null) {
										issue = new ModelIssue(mainPoint, mesg);
									} else {
										issue = new ModelIssue(mainbl.getBlock(), mesg);
									}
									
									// Add 'show reference description' resolution
									ShowReferencedDescription showrefd=new ShowReferencedDescription();
									
									if (refiter.getComposedSource() != null) {
										showrefd.setModel(refiter.getComposedSource());
									} else {
										showrefd.setModel(context.getReferenceSource());
									}
									showrefd.setSourceReference(refPoint.getSource());
									
									issue.getResolutions().add(showrefd);
									
									// Add 'insert model object' resolution
									InsertFromReferencedDescription imo=new InsertFromReferencedDescription();
									
									if (refiter.getComposedSource() != null) {
										imo.setModel(refiter.getComposedSource());
									} else {
										imo.setModel(context.getReferenceSource());
									}
									imo.setSourceReference(refPoint.getSource());
									
									issue.getResolutions().add(imo);
									
									l.error(issue);
									*/
									
									java.util.Map<String,java.io.Serializable> props=
										new java.util.HashMap<String,java.io.Serializable>();
									l.error(mesg, props);
									ret = false;
									
									// Exit loop
									refCount = 0;
								} else {
									refCount--;
								}
							}
		
							// Get ref activity to be compared
							refPoint = innerRefIter.next();
		
							/*
							 * Perform comparison after dealing with
							 * additional or missing activities,
							 * when reached sync point, in case
							 * not fully compaible (i.e. full compare
							 * was set to false to find this inner
							 * sync point)
							 */
							if (mainPoint != null && refPoint != null) {
								CachedScribbleLogger subl=new 
										CachedScribbleLogger();
						
								// Redo comparison with model listener
								if (compareBehaviours(context, mainPoint, refPoint,
										mainiter, refiter, subl, true) == false) {
									ret = false;
								}
												
								//subl.apply(l, innerMainIter.getModelInclude(), null, null);
								
								subl.apply(l);
							}
						} else {
							Behaviour mainPoint=innerMainIter.next();
							Behaviour refPoint=innerRefIter.next();
		
							CachedScribbleLogger subl=new 
									CachedScribbleLogger();
							
							// Redo comparison with model listener
							ret = compareBehaviours(context, mainPoint, refPoint,
									mainiter, refiter, subl, true);
											
							//subl.apply(l, innerMainIter.getModelInclude(), null, null);
							
							subl.apply(l);
						}
		
						// Move iterators to the sync point
						innerMainIter = innerSyncPoint.getMainIterator();
						innerRefIter = innerSyncPoint.getReferenceIterator();
					}
		
					//ret = false;
				}
				
				// Move iterators to the sync point
				mainiter = syncPoint.getMainIterator();
				refiter = syncPoint.getReferenceIterator();
			}
		}
		
		return(ret);
	}
	
	/**
	* This method finds the point in both descriptions where
	* the behaviour is the same (i.e. they are synchronized).
	* 
	* @param context The context
	* @param mainiter The main behaviour iterator
	* @param refiter The reference behaviour iterator
	* @param fullCompare Whether to do a full or basic comparison
	* @param end An optional sync point where this search should
	* 					finish
	* @return The sync point
	*/
	protected SyncPoint findNextSyncPoint(ComparatorContext context,
				BehaviourListIterator mainiter,
				BehaviourListIterator refiter,
				boolean fullCompare, SyncPoint end) {
		SyncPoint ret=null;
		SyncPoint mainSyncPoint=null;
		SyncPoint refSyncPoint=null;
		int mainCount=0;
		int refCount=0;
		int maxMainCount=0;
		int maxRefCount=0;
		
		if (mainiter != null && refiter != null) {
			BehaviourListIterator mainiter2=mainiter.snapshot();
			boolean f_end=false;
			
			while (f_end == false && mainSyncPoint == null && 
								mainiter2.hasNext()) {
				Behaviour main=mainiter2.next();
				
				if (end != null && end.getMain() == main) {
					f_end = true;
				} else {
					BehaviourListIterator refiter2=refiter.snapshot();
					
					refCount = 0;
					
					boolean f_innerEnd=false;
					
					while (f_innerEnd == false && mainSyncPoint == null &&
									refiter2.hasNext()) {
						Behaviour ref=refiter2.next();
						
						if (end != null && end.getReference() == ref) {
							f_innerEnd = true;
						} else {
							CachedScribbleLogger l=new CachedScribbleLogger();//refiter2.getComposedSource());
							
							if (compareBehaviours(context, main, ref,
									mainiter, refiter, l, fullCompare)) {
								
								mainSyncPoint = new SyncPoint(main, ref,
										mainiter2, refiter2, mainCount, refCount);
							} else {
								refCount++;
							}
						}
					}
					
					if (mainSyncPoint == null) {
						mainCount++;
						maxMainCount++;
					}
				}
			}
			
			BehaviourListIterator refiter2=refiter.snapshot();
			refCount = 0;
			f_end = false;
			
			while (f_end == false && refSyncPoint == null &&
							refiter2.hasNext()) {
				Behaviour ref=refiter2.next();
				if (end != null && end.getReference() == ref) {
					f_end = true;
				} else {
					mainiter2 = mainiter.snapshot();
					
					mainCount = 0;
					
					boolean f_innerEnd=false;
					
					while (f_innerEnd == false &&
							refSyncPoint == null && mainiter2.hasNext()) {
						Behaviour main=mainiter2.next();
						
						if (end != null && end.getMain() == main) {
							f_innerEnd = true;
						} else {
							CachedScribbleLogger l=new CachedScribbleLogger();
							
							if (compareBehaviours(context, main, ref,
									mainiter, refiter, l, fullCompare)) {
								
								refSyncPoint = new SyncPoint(main, ref,
										mainiter2, refiter2, mainCount, refCount);
							} else {
								mainCount++;
							}
						}
					}
					
					if (refSyncPoint == null) {
						refCount++;
						maxRefCount++;					
					}
				}
			}
			
			if (mainSyncPoint != null && refSyncPoint != null) {
				if (mainSyncPoint.getTotal() <=
								refSyncPoint.getTotal()) {
					ret = mainSyncPoint;
				} else {
					ret = refSyncPoint;
				}
			} else if (mainSyncPoint != null) {
				ret = mainSyncPoint;
			} else {
				ret = refSyncPoint;
			}
			
			// If no sync point found, but components were remaining
			// in main and/or ref list, then return a sync point with
			// the number of components skipped
			if (ret == null && (maxMainCount+maxRefCount) > 0) {
				ret = new SyncPoint(null, null, null, null, maxMainCount, maxRefCount);
			}
		}
		
		return(ret);
	}
	
	protected boolean compareBehaviours(ComparatorContext context,
				Behaviour mainb, Behaviour refb,
		BehaviourListIterator mainiter, BehaviourListIterator refiter,
		ScribbleLogger l, boolean fullCompare) {
		boolean ret=false;
		
		if (mainb != null && refb != null) {
			// TODO:
			// how do we deal with grouping constructs - 
			// they are behaviours, but if different, then
			// no comparator rule will be found, which will
			// currently default to true - should it return
			// false???
			
			if (fullCompare) {
			
				// Check if multi-path elements
				if (mainb instanceof BehaviourListPaths &&
						refb instanceof BehaviourListPaths) {
					
					ret = compareMultiplePaths(context, (BehaviourListPaths)mainb,
							(BehaviourListPaths)refb,
							mainiter, refiter, l, true);
					
				} else {
					ret = compareSingleComponents(context, mainb, refb,
							mainiter, refiter, l, true);
				}
				
			} else if (mainb instanceof BehaviourListPaths &&
					refb instanceof BehaviourListPaths) {
				ret = context.isComparisonSupported(
						((BehaviourListPaths)mainb).getSourceBehaviour(),
						((BehaviourListPaths)refb).getSourceBehaviour());
			} else {
				
				// Just check that a rule exists for the types
				ret = context.isComparisonSupported(mainb, refb);
			}
		}
		
		return(ret);
	}
	
	protected boolean compareSingleComponents(ComparatorContext context,
				Behaviour mainb, Behaviour refb,
		BehaviourListIterator mainiter, BehaviourListIterator refiter,
		ScribbleLogger l, boolean deep) {
		boolean ret=false;
		
		CachedScribbleLogger dml=new CachedScribbleLogger();
		
		// Add name map to comparator context
		context.pushMainNameMap(mainiter.getNameMap());
		context.pushReferenceNameMap(refiter.getNameMap());
		
		// Do shallow compare, to just check 
		ret = context.compare(mainb, refb, dml, true);
		
		context.popReferenceNameMap();
		context.popMainNameMap();

		/*		
		ModelObject override=null;
		
		if (mainiter.getComposedSource() != null) {
			override = mainiter.getModelInclude();
		}
		
		// Associate reference description information
		java.util.Map<String,Object> props=new java.util.HashMap<String,Object>();
		
		java.util.List<Resolution> resolutions=new java.util.Vector<Resolution>();
		
		ShowReferencedDescription res=new ShowReferencedDescription();
		res.setSourceReference(refb.getSource());
		
		if (refiter.getComposedSource() != null) {
			res.setModel(refiter.getComposedSource());
		} else {
			res.setModel(context.getReferenceSource());
		}
		
		resolutions.add(res);
		
		dml.apply(l, override, props, resolutions);
		*/
		dml.apply(l);
		
		return(ret);
	}
	
	protected boolean compareMultiplePaths(ComparatorContext context,
				BehaviourListPaths mainblp, BehaviourListPaths refblp,
		BehaviourListIterator mainiter, BehaviourListIterator refiter,
				ScribbleLogger l, boolean deep) {
		boolean ret=false;
		
		CachedScribbleLogger dml=new CachedScribbleLogger();
				//mainiter.getComposedSource());
		
		ret = context.compare(mainblp.getSourceBehaviour(),
				refblp.getSourceBehaviour(), dml, false);
		
		if (ret) {
			dml = null;
			
			// Work through paths to find matching pairs and
			// remove them from the lists
			BehaviourList mainlist=null;
			
			while ((mainlist=mainblp.getNext()) != null) {
				
				refblp.reset();
				
				BehaviourList reflist=null;
				boolean pathvalid=false;
				
				do {
					reflist = refblp.getNext();
					
					if (reflist != null) {
						
						// TODO: Need to record model
						// listener messages in temporary
						// place and transfer
						CachedScribbleLogger tmpl=
							new CachedScribbleLogger();//mainiter.getComposedSource());
						
						pathvalid = context.compare(mainlist,
								reflist, tmpl, true);
						
						if (pathvalid) {
							// Remove found list
							refblp.handled(reflist);
						}
					}
				} while (reflist != null && pathvalid == false);
				
				if (pathvalid) {
					mainblp.handled(mainlist);
				}
			}
			
			// Reset main, to find errors in matching
			mainblp.reset();
			
			// This time, the checks will focus on finding
			// errors in the remaining unmatched paths
			while (ret && (mainlist=mainblp.getNext()) != null) {
				
				refblp.reset();
				
				BehaviourList reflist=null;
				
				do {
					reflist = refblp.getNext();
					
					if (reflist != null) {
						
						// TODO: Need to record model
						// listener messages in temporary
						// place and transfer
						dml = new CachedScribbleLogger(); //mainiter.getComposedSource());
								//mainlist.getModelInclude().getReference());
						
						ret = context.compare(mainlist,
								reflist, dml, true);
						
						if (ret == true) {
							dml = null;
						}
					}
				} while (ret && reflist != null);
			}
		
			if (ret) {
				refblp.reset();
				mainblp.reset();
				
				// If reference behaviour list paths still exist, and the
				// main behaviour list paths are not the decision maker,
				// then check the paths for error.
				// NOTE: Checking for main blp being decision maker, for
				// situations where checking composability - so not related
				// to whether ref blp is decision maker (as this will be
				// determined when checking each path), but if main is
				// decision maker, then additional paths are not relevant.
				if (refblp.hasNext() &&
						isMutuallyExclusiveDecisionMaker(mainblp) == false) {
					BehaviourList refb=null;
					
					while ((refb=refblp.getNext()) != null) {
						
						if (isDecisionMaker(refb) == false &&
								refb.getBehaviourList().size() > 0) {
						
							// TODO: Need to check if additional ref
							// path belongs to a conditional multipath
							// construct that is located at the projected
							// role
							
							String mesg=java.util.PropertyResourceBundle.getBundle(
									"org.scribble.conformance.Messages").
									getString("_ADDITIONAL_UNMATCHED_REF_PATHS");
							
							/*
							ModelIssue issue=new ModelIssue(mainblp, mesg);
							
							ShowReferencedDescription res=new ShowReferencedDescription();
							
							if (refiter.getComposedSource() != null) {
								res.setModel(refiter.getComposedSource());
							} else {
								res.setModel(context.getReferenceSource());
							}
							res.setSourceReference(refb.getSource());
							
							issue.getResolutions().add(res);
							
							l.error(issue);
							*/
							
							java.util.Map<String, Serializable> props=
									new java.util.HashMap<String, Serializable>();
							l.error(mesg, props);
							
							ret = false;
						}
					}
				}
				
				if (mainblp.hasNext()&&
						isMutuallyExclusiveDecisionMaker(refblp) == false) {
					BehaviourList src=null;
					
					while ((src=mainblp.getNext()) != null) {
		
						if (isDecisionMaker(src) == false &&
								src.getBehaviourList().size() > 0) {
		
							String mesg=java.util.PropertyResourceBundle.getBundle(
									"org.scribble.conformance.Messages").
									getString("_ADDITIONAL_UNMATCHED_PATHS");
					
							/*
							ModelIssue issue=new ModelIssue(src, mesg);
							
							ShowReferencedDescription res=new ShowReferencedDescription();
							
							if (refiter.getComposedSource() != null) {
								res.setModel(refiter.getComposedSource());
							} else {
								res.setModel(context.getReferenceSource());
							}
							
							issue.getResolutions().add(res);
							
							l.error(issue);
							*/
							
							java.util.Map<String, Serializable> props=
								new java.util.HashMap<String, Serializable>();
							l.error(mesg, props);
						
							ret = false;
						}
					}
					
					//ret = false;
				}
			}
		}
		
		// Copy errors into the main listener
		if (dml != null) {
			/*
			ModelObject override=null;
			
			if (mainiter.getComposedSource() != null) {
				override = mainiter.getModelInclude();
			}
			
			// Associate reference description information
			java.util.Map<String,Object> props=
				new java.util.HashMap<String,Object>();
		
			java.util.List<Resolution> resolutions=new java.util.Vector<Resolution>();
			
			ShowReferencedDescription res=new ShowReferencedDescription();
			res.setSourceReference(refblp.getSource());
		
			if (refiter.getComposedSource() != null) {
				res.setModel(refiter.getComposedSource());
			} else {
				res.setModel(context.getReferenceSource());
			}
			
			resolutions.add(res);
			
			dml.apply(l, override, props, resolutions);
			*/
			
			dml.apply(l);
		}
		
		return(ret);
	}
	
	protected Behaviour getNextBehaviour(BehaviourListIterator iter) {
		Behaviour ret=null;
		
		while (ret == null && iter.hasNext()) {
			Behaviour behaviour=iter.next();
			if ((behaviour instanceof BehaviourListPaths &&
					((BehaviourListPaths)
						behaviour).getSourceBehaviour() instanceof BehaviourList) ||
					behaviour instanceof BehaviourList) {
				//context.isTypeSupported(((BehaviourListPaths)
				//		behaviour).getSourceBehaviour())) ||
				//context.isTypeSupported(behaviour)) {
				ret = behaviour;
			}
		}
		
		return(ret);
	}
	
	/**
	* This method helps to locate the relevant top level behaviour
	* list, where comparison should start from.
	* 
	* @param bl The proposed behaviour list
	* @return The actual top level behaviour list of interest
	*/
	protected BehaviourList getTopLevelBehaviourList(BehaviourList bl) {
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
	
	/**
	* This method determines whether the supplied behaviour
	* is a conditional grouping construct that is the
	* decision maker - i.e. its located role is the same as
	* the projected role associated with the local model
	* definition.
	* 
	* @param b The behaviour
	* @return Whether the behaviour is a conditional decision
	* 						maker
	*/
	protected boolean isConditionalDecisionMaker(Behaviour b) {
		boolean ret=false;
		
		// TODO: Check if conditional and a grouping construct
		// rather than single or multi path. Then make 'if'
		// conditional if it does not have a default 'else'
		// clause
		
		//if (b instanceof SinglePathBehaviour &&
		if (b.isGroupingConstruct() &&
				b.isConditional()) {
			Definition d=b.enclosingDefinition();
			
			if (d != null && d.getLocatedName() != null &&
					d.getLocatedName().getParticipant() != null &&
					b.initiatorParticipants().contains(
							d.getLocatedName().getParticipant())) {
				ret = true;
			}
		}
		
		return(ret);
	}
	
	/**
	* This method determines whether the supplied behaviour
	* is a conditional grouping construct that is the
	* decision maker - i.e. its located role is the same as
	* the projected role associated with the local model
	* definition.
	* 
	* @param b The behaviour
	* @return Whether the behaviour is a conditional decision
	* 						maker
	*/
	protected boolean isMutuallyExclusiveDecisionMaker(BehaviourListPaths blp) {
		boolean ret=false;
		
		if (blp.getSourceBehaviour() instanceof MultiPathBehaviour &&
				((MultiPathBehaviour)blp.getSourceBehaviour()).
							isMutuallyExclusivePaths()) {
			Definition d=blp.getSourceBehaviour().enclosingDefinition();
			
			if (d != null && d.getLocatedName() != null &&
					d.getLocatedName().getParticipant() != null &&
					blp.getSourceBehaviour().initiatorParticipants().contains(
							d.getLocatedName().getParticipant())) {
				ret = true;
			}
		}
		
		return(ret);
	}
	
	/**
	* This method determines whether the supplied behaviour
	* is a block that is the decision maker - i.e. it is
	* located role is the same as the projected role associated 
	* with the local model definition.
	* 
	* @param b The behaviour list
	* @return Whether the behaviour is a block initiated at the
	* 					located role.
	*/
	protected boolean isDecisionMaker(BehaviourList b) {
		boolean ret=false;
		
		Definition d=b.getBlock().enclosingDefinition();
			
		if (d != null && d.getLocatedName() != null &&
				d.getLocatedName().getParticipant() != null &&
				b.getBlock().initiatorParticipants().contains(
							d.getLocatedName().getParticipant())) {
			ret = true;
		}
		
		return(ret);
	}
}
