/**
 * 
 */
package com.marvik.apis.dbcrudgen.application.views.containers.projectconfiguration.php;

import java.util.List;

import com.marvik.apis.dbcrudgen.application.tasks.TasksExecutor;
import com.marvik.apis.dbcrudgen.application.views.containers.projectconfiguration.ProjectConfigurationContainer;
import com.marvik.apis.dbcrudgen.application.views.layouts.HorizontalLayout;
import com.marvik.apis.dbcrudgen.application.views.layouts.VerticalLayout;
import com.marvik.apis.dbcrudgen.application.views.windows.MainWindow;
import com.marvik.apis.dbcrudgen.creator.php.PHPCrudCreator;
import com.marvik.apis.dbcrudgen.database.connection.project.ProjectDatabaseConnectionProperties;
import com.marvik.apis.dbcrudgen.projects.php.configuration.PHPProjectConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
*Created on Feb 5, 2016-3:17:02 AM by victor
*/

/**
 * @author victor
 *
 */
public class PHPProjectConfigurationContainer extends ProjectConfigurationContainer {

	private Button createPHPSourceCode;

	private VerticalLayout phpProjectConfigWidget;

	private VerticalLayout lowLevelCrudScriptsView;

	private VerticalLayout highLevelCrudScriptsView;

	private VerticalLayout sqlScriptsView;

	private VerticalLayout phpCoreDatabaseScriptsView;

	private VerticalLayout mysqlDatabaseConfigWidget;

	private List<Database> mysqlDatabases;

	/**
	 * @param mysqlDatabases
	 * @param parent
	 */
	public PHPProjectConfigurationContainer(List<Database> mysqlDatabases) {
		this.mysqlDatabases = mysqlDatabases;

		// PHPProjectConfiguration
		phpProjectConfigWidget = new VerticalLayout(true);
		phpProjectConfigWidget.setPadding(new Insets(10));

		phpProjectConfigWidget.getChildren().add(new Label("PHP Project Configuration"));

		phpProjectConfigWidget.getChildren().add(new Label("Low level CRUD directory"));
		lowLevelCrudScriptsView = getLowLevelCrudScriptsStorageDirectoryView();
		phpProjectConfigWidget.getChildren().add(lowLevelCrudScriptsView);

		phpProjectConfigWidget.getChildren().add(new Label("High level CRUD directory"));
		highLevelCrudScriptsView = getHighLevelCRUDScriptsStorageDirectoryView();
		phpProjectConfigWidget.getChildren().add(highLevelCrudScriptsView);

		phpProjectConfigWidget.getChildren().add(new Label("SQL Scripts directory"));
		sqlScriptsView = getSQLScriptsStorageDirectoryView();
		phpProjectConfigWidget.getChildren().add(sqlScriptsView);

		phpProjectConfigWidget.getChildren().add(new Label("Core PHP Database directory"));
		phpCoreDatabaseScriptsView = getPHPDatabaseAPIScriptsStorageDirectoryView();
		phpProjectConfigWidget.getChildren().add(phpCoreDatabaseScriptsView);

		phpProjectConfigWidget.getChildren().add(new Label("MYSQL Database Config"));
		mysqlDatabaseConfigWidget = getMYSQLDatabaseConfigView();
		phpProjectConfigWidget.getChildren().add(mysqlDatabaseConfigWidget);

		HorizontalLayout buttons = new HorizontalLayout();
		Button setFields = new Button("AutoConfigure");
		setFields.setOnAction(e -> setFieldsData());

		createPHPSourceCode = new Button("Create Source Code");
		createPHPSourceCode.setOnAction(e -> createPHPSourceCode());

		buttons.getChildren().addAll(setFields, createPHPSourceCode);
		phpProjectConfigWidget.getChildren().add(buttons);

		this.getChildren().add(phpProjectConfigWidget);
	}

	/**
	 * Return a vertical layout with the storage directory used to store LOW
	 * LEVEL CRUD SCRIPTS
	 * 
	 * @return VerticalLayout
	 */
	private Button btChooseLowLevelCrudScriptDir;
	private Label lLowLevelCrudScriptDir;
	private TextField tvLowLevelCrudScriptDir;

