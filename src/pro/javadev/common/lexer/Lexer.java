package pro.javadev.common.lexer;

import java.util.ListIterator;
import java.util.function.Predicate;

public interface Lexer<E, T> extends ListIterator<E>, Iterable<E> {

    E current();

    int length();

    int cursor();

    void cursor(int cursor);

    E lookOver(T start, T end, Lexer<E, T> lexer);

    default E lookOver(T start, T end) {
        return lookOver(start, end, peeker());
    }

    void forward(Predicate<E> predicate);

    void backward(Predicate<E> predicate);

    void forward(T token);

    void backward(T token);

    Lexer<E, T> peeker();

}
