package paoo.Game;

public enum GameStatus{
    MENU(0), GAME_RUNNING(1), GAME_HELP(2), GAME_OVER(3), LEVEL_UP( 4), GAME_PAUSE(5), GAME_COMPLETED(6), GAME_ABOUT(7);
    private final int value;

    private GameStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static GameStatus getTypeFromInt(int value) {
        return GameStatus.values()[value];
    }
}
