package work;

import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

public class PdfWrite {
	private static final String FILE_NAME = "Invoice.pdf";

	//create PDF copy method
	public static void createPDF( String text) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
			document.open();
			document.add(new Paragraph(text));
			document.close();

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}

	}
}