	private VerticalLayout getLowLevelCrudScriptsStorageDirectoryView() {

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		tvLowLevelCrudScriptDir = new TextField();
		btChooseLowLevelCrudScriptDir = new Button("Choose");
		horizontalLayout.getChildren().addAll(tvLowLevelCrudScriptDir, btChooseLowLevelCrudScriptDir);

		lLowLevelCrudScriptDir = new Label("Low level CRUD");

		VerticalLayout layout = new VerticalLayout(true);
		layout.getChildren().addAll(lLowLevelCrudScriptDir, horizontalLayout);

		return layout;
	}

	private Button btChooseHighLevelCrudScriptDir;
	private Label lHighLevelCrudScriptDir;
	private TextField tvHighLevelCrudScriptDir;

	/**
	 * Return a vertical layout with the storage directory used to store HIGH
	 * LEVEL CRUD SCRIPTS
	 * 
	 * @return
	 */
	private VerticalLayout getHighLevelCRUDScriptsStorageDirectoryView() {

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		tvHighLevelCrudScriptDir = new TextField();
		btChooseHighLevelCrudScriptDir = new Button("Choose");
		horizontalLayout.getChildren().addAll(tvHighLevelCrudScriptDir, btChooseHighLevelCrudScriptDir);

		lHighLevelCrudScriptDir = new Label("High level CRUD");

		VerticalLayout layout = new VerticalLayout(true);
		layout.getChildren().addAll(lHighLevelCrudScriptDir, horizontalLayout);

		return layout;

	}

	private Button btChooseSQLScriptDir;
	private Label lSQLScriptDir;
	private TextField tvSQLScriptDir;

	/**
	 * Return a vertical layout with the storage directory used to store SQL
	 * SCRIPTS
	 * 
	 * @return
	 */
	private VerticalLayout getSQLScriptsStorageDirectoryView() {

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		tvSQLScriptDir = new TextField();
		btChooseSQLScriptDir = new Button("Choose");
		horizontalLayout.getChildren().addAll(tvSQLScriptDir, btChooseSQLScriptDir);

		lSQLScriptDir = new Label("SQL Scripts");

		VerticalLayout layout = new VerticalLayout(true);
		layout.getChildren().addAll(lSQLScriptDir, horizontalLayout);

		return layout;

	}

	private Button btChooseCorePHPDatabaseScriptDir;
	private Label lCorePHPDatabaseScriptDir;
	private TextField tvCorePHPDatabaseScriptDir;

	/**
	 * Return a vertical layout with the storage directory used to store PHP
	 * Database API CRUD SCRIPTS
	 * 
	 * @return
	 */
	private VerticalLayout getPHPDatabaseAPIScriptsStorageDirectoryView() {

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		tvCorePHPDatabaseScriptDir = new TextField();
		btChooseCorePHPDatabaseScriptDir = new Button("Choose");
		horizontalLayout.getChildren().addAll(tvCorePHPDatabaseScriptDir, btChooseCorePHPDatabaseScriptDir);

		lCorePHPDatabaseScriptDir = new Label("Core PHP Database Scripts");

		VerticalLayout layout = new VerticalLayout(true);
		layout.getChildren().addAll(lCorePHPDatabaseScriptDir, horizontalLayout);

		return layout;
	}

	private Label lDatabaseHost;
	private TextField tvDatabaseHost;

	private Label lDatabaseUser;
	private TextField tvDatabaseUser;

	private Label lUserPassword;
	private TextField tvUserPassword;

	private Label lDatabaseName;
	private TextField tvDatabaseName;

	/**
	 * Return a vertical layout with the storage directory used to store PHP
	 * Database API CRUD SCRIPTS
	 * 
	 * @return
	 */
	private VerticalLayout getMYSQLDatabaseConfigView() {
		VerticalLayout layout = new VerticalLayout(true);

		lDatabaseHost = new Label("Database host");
		tvDatabaseHost = new TextField();
		layout.getChildren().addAll(lDatabaseHost, tvDatabaseHost);

		lDatabaseUser = new Label("Database user");
		tvDatabaseUser = new TextField();
		layout.getChildren().addAll(lDatabaseUser, tvDatabaseUser);

		lUserPassword = new Label("User password");
		tvUserPassword = new TextField();
		layout.getChildren().addAll(lUserPassword, tvUserPassword);

		lDatabaseName = new Label("Database name");
		tvDatabaseName = new TextField();

		layout.getChildren().addAll(lDatabaseName, tvDatabaseName);

		return layout;
	}

