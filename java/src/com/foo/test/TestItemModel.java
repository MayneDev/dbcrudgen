/**
 * 
 */
package com.foo.test;

/**
*Created on Feb 9, 2016-5:25:22 PM by victor
*/

/**
 * @author victor
 *
 */
public class TestItemModel {
	private int testItemId;
	private String testItemLabel;

	public TestItemModel(int testItemId, String testItemLabel) {
		this.testItemId = testItemId;
		this.testItemLabel = testItemLabel;
	}

	/**
	 * @return the testItemId
	 */
	public int getTestItemId() {
		return testItemId;
	}

	/**
	 * @param testItemId the testItemId to set
	 */
	public void setTestItemId(int testItemId) {
		this.testItemId = testItemId;
	}

	/**
	 * @return the testItemLabel
	 */
	public String getTestItemLabel() {
		return testItemLabel;
	}

	/**
	 * @param testItemLabel the testItemLabel to set
	 */
	public void setTestItemLabel(String testItemLabel) {
		this.testItemLabel = testItemLabel;
	}
	
}
