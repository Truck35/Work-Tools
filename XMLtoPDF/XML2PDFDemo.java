package XMLtoPDF;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;

public class XML2PDFDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XML2PDF PDFObject = new XML2PDF();
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><name>Truck</name>";
		try {
			PDFObject.convertToPDF(xml);
		} catch (FOPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("PDF created");
	}

}
