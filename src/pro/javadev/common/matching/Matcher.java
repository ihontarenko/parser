package pro.javadev.common.matching;

import pro.javadev.common.lexer.Lexer;

import java.util.function.Predicate;

import static pro.javadev.common.token.DefaultToken.*;

public interface Matcher extends Predicate<Lexer> {

    Matcher MATH_TESTER = lexer -> lexer.isNext(T_PLUS, T_MINUS, T_DIVIDE, T_MULTIPLY);
    Matcher FIELD_PATH  = lexer -> {
        boolean caseA = lexer.hasSequence(T_IDENTIFIER, T_DOT, T_IDENTIFIER);
        boolean caseB = lexer.hasSequence(T_IDENTIFIER, T_DOT, T_MULTIPLY);

        return caseA || caseB;
    };

}
