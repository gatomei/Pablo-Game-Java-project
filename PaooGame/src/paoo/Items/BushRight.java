package paoo.Items;

import paoo.Game.ImageLoader;

public class BushRight extends Block{
    public BushRight(int x, int y) {
        super(x, y);
        //loadImage("images/tree.png");
        image= ImageLoader.getInstance().getBushRight();
        getImageDimensions();
        setType(5);
        setHealth(1);
    }
}
