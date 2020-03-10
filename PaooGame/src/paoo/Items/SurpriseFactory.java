package paoo.Items;

public class SurpriseFactory {
    public static Coin getSurprise(int x,int y,int type)
    {
        if(type <3)
            return new Mushroom(x, y);
        else
            if(type<5)
                return new Star(x, y - 28);
            else
                return new Coin(x,y-30);
    }
}
