package paoo.Game;

import paoo.Items.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    public static final int SCREEN_WIDTH = 48 * 20;
    public static final int SCREEN_HEIGHT = 48 * 9;
    public static final int BOARD_WIDTH = 48 * 120;
    public static final int BOARD_HEIGHT = 48 * 9;
    private static ArrayList<Block> blocks = new ArrayList<>();
    private static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<Coin> coins = new ArrayList<>();
    private static File f;
    private static String path;
   final static int[][] level = new int[9][120];
   private static Settings settings;
   private static int[] enemiesPositions;

    public static void initializeMap(int level) {
        settings=new Settings(level);
        try {
            f = new File(path );
            Scanner br = new Scanner(new FileReader(f));
            int i=0,j=0;
            while(br.hasNextInt())
            {
                Map.level[i][j]=br.nextInt();
                ++j;
                if(j==120)
                {
                    j=0;
                    ++i;
                }
                if(i==9)
                break;
            }

            if(br.hasNextInt()) {
                enemiesPositions = new int[br.nextInt()];
                i = 0;
                while (br.hasNextInt()) {
                    enemiesPositions[i] = br.nextInt();
                    ++i;
                }
            }

        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public static void initBlocks() {
        initializeMap(Hero.getLevel());
        int type;
        Castle castle;
        for (int x = 0; x < Map.level.length; x++) {
            for (int y = 0; y < Map.level[0].length; y++) {
                type = Map.level[x][y];
                BlockType bType = BlockType.getTypeFromInt(type);
                switch (bType) {
                    case GRASS:
                        blocks.add(new Grass(y * 48, x * 48));
                        break;
                    case SOIL:
                        items.add(new Soil(y * 48, x * 48, 0));
                        break;
                    case SKY:
                        items.add(new Sky(y * 48, x * 48));
                        break;
                    case CLOUD_L:
                        items.add(new Sky(y * 48, x * 48));
                        items.add(new Cloud_L(y * 48, x * 48));
                        break;
                    case CLOUD_R:
                        items.add(new Sky(y * 48, x * 48));
                        items.add(new Cloud_R(y * 48, x * 48));
                        break;
                    case COIN:
                        items.add(new Sky(y * 48, x * 48));
                        coins.add(new Coin(y * 48, x * 48));
                        break;
                    case HILL_UL:
                        items.add(new Sky(y * 48, x * 48));
                        items.add(new HillUpLeft(y * 48, x * 48));
                        break;
                    case HILL_DL:
                        items.add(new Sky(y * 48, x * 48));
                        items.add(new HillDownLeft(y * 48, x * 48));
                        break;
                    case HILL_UR:
                        items.add(new Sky(y * 48, x * 48));
                        items.add(new HillUpRight(y * 48, x * 48));
                        break;
                    case HILL_DR:
                        items.add(new Sky(y * 48, x * 48));
                        items.add(new HillDownRight(y * 48, x * 48));
                        break;
                    case BUSH_R:
                        items.add(new Sky(y * 48, x * 48));
                        items.add(new BushRight(y * 48, x * 48));
                        break;
                    case BUSH_L:
                        items.add(new Sky(y * 48, x * 48));
                        items.add(new BushLeft(y * 48, x * 48));
                        break;
                    case BRICK:
                        items.add(new Sky(y * 48, x * 48));
                        blocks.add(new Brick(y * 48, x * 48));
                        break;
                    case SURPRISE:
                        items.add(new Sky(y * 48, x * 48));
                        blocks.add(new Surprise(y * 48, x * 48));
                        break;
                    case CASTLE_SS:
                        items.add(new Sky(y * 48, x * 48));
                        items.add(new Castle(y * 48, x * 48));
                        break;
                    case CASTLE_SJ:
                        items.add(new Sky(y * 48, x * 48));
                        castle = new Castle(y * 48, x * 48);
                        castle.image = ImageLoader.getInstance().getCastle_sj();
                        items.add(castle);
                        break;
                    case CASTLE_DS:
                        items.add(new Sky(y * 48, x * 48));
                        castle = new Castle(y * 48, x * 48);
                        castle.image = ImageLoader.getInstance().getCastle_ds();
                        items.add(castle);
                        break;
                    case CASTLE_DJ:
                        items.add(new Sky(y * 48, x * 48));
                        castle = new Castle(y * 48, x * 48);
                        castle.image = ImageLoader.getInstance().getCastle_dj();
                        items.add(castle);
                        break;
                    default:
                        break;
                }
            }
        }

    }
    public static ArrayList<Block> getBlocks(){
        return blocks;
    }
    public static ArrayList<Item> getItems(){
        return items;
    }
    public static ArrayList<Coin> getCoins()
    {
        return coins;
    }
    public static void setPath(String s)
    {
        path=s;
    }
    public static int[] GetEnemiesPositions()
    {
        return enemiesPositions;
    }
}
