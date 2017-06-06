package com.marvik.apis.dbcrudgen.schemamodels.columns.keys;

import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;

public class KeyColumn extends TableColumn {

    /**
     * Sets table key column
     */
    public KeyColumn(String keyColumnName, DataType dataType, boolean isPrimaryKey) {
        super(keyColumnName, dataType, isPrimaryKey);

    }

    /**
     * Return the name of the key column
     */
    public String getKeyColumn() {
        return getColumnName();
    }

    /**
     * KeyColumn#getDataType
     *
     * @return DataType
     */
    public DataType getDataType() {
        return super.getDataType();
    }
}
