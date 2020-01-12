package com.marvik.apis.dbcrudgen;

import java.io.IOException;

import com.marvik.apis.dbcrudgen.application.tasks.TasksExecutor;
import com.marvik.apis.dbcrudgen.core.databases.mysql.MYSQLDatabaseConnection;
import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.creator.android.AndroidCRUDCreator;
import com.marvik.apis.dbcrudgen.creator.j2se.J2SECrudCreator;
import com.marvik.apis.dbcrudgen.creator.php.PHPCrudCreator;
import com.marvik.apis.dbcrudgen.database.connection.DatabaseConnectionProperties;
import com.marvik.apis.dbcrudgen.database.connection.project.ProjectDatabaseConnectionProperties;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidContentProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.AndroidDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.provider.ProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.transactions.TransactionManagerConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectConfiguration;
import com.marvik.apis.dbcrudgen.projects.j2se.configuration.J2SEProjectMYSQLDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.projects.php.configuration.PHPProjectConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;

public class Main {
    public static void main(String[] args) {
        MYSQLDatabaseConnection connection = new MYSQLDatabaseConnection("localhost", "root", "root3358");
        TasksExecutor tasksExecutor = new TasksExecutor();
        tasksExecutor.setMYSQLDatabaseConnection(connection);
        String[] skipTables = {"access_logs", "accountants", "admins", "agent_managers", "auditors", "audit_logs", "country",
                "country", "customers_firebase_notifications", "customer_support", "developers", "failed_jobs", "firebase_notifications",
                "http_requests", "inventory_clerks", "jobs", "migrations", "mpesa_access_tokens", "mpesa_requests", "mpesa_stk_callbacks", "mpesa_stk_process",
                "mpesa_stk_query", "oauth_access_tokens", "oauth_auth_codes", "oauth_clients", "oauth_personal_access_clients", "oauth_refresh_tokens",
                "organizations", "organization_addresses", "organization_banks", "organization_contacts", "organization_details", "organization_mpesa_accounts",
                "organization_offices", "organization_social_medias", "password_resets", "payment_reminders", "payment_reminder_candidates", "sales_managers",
                "sms_http_requests", "special_reminders", "special_reminder_candidates", "stk_push_process", "stk_push_query", "stock_register", "tasks"};
        Database database = tasksExecutor.createDatabaseModel("givewatts", skipTables);
        testAndroidCrudGenerator(database, "/opt/victor/workspace/android", "givewatts", "com.eqtr.android.agent", "app", "src.agent.java");
        testAndroidCrudGenerator(database, "/opt/victor/workspace/android", "givewatts", "com.eqtr.android.customer", "app", "src.customer.java");
    }

    private static void createAndroidChatModule() {
        Database database = new TasksExecutor().createDatabaseModel("wifihacker", new String[]{});
        testAndroidCrudGenerator(database, "F:\\Android\\NerdyGeekApps", "HackersWifi", "marvik.libs.wifihacker", "marvik-libs-wifihacker", "src.main.java");
    }

    private static void testAndroidCrudGenerator(Database database, String projectStorageDir, String projectName,
                                                 String packageName,
                                                 String moduleName, String javaSrcDir) {

        AndroidDatabaseConfiguration androidDatabaseConfiguration = new AndroidDatabaseConfiguration(
                database.getDatabaseName(), 1, "DatabaseManager", "database.sqliteopenhelper",
                "database.tableschemas", "database.tablescrud", "database.tablemodels",
                "database.httpresponses");

        String contentProviderPackage = "database.contentprovider";
        String contentProviderClass = "DataProvider";
        ProviderConfiguration providerConfiguration = new ProviderConfiguration(contentProviderClass,
                contentProviderPackage);

        String transactionManagerPackage = "database.transactions";
        String transactionManagerClass = "TransactionsManager";
        TransactionManagerConfiguration transactionManagerConfiguration = new TransactionManagerConfiguration(
                transactionManagerPackage, transactionManagerClass);

        AndroidContentProviderConfiguration androidContentProviderConfiguration = new AndroidContentProviderConfiguration(
                providerConfiguration, transactionManagerConfiguration, androidDatabaseConfiguration);

        AndroidProjectConfiguration androidProjectConfiguration = new AndroidProjectConfiguration(
                projectStorageDir + NativeUtils.getFileSeparator() + projectName,
                moduleName + NativeUtils.getFileSeparator() + javaSrcDir, packageName,
                androidContentProviderConfiguration);

        AndroidCRUDCreator androidCRUDCreator = new AndroidCRUDCreator();
        androidCRUDCreator.setAndroidProjectConfiguration(androidProjectConfiguration);
        androidCRUDCreator.createProject(database);
    }

    private static void testPHPCrudGenerator(Database database, String storagePath) {

        PHPProjectConfiguration phpProjectConfiguration = new PHPProjectConfiguration(database.getDatabaseName());
        phpProjectConfiguration.setProjectStorageDirectory(storagePath);
        phpProjectConfiguration.setProjectPHPTableCrudLowLevelScriptsStorageDirectory(
                storagePath + "\\database\\crud\\");
        phpProjectConfiguration.setProjectPHPTableCrudHighLevelScriptsStorageDirectory(
                storagePath + "\\database\\modules\\");
        phpProjectConfiguration.setProjectPHPDatabaseAPIScriptsStorageDirectory(
                storagePath + "\\database\\core-apis\\");
        phpProjectConfiguration.setProjectSQLScriptsStorageDirectory(
                storagePath + "\\database\\sql\\");

        ProjectDatabaseConnectionProperties projectDatabaseConnectionProperties = new ProjectDatabaseConnectionProperties(
                "localhost", "root", "", database.getDatabaseName());

        PHPCrudCreator phpCrudCreator = new PHPCrudCreator();
        phpCrudCreator.setProjectConfiguration(phpProjectConfiguration);
        phpCrudCreator.setProjectDatabaseConnectionProperties(projectDatabaseConnectionProperties);
        phpCrudCreator.createProject(database);

    }

    private static void testJ2SECrudGenerator(Database database, String storageDir, String projectName, String packageName, String javaSrcDir, String libDirs) {

        try {
            J2SEProjectConfiguration j2SEProjectConfiguration = new J2SEProjectConfiguration(projectName, packageName, storageDir, javaSrcDir, libDirs);
            DatabaseConnectionProperties databaseConnectionProperties =
                    new DatabaseConnectionProperties("localhost", "root", "", database.getDatabaseName());
            J2SEProjectMYSQLDatabaseConfiguration mysqlDatabaseConfiguration =
                    new J2SEProjectMYSQLDatabaseConfiguration("mysql", databaseConnectionProperties, "schemas", "models", "crud");
            j2SEProjectConfiguration.setJ2SEProjectMYSQLDatabaseConfiguration(mysqlDatabaseConfiguration);
            J2SECrudCreator j2SECrudCreator = new J2SECrudCreator();
            j2SECrudCreator.createProject(j2SEProjectConfiguration, database);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
