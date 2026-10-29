package game;

public class World {
		
	public static Room buildWorld() {
		
		Room livingroom = new Room("Living Room");
		Room bedroom  = new Room("Bedroom");
		Room office = new Room("Office");
		Room kitchen = new Room("Kitchen");
		Room garage = new Room("Garage");
		Room attic = new Room("Attic");
		
		livingroom.addExit(kitchen, 0);
		livingroom.addExit(office, 1); 
		livingroom.addExit(bedroom, 2);

		kitchen.addExit(livingroom, 1); 
		kitchen.addExit(garage, 3); 

		office.addExit(livingroom, 0); 

		bedroom.addExit(livingroom, 3); 
		bedroom.addExit(attic, 4); 

		garage.addExit(kitchen, 2); 

		attic.addExit(bedroom, 5); 
		
        
		
		Item key = new Item("key", false); 
		Item tool =  new tool();
		Item musicbox = new Item("musicbox", false);
		Item flashlight = new Item("flashlight", false);
		Item knife = new Item("Knife", false);
		Item mysteriousorb = new Item("MysteriousOrb", false);
		Item couch = new Item("couch" , true);
		
		
		bedroom.addItem(key);
		livingroom.addItem(tool);
		kitchen.addItem(flashlight);
		kitchen.addItem(knife);
		kitchen.addItem(mysteriousorb);
		livingroom.addItem(musicbox);
		livingroom.addItem(couch);
		
		
		office.setLocked(true);
		attic.setLocked(true);
		garage.setLocked(true);
		
		
		
		

		
		
		
		
		
		
		
		
				
				
				
				
				
				
				
				
				
				
		return livingroom;
		
		
	
		
		
		
	
		
	}
	
	public static void main(String[] args) {
		
		Room rooms = buildWorld();
		System.out.println(rooms);
		rooms = rooms.getExit(0); 
		System.out.println(rooms); 

		rooms = rooms.getExit(1); 
		System.out.println(rooms); 

		rooms = rooms.getExit(2); 
		System.out.println(rooms); 

		rooms = rooms.getExit(3); 
		System.out.println(rooms); 

		rooms = rooms.getExit(4); 
		System.out.println(rooms);

		
		
		
		
		
		
		
	
		
		
	}
	
	

}
