/**
 *
 */
package com.marvik.apis.dbcrudgen.core.platforms.sqlite.grammar;

/**
 *Created on Jan 30, 2016-7:06:21 PM by victor
 */

import java.util.ArrayList;
import java.util.List;

/**
 * SQLiteGrammar
 *
 * Class that hold various data about the SQLite database programming language
 *
 * @author victor
 *
 */
public class SQLiteGrammar {

    /**
     * SQLiteGrammar Keywords
     *
     * These are the keywords/library reserved keywords for the SQLite Database
     * Programming Language
     *
     * @author victor
     *
     */
    public static class Keywords {


        public static final String ABORT = "ABORT";
        public static final String ACTION = "ACTION";
        public static final String ADD = "ADD";
        public static final String AFTER = "AFTER";
        public static final String ALL = "ALL";
        public static final String ALTER = "ALTER";
        public static final String ALWAYS = "ALWAYS";
        public static final String ANALYZE = "ANALYZE";
        public static final String AND = "AND";
        public static final String AS = "AS";
        public static final String ASC = "ASC";
        public static final String ATTACH = "ATTACH";
        public static final String AUTOINCREMENT = "AUTOINCREMENT";
        public static final String BEFORE = "BEFORE";
        public static final String BEGIN = "BEGIN";
        public static final String BETWEEN = "BETWEEN";
        public static final String BY = "BY";
        public static final String CASCADE = "CASCADE";
        public static final String CASE = "CASE";
        public static final String CAST = "CAST";
        public static final String CHECK = "CHECK";
        public static final String COLLATE = "COLLATE";
        public static final String COLUMN = "COLUMN";
        public static final String COMMIT = "COMMIT";
        public static final String CONFLICT = "CONFLICT";
        public static final String CONSTRAINT = "CONSTRAINT";
        public static final String CREATE = "CREATE";
        public static final String CROSS = "CROSS";
        public static final String CURRENT = "CURRENT";
        public static final String CURRENT_DATE = "CURRENT_DATE";
        public static final String CURRENT_TIME = "CURRENT_TIME";
        public static final String CURRENT_TIMESTAMP = "CURRENT_TIMESTAMP";
        public static final String DATABASE = "DATABASE";
        public static final String DEFAULT = "DEFAULT";
        public static final String DEFERRABLE = "DEFERRABLE";
        public static final String DEFERRED = "DEFERRED";
        public static final String DELETE = "DELETE";
        public static final String DESC = "DESC";
        public static final String DETACH = "DETACH";
        public static final String DISTINCT = "DISTINCT";
        public static final String DO = "DO";
        public static final String DROP = "DROP";
        public static final String EACH = "EACH";
        public static final String ELSE = "ELSE";
        public static final String END = "END";
        public static final String ESCAPE = "ESCAPE";
        public static final String EXCEPT = "EXCEPT";
        public static final String EXCLUDE = "EXCLUDE";
        public static final String EXCLUSIVE = "EXCLUSIVE";
        public static final String EXISTS = "EXISTS";
        public static final String EXPLAIN = "EXPLAIN";
        public static final String FAIL = "FAIL";
        public static final String FILTER = "FILTER";
        public static final String FIRST = "FIRST";
        public static final String FOLLOWING = "FOLLOWING";
        public static final String FOR = "FOR";
        public static final String FOREIGN = "FOREIGN";
        public static final String FROM = "FROM";
        public static final String FULL = "FULL";
        public static final String GENERATED = "GENERATED";
        public static final String GLOB = "GLOB";
        public static final String GROUP = "GROUP";
        public static final String GROUPS = "GROUPS";
        public static final String HAVING = "HAVING";
        public static final String IF = "IF";
        public static final String IGNORE = "IGNORE";
        public static final String IMMEDIATE = "IMMEDIATE";
        public static final String IN = "IN";
        public static final String INDEX = "INDEX";
        public static final String INDEXED = "INDEXED";
        public static final String INITIALLY = "INITIALLY";
        public static final String INNER = "INNER";
        public static final String INSERT = "INSERT";
        public static final String INSTEAD = "INSTEAD";
        public static final String INTERSECT = "INTERSECT";
        public static final String INTO = "INTO";
        public static final String IS = "IS";
        public static final String ISNULL = "ISNULL";
        public static final String JOIN = "JOIN";
        public static final String KEY = "KEY";
        public static final String LAST = "LAST";
        public static final String LEFT = "LEFT";
        public static final String LIKE = "LIKE";
        public static final String LIMIT = "LIMIT";
        public static final String MATCH = "MATCH";
        public static final String NATURAL = "NATURAL";
        public static final String NO = "NO";
        public static final String NOT = "NOT";
        public static final String NOTHING = "NOTHING";
        public static final String NOTNULL = "NOTNULL";
        public static final String NULL = "NULL";
        public static final String NULLS = "NULLS";
        public static final String OF = "OF";
        public static final String OFFSET = "OFFSET";
        public static final String ON = "ON";
        public static final String OR = "OR";
        public static final String ORDER = "ORDER";
        public static final String OTHERS = "OTHERS";
        public static final String OUTER = "OUTER";
        public static final String OVER = "OVER";
        public static final String PARTITION = "PARTITION";
        public static final String PLAN = "PLAN";
        public static final String PRAGMA = "PRAGMA";
        public static final String PRECEDING = "PRECEDING";
        public static final String PRIMARY = "PRIMARY";
        public static final String QUERY = "QUERY";
        public static final String RAISE = "RAISE";
        public static final String RANGE = "RANGE";
        public static final String RECURSIVE = "RECURSIVE";
        public static final String REFERENCES = "REFERENCES";
        public static final String REGEXP = "REGEXP";
        public static final String REINDEX = "REINDEX";
        public static final String RELEASE = "RELEASE";
        public static final String RENAME = "RENAME";
        public static final String REPLACE = "REPLACE";
        public static final String RESTRICT = "RESTRICT";
        public static final String RIGHT = "RIGHT";
        public static final String ROLLBACK = "ROLLBACK";
        public static final String ROW = "ROW";
        public static final String ROWS = "ROWS";
        public static final String SAVEPOINT = "SAVEPOINT";
        public static final String SELECT = "SELECT";
        public static final String SET = "SET";
        public static final String TABLE = "TABLE";
        public static final String TEMP = "TEMP";
        public static final String TEMPORARY = "TEMPORARY";
        public static final String THEN = "THEN";
        public static final String TIES = "TIES";
        public static final String TO = "TO";
        public static final String TRANSACTION = "TRANSACTION";
        public static final String TRIGGER = "TRIGGER";
        public static final String UNBOUNDED = "UNBOUNDED";
        public static final String UNION = "UNION";
        public static final String UNIQUE = "UNIQUE";
        public static final String UPDATE = "UPDATE";
        public static final String USING = "USING";
        public static final String VACUUM = "VACUUM";
        public static final String VALUES = "VALUES";
        public static final String VIEW = "VIEW";
        public static final String VIRTUAL = "VIRTUAL";
        public static final String WHEN = "WHEN";
        public static final String WHERE = "WHERE";
        public static final String WINDOW = "WINDOW";
        public static final String WITH = "WITH";
        public static final String WITHOUT = "WITHOUT";

