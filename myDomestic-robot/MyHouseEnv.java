import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.Location;
import java.util.logging.Logger;
import java.util.Random;

public class MyHouseEnv extends Environment {

    // common literals
    //creencia de que el owner ha lanzado una cerveza.
   
    
    public static final Literal ns = Literal.parseLiteral("nextSlot");

    public static final Literal of  = Literal.parseLiteral("open(fridge)");
    public static final Literal clf = Literal.parseLiteral("close(fridge)");
    public static final Literal gb1  = Literal.parseLiteral("get(beer)");
    public static final Literal gp1  = Literal.parseLiteral("get(pincho)");
    public static final Literal gb12  = Literal.parseLiteral("get(beer2)");
    public static final Literal gp12  = Literal.parseLiteral("get(pincho2)");
    public static final Literal hb  = Literal.parseLiteral("hand_in(beer)");
    public static final Literal hp  = Literal.parseLiteral("hand_in(pincho)");
    public static final Literal hb2  = Literal.parseLiteral("hand_in(beer2)");
    public static final Literal hp2  = Literal.parseLiteral("hand_in(pincho2)");
    public static final Literal sb  = Literal.parseLiteral("sip(beer)");
    public static final Literal ep  = Literal.parseLiteral("eat(pincho)");
    public static final Literal hob = Literal.parseLiteral("has(myOwner,beer)");
    public static final Literal hob2 = Literal.parseLiteral("has(myOwner2,beer)");
    public static final Literal hop = Literal.parseLiteral("has(myOwner,pincho)");
    public static final Literal hop2 = Literal.parseLiteral("has(myOwner2,pincho)");
    public static final Literal tb = Literal.parseLiteral("throwBottle(garb)");
    public static final Literal tp = Literal.parseLiteral("throwPincho(dish)");
    public static final Literal pb1 = Literal.parseLiteral("pickUpBottle(garb1)");
    public static final Literal pb2 = Literal.parseLiteral("pickUpBottle(garb2)");
    public static final Literal pd = Literal.parseLiteral("pickUpDish(dish)");
    public static final Literal pd2 = Literal.parseLiteral("pickUpDish(dish2)");
    public static final Literal thrashb = Literal.parseLiteral("thrashBottle(garb)");
    public static final Literal addD = Literal.parseLiteral("addDish(dish)");
    public static final Literal eptyD = Literal.parseLiteral("emptyDW(dishes)");


    public static final Literal af = Literal.parseLiteral("at(myRobot,fridge)");
    public static final Literal afO = Literal.parseLiteral("at(myOwner,fridge)");
    public static final Literal afO2 = Literal.parseLiteral("at(myOwner2,fridge)");
    public static final Literal ao = Literal.parseLiteral("at(myRobot,myOwner)");
    public static final Literal ao1 = Literal.parseLiteral("at(myRobot,myOwner2)");
    public static final Literal ao2 = Literal.parseLiteral("at(myRobot2,myOwner2)");
    public static final Literal ad = Literal.parseLiteral("at(myRobot,delivery)");
    public static final Literal ab = Literal.parseLiteral("at(myRobot,base)");
    public static final Literal ab2 = Literal.parseLiteral("at(myRobot2,base)");
    public static final Literal ag = Literal.parseLiteral("at(myCleaner,garb1)");
    public static final Literal agO1 = Literal.parseLiteral("at(myOwner,garb1)");
    public static final Literal ag2 = Literal.parseLiteral("at(myCleaner,garb2)");
    public static final Literal agO2 = Literal.parseLiteral("at(myOwner2,garb2)");
    public static final Literal apCl = Literal.parseLiteral("at(myCleaner,paper)");
    public static final Literal ap01 = Literal.parseLiteral("at(myOwner,paper)");
    public static final Literal ap02 = Literal.parseLiteral("at(myOwner2,paper)");
    public static final Literal abCl = Literal.parseLiteral("at(myCleaner,base)");
    public static final Literal abO = Literal.parseLiteral("at(myOwner,base)");
    public static final Literal abO2 = Literal.parseLiteral("at(myOwner2,base)");
    public static final Literal ap2 = Literal.parseLiteral("at(myRobot2,paper)");
    public static final Literal afD = Literal.parseLiteral("at(myRobotDel,fridge)");
    public static final Literal aoD = Literal.parseLiteral("at(myRobotDel,myOwner)");
    public static final Literal adD = Literal.parseLiteral("at(myRobotDel,delivery)");
    public static final Literal acbD = Literal.parseLiteral("at(myRobotDel,cboard)");
    public static final Literal abD = Literal.parseLiteral("at(myRobotDel,base)");
    public static final Literal agD = Literal.parseLiteral("at(myRobotDel,garb)");
    public static final Literal apD = Literal.parseLiteral("at(myRobotDel,paper)");
    public static final Literal aoDW = Literal.parseLiteral("at(myRobotDWash,myOwner)");
    public static final Literal ao2DW = Literal.parseLiteral("at(myRobotDWash,myOwner2)");
    public static final Literal adwDW = Literal.parseLiteral("at(myRobotDWash,dwash)");
    public static final Literal acbDW = Literal.parseLiteral("at(myRobotDWash,cboard)");
    public static final Literal abDW = Literal.parseLiteral("at(myRobotDWash,base)");
    public static final Literal tg1 = Literal.parseLiteral("thrown(garb1)");
    public static final Literal tg2 = Literal.parseLiteral("thrown(garb2)");
    public static final Literal td = Literal.parseLiteral("thrown(dish)");
    public static final Literal td2 = Literal.parseLiteral("thrown(dish2)");
    public static final Literal pg = Literal.parseLiteral("pickBottle");
    public static final Literal pF = Literal.parseLiteral("paperFull");
    public static final Literal eP = Literal.parseLiteral("emptyPaper");
    public static final Literal burning = Literal.parseLiteral("burning(garb)");
    public static final Literal addCB = Literal.parseLiteral("addCBoard(dishes)");
    public static final Literal addStockF = Literal.parseLiteral("addStockF");
    public static final Literal takeD = Literal.parseLiteral("takeDishes(dishes, Qtd*5)");

    

