package net.borisovich.json;

import net.borisovich.common.recognizer.Recognizer;
import net.borisovich.json.ast.JSONNode;
import net.borisovich.json.token.JSONExpressionToken;
import net.borisovich.json.token.JSONTokenizer;
import net.borisovich.json.token.Token;
import net.borisovich.json.token.TokenizerExpression;
import net.borisovich.json.token.recognizer.JavaTypeTokenRecognizer;
import net.borisovich.json.token.recognizer.NativeTokenRecognizer;

import java.util.Optional;

public class Playground {

    public static void main(String[] arguments) {

        System.out.println(
                1 << 12
        );

        System.out.println(new NativeTokenRecognizer().recognize("!="));

        JSONTokenizer jsonTokenizer = new JSONTokenizer();

        jsonTokenizer.with(new NativeTokenRecognizer());
        jsonTokenizer.with(new JavaTypeTokenRecognizer());
        jsonTokenizer.with(new Recognizer<Token, String>() {
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

        jsonTokenizer.with(new TokenizerExpression(JSONRegexp.R_QUOTED_STRING_1.expression(), 100));
        jsonTokenizer.with(new TokenizerExpression(JSONRegexp.R_NUMBER.expression(), 200));
        jsonTokenizer.with(new TokenizerExpression(JSONRegexp.R_IDENTIFIER.expression(), 200));
        jsonTokenizer.with(new TokenizerExpression(JSONRegexp.R_SPECIAL_SYMBOLS.expression(), 300));
        jsonTokenizer.with(new TokenizerExpression("@\\w+", 150));

        String json = "{\n" +
                "    \"glossary\": {\n" +
                "        \"Кирилиця\": \"example glossary\",\n" +
                "\t\t\"GlossDiv\": {\n" +
                "            \"title\": \"S\" >= 23,\n" +
                "\t\t\t\"GlossList\": 2.34{\n" +
                "                \"GlossEntry\": [ok > TEST, 123.4] {\n" +
                "                    \"ID\": \"SGML\",\n" +
                "\t\t\t\t\t\"SortAs\": \"SGML\",\n" +
                "\t\t\t\t\t\"GlossTerm\": \"Standard Generalized Markup Language\",\n" +
                "\t\t\t\t\t\"Acronym\": \"SGML\",\n" +
                "\t\t\t\t\t\"Abbrev\": ISO 8879:1986,\n" +
                "\t\t\t\t\t\"GlossDef\": {\n" +
                "                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\n" +
                "\t\t\t\t\t\t\"GlossSeeAlso\": [\"GML\", \"XML\"]\n" +
                "                    },\n" +
                "\t\t\t\t\t\"Gloss\", \"See\": \"mar\", 45.5, 33 * PI() == .003 and a eQ 1, @ref(test.aaa), \"kup\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "}";

        String json2 = "[1, 2, @ref(\"/aa/\") + 100]";

        for (Token.Entry entry : jsonTokenizer.tokenize(json)) {
            System.out.println(entry);
        }

        JSONNode root  = new JSONNode(null);
        JSONNode child = new JSONNode(null);

        root.add(new JSONNode(null));
        root.add(child);
        root.add(new JSONNode(null));

        root.execute(object -> {
            if (object.isRoot()) {
                System.out.println("root!");
            } else {
                System.out.println("child");
            }
        });


    }

}
