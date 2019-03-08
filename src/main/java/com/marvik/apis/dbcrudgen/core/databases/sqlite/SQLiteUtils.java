package com.marvik.apis.dbcrudgen.core.databases.sqlite;

public class SQLiteUtils {
    /**
     * converts MYSQL data type to SQLite Data Type
     *
     * @param mysqlDataType
     * @return
     */
    public static String parseAndroidDataType(String mysqlDataType) {

        // Boolean
        if (mysqlDataType.equalsIgnoreCase("Boolean")) {
            return "boolean";
        }
        // Byte
        if (mysqlDataType.equalsIgnoreCase("Byte")) {
            return "byte";
        }// Char
        if (mysqlDataType.equalsIgnoreCase("Char")) {
            return "TEXT";
        }
        // Bit
        if (mysqlDataType.equalsIgnoreCase("Bit")) {
            return "INTEGER";
        } // Integer
        if (mysqlDataType.equalsIgnoreCase("Integer")) {
            return "INTEGER";
        }
        // Integer
        if (mysqlDataType.equalsIgnoreCase("Int")) {
            return "INTEGER";

        }
        // Integer
        if (mysqlDataType.equalsIgnoreCase("SmallInt")) {
            return "INTEGER";

        }
        // Integer
        if (mysqlDataType.equalsIgnoreCase("TinyInt")) {
            return "INTEGER";

        }// Integer
        if (mysqlDataType.equalsIgnoreCase("BigInt")) {
            return "INTEGER";

        }
        // Date
        if (mysqlDataType.equalsIgnoreCase("Date")) {
            return "TEXT";
        } // Date
        if (mysqlDataType.equalsIgnoreCase("DateTime")) {
            // TO DO ADD SOURCE CODE
            return "TEXT";
        }
        // Date
        if (mysqlDataType.equalsIgnoreCase("blob")) {
            // TO DO ADD SOURCE CODE
            return "TEXT";
        }
        // Double
        if (mysqlDataType.equalsIgnoreCase("Double")) {
            return "DOUBLE";
        }
        // Float
        if (mysqlDataType.equalsIgnoreCase("Float")) {
            return "FLOAT";
        } // Float
        if (mysqlDataType.equalsIgnoreCase("Decimal")) {
            return "FLOAT";
        }
        // Long
        if (mysqlDataType.equalsIgnoreCase("Long")) {
            return "LONG";
        }
        // MYSQL Long
        if (mysqlDataType.equalsIgnoreCase("mediumtext")) {
            return "TEXT";
        }
        // String
        if (mysqlDataType.equalsIgnoreCase("String")) {
            return "TEXT";
        }
        // Varchar
        if (mysqlDataType.equalsIgnoreCase("varchar")) {
            return "VARCHAR";
        }
        // Text
        if (mysqlDataType.equalsIgnoreCase("text")) {
            return "TEXT";
        }
        // Timestamp
        if (mysqlDataType.equalsIgnoreCase("timestamp")) {
            return "TIMESTAMP";
        }  // Enumerations
        if (mysqlDataType.equalsIgnoreCase("Enum")) {
            return "String";
        }
        return "TEXT";
    }
}
