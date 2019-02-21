package helper;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import valueobjects.AssociationObject;
import valueobjects.LargeItemSetVO;

public class FileHelper {
	
	public static Map<Integer,Set<String>> parsefile(String filename) throws IOException{
		
		Map<Integer,Set<String>> filemap = new HashMap<Integer,Set<String>>();
		BufferedReader red = new BufferedReader(new FileReader(filename));
		String readlineinput="";
		Integer readercount=0;
		Set<String> lineSet;
		
		while((readlineinput = red.readLine())!= null){
				++readercount;
				lineSet = new HashSet<String>();
				String temp[] = readlineinput.split(",");
				
				for( int i=0;i<temp.length;i++){
					lineSet.add(temp[i]);
					DataHelper.UpdatefrequencyMap(temp[i]);
				}
				filemap.put(readercount,lineSet);
				
		}
		DataHelper.setNumberofTransaction(readercount);
		return  filemap;
	}
	public static void writefile(Set<LargeItemSetVO> largeItemsetsupport,String filename,Double minsupport,Double minconfidence) {
		try{
			DecimalFormat df = new DecimalFormat("##.00");
			BufferedWriter wr=new BufferedWriter(new FileWriter(filename));
			wr.write("minsupport:"+minsupport);
			wr.newLine();
			wr.write("minconfidence:"+minconfidence);
            wr.newLine();
            wr.write("largeItemSet-------------------");
            wr.newLine();

			for (LargeItemSetVO vo: largeItemsetsupport) {
				wr.write(vo.getItem()+" supp:"+Double.parseDouble(df.format(vo.getSupport())));
	            wr.newLine();
			} 
			
			wr.flush();  
			wr.close();
			System.out.println("成功");
		}
		catch(Exception ex)
		{
			System.out.println("失敗");
		}
	}
	public static void writefile(ArrayList<AssociationObject> associations,Set<Set<String>> largeItemSet,String filename,Double minsupport,Double minconfidence) {
		try{
			DecimalFormat df = new DecimalFormat("##.00");
			BufferedWriter wr=new BufferedWriter(new FileWriter(filename));
			wr.write("minsupport:"+minsupport);
			wr.newLine();
			wr.write("minconfidence:"+minconfidence);
            wr.newLine();
            wr.write("largeItemSet-------------------");
            wr.newLine();
			for (Set value : largeItemSet) {
				wr.write(value.toString());
	            wr.newLine();
			}
			wr.write("AssociationRules---------------");
            wr.newLine();
			for (AssociationObject obj: associations) {
				wr.write(obj.getXitem() + " => "+ obj.getYitem() + " (conf:"+Double.parseDouble(df.format(obj.getConfidence()))+ " supp:" + Double.parseDouble(df.format(obj.getConfidence()))+ " )");
	            wr.newLine();
			}
			wr.flush();  
			wr.close();
			System.out.println("成功");
		}
		catch(Exception ex)
		{
			System.out.println("失敗");
		}
	}
}
