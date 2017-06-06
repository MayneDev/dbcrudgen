/**
 * 
 */
package com.marvik.apis.dbcrudgen.application.events;

/**
*Created on Feb 5, 2016-12:19:37 PM by victor
*/

/**
 * @author victor
 *
 */
public interface UserEventActions {

	/**
	 * This is called when a user selects a database and wants to perform
	 * operations on that database
	 */
	
	public void onDatabaseSelected(String databaseName);
}
