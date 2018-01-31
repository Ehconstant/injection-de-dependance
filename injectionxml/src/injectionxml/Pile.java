package injectionxml;


import java.util.LinkedList;
import java.util.List;



public class Pile<TYPE>{
	private List<TYPE> lst;

	 public Pile(List<TYPE> l)
	 {
		 this.lst = l;
	 }
	
	 public void empiler(TYPE t)
	 {
		 this.lst.add(t);
	 }
	 
	 public TYPE depiler()
	 {
		 TYPE val;
		 int len = (this.lst).size() - 1;
		 if(len < 0) throw new IndexOutOfBoundsException();
		 else {
			 val = lst.get(len);
			 this.lst.remove(len);
		 }
		 
		return val;
		 
	 }
}
