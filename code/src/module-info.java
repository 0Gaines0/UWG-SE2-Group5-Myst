module Myst {
	requires javafx.controls;
	requires org.junit.jupiter.api;
	requires junit;
	requires java.desktop;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	exports application.view.login to javafx.fxml;
	opens application.view.login to javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
}