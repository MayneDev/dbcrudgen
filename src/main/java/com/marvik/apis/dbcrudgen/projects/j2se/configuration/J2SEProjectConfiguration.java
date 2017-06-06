/**
 * 
 */
package com.marvik.apis.dbcrudgen.projects.j2se.configuration;

import com.marvik.apis.dbcrudgen.schemamodels.database.Database;

/**
*Created on Feb 9, 2016-7:20:22 PM by victor
*/

/**
 * @author victor
 *
 */
public class J2SEProjectConfiguration {

	private String projectName;
	private String packageName;
	private String projectStorageDir;
	private String javaSrcDirs;
	private String libsStorageDirs;
	private J2SEProjectMYSQLDatabaseConfiguration j2seProjectMYSQLDatabaseConfiguration;

	/**
	 * @param projectName
	 * @param packageName
	 * @param projectStorageDir
	 * @param javaSrcDirs
	 */
	public J2SEProjectConfiguration(String projectName, String packageName, String projectStorageDir,
			String javaSrcDirs, String libsStorageDirs) {

		this.projectName = projectName;
		this.packageName = packageName;
		this.projectStorageDir = projectStorageDir;
		this.javaSrcDirs = javaSrcDirs;
		this.libsStorageDirs = libsStorageDirs;
	}

	/**
	 * @param j2seProjectMYSQLDatabaseConfiguration
	 *            the j2seProjectMYSQLDatabaseConfiguration to set
	 */
	public void setJ2SEProjectMYSQLDatabaseConfiguration(
			J2SEProjectMYSQLDatabaseConfiguration j2seProjectMYSQLDatabaseConfiguration) {
		this.j2seProjectMYSQLDatabaseConfiguration = j2seProjectMYSQLDatabaseConfiguration;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @return the projectStorageDir
	 */
	public String getProjectStorageDir() {
		return projectStorageDir;
	}

	/**
	 * @return the javaSrcDirs
	 */
	public String getJavaSrcDirs() {
		return javaSrcDirs;
	}

	/**
	 * @return the libsStorageDirs
	 */
	public String getLibsStorageDirs() {
		return libsStorageDirs;
	}

	/**
	 * @return the j2seProjectMYSQLDatabaseConfiguration
	 */
	public J2SEProjectMYSQLDatabaseConfiguration getJ2SEProjectMYSQLDatabaseConfiguration() {
		return j2seProjectMYSQLDatabaseConfiguration;
	}

}
