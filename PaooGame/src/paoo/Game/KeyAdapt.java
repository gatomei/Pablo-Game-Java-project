package paoo.Game;

import paoo.Game.Board;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyAdapt extends KeyAdapter {


    Board board;

    public KeyAdapt(Board b)
    {
        this.board = b;
    }
    public void keyPressed(KeyEvent e)
    {
        board.keyPressed(e);
    }
    public void keyReleased(KeyEvent e)
    {
        board.keyReleased(e);
    }
}