	/**
	 * Created the PHP Source code for the database
	 */
	private void createPHPSourceCode() {

		String databaseName = MainWindow.databaseName;

		TasksExecutor tasksExecutor = new TasksExecutor();

		Database database = null;

		for (Database _database : mysqlDatabases) {
			if (_database.getDatabaseName().equals(databaseName)) {
				database = _database;
			}
		}

		if (tvLowLevelCrudScriptDir.getText().length() < 1) {
			System.out.println("low level crud Cannot be empty");
			return;
		}
		if (tvHighLevelCrudScriptDir.getText().length() < 1) {
			System.out.println("high level Cannot be empty");
			return;
		}
		if (tvSQLScriptDir.getText().length() < 1) {
			System.out.println("sql script Cannot be empty");
			return;
		}
		if (tvCorePHPDatabaseScriptDir.getText().length() < 1) {
			System.out.println("core php Cannot be empty");
			return;
		}

		if (tvDatabaseHost.getText().length() < 1) {
			System.out.println("database host Cannot be empty");
			return;
		}

		if (tvDatabaseUser.getText().length() < 1) {
			System.out.println("database user Cannot be empty");
			return;
		}

		if (tvDatabaseName.getText().length() < 1) {
			System.out.println("Cannot be empty");
			return;
		}

		String projectName = databaseName;
		PHPProjectConfiguration phpProjectConfiguration = new PHPProjectConfiguration(projectName);

		String projectStorageDirectory = "C:\\xampp\\htdocs\\" + databaseName + "\\";
		phpProjectConfiguration.setProjectStorageDirectory(projectStorageDirectory);

		String lowLevelCrudScriptsStorageDirectory = tvLowLevelCrudScriptDir.getText();
		phpProjectConfiguration
				.setProjectPHPTableCrudLowLevelScriptsStorageDirectory(lowLevelCrudScriptsStorageDirectory);

		String highLevelCrudScriptsStorageDirectory = tvHighLevelCrudScriptDir.getText();
		phpProjectConfiguration
				.setProjectPHPTableCrudHighLevelScriptsStorageDirectory(highLevelCrudScriptsStorageDirectory);

		String phpDatabaseAPIScriptsStorageDirectory = tvCorePHPDatabaseScriptDir.getText();
		phpProjectConfiguration.setProjectPHPDatabaseAPIScriptsStorageDirectory(phpDatabaseAPIScriptsStorageDirectory);

		String sqlScriptsStorageDirectory = tvSQLScriptDir.getText();
		phpProjectConfiguration.setProjectSQLScriptsStorageDirectory(sqlScriptsStorageDirectory);

		String databaseUserPassword = tvUserPassword.getText();
		String databaseUser = tvDatabaseUser.getText();
		String databaseHost = tvDatabaseHost.getText();
		ProjectDatabaseConnectionProperties projectDatabaseConnectionProperties = new ProjectDatabaseConnectionProperties(
				databaseHost, databaseUser, databaseUserPassword, databaseName);

		PHPCrudCreator phpCrudCreator = new PHPCrudCreator();
		phpCrudCreator.setProjectConfiguration(phpProjectConfiguration);
		phpCrudCreator.setProjectDatabaseConnectionProperties(projectDatabaseConnectionProperties);
		phpCrudCreator.createProject(database);
	}

	private void setFieldsData() {

		String projectName = MainWindow.databaseName;

		tvLowLevelCrudScriptDir.setText("C:\\xampp\\htdocs\\" + projectName + "\\database\\crud\\low\\");
		tvHighLevelCrudScriptDir.setText("C:\\xampp\\htdocs\\" + projectName + "\\database\\crud\\high\\");
		tvCorePHPDatabaseScriptDir.setText("C:\\xampp\\htdocs\\" + projectName + "\\database\\crud\\core\\");
		tvSQLScriptDir.setText("C:\\xampp\\htdocs\\" + projectName + "\\database\\crud\\sql\\");

		tvUserPassword.setText("");
		tvDatabaseUser.setText("root");
		tvDatabaseHost.setText("localhost");

		tvDatabaseName.setText(projectName);
	}
}