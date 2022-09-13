package pro.javadev.common.tester;

import pro.javadev.common.lexer.Lexer;

import java.util.function.Predicate;

import static pro.javadev.common.token.DefaultToken.*;

public interface Tester extends Predicate<Lexer> {

    Tester MATH_TESTER = lexer -> lexer.hasNext(T_PLUS, T_MINUS, T_DIVIDE, T_MULTIPLY);
    Tester FIELD_PATH  = lexer -> lexer.hasSequence(T_IDENTIFIER, T_DOT, T_IDENTIFIER);

}
