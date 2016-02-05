/**
 * 
 */
package com.marvik.apis.dbcrudgen.application.views.containers.projectconfiguration.android;

import com.marvik.apis.dbcrudgen.application.views.containers.projectconfiguration.ProjectConfigurationContainer;
import com.marvik.apis.dbcrudgen.application.views.layouts.HorizontalLayout;
import com.marvik.apis.dbcrudgen.application.views.layouts.VerticalLayout;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
*Created on Feb 5, 2016-3:16:22 AM by victor
*/

/**
 * @author victor
 *
 */
public class AndroidProjectConfigurationContainer extends ProjectConfigurationContainer {

	/**
	 * @param parent
	 */
	public AndroidProjectConfigurationContainer() {
		super();
		VerticalLayout androidRootLayout = new VerticalLayout();
		androidRootLayout.getChildren().add(new Label("Android Project Configuration"));
		
		HorizontalLayout androidProjectConfigWidgets = new HorizontalLayout();

		// Add android project configuration
		VerticalLayout basicAndroidProjectConfig = createAndroidBasicProjectConfiguraion();
		androidProjectConfigWidgets.getChildren().add(basicAndroidProjectConfig);

		// Android SQLite Database Configuration
		VerticalLayout sqliteDatabaseConfig = new VerticalLayout();
		sqliteDatabaseConfig.getChildren().add(new Label("SQLite Database Configuration"));
		sqliteDatabaseConfig.getChildren().add(createSQLiteDatabaseConfiguration());
		
		androidProjectConfigWidgets.getChildren().add(sqliteDatabaseConfig);

		// Attach all the widgets to the parent container
		androidRootLayout.getChildren().add(androidProjectConfigWidgets);
		this.getChildren().add(androidRootLayout);

	}

	private Label lAndroidProjectStorageDirectory;
	private Label lAndroidProjectPackageName;
	private Label lAndroidProjectJavaSrcDirs;

	private TextField tfAndroidProjectStorageDirectory;
	private TextField tfAndroidProjectPackageName;
	private TextField tfAndroidProjectJavaSrcDirs;

	private Button btAndroidProjectStorageDirectory;
	private Button btAndroidProjectJavaSrcDirs;

	/**
	 * Contains all the widgets for basic project configuration
	 */
	private VerticalLayout createAndroidBasicProjectConfiguraion() {
		
		VerticalLayout vlBasicProjectConfiguration = new VerticalLayout();
		
		vlBasicProjectConfiguration.getChildren().add(new Label("Basic Project Configuration"));
		VerticalLayout lvAndroidProjectConfig = createAndroidProjectConfiguration();
		vlBasicProjectConfiguration.getChildren().add(lvAndroidProjectConfig);

		// Content provider configuration
		vlBasicProjectConfiguration.getChildren().add(new  Label("Content Provider Configuration"));
		VerticalLayout contentProviderConfig = createAndroidContentProviderConfiguration();
		vlBasicProjectConfiguration.getChildren().add(contentProviderConfig);

		// Transaction manager class configuration String
		vlBasicProjectConfiguration.getChildren().add(new  Label("Transaction Manager Configuration"));
		VerticalLayout transactionManagerConfig = createAndroidTransactionsManagerConfiguration();
		vlBasicProjectConfiguration.getChildren().add(transactionManagerConfig);

		return vlBasicProjectConfiguration;
	}

	/**
	 * Android project configuration
	 * 
	 */
	private VerticalLayout createAndroidProjectConfiguration() {
		VerticalLayout verticalLayout = new VerticalLayout(true);

		// Android Project Storage Directory Chooser View
		VerticalLayout androidProjectStorageView = createAndroidProjectStorageDirectoryChooserView();

		// Android Project Package Name Setter View
		VerticalLayout projectPackageNameView = createAndroidProjectPackageNameSetterView();

		// Android Project Java Src Directory Chooser View
		VerticalLayout projectJavaSrcDirView = createAndroidProjectJavaSrcDirectoryChooserView();

		verticalLayout.getChildren().addAll(androidProjectStorageView, projectPackageNameView, projectJavaSrcDirView);

		return verticalLayout;
	}

	/**
	 * Creates a view that is used for setting the android project storage
	 * directory
	 */
	private VerticalLayout createAndroidProjectStorageDirectoryChooserView() {
		VerticalLayout verticalLayout = new VerticalLayout();

		lAndroidProjectStorageDirectory = new Label("Project storage directory");
		tfAndroidProjectStorageDirectory = new TextField();
		btAndroidProjectStorageDirectory = new Button("Choose");

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.getChildren().add(tfAndroidProjectStorageDirectory);
		horizontalLayout.getChildren().add(btAndroidProjectStorageDirectory);

		verticalLayout = new VerticalLayout();
		verticalLayout.getChildren().add(lAndroidProjectStorageDirectory);
		verticalLayout.getChildren().add(horizontalLayout);

		return verticalLayout;
	}

	/**
	 * Creates a view that is used for setting the android project package name
	 */
	private VerticalLayout createAndroidProjectPackageNameSetterView() {
		VerticalLayout verticalLayout = new VerticalLayout();

		lAndroidProjectPackageName = new Label("Package name");
		tfAndroidProjectPackageName = new TextField();

		verticalLayout.getChildren().add(lAndroidProjectPackageName);
		verticalLayout.getChildren().add(tfAndroidProjectPackageName);

		return verticalLayout;
	}