    static Logger logger = Logger.getLogger(MyHouseEnv.class.getName());

    MyHouseModel model; // the model of the grid

    @Override
    public void init(String[] args) {
        model = new MyHouseModel();

        if (args.length == 1 && args[0].equals("gui")) {
            MyHouseView view  = new MyHouseView(model);
            model.setView(view);
        }

        updatePercepts();
    }

    /** creates the agents percepts based on the HouseModel */
    void updatePercepts() {
        // clear the percepts of the agents
        clearPercepts("myRobot");
        clearPercepts("myRobot2");
        clearPercepts("myOwner");
        clearPercepts("myOwner2");
        clearPercepts("myRobotDel");
        clearPercepts("myRobotDWash");
        clearPercepts("myRobotPaper");
        clearPercepts("myCleaner");

        // get the robot location
        Location lRobot = model.getAgPos(0);

        // add agent location to its percepts
        //if (lRobot.equals(model.closeTolFridge)) {
		if (model.atFridge) {
            addPercept("myRobot", af);
        }
        
        //if (lRobot.equals(model.closeTolOwner)) {
		if (model.atOwner) {
            addPercept("myRobot", ao);
        }
        if (model.atFridgeO) {
            addPercept("myOwner", afO);
        }

        if (model.atOwner2) {
            addPercept("myRobot", ao1);
        }

        if (model.atOwner2) {
            addPercept("myRobot2", ao2);
        }

		if (model.atBase) {
            addPercept("myRobot", ab);
        }
        if (model.atBase2) {
            addPercept("myRobot2", ab2);
        }

        if(model.atBottle){
            addPercept("myCleaner", ag);
        }

        if(model.atBottleO){
            addPercept("myOwner", agO1);
        }

        if(model.atPaperO){
            addPercept("myOwner", ap01);
        }

        if(model.atBaseO){
            addPercept("myOwner", abO);
        }

        if(model.atBottle2){
            addPercept("myCleaner", ag2);
        }

        if(model.atPaperCl){
            addPercept("myCleaner", apCl);
        }

        if(model.atBaseCl){
            addPercept("myCleaner", abCl);
        }


       
        if(model.atPaper2){
            addPercept("myRobot2", ap2);
        }

        if(model.atFridgeD){
            addPercept("myRobotDel", afD);
        }

        if(model.atBaseD){
            addPercept("myRobotDel", abD);
        }

        if(model.atDeliveryD){
            addPercept("myRobotDel", adD);
        }

        if(model.atCBoardD){
            addPercept("myRobotDel", acbD);
        }

        if(model.atDWash){
            addPercept("myRobotDWash", adwDW);
        }

        if(model.atCBoard){
            addPercept("myRobotDWash", acbDW);
        }

        if(model.atOwnerDW){
            addPercept("myRobotDWash", aoDW);
        }

        if(model.atOwner2DW){
            addPercept("myRobotDWash", ao2DW);
        }

        if(model.atBaseDw){
            addPercept("myRobotDWash", abDW);
        }



        // add beer "status" the percepts
        if (model.fridgeOpen) {
            addPercept("myRobot", Literal.parseLiteral("stock(beer,"+model.availableBeers+")"));
        }
		
        if (model.sipCount > 0) {
            addPercept("myRobot", hob);
            addPercept("myOwner", hob);
        }
        if (model.sipCount2 > 0) {
            addPercept("myRobot", hob2);
            addPercept("myOwner2", hob2);
        }

        if (model.eatCount > 0) {
            addPercept("myRobot", hop);
            addPercept("myOwner", hop);
        }
        if (model.eatCount2 > 0) {
            addPercept("myRobot", hop2);
            addPercept("myOwner2", hop2);
        }


        if(model.thrownBottle){
            
            if(model.randomNum > 0.1){
                addPercept("myCleaner", tg1);
            }else{
                addPercept("myOwner", tg1);
            }
            
        }

        if(model.thrownBottle2){

            if(model.randomNum2 > 0.1){
                addPercept("myCleaner", tg2);
            }else{
                addPercept("myOwner2", tg2);
            }

           
        }

        if(model.thrownDish){
            addPercept("myRobotDWash", td); 
        }

        if(model.thrownDish2){
            addPercept("myRobotDWash", td2); 
        }

        if(model.bottleCount == 5){
            addPercept("myRobotPaper", pF);  
        }

        if(model.atFridgeO2){
            addPercept("myOwner2", afO2);
        }

        if(model.atBaseO2){
            addPercept("myOwner2", abO2);
        }

        if(model.atBottleO2){
            addPercept("myOwner2", agO2);
        }

        if(model.atPaperO2){
            addPercept("myOwner2", ap02);
        }

        /*if(model.availableBeers < 5 || model.availablePinchos < 5){
            addPercept("myRobot", addStockF);
        }*/



    }


