package paoo.Game;

import javax.swing.*;
import java.awt.*;

public class Level1 extends ImageLoader{
    private final Image sky;
    private final Image grass;
    private final Image soil;
    private final Image brick;

    private static Level1 instance=null;

    public static Level1 getInstance(){
        if( instance != null){
            return instance;
        }
        else{
            instance=new Level1();
            return instance;
        }
    }

    public Level1() {
        this.sky = loadImage("images/cer.png");
        this.grass = loadImage("images/soli.png");

        this.soil = loadImage("images/sol1.png");
        this.brick = loadImage("images/caramida.png");

    }

    public Image loadImage(String imageAddress) {
        ImageIcon icon = new ImageIcon(imageAddress);
        return icon.getImage();
    }
    public Image getSky(){ return sky;}
    public Image getGrass(){ return grass;}
    public Image getSoil(){ return soil;}
    public Image getBrick(){ return brick;}


}
