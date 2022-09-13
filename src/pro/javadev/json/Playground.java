package pro.javadev.json;

import pro.javadev.common.token.DefaultTokenizer;
import pro.javadev.common.Regexps;
import pro.javadev.common.token.TokenizerPattern;
import pro.javadev.common.lexer.Lexer;
import pro.javadev.common.recognizer.Recognizer;
import pro.javadev.common.token.Token;
import pro.javadev.json.ast.JSONNode;
import pro.javadev.json.lexer.JSONLexer;
import pro.javadev.json.token.*;
import pro.javadev.common.recognizer.JavaTypeTokenRecognizer;

import java.util.Optional;

import static pro.javadev.common.token.DefaultToken.*;

public class Playground {

    public static void main(String[] arguments) {

        DefaultTokenizer tokenizer = new DefaultTokenizer();

//        tokenizer.with(new EnumTokenRecognizer(tokens));
        tokenizer.configure(new JavaTypeTokenRecognizer());
        tokenizer.configure(new Recognizer<Token, String>() {
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

        tokenizer.configure(new TokenizerPattern(Regexps.R_QUOTED_STRING_1.expression(), 100));
        tokenizer.configure(new TokenizerPattern(Regexps.R_NUMBER.expression(), 200));
        tokenizer.configure(new TokenizerPattern(Regexps.R_IDENTIFIER.expression(), 200));
        tokenizer.configure(new TokenizerPattern(Regexps.R_SPECIAL_SYMBOLS.expression(), 300));
        tokenizer.configure(new TokenizerPattern("@\\w+", 150));

        String json = "START 1, 2.5 100 (A, B, (C (d) +1)  FIN{\n" +
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
                "\t\t\t\t\t\"GlossDef\": ) XXX {\n" +
                "                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\n" +
                "\t\t\t\t\t\t\"GlossSeeAlso\": [\"GML\", \"XML\" HUI]\n" +
                "                    },\n" +
                "\t\t\t\t\t\"GlossSee\": \"markup\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "} END;";

        String json2 = "[1, 2, @ref(\"/aa/\") + 100]";

        Lexer lexer = new JSONLexer(tokenizer.tokenize(json));

        lexer.forward(T_STRING, "title");
//
//        System.out.println("current" + lexer.current());
//        System.out.println("hasCurrent T_IDENTIFIER " + lexer.hasCurrent(T_OPEN_BRACE));
//        System.out.println("lookAhead 1 " + lexer.lookAhead());
//        System.out.println("lookAhead -1 "+lexer.lookAhead(-1));
//
//        System.out.println("hasNext T_IDENTIFIER " + lexer.hasNext(T_IDENTIFIER));
//        System.out.println("hasPrevious T_INT " +lexer.hasPrevious(T_INT));
//
//        Entry entry = lexer.lookOver(T_OPEN_BRACE, T_CLOSE_BRACE);
//
//        lexer.has(3, -200, T_FLOAT, T_INT);
//
//        System.out.println(entry);
//
//        lexer.cursor(entry.ordinal());

        while (lexer.hasNext()) {
            System.out.println(lexer.next());
        }

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
