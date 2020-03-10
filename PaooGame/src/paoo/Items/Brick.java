package paoo.Items;

import paoo.Game.BlockType;
import paoo.Game.ImageLoader;
import paoo.Game.ImageLoaderFactory;

public class Brick extends Block {
    public Brick(int x, int y) {
        super(x, y);
        setType(BlockType.BRICK.getValue());
        image= ImageLoaderFactory.getImageLoader(Hero.getLevel()).getBrick();
        getImageDimensions();
    }
}
