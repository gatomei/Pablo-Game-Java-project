package paoo.Items;


import paoo.Game.ImageLoader;

public class Star extends Coin {
    public Star (int x, int y) {
        super(x, y);
        image= ImageLoader.getInstance().getStar();
        getImageDimensions();
    }
}
