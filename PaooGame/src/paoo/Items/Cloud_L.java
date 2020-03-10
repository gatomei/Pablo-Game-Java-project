package paoo.Items;

import paoo.Game.ImageLoader;
import paoo.Game.ImageLoaderFactory;

public class Cloud_L extends Item {
    public Cloud_L(int x, int y) {
        super(x, y);
        image= ImageLoaderFactory.getImageLoader(Hero.getLevel()).getCloudLeft();
        getImageDimensions();
    }
}
