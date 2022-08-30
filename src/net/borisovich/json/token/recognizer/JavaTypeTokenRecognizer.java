package net.borisovich.json.token.recognizer;

import net.borisovich.common.recognizer.AbstractRecognizer;
import net.borisovich.common.PriorityComparator;
import net.borisovich.common.recognizer.Recognizer;
import net.borisovich.json.token.Token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static net.borisovich.json.JSONRegexp.*;
import static net.borisovich.json.token.JSONToken.*;

public class JavaTypeTokenRecognizer extends AbstractRecognizer<Token, String> {

    private static final int PRIORITY = 200;

    private final List<Recognizer<Token, String>> recognizers;

    public JavaTypeTokenRecognizer() {
        super(PRIORITY);
        this.recognizers = new ArrayList<Recognizer<Token, String>>() {{
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

        Optional<Token>      token    = Optional.empty();
        Iterator<Recognizer<Token, String>> iterator = recognizers.iterator();

        while (iterator.hasNext() && !token.isPresent()) {
            token = iterator.next().recognize(piece);
        }

        return token;
    }

}
