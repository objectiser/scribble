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

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

import org.scribble.core.model.*;
import org.scribble.protocol.model.*;

public class ProtocolTreeAdapter implements org.antlr.runtime.tree.TreeAdaptor {

	@Override
	public void addChild(Object arg0, Object arg1) {
		System.out.println("ADD "+arg0+" "+arg1);
	}

	@Override
	public Object becomeRoot(Object arg0, Object arg1) {
		System.out.println("BECOME_ROOT "+arg0+" "+arg1);
		return null;
	}

	@Override
	public Object becomeRoot(Token arg0, Object arg1) {
		System.out.println("BECOME_ROOT2 "+arg0+" "+arg1);
		return null;
	}

	@Override
	public Object create(Token arg0) {
		Object ret=null;
		
		System.out.println("CREATE TOKEN "+arg0);
		
		if (arg0.getText().equals("namespace")) {
			ret = new Namespace();
		}
		
		return(ret);
	}

	@Override
	public Object create(int arg0, Token arg1) {
		System.out.println("CREATE "+arg0+" TOKEN "+arg1);
		return null;
	}

	@Override
	public Object create(int arg0, String arg1) {
		System.out.println("CREATE2 "+arg0+" STR "+arg1);
		return null;
	}

	@Override
	public Object create(int arg0, Token arg1, String arg2) {
		System.out.println("CREATE3 "+arg0+" TOKEN "+arg1+" STR "+arg2);
		return null;
	}

	@Override
	public Object deleteChild(Object arg0, int arg1) {
		System.out.println("DELETE "+arg0+" "+arg1);
		return null;
	}

	@Override
	public Object dupNode(Object arg0) {
		System.out.println("DUPNODE "+arg0);
		return null;
	}

	@Override
	public Object dupTree(Object arg0) {
		System.out.println("DUPTREE "+arg0);
		return null;
	}

	@Override
	public Object errorNode(TokenStream arg0, Token arg1, Token arg2,
			RecognitionException arg3) {
		System.out.println("ERRORNODE "+arg0);
		return null;
	}

	@Override
	public Object getChild(Object arg0, int arg1) {
		System.out.println("GET CHILOD "+arg0+" "+arg1);
		return null;
	}

	@Override
	public int getChildCount(Object arg0) {
		System.out.println("GET CHILD COUNT "+arg0);
		return 0;
	}

	@Override
	public int getChildIndex(Object arg0) {
		System.out.println("GET CHILD INDEX "+arg0);
		return 0;
	}

	@Override
	public Object getParent(Object arg0) {
		System.out.println("GET PARENT "+arg0);
		return null;
	}

	@Override
	public String getText(Object arg0) {
		System.out.println("GET TEXT "+arg0);
		return null;
	}

	@Override
	public Token getToken(Object arg0) {
		System.out.println("GET TOKEN "+arg0);
		return null;
	}

	@Override
	public int getTokenStartIndex(Object arg0) {
		System.out.println("GET TOKEN START INDEX "+arg0);
		return 0;
	}

	@Override
	public int getTokenStopIndex(Object arg0) {
		System.out.println("GET TOKEN STOP INDEX "+arg0);
		return 0;
	}

	@Override
	public int getType(Object arg0) {
		System.out.println("GET TYPE "+arg0);
		return 0;
	}

	@Override
	public int getUniqueID(Object arg0) {
		System.out.println("GET UNIQUE ID "+arg0);
		return 0;
	}

	@Override
	public boolean isNil(Object arg0) {
		System.out.println("IS NIL "+arg0);
		return false;
	}

	@Override
	public Object nil() {
		System.out.println("NIL");
		return null;
	}

	@Override
	public void replaceChildren(Object arg0, int arg1, int arg2, Object arg3) {
		System.out.println("REPLACE CHILD "+arg0+" "+arg1+" "+arg2+" "+arg3);
	}

	@Override
	public Object rulePostProcessing(Object arg0) {
		System.out.println("RULE POST PROCESSING "+arg0);
		return null;
	}

	@Override
	public void setChild(Object arg0, int arg1, Object arg2) {
		System.out.println("SET CHILD "+arg0+" "+arg1+" "+arg2);
	}

	@Override
	public void setChildIndex(Object arg0, int arg1) {
		System.out.println("SET CHILD INDEX "+arg0+" "+arg1);		
	}

	@Override
	public void setParent(Object arg0, Object arg1) {
		System.out.println("SET PARENT "+arg0+" "+arg1);
	}

	@Override
	public void setText(Object arg0, String arg1) {
		System.out.println("SET TEXT "+arg0+" "+arg1);
	}

	@Override
	public void setTokenBoundaries(Object arg0, Token arg1, Token arg2) {
		System.out.println("SET TOEN BOUNDARIES "+arg0+" "+arg1+" "+arg2);
	}

	@Override
	public void setType(Object arg0, int arg1) {
		System.out.println("SET TYPE "+arg0+" "+arg1);
	}

}
