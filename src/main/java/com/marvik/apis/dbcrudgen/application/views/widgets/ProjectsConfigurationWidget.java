/**
 * 
 */
package com.marvik.apis.dbcrudgen.application.views.widgets;

import java.util.List;

import com.marvik.apis.dbcrudgen.application.views.containers.projectconfiguration.android.AndroidProjectConfigurationContainer;
import com.marvik.apis.dbcrudgen.application.views.containers.projectconfiguration.php.PHPProjectConfigurationContainer;
import com.marvik.apis.dbcrudgen.application.views.layouts.HorizontalLayout;
import com.marvik.apis.dbcrudgen.schemamodels.database.Database;

import javafx.scene.layout.StackPane;

/**
*Created on Feb 5, 2016-6:11:22 AM by victor
*/

/**
 * 
 * ProjectsConfigurationWidget
 * 
 * This widget stacks all the panes/widgets that are used to provide project
 * configuration
 * 
 * These widgets include the widget to provide android project configuration php
 * project configuration, the j2se project configuration among others that may
 * be added in the future
 * 
 * @author victor
 *
 */
public class ProjectsConfigurationWidget extends HorizontalLayout {

	// Android project configuration container
	private AndroidProjectConfigurationContainer androidProjectConfigurationContainer;

	// PHP project configuration container
	private PHPProjectConfigurationContainer phpProjectConfigurationContainer;

	/**
	 * ProjectsConfigurationWidget
	 * 
	 * @param mysqlDatabases
	 */

	List<Database> mysqlDatabases;

	public ProjectsConfigurationWidget(List<Database> mysqlDatabases) {
		this.mysqlDatabases = mysqlDatabases;
		
		initContainers();

		addContainers();

	}

	/**
	 * Initialises all the project configuration containers
	 */
	private void initContainers() {
		androidProjectConfigurationContainer = new AndroidProjectConfigurationContainer(mysqlDatabases);

		phpProjectConfigurationContainer = new PHPProjectConfigurationContainer(mysqlDatabases);
	}

	/**
	 * add all the project configuration containers to the window
	 */
	private void addContainers() {

		// Add Android Project Configuration Container
		addAndroidProjectConfigurationContainer();

		// Add PHP Project Configuration Container
		addPHPProjectConfiguraionContaier();
	}

	/**
	 * Adds the android project configuration container to the window
	 */
	private void addAndroidProjectConfigurationContainer() {
		getChildren().add(androidProjectConfigurationContainer);
	}

	/**
	 * Add PHP Project Configuration Container
	 */
	private void addPHPProjectConfiguraionContaier() {
		getChildren().add(phpProjectConfigurationContainer);

	}
}
