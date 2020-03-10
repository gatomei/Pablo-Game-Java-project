package paoo.Game;

import javax.swing.*;
import java.awt.*;

public class Level3 extends ImageLoader{
    private final Image enemyRight;
    private final Image enemyLeft;
    private final Image brick;
    private final Image emptySurprise;
    private final Image grass;
    private final Image soil;

    private static Level3 instance=null;

    public static Level3 getInstance(){
        if( instance != null){
            return instance;
        }
        else{
            instance=new Level3();
            return instance;
        }
    }

    public Level3() {
        this.enemyLeft = loadImage("images/enemyLeft3.png");
        this.enemyRight = loadImage("images/enemyRight3.png");
        this.brick = loadImage("images/caramida3.png");
        this.emptySurprise = loadImage("images/emptySurprise3.png");
        this.grass = loadImage("images/sol3.png");
        this.soil = loadImage("images/sol3.png");

    }

    public Image loadImage(String imageAddress) {
        ImageIcon icon = new ImageIcon(imageAddress);
        return icon.getImage();
    }
    public Image getEnemyLeft(){ return enemyLeft;};
    public Image getEnemyRight(){ return  enemyRight;};
    public Image getBrick(){ return brick;}
    public Image getEmptySurprise(){ return emptySurprise;}
    public Image getGrass(){ return grass;}
    public Image getSoil(){ return soil;}
}
