package paoo.Game;

import javax.swing.*;
import java.awt.*;

public class ImageLoader {
    private final Image sky;
    private final Image grass;
    private final Image hillUpLeft;
    private final Image hillDownLeft;
    private final Image hillUpRight;
    private final Image hillDownRight;
    private final Image soil;
    private final Image cloudRight;
    private final Image cloudLeft;
    private final Image bushLeft;
    private final Image bushRight;
    private final Image coin;
    private final Image brick;
    private final Image playerRight;
    private final Image playerLeft;
    private final Image surprise;
    private final Image emptySurprise;
    private final Image mushroom;
    private final Image enemyLeft;
    private final Image playerHurt;
    private final Image enemyRight;
    private final Image bomb;
    private final Image menu;
    private final Image castle_ss;
    private final Image castle_sj;
    private final Image castle_ds;
    private final Image castle_dj;
    private final Image star;
    private final Image gameOver;
    private final Image levelUp;
    private final Image gameCompleted;


    private static ImageLoader instance=null;

    public static ImageLoader getInstance(){
        if( instance != null){
            return instance;
        }
        else{
            instance=new ImageLoader();
            return instance;
        }
    }

    public ImageLoader() {
        this.sky = loadImage("images/cer.png");
        this.grass = loadImage("images/soli.png");
        this.hillUpLeft= loadImage("images/dealss.png");
        this.hillDownLeft= loadImage("images/dealsj.png");
        this.hillUpRight= loadImage("images/dealds.png");
        this.hillDownRight= loadImage("images/dealDJ.png");
        this.soil = loadImage("images/sol1.png");
        this.cloudRight = loadImage("images/nordrt.png");
        this.cloudLeft = loadImage("images/norstg.png");
        this.bushLeft = loadImage("images/tufastg.png");
        this.bushRight = loadImage("images/tufadrt.png");
        this.coin = loadImage("images/coin.png");
        this.brick = loadImage("images/caramida.png");
        this.playerRight = loadImage("images/player_drt.png");
        this.playerLeft = loadImage("images/player_stg.png");
        this.surprise =  loadImage("images/intrebare.png");
        this.mushroom = loadImage("images/mushroom.png");
        this.enemyLeft = loadImage("images/enemyLeft.png");
        this.emptySurprise = loadImage("images/emptySurprise.png");
        this.playerHurt = loadImage("images/player_hurt.png");
        this.enemyRight = loadImage("images/enemyRight.png");
        this.bomb = loadImage("images/bombs.png");
        this.menu = loadImage("images/menu.png");
        this.castle_ss = loadImage("images/castle_ss.png");
        this.castle_sj = loadImage("images/castle_sj.png");
        this.castle_ds = loadImage("images/castle_ds.png");
        this.castle_dj = loadImage("images/castle_dj.png");
        this.star = loadImage("images/star.png");
        this.gameOver = loadImage("images/game_over.png");
        this.levelUp = loadImage("images/level_up.png");
        this.gameCompleted = loadImage("images/game_completed.png");

    }

    public Image loadImage(String imageAddress) {
        ImageIcon icon = new ImageIcon(imageAddress);
        return icon.getImage();
    }
    public Image getSky(){ return sky;}
    public Image getGrass(){ return grass;}
    public Image getHillUpLeft(){ return hillUpLeft;}
    public Image getHillDownLeft(){ return hillDownLeft;}
    public Image getHillUpRight(){ return hillUpRight;}
    public Image getHillDownRight(){ return hillDownRight;}
    public Image getSoil(){ return soil;}
    public Image getCloudRight(){ return cloudRight;}
    public Image getCloudLeft(){ return cloudLeft;}
    public Image getBushLeft(){ return bushLeft;}
    public Image getBushRight(){ return bushRight;}
    public Image getCoin(){ return coin;}
    public Image getBrick(){ return brick;}
    public Image getPlayerRight(){ return playerRight;}
    public Image getPlayerLeft(){ return playerLeft;}
    public Image getSurprise(){ return surprise;}
    public Image getMushroom(){ return mushroom;}
    public Image getEnemyLeft(){return enemyLeft;}
    public Image getEmptySurprise(){ return emptySurprise;}
    public Image getPlayerHurt(){ return playerHurt;}
    public Image getEnemyRight(){ return enemyRight;}
    public Image getBomb(){ return bomb;}
    public Image getMenu(){ return menu;}
    public Image getCastle_ss(){ return castle_ss;};
    public Image getCastle_sj() { return castle_sj;};
    public Image getCastle_ds() {return castle_ds;};
    public Image getCastle_dj(){ return  castle_dj;};
    public Image getStar(){ return star;};
    public Image getGameOver(){return gameOver;};
    public Image getLevelUp(){ return levelUp;};
    public Image getGameCompleted(){ return gameCompleted;}

}