        public static List<String> all() {
            List<String> keywords = new ArrayList<>();
            keywords.add("ABORT"); //ABORT
            keywords.add("ACTION"); //ACTION
            keywords.add("ADD"); //ADD
            keywords.add("AFTER"); //AFTER
            keywords.add("ALL"); //ALL
            keywords.add("ALTER"); //ALTER
            keywords.add("ALWAYS"); //ALWAYS
            keywords.add("ANALYZE"); //ANALYZE
            keywords.add("AND"); //AND
            keywords.add("AS"); //AS
            keywords.add("ASC"); //ASC
            keywords.add("ATTACH"); //ATTACH
            keywords.add("AUTOINCREMENT"); //AUTOINCREMENT
            keywords.add("BEFORE"); //BEFORE
            keywords.add("BEGIN"); //BEGIN
            keywords.add("BETWEEN"); //BETWEEN
            keywords.add("BY"); //BY
            keywords.add("CASCADE"); //CASCADE
            keywords.add("CASE"); //CASE
            keywords.add("CAST"); //CAST
            keywords.add("CHECK"); //CHECK
            keywords.add("COLLATE"); //COLLATE
            keywords.add("COLUMN"); //COLUMN
            keywords.add("COMMIT"); //COMMIT
            keywords.add("CONFLICT"); //CONFLICT
            keywords.add("CONSTRAINT"); //CONSTRAINT
            keywords.add("CREATE"); //CREATE
            keywords.add("CROSS"); //CROSS
            keywords.add("CURRENT"); //CURRENT
            keywords.add("CURRENT_DATE"); //CURRENT_DATE
            keywords.add("CURRENT_TIME"); //CURRENT_TIME
            keywords.add("CURRENT_TIMESTAMP"); //CURRENT_TIMESTAMP
            keywords.add("DATABASE"); //DATABASE
            keywords.add("DEFAULT"); //DEFAULT
            keywords.add("DEFERRABLE"); //DEFERRABLE
            keywords.add("DEFERRED"); //DEFERRED
            keywords.add("DELETE"); //DELETE
            keywords.add("DESC"); //DESC
            keywords.add("DETACH"); //DETACH
            keywords.add("DISTINCT"); //DISTINCT
            keywords.add("DO"); //DO
            keywords.add("DROP"); //DROP
            keywords.add("EACH"); //EACH
            keywords.add("ELSE"); //ELSE
            keywords.add("END"); //END
            keywords.add("ESCAPE"); //ESCAPE
            keywords.add("EXCEPT"); //EXCEPT
            keywords.add("EXCLUDE"); //EXCLUDE
            keywords.add("EXCLUSIVE"); //EXCLUSIVE
            keywords.add("EXISTS"); //EXISTS
            keywords.add("EXPLAIN"); //EXPLAIN
            keywords.add("FAIL"); //FAIL
            keywords.add("FILTER"); //FILTER
            keywords.add("FIRST"); //FIRST
            keywords.add("FOLLOWING"); //FOLLOWING
            keywords.add("FOR"); //FOR
            keywords.add("FOREIGN"); //FOREIGN
            keywords.add("FROM"); //FROM
            keywords.add("FULL"); //FULL
            keywords.add("GENERATED"); //GENERATED
            keywords.add("GLOB"); //GLOB
            keywords.add("GROUP"); //GROUP
            keywords.add("GROUPS"); //GROUPS
            keywords.add("HAVING"); //HAVING
            keywords.add("IF"); //IF
            keywords.add("IGNORE"); //IGNORE
            keywords.add("IMMEDIATE"); //IMMEDIATE
            keywords.add("IN"); //IN
            keywords.add("INDEX"); //INDEX
            keywords.add("INDEXED"); //INDEXED
            keywords.add("INITIALLY"); //INITIALLY
            keywords.add("INNER"); //INNER
            keywords.add("INSERT"); //INSERT
            keywords.add("INSTEAD"); //INSTEAD
            keywords.add("INTERSECT"); //INTERSECT
            keywords.add("INTO"); //INTO
            keywords.add("IS"); //IS
            keywords.add("ISNULL"); //ISNULL
            keywords.add("JOIN"); //JOIN
            keywords.add("KEY"); //KEY
            keywords.add("LAST"); //LAST
            keywords.add("LEFT"); //LEFT
            keywords.add("LIKE"); //LIKE
            keywords.add("LIMIT"); //LIMIT
            keywords.add("MATCH"); //MATCH
            keywords.add("NATURAL"); //NATURAL
            keywords.add("NO"); //NO
            keywords.add("NOT"); //NOT
            keywords.add("NOTHING"); //NOTHING
            keywords.add("NOTNULL"); //NOTNULL
            keywords.add("NULL"); //NULL
            keywords.add("NULLS"); //NULLS
            keywords.add("OF"); //OF
            keywords.add("OFFSET"); //OFFSET
            keywords.add("ON"); //ON
            keywords.add("OR"); //OR
            keywords.add("ORDER"); //ORDER
            keywords.add("OTHERS"); //OTHERS
            keywords.add("OUTER"); //OUTER
            keywords.add("OVER"); //OVER
            keywords.add("PARTITION"); //PARTITION
            keywords.add("PLAN"); //PLAN
            keywords.add("PRAGMA"); //PRAGMA
            keywords.add("PRECEDING"); //PRECEDING
            keywords.add("PRIMARY"); //PRIMARY
            keywords.add("QUERY"); //QUERY
            keywords.add("RAISE"); //RAISE
            keywords.add("RANGE"); //RANGE
            keywords.add("RECURSIVE"); //RECURSIVE
            keywords.add("REFERENCES"); //REFERENCES
            keywords.add("REGEXP"); //REGEXP
            keywords.add("REINDEX"); //REINDEX
            keywords.add("RELEASE"); //RELEASE
            keywords.add("RENAME"); //RENAME
            keywords.add("REPLACE"); //REPLACE
            keywords.add("RESTRICT"); //RESTRICT
            keywords.add("RIGHT"); //RIGHT
            keywords.add("ROLLBACK"); //ROLLBACK
            keywords.add("ROW"); //ROW
            keywords.add("ROWS"); //ROWS
            keywords.add("SAVEPOINT"); //SAVEPOINT
            keywords.add("SELECT"); //SELECT
            keywords.add("SET"); //SET
            keywords.add("TABLE"); //TABLE
            keywords.add("TEMP"); //TEMP
            keywords.add("TEMPORARY"); //TEMPORARY
            keywords.add("THEN"); //THEN
            keywords.add("TIES"); //TIES
            keywords.add("TO"); //TO
            keywords.add("TRANSACTION"); //TRANSACTION
            keywords.add("TRIGGER"); //TRIGGER
            keywords.add("UNBOUNDED"); //UNBOUNDED
            keywords.add("UNION"); //UNION
            keywords.add("UNIQUE"); //UNIQUE
            keywords.add("UPDATE"); //UPDATE
            keywords.add("USING"); //USING
            keywords.add("VACUUM"); //VACUUM
            keywords.add("VALUES"); //VALUES
            keywords.add("VIEW"); //VIEW
            keywords.add("VIRTUAL"); //VIRTUAL
            keywords.add("WHEN"); //WHEN
            keywords.add("WHERE"); //WHERE
            keywords.add("WINDOW"); //WINDOW
            keywords.add("WITH"); //WITH
            keywords.add("WITHOUT"); //WITHOUT
            return keywords;
        }
    }

}
