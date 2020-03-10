package paoo.Items;

import paoo.Game.ImageLoader;
import paoo.Game.ImageLoaderFactory;

public class HillUpLeft extends Item {
    public HillUpLeft(int x, int y) {
        super(x, y);
        image= ImageLoaderFactory.getImageLoader(Hero.getLevel()).getHillUpLeft();
        getImageDimensions();
    }
}
