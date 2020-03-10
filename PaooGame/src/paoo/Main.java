package paoo;

import paoo.Game.*;

public class Main {


    public static void main(String[] args) throws Exception{
	    GameView theView = new GameView();
        theView.setSize(Map.SCREEN_WIDTH,Map.SCREEN_HEIGHT);
	    theView.setResizable(false);
	   Board panel1 = new Board();
	   panel1.repaint();
	   panel1.revalidate();
            theView.getPanel().add(panel1);
            panel1.requestFocusInWindow();

        theView.setVisible(true);
    }
}
