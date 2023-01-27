package pro.javadev.gc;

import pro.javadev.common.token.DefaultTokenizer;
import pro.javadev.common.token.Token;
import pro.javadev.gc.token.GCodeToken;

public class GCodeTokenizer extends DefaultTokenizer {

    @Override
    public Token.Entry entry(Token token, String value, int position, int ordinal) {
        Token.Entry entry    = super.entry(token, value, position, ordinal);
        String      newValue = entry.value();

        if (entry.is(GCodeToken.T_GCODE_EOL)) {
            newValue = "[EOL]";
        }

        return Token.Entry.of(entry.token(), newValue, entry.position(), entry.ordinal());
    }

}
