package com.marvik.apis.dbcrudgen;

import java.util.ArrayList;
import java.util.List;

import com.marvik.apis.dbcrudgen.creator.android.AndroidCRUDCreator;
import com.marvik.apis.dbcrudgen.creator.php.PHPCrudCreator;
import com.marvik.apis.dbcrudgen.database.connection.project.ProjectDatabaseConnectionProperties;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidContentProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidDatabaseConfiguration;
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
		testAndroidCrudGenerator();
		testPHPCrudGenerator();
	}

	private static void testAndroidCrudGenerator() {

		Database database = prepareDatabase();

		AndroidDatabaseConfiguration androidDatabaseConfiguration = new AndroidDatabaseConfiguration(
				database.getDatabaseName(), 1, "DatabaseManager", "database\\sqliteopenhelper", "database\\tables",
				"database\\tablescrud", "database\\tablesinfos");
		AndroidContentProviderConfiguration androidContentProviderConfiguration = new AndroidContentProviderConfiguration(
				"DataProvider", "database\\contentprovider", androidDatabaseConfiguration);
		AndroidProjectConfiguration androidProjectConfiguration = new AndroidProjectConfiguration(
				"C:\\Users\\victor\\Desktop\\dbcrudgenerator\\outputs\\android\\sampleproject",
				androidContentProviderConfiguration);

		AndroidCRUDCreator androidCRUDCreator = new AndroidCRUDCreator();
		androidCRUDCreator.setAndroidProjectConfiguration(androidProjectConfiguration);
		androidCRUDCreator.createProject(database);
	}

	private static void testPHPCrudGenerator() {

		Database database = prepareDatabase();

		PHPProjectConfiguration phpProjectConfiguration = new PHPProjectConfiguration("where_there_is_no_doc");
		phpProjectConfiguration.setProjectStorageDirectory("C:\\xampp\\htdocs\\where_there_is_no_doc\\");
		phpProjectConfiguration.setProjectCRUDScriptsStorageDirectory(
				"C:\\xampp\\htdocs\\where_there_is_no_doc\\scripts\\php\\database\\crud\\");
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
		List<Table> tablesList = new ArrayList<Table>();

		addTableFirstAids(tablesList);
		addTableFirstAidsCategories(tablesList);
		addTableExaminingSickPerson(tablesList);

		Table[] tables = new Table[tablesList.size()];
		for (int i = 0; i < tablesList.size(); i++) {
			tables[i] = tablesList.get(i);
		}

		Database database = new Database("where_there_is_no_doc", tables);
		return database;
	}

	/**
	 * 
	 * @param tablesList
	 */
	private static void addTableExaminingSickPerson(List<Table> tablesList) {
	
		
		List<TableColumn> examiningSickPersonColumnList = new ArrayList<TableColumn>();
		examiningSickPersonColumnList.add(
				new TableColumn("examination_title", new DataType("text", new Constraints("text not null"))));
		examiningSickPersonColumnList.add(
				new TableColumn("examination_description", new DataType("text", new Constraints("text not null"))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[examiningSickPersonColumnList.size()];

		for (int i = 0; i < examiningSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = examiningSickPersonColumnList.get(i);
		}
		String tableSql = "create table examining_sick_person "
		+"( id_examination integer primary key auto_increment,"
		+" examination_title text not null,"
		 +"examination_description text not null);";

		PrimaryKey primaryKey = new PrimaryKey("id_examination",new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(new Table("examining_sick_person", examiningSickPersonColumns, tableSql, primaryKey, null, null));
		
	}

	/**
	 * @param tablesList
	 */
	private static void addTableFirstAids(List<Table> tablesList) {
		List<TableColumn> firstAidColumnList = new ArrayList<TableColumn>();
		firstAidColumnList
				.add(new TableColumn("ailment", new DataType("varchar", new Constraints("varchar(128) NOT NULL"))));
		firstAidColumnList.add(new TableColumn("ailment_information", new DataType("text", new Constraints())));
		firstAidColumnList.add(new TableColumn("ailment_causes", new DataType("text", new Constraints())));
		firstAidColumnList.add(new TableColumn("ailment_prevention", new DataType("text", new Constraints())));
		firstAidColumnList.add(new TableColumn("ailment_signs", new DataType("text", new Constraints())));
		firstAidColumnList.add(new TableColumn("ailment_symptoms", new DataType("text", new Constraints())));
		firstAidColumnList.add(new TableColumn("ailment_cautions", new DataType("text", new Constraints())));
		firstAidColumnList.add(new TableColumn("ailment_medication", new DataType("text", new Constraints())));
		firstAidColumnList.add(new TableColumn("ailment_treatment", new DataType("text", new Constraints())));
		firstAidColumnList
				.add(new TableColumn("ailment_treatment_precautions", new DataType("text", new Constraints())));
		firstAidColumnList.add(new TableColumn("ailment_treatment_position", new DataType("text", new Constraints())));
		firstAidColumnList.add(new TableColumn("ailment_short_notes", new DataType("text", new Constraints())));

		TableColumn[] firstAidColumns = new TableColumn[firstAidColumnList.size()];
		for (int i = 0; i < firstAidColumnList.size(); i++) {
			firstAidColumns[i] = firstAidColumnList.get(i);
		}
		String tableSql = "CREATE TABLE IF NOT EXISTS `firstaids`"
				+ " (`ailment` varchar(128) NOT NULL,`ailment_information` text,"
				+ "`ailment_causes` text,`ailement_prevention` text,`ailment_signs` text,"
				+ "`ailment_symptoms` text,`ailment_cautions` text,`ailment_medication` text,"
				+ "`ailment_treatmeant` text,`ailment_treatmeant_precautions` text,"
				+ "`ailment_treatment_position` text, `ailment_short_notes` text,"
				+ "`id_firstaid` integer primary key auto_increment);";

		PrimaryKey primaryKey = new PrimaryKey("id_firstaid",
				new DataType("integer", new Constraints("primary key auto_increment")));

		tablesList.add(new Table("firstaids", firstAidColumns, tableSql, primaryKey, null, null));
	}

	/**
	 * @param tablesList
	 */
	private static void addTableFirstAidsCategories(List<Table> tablesList) {
		List<TableColumn> firstAidCategoriesColumnList = new ArrayList<TableColumn>();
		firstAidCategoriesColumnList.add(
				new TableColumn("category_name", new DataType("varchar", new Constraints("varchar(128) NOT NULL"))));

		TableColumn[] firstAidColumns = new TableColumn[firstAidCategoriesColumnList.size()];

		for (int i = 0; i < firstAidCategoriesColumnList.size(); i++) {
			firstAidColumns[i] = firstAidCategoriesColumnList.get(i);
		}
		String tableSql = "CREATE TABLE IF NOT EXISTS `firstaid_categories` ("
				+ "`id_firstaid_category` int(11) NOT NULL," + " `category_name` varchar(128) NOT NULL)";

		PrimaryKey primaryKey = new PrimaryKey("id_firstaid_category",
				new DataType("integer", new Constraints("primary key auto_increment")));

		tablesList.add(new Table("firstaid_categories", firstAidColumns, tableSql, primaryKey, null, null));
	}

	private static void print(String string) {
		System.err.println(string);
	}
}
