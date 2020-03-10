package paoo.Game;

import javax.swing.*;
import java.awt.*;

public class Level2 extends ImageLoader{
    private final Image sky;
    private final Image grass;
    private final Image soil;
    private final Image brick;
    private final Image coin;
    private final Image emptySurprise;

    private static Level2 instance=null;

    public static Level2 getInstance(){
        if( instance != null){
            return instance;
        }
        else{
            instance=new Level2();
            return instance;
        }
    }

    public Level2() {
        this.sky = loadImage("images/cer1.png");
        this.grass = loadImage("images/soli2.png");
        this.soil = loadImage("images/sol2.png");
        this.brick = loadImage("images/caramida1.png");
        this.coin = loadImage("images/coin.png");
        this.emptySurprise = loadImage("images/emptySurprise1.png");


    }

    public Image loadImage(String imageAddress) {
        ImageIcon icon = new ImageIcon(imageAddress);
        return icon.getImage();
    }
    public Image getSky(){ return sky;}
    public Image getGrass(){ return grass;}
    public Image getSoil(){ return soil;}
    public Image getBrick(){ return brick;}
    public Image getCoin(){ return coin;}
    public Image getEmptySurprise(){ return emptySurprise;}

}
