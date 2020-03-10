package paoo.Items;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import paoo.Game.*;

public class Hero extends GameObject {
    public int load;
    public static int Level = 1;
    public static int imunity = 0;
    public boolean moving;
    private Animation animation;
    private int gainedCoins =0;



    public Hero(Board board, int x, int y, int lives) {
        super(board,x, y, lives);
        super.image = ImageLoader.getInstance().getPlayerRight();
        getImageDimensions();
        this.width = 48 * 1;
        this.height = 48 * 1 + 24;
        animation = new Animation(x, y);
        load = 2;
        setCanJump(true);
        setFalling(false);
        setJumpHeight( 20);
        setMaxFalling(4);
        setGravity(1);
        setDead(false);

    }

    public void update() {

        move();

        fall();
        getBoard().getCamera().centerOnEntity(this);
        if (getDx() != 0 )
            moving = true;
        else
            moving = false;
        if (moving) {
            animation.updateAnimation();
            image = animation.currentImage;
        }


    }
    public void CoinsCollision()
    {
        Rectangle theMonster = new Rectangle(x + getDx(), y + getDy(), width, height);
        ArrayList<Coin> tempCoins = getBoard().getCoins();
        for (int i = 0; i < tempCoins.size(); ++i) {
            if (theMonster.intersects(tempCoins.get(i).getBounds())) {

                if(tempCoins.get(i).image == ImageLoaderFactory.getImageLoader(Hero.getLevel()).getMushroom())
                {
                    load += 3;
                }
                else if(tempCoins.get(i).image == ImageLoaderFactory.getImageLoader(Hero.getLevel()).getStar())
                {
                    upLives();
                    getBoard().setMyScore(30);
                }
                else
                {
                    getBoard().setMyScore(50);
                    gainedCoins += 1;
                }
                getBoard().removeCoin(tempCoins.get(i));
            }
        }

    }
    public int getGainedCoins()
    {
        return gainedCoins;
    }

    public void move() {

        CoinsCollision();
        if (!hasHorizontalCollision())
            x += getDx();
        if (!hasVerticalCollision())
            y += getDy();
        if (x > getBOARD_WIDTH() - width) {
            x = getBOARD_WIDTH() - width;
        }

        if (y > getBOARD_HEIGHT() - height) {
            y = getBOARD_HEIGHT() - height;
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

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
            if(!isDead()) {
                if (key == KeyEvent.VK_SPACE) {
                    setCanJump(true);
                    setFalling(false);
                    jump();

                } else if (key == KeyEvent.VK_LEFT) {
                    setDx(-5);
                    setDy(0);
                    image = ImageLoaderFactory.getImageLoader(Hero.getLevel()).getPlayerLeft();
                    animation.drt = false;

                } else if (key == KeyEvent.VK_RIGHT) {
                    setDx(5);
                    setDy(0);
                    image = ImageLoaderFactory.getImageLoader(Hero.getLevel()).getPlayerRight();
                    animation.drt = true;


                } else if (key == KeyEvent.VK_ENTER) {
                    if (load > 0) {
                        int side;      //establish the side the bomb is going to move to
                        if (getImage() == ImageLoaderFactory.getImageLoader(Hero.getLevel()).getPlayerRight())
                            side = 3;
                        else
                            side = -3;
                        Board.bombs.add(new Bomb(x + 30, y + 24, side));

                        load -= 1;
                    }
                }
            }
            else{
                image = ImageLoaderFactory.getImageLoader(Hero.getLevel()).getPlayerRight();
            }

    }

    public void keyReleased(KeyEvent e) {
        if(!isDead()) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                setDx(0);
                image = ImageLoaderFactory.getImageLoader(Hero.getLevel()).getPlayerLeft();
            }

            if (key == KeyEvent.VK_RIGHT) {
                setDx(0);
                image = ImageLoaderFactory.getImageLoader(Hero.getLevel()).getPlayerRight();

            }
            if (key == KeyEvent.VK_SPACE) {
                setDy(0);
            }

        }
        else{
            image = ImageLoaderFactory.getImageLoader(Hero.getLevel()).getPlayerRight();
        }
    }


    public static int getLevel() {
        return Level;
    }
    public static void setLevel(int x){Level = x;}


    public boolean hasVerticalCollision() {
        Rectangle theMonster = new Rectangle(x + getDx(), y + getDy(), width, height);
        ArrayList<Block> tempBlocks = Board.getBlocks();
        for (int i = 0; i < tempBlocks.size(); ++i) {
            Block b = tempBlocks.get(i);

            if (theMonster.intersects(b.getTop()) && getDy() > 0) {
                setCanJump(true);
                setFalling(false);
                setDy(0);
                return true;
            } else
               setFalling(true);

            if (theMonster.intersects(b.getBottom()) && getDy() < 0) {
                y = b.getBounds().y + 48;
               setFalling(true);
                setDy(0);
                if (b.getType() == BlockType.SURPRISE.getValue()) {
                    int type = (int)(Math.random()*5);
                    Board.addCoin(SurpriseFactory.getSurprise(b.getX(),b.getY()-48,type));
                    Board.removeBlock(b);
                    Board.addBlock(new EmptySurprise(b.getX(), b.getY()));
                }

                return true;
            }
        }

        return false;
    }

    public boolean hasHorizontalCollision() {
        Rectangle theMonster = new Rectangle(x + getDx(), y + getDy(), width, height);
        ArrayList<Block> tempBlocks = Board.getBlocks();
        ArrayList<Enemy> tempEnemy = Board.getEnemies();
        for (int i = 0; i < tempBlocks.size(); ++i) {
            Block b = tempBlocks.get(i);

            if (theMonster.intersects(b.getRight()) && getDx()< 0) {
                setDx(0);
                return true;
            }
            if (theMonster.intersects(b.getLeft()) && getDx() > 0) {
                setDx(0);
                return true;
            }

        }
        for (int i = 0; i < tempEnemy.size(); ++i) {
            if (theMonster.intersects(tempEnemy.get(i).getBounds()) ){
                if(getLives()>0) {
                    if(imunity==0)
                    {
                        lowerLives();
                        this.image = ImageLoaderFactory.getImageLoader(Hero.getLevel()).getPlayerHurt();
                        imunity = 50;
                    }
                    setDx(0);
                }
                else{
                    setDead(true);
                }
            }


        }
        return false;
    }
    public void setGainedCoins(int x)
    {
        gainedCoins = x;
    }

}

