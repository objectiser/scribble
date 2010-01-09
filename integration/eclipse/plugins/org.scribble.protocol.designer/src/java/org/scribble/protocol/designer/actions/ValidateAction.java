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
package org.scribble.protocol.designer.actions;

import org.eclipse.core.resources.*;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.scribble.protocol.designer.DesignerServices;
import org.scribble.protocol.designer.logger.EclipseScribbleLogger;
import org.scribble.protocol.model.ProtocolModel;

public class ValidateAction implements IObjectActionDelegate {

	/**
	 * This method implements the action's run method.
	 * 
	 * @param action The action
	 */
	public void run(IAction action) {
		
		if (m_selection instanceof StructuredSelection) {
			StructuredSelection sel=(StructuredSelection)m_selection;
			
			IResource res=(IResource)sel.getFirstElement();
			
			if (res instanceof IFile) {
				
				try {
					EclipseScribbleLogger logger=
							new EclipseScribbleLogger((IFile)res);
					
					java.io.InputStream is=((IFile)res).getContents();
					
					ProtocolModel model=
						DesignerServices.getProtocolParser().parse(is,
								logger);
					
					// TODO: Check if error occurred during parsing
					// possibly by using a logger proxy that counts
					// errors logged
					if (model != null && logger.hasErrorOccurred() == false) {
						DesignerServices.getValidationManager().validate(model,
										logger);
					}
					
					logger.finished();
					
				} catch(Exception e) {
					
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * This method indicates that the selection has changed.
	 * 
	 * @param action The action
	 * @param selection The selection
	 */
	public void selectionChanged(IAction action,
            ISelection selection) {
		m_selection = selection;
	}

	/**
	 * This method sets the currently active workbench part.
	 * 
	 * @param action The action
	 * @param targetPart The active workbench part
	 */
	public void setActivePart(IAction action,
            IWorkbenchPart targetPart) {
		m_targetPart = targetPart;
	}
	
	/**
	 * This method is used to report a warning.
	 * 
	 * @param mesg The warning message
	 */
	public void warn(String mesg) {
		
		MessageBox mbox=new MessageBox(m_targetPart.getSite().getShell(),
				SWT.ICON_WARNING|SWT.OK);
		mbox.setMessage(mesg);
		mbox.open();
	}
	
	private ISelection m_selection=null;
    private IWorkbenchPart m_targetPart=null;
}
