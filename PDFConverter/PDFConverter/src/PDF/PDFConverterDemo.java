package PDF;

import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;

import javax.xml.transform.TransformerException;

//import FOPException;
import PDF.PDFConverter;

public class PDFConverterDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PDFConverter PDFObject = new PDFConverter();
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Bay><DeptNumber>27</DeptNumber><DeptName>Electrical</DeptName>"+
		              "<AliseNumber>24</AliseNumber><BayLocation>24-002</BayLocation></Bay>";
		try {
			PDFObject.convertToPDF(xml);
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
			
		
		System.out.println("PDF created");
	}
}
