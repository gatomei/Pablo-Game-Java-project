package paoo.Game;

import paoo.Items.Hero;

public class Camera {
    private int xOffset;
    private Board board;

    public Camera(Board board, int xOffset)
    {
        this.xOffset = xOffset;
        this.board = board;
    }

    public void move(int xAmt)
    {
        xOffset += xAmt;
        checkBlankSpace();
    }
    public int getxOffset()
    {
        return xOffset;
    }
    public void setxOffset(int xOffset)
    {
        this.xOffset = xOffset;
    }

    public void centerOnEntity(Hero monster)
    {
        xOffset = monster.getX() - board.getWidth()/2 +(int) monster.getBounds().getWidth()/2;
        checkBlankSpace();
    }

    public void checkBlankSpace()
    {
        if( xOffset < 0)
            xOffset = 0;
        if( xOffset > Map.BOARD_WIDTH - Map.SCREEN_WIDTH)
            xOffset = Map.BOARD_WIDTH - Map.SCREEN_WIDTH;
    }
}
