package Data_preprocessing;

import java.io.*;
import java.util.*;

import helper.DataHelper;

public class DataProcessing {
	Map<String,Set<String>> filemap;
	Map<String,String> filemap2;
	Map<String,Set<String>> Sorting_map;
	Map<String,String> Sorting_map2;
	String readlineinput="";
	String Setkey="";
	Set<String> lineSet;
	Set<String> mapvalue;
	String Table[][];
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		DataProcessing file=new DataProcessing();
		file.parsefile2("diabetic_data1.csv");	
	}
	
	public void parsefile(String filename) throws IOException{
		
		BufferedReader red = new BufferedReader(new FileReader(filename));
		filemap = new HashMap<String,Set<String>>();
		Sorting_map = new TreeMap<String,Set<String>>();
		int conut=0;
		
		while((readlineinput = red.readLine())!= null){
			
			lineSet = new HashSet<String>();
			String temp[] = readlineinput.split(",");
			Setkey=temp[0];
			//for(int i=0;i<temp.length;i++){System.out.println(temp[i]);}
			//System.out.println("");
			
			if(filemap.containsKey(Setkey)){
				
				mapvalue = new HashSet<String>();
				mapvalue = filemap.get(Setkey);
				
				for(int i=1;i<temp.length;i++){
					for(String value : mapvalue){
						if(temp[i].equals(value)){conut++;}
					}
					if(conut==0){
						conut=0;
						mapvalue.add(temp[i]);
					}
					conut=0;	
				}
				filemap.remove(Setkey);
				filemap.put(Setkey,mapvalue);
			}
			else
			{
				for(int i=1;i<temp.length;i++){lineSet.add(temp[i]);}	
				filemap.put(Setkey,lineSet);	
			}
		}
		Sorting_map.putAll(filemap);
		printMAP();
		Writerfile();
	}
	
	public void parsefile2(String filename) throws IOException{
		
		BufferedReader red2 = new BufferedReader(new FileReader(filename));
		filemap2 = new HashMap<String,String>();
		//Sorting_map2 = new TreeMap<String,String>();
		StringBuffer str=new StringBuffer();
		int count=0;
		
		while((readlineinput = red2.readLine())!= null){
			
			String temp[] = readlineinput.split(",");
			Setkey=temp[0];
			
			//for(int i=0;i<temp.length;i++){System.out.println(temp[i]);}
			//System.out.println("");
			
			if(!filemap2.containsKey(Setkey)){
				
				//for(int i=1;i<temp.length;i++){
				//	if(temp[i].equals("?"))
				//	{
						
				//	}
						
				//}
				//filemap.remove(Setkey);
				//filemap.put(Setkey,mapvalue);
				for(int i=1;i<temp.length;i++)
				{
					if(temp[i].equals("?"))
					{
						count++;
					}
					str.append(","+temp[i]);
				}
				//temp[1]+","+temp[2]+","+temp[3];
				if(count==0){
					
					filemap2.put(Setkey,str.toString());
				}
				count=0;	
			}
			str.delete(0,str.length());
		}
		Sorting_map2 = new TreeMap<String,String>(new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                // ­°§Ç±Æ§Ç
                return obj2.compareTo(obj1);
            }
        });
		Sorting_map2.putAll(filemap2);
		printMAP2();
		Writerfile2();
	}
	
	public void printMAP(){
		
		for (Object key : Sorting_map.keySet()) {
            System.out.println(key + " : " + Sorting_map.get(key));
        }
		
	}
	public void printMAP2(){
		
		for (String key : Sorting_map2.keySet()) {
            System.out.println( key+Sorting_map2.get(key));
        }
		
	}
	public void Writerfile(){
		try{
			BufferedWriter wr=new BufferedWriter(new FileWriter("Data_preprocessing1-1.csv"));
			StringBuffer setvalue = new StringBuffer();
			for (Object key : Sorting_map.keySet()) {
				for(String value : Sorting_map.get(key)){setvalue.append(value+",");}
	            wr.write(key + "," + setvalue.toString() );
	            wr.newLine();
	            setvalue.delete(0,setvalue.length());
	        }
			wr.flush();  
			wr.close();
			System.out.println("ÄÒ®×¶×¥X¦¨¥");
		}
		catch(Exception ex)
		{
			System.out.println("ÄÒ®×¶×¥X¥¢±Ñ");
		}	  
	}
	public void Writerfile2(){
		try{
			BufferedWriter wr=new BufferedWriter(new FileWriter("Data_preprocessing1-1.csv"));
			//StringBuffer setvalue = new StringBuffer();
			
			for (String key : Sorting_map2.keySet()) {
	            wr.write(key  + Sorting_map2.get(key) );
	            wr.newLine();
	            //setvalue.delete(0,setvalue.length());
	        }
			wr.flush();  
			wr.close();
			System.out.println("ÄÒ®×¶×¥X¦¨¥");
		}
		catch(Exception ex)
		{
			System.out.println("ÄÒ®×¶×¥X¥¢±Ñ");
		}	  
	}
	

}
