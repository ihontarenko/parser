package pro.javadev.common.parser;

import pro.javadev.common.lexer.Lexer;
import pro.javadev.common.node.EntryNode;
import pro.javadev.common.node.Node;
import pro.javadev.common.token.Token;

import static pro.javadev.common.token.DefaultToken.*;

public class LiteralParser implements Parser {

    private static final Token[] TOKENS = {T_INT, T_FLOAT, T_STRING, T_TRUE, T_FALSE, T_NULL};

    @Override
    public void parse(Lexer lexer, Node parent, ParserContext context) {
        shift(lexer, TOKENS);
        parent.add(new EntryNode(lexer.current()));
    }

}
