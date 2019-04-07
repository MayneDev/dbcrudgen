package com.marvik.apis.dbcrudgen.projects.android.configuration;

import com.marvik.apis.dbcrudgen.core.utils.NativeUtils;
import com.marvik.apis.dbcrudgen.platforms.android.configuration.AndroidContentProviderConfiguration;
import org.omg.IOP.TAG_JAVA_CODEBASE;

/**
 *
 * AndroidProjectConfiguration
 *
 * Used for setting up all the configuration for an android project
 *
 * @author victor
 *
 */
public class AndroidProjectConfiguration {

	private String _javaFilesStorageLocation;
	private String _projectDefaultJavaFilesRootStorageLocation;

	private String projectStorageDir;
	private String javaSrcDir;
	private String packageName;
	private AndroidContentProviderConfiguration androidContentProviderConfiguration;

	/**
	 * Android Project Configuration
	 *
	 * @param projectStorageDir
	 * @param javaSrcDir
	 * @param packageName
	 * @param androidContentProviderConfiguration
	 */
	public AndroidProjectConfiguration(String projectStorageDir, String javaSrcDir, String packageName,
			AndroidContentProviderConfiguration androidContentProviderConfiguration) {
		this.androidContentProviderConfiguration = androidContentProviderConfiguration;
		this.projectStorageDir = NativeUtils.transformPath( ".", NativeUtils.getFileSeparator(), projectStorageDir) ;
		this.packageName = packageName;
		this.javaSrcDir =   NativeUtils.transformPath( ".", NativeUtils.getFileSeparator(),javaSrcDir ) ;

		// ActualPath Where Java Files are saved
		_setJavaFilesStorageLocation();

	}

	/**
	 * @return the projectStorageDir
	 */
	public String getProjectStorageDir() {
		return projectStorageDir;
	}

	/**
	 * @param projectStorageDir
	 *            the projectStorageDir to set
	 */
	public void setProjectStorageDir(String projectStorageDir) {
		this.projectStorageDir = projectStorageDir;
	}

	/**
	 * @return the androidContentProviderConfiguration
	 */
	public AndroidContentProviderConfiguration getAndroidContentProviderConfiguration() {
		return androidContentProviderConfiguration;
	}

	/**
	 * @param androidContentProviderConfiguration
	 *            the androidContentProviderConfiguration to set
	 */
	public void setAndroidContentProviderConfiguration(
			AndroidContentProviderConfiguration androidContentProviderConfiguration) {
		this.androidContentProviderConfiguration = androidContentProviderConfiguration;
	}

	/**
	 * @return the javaSrcDir
	 */
	public String getJavaSrcDir() {
		return javaSrcDir;
	}

	/**
	 * @param javaSrcDir
	 *            the javaSrcDir to set
	 */
	public void setJavaSrcDir(String javaSrcDir) {
		this.javaSrcDir = javaSrcDir;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName
	 *            the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * Sets the path to the java files storage location. This is usually the
	 * path to the src folder
	 */
	private void _setJavaFilesStorageLocation() {
		_javaFilesStorageLocation = getProjectStorageDir() + NativeUtils.getFileSeparator() + getJavaSrcDir();
		_setProjectDefaultJavaFilesRootStorageLocation();
	}

	/**
	 * The path of the java files storage location
	 *
	 * @return _javaFilesStorageLocation
	 */
	public String _getJavaFilesStorageLocation() {
		return _javaFilesStorageLocation;
	}

	/**
	 * Sets the default path where java files are in the project This is usually
	 * the project name storage path + java src folder + the project package
	 * folder(which is generated from the package name
	 */
	private void _setProjectDefaultJavaFilesRootStorageLocation() {
		String packageNameAsPath = NativeUtils.parsePackagePath(getPackageName());
		_projectDefaultJavaFilesRootStorageLocation = _javaFilesStorageLocation + NativeUtils.getFileSeparator()
				+ packageNameAsPath;
	}

	/**
	 * Returns the default path where java files are in the project This is
	 * usually the project name storage path + java src folder + the project
	 * package folder(which is generated from the package name
	 */
	public String _getProjectDefaultJavaFilesRootStorageLocation() {
		return _projectDefaultJavaFilesRootStorageLocation;
	}

}
