package paoo.Items;

import paoo.Game.ImageLoader;
import paoo.Game.ImageLoaderFactory;

public class Cloud_R extends Item {
    public Cloud_R(int x, int y) {
        super(x, y);
        image= ImageLoaderFactory.getImageLoader(Hero.getLevel()).getCloudRight();
        getImageDimensions();
    }
}
