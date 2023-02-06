package pro.javadev.sql.parser;

import pro.javadev.common.lexer.Lexer;
import pro.javadev.common.node.EntryNode;
import pro.javadev.common.node.Node;
import pro.javadev.common.parser.IdentifierParser;
import pro.javadev.common.parser.Parser;
import pro.javadev.common.parser.ParserContext;

import static pro.javadev.common.token.DefaultToken.T_DOT;

public class FieldPathParser implements Parser {

    @Override
    public void parse(Lexer lexer, Node parent, ParserContext context) {
        Node node = new EntryNode(null);

        context.getParser(IdentifierParser.class).parse(lexer, node, context);
        shift(lexer, T_DOT);
        node.add(new EntryNode(lexer.current()));
        context.getParser(IdentifierParser.class).parse(lexer, node, context);

        parent.add(node);
    }

}
