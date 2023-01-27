package pro.javadev.gc;

import pro.javadev.common.lexer.Lexer;
import pro.javadev.common.node.EntryNode;
import pro.javadev.common.node.Node;
import pro.javadev.common.parser.LiteralParser;
import pro.javadev.common.parser.Parser;
import pro.javadev.common.parser.ParserContext;
import pro.javadev.common.token.DefaultTokenizer;
import pro.javadev.common.token.Token;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class Play {

    public static void main(String[] args) {
        String           string    = getString();
        ParserContext    context   = ParserContext.CONTEXT;
        DefaultTokenizer tokenizer = new GCodeTokenizer();

        new GCodeTokenizerConfigurator().configure(tokenizer);
        new GCodeParserContextConfigurator().configure(context);

        Lexer  lexer  = new GCodeLexer(tokenizer.tokenize(string));
        Node   root   = new EntryNode(lexer.current());
        Parser parser = context.getParser(LiteralParser.class);

        for (Token.Entry entry : lexer) {
            System.out.println(entry);
        }

        iterate(1, n -> (int) Math.round(n * 1.61), System.out::println, 10);

        root.execute(System.out::println);
    }

    public static <T> void iterate(T value, Function<T, T> action, Consumer<T> consumer, int limit) {
        for (int i = 0; i < limit; i++) {
            consumer.accept(value);
            value = action.apply(value);
        }
    }

    public static String getString() {
        String value = null;

        try {
            value = Files.readString(Paths.get(Objects.requireNonNull(Play.class.getResource("/gcode.txt")).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return value;
    }

}
