package com.marvik.apis.dbcrudgen.schemamodels.tables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.marvik.apis.dbcrudgen.parser.android.AndroidTemplatesParser;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.ForeignKeys;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.PrimaryKey;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.UniqueKeys;
import com.marvik.apis.dbcrudgen.schemamodels.constraints.Constraints;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;

/**
 * Table - Contains all the basic data of a typical MYSQL database table
 *
 * @author victor
 */
public class Table {

    private String tableName;
    private TableColumn[] tableColumn;
    private String tableSql;
    private PrimaryKey primaryKey;
    private ForeignKeys[] foreignKeys;
    private UniqueKeys[] uniqueKeys;

    /**
     * @param tableName
     * @param columns
     * @param tableSql
     * @param primaryKey
     */
    public Table(String tableName, TableColumn[] columns, String tableSql, PrimaryKey primaryKey) {
        this.tableName = tableName;
        this.tableColumn = columns;
        this.tableSql = tableSql;
        this.primaryKey = primaryKey;
    }

    /**
     * @param tableName
     * @param tableColumn
     * @param tableSql
     */
    /*
	 * public Table(String tableName, TableColumn[] columns, String tableSql) {
	 * new Table(tableName, columns, tableSql, null, null, null); }
	 * 
	 *//**
     * @param tableName
     * @param tableColumn
     * @param tableSql
     * @param primaryKey
     */
	/*
	 * public Table(String tableName, TableColumn[] columns, String tableSql,
	 * PrimaryKey primaryKey) { new Table(tableName, columns, tableSql,
	 * primaryKey, null, null); }
	 * 
	 *//**
     * @param tableName
     * @param tableColumn
     * @param tableSql
     * @param primaryKey
     * @param foreignKeys
     */
	/*
	 * public Table(String tableName, TableColumn[] columns, String tableSql,
	 * PrimaryKey primaryKey, ForeignKeys[] foreignKeys) { new Table(tableName,
	 * columns, tableSql, primaryKey, foreignKeys, null); }
	 * 
	 *//**
     * @param tableName
     * @param tableColumn
     * @param tableSql
     * @param primaryKey
     * @param uniqueKeys
     */
	/*
	 * public Table(String tableName, TableColumn[] columns, String tableSql,
	 * PrimaryKey primaryKey, UniqueKeys[] uniqueKeys) { new Table(tableName,
	 * columns, tableSql, primaryKey, null, uniqueKeys); }
	 * 
	 *//**
     * @param tableName
     * @param tableColumn
     * @param tableSql
     * @param foreignKeys
     * @param uniqueKeys
     */
	/*
	 * public Table(String tableName, TableColumn[] columns, String tableSql,
	 * ForeignKeys[] foreignKeys, UniqueKeys[] uniqueKeys) { new
	 * Table(tableName, columns, tableSql, null, foreignKeys, uniqueKeys); }
	 * 
	 *//**
     * @param tableName
     * @param tableColumn
     * @param tableSql
     * @param foreignKeys
     */
	/*
	 * public Table(String tableName, TableColumn[] columns, String tableSql,
	 * ForeignKeys[] foreignKeys) { new Table(tableName, columns, tableSql,
	 * null, foreignKeys, null); }
	 * 
	 *//**
     * @param tableName
     * @param tableColumn
     * @param tableSql
     * @param uniqueKeys
     *//*
		 * public Table(String tableName, TableColumn[] columns, String
		 * tableSql, UniqueKeys[] uniqueKeys) { new Table(tableName, columns,
		 * tableSql, null, null, uniqueKeys); }
		 */

    /**
     * @return the foreignKeys
     */
    public ForeignKeys[] getForeignKeys() {
        return foreignKeys;
    }

    /**
     * @param foreignKeys the foreignKeys to set
     */
    public void setForeignKeys(ForeignKeys[] foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    /**
     * @return the uniqueKeys
     */
    public UniqueKeys[] getUniqueKeys() {
        return uniqueKeys;
    }

    /**
     * @param uniqueKeys the uniqueKeys to set
     */
    public void setUniqueKeys(UniqueKeys[] uniqueKeys) {
        this.uniqueKeys = uniqueKeys;
    }

    /**
     * @param primaryKey the primaryKey to set
     */
    public void setPrimaryKeys(PrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @return the tableColumn
     */
    public TableColumn[] getColumns() {
        return tableColumn;
    }

    /**
     * @return the tableSql
     */
    public String getTableSql() {
        return tableSql;
    }

    /**
     * @param tableSql the tableSql to set
     */
    public void setTableSql(String tableSql) {
        this.tableSql = tableSql;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @param columns the tableColumn to set
     */
    public void setColumns(TableColumn[] columns) {
        this.tableColumn = columns;
    }

    /**
     * @return the primaryKeyColumn
     */
    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    /**
     * @param primaryKey the primaryKeyColumn to set
     */
    public void setPrimaryKeyColumn(PrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Returns an array of table columns
     *
     * @return TableColumn[]
     */
    public TableColumn[] getTableColumnsAll() {

        TableColumn[] tableColumns = getColumns();

        //TODO REMOVE THIS BLOCK IF NO LONGER RELEVANT
        // Add primary key column as the last
        if (getPrimaryKey() != null) {
            PrimaryKey primaryKey = getPrimaryKey();
            String primaryKeyColumn = primaryKey.getColumnName();
            //allTableColumns.add(new TableColumn(primaryKeyColumn, primaryKey.getDataType(),false));
        }

        List<TableColumn> allTableColumns = new ArrayList<>(Arrays.asList(tableColumns));

        tableColumns = new TableColumn[allTableColumns.size()];

        for (int i = 0; i < allTableColumns.size(); i++) {
            tableColumns[i] = allTableColumns.get(i);
        }
        return tableColumns;
    }
}
