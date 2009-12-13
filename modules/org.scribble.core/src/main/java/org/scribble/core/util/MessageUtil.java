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
package org.scribble.core.util;

/**
 * This class provides utility functions related to
 * messages.
 * 
 */
public class MessageUtil {

	/**
	 * This method formats the message associated with the supplied
	 * bundle and key using the supplied parameters.
	 * 
	 * @param bundle The bundle of message resources
	 * @param key The message key
	 * @param params The list of parameters
	 * @return The formatted text
	 */
	public static String format(java.util.ResourceBundle bundle, String key,
						String[] params) {
		String ret=null;
		
		String mesg=bundle.getString(key);
	
		ret = format(mesg, params);
					
		return(ret);
	}
	
	/**
	 * This method formats the supplied text using the supplied
	 * parameters.
	 * 
	 * @param mesg The message text
	 * @param params The list of parameters
	 * @return The formatted text
	 */
	public static String format(String mesg, String[] params) {
		
		String ret="";
		int pos=0;
		int prev=0;
		String nextToken=INDEX_START;
		
		while ((pos=mesg.indexOf(nextToken, pos)) != -1) {
			String token=mesg.substring(prev, pos);
			
			if (nextToken == INDEX_START) {
				ret += token;
				nextToken = INDEX_END;
			} else if (nextToken == INDEX_END){
				int index=-1;
				
				try {
					index = Integer.valueOf(token).intValue();
				} catch(Exception e) {
					// Ignore
				}
				
				if (params != null && index >= 0 &&
						index < params.length) {
					ret += params[index];
				} else {
					ret += INDEX_START+index+INDEX_END;
				}
				
				nextToken = INDEX_START;
			}
			
			prev = pos+1;
		}
		
		if (prev != -1) {
			ret += mesg.substring(prev);
		}
		
		return(ret);
	}

	public static final String INDEX_START="{";
	public static final String INDEX_END="}";
}
