package game;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Game {
	static ArrayList<Item> items = new ArrayList<Item>();
	
	
	public static HashMap<String,String> rooms = new HashMap<>();
	static GameGui gui;


public static void RoomDescriptions() {
    rooms.clear();

    try (Scanner fileScan = new Scanner(new File("room_descriptions"))) {
        String roomName = "";
        StringBuilder description = new StringBuilder();

        while (fileScan.hasNextLine()) {
            String line = fileScan.nextLine();

            if (line.trim().equals("#")) {
            	
                if (!roomName.isEmpty()) {
                    rooms.put(roomName.trim(), description.toString().trim());
                }

                roomName = "";
                description.setLength(0);

            } else if (roomName.isEmpty()) {
                if (!line.trim().isEmpty()) {
                    roomName = line.trim();
                }
            } else {
                description.append(line).append("\n");
            }
        }

        System.out.println("Loaded rooms: " + rooms.keySet());   
        System.out.println("Kitchen description: " + rooms.get("Kitchen"));

    } catch (FileNotFoundException ex) {
        System.out.println("Could not find room_descriptions.txt");
        ex.printStackTrace();
    }
}

	

	
	public static void takeItem(String nameItem) {
		Item item = currentroom.getItem(nameItem);
		
		if (item == null) {
			Game.print("there is no item");
		}else if(item.isHeavy()) {
			System.out.println( " The "  +  item.getName() + " is too heavy");
		}else if (item.getName().equals(nameItem)){
			currentroom.removeItem(nameItem);
			items.add(item);
			Game.print("You can take the "  + item.getName() + " with you");
		} else {
			Game.print("The item name does not match.");
		}

	}
	private static Room currentRoom;
	  public static Room getCurrentRoom() {
		  return currentRoom;
	  }
	  
	
	public static void print(Object obj) {
	    if (gui != null) {
	        gui.displayMessage(String.valueOf(obj));
	    } else {
	        System.out.println(obj);
	    }
	}
	
	private static void  move(String directions) {
		int index = -1;
		
		switch(directions) {
		case "north":
			index = 2;
			break;
		case "east":
			index = 0;
			break;
		case "south":
			index = 3;
			break;
		case "west":
			index = 1;
			break;
		case "up":
			index = 4;
			break;
		case "down":
			index = 5;
			break;
		default: 
			Game.print("Wrong Direction");
			return;
		}
	
		
		Room newRoom = currentroom.getExit(index);
		if (newRoom == null) {
			Game.print("You can't go there.");
		}else if(newRoom.getLocked()) {
			Game.print("its locked boo");
		} else {
			currentroom = newRoom;
		}
		
	}

	
	private static Room currentroom;

public static void main(String[] args) {
    RoomDescriptions();
    currentroom = World.buildWorld();

    javax.swing.SwingUtilities.invokeLater(() -> {
        gui = new GameGui();

        gui.setCommandHandler(command -> {
            runGameGUI(command);
        });

        print("♡ Welcome to My Magical Adventure! ♡");
        print(currentroom);
    });
}

		
	
	public static void runGame() {
		Scanner input = new Scanner(System.in);
		String n = "";
		
	    while (!n.equals("exit"))	{
	    	Game.print(currentroom);
	    	n = input.nextLine().toLowerCase();
	    	String[] commands = n.split(" ");
	    	String command = commands[0];    	
	    	
	    	switch (command) {
	    	case "north":
	    	case "east":
	    	case "south":
	    	case "west":
	    	case "up":
	    	case "down" :
	    		move(n);
	    		break;	
	    	case "go":
	    		if(commands.length> 1) {
	    			move(commands[1]);
	    		}else {
	    			Game.print("Go where?");
	    		}
	    		break;
	    	case "take":
	    		String itemName = commands[1];
				takeItem(itemName);
				break;	
			case "i":
				for ( Item item : items) {
					Game.print(item.getName());
				}
				break; 
			case "exit" :
				Game.print("Goodbye");
				break;
	    	default: 
	    		Game.print("Error ");
	    	
	    		
	    	}
	    }
	    
	    input.close();
	}
	

public static void runGameGUI(String input) {
    if (input == null || input.trim().isEmpty()) {
        return;
    }

    String[] commands = input.toLowerCase().trim().split("\\s+");
    String command = commands[0];

    switch (command) {
        case "north":
        case "east":
        case "south":
        case "west":
        case "up":
        case "down":
            move(command);
            print(currentroom);
            break;

        case "go":
            if (commands.length > 1) {
                move(commands[1]);
                print(currentroom);
            } else {
                print("Go where?");
            }
            break;

        case "look":
            print(currentroom);
            break;

        case "take":
            if (commands.length > 1) {
                String itemName = input.trim()
                    .substring(input.trim().indexOf(' ') + 1);
                takeItem(itemName);
            } else {
                print("Take what?");
            }
            break;
        case "use":
        	if(commands.length > 1) {
        		String itemName = input.trim().substring(
        				input.trim().indexOf(' ') + 1);
        		boolean found = false;
        		
        		
        		for (Item item : items) {
        			if(item.getName().equalsIgnoreCase(itemName)) {
        				item.use();
        				found = true;
        				break;
        			}
        		}  
        		
        		           if(!found) {
        		        	   Game.print("you dont have that");
        		           }
        	}else {
        		Game.print("use whatt?");
        	}
        	break ;
        		        	   		
        		        	 
       
    
        		
        	
        case "i":
            print("♡ Your Inventory ♡");
            if (items.isEmpty()) {
                print("Your inventory is empty.");
            } else {
                for (Item item : items) {
                    print(item.getName());
                }
            }
            break;

        case "help":
            print("♡ Available commands ♡");
            print("look, north, south, east, west, up, down");
            print("go north, take item, i, exit");
            break;

        case "exit":
            print("Goodbye, magical adventurer! ♡");
            break;

        default:
            print("I don't know what you mean there babes...");
    }
}

	public static void saveGame() {
		try {
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream("save0.sav"));
			new FileOutputStream("save0.sav");
			out.writeObject(currentroom);
			out.writeObject(items);
			out.writeObject(Room.allRooms);
			out.writeObject(rooms);
			
			out.close();
			
			print("Game saved");
		}catch(IOException e) {
			print("error "  + e.getMessage());
			
		}
	     }
	
	public static void loadGame() {
		try {
			ObjectInputStream in = new ObjectInputStream( new FileInputStream("save0.sav"));
			currentroom = ( Room) in.readObject();
			items = (ArrayList<Item>) in.readObject();
			Room.allRooms=(ArrayList<Room>) in.readObject();
			rooms = (HashMap<String, String>) in.readObject();
			
			
			in.close();
			
			print("Game loaded successfully");
			print(currentroom);
		}catch (IOException | ClassNotFoundException e) {
			print("error " + e.getMessage())
			;
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
	

}  
	
	
	
}

