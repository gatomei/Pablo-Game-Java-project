package paoo.Items;

import paoo.Game.BlockType;
import paoo.Game.ImageLoader;
import paoo.Game.ImageLoaderFactory;

public class Surprise extends Block {
    public Surprise(int x, int y) {
        super(x, y);
        setType(BlockType.SURPRISE.getValue());
        image= ImageLoaderFactory.getImageLoader(Hero.getLevel()).getSurprise();
        getImageDimensions();
    }
}
