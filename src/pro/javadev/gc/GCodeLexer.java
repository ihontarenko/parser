package pro.javadev.gc;

import pro.javadev.common.lexer.AbstractLexer;
import pro.javadev.common.token.Token;

import java.util.List;

public class GCodeLexer extends AbstractLexer {

    public GCodeLexer(List<Token.Entry> entries) {
        super(entries);
    }

}
