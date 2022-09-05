package pro.javadev.sql;

import pro.javadev.common.Regexps;
import pro.javadev.common.TokenizerExpression;
import pro.javadev.common.lexer.Lexer;
import pro.javadev.common.node.Node;
import pro.javadev.common.recognizer.JavaTypeTokenRecognizer;
import pro.javadev.common.recognizer.NativeTokenRecognizer;
import pro.javadev.common.token.DefaultTokenizer;
import pro.javadev.common.token.Token.Entry;
import pro.javadev.sql.ast.SQLNode;
import pro.javadev.sql.recognizer.SQLRecognizer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Play {

    public static void main(String[] args) {
        DefaultTokenizer tokenizer = new DefaultTokenizer();

        tokenizer.with(new JavaTypeTokenRecognizer());
        tokenizer.with(new NativeTokenRecognizer());
        tokenizer.with(new TokenizerExpression(Regexps.R_QUOTED_STRING_1.expression(), 100));
        tokenizer.with(new TokenizerExpression(Regexps.R_NUMBER.expression(), 200));
        tokenizer.with(new TokenizerExpression(Regexps.R_IDENTIFIER.expression(), 300));
        tokenizer.with(new TokenizerExpression(Regexps.R_SPECIAL_SYMBOLS.expression(), 400));
        tokenizer.with(new SQLRecognizer());

        String sql   = getSQLString();
        Lexer  lexer = new SQLLexer(tokenizer.tokenize(sql));

        for (Entry entry : lexer) {
            System.out.println(entry);
        }

        Node node0 = new SQLNode("a", null);
        Node node1 = new SQLNode("b", null);
        Node node2 = new SQLNode("c", null);
        Node node3 = new SQLNode("d", null);

        node0.add(node1);

        node1.add(node2);
        node1.add(node3);

        node2.add(node3);
        node1.add(node3);

        node3.add(node1);
    }

    public static String getSQLString() {
        String sql = null;

        try {
            sql = Files.readString(Paths.get(Objects.requireNonNull(Play.class.getResource("/delete.sql")).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return sql;
    }

}
