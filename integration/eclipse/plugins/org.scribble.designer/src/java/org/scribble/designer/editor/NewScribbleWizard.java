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
package org.scribble.designer.editor;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.*;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import org.eclipse.core.resources.*;

import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

import org.eclipse.core.runtime.Path;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.scribble.protocol.model.ModelReference;
import org.scribble.protocol.model.Notation;

/**
 * This class provides the wizard responsible for creating
 * new Protocol Global definitions.
 */
public class NewScribbleWizard extends Wizard implements INewWizard {

    /**
     * This method initializes the wizard.
     * 
     * @param workbench The workbench
     * @param selection The selected resource
     */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		m_workbench = workbench;
		m_selection = selection;
        setWindowTitle("New Scribble Wizard");
        
        m_notations.add(new org.scribble.protocol.model.ProtocolNotation());
	}
	
	/**
	 * This method is invoked when the new CDL object model
	 * should be created.
	 */
	public boolean performFinish() {
		try {
			// Remember the file.
			//
			final IFile modelFile = getModelFile();

			// Do the work within an operation.
			//
			WorkspaceModifyOperation operation =
				new WorkspaceModifyOperation() {
					protected void execute(IProgressMonitor progressMonitor) {
						try {
							
							// Check that project has scribble nature
							/*
							IProject proj=modelFile.getProject();
							if (proj.hasNature("org.scribble.nature") == false) {
								IProjectDescription description = proj.getDescription();
								String[] natures = description.getNatureIds();
								String[] newNatures = new String[natures.length + 1];
								System.arraycopy(natures, 0, newNatures, 0, natures.length);
								newNatures[natures.length] = "org.scribble.nature";
								description.setNatureIds(newNatures);
								proj.setDescription(description, null);
							}
							*/
							
							byte[] b=new byte[0];
							
							Notation notation=m_notations.get(m_notationSelection.getNotationIndex());
							
							// Identify the model reference from the resource
							org.eclipse.core.runtime.IPath path=modelFile.getFullPath();
							org.eclipse.core.runtime.IPath fqnPath=path.removeFirstSegments(1);
							
							String[] segments=fqnPath.segments();
							
							String local=segments[segments.length-1];
							
							String namespace="";
							
							for (int i=0; i < segments.length-1; i++) {
								if (i > 0) {
									namespace += ".";
								}
								namespace += segments[i];
							}
							
							String type=null;
							String located=null;
							
							if (local != null) {
								int nindex=local.lastIndexOf('.');
								if (nindex != -1) {
									type = local.substring(nindex+1);
									local = local.substring(0, nindex);

									int pindex=local.lastIndexOf(ModelReference.LOCATED_REFERENCE_SEPARATOR);
									if (pindex != -1) {
										located = local.substring(pindex+1);
										local = local.substring(0, pindex);
									}
								}
							}
							
							String name=namespace;
							
							if (name.length() > 0) {
								name += '.';
							}
							
							name += local;
							
							if (located != null) {
								name += ModelReference.LOCATED_REFERENCE_SEPARATOR + located;
							}
							
							ModelReference ref=new ModelReference(name);
							
							String initDesc=notation.getInitialDescription(ref);
							
							if (initDesc != null) {
								b = initDesc.getBytes();
							} else {
								b = "".getBytes();
							}
							
							java.io.ByteArrayInputStream bis=new java.io.ByteArrayInputStream(b);
							
							modelFile.create(bis, true, progressMonitor);
							
							bis.close();
							
						} catch (Exception e) {
							org.scribble.designer.osgi.Activator.logError(e.getMessage(), e);
						} finally {
							progressMonitor.done();
						}
					}
				};

			getContainer().run(false, false, operation);

			// Select the new file resource in the current view.
			//
			IWorkbenchWindow workbenchWindow =
			    m_workbench.getActiveWorkbenchWindow();
			IWorkbenchPage page = workbenchWindow.getActivePage();
			final IWorkbenchPart activePart = page.getActivePart();
			if (activePart instanceof ISetSelectionTarget) {
				final ISelection targetSelection = new StructuredSelection(modelFile);
				getShell().getDisplay().asyncExec
					(new Runnable() {
						 public void run() {
							 ((ISetSelectionTarget)activePart).selectReveal(targetSelection);
						 }
					 });
			}

			// Open an editor on the new file.
			//
			try {
				org.eclipse.ui.IEditorDescriptor ed=
					m_workbench.getEditorRegistry().getDefaultEditor(modelFile.getFullPath().toString());
				
				if (ed != null) {
					page.openEditor(new FileEditorInput(modelFile),
										ed.getId());
				}
			}
			catch (PartInitException exception) {
				MessageDialog.openError(workbenchWindow.getShell(),
						"Open Error", exception.getMessage());
				return false;
			}

			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			org.scribble.designer.osgi.Activator.logError(e.getMessage(), e);
			return false;
		}
	}

    /**
     * Get the file from the page.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IFile getModelFile() {
        return m_newFileCreationPage.getModelFile();
    }

    /**
     * The framework calls this to create the contents of the wizard.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void addPages() {
    	
    	m_notationSelection = new NotationSelectionPage(m_notations);
    	
        m_newFileCreationPage = new ScribbleNewFileCreationPage("Whatever", m_selection);
        m_newFileCreationPage.setTitle("Scribble Definition");
        m_newFileCreationPage.setDescription("Create a new Scribble Definition");

        addPage(m_notationSelection);
        addPage(m_newFileCreationPage);
        
        initFileCreationPage();
    }
    
    protected void initFileCreationPage() {
        String defaultModelBaseFilename = "My";
        
        Notation notation=m_notations.get(m_notationSelection.getNotationIndex());
        
        String defaultModelFilenameExtension = notation.getCode();
        String modelFilename = defaultModelBaseFilename + "." + defaultModelFilenameExtension;

        // Create a page, set the title, and the initial model file name.
        //
        m_newFileCreationPage.setFileName(modelFilename);

        // Try and get the resource selection to determine a current directory for the file dialog.
        //
        if (m_selection != null && !m_selection.isEmpty()) {
            // Get the resource...
            //
            Object selectedElement = m_selection.iterator().next();
            if (selectedElement instanceof IResource) {
                // Get the resource parent, if its a file.
                //
                IResource selectedResource = (IResource)selectedElement;
                if (selectedResource.getType() == IResource.FILE) {
                    selectedResource = selectedResource.getParent();
                }

                // This gives us a directory...
                //
                if (selectedResource instanceof IFolder || selectedResource instanceof IProject) {
                    // Set this for the container.
                    //
                    m_newFileCreationPage.setContainerFullPath(selectedResource.getFullPath());

                    // Make up a unique new name here.
                    //
                    for (int i = 1; ((IContainer)selectedResource).findMember(modelFilename) != null; ++i) {
                        modelFilename = defaultModelBaseFilename + i + "." + defaultModelFilenameExtension;
                    }
                    m_newFileCreationPage.setFileName(modelFilename);
                }
            }
        }
    }

    private IWorkbench m_workbench=null;
	private IStructuredSelection m_selection=null;
	private java.util.List<org.scribble.protocol.model.Notation> m_notations=
			new java.util.Vector<org.scribble.protocol.model.Notation>();
	private ScribbleNewFileCreationPage m_newFileCreationPage=null;
	private NotationSelectionPage m_notationSelection=null;
	
	/**
	 * This class represents the wizard page for selecting
	 * a notation.
	 */
	public class NotationSelectionPage extends org.eclipse.jface.wizard.WizardPage {

		/**
		 * Constructor for the notation selection page.
		 * 
		 * @param notations The list of notations
		 */
		public NotationSelectionPage(java.util.List<Notation> notations) {
			super("Notation Selection");
			setTitle("Notation Selection");
			setDescription("Select the notation for the description to be created");
			setPageComplete(true);
			
			m_notations = notations;
		}

		/**
		 * Create the control for the selection page.
		 * 
		 * @param parent The parent component
		 */
		public void createControl(Composite parent) {
		    
			// Create the composite component
			Composite composite = new Composite(parent, SWT.NONE);
			composite.setLayout(new GridLayout());
			composite.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_FILL));
			
			GridData gd=null;

			Group servgroup=new Group(composite, SWT.H_SCROLL|SWT.V_SCROLL);
			
			gd=new GridData();
			gd.horizontalAlignment = SWT.FILL;
			gd.verticalAlignment = SWT.FILL;
			//gd.horizontalSpan = 1;
			//gd.widthHint = 700;
			gd.grabExcessHorizontalSpace = true;
			gd.grabExcessVerticalSpace = true;
			servgroup.setLayoutData(gd);
			
			GridLayout layout = new GridLayout();
			layout.numColumns = 1;
			servgroup.setLayout(layout);
			
			m_notationList =
				new org.eclipse.swt.widgets.Combo(servgroup,
									SWT.H_SCROLL|SWT.V_SCROLL);
			
			m_notationList.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					initFileCreationPage();
				}

				public void widgetSelected(SelectionEvent e) {
					initFileCreationPage();
				}
				
			});
	
			gd=new GridData();
			gd.horizontalAlignment = SWT.FILL;
			gd.verticalAlignment = SWT.FILL;
			gd.grabExcessHorizontalSpace = true;
			gd.grabExcessVerticalSpace = true;
			m_notationList.setLayoutData(gd);
			
			for (int i=0; i < m_notations.size(); i++) {
				m_notationList.add(m_notations.get(i).getName());
			}
			
			if (m_notations.size() > 0) {
				m_notationList.select(0);
			}
			
			setControl(composite);
		}
		
		/**
		 * This method returns the notation index currently selected.
		 * 
		 * @return The notation index
		 */
		public int getNotationIndex() {
			int ret=0;
			
			if (m_notationList != null) {
				ret = m_notationList.getSelectionIndex();
			}
			
			return(ret);
		}

		private org.eclipse.swt.widgets.Combo m_notationList=null;
		private java.util.List<Notation> m_notations=null;
	}

	/**
     * This is the one page of the wizard.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public class ScribbleNewFileCreationPage extends WizardNewFileCreationPage {
        /**
         * Remember the model file.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected IFile modelFile;
    
        /**
         * Pass in the selection.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public ScribbleNewFileCreationPage(String pageId, IStructuredSelection selection) {
            super(pageId, selection);
        }
    
        /**
         * The framework calls this to see if the file is correct.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected boolean validatePage() {
            if (super.validatePage()) {
                String requiredExt = "spg";
            	int index=m_notationSelection.getNotationIndex();
            	if (index != -1) {
            		requiredExt = m_notations.get(index).getCode();
            	}
            	
                String enteredExt = new Path(getFileName()).getFileExtension();
                if (enteredExt == null || !enteredExt.equals(requiredExt)) {
                    setErrorMessage("The filename must end in: "+requiredExt);
                    return false;
                }
                else {
                    return true;
                }
            }
            else {
                return false;
            }
        }
    
        /**
         * Store the dialog field settings upon completion.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public boolean performFinish() {
            modelFile = getModelFile();
            return true;
        }
    
        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public IFile getModelFile() {
            return
                modelFile == null ?
                    ResourcesPlugin.getWorkspace().getRoot().getFile(getContainerFullPath().append(getFileName())) :
                    modelFile;
        }
    }

}
