package apriori;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;

import helper.FileHelper;
import helper.DataHelper;

import valueobjects.LargeItemSetVO;
import valueobjects.AssociationObject;

public class Apriori {
	
	Set<Set<String>> largeItemSet;
	Set<Set<String>> currentItemset;
	String writefilename="";
	Map<Integer,Set<String>> filemap;
	Double support;
	Double confidence;
	
	public Apriori(){
		
		largeItemSet = new HashSet<Set<String>>();
		currentItemset = new HashSet<Set<String>>();
		filemap = new HashMap<Integer,Set<String>>();
		
	}
	
	public void doApriori(Double minsupport,Double minconfidence,String filename,String writename ) throws IOException{
		
		support=minsupport;
		confidence=minconfidence;
		writefilename=writename;
		filemap=FileHelper.parsefile(filename);
		currentItemset=DataHelper.getLoneSet();
		largeItemSet=currentItemset;
		
		while(currentItemset.size() > 0){
			
			
			currentItemset=getNextlevelItemset(currentItemset);
			
		}
		
		for (Set value : largeItemSet) {
			System.out.println(value);
		}
		//DecimalFormat df = new DecimalFormat("##.00");
		//ArrayList<LargeItemSetVO> set = new ArrayList<LargeItemSetVO>(DataHelper.getLargeItemSetWithSupport());
		//for(LargeItemSetVO vo: set){
		//	System.out.println(vo.getItem()+" supp:"+Double.parseDouble(df.format(vo.getSupport())));
		//}
		//System.out.println(largeItemSet);
		//generateAssociationRules(minconfidence);
		//FileHelper.writefile(DataHelper.getLargeItemSetWithSupport(),writefilename,support,confidence);
	}
	
	public void generateAssociationRules(Double minConfidence){
		ArrayList<AssociationObject> associations = new ArrayList<AssociationObject>();
		ArrayList<LargeItemSetVO> set = new ArrayList<LargeItemSetVO>(DataHelper.getLargeItemSetWithSupport());
	
		for(LargeItemSetVO vo: set){
			
			if(vo.getItem().size() == 1){
				String item = vo.getItem().iterator().next();
				for(LargeItemSetVO other:set){
					
					if(other.getItem().contains(item) && other.getItem().size() > 1){
						Set<String> toOutput = new HashSet<String>(other.getItem());
                        toOutput.remove(item);
                        if ((other.getSupport().doubleValue() / DataHelper.getSupport(toOutput).doubleValue()) >= minConfidence) {
                                associations.add(new AssociationObject(toOutput, vo.getItem(), other.getSupport(), (other.getSupport().doubleValue() / DataHelper.getSupport(toOutput).doubleValue())));
                        }
					}
					
				}
				
			}
			
		}
		Collections.sort(associations);
		printAssociations(associations);
		//FileHelper.writefile(associations,largeItemSet,writefilename,support,confidence);
	}
	
	private void printAssociations(ArrayList<AssociationObject> associations) {
		DecimalFormat df = new DecimalFormat("##.00");
		for (AssociationObject obj: associations) {
                System.out.println(obj.getXitem() + " => "+ obj.getYitem() + " (conf:"+Double.parseDouble(df.format(obj.getConfidence()))+ " supp:" + Double.parseDouble(df.format(obj.getSupport()))+ " )");
        }
	}
	
	public Set<Set<String>> getNextlevelItemset(Set<Set<String>> curItemset){
		Set<Set<String>> coneItemset = new HashSet<Set<String>>(curItemset);
		Set<Set<String>> Nextlevel = new HashSet<Set<String>>();
		
		for(Set<String> set : curItemset){	
			for(Set<String> set1 : coneItemset){
				if(!set.equals(set1)){
					Set<String> toadd=new HashSet<String>(set);
					toadd.addAll(set1);
					Nextlevel.add(toadd);
				}
			}	
		}
		//System.out.println(Nextlevel);
		Nextlevel = DataHelper.getFriequentItems(Nextlevel,filemap);
		largeItemSet.addAll(Nextlevel);		
		return Nextlevel;
	}
}
