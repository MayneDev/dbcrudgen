/**
 * 
 */
package com.marvik.apis.dbcrudgen.projects.j2se.configuration;

import com.marvik.apis.dbcrudgen.database.connection.DatabaseConnectionProperties;

/**
*Created on Feb 9, 2016-8:29:47 PM by victor
*/

/**
 * @author victor
 *
 */
public class J2SEProjectMYSQLDatabaseConfiguration {

	private String mysqlAPIsClassesSrcDirs;
	private DatabaseConnectionProperties databaseConnectionProperties;

	public J2SEProjectMYSQLDatabaseConfiguration(String mysqlAPIsClassesSrcDirs,
			DatabaseConnectionProperties databaseConnectionProperties) {
		this.databaseConnectionProperties = databaseConnectionProperties;
		this.mysqlAPIsClassesSrcDirs = mysqlAPIsClassesSrcDirs;
	}

	/**
	 * @return the databaseConnectionProperties
	 */
	public DatabaseConnectionProperties getDatabaseConnectionProperties() {
		return databaseConnectionProperties;
	}

	/**
	 * @return the mysqlAPIsClassesSrcDirs
	 */
	public String getMysqlAPIsClassesSrcDirs() {
		return mysqlAPIsClassesSrcDirs;
	}
}
