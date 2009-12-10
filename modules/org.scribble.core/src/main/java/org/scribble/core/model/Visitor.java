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
 * 18 Nov 2007 : Initial version created by gary
 */
package org.scribble.core.model;

/**
 * This interface represents a visitor which can be used
 * to traverse a model.
 */
public interface Visitor {

	/**
	 * This method visits the supplied model object.
	 * 
	 * @param obj The model object
	 * @return Whether contained objects should be visited
	 */
	public boolean visit(ModelObject obj);
	
}
