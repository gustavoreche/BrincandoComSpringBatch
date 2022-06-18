package com.br.springbatch.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class CSVUtil {
	
	private String file;
	
	public CSVUtil(String file) {
		this.file = file;
	}

	public List<String[]> readLines() {
		List<String[]> records = new ArrayList<String[]>();
		URL resource = getClass().getClassLoader().getResource(this.file);
		try (CSVReader csvReader = new CSVReader(new FileReader(new File(resource.toURI())))) {
		    String[] values = null;
		    while ((values = csvReader.readNext()) != null) {
		        records.add(values);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return records;
    }

}
