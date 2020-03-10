package paoo.Game;

import java.awt.event.*;
import java.util.ArrayList;
import paoo.Items.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.*;

/*
 * Board class of the game
 */
public class Board extends JPanel implements Runnable{

    private ArrayList<Item> items = new ArrayList<>();
    public static ArrayList<Block> blocks =new ArrayList<>();
    public static ArrayList<Coin> coins = new ArrayList<>();
    public static ArrayList<Bomb> bombs = new ArrayList<>();
    private Hero monster;
    private GameStatus gs;
    private Image menu = ImageLoader.getInstance().getMenu();
    private Image bgd = ImageLoader.getInstance().getLevelUp();
    private Image cursor = Level2.getInstance().getCoin().getScaledInstance(50,50, Image.SCALE_DEFAULT);
    private Image heart = (new ImageIcon("images/heart.png")) .getImage().getScaledInstance(15,15,Image.SCALE_DEFAULT);
    private int cursorY = 225;
    private boolean isRunning = false;
    private Camera camera;
    private long myScore;
    private static int deadenemy=0;
    private KeyAdapt ka;
    public Thread thread;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public Board() {

        gs = GameStatus.getTypeFromInt(0);
        camera = new Camera(this,0);
        monster = new Hero(this, 0 * 48, 5 * 48 + 24, 1);
        myScore = 0;
        ka = new KeyAdapt(this);
        addKeyListener(ka);
        initBoard();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 70.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (isRunning && !thread.isInterrupted()) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                if (gs == GameStatus.GAME_RUNNING) {
                    monster.update();
                    if(monster.getX() >= Map.BOARD_WIDTH - 60)
                    {
                        if(Hero.getLevel()<3)
                            gs = GameStatus.LEVEL_UP;
                        else
                            gs=GameStatus.GAME_COMPLETED;
                    }
                    if(monster.getLives() <=0 || monster.getY() > 6*48)
                    {
                        gs = GameStatus.GAME_OVER;
                        monster.setLives(0);
                    }

                        for (Enemy enemy : enemies) {
                            enemy.update();
                            if (enemies.size() == 0)
                                break;
                        }
                    for (Bomb b : bombs) {
                        b.update();

                    }
                    repaint();
                }
                if(gs==GameStatus.GAME_PAUSE) {
                    try {
                        thread.sleep(2);
                        repaint();


                    } catch (InterruptedException ex) {

                    }
                }
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }

        }

    }

    /**
     * Initialize the board.
     */
    private void initBoard() {
        setFocusable(true);
        setPreferredSize(new Dimension(Map.SCREEN_WIDTH, Map.SCREEN_HEIGHT));
    }

    /**
     * Initialize blocks according to the map.
     */
    void initBlocks() {
       // int rand;
        Map.initBlocks();
        items = Map.getItems();
        blocks = Map.getBlocks();
        coins = Map.getCoins();
        int[] positions = Map.GetEnemiesPositions();
        for (int i = 0; i < positions.length; ++i) { //initiliaze the enemies with random positions->must take care that they don't intersect blocks
            enemies.add(new Enemy(this, positions[i] * 48, 6 * 48, monster.getLevel()));

        }

    }
    public static ArrayList<Coin> getCoins() {
        return coins;
    }

    public static ArrayList<Block> getBlocks() {
        return blocks;
    }

    public static ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawObjects(g);
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Draw objects on the board.
     */
    private void drawObjects(Graphics g) {
        if (gs == GameStatus.GAME_OVER) {
            isRunning = false;
            resetCamera();
            g.setColor(Color.RED);
            Font f = new Font("Serif", Font.BOLD, 40);
            g.setFont(f);
            bgd = ImageLoader.getInstance().getGameOver();
            g.drawImage(bgd, 0, 0, this);

        } else if(gs==GameStatus.GAME_COMPLETED) {
            isRunning = false;
            bgd=ImageLoader.getInstance().getGameCompleted();
            g.drawImage(bgd,0,0,this);
            g.setFont(new Font("Times New Roman", Font.ITALIC, 40));
            if(Settings.GetHighScore(Hero.getLevel())<myScore)
            {
                Settings.SetHighScore(Hero.getLevel(),(int)myScore);
                g.drawString("New Highscore:"+myScore,350,300);
            }else
                g.drawString("Score: "+myScore, 350, 300);
        }
        else
         if (gs == GameStatus.LEVEL_UP) {
            if(isRunning)
                isRunning = false;
             resetCamera();
             g.setColor(Color.RED);
             bgd = ImageLoader.getInstance().getLevelUp();
             g.drawImage(bgd, 0, 0, this);
             g.setFont(new Font("Times New Roman", Font.ITALIC, 40));
             g.drawString("Coins colected: " + monster.getGainedCoins(), 350,250);
            if(Settings.GetHighScore(Hero.getLevel())<myScore)
            {
                Settings.SetHighScore(Hero.getLevel(),(int)myScore);
                g.drawString("New Highscore:"+myScore,350,300);
            }else
                g.drawString("Score: "+myScore, 350, 300);
            monster.setLevel(monster.getLevel()+1); //=--------------------------------------------


        } else if (gs == GameStatus.GAME_RUNNING||gs==GameStatus.GAME_PAUSE) {
            for (Item a : items) {
                if (a.isVisible() && a.x - camera.getxOffset() >= -48 && a.x - camera.getxOffset() <= Map.SCREEN_WIDTH) {
                    g.drawImage(a.getImage(), a.getX() - camera.getxOffset(), a.getY(), this);
                }
            }
            for (Coin c : coins) {
                if (c.isVisible() && c.x - camera.getxOffset() >= -48 && c.x - camera.getxOffset() <= Map.SCREEN_WIDTH) {
                    g.drawImage(c.getImage(), c.getX() - camera.getxOffset(), c.getY(), this);
                }
            }
            for (Block b : blocks) {
                if (b.isVisible() && b.x - camera.getxOffset() >= -48 && b.x - camera.getxOffset() <= Map.SCREEN_WIDTH) {
                    g.drawImage(b.getImage(), b.getX() - camera.getxOffset(), b.getY(), this);

                }
            }
            for (Enemy e : enemies) {
                if (e.isVisible()) {
                    if (e.isDead()) {
                        g.drawImage(e.getImage(), e.getX() - camera.getxOffset(), e.getY() + 24, this);
                        enemies.remove(e);
                    } else
                        g.drawImage(e.getImage(), e.getX() - camera.getxOffset(), e.getY(), this);
                } else {
                    enemies.remove(e);
                }
                if (enemies.size() == 0)
                    break;

            }
            for (int i = 0; i < bombs.size(); ++i) {
                Bomb bomb = bombs.get(i);
                if (bomb.isVisible()) {
                    g.drawImage(bomb.getImage(), bomb.getX() - camera.getxOffset(), bomb.getY(), this);
                } else {
                    bombs.remove(bomb);
                    if (bombs.size() == 0)
                        break;

                }

            }
            if (monster.isVisible())
                g.drawImage(monster.getImage(), monster.getX() - camera.getxOffset(), monster.getY(), this);
            g.setColor(Color.orange);
            g.setFont(new Font("Times New Roman", Font.ITALIC, 15));
            g.drawString("Score: " + myScore, 30, 30);
            g.drawString("Level: " + (monster.getLevel()), 30, 45);
            g.drawString("Load: " + monster.load, 30, 60);
            g.drawString("Coins: " + monster.getGainedCoins(), 30, 75);
            g.drawString(""+monster.getLives(),30,90);
            g.drawImage(heart,40,78,this);
            if(monster.imunity>0)
            {
                monster.imunity -=1;
                if(monster.imunity == 0)
                    monster.image=ImageLoaderFactory.getImageLoader(Hero.getLevel()).getPlayerRight();
            }
            if(gs==GameStatus.GAME_PAUSE)
            {
                Font f = new Font("Arial", Font.BOLD, 60);
                g.setFont(f);
                g.setColor(Color.black);
                g.drawString("PAUSED",400,256);
            }
        }
        else if(gs == GameStatus.MENU){
            g.drawImage(menu,0,0, Map.SCREEN_WIDTH,Map.SCREEN_HEIGHT,this);;
            Font f = new Font("Arial", Font.BOLD, 30);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("PLAY", 510, 260);
            g.drawString("HELP" + "", 510,300);
            g.drawString("ABOUT" + "", 510,340);
            g.drawImage(cursor,460,cursorY,this);
            isRunning = false;
        }

        else if(gs==GameStatus.GAME_HELP)
            {
            g.drawImage(menu, 0, 0, Map.SCREEN_WIDTH, Map.SCREEN_HEIGHT, this);
            Font f = new Font("Arial", Font.BOLD, 40);
            g.setFont(f);
            g.setColor(Color.BLACK);
            f = new Font("Times New Roman", Font.CENTER_BASELINE, 25);
            g.setFont(f);
            g.drawString("GO BACK TO MENU", 210, 240);
            g.drawImage(cursor, 160, 205, this);
            g.setColor(Color.white);
            g.drawString("PLAYER CONTROL: ", 530, 200);
            f = new Font("Times New Roman", Font.CENTER_BASELINE, 16);
            g.setFont(f);
            g.setColor(Color.ORANGE);
            g.drawString("1.PRESS ESC = PAUSE",550,220);
            g.drawString("2.PRESS ENTER = FIRE", 550, 250);
            g.drawString("3.PRESS SPACE = JUMP", 550, 280);
            g.drawString("4.PRESS -> = MOVE RIGHT", 550, 310);
            g.drawString("5.PRESS <- = MOVE LEFT", 550, 340);
        }
        else
         {
             g.drawImage(menu, 0, 0, Map.SCREEN_WIDTH, Map.SCREEN_HEIGHT, this);
             Font f = new Font("Arial", Font.BOLD, 40);
             g.setFont(f);
             g.setColor(Color.BLACK);
             f = new Font("Times New Roman", Font.CENTER_BASELINE, 25);
             g.setFont(f);
             g.drawString("GO BACK TO MENU", 310, 230);
             g.drawImage(cursor, 260, 195, this);
             g.setColor(Color.WHITE);
             g.drawString("High Score Rank:",260,280);
             g.drawString("Level1: "+Settings.GetHighScore(1),460,280);
             g.drawString("Level2: "+Settings.GetHighScore(2),460,315);
             g.drawString("Level3: "+Settings.GetHighScore(3),460,350);
         }
    }

    public synchronized void start()
    {
        if (isRunning)
           return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
        resetBoard();
        initBlocks();
        gs = GameStatus.GAME_RUNNING;
    }
    public static void removeCoin(Coin c) {
        coins.remove(c);
    }

    public static void addCoin(Coin c) {
        coins.add(c);
    }

    public static void removeEnemy(Enemy e) {
        enemies.remove(e);
    }

    public static void removeBlock(Block b) {
        blocks.remove(b);
    }

    public static void addBlock(Block b) {
        blocks.add(b);
    }

    public Camera getCamera() {
        return camera;
    }

    public void setMyScore(long score) {
        myScore += score;
        myScore +=deadenemy*100;
        deadenemy = 0;
    }
    public void resetCamera()
    {
        camera.setxOffset(0);
    }
    public void resetBoard()
    {

        monster.load = 2;
        if(monster.getLives()<0||  gs==GameStatus.GAME_OVER)
        {
            monster.setLives(1);
            monster.setGainedCoins(0);
            myScore = 0;
            Hero.setLevel(1);
        }
        monster.y = 5*48 + 24;
        monster.x = 0;
        monster.setDx(0);
        monster.setDy(0);
        monster.image = ImageLoader.getInstance().getPlayerRight();
        blocks.removeAll(blocks);
        items.removeAll(items);
        coins.removeAll(coins);
        enemies.removeAll(enemies);
        resetCamera();

    }

    public void keyPressed(KeyEvent e) {
            if(gs == GameStatus.GAME_RUNNING )
            {
                monster.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    gs = GameStatus.GAME_PAUSE;
            }
            else if(gs==GameStatus.GAME_PAUSE) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    gs = GameStatus.GAME_RUNNING;
            }
            else {

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (!isRunning && cursorY < 305)
                        cursorY += 40;
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (!isRunning && cursorY > 225)
                        cursorY -= 40;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (gs == GameStatus.MENU) {
                        if (cursorY == 225) {
                            start();
                        } else if (cursorY == 265)
                            gs = GameStatus.GAME_HELP;
                        else
                            gs=GameStatus.GAME_ABOUT;
                    } else if (gs == GameStatus.GAME_HELP|| gs==GameStatus.GAME_ABOUT) {
                        gs = GameStatus.MENU;
                        resetBoard();
                    } else {
                        if (gs == GameStatus.GAME_OVER) {
                            resetBoard();
                            gs = GameStatus.MENU;


                        }
                        else if(gs==GameStatus.LEVEL_UP)
                        {
                            start();
                        }
                        else if(gs==GameStatus.GAME_COMPLETED)
                        {
                            resetBoard();
                            gs = GameStatus.MENU;
                        }


                    }

                }

                repaint();
            }
    }



    public void keyReleased(KeyEvent e)
    {
        if(gs == GameStatus.GAME_RUNNING)
            monster.keyReleased(e);

    }
    public static void increaseDeadenemy(int x)
    {
        deadenemy +=x;
    }
}
