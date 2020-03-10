package paoo.Items;

import paoo.Game.ImageLoader;
import paoo.Game.ImageLoaderFactory;

public class HillDownLeft extends Item {
    public HillDownLeft(int x, int y) {
        super(x, y);
        image= ImageLoaderFactory.getImageLoader(Hero.getLevel()).getHillDownLeft();
        getImageDimensions();
    }
}
