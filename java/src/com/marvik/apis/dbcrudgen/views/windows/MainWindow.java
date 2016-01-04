package com.marvik.apis.dbcrudgen.views.windows;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow extends Application{

	private Scene mScene;
	private Stage mStage;
	
	private String mStageName;
	
	public MainWindow (){
		setStageName("Database CRUD Generator");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		initStage(stage,getStageName());
	}

	private void initStage(Stage stage,String stageName){
		setStage(stage);
		
		
		StackPane stackPane = new StackPane();
		mScene = new Scene(stackPane,1200,600);
		
		
		getStage().setTitle(getStageName());
		getStage().setScene(getScene());
		getStage().show();
	}
	/**
	 * @return the mScene
	 */
	public Scene getScene() {
		return mScene;
	}
	/**
	 * @param mScene the mScene to set
	 */
	public void setScene(Scene mScene) {
		this.mScene = mScene;
	}
	/**
	 * @return the mStage
	 */
	public Stage getStage() {
		return mStage;
	}
	/**
	 * @param mStage the mStage to set
	 */
	public void setStage(Stage mStage) {
		this.mStage = mStage;
	}
	/**
	 * @return the mStageName
	 */
	public String getStageName() {
		return mStageName;
	}
	/**
	 * @param mStageName the mStageName to set
	 */
	public void setStageName(String mStageName) {
		this.mStageName = mStageName;
	}
	
	
	
}
