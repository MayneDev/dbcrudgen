package com.marvik.apis.dbcrudgen;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.marvik.apis.dbcrudgen.application.tasks.TasksExecutor;
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

import javax.xml.crypto.Data;

public class Main {
    public static void main(String[] args) {
        Database database = new TasksExecutor().createDatabaseModel("wifihacker");
        testJ2SECrudGenerator(database,"C:\\Users\\victor\\Desktop","WifiHacker","com.wifihacker","src","lib");
    }

    private static void createAndroidChatModule() {
        Database database = new TasksExecutor().createDatabaseModel("wifihacker");
        testAndroidCrudGenerator(database, "F:\\Android\\NerdyGeekApps", "HackersWifi", "marvik.libs.wifihacker", "marvik-libs-wifihacker");
    }

    private static void testAndroidCrudGenerator(Database database, String projectStorageDir, String projectName,
                                                 String packageName,
                                                 String moduleName) {

        AndroidDatabaseConfiguration androidDatabaseConfiguration = new AndroidDatabaseConfiguration(
                database.getDatabaseName(), 1, "DatabaseManager", "database\\sqliteopenhelper",
                "database\\tableschemas", "database\\tablescrud", "database\\tablemodels");

        String contentProviderPackage = "database\\contentprovider";
        String contentProviderClass = "DataProvider";
        ProviderConfiguration providerConfiguration = new ProviderConfiguration(contentProviderClass,
                contentProviderPackage);

        String transactionManagerPackage = "database\\transactions";
        String transactionManagerClass = "TransactionsManager";
        TransactionManagerConfiguration transactionManagerConfiguration = new TransactionManagerConfiguration(
                transactionManagerPackage, transactionManagerClass);

        AndroidContentProviderConfiguration androidContentProviderConfiguration = new AndroidContentProviderConfiguration(
                providerConfiguration, transactionManagerConfiguration, androidDatabaseConfiguration);

        AndroidProjectConfiguration androidProjectConfiguration = new AndroidProjectConfiguration(
                projectStorageDir + "\\" + projectName, moduleName + "\\src\\main\\java", packageName,
                androidContentProviderConfiguration);

        AndroidCRUDCreator androidCRUDCreator = new AndroidCRUDCreator();
        androidCRUDCreator.setAndroidProjectConfiguration(androidProjectConfiguration);
        androidCRUDCreator.createProject(database);
    }

    private static void testPHPCrudGenerator(Database database) {

        PHPProjectConfiguration phpProjectConfiguration = new PHPProjectConfiguration(database.getDatabaseName());
        phpProjectConfiguration.setProjectStorageDirectory("C:\\xampp\\htdocs\\" + database.getDatabaseName() + "\\");
        phpProjectConfiguration.setProjectPHPTableCrudLowLevelScriptsStorageDirectory(
                "C:\\xampp\\htdocs\\" + database.getDatabaseName() + "\\scripts\\php\\database\\crud\\");
        phpProjectConfiguration.setProjectPHPTableCrudHighLevelScriptsStorageDirectory(
                "C:\\xampp\\htdocs\\" + database.getDatabaseName() + "\\scripts\\php\\database\\modules\\");
        phpProjectConfiguration.setProjectPHPDatabaseAPIScriptsStorageDirectory(
                "C:\\xampp\\htdocs\\" + database.getDatabaseName() + "\\scripts\\php\\database\\core-apis\\");
        phpProjectConfiguration.setProjectSQLScriptsStorageDirectory(
                "C:\\xampp\\htdocs\\" + database.getDatabaseName() + "\\scripts\\php\\database\\sql\\");

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
                    new DatabaseConnectionProperties("localhost","root","",database.getDatabaseName());
            J2SEProjectMYSQLDatabaseConfiguration mysqlDatabaseConfiguration =
                    new J2SEProjectMYSQLDatabaseConfiguration("mysql",databaseConnectionProperties,"schemas","models","crud");
            j2SEProjectConfiguration.setJ2SEProjectMYSQLDatabaseConfiguration(mysqlDatabaseConfiguration);
            J2SECrudCreator j2SECrudCreator = new J2SECrudCreator();
            j2SECrudCreator.createProject(j2SEProjectConfiguration, database);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
