/**
 * 
 */
package com.marvik.apis.dbcrudgen.application.views.layouts;

import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
*Created on Feb 5, 2016-6:21:10 AM by victor
*/

/**
 * 
 * A layout that arranges item horizontally
 * 
 * @author victor
 *
 */
public class HorizontalLayout extends HBox {

	/**
	 * A layout that arranges item horizontally
	 */
	public HorizontalLayout() {
		new HorizontalLayout(new Insets(10, 10, 10, 10));
	}

	/**
	 * A layout that arranges item horizontally
	 */
	public HorizontalLayout(Insets insets) {
		setPadding(insets);
	}

	/**
	 * A layout that arranges item horizontally
	 */
	public HorizontalLayout(Border border, Insets insets) {
		setPadding(insets);
		setBorder(border);
	}

}
