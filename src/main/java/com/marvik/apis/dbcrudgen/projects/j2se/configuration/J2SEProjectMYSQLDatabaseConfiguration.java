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
	private String tableSchemasSrcDir;
	private String tableModelsSrcDir;
	private String tablesCRUDSrcDir;

	public J2SEProjectMYSQLDatabaseConfiguration(String mysqlAPIsClassesSrcDirs,
			DatabaseConnectionProperties databaseConnectionProperties, String tableSchemasSrcDir,
			String tableModelsSrcDir, String tablesCRUDSrcDir) {
		this.databaseConnectionProperties = databaseConnectionProperties;
		this.mysqlAPIsClassesSrcDirs = mysqlAPIsClassesSrcDirs;
		this.tableSchemasSrcDir = tableSchemasSrcDir;
		this.tableModelsSrcDir = tableModelsSrcDir;
		this.tablesCRUDSrcDir = tablesCRUDSrcDir;
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

	/**
	 * @return the tableSchemasSrcDir
	 */
	public String getTableSchemasSrcDir() {
		return tableSchemasSrcDir;
	}

	/**
	 * @return the tableModelsSrcDir
	 */
	public String getTableModelsSrcDir() {
		return tableModelsSrcDir;
	}

	/**
	 * @return the tablesCRUDSrcDir
	 */
	public String getTablesCRUDSrcDir() {
		return tablesCRUDSrcDir;
	}
}
