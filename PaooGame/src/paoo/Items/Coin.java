package paoo.Items;

import paoo.Game.ImageLoader;
import paoo.Game.ImageLoaderFactory;

public class Coin extends Item {
    public Coin (int x, int y) {
        super(x, y);
        image= ImageLoaderFactory.getImageLoader(Hero.getLevel()).getCoin();
        getImageDimensions();
    }
}
