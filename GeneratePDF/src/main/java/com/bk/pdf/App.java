package com.bk.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.net.URI;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        generatePDFbyPDFBOX();
        //generatePDFbyFlyingSaucer();
        
    }
    
    private static void generatePDFbyFlyingSaucer() {
    	//createFSpdf1();
    	createFSpdf2();
    	
	}

	private static void createFSpdf2() {
		try {
			FileReader fileReader = new FileReader("D:\\tmp\\about.html");
			String html=IOUtils.toString(fileReader);
			
			FileInputStream fin=new FileInputStream("D:\\tmp\\about.html");
					
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //Document doc = docBuilder.parse(new StringBufferInputStream(html));
            Document doc = docBuilder.parse(fin);
            
            String fileNameWithPath = "D:\\tmp\\" + "PDF-FromHtmlString2.pdf";
            FileOutputStream fos = new FileOutputStream( fileNameWithPath );

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(doc, null);
            renderer.layout();
            renderer.createPDF( fos );
            
            
            fos.close();
            System.out.println("FlyingSaucer PDF created2");
          }catch(Exception e) {
            	e.printStackTrace();
          }
	}

	private static void createFSpdf1() {
		try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("<html>");
            buffer.append("<head>");
            buffer.append("<style type='text/css'>");
            buffer.append(" @page {background-color: #f0f0f0;}");
            buffer.append("</style>");
            buffer.append("</head>");
            buffer.append("<body>");
            buffer.append("<h2>Hello</h2>");
            buffer.append("</body>");
            buffer.append("</html>");
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docBuilder.parse(new StringBufferInputStream(buffer.toString()));
            
            String fileNameWithPath = "D:\\tmp\\" + "PDF-FromHtmlString1.pdf";
            FileOutputStream fos = new FileOutputStream( fileNameWithPath );

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(doc, null);
            renderer.layout();
            renderer.createPDF( fos );
            
            
            fos.close();
            System.out.println("FlyingSaucer PDF created");
          }catch(Exception e) {
            	e.printStackTrace();
          }
	}

	public static void generatePDFbyPDFBOX() {
    	 
    	try {
    		//Creating PDF document object 
            PDDocument document = new PDDocument(); 
            
            //Add Empty Pages
            //addEmptyPages(document); 
            
            //Set key properties to document
            //setKeyProperety(document);
            
            //Add a page with some text 
            //addPageWithText(document);
		    
           //Drawing the image in the PDF document
           // addImage(document);	
          
           //Protect PDF
           //protectMyDoc(document); 
            
            
            
            //Saving the document
            document.save("D:\\tmp\\my_doc.pdf");
               
            System.out.println("PDF created");  
            
            //Closing the document  
            document.close();
            
            //Loading and Reading an existing document
            //readTheText();
           
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	
    }

	private static void protectMyDoc(PDDocument document) throws IOException {
		//Creating access permission object
		AccessPermission ap = new AccessPermission();         

		//Creating StandardProtectionPolicy object
		StandardProtectionPolicy spp = new StandardProtectionPolicy("1234", "1234", ap);

		//Setting the length of the encryption key
		spp.setEncryptionKeyLength(128);

		//Setting the access permissions
		spp.setPermissions(ap);

		//Protecting the document
		document.protect(spp);

		System.out.println("Document encrypted");
	}

	private static void addImage(PDDocument document) throws IOException {
		//Creating a blank page 
		PDPage blankPage = new PDPage();

		//Adding the blank page to the document
		document.addPage( blankPage );
		
		//Creating PDImageXObject object
		PDImageXObject pdImage = PDImageXObject.createFromFile("D:\\tmp\\LeaveBalnceOnApril.png",document);
		 
		//creating the PDPageContentStream object
		PDPageContentStream contents = new PDPageContentStream(document, blankPage);

		//Drawing the image in the PDF document
		contents.drawImage(pdImage, 70, 250);

		System.out.println("Image inserted");
		
		//Closing the PDPageContentStream object
		contents.close();
	}

	private static void readTheText() throws InvalidPasswordException, IOException {
		File file = new File("D:\\tmp\\my_doc.pdf");
		PDDocument document = PDDocument.load(file);

		//Instantiate PDFTextStripper class
		PDFTextStripper pdfStripper = new PDFTextStripper();

		//Retrieving text from PDF document
		String text = pdfStripper.getText(document);
		System.out.println(text);

		//Closing the document
		document.close();
	}

	private static void addPageWithText(PDDocument document) throws IOException {
		PDPage blankPage = new PDPage();
		
		PDPageContentStream contentStream = new PDPageContentStream(document, blankPage); 
		   
		  //Begin the Content stream 
		  contentStream.beginText(); 
		   
		  //Setting the font to the Content stream
		  contentStream.setFont( PDType1Font.TIMES_ROMAN, 16 );
		   
		  //Setting the leading
		  contentStream.setLeading(14.5f);

		  //Setting the position for the line
		  contentStream.newLineAtOffset(25, 725);

		  String text1 = "This is an example of adding text to a page in the pdf document. we can add as many lines";
		  String text2 = "as we want like this using the ShowText()  method of the ContentStream class";

		  //Adding text in the form of string
		  contentStream.showText(text1);
		  contentStream.newLine();
		  contentStream.showText(text2);
		  //Ending the content stream
		  contentStream.endText();

		  System.out.println("Content added");

		  //Closing the content stream
		  contentStream.close();
		  
		//Adding the blank page to the document
		document.addPage( blankPage );
	}

	private static void addEmptyPages(PDDocument document) {
		for (int i=0; i<10; i++) {
		    //Creating a blank page 
		    PDPage blankPage = new PDPage();

		    //Adding the blank page to the document
		    document.addPage( blankPage );
		 }
	}

	private static void setKeyProperety(PDDocument document) {
		//Creating the PDDocumentInformation object 
	      PDDocumentInformation pdd = document.getDocumentInformation();

	      //Setting the author of the document
	      pdd.setAuthor("Biswakalyan");
	       
	      // Setting the title of the document
	      pdd.setTitle("Sample document"); 
	       
	      //Setting the creator of the document 
	      pdd.setCreator("PDF Box Example"); 
	       
	      //Setting the subject of the document 
	      pdd.setSubject("Example document"); 
	       
	      //Setting the created date of the document 
	      Calendar date = new GregorianCalendar();
	      date.set(2015, 11, 5); 
	      pdd.setCreationDate(date);
	      //Setting the modified date of the document 
	      date.set(2016, 6, 5); 
	      pdd.setModificationDate(date); 
	       
	      //Setting keywords for the document 
	      pdd.setKeywords("sample, example, my pdf");
		
	}
}
