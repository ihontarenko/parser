package pro.javadev.common.lexer;

import pro.javadev.common.token.Token;

import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractLexer extends AbstractListIterator<Token.Entry> implements Lexer<Token.Entry, Token> {

    public AbstractLexer(List<Token.Entry> entries) {
        super(entries);
    }

    @Override
    public void forward(Predicate<Token.Entry> predicate) {
        while (hasNext() && !predicate.test(current())) {
            next();
        }
    }

    @Override
    public void forward(Token token) {
        forward(entry -> entry.is(token));
    }

    @Override
    public void backward(Predicate<Token.Entry> predicate) {
        while (hasPrevious() && !predicate.test(current())) {
            previous();
        }
    }

    @Override
    public void backward(Token token) {
        backward(entry -> entry.is(token));
    }

    @Override
    public Token.Entry current() {
        return entries.get(cursor);
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public int cursor() {
        return cursor;
    }

    @Override
    public void cursor(int cursor) {
        this.cursor = cursor;
    }

    @Override
    public Token.Entry lookOver(Token start, Token end, Lexer<Token.Entry, Token> lexer) {
        Token.Entry next  = lexer.next();
        int         depth = 0;

        do {
            depth += (next.is(start) || next.is(end)) ? next.is(end) ? -1 : 1 : 0;
            next = lexer.next();
        } while (depth > 0);

        return next;
    }

    @Override
    public Lexer<Token.Entry, Token> peeker() {
        Lexer<Token.Entry, Token> lexer = new InnerLexer(AbstractLexer.this.entries);

        lexer.cursor(AbstractLexer.this.cursor);

        return lexer;
    }

    static class InnerLexer extends AbstractLexer {

        public InnerLexer(List<Token.Entry> entries) {
            super(entries);
        }

    }

}
