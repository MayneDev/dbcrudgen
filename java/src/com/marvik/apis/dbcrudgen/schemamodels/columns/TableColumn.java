package com.marvik.apis.dbcrudgen.schemamodels.columns;

import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;

public class TableColumn {

    private String columnName;
    private DataType dataType;
    private String[] columnNames;
    private boolean isPrimaryKey;

    public TableColumn(String columnName, DataType dataType, boolean isPrimaryKey) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.isPrimaryKey = isPrimaryKey;
    }

    /*
     * This constructor should be improved in future
     */
    public TableColumn(String[] columnNames) {
        this.columnNames = columnNames;

    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @return the dataType
     */
    public DataType getDataType() {
        return dataType;
    }

    /**
     * @return the columnNames
     */
    public String[] getColumnNames() {
        return columnNames;
    }

    /**
     * @param columnNames the columnNames to set
     */
    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    /**
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    /**
     * Set is primary key
     *
     * @param primaryKey
     */
    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    /**
     * @return is primary key
     */
    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }
}
