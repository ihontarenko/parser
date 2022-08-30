package net.borisovich.json.token;

import net.borisovich.common.Expression;
import net.borisovich.common.Priority;
import net.borisovich.common.recognizer.Recognizer;
import net.borisovich.json.ast.Node;

import java.util.List;

public interface Tokenizer<R extends Recognizer<?, ?>, E extends Expression<?>> {
    List<Token.Entry> tokenize(CharSequence sequence);
    void with(E expression);
    void with(R recognizer);
}
