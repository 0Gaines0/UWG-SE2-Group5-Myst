module Myst {
	requires javafx.controls;
	requires org.junit.jupiter.api;
	requires junit;
	
	opens application to javafx.graphics, javafx.fxml;
}
