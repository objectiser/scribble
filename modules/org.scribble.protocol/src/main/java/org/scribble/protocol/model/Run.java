/*
 * Copyright 2007 Pi4 Technologies Ltd
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
 *
 * Change History:
 * 1 Nov 2007 : Initial version created by gary
 */
package org.scribble.protocol.model;

/**
 * This class represents the Run construct.
 * 
 */
public class Run extends Compose {

	private static final long serialVersionUID = 7877693795925137333L;

	/**
	 * This is the default constructor.
	 * 
	 */
	public Run() {
	}
	
	/**
	 * This method determines whether the compose construct
	 * is asynchronous.
	 * 
	 * @return Whether the compose is asynchronous
	 */
	public boolean isAsynchronous() {
		return(false);
	}
}
