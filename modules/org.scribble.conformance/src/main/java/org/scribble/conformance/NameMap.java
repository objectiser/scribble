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

/**
 * This interface represents a component that maps names
 * to other previously used names.
 */
public interface NameMap {

	/**
	 * This method returns the name that has been mapped
	 * to the supplied name. If the supplied name has
	 * no mapping, then the same name will be returned.
	 * 
	 * @param name The current name
	 * @return The original name
	 */
	public String getName(String name);
	
}
