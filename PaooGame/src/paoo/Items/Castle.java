package paoo.Items;

import paoo.Game.ImageLoader;

public class Castle extends Item {
    public Castle(int x, int y) {
        super(x, y);
        image = ImageLoader.getInstance().getCastle_ss();
        getImageDimensions();
    }
}
