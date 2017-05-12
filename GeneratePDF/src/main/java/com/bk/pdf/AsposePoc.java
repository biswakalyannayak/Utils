package com.bk.pdf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.aspose.words.Cell;
import com.aspose.words.Document;
import com.aspose.words.NodeType;
import com.aspose.words.Row;
import com.aspose.words.Table;

public class AsposePoc {

	public static void main(String[] args) throws Exception {
		 Map<String, String> map = new HashMap<String, String>();
		  map.put("ReferredBy", "ReferredBy-Value");
		  map.put("Tel", "Tel-Value");
		  map.put("date", "date-Value");
		  map.put("ArrivalDate", "ArrivalDate-Value");
		  map.put("AccStartDate", "AccStartDate-Value");
		  map.put("SpecialNeed", "SpecialNeed-Value");
		  map.put("DependentCount", "DependentCount-Value");
		  
		
		
		String filename = "SCF-Pre-Template.doc";
		String path = "D:\\tmp\\";
		
		Document doc = new Document(path + filename);
		
		// Retrieve the first table in the document.
		Table table = (Table) doc.getChild(NodeType.TABLE, 7, true);
		
		
		Row row1 = (Row) table.getChild(NodeType.ROW, 0, false);
		
		//Separator
		/*Row row3 = (Row) table.getChild(NodeType.ROW, 3, true);
		System.out.println(row3.getText());*/
		
		
		Row row2 = (Row) table.getChild(NodeType.ROW, 1, false);
		System.out.println(row2.getText());
		System.out.println("----------------------------------------");
		Row row3 = (Row) table.getChild(NodeType.ROW, 2, false);
		System.out.println(row3.getText());
		System.out.println("----------------------------------------");
		
		for (int i = 0; i < 5; i++) {
			Row rowToUpdate1 = (Row) row2.deepClone(true);
			Row rowToUpdate2 = (Row) row3.deepClone(true);
			//Manipulate
			Cell cell1 = (Cell) rowToUpdate1.getChild(NodeType.CELL, 1, true);
			System.out.println(cell1.getText());
			cell1.getRange().replace(Pattern.compile("\\{ForenameV\\}"), "Replaced-ForenameV"+i);
			System.out.println(cell1.getText());
		
			
			Cell cell2 = (Cell) rowToUpdate1.getChild(NodeType.CELL, 5, true);
			System.out.println(cell2.getText());
			cell2.getRange().replace(Pattern.compile("\\{RelationV}"), "Replaced-RelationV"+i);
			System.out.println(cell2.getText());
			
			/*Cell cell3 = (Cell) rowToUpdate.getChild(NodeType.CELL, 8, true);
			System.out.println(cell3.getText());
			cell3.getRange().replace(Pattern.compile("\\{RelationV}"), "Replaced-RelationV");
			System.out.println(cell3.getText());*/
			
			System.out.println(rowToUpdate2.getText());
			rowToUpdate2.getRange().replace(Pattern.compile("\\{SurnameV\\}"), "Replaced-SurnameV"+i);
			System.out.println(rowToUpdate2.getText());
			
			row1.getParentNode().insertAfter(rowToUpdate1, row1);
			rowToUpdate1.getParentNode().insertAfter(rowToUpdate2, rowToUpdate1);
			//row1.getParentNode().insertAfter(row3, rowToUpdate);
		}
		
		row2.remove();
		row3.remove();
		
		 List<String> documentFields = new DocumentFieldParser().extractTags(doc);
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
	      
		doc.save(path + "SCF-Generated.doc");
		doc.save(path + "SCF-Generated.pdf");
		

		// Try to replace the evaluation.
		/*Document doc2 = new Document(path + "Table_CloneTableAndInsert_Out.doc");
		doc2.getRange().replace("Evaluation Only. Created with Aspose.Words. Copyright 2003-2014 Aspose Pty Ltd.", "", false, false);
		doc2.save(new ByteArrayOutputStream(), SaveFormat.PDF);*/
	}

}
