package paoo.Items;

import paoo.Game.BlockType;
import paoo.Game.Board;
import paoo.Game.ImageLoader;
import paoo.Game.Map;
import java.awt.*;

public class GameObject extends Item {
    private final int BOARD_WIDTH = Map.BOARD_WIDTH;
    private final int BOARD_HEIGHT = Map.BOARD_HEIGHT;
    private int dx;
    private int dy;
    private Board board;
    private int lives;
    private boolean canJump; // daca se afla pe sol obiectul Canjump= true
    private boolean falling;        // daca obiectul e in cadere falling = true
    private int gravity ;
    private int maxFalling ;
    private int jumpHeight ;
    private boolean dead;

    public void upLives() {
        this.lives += 1;
    }
    public boolean isDead(){ return dead;}
    public void setDead(boolean d){ dead=d;}


    public int getLives() {
        return this.lives;
    }

    public void lowerLives() {
        this.lives -= 1;
    }


    public GameObject(Board board, int x, int y, int lives) {
        super(x, y);
        this.board = board;
        image = ImageLoader.getInstance().getPlayerRight();
        getImageDimensions();
        this.width = 48 * 1;
        this.height = 48 * 1 + 24;
        this.lives = lives;
    }

    public void update() {

        move();
        fall();


    }

    public void move() {


            x += dx;
            y += dy;
        if (x > BOARD_WIDTH - width) {
            x = BOARD_WIDTH - width;
        }

        if (y > BOARD_HEIGHT - height) {
            y = BOARD_HEIGHT - height;
        }
        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
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


    public void fall() {
        if (falling) {
            dy += gravity;

            if (dy > maxFalling)
            {
                dy = maxFalling;
            }


        }

    }

    public void jump() {
        if (canJump) {
            dy -= jumpHeight;
            canJump = false;
        }

    }
    public void setJumpHeight(int height)
    {
        jumpHeight = height;
    }
    public void setMaxFalling(int fall)
    {
        maxFalling = fall;
    }
    public void setCanJump(boolean a)
    {
        canJump = a;
    }
    public void setFalling(boolean a)
    {
        falling = a;
    }
    public void setGravity(int a)
    {
        gravity = a;
    }
    public int getGravity()
    {
        return gravity;
    }
    public int getMaxFalling()
    {
        return maxFalling;
    }
    public boolean getFalling()
    {
        return falling;
    }
    public boolean getCanJump()
    {
        return canJump;
    }
    public Board getBoard()
    {
        return board;
    }
    public int getDx()
    {
        return dx;
    }
    public int getDy()
    {
        return dy;
    }
    public int getBOARD_WIDTH()
    {
        return BOARD_WIDTH;
    }
    public int getBOARD_HEIGHT()
    {
        return BOARD_HEIGHT;
    }
    public void setDx(int Dx)
    {
        dx = Dx;
    }
    public void setDy(int Dy)
    {
        dy = Dy;
    }
    public void setLives(int x)
    {
        lives = x;
    }




}

