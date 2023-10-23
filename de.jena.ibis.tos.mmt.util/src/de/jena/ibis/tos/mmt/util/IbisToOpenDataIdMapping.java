/**
 * Copyright (c) 2012 - 2023 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package de.jena.ibis.tos.mmt.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.util.promise.PromiseFactory;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

/**
 * 
 * @author ilenia
 * @since Oct 23, 2023
 */
@Component(immediate=true, service=IbisToOpenDataIdMapping.class, name="IbisToOpenDataIdMapping")
public class IbisToOpenDataIdMapping {
	
	private final static Logger LOGGER = Logger.getLogger(IbisToOpenDataIdMapping.class.getName());
	private final static String STOP_ID_MAPPING_FILE = "data/StopMapping_IbisID_OpenDataID.csv";
	private final static char CSV_SEPARATOR = ',';
	
	private PromiseFactory promiseFactory = new PromiseFactory(Executors.newCachedThreadPool());
	private Map<String, String> stopIdMap = new HashMap<>();
	
	
	@Activate
	public void activate() {
		promiseFactory
		.submit(() -> {
			try(InputStream is = new FileInputStream(STOP_ID_MAPPING_FILE)){
				List<String[]> csvData = readFromCSV(is, CSV_SEPARATOR, 0);
				if(!csvData.isEmpty()) {
					processCSVData(csvData);
				}
				else {
					LOGGER.warning(String.format("No CSV Data loaded from file %s", STOP_ID_MAPPING_FILE));
					return false;
				}
			}
			return true;
		});
	}
	
	public String getOpenDataID(String ibisID) {
		String openDataId = null;
		for(String iid : stopIdMap.keySet()) {
			if(iid.equals(ibisID)) {
				openDataId = stopIdMap.get(iid);
				break;
			}
			String iidNumeric = iid.replaceAll("[^\\d]", "");
			if(iidNumeric.equals(ibisID)) {
				openDataId = stopIdMap.get(iid);
				break;
			}
		}
		if(openDataId == null) LOGGER.warning(String.format("No OpenData ID equivalent found for Ibis Stop ID %s", ibisID));
		return openDataId;
	}
	
	private void processCSVData(List<String[]> csvData) {
		csvData.forEach(row -> {
			stopIdMap.put(row[0], row[1]);
		});		
	}

	private static List<String[]> readFromCSV(InputStream is, char separator, int linesToSkip) throws IOException {

		List<String[]> allData = new ArrayList<String[]>();
		try {
			InputStreamReader reader = new InputStreamReader(is, Charset.forName("UTF-8"));
			CSVParser parser = new CSVParserBuilder().withSeparator(separator).build(); 
			CSVReader csvReader = new CSVReaderBuilder(reader)
					.withCSVParser(parser)
					.withSkipLines(linesToSkip)
					.build(); 
			allData = csvReader.readAll(); 
		} catch (CsvException e) {
			LOGGER.log(Level.SEVERE, String.format("Something went wrong when parsing CSV file"), e);
		}		
		return allData;
	}

}
