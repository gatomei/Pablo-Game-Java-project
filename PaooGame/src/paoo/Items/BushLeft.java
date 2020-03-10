package paoo.Items;

import paoo.Game.ImageLoader;

public class BushLeft extends Block{
    public BushLeft(int x, int y) {
        super(x, y);
        //loadImage("images/tree.png");
        image= ImageLoader.getInstance().getBushLeft();
        getImageDimensions();
        setType(5);
        setHealth(1);
    }
}
