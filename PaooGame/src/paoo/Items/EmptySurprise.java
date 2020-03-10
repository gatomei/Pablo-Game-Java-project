package paoo.Items;

import paoo.Game.BlockType;
import paoo.Game.ImageLoader;
import paoo.Game.ImageLoaderFactory;

public class EmptySurprise extends Block {
    public EmptySurprise(int x, int y) {
        super(x, y);
        setType(BlockType.EMPTY_SURPRISE.getValue());
        image= ImageLoaderFactory.getImageLoader(Hero.getLevel()).getEmptySurprise();
        getImageDimensions();
    }
}
