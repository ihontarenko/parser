package pro.javadev.json;

import pro.javadev.common.lexer.Lexer;
import pro.javadev.common.recognizer.Recognizer;
import pro.javadev.common.token.Token;
import pro.javadev.json.ast.JSONNode;
import pro.javadev.json.lexer.JSONLexer;
import pro.javadev.json.token.*;
import pro.javadev.json.token.recognizer.JavaTypeTokenRecognizer;
import pro.javadev.common.recognizer.NativeTokenRecognizer;

import java.util.Optional;

import static pro.javadev.json.token.JSONToken.*;

public class Playground {

    public static void main(String[] arguments) {
        System.out.println(new NativeTokenRecognizer().recognize("!="));

        JSONTokenizer tokenizer = new JSONTokenizer();

        tokenizer.with(new NativeTokenRecognizer());
        tokenizer.with(new JavaTypeTokenRecognizer());
        tokenizer.with(new Recognizer<Token, String>() {
            @Override
            public Optional<Token> recognize(String subject) {
                switch (subject) {
                    case "@ref":
                        return Optional.of(JSONExpressionToken.T_AT_REF);
                }

                return Optional.empty();
            }

            @Override
            public int priority() {
                return 100;
            }
        });

        tokenizer.with(new TokenizerExpression(JSONRegexp.R_QUOTED_STRING_1.expression(), 100));
        tokenizer.with(new TokenizerExpression(JSONRegexp.R_NUMBER.expression(), 200));
        tokenizer.with(new TokenizerExpression(JSONRegexp.R_IDENTIFIER.expression(), 200));
        tokenizer.with(new TokenizerExpression(JSONRegexp.R_SPECIAL_SYMBOLS.expression(), 300));
        tokenizer.with(new TokenizerExpression("@\\w+", 150));

        String json = "START (A, B, (C (d) +1)  FIN{\n" +
                "    \"glossary\": {\n" +
                "        \"title\": \"example glossary\",\n" +
                "\t\t\"GlossDiv\": {\n" +
                "            \"title\": \"S\",\n" +
                "\t\t\t\"GlossList\": {\n" +
                "                \"GlossEntry\": {\n" +
                "                    \"ID\": \"TEST\",\n" +
                "\t\t\t\t\t\"SortAs\": \"SGML\",\n" +
                "\t\t\t\t\t\"GlossTerm\": \"Standard Generalized Markup Language\",\n" +
                "\t\t\t\t\t\"Acronym\": \"SGML\",\n" +
                "\t\t\t\t\t\"Abbrev\": \"ISO 8879:1986\",\n" +
                "\t\t\t\t\t\"GlossDef\": {\n" +
                "                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\n" +
                "\t\t\t\t\t\t\"GlossSeeAlso\": [\"GML\", \"XML\" )HUI]\n" +
                "                    },\n" +
                "\t\t\t\t\t\"GlossSee\": \"markup\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "} END;";

        String json2 = "[1, 2, @ref(\"/aa/\") + 100]";


        Lexer<Token.Entry, Token> lexer = new JSONLexer(tokenizer.tokenize(json));

        lexer.forward(T_OPEN_BRACE);
        System.out.println(lexer.current());

        System.out.println(lexer.lookOver(T_OPEN_BRACE, T_CLOSE_BRACE));

        System.out.println("continue...");
        while (lexer.hasNext()) {
            System.out.println(lexer.next());
        }


        /*System.out.println("forward");
        lexer.forward(T_CLOSE_BRACKET);

        int cursor = lexer.cursor();
        System.out.println("lexer cursor: " + cursor);

        while (lexer.hasNext()) {
            System.out.println(lexer.next());
        }

        System.out.println("< cursor: " + lexer.cursor());
        lexer.cursor(cursor);
        System.out.println("> cursor: " + lexer.cursor());

        System.out.println("> current: " + lexer.current());

        lexer.backward(T_OPEN_BRACKET);

        System.out.println("> current: " + lexer.current());

        while (lexer.hasNext()) {
            System.out.println(lexer.next());
        }*/

//        while (lexer.hasPrevious()) {
//            System.out.println(lexer.previous());
//        }

        System.out.println(lexer.hasNext());
        System.out.println(lexer.cursor());
        System.out.println(lexer.length());


        JSONNode root  = new JSONNode(null);
        JSONNode child = new JSONNode(null);

        root.add(new JSONNode(null));
        root.add(child);
        root.add(new JSONNode(null));

//        root.execute(object -> {
//            if (object.isRoot()) {
//                System.out.println("root!");
//            } else {
//                System.out.println("child");
//            }
//        });

        System.out.println(
                T_STRING instanceof Enum
        );

    }

}
