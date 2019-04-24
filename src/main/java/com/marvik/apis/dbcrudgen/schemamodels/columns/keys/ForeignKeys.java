package com.marvik.apis.dbcrudgen.schemamodels.columns.keys;

import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;

public class ForeignKeys extends KeyColumn {

    /**
     * Sets table foreign key column
     */
    public ForeignKeys(String foreignKey, DataType dataType, boolean nullable, boolean isPrimaryKey, String defaultValue, String extra) {
        super(foreignKey, dataType, nullable, isPrimaryKey, defaultValue, extra);
    }

    /**
     * Return the set foreign key
     */
    public String getForeignKey() {
        return getColumnName();
    }
}
