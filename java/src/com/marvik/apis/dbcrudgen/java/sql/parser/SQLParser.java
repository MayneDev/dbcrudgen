package com.marvik.apis.dbcrudgen.java.sql.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.marvik.apis.dbcrudgen.java.schemamodels.columns.Columns;
import com.marvik.apis.dbcrudgen.java.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.java.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.java.sql.reader.SQLReader;

public class SQLParser {

	public SQLParser() {
		super();
	}

	/*
	 * Reads an SQL file and Returns a database schema
	 */
	public Database getDatabaseSchemas(SQLReader sqlReader, String sqlFilePath) throws IOException {
		return getDatabaseSchemas(sqlReader.getSQL(sqlFilePath));

	}

	/*
	 * Reads an SQL file and Returns a database schema
	 */
	public Database getDatabaseSchemas(SQLReader sqlReader, File sqlFile) throws IOException {
		return getDatabaseSchemas(sqlReader.getSQL(sqlFile));

	}

	/*
	 * Returns a database schema from a SQL
	 */
	public Database getDatabaseSchemas(String sql) {
		Table[] tables = getDatabaseTables(sql);
		return null;

	}

	private Table[] getDatabaseTables(String sql) {
		String[] sqlStatements = sql.split(";");
		List<Table>tables = new ArrayList<Table>();

		for (String sqlStatement : sqlStatements) {

			sqlStatement.toUpperCase();

			// create table statements
			if (sqlStatement.startsWith(new String("CREATE TABLE"))) {

				Table _table = new Table(
						getTableName(sqlStatement),
						getTableColumns(sqlStatement),
						getTableSQL(sqlStatement));
				tables.add(_table);
			}

			// table insert statements
			if (sqlStatement.startsWith(new String("INSERT INTO"))) {

			}
		}
		
		Table [] table = new Table[tables.size()];
		
		for(int i = 0; i < tables.size();i++){
			table[i] = tables.get(i); 
		}
		return table;
	}

	private String getTableSQL(String sqlStatement) {
		return sqlStatement;
	}

	private Columns[] getTableColumns(String sqlStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getTableName(String sqlStatement) {
		// TODO Auto-generated method stub
		return null;
	}

}
