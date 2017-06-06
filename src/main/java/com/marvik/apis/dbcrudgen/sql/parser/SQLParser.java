package com.marvik.apis.dbcrudgen.sql.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.sql.reader.SQLReader;

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
		return new Database(getDatabaseName(sql), tables);

	}

	private String getDatabaseName(String sql) {
		String databaseName = null;

		String sqlDatabaseNameComment = "-- Database: `";

		if (sql.contains(sqlDatabaseNameComment)) {
			int startOfDatabaseName = sql.indexOf(sqlDatabaseNameComment) + sqlDatabaseNameComment.length();
			int endOfDatabaseName = sql.indexOf("`", startOfDatabaseName);
			databaseName = sql.substring(startOfDatabaseName, endOfDatabaseName);
		}
		String sqlDatabaseCreateSQL = "CREATE DATABASE IF NOT EXISTS `";
		if (sql.contains(sqlDatabaseCreateSQL)) {
			int startOfDatabaseName = sql.indexOf(sqlDatabaseCreateSQL) + sqlDatabaseCreateSQL.length();
			int endOfDatabaseName = sql.indexOf("`", startOfDatabaseName);
			databaseName = sql.substring(startOfDatabaseName, endOfDatabaseName);
		}

		return databaseName;
	}

	private Table[] getDatabaseTables(String sql) {
		String[] sqlStatements = sql.split(";");
		List<Table> tables = new ArrayList<Table>();

		for (String sqlStatement : sqlStatements) {

			sqlStatement.toUpperCase();

			// create table statements
			if (sqlStatement.contains(new String("CREATE TABLE IF NOT EXISTS `"))) {

				Table _table = new Table(getTableName(sqlStatement), getTableColumns(sqlStatement),
						getTableSQL(sqlStatement), null);
				tables.add(_table);
			}

			// table insert statements
			if (sqlStatement.contains(new String("INSERT INTO `"))) {

			}
		}

		Table[] table = new Table[tables.size()];

		for (int i = 0; i < tables.size(); i++) {
			table[i] = tables.get(i);
		}
		return table;
	}

	private String getTableSQL(String sqlStatement) {
		return sqlStatement;
	}

	@SuppressWarnings("Returns Null")
	private TableColumn[] getTableColumns(String sqlStatement) {

		return null;
	}

	private String getTableName(String sqlStatement) {

		int startOfTableName = sqlStatement.indexOf("`");
		int endOfTableName = sqlStatement.indexOf("`", startOfTableName + 1);
		return sqlStatement.substring(startOfTableName, endOfTableName);
	}
}
