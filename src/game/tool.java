package game;

public class tool extends Item {
	private boolean usedTool;
	
	public tool() {
		super ("tool");
		usedTool=false;
		
	}
     public void use() {
    	 if(Game.getCurrentRoom().getName().equals("bedroom")) {
    		 if(usedTool) {
    			 Game.print("you already opened the attic door");
    	 }else {
    		 Game.getCurrentRoom().getExit(4).setLocked(false);
    		 usedTool = true;
    		 Game.print("using the tool, you are able to....");
    	 }
    	 }else {
          Game.print("its no way to use the tool");
    	
    	 }
    		 
    			 	
     }
}
     
		  
	
