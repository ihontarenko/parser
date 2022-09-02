package pro.javadev.json.token;

import pro.javadev.common.token.Token;

public enum JSONExpressionToken implements Token {

    T_AT_REF(7700, "@ref");

    private final int      type;
    private final String[] values;

    JSONExpressionToken(final int type) {
        this(type, new String[0]);
    }

    JSONExpressionToken(final int type, final String... values) {
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
    public Token[] tokens() {
        return values();
    }

}
