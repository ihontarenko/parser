package pro.javadev.gc.token;

import pro.javadev.common.token.Token;

public enum GCodeToken implements Token {

    T_GCODE_COMMAND(20000),
    T_GCODE_KEY(21000),
    T_GCODE_VALUE(21500),
    T_GCODE_EOL(22000),
    T_GCODE_COMMENT_TEXT(29000);

    private final int      type;
    private final String[] values;

    GCodeToken(final int type) {
        this(type, new String[0]);
    }

    GCodeToken(final int type, final String... values) {
        this.type = type;
        this.values = values;
    }

    @Override
    public int type() {
        return type;
    }

    @Override
    public String[] expressions() {
        return values;
    }

    @Override
    public GCodeToken[] tokens() {
        return values();
    }

}
