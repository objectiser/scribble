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
package org.scribble.protocol.parser.antlr;

import java.beans.PropertyDescriptor;
import java.util.logging.Logger;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

import org.scribble.protocol.model.*;

public class ProtocolTreeAdaptor implements org.antlr.runtime.tree.TreeAdaptor {

	private static final String ACTIVITY_RULE_NAME = "activityDef";
	private static java.util.Map<String,String> m_propertyToken=
		new java.util.HashMap<String, String>();
	private static java.util.Map<String,Class<?>> m_tokenClass=
		new java.util.HashMap<String, Class<?>>();
	private static java.util.Map<String,Class<?>> m_listClass=
		new java.util.HashMap<String, Class<?>>();
	private static java.util.Map<String,Class<?>> m_parserGroupingRuleClass=
		new java.util.HashMap<String, Class<?>>();
	private static java.util.List<String> m_clearTokenListRules=
		new java.util.Vector<String>();
	
	private ScribbleProtocolParser m_parser=null;
	private Token m_currentToken=null;
	
	private ProtocolModel m_model=null;
	
	private static final Logger _log=Logger.getLogger(ProtocolTreeAdaptor.class.getName());
	
	static {
		// The map of root tokens, that begin a grammer
		// rule, and the model class they are associated
		// with
		m_tokenClass.put("namespace", Namespace.class);
		m_tokenClass.put("import", Import.class);
		m_tokenClass.put("protocol", Protocol.class);
		m_tokenClass.put("participant", ParticipantList.class);
		m_tokenClass.put("choice", Choice.class);
		m_tokenClass.put("when", WhenBlock.class);
		m_tokenClass.put("parallel", Parallel.class);
		m_tokenClass.put("repeat", Repeat.class);
		m_tokenClass.put("optional", Optional.class);
		m_tokenClass.put("raise", Raise.class);
		m_tokenClass.put("try", TryEscape.class);
		m_tokenClass.put("catch", CatchBlock.class);
		m_tokenClass.put("interrupt", InterruptBlock.class);
		m_tokenClass.put("run", Run.class);

		// Clear token list - determines whether prior to processing
		// a list of tokens, the 'current token' should be cleared
		m_clearTokenListRules.add("boundParameter");

		// This may define the model object that should be
		// created after processing the named grammer rule
		m_parserGroupingRuleClass.put("qualifiedName", String.class);
		m_parserGroupingRuleClass.put("blockDef", Block.class);
		m_parserGroupingRuleClass.put("interactionDef", Interaction.class);
		m_parserGroupingRuleClass.put("interactionSignatureDef", MessageSignature.class);
		m_parserGroupingRuleClass.put("typeReferenceDef", TypeReference.class);
		m_parserGroupingRuleClass.put("participantName", Participant.class);
		m_parserGroupingRuleClass.put("participantDef", Participant.class);
		m_parserGroupingRuleClass.put("locatedNameDef", LocatedName.class);
		m_parserGroupingRuleClass.put("protocolRefDef", ProtocolReference.class);
		m_parserGroupingRuleClass.put("boundParameter", DeclarationBinding.class);
		m_parserGroupingRuleClass.put("inlineProtocolDef", Protocol.class);
		m_parserGroupingRuleClass.put("declarationName", String.class);
		
		// When a particular class has multiple properties of the
		// same type, then a preceding token must be used to
		// determine which property to set. This map provides the
		// mapping between the property name and the token.
		m_propertyToken.put("fromParticipant", "from");
		m_propertyToken.put("toParticipant", "to");
		m_propertyToken.put("boundName", "");
		m_propertyToken.put("localName", "=");
		
		// Defines the list element base type associated with a
		// property name
		m_listClass.put("imports", Import.class);
		m_listClass.put("contents", Activity.class);
		m_listClass.put("participants", Participant.class);
		m_listClass.put("types", TypeReference.class);
		m_listClass.put("blocks", Block.class);
		m_listClass.put("whenBlocks", WhenBlock.class);
		m_listClass.put("bindings", DeclarationBinding.class);
	}
	
	public ProtocolModel getProtocolModel() {
		return(m_model);
	}
	
	public void setParser(ScribbleProtocolParser parser) {
		m_parser = parser;
	}
	