	/**
	 * Creates a view that is used for setting the android project java src dir
	 */
	private VerticalLayout createAndroidProjectJavaSrcDirectoryChooserView() {

		VerticalLayout verticalLayout = new VerticalLayout();

		lAndroidProjectJavaSrcDirs = new Label("Java src directory");
		tfAndroidProjectJavaSrcDirs = new TextField();
		btAndroidProjectJavaSrcDirs = new Button("Choose");

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.getChildren().add(tfAndroidProjectJavaSrcDirs);
		horizontalLayout.getChildren().add(btAndroidProjectJavaSrcDirs);

		verticalLayout.getChildren().add(lAndroidProjectJavaSrcDirs);
		verticalLayout.getChildren().add(horizontalLayout);

		return verticalLayout;
	}

	/**
	 * Android Content Provider Configuration
	 */

	private Label lContentProviderClass;
	private Label lContentProviderPackage;

	private TextField tfContentProviderClass;
	private TextField tfContentProviderPackage;

	private VerticalLayout createAndroidContentProviderConfiguration() {

		VerticalLayout verticalLayout = new VerticalLayout(true);

		lContentProviderClass = new Label("Content Provider Class");
		lContentProviderPackage = new Label("Content Provider Package");

		tfContentProviderClass = new TextField();
		tfContentProviderPackage = new TextField();

		verticalLayout.getChildren().add(lContentProviderPackage);
		verticalLayout.getChildren().add(tfContentProviderPackage);

		verticalLayout.getChildren().add(lContentProviderClass);
		verticalLayout.getChildren().add(tfContentProviderClass);

		verticalLayout.getChildren().add(new Label("Content Provider Configuration"));

		return verticalLayout;

	}

	/**
	 * Android Content Provider Configuration
	 */

	private Label lTransactionsManagerClass;
	private Label lTransactionsManagerPackage;

	private TextField tfTransactionsManagerClass;
	private TextField tfTransactionsManagerPackage;

	private VerticalLayout createAndroidTransactionsManagerConfiguration() {

		VerticalLayout verticalLayout = new VerticalLayout(true);

		lTransactionsManagerClass = new Label("Transactions Manager Class");
		lTransactionsManagerPackage = new Label("Transactions Manager Package");

		tfTransactionsManagerClass = new TextField();
		tfTransactionsManagerPackage = new TextField();

		verticalLayout.getChildren().add(lTransactionsManagerPackage);
		verticalLayout.getChildren().add(tfTransactionsManagerPackage);

		verticalLayout.getChildren().add(lTransactionsManagerClass);
		verticalLayout.getChildren().add(tfTransactionsManagerClass);

		verticalLayout.getChildren().add(new Label("Transactions Manager Configuration"));

		return verticalLayout;
	}

	/**
	 * Android SQLite Database Configuration
	 */

	private Label lDatabaseName;
	private Label lDatabaseVersion;
	private Label lSQLiteOpenHelperClass;
	private Label lSQLiteOpenHelperPackage;
	private Label lTablesSchemasPackage;
	private Label lTablesCrudPackage;
	private Label lTableModelsPackage;

	private TextField tfDatabaseName;
	private TextField tfDatabaseVersion;
	private TextField tfSQLiteOpenHelperClass;
	private TextField tfSQLiteOpenHelperPackage;
	private TextField tfTablesSchemasPackage;
	private TextField tfTablesCrudPackage;
	private TextField tfTableModelsPackage;

	private VerticalLayout createSQLiteDatabaseConfiguration() {

		VerticalLayout verticalLayout = new VerticalLayout(true);

		lDatabaseName = new Label("Database name");
		tfDatabaseName = new TextField();
		verticalLayout.getChildren().addAll(lDatabaseName, tfDatabaseName);

		lDatabaseVersion = new Label("Database version");
		tfDatabaseVersion = new TextField();
		verticalLayout.getChildren().addAll(lDatabaseVersion, tfDatabaseVersion);

		lSQLiteOpenHelperClass = new Label("SQLite OpenHelper Class");
		tfSQLiteOpenHelperClass = new TextField();
		verticalLayout.getChildren().addAll(lSQLiteOpenHelperClass, tfSQLiteOpenHelperClass);

		lSQLiteOpenHelperPackage = new Label("SQLite OpenHelper Package");
		tfSQLiteOpenHelperPackage = new TextField();
		verticalLayout.getChildren().addAll(lSQLiteOpenHelperPackage, tfSQLiteOpenHelperPackage);

		lTablesSchemasPackage = new Label("Table Schemas package");
		tfTablesSchemasPackage = new TextField();
		verticalLayout.getChildren().addAll(lTablesSchemasPackage, tfTablesSchemasPackage);

		lTablesCrudPackage = new Label("Tables CRUD Package");
		tfTablesCrudPackage = new TextField();
		verticalLayout.getChildren().addAll(lTablesCrudPackage, tfTablesCrudPackage);

		lTableModelsPackage = new Label("Table models package");
		tfTableModelsPackage = new TextField();
		verticalLayout.getChildren().addAll(lTableModelsPackage, tfTableModelsPackage);

		return verticalLayout;
	}
}
