/**
 * 
 */
package com.marvik.apis.dbcrudgen.parser.j2se.mysql;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.database.connection.DatabaseConnectionProperties;
import com.marvik.apis.dbcrudgen.natives.syntax.Syntax.PrintingChars;
import com.marvik.apis.dbcrudgen.parser.java.j2se.J2SETemplatesParser;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectMYSQLDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.templates.j2se.classes.MYSQLDatabaseConnectionPropertiesTemplate;
import com.marvik.apis.dbcrudgen.templates.j2se.classes.MYSQLTransactionsWrapperTemplate;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

/**
*Created on Feb 9, 2016-8:08:07 PM by victor
*/

/**
 * @author victor
 *
 */
public class MYSQLDatabaseConnectionPropertiesTemplateParser extends J2SETemplatesParser {

	public MYSQLDatabaseConnectionPropertiesTemplateParser() {

	}

	/**
	 * @param j2seProjectConfiguration
	 * @param database
	 * @param mysqlAPIsStorageLocation
	 * @return
	 */
	public String parseTransactionsWrapper(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String mysqlAPIsStorageLocation) {
		String template = new MYSQLDatabaseConnectionPropertiesTemplate().getTemplate();

		String projectPackageName = j2seProjectConfiguration.getPackageName();
		String mysqlAPIsSrcDirs = j2seProjectConfiguration.getJ2SEProjectMYSQLDatabaseConfiguration()
				.getMysqlAPIsClassesSrcDirs();
		String mysqlAPIsPackage = projectPackageName + PrintingChars.DOT
				+ NativeUtils.parseJavaPackage(mysqlAPIsSrcDirs);

		// Add Package name;
		template = addPackageName(template, mysqlAPIsPackage);

		// Add Database connection infos
		template = addDatabaseConnectionProperties(template,
				j2seProjectConfiguration.getJ2SEProjectMYSQLDatabaseConfiguration().getDatabaseConnectionProperties());

		return template;
	}

	/**
	 * Add the database connection properties
	 * 
	 * @param template
	 * @param databaseConnectionProperties 
	 * @param j2seProjectMYSQLDatabaseConfiguration
	 * @return template
	 */
	private String addDatabaseConnectionProperties(String template, DatabaseConnectionProperties databaseConnectionProperties
			) {
		String databaseHost = databaseConnectionProperties.getDatabaseHost();
		String databaseName = databaseConnectionProperties.getDatabaseName();
		String databaseUser = databaseConnectionProperties.getDatabaseUser();
		String userPassword = databaseConnectionProperties.getDatabaseUserPassword();
		
		return template.replace(TemplateTags.SQL.DATABASE_HOST, databaseHost)
				.replace(TemplateTags.SQL.DATABASE_NAME, databaseName)
				.replace(TemplateTags.SQL.DATABASE_USER, databaseUser)
				.replace(TemplateTags.SQL.USER_PASSWORD,userPassword );
	}

	/**
	 * 
	 * Adds the package name to the template
	 * 
	 * @param template
	 * @param packageName
	 * @return template with valid package name
	 */
	private String addPackageName(String template, String packageName) {
		return template.replace(TemplateTags.Java.PACKAGE_NAME, packageName);
	}

}
