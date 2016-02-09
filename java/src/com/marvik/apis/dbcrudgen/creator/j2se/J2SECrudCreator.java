/**
 * 
 */
package com.marvik.apis.dbcrudgen.creator.j2se;

import java.io.File;
import java.io.IOException;

import javax.swing.filechooser.FileNameExtensionFilter;

import com.marvik.apis.dbcrudgen.core.toolchains.jdk.JDKFilenames;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.creator.CrudCreator;
import com.marvik.apis.dbcrudgen.io.writer.FileStreamWriter;
import com.marvik.apis.dbcrudgen.natives.assets.NativeAssets;
import com.marvik.apis.dbcrudgen.parser.j2se.mysql.MYSQLTransactionsWrapperTemplateParser;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectMYSQLDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.projects.java.filenames.JavaProjectFileNames;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.templates.CrudTemplates;
import com.marvik.apis.dbcrudgen.templates.j2se.classes.MYSQLTransactionsWrapperTemplate;
import com.marvik.apis.dbcrudgen.templates.simple.SimpleTemplates;
import com.marvik.apis.dbcrudgen.templates.tags.TemplateTags;

/**
*Created on Feb 9, 2016-8:09:04 PM by victor
*/

/**
 * @author victor
 *
 */
public class J2SECrudCreator extends CrudCreator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marvik.apis.dbcrudgen.creator.CrudCreator#getCrudTemplate()
	 */
	@Override
	public CrudTemplates getCrudTemplate() {

		return new MYSQLTransactionsWrapperTemplate();
	}

	/**
	 * @param database
	 * @throws IOException
	 */
	public void createProject(J2SEProjectConfiguration j2seProjectConfiguration, Database database) throws IOException {

		// Create project folder if it does not exists
		String projectStorageDir = j2seProjectConfiguration.getProjectStorageDir();
		getFilesHandler().createDirectories(projectStorageDir);

		// Create the project storage directory
		String projectName = j2seProjectConfiguration.getProjectName();
		getFilesHandler().createDirectories(projectStorageDir + NativeUtils.getFileSeparator() + projectName);

		// Create the project java storage directory
		String javaSrcDirs = j2seProjectConfiguration.getJavaSrcDirs();
		getFilesHandler().createDirectories(projectStorageDir + NativeUtils.getFileSeparator() + projectName
				+ NativeUtils.getFileSeparator() + javaSrcDirs);

		// Create project libs folder
		String libsStorageDirs = j2seProjectConfiguration.getLibsStorageDirs();
		getFilesHandler().createDirectories(projectStorageDir + NativeUtils.getFileSeparator() + projectName
				+ NativeUtils.getFileSeparator() + libsStorageDirs);

		// TODO ADD CODE TO AD LIBS TO CLASS PATH

		// Create the projects main package dirs
		String packageName = j2seProjectConfiguration.getPackageName();
		getFilesHandler().createDirectories(
				projectStorageDir + NativeUtils.getFileSeparator() + projectName + NativeUtils.getFileSeparator()
						+ javaSrcDirs + NativeUtils.getFileSeparator() + NativeUtils.parsePackagePath(packageName));

		// Add JDBC JAR to project libs
		String jdbcJarStorageLocation = projectStorageDir + NativeUtils.getFileSeparator() + projectName
				+ NativeUtils.getFileSeparator() + libsStorageDirs + NativeUtils.getFileSeparator()
				+ NativeAssets.FileNames.JDBC_JAR_FILE;
		getFilesHandler().copyFile(NativeAssets.Files.JDBC_JAR_FILE_PATH, jdbcJarStorageLocation);

		// Add JDBC lib to classpath
		addJDBCLibToClassPath(projectStorageDir, projectName,
				libsStorageDirs + NativeUtils.getFileSeparator() + NativeAssets.FileNames.JDBC_JAR_FILE);

		// Create MYSQL APIS
		J2SEProjectMYSQLDatabaseConfiguration j2seProjectMYSQLDatabaseConfiguration = j2seProjectConfiguration
				.getJ2SEProjectMYSQLDatabaseConfiguration();
		String mysqlAPISrcDirs = j2seProjectMYSQLDatabaseConfiguration.getMysqlAPIsClassesSrcDirs();
		String mysqlAPIStorageLocation = projectStorageDir + NativeUtils.getFileSeparator() + projectName
				+ NativeUtils.getFileSeparator() + javaSrcDirs + NativeUtils.getFileSeparator()
				+ NativeUtils.parsePackagePath(packageName) + NativeUtils.getFileSeparator() + mysqlAPISrcDirs;
		getFilesHandler().createDirectories(mysqlAPIStorageLocation);

		// Create MYSQL APIS
		createMYSQLAPIS(j2seProjectConfiguration, database, mysqlAPIStorageLocation);

	}

	/**
	 * @param projectStorageDir
	 * @param string
	 * @throws IOException
	 */
	private void addJDBCLibToClassPath(String projectStorageDir, String projectName, String jdbcLibPath) {
		try {
			String javaClassPathFile = JDKFilenames.CLASSPATH;
			String projectClassPathConfigFile = projectStorageDir + NativeUtils.getFileSeparator() + projectName
					+ NativeUtils.getFileSeparator() + javaClassPathFile;

			String libClassPathItemEntryTemplate = SimpleTemplates.Java.CLASS_PATH_ITEM_ENTRY;

			libClassPathItemEntryTemplate = libClassPathItemEntryTemplate
					.replace(TemplateTags.Java.CLASS_PATH_ITEM_KIND, TemplateTags.Java.CLASS_PATH_ITEM_KIND_LIB)
					.replace(TemplateTags.Java.CLASS_PATH_ITEM_PATH, jdbcLibPath);

			String classPathXML = getFilesHandler().readFile(projectClassPathConfigFile, true);

			// Append class path -- POOR MECHANISM SHOULD READ XML USING THE
			// STANDARD ISO WAY
			classPathXML = classPathXML.replace(TemplateTags.Java.CLASS_PATH_XML_CLOSING_ELEMENT,
					libClassPathItemEntryTemplate + TemplateTags.Java.CLASS_PATH_XML_CLOSING_ELEMENT);
			new FileStreamWriter().writeStream(new File(projectClassPathConfigFile), classPathXML);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param database
	 * @param j2seProjectConfiguration
	 * @throws IOException
	 */
	private void createMYSQLAPIS(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String mysqlAPIsStorageLocation) throws IOException {

		// Create myql api storage dirs
		J2SEProjectMYSQLDatabaseConfiguration j2seProjectMYSQLDatabaseConfiguration = j2seProjectConfiguration
				.getJ2SEProjectMYSQLDatabaseConfiguration();

		// Create database connection properties class
		createDatabaseConnectionPropertiesClass(j2seProjectConfiguration, database, mysqlAPIsStorageLocation);

		// create J2SE MYSQL connection class
		createJ2SEMYSQLConnectionClass(j2seProjectConfiguration, database, mysqlAPIsStorageLocation);

		// Create transaction executor class
		createTransactionExecutorClass(j2seProjectConfiguration, database, mysqlAPIsStorageLocation);

		// Create transaction wrapper class
		createTransactionsWrapperClass(j2seProjectConfiguration, database, mysqlAPIsStorageLocation);
	}

	/**
	 * Create the transaction wrapper class
	 * 
	 * @throws IOException
	 */
	private void createTransactionsWrapperClass(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String mysqlAPIsStorageLocation) throws IOException {

		MYSQLTransactionsWrapperTemplateParser mysqlTransactionsWrapperTemplateParser = new MYSQLTransactionsWrapperTemplateParser();
		String sourceCode = mysqlTransactionsWrapperTemplateParser.parseTransactionsWrapper(j2seProjectConfiguration,
				database, mysqlAPIsStorageLocation);
		String sourceFile = JavaProjectFileNames.MYSQL_TRANSACTIONS_WRAPPER_CLASS_NAME;
		String sourceFileStoragePath = mysqlAPIsStorageLocation + NativeUtils.getFileSeparator() + sourceFile
				+ JavaProjectFileNames.JAVA_FILE_EXTENSION;
		new FileStreamWriter().writeStream(new File(sourceFileStoragePath), sourceCode);
	}

	/**
	 * create the transaction executor class
	 * @throws IOException 
	 */
	private void createTransactionExecutorClass(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String mysqlAPIsStorageLocation) throws IOException {
		MYSQLTransactionsWrapperTemplateParser mysqlTransactionsWrapperTemplateParser = new MYSQLTransactionsWrapperTemplateParser();
		String sourceCode = mysqlTransactionsWrapperTemplateParser.parseTransactionsWrapper(j2seProjectConfiguration,
				database, mysqlAPIsStorageLocation);
		String sourceFile = JavaProjectFileNames.MYSQL_TRANSACTIONS_WRAPPER_CLASS_NAME;
		String sourceFileStoragePath = mysqlAPIsStorageLocation + NativeUtils.getFileSeparator() + sourceFile
				+ JavaProjectFileNames.JAVA_FILE_EXTENSION;
		new FileStreamWriter().writeStream(new File(sourceFileStoragePath), sourceCode);
	}

	/**
	 * create the j2se mysql connection classs
	 * @throws IOException 
	 */
	private void createJ2SEMYSQLConnectionClass(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String mysqlAPIsStorageLocation) throws IOException {
		MYSQLTransactionsWrapperTemplateParser mysqlTransactionsWrapperTemplateParser = new MYSQLTransactionsWrapperTemplateParser();
		String sourceCode = mysqlTransactionsWrapperTemplateParser.parseTransactionsWrapper(j2seProjectConfiguration,
				database, mysqlAPIsStorageLocation);
		String sourceFile = JavaProjectFileNames.MYSQL_TRANSACTIONS_WRAPPER_CLASS_NAME;
		String sourceFileStoragePath = mysqlAPIsStorageLocation + NativeUtils.getFileSeparator() + sourceFile
				+ JavaProjectFileNames.JAVA_FILE_EXTENSION;
		new FileStreamWriter().writeStream(new File(sourceFileStoragePath), sourceCode);
	}

	/**
	 * create the database connection properties class
	 * @throws IOException 
	 */
	private void createDatabaseConnectionPropertiesClass(J2SEProjectConfiguration j2seProjectConfiguration,
			Database database, String mysqlAPIsStorageLocation) throws IOException {
		MYSQLTransactionsWrapperTemplateParser mysqlTransactionsWrapperTemplateParser = new MYSQLTransactionsWrapperTemplateParser();
		String sourceCode = mysqlTransactionsWrapperTemplateParser.parseTransactionsWrapper(j2seProjectConfiguration,
				database, mysqlAPIsStorageLocation);
		String sourceFile = JavaProjectFileNames.MYSQL_TRANSACTIONS_WRAPPER_CLASS_NAME;
		String sourceFileStoragePath = mysqlAPIsStorageLocation + NativeUtils.getFileSeparator() + sourceFile
				+ JavaProjectFileNames.JAVA_FILE_EXTENSION;
		new FileStreamWriter().writeStream(new File(sourceFileStoragePath), sourceCode);
	}

}
