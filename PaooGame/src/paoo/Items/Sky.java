package paoo.Items;

import paoo.Game.ImageLoader;
import paoo.Game.ImageLoaderFactory;

public class Sky extends Item {
    public Sky(int x, int y) {
        super(x, y);

        image= ImageLoaderFactory.getImageLoader(Hero.getLevel()).getSky();
        getImageDimensions();
    }
}
