package pro.javadev.sql;

import pro.javadev.common.lexer.Lexer;
import pro.javadev.common.node.EntryNode;
import pro.javadev.common.node.Node;
import pro.javadev.common.parser.LiteralParser;
import pro.javadev.common.parser.Parser;
import pro.javadev.common.parser.ParserContext;
import pro.javadev.common.token.DefaultToken;
import pro.javadev.common.token.DefaultTokenizer;
import pro.javadev.sql.parser.FieldPathParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Play {

    public static void main(String[] args) {
        String           sql       = getSQLString();
        ParserContext    context   = ParserContext.CONTEXT;
        DefaultTokenizer tokenizer = new DefaultTokenizer();

        new SQLTokenizerConfigurator().configure(tokenizer);
        new SQLParserContextConfigurator().configure(context);

        Lexer  lexer  = new SQLLexer(tokenizer.tokenize(sql));
        Node   root   = new EntryNode(lexer.current());
        Parser parser = context.getParser(FieldPathParser.class);

//        lexer.forward(DefaultToken.T_IDENTIFIER);

        parser.parse(lexer, root, context);

        lexer.forEach(System.out::println);

        System.out.println(lexer.length());

        root.execute(System.out::println);
    }

    public static String getSQLString() {
        String sql = null;

        try {
            sql = Files.readString(Paths.get(Objects.requireNonNull(Play.class.getResource("/select.sql")).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return sql;
    }

}
