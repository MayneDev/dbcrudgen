/**
 *
 */
package com.marvik.apis.dbcrudgen.application.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.marvik.apis.dbcrudgen.application.views.windows.MainWindow;
import com.marvik.apis.dbcrudgen.core.databases.mysql.MYSQLDatabaseConnection;
import com.marvik.apis.dbcrudgen.core.databases.mysql.MYSQLDefaultConnectionProperties;
import com.marvik.apis.dbcrudgen.core.databases.mysql.MYSQLQueryExecutor;
import com.marvik.apis.dbcrudgen.core.platforms.mysql.grammar.MYSQLGrammar;
import com.marvik.apis.dbcrudgen.core.platforms.mysql.queries.MYSQLQueries;
import com.marvik.apis.dbcrudgen.core.toolchains.xampp.XAMPP;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.PrimaryKey;
import com.marvik.apis.dbcrudgen.schemamodels.constraints.Constraints;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;

/**
 * Created on Feb 5, 2016-10:40:21 AM by victor
 */

/**
 * @author victor
 */
public class TasksExecutor {

    /**
     * Tasks Executor
     * <p>
     * Executes all the actions of the main window
     */
    public TasksExecutor() {
        startXAMPPServer();
        getMYSQLQueryExecutor();
    }

    private MYSQLQueryExecutor queryExecutor;

    /**
     * Get MYSQL query executor - Get an handle for using to execute MYSQL
     * queries
     *
     * @return
     */
    public MYSQLQueryExecutor getMYSQLQueryExecutor() {

        if (queryExecutor == null) {
            queryExecutor = new MYSQLQueryExecutor(getMYSQLDatabaseConnection());
        }
        return queryExecutor;
    }

    private MYSQLDatabaseConnection databaseConnection;

    /**
     * Get MYSQL Database Connection
     *
     * @return MYSQLDatabaseConnection
     */
    public MYSQLDatabaseConnection getMYSQLDatabaseConnection() {

        if (databaseConnection == null) {
            databaseConnection = new MYSQLDatabaseConnection(MYSQLDefaultConnectionProperties.DATABASE_HOST,
                    MYSQLDefaultConnectionProperties.DATABASE_USER, MYSQLDefaultConnectionProperties.USER_PASSWORD);
        }

        return databaseConnection;
    }

