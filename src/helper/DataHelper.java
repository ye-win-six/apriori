package helper;

import java.util.*;
import valueobjects.LargeItemSetVO;

public class DataHelper {
	
	static Map<String,Integer> frequencyMap;
	static Integer numberofTransaction;
	static Double minsupport;
	static Double minconfidence;
	static Set<LargeItemSetVO> largeItemsetsupport;
	
	public static void init(Double sup , Double cof){
		frequencyMap = new HashMap<String,Integer>();
		largeItemsetsupport=new HashSet<LargeItemSetVO>();
		minsupport = sup;
		minconfidence = cof;
	}
	
	public static Set<Set<String>> getLoneSet(){
		Set<Set<String>> Loneset=new HashSet<Set<String>>();
		Set<String> item=null;
	
		for(String s : frequencyMap.keySet()){
			Double support = frequencyMap.get(s).doubleValue()/ numberofTransaction.doubleValue();
			if(support >= minsupport){
				item=new HashSet<String>();
				item.add(s);
				Loneset.add(item);
				largeItemsetsupport.add(new LargeItemSetVO(item, support));
			}
		}
		return Loneset;
	}
	
	public static void UpdatefrequencyMap(String word ){
		Integer count = frequencyMap.get(word);
		
		if (count == null) {
			
            frequencyMap.put(word, 1);
		
		}
		else {
			
            frequencyMap.put(word, count+1);
            
		}
	}
	
	public static Set<Set<String>> getFriequentItems(Set<Set<String>> allcurrentItemset,Map<Integer,Set<String>> filemap){
		Set<Set<String>> friequentSet = new HashSet<Set<String>>();
		
		for(Set<String> set : allcurrentItemset){
			if(checksetIsfriequent(filemap,set)){
				friequentSet.add(set);
			}
		}
		return friequentSet;
	}
	
	public static boolean checksetIsfriequent(Map<Integer,Set<String>> filemap,Set<String> set){
		Integer count = 0;
		for(Integer key : filemap.keySet()){
			Set<String> row=filemap.get(key);
			if(row.containsAll(set))
				++count;
		}
		if(count.doubleValue()/numberofTransaction.doubleValue() >= minsupport){
			largeItemsetsupport.add(new LargeItemSetVO(set, count.doubleValue() / numberofTransaction.doubleValue()));
			return true;
		}	
		return false;
	}
	
	public static Double getSupport(Set<String> s) {
        for (LargeItemSetVO vo : largeItemsetsupport) {
                if (vo.getItem().equals(s)) {
                        return vo.getSupport();
                }
        }
        return null;
	}
	
	public static void setNumberofTransaction(Integer numtransaction){
		numberofTransaction = numtransaction;
	}
	
	public static Set<LargeItemSetVO> getLargeItemSetWithSupport() {
        return largeItemsetsupport;
	}
	
	public static Map<String,Integer> getfrequencyMap() {
        return frequencyMap;
	}
}
