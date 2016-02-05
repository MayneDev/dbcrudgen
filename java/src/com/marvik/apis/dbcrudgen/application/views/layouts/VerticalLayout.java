/**
 * 
 */
package com.marvik.apis.dbcrudgen.application.views.layouts;

import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;

/**
*Created on Feb 5, 2016-6:21:23 AM by victor
*/

/**
 * 
 * A layout that arranges item vertically
 * 
 * @author victor
 *
 */
public class VerticalLayout extends VBox {

	/**
	 * Vertical Layout arranges item vertically
	 */
	public VerticalLayout() {
		new VerticalLayout(false);
	}

	/**
	 * Vertical Layout arranges item vertically
	 */
	public VerticalLayout(boolean styled) {

		String style = ("-fx-padding:10;-fx-margin:10;-fx-border-color:black;fx-border-style:solid;");
		new VerticalLayout(null, new Insets(10, 10, 10, 10), style);
		
		//STYLES NOT SHOWING-ADDED THIS LITTLE HACK
		setStyle(style);
		setSpacing(5);
	}

	/**
	 * Vertical Layout arranges item vertically
	 */
	public VerticalLayout(Insets insets) {
		new VerticalLayout(null,insets);
	}

	/**
	 * Vertical Layout arranges item vertically
	 */
	public VerticalLayout(Border border) {
		new VerticalLayout(border, new Insets(10, 10, 10, 10));
	}

	/**
	 * Vertical Layout arranges item vertically
	 */
	public VerticalLayout(Border border, Insets insets) {
		new VerticalLayout(border, insets, new String());
	}

	/**
	 * Vertical Layout arranges item vertically
	 */
	public VerticalLayout(Border border, Insets insets, String style) {
		setPadding(insets);
		setBorder(border);
		setStyle(style);
		//setSpacing(10);
	}

}
