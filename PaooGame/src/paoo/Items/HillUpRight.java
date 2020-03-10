package paoo.Items;

import paoo.Game.ImageLoader;
import paoo.Game.ImageLoaderFactory;

public class HillUpRight extends Item {
    public HillUpRight(int x, int y) {
        super(x, y);

        image= ImageLoaderFactory.getImageLoader(Hero.getLevel()).getHillUpRight();
        getImageDimensions();
    }
}
