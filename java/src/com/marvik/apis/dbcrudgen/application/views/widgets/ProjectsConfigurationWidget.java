/**
 * 
 */
package com.marvik.apis.dbcrudgen.application.views.widgets;

import com.marvik.apis.dbcrudgen.application.views.containers.projectconfiguration.android.AndroidProjectConfigurationContainer;

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
public class ProjectsConfigurationWidget extends StackPane {

	private AndroidProjectConfigurationContainer androidProjectConfigurationContainer;

	/**
	 * ProjectsConfigurationWidget
	 */
	public ProjectsConfigurationWidget() {

		initContainers();

		addContainers();

	}

	/**
	 * Initialises all the project configuration containers
	 */
	private void initContainers() {
		androidProjectConfigurationContainer = new AndroidProjectConfigurationContainer();
	}

	/**
	 * add all the project configuration containers to the window
	 */
	private void addContainers() {

		// Add Android Project Configuration Container
		addAndroidProjectConfigurationContainer();
	}

	/**
	 * Adds the android project configuration container to the window
	 */
	private void addAndroidProjectConfigurationContainer() {
		getChildren().add(androidProjectConfigurationContainer);
	}

}
