package paoo.Items;

import paoo.Game.*;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends GameObject {

    public Enemy(Board board, int x,int y, int lives)
    {
        super(board,x, y, lives);
        image = ImageLoaderFactory.getImageLoader(Hero.getLevel()).getEnemyLeft();
        getImageDimensions();
        this.width = 48  ;
        this.height = 48 ;
        setCanJump(true);
        setGravity(1);
        setFalling(false);
        setDx(-1);
        setDy(0);
        setJumpHeight(0);
        setMaxFalling(10);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
    public void move()
    {
        if(!hasHorizontalCollision())
            x += getDx();
        if(!hasVerticalCollision())
            y += getDy();
        if (x > Map.BOARD_WIDTH - width) {
            x = Map.BOARD_WIDTH - width;
            setDx(-getDx());
            image =  ImageLoaderFactory.getImageLoader(Hero.getLevel()).getEnemyLeft();
        }
        if (x < 1) {
            x = 1;
            setDx(-getDx());
            image =  ImageLoaderFactory.getImageLoader(Hero.getLevel()).getEnemyRight();
        }
    }
    public void update() {

        move();
        fall();
    }
    public boolean hasHorizontalCollision() {
        Rectangle theEnemy = new Rectangle(x + getDx(), y + getDy(), width, height);
        ArrayList<Block> tempBlocks = getBoard().getBlocks();
        for (int i = 0; i < tempBlocks.size(); ++i) {
            Block b = tempBlocks.get(i);

            if (theEnemy.intersects(b.getRight()) && getDx()< 0) {
                setDx(-getDx());
                image =  ImageLoaderFactory.getImageLoader(Hero.getLevel()).getEnemyRight();
                return true;
            }
            if (theEnemy.intersects(b.getLeft()) && getDx() > 0) {
                setDx(-getDx());
                image = ImageLoaderFactory.getImageLoader(Hero.getLevel()).getEnemyLeft();
                return true;
            }

        }

        return false;
    }
    public boolean hasVerticalCollision() {
        Rectangle theEnemy = new Rectangle(x + getDx(), y + getDy(), width, height);
        ArrayList<Block> tempBlocks = getBoard().getBlocks();
        for (int i = 0; i < tempBlocks.size(); ++i) {
            Block b = tempBlocks.get(i);

            if (theEnemy.intersects(b.getTop()) && getDy() > 0) {
                setCanJump(true);
                setFalling(false);
                setDy(0);
                return true;
            } else
                setFalling(true);

        }

        return false;
    }

}
