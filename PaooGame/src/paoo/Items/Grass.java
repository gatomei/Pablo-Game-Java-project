package paoo.Items;

import paoo.Game.BlockType;
import paoo.Game.ImageLoader;
import paoo.Game.ImageLoaderFactory;

public class Grass extends Block{
    public Grass(int x, int y) {
        super(x, y);
        setType(BlockType.GRASS.getValue());
        image= ImageLoaderFactory.getImageLoader(Hero.getLevel()).getGrass();
        getImageDimensions();
    }
}
