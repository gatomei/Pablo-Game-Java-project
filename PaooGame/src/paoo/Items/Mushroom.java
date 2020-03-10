package paoo.Items;


import paoo.Game.ImageLoader;

public class Mushroom extends Coin {
    public Mushroom (int x, int y) {
        super(x, y);
        image= ImageLoader.getInstance().getMushroom();
        getImageDimensions();
    }
}
