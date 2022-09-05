package pro.javadev.common.recognizer;

import pro.javadev.common.PriorityComparator;
import pro.javadev.common.token.Token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static pro.javadev.common.Regexps.*;
import static pro.javadev.common.token.DefaultToken.*;

public class JavaTypeTokenRecognizer extends AbstractRecognizer<Token, String> {

    private static final int PRIORITY = 2500;

    private final List<Recognizer<Token, String>> recognizers;

    public JavaTypeTokenRecognizer() {
        super(PRIORITY);
        this.recognizers = new ArrayList<>() {{
            add(new PatternTokenRecognizer(R_QUOTED_STRING_1.expression(), T_STRING, 100));
            add(new PatternTokenRecognizer(R_QUOTED_STRING_2.expression(), T_STRING, 150));
            add(new PatternTokenRecognizer(R_FLOAT.expression(), T_FLOAT, 500));
            add(new PatternTokenRecognizer(R_INT.expression(), T_INT, 600));
            add(new PatternTokenRecognizer(R_IDENTIFIER.expression(), T_IDENTIFIER, 700));
        }};
    }


    @Override
    public Optional<Token> recognize(String piece) {
        this.recognizers.sort(new PriorityComparator<>());

        Optional<Token>                     token    = Optional.empty();
        Iterator<Recognizer<Token, String>> iterator = recognizers.iterator();

        while (iterator.hasNext() && !token.isPresent()) {
            token = iterator.next().recognize(piece);
        }

        return token;
    }

}
