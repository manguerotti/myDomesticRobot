import java.util.Random;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/** class that implements the Model of Domestic Robot application */
public class MyHouseModel extends GridWorldModel {

    // constants for the grid objects
    public static final int FRIDGE 		= 16;
    public static final int OWNER  		= 32;
    public static final int DELIVERY  	= 64;
//////AÑADIDO//////////////
	public static final int PAPER  		= 128;
	public static final int BOTTLE		= 256;
    public static final int DELIVERY2   = 512;
    public static final int DELIVERY3   = 1024;
    public static final int DISHWASHER  = 2048;
    public static final int CUPBOARD    = 4096;
    public static final int OWNER2  	= 8192;
    public static final int SILLONCL  	= 14;
	
	
////////////////////////////
    // the grid size
    public static final int GSize = 11;

    

    boolean thrownBottle = false;
    boolean thrownBottle2 = false;
    boolean thrownDish = false;
    boolean thrownDish2 = false;
    boolean fridgeOpen   = false; // whether the fridge is open
    boolean carryingBeer = false;
    boolean carryingPincho = false;
    boolean carryingBeer2 = false;
    boolean carryingPincho2 = false;
    boolean burningGarb = false; // whether the robot is carrying beer
    int sipCount        = 0; // how many sip the owner did
    int eatCount        = 0;
    int sipCount2        = 0; // how many sip the owner did
    int eatCount2       = 0;
    boolean flagGar = false;
    int availableBeers  = 5;
    int availablePinchos = 5; // how many beers are available
    int xGar = 0;
    int yGar = 0;
    int bottleCount  = 0; // how many beers are in paper
    int dishCount = 0;
    int dishCountCB = 30;
    double randomNum = new Random().nextDouble();
    double randomNum2 = new Random().nextDouble();

	
    Location lFridge = new Location(0,0);
    Location lOwner  = new Location(GSize-1, GSize-1);
    Location lOwner2  = new Location(GSize-3, GSize-1); 
    Location lDelivery  = new Location(0, GSize-1);
	Location lRobot = new Location(GSize/2, GSize/2);
    Location lRobot2 = new Location(GSize/2, GSize/2+1);
    Location lDelRobot = new Location(0,GSize/2);
    Location lDWashRobot = new Location(GSize-1,GSize/2);
    Location lDWash = new Location(GSize/2,0);
    Location lCBoard = new Location(lDWash.x+1,lDWash.y);
    ArrayList<Location> lBottle = new ArrayList<>();
    ArrayList<Location> lBottle2 = new ArrayList<>();
    ArrayList<Location> lObstacles = new ArrayList<>();
    List<Location> ubicaciones = new ArrayList<Location>();
	
//////AÑADIDO//////////////
	Location lPaper = new Location(GSize-1, 0);
    Location lPaperRobot = new Location(GSize-2, 0);
    Location lCleaner = new Location(GSize/2, GSize-1);
/////////////////////////
	

	Location closeTolDelivery  = new Location(1,GSize-2);
    Location closeTolFridge = new Location(0,1);
    Location closeToDWash = new Location(GSize/2, 1);
    Location closeTolOwnerDW = new Location(GSize-1, GSize-2);
    Location closeTolOwner2DW = new Location(GSize-3, GSize-2);

    Location closeToCBoard = new Location(lCBoard.x, 1);
	Location auxFridge  = new Location(closeTolFridge.x,closeTolFridge.y+1);
    Location closeTolOwner  = new Location(GSize-2,GSize-1);   
	//Location closeTolBin = new Location(GSize-1,1);
    Location closeTolPaper = new Location(GSize-1,1);

    Location closeTolOwner2 = new Location(GSize-4,GSize-1);
	
	boolean atFridge = false;
	boolean atOwner = false;
	boolean atDelivery = false;
//////AÑADIDO//////////////
	boolean atBin = false;
    boolean atBottle = false;
    boolean atBottle2 = false;
    boolean atPaper = false;
///////////////////
	boolean atBase = false;

    boolean atFridgeD = false;
	boolean atOwnerD = false;
	boolean atDeliveryD = false;
//////AÑADIDO//////////////
	boolean atBinD = false;
    boolean atBottleD = false;
    boolean atPaperD = false;
///////////////////
	boolean atBaseD = false;

    boolean atDWash = false;
    boolean atCBoard = false;
    boolean atOwnerDW = false;
    boolean atOwner2DW = false;
    boolean atBaseDw = false;

    boolean atOwner2 = false;
    boolean atFridge2 = false;
    boolean atPaper2 = false;
    boolean atBase2 = false;

