module FlyOutJavaFX {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	// linha 6 permite o package model utilizador javafx.base
	opens model to javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.fxml;
}
