package com.foo;

import java.util.ArrayList;
import java.util.List;

import com.marvik.apis.dbcrudgen.creator.android.AndroidCRUDCreator;
import com.marvik.apis.dbcrudgen.creator.php.PHPCrudCreator;
import com.marvik.apis.dbcrudgen.database.connection.project.ProjectDatabaseConnectionProperties;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidContentProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.AndroidDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.provider.ProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.transactions.TransactionManagerConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.php.configuration.PHPProjectConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.PrimaryKey;
import com.marvik.apis.dbcrudgen.schemamodels.constraints.Constraints;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;

public class Main {
	public static void main(String[] args) {

		testProjectPHPCrudGenerator();
	}

	private static void testProjectAndroidCrudGenerator() {

		Database database = prepareDatabase();

		AndroidDatabaseConfiguration androidDatabaseConfiguration = new AndroidDatabaseConfiguration(
				database.getDatabaseName(), 1, "DatabaseManager", "database\\sqliteopenhelper", "database\\tables",
				"database\\tablescrud", "database\\tablesinfos");

		String contentProviderPackage = "database\\contentprovider";
		String contentProviderClass = "DataProvider";
		ProviderConfiguration providerConfiguration = new ProviderConfiguration(contentProviderClass,
				contentProviderPackage);

		String transactionManagerPackage = "database\\transactions";
		String transactionManagerClass = "TransactionsManager";
		TransactionManagerConfiguration transactionManagerConfiguration = new TransactionManagerConfiguration(
				transactionManagerPackage, transactionManagerClass);

		AndroidContentProviderConfiguration androidContentProviderConfiguration = new AndroidContentProviderConfiguration(
				providerConfiguration,transactionManagerConfiguration, androidDatabaseConfiguration);
		
		AndroidProjectConfiguration androidProjectConfiguration = new AndroidProjectConfiguration(
				"G:\\MarvikApps2016\\TEST_TABLE", "app\\src\\main\\java", "com.kevo",
				androidContentProviderConfiguration);

		AndroidCRUDCreator androidCRUDCreator = new AndroidCRUDCreator();
		androidCRUDCreator.setAndroidProjectConfiguration(androidProjectConfiguration);
		androidCRUDCreator.createProject(database);
	}

	private static void testProjectPHPCrudGenerator() {

		Database database = prepareDatabase();

		PHPProjectConfiguration phpProjectConfiguration = new PHPProjectConfiguration("mafisi");
		phpProjectConfiguration.setProjectStorageDirectory("C:\\xampp\\htdocs\\mafisi\\");
		phpProjectConfiguration.setProjectPHPTableCrudLowLevelScriptsStorageDirectory(
				"C:\\xampp\\htdocs\\mafisi\\scripts\\php\\database\\crud\\");
		phpProjectConfiguration.setProjectPHPTableCrudHighLevelScriptsStorageDirectory(
				"C:\\xampp\\htdocs\\mafisi\\scripts\\php\\database\\modules\\");
		phpProjectConfiguration.setProjectPHPDatabaseAPIScriptsStorageDirectory(
				"C:\\xampp\\htdocs\\mafisi\\scripts\\php\\database\\core-apis\\");
		phpProjectConfiguration.setProjectSQLScriptsStorageDirectory(
				"C:\\xampp\\htdocs\\mafisi\\scripts\\php\\database\\sql\\");

		ProjectDatabaseConnectionProperties projectDatabaseConnectionProperties = new ProjectDatabaseConnectionProperties(
				"localhost", "root", "", "mafisi_db");

		PHPCrudCreator phpCrudCreator = new PHPCrudCreator();
		phpCrudCreator.setProjectConfiguration(phpProjectConfiguration);
		phpCrudCreator.setProjectDatabaseConnectionProperties(projectDatabaseConnectionProperties);
		phpCrudCreator.createProject(database);

	}

	/**
	 * @return
	 */
	private static Database prepareDatabase() {
		List<Table> tablesList = new ArrayList<Table>();

		addLinks(tablesList);

		
		Table[] tables = new Table[tablesList.size()];
		for (int i = 0; i < tablesList.size(); i++) {
			tables[i] = tablesList.get(i);
		}

		Database database = new Database("mafisi_db", tables);
		return database;
	}



	
	/**
	 * @param tablesList
	 */
	private static void addLinks(List<Table> tablesList) {
		
		List<TableColumn> linksTableColumns = new ArrayList<TableColumn>();
		linksTableColumns.add(
				new TableColumn("firstname", new DataType("varchar", new Constraints("(200) NOT NULL"))));
		linksTableColumns.add(
				new TableColumn("lastname", new DataType("varchar", new Constraints("(200) NOT NULL"))));
		linksTableColumns.add(
				new TableColumn("email", new DataType("varchar", new Constraints("(200) NOT NULL"))));
		linksTableColumns.add(
				new TableColumn("phone", new DataType("varchar", new Constraints("(200) NOT NULL"))));
		linksTableColumns.add(
				new TableColumn("year_of_birth", new DataType("varchar", new Constraints("(200) NOT NULL"))));
		linksTableColumns.add(
				new TableColumn("address", new DataType("varchar", new Constraints("(200) NOT NULL"))));

		TableColumn[] firstAidColumns = new TableColumn[linksTableColumns.size()];

		for (int i = 0; i < linksTableColumns.size(); i++) {
			firstAidColumns[i] = linksTableColumns.get(i);
		}
		String tableSql = "create table user ( id_user integer primary key auto_increment,firstname varchar (50) not null, lastname varchar (50) not null, email varchar (50) not null, phone varchar (50) not null, year_of_birth varchar (50) not null, address varchar (50) not null )";

		PrimaryKey primaryKey = new PrimaryKey("id_user",
				new DataType("integer", new Constraints("primary key auto_increment")));
		

		tablesList.add(new Table("user", firstAidColumns, tableSql, primaryKey));
	
	}

	

}
