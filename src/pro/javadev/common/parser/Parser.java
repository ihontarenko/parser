package pro.javadev.common.parser;

import pro.javadev.common.lexer.Lexer;
import pro.javadev.common.node.Node;
import pro.javadev.common.matching.Matcher;
import pro.javadev.common.token.Token;

public interface Parser {

    void parse(Lexer lexer, Node parent, ParserContext context);

    default boolean is(Lexer lexer, Matcher tester) {
        return tester.test(lexer.lexer());
    }

    default boolean isMath(Lexer lexer) {
        return is(lexer, Matcher.MATH_TESTER);
    }

    default boolean isFieldPath(Lexer lexer) {
        return is(lexer, Matcher.FIELD_PATH);
    }

    default void shift(Lexer lexer, Token... tokens) {
        if (lexer.isNext(tokens)) {
            lexer.next();
        } else {
            throwSyntaxErrorException(lexer, tokens);
        }
    }

    default void throwSyntaxErrorException(Lexer lexer, Token... token) {
        throw new SyntaxErrorException(this, lexer.lookAhead(), token);
    }

}