    boolean atBaseCl = false;
    boolean atPaperCl = false;

    boolean atBottleO = false;
    boolean atBaseO = false;
    boolean atPaperO = false;
    boolean atFridgeO = false;

    boolean atFridgeO2 = false;
    boolean atBaseO2 = false;
    boolean atBottleO2 = false;
    boolean atPaperO2 = false;
    boolean atCBoardD = false;

    int numRobots = 7;

    public MyHouseModel() {
        // create a 7x7 grid with one mobile agent
        super(GSize, GSize, 7);

        //Location lObstacle = new Location(new Random().nextInt(6)+2,new Random().nextInt(6)+2);
        for (int index = 0; index < 9; index++) {
            Location lObstacle = new Location(new Random().nextInt(6)+2,new Random().nextInt(6)+2);

            while(lObstacle.equals(lRobot)  || lObstacles.contains(lObstacle)){
                lObstacle = new Location(new Random().nextInt(6)+2,new Random().nextInt(6)+2);
            }
           
            lObstacles.add(lObstacle);
        }

        // Base location of robot
        setAgPos(0, lRobot);
        setAgPos(1, lDelRobot);
        setAgPos(2, lDWashRobot);
        setAgPos(3, lPaperRobot);
        setAgPos(4, lOwner);
        setAgPos(5, lCleaner);
        setAgPos(6, lOwner2);

        
        add(FRIDGE, lFridge);
        add(OWNER, lOwner);
        add(OWNER2, lOwner2);
        add(PAPER, lPaper);
		add(DELIVERY, lDelivery);
        add(DISHWASHER, lDWash);
        add(CUPBOARD, lCBoard);
        add(SILLONCL, lCleaner);
        for (int i = 0; i < lObstacles.size(); i++) {
            int x = lObstacles.get(i).x;
            int y = lObstacles.get(i).y;
            addWall(x,y,x,y);
        }

        
        
        
    }

	
    boolean openFridge() {
        if (!fridgeOpen) {
            fridgeOpen = true;
            return true;
        } else {
            return true;
        }
    }

    boolean closeFridge() {
        if (fridgeOpen) {
            fridgeOpen = true;
            return true;
        } else {
            return true;
        }
    }
 
/*
	boolean atFridge(Location pos) {
		return pos.equals(lFridge);
	}       
                                                                                           
	boolean atOwner(Location pos) {
		return pos.equals(lOwner);
	}   
*/	

    boolean moveTowards(Location dest, int ag) {
        
        Location r1 = getAgPos(ag);

        Random random = new Random();
        double numeroRandom = random.nextDouble();

        if (numeroRandom > 0.5) {
              // Moverse en el eje x
              if (dest.x > r1.x) {
                r1.x++;
              } else if (dest.x < r1.x) {
                r1.x--;
              }
        } else {
              // Moverse en el eje y
              if (dest.y > r1.y) {
                r1.y++;
              } else if (dest.y < r1.y) {
                r1.y--;
              }
        }

		
		if (lObstacles.contains(r1) || isOccupiedByRobot(r1) || r1.equals(lDWash)
        || r1.equals(lCBoard) || r1.equals(lPaper) || r1.equals(lFridge)) {
            
             List<Location> adjacentPositions = getAdjacentLocations(getAgPos(ag));
            for (Location adj : adjacentPositions) {
                if (!lObstacles.contains(adj) && !isOccupiedByRobot(adj) && !adj.equals(lDWash)
                && !adj.equals(lCBoard) && !adj.equals(lPaper) && !adj.equals(lFridge) ) {
                    r1 = adj;
                    setAgPos(ag, r1);
                    break;
               }
            }
        } else {
            setAgPos(ag, r1);
        }
		
         // move the robot in the grid
		if(ag == 0){
            atOwner = r1.equals(closeTolOwner);
            atOwner2 = r1.equals(closeTolOwner2);
            atFridge = r1.equals(closeTolFridge);
            atDelivery = r1.equals(lDelivery);
            atBase = r1.equals(lRobot);
            atPaper = r1.equals(closeTolPaper);
        }else if(ag == 1){
            atFridgeD = r1.equals(closeTolFridge);
            atDeliveryD = r1.equals(lDelivery);
            atBaseD = r1.equals(lDelRobot);
            atCBoardD = r1.equals(closeToCBoard);

        }else if(ag == 2){
            atDWash= r1.equals(closeToDWash);
            atCBoard = r1.equals(closeToCBoard);
            atOwnerDW = r1.equals(closeTolOwnerDW);
            atOwner2DW = r1.equals(closeTolOwner2DW);
            atBaseDw = r1.equals(lDWashRobot);
        }else if(ag == 4){
            atBaseO = r1.equals(lOwner);
            atPaperO = r1.equals(closeTolPaper);
            atFridgeO = r1.equals(closeTolFridge);
        }else if(ag == 5){
            atBaseCl = r1.equals(lCleaner);
            atPaperCl = r1.equals(closeTolPaper);
        }else if(ag == 6){
            atBaseO2 = r1.equals(lOwner2);
            atPaperO2 = r1.equals(closeTolPaper);
            atFridgeO2 = r1.equals(closeTolFridge);
        }

        if(thrownBottle && ag == 5){
            atBottle = r1.equals(lBottle.get(0));
        }
        if(thrownBottle && ag == 4){
            atBottleO = r1.equals(lBottle.get(0));
        }
        if(thrownBottle2 && ag == 5){
            atBottle2 = r1.equals(lBottle2.get(0));
        }
        if(thrownBottle2 && ag == 6){
            atBottleO2 = r1.equals(lBottle2.get(0));
        }
        

        // repaint the fridge and owner locations
        if (view != null) {
            view.update(lFridge.x,lFridge.y);
            view.update(lOwner.x,lOwner.y);
            view.update(lDelivery.x,lDelivery.y);
            view.update(lOwner2.x,lOwner2.y);
            view.update(lDWash.x,lDWash.y);
            view.update(lCBoard.x,lCBoard.y);
            
        }
        return true;
    }


