package paoo.Game;

public class ImageLoaderFactory {

    public static ImageLoader getImageLoader(int level)
    {
        switch(level)
        {
            case 1:
                return Level1.getInstance();
            case 2:
                    return Level2.getInstance();
                    default:
                        return Level3.getInstance();
        }
    }
}
