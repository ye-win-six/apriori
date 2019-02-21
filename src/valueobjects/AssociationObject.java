package valueobjects;

import java.util.Set;

public class AssociationObject implements Comparable<AssociationObject> {
	private Set<String> Xitem;
	private Set<String> Yitem;
	private Double support;
	private Double confidence;
	
	public AssociationObject(Set<String> Xitem,Set<String> Yitem,Double support,Double confidence){
		
		this.setXitem(Xitem);
		this.setYitem(Yitem);
		this.setSupport(support);
		this.confidence = confidence;
		
	}
	
	public void setXitem(Set<String> Xitem){
		this.Xitem = Xitem;
	}
	
	public Set<String> getXitem(){
		return Xitem;
	}
	
	public void setYitem(Set<String> Yitem){
		this.Yitem = Yitem;
	}
	
	public Set<String> getYitem(){
		return Yitem;
	}
	public void setSupport(Double support) {
        this.support = support;
	}

	public Double getSupport() {
        return support;
	}

	public Double getConfidence() {
        return confidence;
	}
	
	 @Override
     public int compareTo(AssociationObject o) {
             if (this.confidence < o.confidence)
                     return 1;
             return 0;
     }
}
