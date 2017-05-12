package com.bk.pdf;

import java.util.ArrayList;
import java.util.List;


import com.aspose.words.Document;

/**
 * The Class DocumentFieldParser.
 */
public class DocumentFieldParser {

	private static final int FIELD_END_INDEX = 1;
	private static final int FIELD_START_INDEX = 2;
	private static String prefix = "${";
	private static String suffix = "}";

	/**
	 * Extract tags.
	 *
	 * @param document
	 *            the document
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	public static List<String> extractTags(Document document) throws Exception {
		final List<String> fields = new ArrayList<String>(0);

		final String documentContents = document.getRange().getText();

		int idx = 0;

		while ((idx = documentContents.indexOf(prefix, idx)) != -1) {
			int endIdx = documentContents.indexOf(suffix, idx);
			// suffix not found after prefix
			if (endIdx == -1) {
				System.out.println("Unexpectedly reached end of document while searching for field suffix");

				break;
			}

			//fieldTag example : ${username}
			final String fieldTag = documentContents.substring(idx, endIdx + 1);
			
			//field example : username
			final String field = fieldTag.substring(FIELD_START_INDEX, fieldTag.length() - FIELD_END_INDEX);
			
			// prevent duplicate entries
			if (!fields.contains(field)) {
				fields.add(field);
			}

			// search for next field from the end of current field
			idx = endIdx;
		}

		return fields;
	}
	
}
