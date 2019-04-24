package com.marvik.apis.dbcrudgen.schemamodels.columns;

import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;

public class TableColumn {

    private String columnName;
    private DataType dataType;
    private boolean nullable;
    private boolean isPrimaryKey;
    private String defaultValue;
    private String extra;

    public TableColumn(String columnName, DataType dataType, boolean nullable, boolean isPrimaryKey, String defaultValue, String extra) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.nullable = nullable;
        this.isPrimaryKey = isPrimaryKey;
        this.defaultValue = defaultValue;
        this.extra = extra;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
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
