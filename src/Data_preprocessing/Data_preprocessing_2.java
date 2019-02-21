package Data_preprocessing;

import java.io.*;
import java.util.*;

public class Data_preprocessing_2 {
	
	//讀取檔案資料以MAP型態儲存
	public Map<String,Set<String>> readfile_map(String filename) throws IOException{
		
		BufferedReader red = new BufferedReader(new FileReader(filename));
		Map<String,Set<String>> filemap = new HashMap<String,Set<String>>();
		Set<String> lineSet;
		String readlineinput="";
		String setkey="";
		
		while((readlineinput = red.readLine())!= null){
			
			lineSet = new HashSet<String>();
			String temp[] = readlineinput.split(",");
			setkey=temp[0];
			
			for(int i=1;i<temp.length;i++){
				lineSet.add(temp[i]);
			}
			
			filemap.put(setkey,lineSet);	
		}
		return filemap;
		
	}
	
	//讀取檔案資料以SET型態儲存
	public Set<String> readfile_set(String filename)throws IOException{

		BufferedReader red = new BufferedReader(new FileReader(filename));
		Set<String> set = new HashSet<String>();
		String readdata = "";

		while((readdata = red.readLine())!= null){ 
			set.add(readdata);
		}

		return set;
	}
	
	//印出出MAP
	public void printMAP(Map<String,Set<String>> map){
		
		for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
		
	}
	
	//印出SET
	public void printSET(Set<String> set){
		
		for (String value : set) {
			System.out.println(value);
        }
		
	}
	
	//把">7"資料合併到指定檔案
	public Map<String,Set<String>> Data_processing_7Value(Map<String,Set<String>> mapA,Map<String,Set<String>> mapB){
		
		Map<String,Set<String>> filemapA = new HashMap<String,Set<String>>();
		Map<String,Set<String>> filemapB = new HashMap<String,Set<String>>();
		filemapA.putAll(mapA);
		filemapB.putAll(mapB);
		Set<String> set;
		
		for (String keyB : filemapB.keySet()) {
	        if(filemapA.containsKey(keyB)){
	          	set=new HashSet<String>();
	           	set.addAll(filemapA.get(keyB));
	           	set.add(">7");
	           	filemapA.remove(keyB);
	           	filemapA.put(keyB,set);
	       }
        }
		return filemapA;
		
	}
	
	//把指定檔案某些資料修改成"250"
	public Map<String,Set<String>> Data_processing_250Value(Map<String,Set<String>> filemap,Set<String> set){
	
		Map<String,Set<String>> map = new HashMap<String,Set<String>>();
		Map<String,Set<String>> NEWmap = new HashMap<String,Set<String>>();
		
		map.putAll(filemap);
		Set<String> value;
		int count = 0;

		for(String key : map.keySet()){
			//System.out.println(key + " : " + map.get(key));
			value = new HashSet<String>();
			value.addAll(map.get(key));
			
			for(String setv : map.get(key)){
				if(set.contains(setv)){
					count++;
					value.remove(setv);
				}
			}
			//System.out.println(count);
			if(count>0){
				//value.add("250");
				value.remove("");
				//printSET(value);
				//map.remove(key);
				NEWmap.put(key,value);
			}else{
				//value.add("250");
				value.remove("");
				//printSET(value);
				//map.remove(key);
				NEWmap.put(key,value);
			}
			count=0;
			//value.clear();
		}
		
		return NEWmap;
	}
	
	//將整理完DATA寫入到檔案
	public void Writerfile(Map<String,Set<String>>Sorting_map,String filename){
		try{
			BufferedWriter wr=new BufferedWriter(new FileWriter(filename));
			StringBuffer setvalue = new StringBuffer();
			for (Object key : Sorting_map.keySet()) {
				for(String value : Sorting_map.get(key)){setvalue.append(value+",");}
	            wr.write(key + "," + setvalue.toString() );
	            wr.newLine();
	            setvalue.delete(0,setvalue.length());
	        }
			wr.flush();  
			wr.close();
			System.out.println("黨案匯出成功");
		}
		catch(Exception ex)
		{
			System.out.println("黨案匯出失敗");
		}	  
	}
	
}
