package game;
import java.io.Serializable;


public class Item implements Serializable {

		private static final long serialVersionUID = 1L;
	private String name;
	public Item ( String n) {
		name = n;		
	}
	private boolean heavy;
	
	public String getName() {
		return name;
	
}
	public void setName(String n) {
		name = n;
	}
	public String toString() {
		return name;
	}


public Item(String n, boolean h) {
	name = n;
	heavy = h;
}

public boolean isHeavy() {
	return heavy;
}
 public void use() {
	 Game.print("You cant use this item unt unt");
 }
 
 
 
  }
 
 
 
 
 
 
 
 
 
 
 
 
 