    @Override
    public boolean executeAction(String ag, Structure action) {
        System.out.println("["+ag+"] doing: "+action);
        boolean result = false;
        
        if(action.equals(of) & ag.equals("myOwner2")){
            result = model.openFridge();

        }else if(action.equals(of) & ag.equals("myOwner")){
            result = model.openFridge();

        }else if(action.equals(gb1) & ag.equals("myOwner")){
            result = model.getBeer();

        }else if(action.equals(gb12) & ag.equals("myOwner2")){
            result = model.getBeer2();

        }else if(action.equals(gp1) & ag.equals("myOwner")){
            result = model.getPincho();

        }else if(action.equals(gp12) & ag.equals("myOwner2")){
            result = model.getPincho2();

        }else if(action.equals(clf) & ag.equals("myOwner")){
            result = model.closeFridge();

        }else if(action.equals(clf) & ag.equals("myOwner2")){
            result = model.closeFridge();

        }else if(action.equals(hb) & ag.equals("myOwner")){
            result = model.handInBeer();

        }else if(action.equals(hp) & ag.equals("myOwner")){
            result = model.handInPincho();

        }else if(action.equals(tb) & ag.equals("myOwner")){
            result = model.throw_bottle();

        }else if(action.equals(tb) & ag.equals("myOwner2")){
            result = model.throw_bottle2();

        }else if(action.equals(addD) & ag.equals("myDishWasher")){
            result = model.addDish();

        }else if(action.equals(eptyD) & ag.equals("myRobotDWash")){
            result = model.emptyDW();

        }else if(action.equals(tp) & ag.equals("myOwner")){
            result = model.throw_dish();

        }else if(action.equals(tp) & ag.equals("myOwner2")){
            result = model.throw_dish2();

        }else if(action.equals(eP) & ag.equals("myRobotPaper")){
            result = model.emptyPaper();

        }else if(action.equals(burning) & ag.equals("myRobotPaper")){
            result = model.burnGarb();

        }else if(action.equals(pd) & ag.equals("myRobotDWash")){
            //addPercept("myRobotDWash",hola);
            result = model.pickUpDish();

        }else if(action.equals(addCB) & ag.equals("myRobotDWash")){
            //addPercept("myRobotDWash",hola);
            result = model.addCB();

        }else if(action.equals(pd2) & ag.equals("myRobotDWash")){
            //addPercept("myRobotDWash",hola);
            result = model.pickUpDish2();

        }else if(action.equals(pb1) & ag.equals("myCleaner")){
            result = model.pickUpBottle();

        }else if(action.getFunctor().equals("takeDishes") & ag.equals("myRobotDel")){
            try {
                //Thread.sleep(4000);
                result = model.removeCB( (int)((NumberTerm)action.getTerm(1)).solve());
            } catch (Exception e) {
                logger.info("Failed to execute action deliver!"+e);
            }

        }else if(action.equals(pb1) & ag.equals("myOwner")){
            result = model.pickUpBottle();

        }else if(action.equals(pb2) & ag.equals("myOwner2")){
            result = model.pickUpBottle2();

        }else if(action.equals(pb2) & ag.equals("myCleaner")){
            result = model.pickUpBottle2();

        }else if(action.equals(thrashb) & ag.equals("myCleaner")){
            result = model.trashBottle();

        }else if(action.equals(thrashb) & ag.equals("myOwner")){
            result = model.trashBottle();

        }else if(action.equals(thrashb) & ag.equals("myOwner2")){
            result = model.trashBottle();

        }else if (action.equals(of) & ag.equals("myRobot")) { // of = open(fridge)
            result = model.openFridge();

        } else if (action.equals(of) & ag.equals("myRobot2")) { // of = open(fridge)
            result = model.openFridge();

        } else if (action.equals(clf) & ag.equals("myRobot")) { // clf = close(fridge)
            result = model.closeFridge();

        } else if (action.equals(clf) & ag.equals("myRobot2")) { // clf = close(fridge)
            result = model.closeFridge();

        } else if (action.getFunctor().equals("move_towards") & ag.equals("myRobot")) {
            String l = action.getTerm(0).toString();
            Location dest = null;
            if (l.equals("fridge")) {
                dest = model.closeTolFridge;
            } else if (l.equals("myOwner")) {
                dest = model.closeTolOwner;
            } else if (l.equals("myOwner2")) {
                dest = model.closeTolOwner2;
            } else if (l.equals("delivery")) {
                dest = model.lDelivery;
            } else if (l.equals("base")) {
                dest = model.lRobot;
            }else if (l.equals("garb")){
                dest = model.lBottle.get(0);
            }else if(l.equals("paper")){
                dest = model.closeTolPaper;
            }

            try {
                result = model.moveTowards(dest,0);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if (action.getFunctor().equals("move_towards") & ag.equals("myOwner")) {
            String l = action.getTerm(0).toString();
            Location dest = null;
            if (l.equals("fridge")) {
                dest = model.closeTolFridge;
            } else if (l.equals("base")) {
                dest = model.lOwner;
            }else if (l.equals("garb1")){
                dest = model.lBottle.get(0);
            }else if(l.equals("paper")){
                dest = model.closeTolPaper;
            }

            try {
                result = model.moveTowards(dest,4);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if (action.getFunctor().equals("move_towards") & ag.equals("myOwner2")) {
            String l = action.getTerm(0).toString();
            Location dest = null;
            if (l.equals("fridge")) {
                dest = model.closeTolFridge;
            } else if (l.equals("base")) {
                dest = model.lOwner;
            }else if (l.equals("garb2")){
                dest = model.lBottle2.get(0);
            }else if(l.equals("paper")){
                dest = model.closeTolPaper;
            }

            try {
                result = model.moveTowards(dest,6);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if (action.getFunctor().equals("move_towards") & ag.equals("myRobotDel")) {
            String l = action.getTerm(0).toString();
            Location dest = null;
            if (l.equals("fridge")) {
                dest = model.closeTolFridge;
            } else if (l.equals("myOwner")) {
                dest = model.closeTolOwner;
            } else if (l.equals("delivery")) {
                dest = model.lDelivery;
            } else if (l.equals("base")) {
                dest = model.lDelRobot;
            }else if (l.equals("cboard")) {
                dest = model.closeToCBoard;
            }

            try {
                result = model.moveTowards(dest,1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (action.getFunctor().equals("move_towards") & ag.equals("myRobotDWash")) {
            String l = action.getTerm(0).toString();
            Location dest = null;
            if (l.equals("fridge")) {
                dest = model.closeTolFridge;
            } else if (l.equals("myOwner")) {
                dest = model.closeTolOwnerDW;
            } else if (l.equals("myOwner2")) {
                dest = model.closeTolOwner2DW;
            } else if (l.equals("base")) {
                dest = model.lDWashRobot;
            }else if (l.equals("dwash")) {
                dest = model.closeToDWash;
            }else if (l.equals("cboard")) {
                dest = model.closeToCBoard;
            }

            try {
                result = model.moveTowards(dest,2);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (action.getFunctor().equals("move_towards") & ag.equals("myCleaner")) {
            String l = action.getTerm(0).toString();
            Location dest = null;
            if (l.equals("garb1")) {
                dest = model.lBottle.get(0);
            }else if (l.equals("garb2")) {
                dest = model.lBottle2.get(0);
            } else if (l.equals("base")) {
                dest = model.lCleaner;
            }else if (l.equals("paper")) {
                dest = model.closeTolPaper;
            }

            try {
                result = model.moveTowards(dest,5);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (action.equals(gb1) & ag.equals("myRobot")) {
            result = model.getBeer();

        } else if (action.equals(gb12) & ag.equals("myRobot")) {
            result = model.getBeer2();

        } else if (action.equals(gp1) & ag.equals("myRobot")) {
            result = model.getPincho();

        } else if (action.equals(gp12) & ag.equals("myRobot")) {
            result = model.getPincho2();

        } else if (action.equals(gb1) & ag.equals("myRobot2")) {
            result = model.getBeer2();

        } else if (action.equals(gp1) & ag.equals("myRobot2")) {
            result = model.getPincho2();

        } else if (action.equals(hb) & ag.equals("myRobot")) {
            result = model.handInBeer();

        } else if (action.equals(hb2) & ag.equals("myRobot")) {
            result = model.handInBeer2();

        } else if (action.equals(hb2) & ag.equals("myOwner2")) {
            result = model.handInBeer2();

        } else if (action.equals(hb) & ag.equals("myRobot2")) {
            result = model.handInBeer2();

        } else if (action.equals(hp) & ag.equals("myRobot")) {
            result = model.handInPincho();

        } else if (action.equals(hp2) & ag.equals("myRobot")) {
            result = model.handInPincho2();

        } else if (action.equals(hp2) & ag.equals("myOwner2")) {
            result = model.handInPincho2();

        } else if (action.equals(hp) & ag.equals("myRobot2")) {
            result = model.handInPincho2();

        } else if (action.equals(sb) & ag.equals("myOwner")) {
            result = model.sipBeer();

        } else if (action.equals(ep) & ag.equals("myOwner")) {
            result = model.eatPincho();

        } else if (action.equals(sb) & ag.equals("myOwner2")) {
            result = model.sipBeer2();

        } else if (action.equals(ep) & ag.equals("myOwner2")) {
            result = model.eatPincho2();

        } else if (action.getFunctor().equals("deliver") & ag.equals("mySupermarket")) {
            try {
                Thread.sleep(4000);
                result = model.addBeer( (int)((NumberTerm)action.getTerm(1)).solve());
            } catch (Exception e) {
                logger.info("Failed to execute action deliver!"+e);
            }

        } else if(action.getFunctor().equals("deliver") & ag.equals("mySupermarket2")){
            try {
                Thread.sleep(4000);
                result = model.addBeer( (int)((NumberTerm)action.getTerm(1)).solve());
            } catch (Exception e) {
                logger.info("Failed to execute action deliver!"+e);
            }
        }else if(action.getFunctor().equals("deliver") & ag.equals("mySupermarket3")){
            try {
                Thread.sleep(4000);
                result = model.addBeer( (int)((NumberTerm)action.getTerm(1)).solve());
            } catch (Exception e) {
                logger.info("Failed to execute action deliver!"+e);
            }
        }else if (action.getFunctor().equals("deliverP") & ag.equals("mySupermarket")) {
            try {
                Thread.sleep(4000);
                result = model.addPincho( (int)((NumberTerm)action.getTerm(1)).solve());
            } catch (Exception e) {
                logger.info("Failed to execute action deliver!"+e);
            }

        } else if(action.getFunctor().equals("deliverP") & ag.equals("mySupermarket2")){
            try {
                Thread.sleep(4000);
                result = model.addPincho( (int)((NumberTerm)action.getTerm(1)).solve());
            } catch (Exception e) {
                logger.info("Failed to execute action deliver!"+e);
            }
        }else if(action.getFunctor().equals("deliverP") & ag.equals("mySupermarket3")){
            try {
                Thread.sleep(4000);
                result = model.addPincho( (int)((NumberTerm)action.getTerm(1)).solve());
            } catch (Exception e) {
                logger.info("Failed to execute action deliver!"+e);
            }
        }else if(action.getFunctor().equals("deliverP") & ag.equals("myRobotDel")){
            try {
                Thread.sleep(4000);
                result = model.addPincho( (int)((NumberTerm)action.getTerm(1)).solve());
            } catch (Exception e) {
                logger.info("Failed to execute action deliver!"+e);
            }
        }else {
            logger.info("Failed to execute action "+action);
        }

        if (result) {
            updatePercepts();
            try {
                Thread.sleep(100);
            } catch (Exception e) {}
        }
        return result;
    }
}
