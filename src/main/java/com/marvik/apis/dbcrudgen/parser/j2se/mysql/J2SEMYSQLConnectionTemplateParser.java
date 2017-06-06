/**
 * 
 */
package com.marvik.apis.dbcrudgen.parser.j2se.mysql;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.natives.syntax.Syntax.PrintingChars;
import com.marvik.apis.dbcrudgen.parser.java.j2se.J2SETemplatesParser;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.templates.j2se.classes.J2SEMYSQLConnectionTemplate;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

/**
*Created on Feb 9, 2016-8:05:11 PM by victor
*/

/**
 * @author victor
 *
 */
public class J2SEMYSQLConnectionTemplateParser extends J2SETemplatesParser {

	/**
	 * J2SEMYSQLConnectionTemplateParser
	 */
	public J2SEMYSQLConnectionTemplateParser() {

	}

	/**
	 * @param j2seProjectConfiguration
	 * @param database
	 * @param mysqlAPIsStorageLocation
	 * @return
	 */
	public String parseJ2SEMYSQLConnection(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String mysqlAPIsStorageLocation) {
		String template = new J2SEMYSQLConnectionTemplate().getTemplate();

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
