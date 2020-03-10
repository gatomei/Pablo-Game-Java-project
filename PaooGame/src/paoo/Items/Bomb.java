package paoo.Items;

import paoo.Game.*;

import java.awt.*;
import java.util.ArrayList;

public class Bomb extends Block {
    public int moveX;
    public ArrayList<Enemy> enemies = Board.getEnemies();
    public Bomb(int x, int y, int moveX)
    {
        super(x,y);
        this.moveX = moveX;
        setType(BlockType.BOMB.getValue());
        image = ImageLoaderFactory.getImageLoader(Hero.getLevel()).getBomb();
        getImageDimensions();
    }
    public void move()
    {
        x += moveX;
        detectCollision();
        if (x > Map.BOARD_WIDTH - width) {
            x = Map.BOARD_WIDTH - width;
            setVisible(false);
        }
        if (x < 1) {
            x = 1;
            setVisible(false);
        }
    }
    public void update() {

        move();

    }
    public void detectCollision()
    {

        for(int i=0; i< enemies.size(); ++i)
        {
            Enemy e = enemies.get(i);
            if(this.getBounds().intersects(e.getBounds())) {
                setVisible(false);
                e.setLives(e.getLives()-1);
                if(e.getLives()<1)
                {
                    Board.increaseDeadenemy(1);
                    Board.removeEnemy(e);
                }
            }
        }
    }

}
