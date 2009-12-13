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
package org.scribble.designer.editor.outliner;

import org.eclipse.swt.graphics.Image;
import org.scribble.core.model.*;
import org.scribble.designer.editor.ScribbleImages;

/**
 * This is the abstract base implementation of the ModelOutliner
 * interface, responsible for the model objects in the generic
 * Scribble model package.
 */
public class DefaultModelOutliner implements ModelOutliner {

	/**
	 * This method returns the label to be displayed
	 * for the supplied object.
	 * 
	 * @param ref The model reference
	 * @param obj The object
	 * @return The label
	 */
	public String getLabel(ModelReference ref, Object obj) {
		String ret=null;
		
		
		if (ret == null) {
			
			if (obj instanceof org.scribble.core.model.Declaration) {
				ret = ((org.scribble.core.model.Declaration)obj).getName();
			}
		}
		
		return(ret);
	}
	
	/**
	 * This method returns an optional image associated with
	 * the supplied object.
	 * 
	 * @param ref The model reference
	 * @param obj The object
	 * @return The image, or null if no image to display
	 */
	public org.eclipse.swt.graphics.Image getImage(ModelReference ref, Object obj) {
		Image ret=null;

		// TODO: Check derived language specific outliners

		if (ret == null) {
			
			if (obj instanceof org.scribble.core.model.Declaration) {
				ret = ScribbleImages.getImage("declaration.png");
			} else if (obj instanceof org.scribble.core.model.Activity) {
				ret = ScribbleImages.getImage("activity.png");
			}
		}
		
		return(ret);		
	}
	
	/**
	 * This method returns the list of children associated
	 * with the supplied object.
	 * 
	 * @param ref The model reference
	 * @param obj The object
	 * @return The list of child objects
	 */
	public java.util.List<Object> getChildren(ModelReference ref, Object obj) {
		java.util.List<Object> ret=null;

		// TODO: Check language specific outliners
		
		if (ret == null) {
			ret = new java.util.Vector<Object>();
		}
		
		return(ret);		
	}
	
	/**
	 * This method determines whether the supplied object has
	 * children.
	 * 
	 * @param ref The model reference
	 * @param obj The object
	 * @return Whether the object has children
	 */
	public boolean hasChildren(ModelReference ref, Object obj) {
		boolean ret=false;
		
		ret = getChildren(ref, obj).size() > 0;
		
		return(ret);
	}
}
