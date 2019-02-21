package Data_preprocessing;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Data_preprocessing_2 DP = new Data_preprocessing_2();
		
		Map<String,Set<String>> filemap = new HashMap<String,Set<String>>();
		Map<String,Set<String>> filemapB = new HashMap<String,Set<String>>();
		Set<String> set_250 = new HashSet<String>();
		
		filemap.putAll(DP.readfile_map("Data_preprocessing2.csv"));
		set_250.addAll(DP.readfile_set("fileset.csv"));
		
		filemapB.putAll(DP.Data_processing_250Value(filemap, set_250));
		//DP.printSET(set_250);		
		//DP.printMAP(filemapB);
		DP.Writerfile(filemapB,"Data_preprocessing5.csv");
	}

}