	@Override
	public Object create(Token token) {
		Object ret=token;
		
		_log.finer("CREATE TOKEN "+token);
		
		Class<?> cls=m_tokenClass.get(token.getText());
		
		_log.info("Token class for '"+token.getText()+
				"' is: "+cls);

		if (cls != null) {
			try {
				ret = cls.newInstance();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if (ret == token && token.getType() != ScribbleProtocolParser.ID) {
			_log.info("Set current token="+token);
			m_currentToken = token;
		}
		
		return(ret);
	}
	
	@Override
	public Object dupNode(Object arg0) {
		_log.finest("DUPNODE "+arg0);
		return null;
	}

	@Override
	public int getChildIndex(Object arg0) {
		_log.finest("GET CHILD INDEX "+arg0);
		return 0;
	}

	@Override
	public Object getParent(Object arg0) {
		_log.finest("GET PARENT "+arg0);
		return null;
	}

	@Override
	public Token getToken(Object arg0) {
		_log.finest("GET TOKEN "+arg0);
		return null;
	}

	@Override
	public int getTokenStartIndex(Object arg0) {
		_log.finest("GET TOKEN START INDEX "+arg0);
		return 0;
	}

	@Override
	public int getTokenStopIndex(Object arg0) {
		_log.finest("GET TOKEN STOP INDEX "+arg0);
		return 0;
	}

	@Override
	public void setChildIndex(Object arg0, int arg1) {
		_log.finest("SET CHILD INDEX "+arg0+" "+arg1);		
	}

	@Override
	public void replaceChildren(Object arg0, int arg1, int arg2, Object arg3) {
		_log.finest("REPLACE CHILD "+arg0+" "+arg1+" "+arg2+" "+arg3);
	}

	@Override
	public void setParent(Object arg0, Object arg1) {
		_log.finest("SET PARENT "+arg0+" "+arg1);
	}

	@Override
	public void setTokenBoundaries(Object arg0, Token arg1, Token arg2) {
		_log.finest("SET TOKEN BOUNDARIES "+arg0+" "+arg1+" "+arg2);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addChild(Object parent, Object child) {
		_log.info("Add child: parent="+parent+" child="+child);
		
		// Check if child is a list
		if (isNil(child)) {
			java.util.List<Object> nil=
				(java.util.List<Object>)child;
			
			if (m_parser != null &&
					m_parser.getRuleInvocationStack().size() > 0) {
				String ruleName=(String)m_parser.getRuleInvocationStack().get(
						m_parser.getRuleInvocationStack().size()-1);
								
				// Before processing the sublist of tokens, clear
				// the current token - needed for cases like the
				// 'boundParameter' syntax rule
				if (nil.size() > 0 && ruleName != null && m_clearTokenListRules.contains(ruleName)) {
					_log.info("Clear current token before processing sublist of tokens: rule="+ruleName);
					m_currentToken = null;
				}
			}
				
			// Check if ID token
			StringBuffer buf=new StringBuffer();
			
			for (int i=0; i < nil.size(); i++) {
				
				if (nil.get(i) instanceof Token &&
						(((Token)nil.get(i)).getType() == ScribbleProtocolParser.ID ||
							((Token)nil.get(i)).getType() == ScribbleProtocolParser.FULLSTOP)) {
					buf.append(((Token)nil.get(i)).getText());
				} else {
					if (buf.length() > 0) {
						addChild(parent, buf.toString());
						
						buf = new StringBuffer();
					}
					
					if (nil.get(i) instanceof Token) {
						m_currentToken = (Token)nil.get(i);
						_log.info("Set current token: "+m_currentToken);
					}

					addChild(parent, nil.get(i));
				}				
			}
			
			if (buf.length() > 0) {
				addChild(parent, buf.toString());
			}
		} else if (parent != null && child != null) {

			if (isNil(parent)) {
				java.util.List<Object> nil=
					(java.util.List<Object>)parent;

				_log.finest("Add child: "+child);
				nil.add(child);
				
			} else {
				_log.finest("Determine if can be set by property descriptor");
				
				try {
					// Get property descriptors for parent class
					java.beans.BeanInfo bi=
						java.beans.Introspector.getBeanInfo(parent.getClass());
					
					PropertyDescriptor[] pds=bi.getPropertyDescriptors();
					PropertyDescriptor pd=null;
					
					for (int i=0; i < pds.length; i++) {
						
						if (pds[i].getPropertyType().isAssignableFrom(child.getClass()) &&
								pds[i].getName().equals("parent") == false) {
							
							String token=m_propertyToken.get(pds[i].getName());
							
							if ((token == null ||
									(token.length() == 0 && m_currentToken == null) ||
									(m_currentToken != null && 
										token.equals(m_currentToken.getText()))) &&
										pds[i].getWriteMethod() != null) {
								pd = pds[i];
							}
						} else if (pds[i].getPropertyType() == java.util.List.class) {
							
							Class<?> listElementCls=m_listClass.get(pds[i].getName());
							
							if (listElementCls != null &&
									listElementCls.isAssignableFrom(child.getClass())) {
								java.util.List list=(java.util.List)
										pds[i].getReadMethod().invoke(parent);
								
								_log.info("Adding "+child+" to list: "+
										list+" on parent "+parent);
								list.add(child);
							}
						}
					}
					
					// If property descriptor is not for 'parent' property,
					// as this would match any 'child', and the property
					// must have a set method
					if (pd != null && pd.getWriteMethod() != null) {
						
						_log.info("Set property '"+pd.getName()+
								"' on="+parent+" (class="+parent.getClass()+") to="+child);
						
						pd.getWriteMethod().invoke(parent, child);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Object becomeRoot(Object newRoot, Object oldRoot) {
		_log.finest("Become root "+newRoot+" "+oldRoot);
		
		if (oldRoot != null) {
			addChild(newRoot, oldRoot);
			
			if (isNil(newRoot)) {
				java.util.List<?> nil=
					(java.util.Vector<?>)newRoot;
				
				if (nil.size() == 1) {
					newRoot = nil.get(0);
				}
			}
		}
		
		return(newRoot);
	}

	@Override
	public Object becomeRoot(Token arg0, Object arg1) {
		_log.finest("BECOME_ROOT2 "+arg0+" "+arg1);
		return null;
	}

	@Override
	public Object create(int arg0, Token arg1) {
		_log.finest("CREATE "+arg0+" TOKEN "+arg1);
		return null;
	}

	@Override
	public Object create(int arg0, String arg1) {
		_log.finest("CREATE2 "+arg0+" STR "+arg1);
		return null;
	}

	@Override
	public Object create(int arg0, Token arg1, String arg2) {
		_log.finest("CREATE3 "+arg0+" TOKEN "+arg1+" STR "+arg2);
		return null;
	}

	@Override
	public Object deleteChild(Object arg0, int arg1) {
		_log.finest("DELETE "+arg0+" "+arg1);
		return null;
	}

	@Override
	public Object dupTree(Object arg0) {
		_log.finest("DUPTREE "+arg0);
		return null;
	}

	@Override
	public Object errorNode(TokenStream arg0, Token arg1, Token arg2,
			RecognitionException arg3) {
		_log.finest("ERRORNODE "+arg0+" "+arg1+" "+arg2+" "+arg3);
		return null;
	}

	@Override
	public Object getChild(Object arg0, int arg1) {
		_log.finest("GET CHILD "+arg0+" "+arg1);
		return null;
	}

	@Override
	public int getChildCount(Object arg0) {
		_log.finest("GET CHILD COUNT "+arg0);
		return 0;
	}

	@Override
	public String getText(Object arg0) {
		_log.finest("GET TEXT "+arg0);
		return null;
	}

	@Override
	public int getType(Object arg0) {
		_log.finest("GET TYPE "+arg0);
		return 0;
	}

	@Override
	public int getUniqueID(Object arg0) {
		_log.finest("GET UNIQUE ID "+arg0);
		return 0;
	}

	@Override
	public boolean isNil(Object obj) {
		return(obj instanceof java.util.List<?>);
	}

	@Override
	public Object nil() {
		Object ret=null;
		
		if (m_model == null) {
			m_model = new ProtocolModel();
			ret = m_model;
		} else {
			ret = new java.util.Vector<Object>();
		}
		
		return(ret);
	}

	@Override
	public Object rulePostProcessing(Object root) {
		Object ret=root;
		
		_log.finest("RULE POST PROCESSING "+root);

		// Check if intermediate node required
		if (m_parser != null &&
				m_parser.getRuleInvocationStack().size() > 0) {
			String ruleName=(String)m_parser.getRuleInvocationStack().get(
					m_parser.getRuleInvocationStack().size()-1);
			
			Class<?> cls = m_parserGroupingRuleClass.get(ruleName);
			
			_log.finest("Parser grouping rule class="+cls);
			
			// Check if rule invocation is associated with activity
			if (ruleName.equals(ACTIVITY_RULE_NAME)) {
				_log.fine("Reset current token");
				m_currentToken = null;
			}
			
			if (cls != null) {
				
				if (cls == String.class) {
					
					if (isNil(root)) {
						java.util.List<?> nil=
							(java.util.List<?>)root;
						StringBuffer buf=new StringBuffer();
						
						for (int i=0; i < nil.size(); i++) {
							if (nil.get(i) instanceof Token) {
								buf.append(((Token)nil.get(i)).getText());
							} else {
								buf.append(nil.get(i).toString());
							}
						}
						
						ret = buf.toString();
					}
				} else {
					try {
						Object newRoot=cls.newInstance();
	
						_log.finest("New root is: "+newRoot);
						
						addChild(newRoot, root);
							
						ret = newRoot;
	
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if (isNil(ret)) {
			java.util.List<?> nil=
					(java.util.List<?>)ret;
			
			if (nil.size() == 1) {
				return(nil.get(0));
			} else {
				return(nil);
			}
		}
		
		return(ret);
	}

	@Override
	public void setChild(Object arg0, int arg1, Object arg2) {
		_log.finest("SET CHILD "+arg0+" "+arg1+" "+arg2);
	}

	@Override
	public void setText(Object arg0, String arg1) {
		_log.finest("SET TEXT "+arg0+" "+arg1);
	}

	@Override
	public void setType(Object arg0, int arg1) {
		_log.finest("SET TYPE "+arg0+" "+arg1);
	}
}
