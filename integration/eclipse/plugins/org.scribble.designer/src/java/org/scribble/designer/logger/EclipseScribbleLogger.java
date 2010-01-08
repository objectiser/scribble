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
package org.scribble.designer.logger;

import java.io.Serializable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.scribble.common.logger.ScribbleLogger;

public class EclipseScribbleLogger implements ScribbleLogger {

	public EclipseScribbleLogger(IFile file) {
		m_file = file;
	}

	public void debug(String issue, java.util.Map<String,Serializable> props) {
		// TODO Auto-generated method stub
		
	}

	public void error(String issue, java.util.Map<String,Serializable> props) {
		reportIssue(issue, ReportEntry.ERROR_TYPE);
		m_errorOccurred = true;
	}

	public boolean hasErrorOccurred() {
		return(m_errorOccurred);
	}
	
	public void info(String issue, java.util.Map<String,Serializable> props) {
		reportIssue(issue, ReportEntry.INFORMATION_TYPE);
	}

	public void trace(String issue, java.util.Map<String,Serializable> props) {
		// TODO Auto-generated method stub
		
	}

	public void warning(String issue, java.util.Map<String,Serializable> props) {
		reportIssue(issue, ReportEntry.WARNING_TYPE);
	}
	
	protected void reportIssue(String issue, int issueType) {
		
		if (m_file != null) {
	
			synchronized(m_entries) {
				m_entries.add(new ReportEntry(issue, issueType));
			}
			
			if (m_finished) {
				// Publish immediately
				finished();
			}
		}
	}			
	
	public void finished() {
		org.eclipse.swt.widgets.Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				
				if (m_file != null && m_file.exists()) {
					
					// Clear current markers
					try {
						synchronized(m_entries) {
							
							if (m_finished == false) {
								m_file.deleteMarkers(ScribbleMarker.SCRIBBLE_PROBLEM, true,
										IFile.DEPTH_INFINITE);
								m_finished = true;
							}
						
							// Update the markers
							for (int i=0; i < m_entries.size(); i++) {
								ReportEntry re=(ReportEntry)m_entries.get(i);
								
								if (m_reported.contains(re) == false) {
									createMarker(re.getStartPosition(),
										re.getEndPosition(),
										re.getIssue(), re.getType(),
										re.getProperties());
									
									m_reported.add(re);
								}
							}
							
							m_entries.clear();
						}
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	protected void createMarker(int startPos, int endPos, String mesg,
			int type, java.util.Map<String,Object> props) {
					
		// Create marker for message
		try {
			IMarker marker=m_file.createMarker(ScribbleMarker.SCRIBBLE_PROBLEM);
			
			// Initialize the attributes on the marker
			marker.setAttribute(IMarker.MESSAGE, mesg);
			
			if (type == ReportEntry.ERROR_TYPE) {
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			} else if (type == ReportEntry.WARNING_TYPE) {
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
			} else if (type == ReportEntry.INFORMATION_TYPE) {
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
			}
			
			if (startPos >= 0) {
				marker.setAttribute(IMarker.CHAR_START, startPos);
			}
			
			if (endPos >= 0) {
				marker.setAttribute(IMarker.CHAR_END, endPos);
			}
		} catch(Exception e) {
			
			// TODO: report error
			e.printStackTrace();
		}
	}

	private IFile m_file=null;
	private boolean m_finished=false;
	private boolean m_errorOccurred=false;
	private java.util.Vector<ReportEntry> m_entries=new java.util.Vector<ReportEntry>();
	private java.util.Vector<ReportEntry> m_reported=new java.util.Vector<ReportEntry>();
	
	/**
	 * This is a simple data container class to hold the
	 * information reported during validation.
	 *
	 */
	public class ReportEntry {
		public ReportEntry(String issue, int type) {
			m_issue = issue;
			m_type = type;
		}
		
		public String getIssue() {
			return(m_issue);
		}
		
		public int getType() {
			return(m_type);
		}
		
		public int getStartPosition() {
			return(0);
		}
		
		public int getEndPosition() {
			return(0);
		}
		
		public java.util.Map<String, Object> getProperties() {
			return(null);
		}
		
		public static final int ERROR_TYPE=0;
		public static final int WARNING_TYPE=1;
		public static final int INFORMATION_TYPE=2;
		
		private String m_issue=null;
		private int m_type=0;
	}
}
