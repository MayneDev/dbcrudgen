package com.marvik.apis.dbcrudgen.core.databases.mysql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MYSQLUtils {
    /**
     * Splits the passed mysql data type to get the data type and strip other params
     *
     * @param rayMysqlDataType mysql data type
     * @return
     */
    public static String parseDataType(String rayMysqlDataType) {


        if (rayMysqlDataType.matches("(\\w+)")) { //e.g int
            Pattern pattern = Pattern.compile("(\\w+)");
            Matcher matcher = pattern.matcher(rayMysqlDataType);

            if (matcher.find()) {
                return matcher.group();
            }
        }
        if (rayMysqlDataType.matches("(\\w+)?\\(\\d+\\)")) { //e.g varchar(255)
            Pattern pattern = Pattern.compile("(\\w+)?\\(\\d+\\)");
            Matcher matcher = pattern.matcher(rayMysqlDataType);

            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        if (rayMysqlDataType.matches("(\\w+)?\\(\\d+\\)?(\\s)(\\w+)?")) { //e.g int(10) unsigned
            Pattern pattern = Pattern.compile("(\\w+)?\\(\\d+\\)?(\\s)(\\w+)?");
            Matcher matcher = pattern.matcher(rayMysqlDataType);

            if (matcher.find()) {
                return matcher.group(1);
            }

        }
        if (rayMysqlDataType.matches("(\\w+)?\\(\\d+,\\d+\\)?")) { //e.g double(8,2)
            Pattern pattern = Pattern.compile("(\\w+)?\\(\\d+,\\d+\\)?");
            Matcher matcher = pattern.matcher(rayMysqlDataType);
            if (matcher.find()) {
                return matcher.group(1);
            }
        } else {
            Pattern pattern = Pattern.compile("(\\w+)?(.*)"); //e.g enum('DAYS','WEEKS','MONTHS')
            Matcher matcher = pattern.matcher(rayMysqlDataType);
            if (matcher.find()) {
                return matcher.group(1);
            }
           
        }
        return null;
    }
}