    public List<Location> getAdjacentLocations(Location loc) {
        List<Location> adjacents = new ArrayList<>();
        
        // Check up
        if (loc.y > 0) {
            adjacents.add(new Location(loc.x, loc.y - 1));
        }
        
        // Check right
        if (loc.x < GSize - 1) {
            adjacents.add(new Location(loc.x + 1, loc.y));
        }

        // Check down
        if (loc.y < GSize - 1) {
            adjacents.add(new Location(loc.x, loc.y + 1));
        }
        
        // Check left
        if (loc.x > 0) {
            adjacents.add(new Location(loc.x - 1, loc.y));
        }
        
        Collections.shuffle(adjacents);
        
        return adjacents;
    }

    boolean isOccupiedByRobot(Location lo){
        List<Location> list = new ArrayList<>();
        for (int i = 0; i < numRobots; i++) {
            list.add(getAgPos(i));
        }

        if(list.contains(lo)){
            return true;
        }else{
            return false;
        }
    }
    


    boolean getBeer() {
        availableBeers--;
        carryingBeer = true;
            
        if (view != null)
            view.update(lFridge.x,lFridge.y);
        return true;
    }

    boolean getBeer2(){
        //if(fridgeOpen && availableBeers > 0 && !carryingBeer2 ){
            availableBeers--;
            carryingBeer2 = true;
            
            if (view != null)
                view.update(lFridge.x,lFridge.y);
            return true;
        //}
        //return false;
    }

    boolean getPincho() {
        availablePinchos--;
        carryingPincho = true;
            
         if (view != null)
            view.update(lFridge.x,lFridge.y);
        return true; 
    }

    boolean burnGarb(){
        burningGarb = !burningGarb;
        return true;
    }


    boolean getPincho2(){
        //if(fridgeOpen && availableBeers > 0 && !carryingPincho2 ){
            availablePinchos--;
            carryingPincho2 = true;
            
            if (view != null)
                view.update(lFridge.x,lFridge.y);
            return true;
        //}
            
        //return false;
    }

    boolean addDish(){
        dishCount++;
        if (view != null)
            view.update(lDWash.x,lDWash.y);
        return true;
    }

    boolean addCB(){
        dishCountCB += 3;
        if (view != null)
            view.update(lCBoard.x,lCBoard.y);
        return true;
    }

    boolean removeCB(int n){
        dishCountCB -= n;
        if (view != null)
            view.update(lCBoard.x,lCBoard.y);
        return true;
    }

    boolean emptyDW(){
        dishCount = 0;
        if (view != null)
            view.update(lDWash.x,lDWash.y);
        return true;
    }

    boolean addBeer(int n) {
        availableBeers += n;
        if (view != null)
            view.update(lFridge.x,lFridge.y);
        return true;
    }

    boolean addPincho(int n){
        availablePinchos += n;
        if (view != null)
            view.update(lFridge.x,lFridge.y);
        return true;
    }

