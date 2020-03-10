package paoo.Items;

import paoo.Game.ImageLoader;
import paoo.Game.ImageLoaderFactory;

public class Soil extends Item {
    public Soil(int x, int y,int poz) {
        super(x, y);

        image= ImageLoaderFactory.getImageLoader(Hero.getLevel()).getSoil();

        getImageDimensions();
    }
}
