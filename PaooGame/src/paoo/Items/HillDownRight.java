package paoo.Items;

import paoo.Game.ImageLoader;
import paoo.Game.ImageLoaderFactory;

public class HillDownRight extends Item {
    public HillDownRight(int x, int y) {
        super(x, y);
        image= ImageLoaderFactory.getImageLoader(Hero.getLevel()).getHillDownRight();
        getImageDimensions();
    }
}