    boolean addProduct(String product, int n){
        if(product == "beer"){
            availableBeers += n;
        }else if(product == "pincho"){
            availablePinchos += n;
        }
        if (view != null)
            view.update(lFridge.x,lFridge.y);
        return true;
    }

    boolean handInBeer() {
        if (carryingBeer) {
			
            sipCount = 10;
            carryingBeer = false;
            if (view != null)
                view.update(lOwner.x,lOwner.y);
            return true;
        } else {
            return false;
        }
    }
    boolean handInBeer2() {
        if (carryingBeer2) {
			
            sipCount2 = 10;
            carryingBeer2 = false;
            if (view != null)
                view.update(lOwner2.x,lOwner2.y);
            return true;
        } else {
            return false;
        }
    }

    boolean handInPincho() {
        if (carryingPincho) {

            eatCount = 10;
            carryingPincho = false;
            return true;
        } else {
            return false;
        }
    }


    boolean handInPincho2() {
        if (carryingPincho2) {

            eatCount2 = 10;
            carryingPincho2 = false;
            return true;
        } else {
            return false;
        }
    }


	
	int aux=0;

    boolean sipBeer() {
        if (sipCount > 0) {
            sipCount--;
            if (view != null)
                view.update(lOwner.x,lOwner.y);
            return true;
        } else {
            return false;
        }
    }

    boolean sipBeer2() {
        if (sipCount2 > 0) {
            sipCount2--;
            if (view != null)
                view.update(lOwner2.x,lOwner2.y);
            return true;
        } else {
            return false;
        }
    }

    boolean eatPincho() {
        if (eatCount > 0) {
            eatCount--;
            if (view != null)
                view.update(lOwner.x,lOwner.y);
            return true;
        } else {
            return false;
        }
    }

    boolean eatPincho2() {
        if (eatCount2 > 0) {
            eatCount2--;
            if (view != null)
                view.update(lOwner2.x,lOwner2.y);
            return true;
        } else {
            return false;
        }
    }
    
    
    boolean pickUpBottle(){
        if(thrownBottle){
            thrownBottle = !thrownBottle;
            remove(BOTTLE,lBottle.get(0));
			lBottle.remove(0);
			atBottle = false;
            return true;
        }
        return false;
    }

    boolean pickUpBottle2(){
        if(thrownBottle2){
            thrownBottle2 = !thrownBottle2;
            remove(BOTTLE,lBottle2.get(0));
			lBottle2.remove(0);
			atBottle2 = false;
            return true;
        }
        return false;
    }

    boolean pickUpDish(){
        if(thrownDish){
            thrownDish = !thrownDish;
            return true;
        }else{
            return false;
        }
    }

    boolean pickUpDish2(){
        if(thrownDish2){
            thrownDish2 = !thrownDish2;
            return true;
        }else{
            return false;
        }
    }

    boolean trashBottle(){
        bottleCount++;
        if (view != null)
                view.update(lPaper.x,lPaper.y);
        return true;
    }

    boolean emptyPaper(){
        bottleCount = 0;
        if (view != null)
                view.update(lPaper.x,lPaper.y);
        return true;
    }
	
	
	boolean throw_bottle(){
        if(sipCount == 0){
            randomNum = new Random().nextDouble();
            Location lB = new Location(0,0);
            do{
                lB = new Location(new Random().nextInt(8)+1,new Random().nextInt(8)+1);
            }while(lObstacles.contains(lB));
            add(BOTTLE,lB);
            lBottle.add(lB);
            thrownBottle = true;
            if(lBottle.size() > 0){
				for(Location i : lBottle){
					view.update(i.x,i.y);
				}
			}
            return true;
        }else{
            return true;
        }
    }

    boolean throw_bottle2(){
        if(sipCount2 == 0){
            randomNum2 = new Random().nextDouble();
            Location lB = new Location(new Random().nextInt(8)+1,new Random().nextInt(8)+1);
            while(lObstacles.contains(lB)){
                lB = new Location(new Random().nextInt(8)+1,new Random().nextInt(8)+1);
            }
            add(BOTTLE,lB);
            lBottle2.add(lB);
            //lBottle.add(lB);
            thrownBottle2 = true;
            if(lBottle.size() > 0){
				for(Location i : lBottle){
					view.update(i.x,i.y);
				}
			}
            return true;
        }else{
            return true;
        }
    }

    boolean throw_dish(){
        if(eatCount == 0){
            thrownDish = true;
            return true;
        }else{
            return true;
        }
    }

    boolean throw_dish2(){
        if(eatCount2 == 0){
            thrownDish2 = true;
            return true;
        }else{
            return true;
        }
    }

   

}



