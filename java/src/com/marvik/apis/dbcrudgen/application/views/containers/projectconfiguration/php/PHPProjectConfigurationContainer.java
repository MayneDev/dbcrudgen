/**
 * 
 */
package com.marvik.apis.dbcrudgen.application.views.containers.projectconfiguration.php;

import com.marvik.apis.dbcrudgen.application.views.containers.projectconfiguration.ProjectConfigurationContainer;
import com.marvik.apis.dbcrudgen.application.views.layouts.HorizontalLayout;
import com.marvik.apis.dbcrudgen.application.views.layouts.VerticalLayout;

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

	private VerticalLayout phpProjectConfigWidget;

	private VerticalLayout lowLevelCrudScriptsView;

	private VerticalLayout highLevelCrudScriptsView;

	private VerticalLayout sqlScriptsView;

	private VerticalLayout phpCoreDatabaseScriptsView;

	private VerticalLayout mysqlDatabaseConfigWidget;

	/**
	 * @param parent
	 */
	public PHPProjectConfigurationContainer() {

		// PHPProjectConfiguration
		phpProjectConfigWidget = new VerticalLayout();

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
}