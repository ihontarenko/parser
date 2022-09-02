package pro.javadev.json.lexer;

import pro.javadev.common.lexer.AbstractLexer;
import pro.javadev.common.token.Token;

import java.util.List;

public class JSONLexer extends AbstractLexer {

    public JSONLexer(List<Token.Entry> entries) {
        super(entries);
    }

}
