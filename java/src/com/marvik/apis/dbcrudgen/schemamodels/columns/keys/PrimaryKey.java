package com.marvik.apis.dbcrudgen.schemamodels.columns.keys;

import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;

public class PrimaryKey extends KeyColumn {

    /**
     * Defines a primary key column
     */
    public PrimaryKey(String primaryKey, DataType dataType) {
        super(primaryKey, dataType, true);
    }

    /*
     * Return the set primary keys
     */
    public String getPrimaryKey() {
        return getColumnName();
    }
}
