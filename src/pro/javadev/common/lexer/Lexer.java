package pro.javadev.common.lexer;

import pro.javadev.common.token.Token;

import java.util.ListIterator;
import java.util.function.Predicate;

public interface Lexer extends ListIterator<Token.Entry>, Iterable<Token.Entry> {

    Token.Entry current();

    int length();

    int cursor();

    void cursor(int cursor);

    Token.Entry lookOver(Token start, Token end, Lexer lexer);

    void forward(Predicate<Token.Entry> predicate);

    void backward(Predicate<Token.Entry> predicate);

    void forward(Token.Entry entry);

    void backward(Token.Entry entry);

    void forward(Token token);

    void backward(Token token);

    Lexer lexer(int increase);

    boolean has(int limit, int offset, Token... tokens);

    default void forward(Token token, String value) {
        forward(Token.Entry.of(token, value));
    }

    default void backward(Token token, String value) {
        backward(Token.Entry.of(token, value));
    }

    default boolean hasCurrent(Token... tokens) {
        return has(1, tokens);
    }

    default boolean has(int limit, Token... tokens) {
        return has(limit, 0, tokens);
    }

    default boolean hasNext(Token... tokens) {
        return has(1, 1, tokens);
    }

    default boolean hasPrevious(Token... tokens) {
        return has(1, -1, tokens);
    }

    default Token.Entry lookOver(Token start, Token end) {
        return lookOver(start, end, lexer());
    }

    default Lexer lexer() {
        return lexer(0);
    }

    default Token.Entry lookAhead(int offset) {
        return lexer(offset).current();
    }

    default Token.Entry lookAhead() {
        return lookAhead(1);
    }

}
