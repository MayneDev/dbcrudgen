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
import java.util.Arrays;
import java.util.List;

import com.marvik.apis.dbcrudgen.application.views.windows.MainWindow;
import com.marvik.apis.dbcrudgen.core.databases.mysql.MYSQLDatabaseConnection;
import com.marvik.apis.dbcrudgen.core.databases.mysql.MYSQLDefaultConnectionProperties;
import com.marvik.apis.dbcrudgen.core.databases.mysql.MYSQLQueryExecutor;
import com.marvik.apis.dbcrudgen.core.databases.sqlite.SQLiteUtils;
import com.marvik.apis.dbcrudgen.core.os.LinuxOperatingSystem;
import com.marvik.apis.dbcrudgen.core.os.MacOperatingSystem;
import com.marvik.apis.dbcrudgen.core.os.WindowsOperatingSystem;
import com.marvik.apis.dbcrudgen.core.platforms.mysql.grammar.MYSQLGrammar;
import com.marvik.apis.dbcrudgen.core.platforms.mysql.queries.MYSQLQueries;
import com.marvik.apis.dbcrudgen.core.platforms.sqlite.grammar.SQLiteGrammar;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.schemamodels.columns.TableColumn;
import com.marvik.apis.dbcrudgen.schemamodels.columns.keys.PrimaryKey;
import com.marvik.apis.dbcrudgen.schemamodels.constraints.Constraints;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;
import com.marvik.apis.dbcrudgen.schemamodels.datatypes.DataType;
import com.marvik.apis.dbcrudgen.schemamodels.tables.Table;
import com.marvik.apis.dbcrudgen.utilities.Utils;

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
        //startHttpServer();
        //getMYSQLQueryExecutor();
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
     * Set MYSQL Database Connection
     *
     * @return MYSQLDatabaseConnection
     */
    public void setMYSQLDatabaseConnection(MYSQLDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;

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
                Table[] databaseTables = createDatabaseTables(databaseName, new String[]{});
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

    private String parseTableName(String tableName) {
        return tableName;
    }

    /**
     *
     */
    public void startHttpServer() {

        LinuxOperatingSystem linuxOs = new LinuxOperatingSystem();
        MacOperatingSystem macOs = new MacOperatingSystem();
        WindowsOperatingSystem winOs = new WindowsOperatingSystem();

        String os = Utils.getOperatingSystem();

        String apachePath = null;
        String mysqlPath = null;

        if (os.equals(linuxOs.getName())) {
            apachePath = linuxOs.getApacheStartCommand();
            mysqlPath = linuxOs.getMysqlStartCommand();
        }
        if (os.equals(macOs.getName())) {
            apachePath = macOs.getApacheStartCommand();
            mysqlPath = macOs.getMysqlStartCommand();
        }
        if (os.equals(winOs.getName())) {
            apachePath = winOs.getApacheStartCommand();
            mysqlPath = winOs.getMysqlStartCommand();
        }

        try {
            Process startApache = Runtime.getRuntime().exec(apachePath);
            Process startMysql = Runtime.getRuntime().exec(mysqlPath);

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
     * @param skipTables
     * @return Database
     */
    public Database createDatabaseModel(String databaseName, String[] skipTables) {
        Table[] tables = createDatabaseTables(databaseName, skipTables);

        return new Database(databaseName, tables);
    }

    /**
     * Creates all the tables in a database
     *
     * @param databaseName
     * @param skipTables
     * @return array of tables in a database
     */
    private Table[] createDatabaseTables(String databaseName, String[] skipTables) {
        List<String> databaseTables = getDatabaseTablesNames(databaseName);

        System.out.println("Initial tables : " + databaseTables.size());
        databaseTables.removeAll(Arrays.asList(skipTables));
        System.out.println("New tables tables : " + databaseTables.size());

        Table[] tables = new Table[databaseTables.size()];

        for (int i = 0; i < databaseTables.size(); i++) {
            tables[i] = getDatabaseTable(databaseTables.get(i), databaseName);
        }

        return tables;
    }

    /**
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

                StringBuilder field = new StringBuilder(tableColumns.getString(MYSQLQueries.ResultsKeys.ShowTableColumns.KEY_FIELD));

                for (String keyword : SQLiteGrammar.Keywords.all()) {
                    if (field.toString().equalsIgnoreCase(keyword)) {
                        field = new StringBuilder(field.append("_"));
                    }
                }

                String type = tableColumns.getString(MYSQLQueries.ResultsKeys.ShowTableColumns.KEY_TYPE);
                type = SQLiteUtils.parseAndroidDataType(NativeUtils.toMYSQLDataType(type));

                String _null = tableColumns.getString(MYSQLQueries.ResultsKeys.ShowTableColumns.KEY_NULL);
                String key = tableColumns.getString(MYSQLQueries.ResultsKeys.ShowTableColumns.KEY_TABLE_KEY);
                String _default = tableColumns.getString(MYSQLQueries.ResultsKeys.ShowTableColumns.KEY_DEFAULT);
                String extra = tableColumns.getString(MYSQLQueries.ResultsKeys.ShowTableColumns.KEY_EXTRA);

                boolean nullable = _null.equalsIgnoreCase("YES");

                Constraints constraints = new Constraints(_null.equalsIgnoreCase("no") ? extra + " NOT NULL " : " NULL DEFAULT " + _default);
                DataType dataType = new DataType(type, constraints);

                if (key.equalsIgnoreCase(MYSQLGrammar.Keys.PRIMARY_KEY)) {
                    isPrimaryKey = true;
                    primaryKey = new PrimaryKey(field.toString(), dataType, nullable, true, _default, extra);
                    constraints = new Constraints(_null.equalsIgnoreCase("no") ? " PRIMARY KEY " + extra + " NOT NULL  " : " NULL DEFAULT " + _default);
                    dataType.setConstraints(constraints);
                }


                TableColumn tableColumn = new TableColumn(field.toString(), dataType, nullable, isPrimaryKey, _default, extra);
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
