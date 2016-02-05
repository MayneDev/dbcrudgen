/**
 * 
 */
package com.marvik.apis.dbcrudgen.application.views.lists;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/**
*Created on Feb 5, 2016-5:24:07 AM by victor
*/

/**
 * @author victor
 *
 */
public class StyledListView<T> extends ListView<T> {

	/**
	 * Default height for each item in the list(ObservableList<T>)
	 */
	private static final double DEFAULT_NODE_HEIGHT = 24.0;

	public StyledListView(Cursor cursor) {
		new StyledListView<T>(cursor, new Insets(0.0));
	}

	public StyledListView(Cursor cursor, Insets insets) {
		new StyledListView<T>(SelectionMode.SINGLE, cursor, insets);
	}

	public StyledListView(SelectionMode selectionMode) {
		new StyledListView<T>(selectionMode, Cursor.DEFAULT);
	}

	public StyledListView(SelectionMode selectionMode, Insets insets) {
		new StyledListView<T>(selectionMode, Cursor.DEFAULT);
	}

	public StyledListView(SelectionMode selectionMode, Cursor cursor) {
		new StyledListView<T>(selectionMode, cursor, new Insets(0.0));
	}

	public StyledListView(SelectionMode selectionMode, Cursor cursor, Insets insets) {
		getSelectionModel().setSelectionMode(selectionMode);
		setCursor(cursor);
		setPadding(insets);
		setEditable(false);
		setHover(true);
	}

	/**
	 * Makes the list size to equal the size of the items
	 */
	public void wrap() {
		ObservableList<T> listItems = getItems();
		int itemsCount = listItems.size();

		double listItemHeight = DEFAULT_NODE_HEIGHT;

		setMaxHeight(itemsCount * listItemHeight);
	}
}
