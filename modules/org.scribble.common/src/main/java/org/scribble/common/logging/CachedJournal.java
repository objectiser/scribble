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
package org.scribble.common.logging;

import java.io.Serializable;
import java.util.Map;

public class CachedJournal implements Journal {
	
	private java.util.List<IssueDetails> m_issues=new java.util.Vector<IssueDetails>();

	@Override
	public void debug(String issue, Map<String, Serializable> props) {
		m_issues.add(new IssueDetails(IssueType.Debug, issue, props));
	}

	@Override
	public void error(String issue, Map<String, Serializable> props) {
		m_issues.add(new IssueDetails(IssueType.Error, issue, props));
	}

	@Override
	public void info(String issue, Map<String, Serializable> props) {
		m_issues.add(new IssueDetails(IssueType.Info, issue, props));
	}

	@Override
	public void trace(String issue, Map<String, Serializable> props) {
		m_issues.add(new IssueDetails(IssueType.Trace, issue, props));
	}

	@Override
	public void warning(String issue, Map<String, Serializable> props) {
		m_issues.add(new IssueDetails(IssueType.Warning, issue, props));
	}
	
	/**
	 * This method applies any cached issues to the supplied logger.
	 * 
	 * @param logger The logger
	 */
	public void apply(Journal logger) {
		for (IssueDetails id : m_issues) {
			if (id.getIssueType() == IssueType.Debug) {
				logger.debug(id.getMessage(), id.getProperties());
			} else if (id.getIssueType() == IssueType.Error) {
				logger.error(id.getMessage(), id.getProperties());
			} else if (id.getIssueType() == IssueType.Info) {
				logger.info(id.getMessage(), id.getProperties());
			} else if (id.getIssueType() == IssueType.Trace) {
				logger.trace(id.getMessage(), id.getProperties());
			} else if (id.getIssueType() == IssueType.Warning) {
				logger.warning(id.getMessage(), id.getProperties());
			}
		}
	}

	public enum IssueType {
		Debug,
		Error,
		Info,
		Trace,
		Warning
	}

	public class IssueDetails {
		public IssueDetails(IssueType type, String mesg,
						Map<String, Serializable> props) {
			m_type = type;
			m_message = mesg;
			m_properties = props;
		}
		
		public IssueType getIssueType() {
			return(m_type);
		}
		
		public String getMessage() {
			return(m_message);
		}
		
		public Map<String,Serializable> getProperties() {
			return(m_properties);
		}
		
		private IssueType m_type=IssueType.Info;
		private String m_message=null;
		private Map<String,Serializable> m_properties=null;
	}
}
