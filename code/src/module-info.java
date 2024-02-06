module Myst {
	requires javafx.controls;
	requires org.junit.jupiter.api;
	requires junit;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
}
