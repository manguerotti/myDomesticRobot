import jason.environment.grid.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import jason.environment.grid.Location;


/** class that implements the View of Domestic Robot application */
public class MyHouseView extends GridWorldView {

    MyHouseModel hmodel;

    public MyHouseView(MyHouseModel model) {
        super(model, "Domestic Robot", 700);
        hmodel = model;
        defaultFont = new Font("Arial", Font.BOLD, 12); // change default font
        setVisible(true);
        //repaint();
    }

    /** draw application objects */
    @Override
    public void draw(Graphics g, int x, int y, int object) {
        Location lRobot = hmodel.getAgPos(0);
        super.drawObstacle(g,x,y);
		super.drawAgent(g, x, y, Color.lightGray, -1);
        switch (object) {
        case MyHouseModel.FRIDGE:
			super.drawAgent(g, x, y, Color.white, -1);
			if (lRobot.equals(hmodel.lFridge)) {
                super.drawAgent(g, x, y, Color.yellow, -1);
            }
            g.setColor(Color.black);
            drawString(g, x, y, defaultFont, "F (P:"+hmodel.availablePinchos+" B:"+hmodel.availableBeers+")");
            break;
        case MyHouseModel.DELIVERY:
			super.drawAgent(g, x, y, Color.green, -1);
            if (lRobot.equals(hmodel.lDelivery)) {
                super.drawAgent(g, x, y, Color.yellow, -1);
            }
            g.setColor(Color.black);
            drawString(g, x, y, defaultFont, "Del");
            break;
        /*case MyHouseModel.OWNER:
            if(!hmodel.thrownBottle){
                super.drawAgent(g, x, y, Color.red, -1);
                if (lRobot.equals(hmodel.lOwner)) {
                    super.drawAgent(g, x, y, Color.yellow, -1);
                }
                String o = "O1";
                if (hmodel.sipCount > 0) {
                    o +=  " ("+hmodel.sipCount+","+hmodel.eatCount+")";
                }
                g.setColor(Color.black);
                drawString(g, x, y, defaultFont, o);
            }
            break;*/
        /*case MyHouseModel.OWNER2:
			super.drawAgent(g, x, y, Color.red, -1);
            if (lRobot.equals(hmodel.lOwner2)) {
                super.drawAgent(g, x, y, Color.yellow, -1);
            }
            String o2 = "O2";
            if (hmodel.sipCount > 0) {
                o2 +=  " ("+hmodel.sipCount2+","+hmodel.eatCount2+")";
            }
            g.setColor(Color.black);
            drawString(g, x, y, defaultFont, o2);
            break;*/
		case MyHouseModel.PAPER:
			super.drawAgent(g, x, y, Color.blue, -1);
            String p = "Bin";
                p +=  " ("+hmodel.bottleCount+"/5)"; 
            g.setColor(Color.black);
            drawString(g, x, y, defaultFont, p);
            break;
		case MyHouseModel.BOTTLE:
            if (lRobot.equals(hmodel.lBottle)) {
                super.drawAgent(g, x, y, Color.yellow, -1);
            }
            String b = "Bottle";
            super.drawAgent(g, x, y, Color.LIGHT_GRAY, -1);
            g.setColor(Color.black);
            drawString(g, x, y, defaultFont, b);
            break;
		case MyHouseModel.DISHWASHER:
            String d = "DW";
			super.drawAgent(g, x, y, Color.MAGENTA, -1);
            d +=  " ("+hmodel.dishCount+"/3)";
            g.setColor(Color.black);
            drawString(g, x, y, defaultFont, d);
            break;
		case MyHouseModel.CUPBOARD:
            Color verdeLima = new Color(50, 205, 50);
            String u = "CB";
            u += " ("+hmodel.dishCountCB+"/50)";
			super.drawAgent(g, x, y, verdeLima, -1);
            g.setColor(Color.black);
            drawString(g, x, y, defaultFont, u);
            break;
        case MyHouseModel.SILLONCL:
            String cl = "Sillón";
            super.drawAgent(g, x, y, Color.LIGHT_GRAY, -1);
            g.setColor(Color.black);
            drawString(g, x, y, defaultFont, cl);
            break;

        }
        //repaint();
    }

    @Override
    public void drawAgent(Graphics g, int x, int y, Color c, int id) {
        Location lRobot = hmodel.getAgPos(0);
        Location lDelRobot = hmodel.getAgPos(1);
        Location lDWashRobot = hmodel.getAgPos(2);
        Location lOwner = hmodel.getAgPos(4);
        Location lPaperRobot = hmodel.getAgPos(3);
        Location lCleaner = hmodel.getAgPos(5);
        Location lOwner2 = hmodel.getAgPos(6);
        

        if (!lRobot.equals(hmodel.lOwner) && !lRobot.equals(hmodel.lFridge) && !lRobot.equals(hmodel.getAgPos(3)) ) {
            c = Color.yellow;
            if (hmodel.carryingBeer) c = Color.orange;
            super.drawAgent(g, lRobot.x, lRobot.y, c, -1);
            g.setColor(Color.black);
            super.drawString(g, lRobot.x, lRobot.y, defaultFont, "Rob");

        }
        if(!hmodel.burningGarb){
            super.drawAgent(g, lPaperRobot.x,lPaperRobot.y, Color.RED, -1);
        }else{
            super.drawAgent(g, lPaperRobot.x,lPaperRobot.y, Color.GREEN, -1);
        }
        
        super.drawAgent(g, lDelRobot.x,lDelRobot.y, Color.yellow, -1);
        super.drawAgent(g, lCleaner.x,lCleaner.y, Color.yellow, -1);
        super.drawAgent(g, lDWashRobot.x,lDWashRobot.y, Color.yellow, -1);
        super.drawAgent(g, lOwner.x,lOwner.y, Color.red, -1);
        super.drawAgent(g, lOwner2.x,lOwner2.y, Color.red, -1);
            
        
        

        

        g.setColor(Color.black);
        super.drawString(g, lPaperRobot.x, lPaperRobot.y, defaultFont, "Burner");
        super.drawString(g, lDelRobot.x, lDelRobot.y, defaultFont, "DelRob");  
        super.drawString(g, lCleaner.x, lCleaner.y, defaultFont, "Cleaner");
        super.drawString(g, lDWashRobot.x, lDWashRobot.y, defaultFont, "DWRob");
        String o = "O1";
        if (hmodel.sipCount > 0) {
            o +=  " ("+hmodel.sipCount+","+hmodel.eatCount+")";
        }
        super.drawString(g, lOwner.x, lOwner.y, defaultFont, o);

        String o2 = "O2";
        if (hmodel.sipCount2 > 0) {
            o2 +=  " ("+hmodel.sipCount2+","+hmodel.eatCount2+")";
        }
        super.drawString(g, lOwner2.x, lOwner2.y, defaultFont, o2);

          
       
    }

    public void drawEmpty(Graphics g, int x, int y){
		g.setColor(new Color(0xEEEEEE));
		g.fillRect(x * cellSizeW +1, y * cellSizeH+1, cellSizeW-1, cellSizeH-1);
		g.setColor(Color.lightGray);
		g.drawRect(x * cellSizeW, y * cellSizeH, cellSizeW, cellSizeH);


		
	}

}
