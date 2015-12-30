package com.marvik.apis.dbcrudgen.schemamodels.tables;

import com.marvik.apis.dbcrudgen.schemamodels.columns.Columns;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.ForeignKeys;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.PrimaryKeys;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.UniqueKeys;

public class Table {

	private String tableName;
	private Columns[] columns;
	private String tableSql;
	private PrimaryKeys primaryKeys;
	private ForeignKeys foreignKeys;
	private UniqueKeys uniqueKeys;

	/**
	 * @param tableName
	 * @param columns
	 * @param tableSql
	 * @param primaryKeys
	 * @param foreignKeys
	 * @param uniqueKeys
	 */
	public Table(String tableName, Columns[] columns, String tableSql, PrimaryKeys primaryKeys, ForeignKeys foreignKeys,
			UniqueKeys uniqueKeys) {
		this.tableName = tableName;
		this.columns = columns;
		this.tableSql = tableSql;
		this.primaryKeys = primaryKeys;
		this.foreignKeys = foreignKeys;
		this.uniqueKeys = uniqueKeys;
	}

	/**
	 * @param tableName
	 * @param columns
	 * @param tableSql
	 */
	public Table(String tableName, Columns[] columns, String tableSql) {
		new Table(tableName, columns, tableSql, null, null, null);
	}

	/**
	 * @param tableName
	 * @param columns
	 * @param tableSql
	 * @param primaryKeys
	 */
	public Table(String tableName, Columns[] columns, String tableSql, PrimaryKeys primaryKeys) {
		new Table(tableName, columns, tableSql, primaryKeys, null, null);
	}

	/**
	 * @param tableName
	 * @param columns
	 * @param tableSql
	 * @param primaryKeys
	 * @param foreignKeys
	 */
	public Table(String tableName, Columns[] columns, String tableSql, PrimaryKeys primaryKeys,
			ForeignKeys foreignKeys) {
		new Table(tableName, columns, tableSql, primaryKeys, foreignKeys, null);
	}

	/**
	 * @param tableName
	 * @param columns
	 * @param tableSql
	 * @param primaryKeys
	 * @param uniqueKeys
	 */
	public Table(String tableName, Columns[] columns, String tableSql, PrimaryKeys primaryKeys, UniqueKeys uniqueKeys) {
		new Table(tableName, columns, tableSql, primaryKeys, null, uniqueKeys);
	}

	/**
	 * @param tableName
	 * @param columns
	 * @param tableSql
	 * @param foreignKeys
	 * @param uniqueKeys
	 */
	public Table(String tableName, Columns[] columns, String tableSql, ForeignKeys foreignKeys, UniqueKeys uniqueKeys) {
		new Table(tableName, columns, tableSql, null, foreignKeys, uniqueKeys);
	}

	/**
	 * @param tableName
	 * @param columns
	 * @param tableSql
	 * @param foreignKeys
	 */
	public Table(String tableName, Columns[] columns, String tableSql, ForeignKeys foreignKeys) {
		new Table(tableName, columns, tableSql, null, foreignKeys, null);
	}

	/**
	 * @param tableName
	 * @param columns
	 * @param tableSql
	 * @param uniqueKeys
	 */
	public Table(String tableName, Columns[] columns, String tableSql, UniqueKeys uniqueKeys) {
		new Table(tableName, columns, tableSql, null, null, uniqueKeys);
	}

	/**
	 * @return the foreignKeys
	 */
	public ForeignKeys getForeignKeys() {
		return foreignKeys;
	}

	/**
	 * @param foreignKeys
	 *            the foreignKeys to set
	 */
	public void setForeignKeys(ForeignKeys foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	/**
	 * @return the uniqueKeys
	 */
	public UniqueKeys getUniqueKeys() {
		return uniqueKeys;
	}

	/**
	 * @param uniqueKeys
	 *            the uniqueKeys to set
	 */
	public void setUniqueKeys(UniqueKeys uniqueKeys) {
		this.uniqueKeys = uniqueKeys;
	}

	/**
	 * @param primaryKeys
	 *            the primaryKeys to set
	 */
	public void setPrimaryKeys(PrimaryKeys primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @return the columns
	 */
	public Columns[] getColumns() {
		return columns;
	}

	/**
	 * @return the tableSql
	 */
	public String getTableSql() {
		return tableSql;
	}

	/**
	 * @param tableSql
	 *            the tableSql to set
	 */
	public void setTableSql(String tableSql) {
		this.tableSql = tableSql;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(Columns[] columns) {
		this.columns = columns;
	}

	/**
	 * @return the primaryKeyColumn
	 */
	public PrimaryKeys getPrimaryKeys() {
		return primaryKeys;
	}

	/**
	 * @param primaryKeyColumn
	 *            the primaryKeyColumn to set
	 */
	public void setPrimaryKeyColumn(PrimaryKeys primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

}
