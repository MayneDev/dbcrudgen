package com.marvik.apis.dbcrudgen.core.platforms.sql.grammar;

import java.util.HashMap;
import java.util.Map;

/**
 * SQLGrammar
 * <p>
 * Structured Query Language Grammar - Contains the used Grammar of the SQL
 * programming language used in developing any part of this application/library
 *
 * @author victor
 */
public class SQLGrammar {

    /**
     * Keywords
     * <p>
     * Contains any of the SQL keywords used in this application
     *
     * @author victor
     */
    public static class Keywords {

        /**
         * AND
         */
        public static final String AND = "AND";

        /**
         * OR
         */
        public static final String OR = "OR";

        /**
         * AUTO_INCREMENT
         */
        public static final String AUTO_INCREMENT = "AUTO_INCREMENT";
    }

    public static String getSqliteEquivalent(String sqlKeyword) {
        return sqlKeyword;
    }

    public static Map<String, String> getSqliteEquivalents() {
        Map<String, String> keywordEquivalents = new HashMap<>();
        return keywordEquivalents;
    }
}
