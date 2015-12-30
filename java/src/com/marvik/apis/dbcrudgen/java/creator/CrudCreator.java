package com.marvik.apis.dbcrudgen.java.creator;

import java.util.ArrayList;
import java.util.List;

import com.marvik.apis.dbcrudgen.java.schemamodels.columns.Columns;
import com.marvik.apis.dbcrudgen.java.schemamodels.constraints.Constraints;
import com.marvik.apis.dbcrudgen.java.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.java.schemamodels.datatypes.DataType;
import com.marvik.apis.dbcrudgen.java.schemamodels.tables.Table;

public class CrudCreator {

	public static void main(String[] args) {

		/*
		 * TablesTemplate tablesTemplate = new TablesTemplate(); try {
		 * System.out.println(tablesTemplate.getTemplate()); } catch
		 * (IOException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 * 
		 * ColumnsCrudTemplate columnsCrudTemplate = new ColumnsCrudTemplate();
		 * try { System.out.println(columnsCrudTemplate.getTemplate()); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * SQLParser sqlParser = new SQLParser(); try { Database database =
		 * sqlParser.getDatabaseSchemas(new SQLReader(),TestRes.TEST_SQL_FILE);
		 * System.out.println(database.getDatabaseName()); for(Table table :
		 * database.getTables()){ System.out.println(table.getTableName());
		 * System.out.println(table.getTableSql()); }
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		List<Table> tablesList = new ArrayList<Table>();

		List<Columns> firstAidColumnList = new ArrayList<Columns>();
		firstAidColumnList.add(new Columns("id_firstaid", new DataType("int", new Constraints("integer primary key auto_increment"))));
		firstAidColumnList.add(new Columns("ailment", new DataType("varchar", new Constraints("varchar(128) NOT NULL"))));
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
		tablesList.add(new Table("firstaids", firstAidColumns, tableSql));

		Table[] tables = new Table[tablesList.size()];
		for(int i = 0; i<tablesList.size(); i++){
			tables[i] = tablesList.get(i);
		}
		Database database = new Database("where_there_is_no_doc", tables);
		String databaseSqlStatement = database.createSQL();
		
		print(databaseSqlStatement);
	}
	private static void print (String string){
		System.err.println(string);
	}
}
