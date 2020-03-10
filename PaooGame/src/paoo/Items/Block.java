package paoo.Items;

import java.awt.*;

public class Block extends Item{
    public int health = 1;
    private int type;

    public Block(int x, int y) {
        super(x, y);
    }

    public void lowerHealth() {
        health -= 1;
    }

    public int currentHealth() {
        return health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void updateAnimation() {

    }

    public Rectangle getTop()
    {
        return new Rectangle(x+3, y, width-3, 5);
    }
    public Rectangle getBottom()
    {
        return new Rectangle(x+2, y+height - 6, width-2, height);
    }
    public Rectangle getRight()
    {
        return new Rectangle(x + width-4, y+6, 4, height - 6);
    }
    public Rectangle getLeft()
    {
        return new Rectangle(x, y + 6, 5, height - 6);
    }
}
