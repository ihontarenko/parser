package pro.javadev.common.parser;

import pro.javadev.common.lexer.Lexer;
import pro.javadev.common.node.EntryNode;
import pro.javadev.common.node.Node;
import pro.javadev.common.token.Token;

import java.util.HashMap;
import java.util.Map;

import static pro.javadev.common.token.DefaultToken.*;

public class LiteralParser implements Parser {

    private static final Token[] TOKENS = {
            T_INT, T_FLOAT, T_STRING, T_TRUE, T_FALSE, T_NULL
    };
    private final Map<Token, Class<?>> types = new HashMap<>() {{
        put(T_STRING, String.class);
        put(T_INT, Integer.class);
        put(T_FLOAT, Float.class);
        put(T_TRUE, Boolean.class);
        put(T_FALSE, Boolean.class);
        put(T_NULL, null);
    }};

    @Override
    public void parse(Lexer lexer, Node parent, ParserContext context) {
        shift(lexer, TOKENS);

        EntryNode node = new EntryNode(lexer.current());

        node.properties().put("type", types.get(node.entry().token()));

        parent.add(node);
    }

}
