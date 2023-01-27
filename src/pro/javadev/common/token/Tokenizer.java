package pro.javadev.common.token;

import pro.javadev.common.Pattern;
import pro.javadev.common.recognizer.Recognizer;

import java.util.List;

public interface Tokenizer {
    List<Token.Entry> tokenize(CharSequence sequence);

    void configure(Pattern<String> expression);

    void configure(Recognizer<Token, String> recognizer);

    default Token.Entry entry(Token token, String value, final int position, final int ordinal) {
        return Token.Entry.of(token, value, position, ordinal);
    }

}
