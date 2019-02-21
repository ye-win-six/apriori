package valueobjects;

import java.util.Set;

public  class LargeItemSetVO implements Comparable<LargeItemSetVO>{
	private Set<String> items;
	private Double support;
	
	
	
	public LargeItemSetVO(Set<String> items,double support){
		this.items = items;
		this.support = support;
	}
	
	public void setItems(Set<String> items){
		this.items=items;	
	}
	
	public Set<String> getItem(){
		return items;
	}
	
	public void setSupport(double support){
		this.support = support;	
	}
	
	public Double setSupport(){
		return support;
	}
	
	public Double getSupport() {
        return support;
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		 int result = 1;
         result = prime * result + ((items == null) ? 0 : items.hashCode());
         result = prime * result + ((support == null) ? 0 : support.hashCode());
         return result;
	}
	
	@Override
	public boolean equals(Object obj){
		 if (this == obj)
             return true;
		 if (obj == null)
             return false;
		 if (getClass() != obj.getClass())
             return false;
		 LargeItemSetVO other = (LargeItemSetVO) obj;
		 if (items == null) {
             if (other.items != null)
                     return false;
		 }
		 else if (!items.equals(other.items))
             return false;
		 if (support == null) {
             if (other.support != null)
                     return false;
		 } else if (!support.equals(other.support))
             return false;
		 return true;
	}
	
	 @Override
     public int compareTo(LargeItemSetVO other) {
		 
             if (this.support < other.support)
                     return 1;
             
             return 0;
     }
	 
}
