package com.marvik.apis.dbcrudgen;

import com.marvik.apis.dbcrudgen.application.tasks.TasksExecutor;
import com.marvik.apis.dbcrudgen.creator.android.AndroidCRUDCreator;
import com.marvik.apis.dbcrudgen.creator.j2se.J2SECrudCreator;
import com.marvik.apis.dbcrudgen.creator.php.PHPCrudCreator;
import com.marvik.apis.dbcrudgen.database.connection.DatabaseConnectionProperties;
import com.marvik.apis.dbcrudgen.database.connection.project.ProjectDatabaseConnectionProperties;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidContentProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.AndroidDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.provider.ProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.transactions.TransactionManagerConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectMYSQLDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.projects.php.configuration.PHPProjectConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;

public class Main {
	public static void main(String[] args) {
		// testAndroidCrudGenerator();
		// testPHPCrudGenerator();
		testJ2SECrudCreator();
	}

	private static void testJ2SECrudCreator() {

		Database database = prepareDatabase();

		String projectName = "WTNDJ2SE";
		String packageName = "com.victor.j2se.wtnd";
		String projectStorageDir = "G:\\4thYr";
		String javaSrcDirs = "src";
		String libsSrcDirs = "lib";

		J2SEProjectConfiguration j2seProjectConfiguration = new J2SEProjectConfiguration(projectName, packageName,
				projectStorageDir, javaSrcDirs, libsSrcDirs);

		String databaseHost = "localhost";
		String databaseUser = "root";
		String databaseUserPassword = "";
		String databaseName = "where_there_is_no_doc";
		DatabaseConnectionProperties databaseConnectionProperties = new DatabaseConnectionProperties(databaseHost,
				databaseUser, databaseUserPassword, databaseName);
		String mysqlAPIsClassesSrcDirs = "coreutils";
		J2SEProjectMYSQLDatabaseConfiguration j2seProjectMYSQLDatabaseConfiguration = new J2SEProjectMYSQLDatabaseConfiguration(
				mysqlAPIsClassesSrcDirs, databaseConnectionProperties);

		j2seProjectConfiguration.setJ2SEProjectMYSQLDatabaseConfiguration(j2seProjectMYSQLDatabaseConfiguration);

		J2SECrudCreator j2seCrudCreator = new J2SECrudCreator();
		j2seCrudCreator.createProject(j2seProjectConfiguration, database);
	}

	private static void testAndroidCrudGenerator() {

		Database database = prepareDatabase();

		AndroidDatabaseConfiguration androidDatabaseConfiguration = new AndroidDatabaseConfiguration(
				database.getDatabaseName(), 1, "DatabaseManager", "database\\sqliteopenhelper",
				"database\\tableschemas", "database\\tablescrud", "database\\tablemodels");

		String contentProviderPackage = "database\\contentprovider";
		String contentProviderClass = "DataProvider";
		ProviderConfiguration providerConfiguration = new ProviderConfiguration(contentProviderClass,
				contentProviderPackage);

		String transactionManagerPackage = "database\\transactions";
		String transactionManagerClass = "TransactionsManager";
		TransactionManagerConfiguration transactionManagerConfiguration = new TransactionManagerConfiguration(
				transactionManagerPackage, transactionManagerClass);

		AndroidContentProviderConfiguration androidContentProviderConfiguration = new AndroidContentProviderConfiguration(
				providerConfiguration, transactionManagerConfiguration, androidDatabaseConfiguration);

		AndroidProjectConfiguration androidProjectConfiguration = new AndroidProjectConfiguration(
				"G:\\MarvikApps\\AndroidStudioProjects\\2016\\WTND", "app\\src\\main\\java", "com.victor.apps.wtnd",
				androidContentProviderConfiguration);

		AndroidCRUDCreator androidCRUDCreator = new AndroidCRUDCreator();
		androidCRUDCreator.setAndroidProjectConfiguration(androidProjectConfiguration);
		androidCRUDCreator.createProject(database);
	}

	private static void testPHPCrudGenerator() {

		Database database = prepareDatabase();

		PHPProjectConfiguration phpProjectConfiguration = new PHPProjectConfiguration("where_there_is_no_doc");
		phpProjectConfiguration.setProjectStorageDirectory("C:\\xampp\\htdocs\\where_there_is_no_doc\\");
		phpProjectConfiguration.setProjectPHPTableCrudLowLevelScriptsStorageDirectory(
				"C:\\xampp\\htdocs\\where_there_is_no_doc\\scripts\\php\\database\\crud\\");
		phpProjectConfiguration.setProjectPHPTableCrudHighLevelScriptsStorageDirectory(
				"C:\\xampp\\htdocs\\where_there_is_no_doc\\scripts\\php\\database\\modules\\");
		phpProjectConfiguration.setProjectPHPDatabaseAPIScriptsStorageDirectory(
				"C:\\xampp\\htdocs\\where_there_is_no_doc\\scripts\\php\\database\\core-apis\\");
		phpProjectConfiguration.setProjectSQLScriptsStorageDirectory(
				"C:\\xampp\\htdocs\\where_there_is_no_doc\\scripts\\php\\database\\sql\\");

		ProjectDatabaseConnectionProperties projectDatabaseConnectionProperties = new ProjectDatabaseConnectionProperties(
				"localhost", "root", "", "where_there_is_no_doc");

		PHPCrudCreator phpCrudCreator = new PHPCrudCreator();
		phpCrudCreator.setProjectConfiguration(phpProjectConfiguration);
		phpCrudCreator.setProjectDatabaseConnectionProperties(projectDatabaseConnectionProperties);
		phpCrudCreator.createProject(database);

	}

	/**
	 * @return
	 */
	private static Database prepareDatabase() {
		TasksExecutor tasksExecutor = new TasksExecutor();
		return tasksExecutor.createDatabaseModel("where_there_is_no_doc");
	}

}
