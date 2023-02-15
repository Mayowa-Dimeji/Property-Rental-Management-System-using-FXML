module work {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires itextpdf;
	requires javax.mail;
	requires activation;
	requires org.junit.jupiter.api;
	
	opens work to javafx.graphics,javafx.base, javafx.fxml;
}