    /**
     * Get a list of all the MYSQL databases
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws SocketException
     * @throws ConnectException
     */
    public List<Database> getDatabases() {

        List<Database> databases = new ArrayList<Database>();
        ResultSet mysqlDatabases = getMYSQLQueryExecutor().execSQL(MYSQLQueries.MYSQL_QUERY_SHOW_DATABASES);
        try {
            for (mysqlDatabases.first(); !mysqlDatabases.isAfterLast(); mysqlDatabases.next()) {
                String databaseName = mysqlDatabases.getString(MYSQLQueries.ResultsKeys.ShowDatabases.KEY_DATABASE);
                Table[] databaseTables = createDatabaseTables(databaseName);
                databases.add(new Database(databaseName, databaseTables));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return databases;
    }

    /**
     * @param database
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws SocketException
     * @throws ConnectException
     */
    public List<String> getDatabaseTablesNames(String database) {

        MainWindow.setSelectedDatabase(database);

        List<String> tables = new ArrayList<>();
        try {
            String useDatabaseMYSQLQuery = MYSQLQueries.MYSQL_QUERY_USE_DATABASE;
            useDatabaseMYSQLQuery = useDatabaseMYSQLQuery.replace(MYSQLQueries.QueryTags.DATABASE, database);

            getMYSQLQueryExecutor().execute(useDatabaseMYSQLQuery);

            String showTables = MYSQLQueries.MYSQL_QUERY_SHOW_TABLES;

            ResultSet databaseTables = getMYSQLQueryExecutor().execSQL(showTables);

            for (databaseTables.first(); !databaseTables.isAfterLast(); databaseTables.next()) {
                String showTablesResultsKey = MYSQLQueries.ResultsKeys.ShowDatabaseTables.TABLES_IN_DATABASE;
                showTablesResultsKey = showTablesResultsKey.replace(MYSQLQueries.QueryTags.DATABASE, database);
                String tableName = databaseTables.getString(showTablesResultsKey);
                tables.add(tableName);
            }

            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    /**
     *
     */
    public void startXAMPPServer() {
        try {
            Process startApache = Runtime.getRuntime().exec(XAMPP.START_APACHE_PATH);
            Process startMysql = Runtime.getRuntime().exec(XAMPP.START_MYSQL_PATH);

            if (startApache.isAlive()) {
                System.out.println("Apache : Is Alive");
            } else {
                printStream(startApache.getInputStream());
            }
            if (startMysql.isAlive()) {
                System.out.println("MYSQL : Is Alive");
            } else {
                printStream(startMysql.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param inputStream
     */
    private void printStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        StringBuilder builder = new StringBuilder();
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * Creates an object of the selected database
     *
     * @param databaseName
     * @return Database
     */
    public Database createDatabaseModel(String databaseName) {
        Table[] tables = createDatabaseTables(databaseName);

        return new Database(databaseName, tables);
    }

    /**
     * Creates all the tables in a database
     *
     * @param databaseName
     * @return array of tables in a database
     */
    private Table[] createDatabaseTables(String databaseName) {
        List<String> databaseTables = getDatabaseTablesNames(databaseName);
        Table[] tables = new Table[databaseTables.size()];
        for (int i = 0; i < databaseTables.size(); i++) {
            tables[i] = getDatabaseTable(databaseTables.get(i), databaseName);
        }
        return tables;
    }

    /**
     *
     * @param tableName
     * @param databaseName
     * @return
     */
    private Table getDatabaseTable(String tableName, String databaseName) {

        String showTableColumnsQuery = MYSQLQueries.MYSQL_QUERY_SHOW_TABLE_COLUMNS;
        showTableColumnsQuery = showTableColumnsQuery.replace(MYSQLQueries.QueryTags.TABLE, tableName);
        showTableColumnsQuery = showTableColumnsQuery.replace(MYSQLQueries.QueryTags.DATABASE, databaseName);

        ResultSet tableColumns = getMYSQLQueryExecutor().execSQL(showTableColumnsQuery);

        List<TableColumn> lTableColumns = new ArrayList<>();

        PrimaryKey primaryKey = null;

        try {
            for (tableColumns.first(); !tableColumns.isAfterLast(); tableColumns.next()) {

                boolean isPrimaryKey = false;

                String field = tableColumns.getString(MYSQLQueries.ResultsKeys.ShowTableColumns.KEY_FIELD);
                field = NativeUtils.toLetters(field);

                String type = tableColumns.getString(MYSQLQueries.ResultsKeys.ShowTableColumns.KEY_TYPE);
                type = NativeUtils.toLetters(type);

                String _null = tableColumns.getString(MYSQLQueries.ResultsKeys.ShowTableColumns.KEY_NULL);
                String key = tableColumns.getString(MYSQLQueries.ResultsKeys.ShowTableColumns.KEY_TABLE_KEY);
                String _default = tableColumns.getString(MYSQLQueries.ResultsKeys.ShowTableColumns.KEY_DEFAULT);
                String extra = tableColumns.getString(MYSQLQueries.ResultsKeys.ShowTableColumns.KEY_EXTRA);

                Constraints constraints = new Constraints(_null.equalsIgnoreCase("no") ? " NOT NULL  " : " NULL DEFAULT " + _default);
                DataType dataType = new DataType(type, constraints);

                if (key.equalsIgnoreCase(MYSQLGrammar.Keys.PRIMARY_KEY)) {
                    isPrimaryKey = true;
                    primaryKey = new PrimaryKey(field, dataType);
                }

                TableColumn tableColumn = new TableColumn(field, dataType, isPrimaryKey);
                lTableColumns.add(tableColumn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TableColumn[] columns = new TableColumn[lTableColumns.size()];

        for (int i = 0; i < lTableColumns.size(); i++) {
            columns[i] = lTableColumns.get(i);
        }

        return new Table(tableName, columns, getCreateTableSQL(tableName), primaryKey);
    }

    /**
     * @param tableName
     * @return
     */
    private String getCreateTableSQL(String tableName) {
        String createTableSQL = "";
        String query = MYSQLQueries.MYSQL_QUERY_SHOW_CREATE_TABLE;
        query = query.replace(MYSQLQueries.QueryTags.TABLE, tableName);
        ResultSet resultSet = getMYSQLQueryExecutor().execSQL(query);
        try {
            for (resultSet.first(); !resultSet.isAfterLast(); resultSet.next()) {
                createTableSQL = resultSet.getString(MYSQLQueries.ResultsKeys.ShowCreateTable.KEY_CREATE_TABLE);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return createTableSQL;
    }
}
