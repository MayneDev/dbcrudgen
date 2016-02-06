/**
 * 
 */
package com.marvik.apis.dbcrudgen.application.views.containers.projectconfiguration.android;

import java.util.List;

import com.marvik.apis.dbcrudgen.application.tasks.TasksExecutor;
import com.marvik.apis.dbcrudgen.application.views.containers.projectconfiguration.ProjectConfigurationContainer;
import com.marvik.apis.dbcrudgen.application.views.layouts.HorizontalLayout;
import com.marvik.apis.dbcrudgen.application.views.layouts.VerticalLayout;
import com.marvik.apis.dbcrudgen.application.views.windows.MainWindow;
import com.marvik.apis.dbcrudgen.creator.android.AndroidCRUDCreator;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidContentProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.AndroidDatabaseConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.provider.ProviderConfiguration;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.database.transactions.TransactionManagerConfiguration;
import com.marvik.apis.dbcrudgen.projects.android.configuration.AndroidProjectConfiguration;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;

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

	private Button btCreateSourceCode;

	public AndroidProjectConfigurationContainer(List<Database> mysqlDatabases) {
		super();
		VerticalLayout androidRootLayout = new VerticalLayout(true);
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

		HorizontalLayout layout = new HorizontalLayout();
		Button bSetData = new Button("Set Data");
		bSetData.setOnAction(e -> setData());
		layout.getChildren().add(bSetData);

		btCreateSourceCode = new Button("Create SourceCode");
		btCreateSourceCode.setOnAction(e -> createAndroidProjectSourceCode());
		layout.getChildren().add(btCreateSourceCode);
		androidRootLayout.getChildren().add(layout);
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
		vlBasicProjectConfiguration.getChildren().add(new Label("Content Provider Configuration"));
		VerticalLayout contentProviderConfig = createAndroidContentProviderConfiguration();
		vlBasicProjectConfiguration.getChildren().add(contentProviderConfig);

		// Transaction manager class configuration String
		vlBasicProjectConfiguration.getChildren().add(new Label("Transaction Manager Configuration"));
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

	private void createAndroidProjectSourceCode() {

		if (tfDatabaseName.getText().length() < 1) {
			System.out.println("Database Name cannot be null");
			return;
		}
		if (tfDatabaseVersion.getText().length() < 1) {
			System.out.println("Database Version cannot be null");
			return;
		}
		if (tfSQLiteOpenHelperClass.getText().length() < 1) {
			System.out.println("SQLiteOpenHelperClass cannot be null");
			return;
		}
		if (tfSQLiteOpenHelperPackage.getText().length() < 1) {
			System.out.println("SQLite Class Package cannot be null");
			return;
		}
		if (tfTablesSchemasPackage.getText().length() < 1) {
			System.out.println("Table Schemas Package cannot be null");
			return;
		}
		if (tfTablesCrudPackage.getText().length() < 1) {
			System.out.println("Tables Crud Package cannot be null");
			return;
		}
		if (tfTableModelsPackage.getText().length() < 1) {
			System.out.println("Table Model Package cannot be null");
			return;
		}

		if (tfContentProviderPackage.getText().length() < 1) {
			System.out.println("ContentProviders Package cannot be null");
			return;
		}
		if (tfContentProviderClass.getText().length() < 1) {
			System.out.println("Content Provider Class");
			return;
		}

		if (tfTransactionsManagerPackage.getText().length() < 1) {
			System.out.println("Transaction Manager Package cannot be null");
			return;
		}
		if (tfTransactionsManagerClass.getText().length() < 1) {
			System.out.println("Transaction Manager Class cannot be null");
			return;
		}

		if (tfAndroidProjectStorageDirectory.getText().length() < 1) {
			System.out.println("Project Storage Directory cannot be null");
			return;
		}
		if (tfAndroidProjectJavaSrcDirs.getText().length() < 1) {
			System.out.println("Java Src Dirs cannot be null");
			return;
		}
		if (tfAndroidProjectPackageName.getText().length() < 1) {
			System.out.println("Project Name cannot be null");
			return;
		}

		String databaseName = tfDatabaseName.getText().toString();
		int databaseVersion = Integer.parseInt(tfDatabaseVersion.getText());
		String sqliteOpenHelperClass = tfSQLiteOpenHelperClass.getText().toString();
		String sqliteOpenHelperClassPackage = tfSQLiteOpenHelperPackage.getText().toString();
		String tablesSchemasPackage = tfTablesSchemasPackage.getText().toString();
		String tablesCRUDPackage = tfTablesCrudPackage.getText().toString();
		String tablesInfosModelClassesPackage = tfTableModelsPackage.getText().toString();

		TasksExecutor tasksExecutor = new TasksExecutor();
		Database database = tasksExecutor.createDatabaseModel(databaseName);

		AndroidDatabaseConfiguration androidDatabaseConfiguration = new AndroidDatabaseConfiguration(databaseName,
				databaseVersion, sqliteOpenHelperClass, sqliteOpenHelperClassPackage, tablesSchemasPackage,
				tablesCRUDPackage, tablesInfosModelClassesPackage);

		String contentProviderPackage = tfContentProviderPackage.getText();
		String contentProviderClass = tfContentProviderClass.getText();
		ProviderConfiguration providerConfiguration = new ProviderConfiguration(contentProviderClass,
				contentProviderPackage);

		String transactionManagerPackage = tfTransactionsManagerPackage.getText();
		String transactionManagerClass = tfTransactionsManagerClass.getText();
		TransactionManagerConfiguration transactionManagerConfiguration = new TransactionManagerConfiguration(
				transactionManagerPackage, transactionManagerClass);

		AndroidContentProviderConfiguration androidContentProviderConfiguration = new AndroidContentProviderConfiguration(
				providerConfiguration, transactionManagerConfiguration, androidDatabaseConfiguration);

		String projectStorageDir = tfAndroidProjectStorageDirectory.getText();
		String javaSrcDir = tfAndroidProjectJavaSrcDirs.getText();
		String packageName = tfAndroidProjectPackageName.getText();
		AndroidProjectConfiguration androidProjectConfiguration = new AndroidProjectConfiguration(projectStorageDir,
				javaSrcDir, packageName, androidContentProviderConfiguration);

		AndroidCRUDCreator androidCRUDCreator = new AndroidCRUDCreator();
		androidCRUDCreator.setAndroidProjectConfiguration(androidProjectConfiguration);
		androidCRUDCreator.createProject(database);
	}

	private void setData() {
		String databasename = MainWindow.databaseName;

		tfDatabaseName.setText(databasename);
		tfDatabaseVersion.setText("1");
		tfSQLiteOpenHelperClass.setText("DatabaseManager");
		tfSQLiteOpenHelperPackage.setText("database\\sqliteopenhelper");
		tfTablesSchemasPackage.setText("database\\tableschemas");
		tfTablesCrudPackage.setText("database\\tablescrud");
		tfTableModelsPackage.setText("database\\tablemodels");

		tfContentProviderPackage.setText("database\\contentprovider");
		tfContentProviderClass.setText("DataProvider");

		tfTransactionsManagerPackage.setText("database\\transactionsmanager");
		tfTransactionsManagerClass.setText("TransactionManager");

		tfAndroidProjectStorageDirectory.setText("G:\\4thYr\\GDGMMUST");
		tfAndroidProjectJavaSrcDirs.setText("app\\src\\main\\java");
		tfAndroidProjectPackageName.setText("com.gdgmmust.gdgevents");
	}
}
