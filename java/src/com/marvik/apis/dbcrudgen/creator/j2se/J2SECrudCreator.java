/**
 * 
 */
package com.marvik.apis.dbcrudgen.creator.j2se;

import java.io.File;
import java.io.IOException;

import com.marvik.apis.dbcrudgen.core.templates.tags.NativeTemplateTags;
import com.marvik.apis.dbcrudgen.core.toolchains.jdk.JDKFilenames;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.creator.CrudCreator;
import com.marvik.apis.dbcrudgen.io.writer.FileStreamWriter;
import com.marvik.apis.dbcrudgen.natives.assets.NativeAssets;
import com.marvik.apis.dbcrudgen.parser.j2se.mysql.J2SEMYSQLConnectionTemplateParser;
import com.marvik.apis.dbcrudgen.parser.j2se.mysql.J2SEMYSQLTableSchemasTemplatesParser;
import com.marvik.apis.dbcrudgen.parser.j2se.mysql.JavaObjectDefaultEncapsulationTemplateParser;
import com.marvik.apis.dbcrudgen.parser.j2se.mysql.JavaTableModelTemplateParser;
import com.marvik.apis.dbcrudgen.parser.j2se.mysql.MYSQLDatabaseConnectionPropertiesTemplateParser;
import com.marvik.apis.dbcrudgen.parser.j2se.mysql.MYSQLTransactionsExecutorTemplateParser;
import com.marvik.apis.dbcrudgen.parser.j2se.mysql.MYSQLTransactionsWrapperTemplateParser;
import com.marvik.apis.dbcrudgen.parser.j2se.mysql.RecordsDeleteExceptionTemplateParser;
import com.marvik.apis.dbcrudgen.parser.j2se.mysql.RecordsInsertExceptionTemplateParser;
import com.marvik.apis.dbcrudgen.parser.j2se.mysql.RecordsQueryExceptionTemplateParser;
import com.marvik.apis.dbcrudgen.parser.j2se.mysql.RecordsUpdateExceptionTemplateParser;
import com.marvik.apis.dbcrudgen.projects.android.filenames.AndroidProjectFileNames;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectMYSQLDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.projects.java.filenames.JavaProjectFileNames;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
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

		// Create all the source codes storage directories
		createAllStorageDirs(j2seProjectConfiguration, database);

		// Create all source codes
		createSourceCodes(j2seProjectConfiguration, database);

	}

	/**
	 * @param j2seProjectConfiguration
	 * @param database
	 * @param mysqlAPIStorageLocation
	 */
	private void createAllStorageDirs(J2SEProjectConfiguration j2seProjectConfiguration, Database database) {

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

	}

	/**
	 * @param j2seProjectConfiguration
	 * @param database
	 * @param mysqlAPIStorageLocation
	 * @throws IOException
	 */
	private void createSourceCodes(J2SEProjectConfiguration j2seProjectConfiguration, Database database)
			throws IOException {

		String projectStorageDir = j2seProjectConfiguration.getProjectStorageDir();
		String projectName = j2seProjectConfiguration.getProjectName();
		String packageName = j2seProjectConfiguration.getPackageName();
		String javaSrcDirs = j2seProjectConfiguration.getJavaSrcDirs();

		J2SEProjectMYSQLDatabaseConfiguration j2seProjectMYSQLDatabaseConfiguration = j2seProjectConfiguration
				.getJ2SEProjectMYSQLDatabaseConfiguration();

		String projectFilesDefaultStorageDirectory = projectStorageDir + NativeUtils.getFileSeparator() + projectName
				+ NativeUtils.getFileSeparator() + javaSrcDirs + NativeUtils.getFileSeparator()
				+ NativeUtils.parsePackagePath(packageName);

		String mysqlAPISrcDirs = j2seProjectMYSQLDatabaseConfiguration.getMysqlAPIsClassesSrcDirs();
		String mysqlAPIStorageLocation = projectFilesDefaultStorageDirectory + NativeUtils.getFileSeparator()
				+ mysqlAPISrcDirs;

		// Create MYSQL APIS
		createMYSQLAPIS(j2seProjectConfiguration, database, mysqlAPIStorageLocation);

		// Create SQL EXCEPTIONS CLASSES
		createSQLExceptionClasses(j2seProjectConfiguration, database, mysqlAPIStorageLocation);

		// Create table schemas class
		createTablesSchemasSourceFile(j2seProjectConfiguration, database, projectFilesDefaultStorageDirectory);

		// create tables model classes
		createTableModelInfoClassesSourceFiles(j2seProjectConfiguration, database, projectFilesDefaultStorageDirectory);
	}

	
	private void createTableModelInfoClassesSourceFiles(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String projectFilesDefaultStorageDirectory) {

		String packageName = j2seProjectConfiguration.getPackageName();
		
		String tablesModelInfoPackage  = j2seProjectConfiguration.getJ2SEProjectMYSQLDatabaseConfiguration()
				.getTableModelsSrcDir();
		
		JavaTableModelTemplateParser javaTableModelTemplateParser = new JavaTableModelTemplateParser();
		
		JavaObjectDefaultEncapsulationTemplateParser javaObjectDefaultEncapsulationTemplateParser = new JavaObjectDefaultEncapsulationTemplateParser();

		for (Table table : database.getTables()) {

			String tableClassName = NativeUtils.toJavaBeansClass(table.getTableName());

			// package where this table model info class will be saved
			String tableModelSourceFilePackageFilePath = tablesModelInfoPackage + NativeUtils.getFileSeparator()
					+ tableClassName.toLowerCase();

			// the table model source file
			String tableModelSourceFile = projectFilesDefaultStorageDirectory + NativeUtils.getFileSeparator()
					+ tableModelSourceFilePackageFilePath + NativeUtils.getFileSeparator() + tableClassName
					+ TemplateTags.Java.INFO + JavaProjectFileNames.JAVA_FILE_EXTENSION;

			String tableModelSourceCode = javaTableModelTemplateParser.createSourceCode(
					javaObjectDefaultEncapsulationTemplateParser,
					packageName + NativeTemplateTags.DOT
							+ NativeUtils.parseJavaPackage(tableModelSourceFilePackageFilePath), // Package
																									// name
					table);

			createSourceFile(tableModelSourceFile, tableModelSourceCode);

		}
	}

	/**
	 * {@link J2SECrudCreator}#createTablesSchemasSourceFile
	 * 
	 * Creates all the source code for all the tables schemas and saves the
	 * source code to the disk.
	 * 
	 * @param j2seProjectConfiguration
	 * 
	 * @param database
	 * @param projectFilesDefaultStorageDirectory
	 * @param databaseTablesPackage
	 */
	private void createTablesSchemasSourceFile(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String projectFilesDefaultStorageDirectory) {

		J2SEMYSQLTableSchemasTemplatesParser j2semysqlTableSchemasTemplatesParser = new J2SEMYSQLTableSchemasTemplatesParser();

		String databaseTablesSchemasPackage = j2seProjectConfiguration.getJ2SEProjectMYSQLDatabaseConfiguration()
				.getTableSchemasSrcDir();
		String tablesSchemasSourceCode = j2semysqlTableSchemasTemplatesParser
				.createTablesSchemas(j2seProjectConfiguration, database.getTables());

		String tablesSchemasAbsoluteSourceFile = projectFilesDefaultStorageDirectory + NativeUtils.getFileSeparator()
				+ databaseTablesSchemasPackage + NativeUtils.getFileSeparator()
				+ JavaProjectFileNames.TABLE_SCHEMAS_FILE_NAME + JavaProjectFileNames.JAVA_FILE_EXTENSION;

		createSourceFile(tablesSchemasAbsoluteSourceFile, tablesSchemasSourceCode);
	}

	/**
	 * @param j2seProjectConfiguration
	 * @param database
	 * @param mysqlAPIStorageLocation
	 * @throws IOException
	 */
	private void createSQLExceptionClasses(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String mysqlAPIStorageLocation) throws IOException {

		// Create delete Exception class
		createDeleteExceptionClasses(j2seProjectConfiguration, database, mysqlAPIStorageLocation);

		// Create insert Exception class
		createInsertExceptionClasses(j2seProjectConfiguration, database, mysqlAPIStorageLocation);

		// Create query Exception class
		createQueryExceptionClasses(j2seProjectConfiguration, database, mysqlAPIStorageLocation);

		// Create update Exception class
		createUpdateExceptionClasses(j2seProjectConfiguration, database, mysqlAPIStorageLocation);
	}

	/**
	 * @param j2seProjectConfiguration
	 * @param database
	 * @param mysqlAPIStorageLocation
	 * @throws IOException
	 */
	private void createUpdateExceptionClasses(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String mysqlAPIStorageLocation) throws IOException {

		RecordsUpdateExceptionTemplateParser recordsUpdateExceptionTemplateParser = new RecordsUpdateExceptionTemplateParser();
		String sourceCode = recordsUpdateExceptionTemplateParser
				.parseUpdateExceptionTemplateParser(j2seProjectConfiguration, database, mysqlAPIStorageLocation);
		String sourceFile = JavaProjectFileNames.RECORDS_UPDATE_EXCEPTION_CLASS_NAME;
		String sourceFileStoragePath = mysqlAPIStorageLocation + NativeUtils.getFileSeparator() + sourceFile
				+ JavaProjectFileNames.JAVA_FILE_EXTENSION;
		new FileStreamWriter().writeStream(new File(sourceFileStoragePath), sourceCode);

	}

	/**
	 * @param j2seProjectConfiguration
	 * @param database
	 * @param mysqlAPIStorageLocation
	 * @throws IOException
	 */
	private void createQueryExceptionClasses(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String mysqlAPIStorageLocation) throws IOException {

		RecordsQueryExceptionTemplateParser recordsQueryExceptionTemplateParser = new RecordsQueryExceptionTemplateParser();
		String sourceCode = recordsQueryExceptionTemplateParser
				.parseQueryExceptionTemplateParser(j2seProjectConfiguration, database, mysqlAPIStorageLocation);
		String sourceFile = JavaProjectFileNames.RECORDS_QUERY_EXCEPTION_CLASS_NAME;
		String sourceFileStoragePath = mysqlAPIStorageLocation + NativeUtils.getFileSeparator() + sourceFile
				+ JavaProjectFileNames.JAVA_FILE_EXTENSION;
		new FileStreamWriter().writeStream(new File(sourceFileStoragePath), sourceCode);
	}

	/**
	 * @param j2seProjectConfiguration
	 * @param database
	 * @param mysqlAPIStorageLocation
	 * @throws IOException
	 */
	private void createInsertExceptionClasses(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String mysqlAPIStorageLocation) throws IOException {

		RecordsInsertExceptionTemplateParser recordsInsertExceptionTemplateParser = new RecordsInsertExceptionTemplateParser();
		String sourceCode = recordsInsertExceptionTemplateParser
				.parseInsertExceptionTemplateParser(j2seProjectConfiguration, database, mysqlAPIStorageLocation);
		String sourceFile = JavaProjectFileNames.RECORDS_INSERT_EXCEPTION_CLASS_NAME;
		String sourceFileStoragePath = mysqlAPIStorageLocation + NativeUtils.getFileSeparator() + sourceFile
				+ JavaProjectFileNames.JAVA_FILE_EXTENSION;
		new FileStreamWriter().writeStream(new File(sourceFileStoragePath), sourceCode);
	}

	/**
	 * @param j2seProjectConfiguration
	 * @param database
	 * @param mysqlAPIStorageLocation
	 * @throws IOException
	 */
	private void createDeleteExceptionClasses(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String mysqlAPIStorageLocation) throws IOException {

		RecordsDeleteExceptionTemplateParser recordsDeleteExceptionTemplateParser = new RecordsDeleteExceptionTemplateParser();
		String sourceCode = recordsDeleteExceptionTemplateParser
				.parseDeleteExceptionTemplateParser(j2seProjectConfiguration, database, mysqlAPIStorageLocation);
		String sourceFile = JavaProjectFileNames.RECORDS_DELETE_EXCEPTION_CLASS_NAME;
		String sourceFileStoragePath = mysqlAPIStorageLocation + NativeUtils.getFileSeparator() + sourceFile
				+ JavaProjectFileNames.JAVA_FILE_EXTENSION;
		new FileStreamWriter().writeStream(new File(sourceFileStoragePath), sourceCode);

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
			// new FileStreamWriter().writeStream(new
			// File(projectClassPathConfigFile), classPathXML);
			System.err.println("EDITING BUILD PATH DISABLED in " + this.getClass().getCanonicalName());
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
	 * 
	 * @throws IOException
	 */
	private void createTransactionExecutorClass(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String mysqlAPIsStorageLocation) throws IOException {
		MYSQLTransactionsExecutorTemplateParser mysqlTransactionsExecutorTemplateParser = new MYSQLTransactionsExecutorTemplateParser();
		String sourceCode = mysqlTransactionsExecutorTemplateParser.parseTransactionsExecutor(j2seProjectConfiguration,
				database, mysqlAPIsStorageLocation);
		String sourceFile = JavaProjectFileNames.MYSQL_TRANSACTIONS_EXECUTOR_CLASS_NAME;
		String sourceFileStoragePath = mysqlAPIsStorageLocation + NativeUtils.getFileSeparator() + sourceFile
				+ JavaProjectFileNames.JAVA_FILE_EXTENSION;
		new FileStreamWriter().writeStream(new File(sourceFileStoragePath), sourceCode);
	}

	/**
	 * create the j2se mysql connection classs
	 * 
	 * @throws IOException
	 */
	private void createJ2SEMYSQLConnectionClass(J2SEProjectConfiguration j2seProjectConfiguration, Database database,
			String mysqlAPIsStorageLocation) throws IOException {
		J2SEMYSQLConnectionTemplateParser j2semysqlConnectionTemplateParser = new J2SEMYSQLConnectionTemplateParser();
		String sourceCode = j2semysqlConnectionTemplateParser.parseJ2SEMYSQLConnection(j2seProjectConfiguration,
				database, mysqlAPIsStorageLocation);
		String sourceFile = JavaProjectFileNames.J2SE_MYSQL_DATABASE_CONNECTION_CLASS_NAME;
		String sourceFileStoragePath = mysqlAPIsStorageLocation + NativeUtils.getFileSeparator() + sourceFile
				+ JavaProjectFileNames.JAVA_FILE_EXTENSION;
		new FileStreamWriter().writeStream(new File(sourceFileStoragePath), sourceCode);
	}

	/**
	 * create the database connection properties class
	 * 
	 * @throws IOException
	 */
	private void createDatabaseConnectionPropertiesClass(J2SEProjectConfiguration j2seProjectConfiguration,
			Database database, String mysqlAPIsStorageLocation) throws IOException {
		MYSQLDatabaseConnectionPropertiesTemplateParser mysqlDatabaseConnectionPropertiesTemplateParser = new MYSQLDatabaseConnectionPropertiesTemplateParser();
		String sourceCode = mysqlDatabaseConnectionPropertiesTemplateParser
				.parseTransactionsWrapper(j2seProjectConfiguration, database, mysqlAPIsStorageLocation);
		String sourceFile = JavaProjectFileNames.MYSQL_DATABASE_CONNECTION_PROPERTIES_CLASS_NAME;
		String sourceFileStoragePath = mysqlAPIsStorageLocation + NativeUtils.getFileSeparator() + sourceFile
				+ JavaProjectFileNames.JAVA_FILE_EXTENSION;
		new FileStreamWriter().writeStream(new File(sourceFileStoragePath), sourceCode);
	}

}
