package com.bk.pdf;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.lowagie.text.pdf.PdfDocument;

public class PdfBoxPoc {
	public static void main(String[] args) throws Exception{
		
		modifTrekPdf(loadExistingOne());
		//readTheText(loadExistingOne());
		//editText(loadExistingOne());
	}
	
	private static void modifTrekPdf(PDDocument loadExistingOne) throws Exception {
		//Instantiate PDFTextStripper class
		PDFTextStripper pdfStripper = new PDFTextStripper();

		//Retrieving text from PDF document
		String text = pdfStripper.getText(loadExistingOne);
		System.out.println(text);
		Document doc = new Document(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8)));
	      List<String> documentFields = new DocumentFieldParser().extractTags(doc);
	   
	             try {
					doc.getRange().replace("57000", "40000", false, false);
				} catch (Exception e) {
					e.printStackTrace();
				}

	       
	      //doc.save(new ByteArrayOutputStream(), SaveFormat.PDF);
	             doc.save("D:\\tmp\\" + "Table.pdf");
	}

	private static PDDocument loadExistingOne() throws InvalidPasswordException, IOException {
		File file = new File("D:\\tmp\\Chandigarh -Manali-Leh-Manali -Chandigarh.pdf");
		return PDDocument.load(file);
	}
	
	private static void readTheText(PDDocument document) throws InvalidPasswordException, IOException {
		//Instantiate PDFTextStripper class
		PDFTextStripper pdfStripper = new PDFTextStripper();

		//Retrieving text from PDF document
		String text = pdfStripper.getText(document);
		System.out.println(text);

		//Closing the document
		document.close();
	}
	
	private static void editText(PDDocument document) throws Exception {
		 //Retrieving the pages of the document 
		  Map<String, String> map = new HashMap<String, String>();
		  map.put("ReferredBy", "ReferredBy-Value");
		  map.put("Tel", "Tel-Value");
		  map.put("date", "date-Value");
		  map.put("ArrivalDate", "ArrivalDate-Value");
		  map.put("AccStartDate", "AccStartDate-Value");
		  
	      PDPage page = document.getPage(1);
	      InputStream pageContent = page.getContents();
	      Document doc = new Document(pageContent);
	      while (page.getContentStreams().hasNext()) {
			PDStream type = (PDStream) page.getContentStreams().next();
			
			COSStream stream = type.getCOSObject();
			System.err.println(stream.getString());
			
		}
	      
	      
	      
	      
	     /* List<String> documentFields = new DocumentFieldParser().extractTags(doc);
	      map.forEach((k, v) -> {

	            String key = String.format("%s%s%s", "${", k, "}");

	            // Check for fields in map that are not found in document
	            if (!documentFields.contains(key)) {
	                
	                 System.out.println("Merge field not found in document: " + key);
	            }

	             try {
					doc.getRange().replace(key, v, false, false);
				} catch (Exception e) {
					e.printStackTrace();
				}

	        });
	      doc.save(new ByteArrayOutputStream(), SaveFormat.PDF);*/
	      
	      PDPageContentStream contentStream = new PDPageContentStream(document, page,AppendMode.APPEND,true);
	     
	      
	      
	      
	      
	     //Begin the Content stream 
	     contentStream.beginText(); 
	      
	      //----------------------------
	      
	      
	      //----------------------------    
	      
	      
	       
	      //Setting the font to the Content stream  
	      contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

	      //Setting the position for the line 
	      contentStream.newLineAtOffset(25, 500);

	      String text = "This is the sample document and we are adding content to it.";

	      //Adding text in the form of string 
	      contentStream.showText(text);      

	      //Ending the content stream
	      contentStream.endText();

	      System.out.println("Content added");

	      //Closing the content stream
	      contentStream.close();

	      //Saving the document
	      document.save(new File("D:\\tmp\\new.pdf"));

	      //Closing the document
	      document.close();
	}
}
