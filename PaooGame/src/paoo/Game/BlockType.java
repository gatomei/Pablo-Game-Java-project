package paoo.Game;

public enum BlockType {
    GRASS(0), SOIL(1),SKY(2), CLOUD_R(3), CLOUD_L(4), COIN(5), HILL_UL(6), HILL_DL(7),HILL_UR(8),HILL_DR(9),BUSH_R(10),BUSH_L(11), BRICK(12),SURPRISE(13), MUSHROOM(14),EMPTY_SURPRISE(15), BOMB(16), CASTLE_SS(17), CASTLE_SJ(18), CASTLE_DS(19), CASTLE_DJ(20);
    private final int value;

    private BlockType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BlockType getTypeFromInt(int value) {
        return BlockType.values()[value];
    }
}
