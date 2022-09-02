package pro.javadev.common.token;

import pro.javadev.common.Expression;
import pro.javadev.common.recognizer.Recognizer;

import java.util.List;

public interface Tokenizer<R extends Recognizer<?, ?>, E extends Expression<?>> {
    List<Token.Entry> tokenize(CharSequence sequence);
    void with(E expression);
    void with(R recognizer);
}
