package com.example.junior;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Form {
	private String contentTextInput;
	private String contentFileInput;
	private Map<Character, Result> results = new HashMap<Character, Result>();
	private String filePath;

	/* Formats textarea data from hasmap in a form of how it was asked in the spec */
	public String getResultsTextInput() {
		if (contentTextInput.isEmpty()) {
			return "";
		} else {
			computeResults(contentTextInput);
			TreeMap<Character, Result> sortedHM = new TreeMap<Character, Result>();
			sortedHM.putAll(results);
			String formattedResult = "";
			for (Map.Entry<Character, Result> entry : sortedHM.entrySet()) {
				formattedResult += entry.getKey() + " " + entry.getValue().getNumberOfWords() + " "
						+ entry.getValue().getWords() + '\n';
			}
			writePreviousRequest(contentTextInput, formattedResult, "textarea");
			return formattedResult;
		}

	}
	/* Formats file data from hasmap in a form of how it was asked in the spec
	 * Redundant to previous method, cannot think of how to combine them */
	public String getResultsFileInput() {
		results.clear();
		if (contentFileInput == null) {
			return "";
		} else {
			computeResults(contentFileInput);
			TreeMap<Character, Result> sortedHM = new TreeMap<Character, Result>();
			sortedHM.putAll(results);
			String formattedResult = "";
			for (Map.Entry<Character, Result> entry : sortedHM.entrySet()) {
				formattedResult += entry.getKey() + " " + entry.getValue().getNumberOfWords() + " "
						+ entry.getValue().getWords() + '\n';
			}
			writePreviousRequest(contentFileInput, formattedResult, "text file");
			return formattedResult;
		}
	}

	public String getContentTextInput() {
		return contentTextInput;
	}

	public void setContentTextInput(String contentTextInput) {
		this.contentTextInput = contentTextInput;
	}

	public String getContentFileInput() {
		readFromFile(filePath);
		return contentFileInput;
	}

	public void setContentFileInput(String contentFileInput) {
		this.contentFileInput = contentFileInput;
	}

	/* Arranges data to hashmap */
	private void computeResults(String content) {
		String[] trimmed = content.trim().split(" ");
		for (String singleWord : trimmed) {
			if (isOnlyLatin(singleWord)) {
				if (results.get(getLastCharacter(singleWord)) != null) {
					results.get(getLastCharacter(singleWord)).populateWords(singleWord);
				} else {
					results.put(getLastCharacter(singleWord), new Result(singleWord));
				}
			}
		}
	}

	/* Checks if the word contains any non-latin characters with RegEx */
	private Boolean isOnlyLatin(String splittedContent) {
		return splittedContent.matches("^[a-zA-Z]*$");
	}

	private Character getLastCharacter(String splittedContent) {
		return splittedContent.toLowerCase().charAt(splittedContent.length() - 1);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	private void readFromFile(String filePath) {
		String content = "";
		try {
			File file = new File(filePath);
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				content += data + " ";
			}
			myReader.close();
			setContentFileInput(content);
		} catch (FileNotFoundException e) {
		}
	}

	/* Writes data to log.txt file to perform history tracking */
	private void writePreviousRequest(String content, String result, String fileOrInput) {
		File log = new File("./history/log.txt");
		try {
			if (log.exists() == false) {
				log.createNewFile();
			}
			PrintWriter out = new PrintWriter(new FileWriter(log, true));
			out.append("Requested text from " + fileOrInput + " : " + content + "\n" + "Result: " + result + "\n\n");
			out.close();
		} catch (IOException e) {
			System.out.println("Failed to write request history");
		}
	}

}
