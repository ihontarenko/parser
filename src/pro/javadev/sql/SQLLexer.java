package pro.javadev.sql;

import pro.javadev.common.lexer.AbstractLexer;
import pro.javadev.common.token.Token;

import java.util.List;

public class SQLLexer extends AbstractLexer {

    public SQLLexer(List<Token.Entry> entries) {
        super(entries);
    }

}
