package com.marvik.apis.dbcrudgen;

import java.util.ArrayList;
import java.util.List;

import com.marvik.apis.dbcrudgen.creator.php.PHPCrudCreator;
import com.marvik.apis.dbcrudgen.schemamodels.columns.Columns;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.PrimaryKeys;
import com.marvik.apis.dbcrudgen.schemamodels.constraints.Constraints;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.sql.parser.SQLParser;
import com.marvik.apis.dbcudgen.java.templates.php.PHPColumnsCrudTemplate;
import com.marvik.apis.dbcudgen.java.templates.sql.SQLTablesTemplate;

public class Main {
	public static void main(String[] args) {

		SQLTablesTemplate sQLTablesTemplate = new SQLTablesTemplate();
		// System.out.println(sQLTablesTemplate.getTemplate());

		PHPColumnsCrudTemplate columnsCrudTemplate = new PHPColumnsCrudTemplate();
		// System.out.println(columnsCrudTemplate.getTemplate());

		SQLParser sqlParser = new SQLParser();
		/*
		 * Database database = sqlParser.getDatabaseSchemas(new SQLReader(),
		 * TestRes.TEST_SQL_FILE);
		 * System.out.println(database.getDatabaseName()); for (Table table :
		 * database.getTables()) { System.out.println(table.getTableName());
		 * System.out.println(table.getTableSql()); }
		 */

		List<Table> tablesList = new ArrayList<Table>();

		List<Columns> firstAidColumnList = new ArrayList<Columns>();
		firstAidColumnList.add(
				new Columns("id_firstaid", new DataType("int", new Constraints("integer primary key auto_increment"))));
		firstAidColumnList
				.add(new Columns("ailment", new DataType("varchar", new Constraints("varchar(128) NOT NULL"))));
		firstAidColumnList.add(new Columns("ailment_information", new DataType("text", new Constraints())));
		firstAidColumnList.add(new Columns("ailment_causes", new DataType("text", new Constraints())));
		firstAidColumnList.add(new Columns("ailement_prevention", new DataType("text", new Constraints())));
		firstAidColumnList.add(new Columns("ailment_signs", new DataType("text", new Constraints())));
		firstAidColumnList.add(new Columns("ailment_symptoms", new DataType("text", new Constraints())));
		firstAidColumnList.add(new Columns("ailment_cautions", new DataType("text", new Constraints())));
		firstAidColumnList.add(new Columns("ailment_medication", new DataType("text", new Constraints())));
		firstAidColumnList.add(new Columns("ailment_treatmeant", new DataType("text", new Constraints())));
		firstAidColumnList.add(new Columns("ailment_treatmeant_precautions", new DataType("text", new Constraints())));
		firstAidColumnList.add(new Columns("ailment_treatment_position", new DataType("text", new Constraints())));
		firstAidColumnList.add(new Columns("ailment_short_notes", new DataType("text", new Constraints())));

		Columns[] firstAidColumns = new Columns[firstAidColumnList.size()];
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
		
		PrimaryKeys primaryKeys = new PrimaryKeys(new String[] { "id_firstaid" ,"ailment_medication","ailment_treatmeant_precautions"});
		
		tablesList.add(new Table("firstaids", firstAidColumns, tableSql, primaryKeys, null, null));

		Table[] tables = new Table[tablesList.size()];
		for (int i = 0; i < tablesList.size(); i++) {
			tables[i] = tablesList.get(i);
		}

		Database database = new Database("where_there_is_no_doc", tables);
		PHPCrudCreator phpCrudCreator = new PHPCrudCreator();

		for (Table table : database.getTables()) {
			String tablsesCrud = phpCrudCreator.getTableCrud(table);
			print(tablsesCrud);
		}

	}

	private static void print(String string) {
		System.err.println(string);
	}
}
