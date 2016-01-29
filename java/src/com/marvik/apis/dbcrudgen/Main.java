package com.marvik.apis.dbcrudgen;

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
		//testAndroidCrudGenerator();
		testPHPCrudGenerator();
	}

	private static void testAndroidCrudGenerator() {

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
				"G:\\MarvikApps2016\\WTND", "app\\src\\main\\java", "com.victor.apps.wtnd",
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
		addTableTakingCareOfTheSick(tablesList);
		addTableDangerousMedicine(tablesList);
		addTableAntibioticsAndHowToUseThem(tablesList);
		addTableHealthProblemsSpecialDiet(tablesList);
		addTableWormsAndIntestinalParasites(tablesList);
		addTableVaccinations(tablesList);
		addTableCommonSickness(tablesList);
		addTableSkinProblems(tablesList);
		addTableEyeProblems(tablesList);
		addTableTeethGumsEyesProblems(tablesList);
		addTableGenitalProblems(tablesList);
		addTableFamilyProblems(tablesList);
		addTableChildrenSickness(tablesList);
		addTableOldAgeSickness(tablesList); 

		Table[] tables = new Table[tablesList.size()];
		for (int i = 0; i < tablesList.size(); i++) {
			tables[i] = tablesList.get(i);
		}

		Database database = new Database("where_there_is_no_doc", tables);
		return database;
	}



	private static void addTableOldAgeSickness(List<Table> tablesList) {

		List<TableColumn> takingCareOfSickPersonColumnList = new ArrayList<TableColumn>();
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness", new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_info", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_causes", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_signs", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_treatment", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_treatment_precautions", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_prevention", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_caution", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_note", new DataType("text", new Constraints("text "))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[takingCareOfSickPersonColumnList.size()];

		for (int i = 0; i < takingCareOfSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = takingCareOfSickPersonColumnList.get(i);
		}
		String tableSql = "create table oldage_sickness ( id_sickness integer primary key auto_increment, sickness text not null, sickness_info text, sickness_causes text,"
				+ "sickness_signs text,  sickness_treatment text,  sickness_treatment_precautions text, sickness_prevention text, sickness_caution text, sickness_note text );";

		PrimaryKey primaryKey = new PrimaryKey("id_sickness",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(new Table("oldage_sickness", examiningSickPersonColumns, tableSql, primaryKey, null, null));

	}

	private static void addTableChildrenSickness(List<Table> tablesList) {

		List<TableColumn> takingCareOfSickPersonColumnList = new ArrayList<TableColumn>();
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness", new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_info", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_causes", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_signs", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_treatment", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_treatment_precautions", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_prevention", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_caution", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sickness_note", new DataType("text", new Constraints("text "))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[takingCareOfSickPersonColumnList.size()];

		for (int i = 0; i < takingCareOfSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = takingCareOfSickPersonColumnList.get(i);
		}
		String tableSql = "create table children_sickness (id_sickness integer primary key auto_increment, sickness text not null, sickness_info text, sickness_causes text, sickness_signs text,"
				+ "sickness_treatment text, sickness_treatment_precautions text, sickness_prevention text, sickness_caution text, sickness_note text );";

		PrimaryKey primaryKey = new PrimaryKey("id_sickness",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(new Table("children_sickness", examiningSickPersonColumns, tableSql, primaryKey, null, null));

	}

	private static void addTableFamilyProblems(List<Table> tablesList) {

		List<TableColumn> takingCareOfSickPersonColumnList = new ArrayList<TableColumn>();
		takingCareOfSickPersonColumnList
				.add(new TableColumn("family_planning_method", new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList.add(
				new TableColumn("family_planning_method_information", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("method_precautions", new DataType("text", new Constraints("text "))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[takingCareOfSickPersonColumnList.size()];

		for (int i = 0; i < takingCareOfSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = takingCareOfSickPersonColumnList.get(i);
		}
		String tableSql = "create table family_planning (  id_family_planning_method integer primary key auto_increment,"
				+ "family_planning_method text not null, family_planning_method_information text , method_precautions text);";

		PrimaryKey primaryKey = new PrimaryKey("id_family_planning_method",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(new Table("family_planning", examiningSickPersonColumns, tableSql, primaryKey, null, null));

	}

	private static void addTableGenitalProblems(List<Table> tablesList) {

		List<TableColumn> takingCareOfSickPersonColumnList = new ArrayList<TableColumn>();
		takingCareOfSickPersonColumnList
				.add(new TableColumn("genital_problem", new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("genital_problem_information", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("genital_problem_problems", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("genital_problem_signs", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("genital_problem_treatment", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("genital_problem_prevention", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("genital_problem_caution", new DataType("text", new Constraints("text "))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[takingCareOfSickPersonColumnList.size()];

		for (int i = 0; i < takingCareOfSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = takingCareOfSickPersonColumnList.get(i);
		}
		String tableSql = "create table genital_problems( id_genital_problem integer primary key auto_increment, genital_problem text not null, genital_problem_information text, genital_problem_problems text,"
				+ "genital_problem_signs text,  genital_problem_treatment text, genital_problem_prevention text, genital_problem_caution text  );";

		PrimaryKey primaryKey = new PrimaryKey("id_genital_problem",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(new Table("genital_problems", examiningSickPersonColumns, tableSql, primaryKey, null, null));
	}

	private static void addTableTeethGumsEyesProblems(List<Table> tablesList) {

		List<TableColumn> takingCareOfSickPersonColumnList = new ArrayList<TableColumn>();
		takingCareOfSickPersonColumnList
				.add(new TableColumn("problem", new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("problem_information", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("problem_treatment", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("problem_prevention", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("problem_caution", new DataType("text", new Constraints("text "))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[takingCareOfSickPersonColumnList.size()];

		for (int i = 0; i < takingCareOfSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = takingCareOfSickPersonColumnList.get(i);
		}
		String tableSql = "create table teeth_gums_eyes_problems ( id_problem integer primary key auto_increment, problem text not null,"
				+ " problem_information text ,  problem_treatment text ,  problem_prevention text , problem_caution text  );";

		PrimaryKey primaryKey = new PrimaryKey("id_problem",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(
				new Table("teeth_gums_eyes_problems", examiningSickPersonColumns, tableSql, primaryKey, null, null));
	}

	private static void addTableEyeProblems(List<Table> tablesList) {

		List<TableColumn> takingCareOfSickPersonColumnList = new ArrayList<TableColumn>();
		takingCareOfSickPersonColumnList
				.add(new TableColumn("eye_problem", new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("eye_problem_info", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("eye_problem_signs", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("eye_problem_treatment", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("eye_problem_prevention", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("eye_problem_warning", new DataType("text", new Constraints("text "))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[takingCareOfSickPersonColumnList.size()];

		for (int i = 0; i < takingCareOfSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = takingCareOfSickPersonColumnList.get(i);
		}
		String tableSql = "create table  eye_problems ( id_eye_problem integer primary key auto_increment, eye_problem text not null, eye_problem_info text,"
				+ "eye_problem_signs text, eye_problem_treatment text, eye_problem_prevention text, eye_problem_warning text );";

		PrimaryKey primaryKey = new PrimaryKey("id_eye_problem",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(new Table("eye_problems", examiningSickPersonColumns, tableSql, primaryKey, null, null));
	}

	private static void addTableSkinProblems(List<Table> tablesList) {

		List<TableColumn> takingCareOfSickPersonColumnList = new ArrayList<TableColumn>();
		takingCareOfSickPersonColumnList
				.add(new TableColumn("skin_problem", new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("skin_problem_information", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("treatment", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("prevention", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("skin_problem_caution", new DataType("text", new Constraints("text "))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[takingCareOfSickPersonColumnList.size()];

		for (int i = 0; i < takingCareOfSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = takingCareOfSickPersonColumnList.get(i);
		}
		String tableSql = "create table skin_problems ( id_skin_problem integer primary key auto_increment,"
				+ "skin_problem text not null, skin_problem_information text, treatment text , prevention text, skin_problem_caution text );";

		PrimaryKey primaryKey = new PrimaryKey("id_skin_problem",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(new Table("skin_problems", examiningSickPersonColumns, tableSql, primaryKey, null, null));
	}

	private static void addTableCommonSickness(List<Table> tablesList) {

		List<TableColumn> takingCareOfSickPersonColumnList = new ArrayList<TableColumn>();
		takingCareOfSickPersonColumnList
				.add(new TableColumn("common_sickness", new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("common_sickness_information", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("common_sickness_causes", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("common_sickness_signs", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("common_sickness_variants", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("common_sickness_prevention", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("common_sickness_treatment", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("common_sickness_medicine", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("common_sickness_caution", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("common_sickness_note", new DataType("text", new Constraints("text "))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[takingCareOfSickPersonColumnList.size()];

		for (int i = 0; i < takingCareOfSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = takingCareOfSickPersonColumnList.get(i);
		}
		String tableSql = "create table common_sickness ( id_common_sickness integer primary key auto_increment, common_sickness text not null, common_sickness_information text , common_sickness_causes text,common_sickness_signs text, common_sickness_variants text,"
				+ "common_sickness_prevention text, common_sickness_treatment text , common_sickness_medicine text, common_sickness_caution text , common_sickness_note text  )";

		PrimaryKey primaryKey = new PrimaryKey("id_common_sickness",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(new Table("common_sickness", examiningSickPersonColumns, tableSql, primaryKey, null, null));
	}

	private static void addTableVaccinations(List<Table> tablesList) {

		List<TableColumn> takingCareOfSickPersonColumnList = new ArrayList<TableColumn>();
		takingCareOfSickPersonColumnList
				.add(new TableColumn("immunization", new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList.add(
				new TableColumn("immunization_information", new DataType("text", new Constraints("text not null"))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[takingCareOfSickPersonColumnList.size()];

		for (int i = 0; i < takingCareOfSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = takingCareOfSickPersonColumnList.get(i);
		}
		String tableSql = "create table vaccinations ( id_immunization integer primary key auto_increment, immunization text not null, immunization_information text not null );";

		PrimaryKey primaryKey = new PrimaryKey("id_immunization",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(new Table("vaccinations", examiningSickPersonColumns, tableSql, primaryKey, null, null));
	}

	private static void addTableWormsAndIntestinalParasites(List<Table> tablesList) {

		List<TableColumn> takingCareOfSickPersonColumnList = new ArrayList<TableColumn>();
		takingCareOfSickPersonColumnList.add(new TableColumn("worm_and_intestinal_parasite",
				new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList.add(new TableColumn("worm_and_intestinal_parasite_information",
				new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("infection_signs", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("transmission", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("health_effect", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("treatment_and_prevention", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList.add(new TableColumn("worm_and_intestinal_parasite_caution",
				new DataType("text", new Constraints("text "))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[takingCareOfSickPersonColumnList.size()];

		for (int i = 0; i < takingCareOfSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = takingCareOfSickPersonColumnList.get(i);
		}
		String tableSql = "create table worms_and_intestinal_parasites ( id_worm_and_intestinal_parasite integer primary key auto_increment, worm_and_intestinal_parasite text not null, worm_and_intestinal_parasite_information text,"
				+ "infection_signs text, transmission text ,  health_effect text ,  treatment_and_prevention text , worm_and_intestinal_parasite_caution text  );";

		PrimaryKey primaryKey = new PrimaryKey("id_worm_and_intestinal_parasite",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(new Table("worms_and_intestinal_parasites", examiningSickPersonColumns, tableSql, primaryKey,
				null, null));
	}

	private static void addTableHealthProblemsSpecialDiet(List<Table> tablesList) {

		List<TableColumn> takingCareOfSickPersonColumnList = new ArrayList<TableColumn>();
		takingCareOfSickPersonColumnList
				.add(new TableColumn("health_problem", new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList.add(
				new TableColumn("health_problem_information", new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("health_problem_signs", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("health_problem_signs_advanced", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("health_problem_treatment", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("health_problem_prevention", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("health_problem_caution", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("health_problem_note", new DataType("text", new Constraints("text "))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("special_diet", new DataType("text", new Constraints("text "))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[takingCareOfSickPersonColumnList.size()];

		for (int i = 0; i < takingCareOfSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = takingCareOfSickPersonColumnList.get(i);
		}
		String tableSql = "create table special_diets( id_health_problem integer primary  key auto_increment, health_problem text not null, health_problem_information not null, health_problem_signs text ,"
				+ " health_problem_signs_advanced text , health_problem_treatment text , health_problem_prevention text ,  health_problem_caution text , health_problem_note text, special_diet text not null );";

		PrimaryKey primaryKey = new PrimaryKey("id_health_problem",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(new Table("special_diets", examiningSickPersonColumns, tableSql, primaryKey,
				null, null));
	}

	private static void addTableAntibioticsAndHowToUseThem(List<Table> tablesList) {

		List<TableColumn> takingCareOfSickPersonColumnList = new ArrayList<TableColumn>();
		takingCareOfSickPersonColumnList
				.add(new TableColumn("item_title", new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("item_description", new DataType("text", new Constraints("text not null"))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[takingCareOfSickPersonColumnList.size()];

		for (int i = 0; i < takingCareOfSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = takingCareOfSickPersonColumnList.get(i);
		}
		String tableSql = "create table antibiotics ( id_item int primary key auto_increment, item_title text not null, item_description text not null  )";

		PrimaryKey primaryKey = new PrimaryKey("id_item",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(new Table("antibiotics", examiningSickPersonColumns, tableSql, primaryKey,
				null, null));
	}

	private static void addTableDangerousMedicine(List<Table> tablesList) {

		List<TableColumn> takingCareOfSickPersonColumnList = new ArrayList<TableColumn>();
		takingCareOfSickPersonColumnList
				.add(new TableColumn("medicine", new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("misuse", new DataType("text", new Constraints("text not null"))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[takingCareOfSickPersonColumnList.size()];

		for (int i = 0; i < takingCareOfSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = takingCareOfSickPersonColumnList.get(i);
		}
		String tableSql = "create table dangerous_medicine_misuse (" + "id_medicine integer primary key auto_increment,"
				+ "medicine text not null," + "misuse text not null  )";

		PrimaryKey primaryKey = new PrimaryKey("id_medicine",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(
				new Table("dangerous_medicine_misuse", examiningSickPersonColumns, tableSql, primaryKey, null, null));
	}

	private static void addTableTakingCareOfTheSick(List<Table> tablesList) {

		List<TableColumn> takingCareOfSickPersonColumnList = new ArrayList<TableColumn>();
		takingCareOfSickPersonColumnList
				.add(new TableColumn("care_item", new DataType("text", new Constraints("text not null"))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("sick_person_care", new DataType("text", new Constraints("text"))));
		takingCareOfSickPersonColumnList
				.add(new TableColumn("very_sick_person_care", new DataType("text", new Constraints("text"))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[takingCareOfSickPersonColumnList.size()];

		for (int i = 0; i < takingCareOfSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = takingCareOfSickPersonColumnList.get(i);
		}
		String tableSql = "create table taking_care_of_sick(" + " id_care_item integer primary key auto_increment,"
				+ " care_item text not null," + " sick_person_care text," + " very_sick_person_care text)";

		PrimaryKey primaryKey = new PrimaryKey("id_care_item",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList.add(new Table("taking_care_of_sick", examiningSickPersonColumns, tableSql, primaryKey, null, null));

	}

	/**
	 * 
	 * @param tablesList
	 */
	private static void addTableExaminingSickPerson(List<Table> tablesList) {

		List<TableColumn> examiningSickPersonColumnList = new ArrayList<TableColumn>();
		examiningSickPersonColumnList
				.add(new TableColumn("examination_title", new DataType("text", new Constraints("text not null"))));
		examiningSickPersonColumnList.add(
				new TableColumn("examination_description", new DataType("text", new Constraints("text not null"))));

		TableColumn[] examiningSickPersonColumns = new TableColumn[examiningSickPersonColumnList.size()];

		for (int i = 0; i < examiningSickPersonColumnList.size(); i++) {
			examiningSickPersonColumns[i] = examiningSickPersonColumnList.get(i);
		}
		String tableSql = "create table examining_sick_person " + "( id_examination integer primary key auto_increment,"
				+ " examination_title text not null," + "examination_description text not null);";

		PrimaryKey primaryKey = new PrimaryKey("id_examination",
				new DataType("integer", new Constraints("primary key auto_increment")));
		tablesList
				.add(new Table("examining_sick_person", examiningSickPersonColumns, tableSql, primaryKey, null, null));

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
