package com.example.junior;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class History {
	private String previousRequests;

	public String getPreviousRequests() {
		return previousRequests;
	}

	public void setPreviousRequests(String previousRequests) {
		this.previousRequests = previousRequests;
	}
	
	public String readFromHistoryFile() {
		String content = "";
		try {
			File file = new File("./history/log.txt");
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				content += data + "\n";
			}
			myReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File's not found.");
		}
		return content;
	}
}
