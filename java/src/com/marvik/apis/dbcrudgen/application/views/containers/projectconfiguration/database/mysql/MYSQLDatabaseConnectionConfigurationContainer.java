/**
 * 
 */
package com.marvik.apis.dbcrudgen.application.views.containers.projectconfiguration.database.mysql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.UserDataHandler;

import com.marvik.apis.dbcrudgen.application.events.UserEventActions;
import com.marvik.apis.dbcrudgen.application.tasks.TasksExecutor;
import com.marvik.apis.dbcrudgen.application.views.containers.projectconfiguration.ProjectConfigurationContainer;
import com.marvik.apis.dbcrudgen.application.views.layouts.HorizontalLayout;
import com.marvik.apis.dbcrudgen.application.views.layouts.VerticalLayout;
import com.marvik.apis.dbcrudgen.application.views.lists.StyledListView;
import com.marvik.apis.dbcrudgen.application.views.windows.MainWindow;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;

/**
*Created on Feb 5, 2016-3:18:04 AM by victor
*/

/**
 * @author victor
 *
 */
public class MYSQLDatabaseConnectionConfigurationContainer extends ProjectConfigurationContainer {

	private UserEventActions userEventActions;

	private Button btShowTables;
	private Label lNavigation;
	private Label lTabels;
	
	private StyledListView<String> lvDatabases;
	private StyledListView<String> lvTables;

	private HorizontalLayout databaseSchemasLayout;
	VerticalLayout databasesLayout;
	VerticalLayout tablesLayout;

	/**
	 * @param parent
	 */
	public MYSQLDatabaseConnectionConfigurationContainer() {
		super();
		initChildren();
		setNavigationLabel("Databases");
	}

	private void initChildren() {

		databaseSchemasLayout = new HorizontalLayout();
		databaseSchemasLayout.setPadding(new Insets(10));
		
		databasesLayout = new VerticalLayout();
		databasesLayout.setPadding(new Insets(10));
		
		// init the navigation label
		lNavigation = new Label();
		lNavigation.setPadding(new Insets(10));
		databasesLayout.getChildren().add(lNavigation);

		// init the database list view
		lvDatabases = new StyledListView<String>(SelectionMode.SINGLE, Cursor.HAND, new Insets(5));
		databasesLayout.getChildren().add(lvDatabases);

		// init the show table button
		btShowTables = new Button("Show Tables");
		btShowTables.setOnAction(e -> showTablesOnDatabaseSelected());
		databasesLayout.getChildren().add(btShowTables);

		databaseSchemasLayout.getChildren().add(databasesLayout);
		
		
		tablesLayout = new VerticalLayout();
		tablesLayout.setPadding(new Insets(10));
		
		lTabels = new Label("Tables");
		lTabels.setPadding(new Insets(10));
		tablesLayout.getChildren().add(lTabels);
		databaseSchemasLayout.getChildren().add(tablesLayout);
		this.getChildren().add(databaseSchemasLayout);
	}

	public void showTablesOnDatabaseSelected(){
		if (lvTables == null) {
			lvTables = new StyledListView<String>(SelectionMode.SINGLE, Cursor.HAND, new Insets(5));
			tablesLayout.getChildren().add(lvTables);
		} else {
			lvTables.getItems().removeAll(lvTables.getItems());
		}

		String selectedDatabase = lvDatabases.getSelectionModel().getSelectedItems().get(0);
		List<String> tables;

		tables = getTasksExecutor().getDatabaseTables(selectedDatabase);

		lvTables.getItems().addAll(tables);

	}

	/**
	 * Creates a list of all the MYSQL database in the PC
	 */
	public void addDatabasesListView(List<Database> databases) {

		populateDatabasesList(databases);

	}

	/**
	 * Set the current navigation
	 */
	private void setNavigationLabel(String navigation) {
		lNavigation.setText(navigation);
	}

	/**
	 * Creates all the data for the database
	 */
	private void populateDatabasesList(List<Database> databases) {

		ArrayList<String> databaseNames = new ArrayList<>();

		for (Database database : databases) {
			databaseNames.add(database.getDatabaseName());
		}

		// Add all the database names to the listview
		for (String databaseName : databaseNames) {
			lvDatabases.getItems().add(databaseName);
		}

		lvDatabases.wrap();
	}

	/**
	 * @param tasksExecutor
	 */
	private TasksExecutor tasksExecutor;

	public void setTasksExecutor(TasksExecutor tasksExecutor) {
		this.tasksExecutor = tasksExecutor;
	}

	/**
	 * @return the tasksExecutor
	 */
	public TasksExecutor getTasksExecutor() {
		return tasksExecutor;
	}
}
