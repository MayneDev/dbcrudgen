package com.marvik.apis.dbcrudgen.utilities;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.MYSQLDataTypes;

public class Utils {

    public static MYSQLDataTypes parseMysqlDatatype(String datatype) {

        datatype = NativeUtils.toLettersOnly(datatype);

        if (datatype.equalsIgnoreCase("INT"))
            return MYSQLDataTypes.INT;

        if (datatype.equalsIgnoreCase("INTEGER"))
            return MYSQLDataTypes.INTEGER;

        if (datatype.equalsIgnoreCase("VARCHAR"))
            return MYSQLDataTypes.VARCHAR;

        if (datatype.equalsIgnoreCase("TEXT"))
            return MYSQLDataTypes.TEXT;

        if (datatype.equalsIgnoreCase("TINYINT"))
            return MYSQLDataTypes.TINYINT;

        if (datatype.equalsIgnoreCase("SMALLINT"))
            return MYSQLDataTypes.SMALLINT;

        if (datatype.equalsIgnoreCase("MEDIUMINT"))
            return MYSQLDataTypes.MEDIUMINT;

        if (datatype.equalsIgnoreCase("BIGINT"))
            return MYSQLDataTypes.BIGINT;

        if (datatype.equalsIgnoreCase("DECIMAL"))
            return MYSQLDataTypes.DECIMAL;

        if (datatype.equalsIgnoreCase("FLOAT"))
            return MYSQLDataTypes.FLOAT;

        if (datatype.equalsIgnoreCase("DOUBLE"))
            return MYSQLDataTypes.DOUBLE;

        if (datatype.equalsIgnoreCase("REAL"))
            return MYSQLDataTypes.REAL;
        if (datatype.equalsIgnoreCase("BIT"))
            return MYSQLDataTypes.BIT;

        if (datatype.equalsIgnoreCase("BOOLEAN"))
            return MYSQLDataTypes.BOOLEAN;

        if (datatype.equalsIgnoreCase("SERIAL"))
            return MYSQLDataTypes.SERIAL;

        if (datatype.equalsIgnoreCase("DATE"))
            return MYSQLDataTypes.DATE;

        if (datatype.equalsIgnoreCase("DATETIME"))
            return MYSQLDataTypes.DATETIME;

        if (datatype.equalsIgnoreCase("TIMESTAMP"))
            return MYSQLDataTypes.TIMESTAMP;

        if (datatype.equalsIgnoreCase("TIME"))
            return MYSQLDataTypes.TIME;

        if (datatype.equalsIgnoreCase("YEAR"))
            return MYSQLDataTypes.YEAR;

        if (datatype.equalsIgnoreCase("CHAR"))
            return MYSQLDataTypes.CHAR;

        if (datatype.equalsIgnoreCase("VARCHAR"))
            return MYSQLDataTypes.VARCHAR;

        if (datatype.equalsIgnoreCase("TINYTEXT"))
            return MYSQLDataTypes.TINYTEXT;

        if (datatype.equalsIgnoreCase("TEXT"))
            return MYSQLDataTypes.TEXT;

        if (datatype.equalsIgnoreCase("MEDIUMTEXT"))
            return MYSQLDataTypes.MEDIUMTEXT;

        if (datatype.equalsIgnoreCase("LONGTEXT"))
            return MYSQLDataTypes.LONGTEXT;

        if (datatype.equalsIgnoreCase("BINARY"))
            return MYSQLDataTypes.BINARY;

        if (datatype.equalsIgnoreCase("VARBINARY"))
            return MYSQLDataTypes.VARBINARY;

        if (datatype.equalsIgnoreCase("TINYBLOB"))
            return MYSQLDataTypes.TINYBLOB;

        if (datatype.equalsIgnoreCase("MEDIUMBLOB"))
            return MYSQLDataTypes.MEDIUMBLOB;

        if (datatype.equalsIgnoreCase("BLOB"))
            return MYSQLDataTypes.BLOB;

        if (datatype.equalsIgnoreCase("LONGBLOB"))
            return MYSQLDataTypes.LONGBLOB;

        if (datatype.equalsIgnoreCase("ENUM"))
            return MYSQLDataTypes.ENUM;

        if (datatype.equalsIgnoreCase("SET"))
            return MYSQLDataTypes.SET;

        if (datatype.equalsIgnoreCase("GEOMETRY"))
            return MYSQLDataTypes.GEOMETRY;

        if (datatype.equalsIgnoreCase("POINT"))
            return MYSQLDataTypes.POINT;

        if (datatype.equalsIgnoreCase("LINESTRING"))
            return MYSQLDataTypes.LINESTRING;

        if (datatype.equalsIgnoreCase("POLYGON"))
            return MYSQLDataTypes.POLYGON;

        if (datatype.equalsIgnoreCase("MULTIPOINT"))
            return MYSQLDataTypes.MULTIPOINT;

        if (datatype.equalsIgnoreCase("MULTILINESTRING"))
            return MYSQLDataTypes.MULTILINESTRING;

        if (datatype.equalsIgnoreCase("MULTIPOLYGON"))
            return MYSQLDataTypes.MULTIPOLYGON;

        if (datatype.equalsIgnoreCase("GEOMETRYCOLLECTION"))
            return MYSQLDataTypes.GEOMETRYCOLLECTION;

        System.err.println("Could not find data type for " + datatype);

        return null;
    }

    public static String generateJavaQueryMethodReturnType(MYSQLDataTypes mysqlDataType) {

        switch (mysqlDataType) {
            // Boolean
            case TINYINT:

            case BOOLEAN:
                return "Boolean";

            // Byte
            case BIT:
            case BINARY:
            case VARBINARY:
            case TINYBLOB:
                return "Byte";

            // Integer
            case CHAR:
            case INT:
            case INTEGER:
            case SET:
            case SMALLINT:
                return "Integer";

            // Date
            case DATE:
            case DATETIME:
            case TIMESTAMP:
            case TIME:
            case YEAR:
                return "String";

            // Double
            case DECIMAL:
            case DOUBLE:
                return "Double";

            // Float
            case FLOAT:
                return "Float";

            // Long
            case MEDIUMTEXT:
            case MEDIUMINT:
            case BIGINT:
            case SERIAL:

                return "Long";

            // String
            case ENUM:
            case VARCHAR:
            case TEXT:
            case TINYTEXT:
            case LONGTEXT:
            case MEDIUMBLOB:
            case BLOB:
            case LONGBLOB:

                return "String";
            case GEOMETRY:
            case POINT:
            case LINESTRING:
            case POLYGON:
            case MULTIPOINT:
            case MULTILINESTRING:
            case MULTIPOLYGON:
            case GEOMETRYCOLLECTION:
                return "Class";

            default:
                return "Class";

        }

    }

    /**
     * Get the operating system of the PC
     *
     * @return
     */
    public static String getOperatingSystem() {
        return System.getProperty("os.name");
    }
}
