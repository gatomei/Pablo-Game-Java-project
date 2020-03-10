package paoo.Items;

import paoo.Game.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Animation extends Item{
    ArrayList<Image> imagesR = new ArrayList<>();
    ArrayList<Image> imagesL = new ArrayList<>();
    public int count;
    public Image currentImage;
    public int speed;
    public int index;
    public boolean drt;

    public Animation(int x, int y) {
        super(x, y);
        imagesR.add(loadImage("images/player_drt.png"));
        imagesR.add(loadImage("images/player_walkingd.png"));
        imagesR.add(loadImage("images/player_walking1.png"));
        imagesL.add(loadImage("images/player_stg.png"));
        imagesL.add(loadImage("images/player_walkings.png"));
        imagesL.add(loadImage("images/player_walking1s.png"));
        count = 0;
        speed = 3;
    }

    public void updateAnimation() {
     if( ++index > speed)
     {
         index = 0;
         nextImage();
     }
    }
     protected Image loadImage(String imageName) {
        ImageIcon i = new ImageIcon(imageName);
        return i.getImage();
    }
   public void nextImage()
   {
       if( drt) {
           currentImage = imagesR.get(count++);
           if (count >= imagesR.size())
               count = 0;
       }
       else
       {
           currentImage = imagesL.get(count++);
           if (count >= imagesL.size())
               count = 0;
       }

   }
}
