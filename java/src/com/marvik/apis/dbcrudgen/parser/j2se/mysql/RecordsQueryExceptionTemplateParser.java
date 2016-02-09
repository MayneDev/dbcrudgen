/**
 * 
 */
package com.marvik.apis.dbcrudgen.parser.j2se.mysql;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.natives.syntax.Syntax.PrintingChars;
import com.marvik.apis.dbcrudgen.parser.java.j2se.J2SETemplatesParser;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.templates.j2se.classes.MYSQLTransactionsWrapperTemplate;
import com.marvik.apis.dbcrudgen.templates.j2se.classes.RecordsQueryExceptionTemplate;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

/**
*Created on Feb 10, 2016-12:17:36 AM by victor
*/

/**
 * @author victor
 *
 */
public class RecordsQueryExceptionTemplateParser extends J2SETemplatesParser {

	/**
	 * RecordsQueryExceptionTemplateParser
	 */
	public RecordsQueryExceptionTemplateParser() {

	}

	/**
	 * @param j2seProjectConfiguration
	 * @param database
	 * @param mysqlAPIStorageLocation
	 * @return
	 */
	public String parseQueryExceptionTemplateParser(J2SEProjectConfiguration j2seProjectConfiguration,
			Database database, String mysqlAPIStorageLocation) {
		String template = new RecordsQueryExceptionTemplate().getTemplate();

		String projectPackageName = j2seProjectConfiguration.getPackageName();
		String mysqlAPIsSrcDirs = j2seProjectConfiguration.getJ2SEProjectMYSQLDatabaseConfiguration()
				.getMysqlAPIsClassesSrcDirs();
		String mysqlAPIsPackage = projectPackageName + PrintingChars.DOT
				+ NativeUtils.parseJavaPackage(mysqlAPIsSrcDirs);

		// Add Package name;
		template = addPackageName(template, mysqlAPIsPackage);

		return template;
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