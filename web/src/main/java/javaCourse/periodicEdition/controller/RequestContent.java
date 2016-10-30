package javaCourse.periodicEdition.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * class to keep the contents of the request
 * 
 * @author Vladimir Pliuta
 *
 */
public class RequestContent {

	private HashMap<String, Object> requestAttributes = new HashMap<String, Object>();
	private HashMap<String, String[]> requestParameters = new HashMap<String, String[]>();
	private HashMap<String, Object> sessionAttributes = new HashMap<String, Object>();
	private HttpSession session = null;
	
	public void extractValue(HttpServletRequest request) {

		Enumeration<String> requestAttributeNames = request.getAttributeNames();
		while (requestAttributeNames.hasMoreElements()) {
			String requestAttributeName = requestAttributeNames.nextElement();
			requestAttributes.put(requestAttributeName, request.getAttribute(requestAttributeName));
		}

		Enumeration<String> requestParametrNames = request.getParameterNames();
		while (requestParametrNames.hasMoreElements()) {
			String requestParametrName = requestParametrNames.nextElement();
			requestParameters.put(requestParametrName, request.getParameterValues(requestParametrName));
		}

		session = request.getSession(true);
		Enumeration<String> sessionAttributeNames = session.getAttributeNames();
		while (sessionAttributeNames.hasMoreElements()) {
			String sessionAttributeName = sessionAttributeNames.nextElement();
			sessionAttributes.put(sessionAttributeName, session.getAttribute(sessionAttributeName));
		}
	}

	public HttpServletRequest updateRequest(HttpServletRequest request) {

		Set<String> requestAttributeNames = requestAttributes.keySet();
		Iterator<String> requestAttributeNamesIterator = requestAttributeNames.iterator();
		while (requestAttributeNamesIterator.hasNext()) {
			String requestAttributeName = requestAttributeNamesIterator.next();
			request.setAttribute(requestAttributeName, requestAttributes.get(requestAttributeName));
		}

		session = request.getSession(true);
		Set<String> sessionAttributeNames = sessionAttributes.keySet();
		Iterator<String> sessionAttributeNamesIterator = sessionAttributeNames.iterator();
		while (sessionAttributeNamesIterator.hasNext()) {
			String sessionAttributeName = sessionAttributeNamesIterator.next();
			session.setAttribute(sessionAttributeName, sessionAttributes.get(sessionAttributeName));
		}
		return request;
	}

	public void setRequestAttribute(String key, Object value) {
		requestAttributes.put(key, value);
	}

	public void setRequestParameters(String key, String[] value) {
		requestParameters.put(key, value);
	}

	public void setSessionAttribute(String key, Object value) {
		sessionAttributes.put(key, value);
	}

	public Object getRequestAttribute(String key) {
		return requestAttributes.get(key);
	}
	
	public String getRequestParameter(String key) {
		return requestParameters.get(key)[0];
	}
	
	public Object getSessionAttribute(String key) {
		return sessionAttributes.get(key);
	}
	
	public void sessionInvalidate(){
		this.session.invalidate();
	}
}
