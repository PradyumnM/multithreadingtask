package com.pra.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
	
	public static List<String> getCSVData(String filename) throws IOException {
		List<String> csvList = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line1="";
			while ((line1 = br.readLine()) != null)
				csvList.add(line1);
			br.close();
		return csvList;
	}

}
