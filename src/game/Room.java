package game;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

	
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static ArrayList<Room> allRooms = new ArrayList<>();
	
	private String name;
	private Room[] rooms;
	private HashMap<String, Item> items;
	
	
	public Room(String n) {
		name = n;
		rooms = new Room[6];	
		items = new HashMap<>();
		allRooms.add(this);
	
	}
	
	public String getName(){
		return name;
	}
	public String getDescription(){
		return Game.rooms.get(name);
	}

	public void setName(String newName) {
		name = newName;
    }
	public void setDescription(String newDescription) {
		Game.rooms.put(name,  newDescription);
    }
	 
	
	public String toString() {
	    String description = Game.rooms.get(name);

	    if (description == null || description.isEmpty()){
	        return "This room is " + name
	             + ".\n\nNo description found!";
	    }

	   
	

	    return "This room is " + name + ".\n\n" + description;
	}
   public void addExit(Room r, int i) {
	   rooms[i]=r;
   }
	   
   public Room getExit(int i) {
	   return rooms[i];
   }
   public Item getItem(String itemName) {
	   if (items.containsKey(itemName)) {
		   return items.get(itemName);
	   }
	   
	   return null;
   }
   public void addItem(Item item) {
	   items.put(item.getName(), item);
   }
   
   public void removeItem(String itemName) {
	   if (items.containsKey(itemName)) {
		   items.remove(itemName);
	   }
   }

   
   
   private boolean locked; 
	
	public boolean getLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
 }

