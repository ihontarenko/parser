package pro.javadev.common.parser;

import pro.javadev.common.token.Token;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SyntaxErrorException extends Error {

    public SyntaxErrorException(Parser parser, Token.Entry entry, Token... expected) {
        this(String.format("SYNTAX ERROR. [%s] expected: %s got \"%s\" at position %d",
                parser.getClass().getSimpleName(), Stream.of(expected).map(Objects::toString).collect(Collectors.joining(", ")),
                entry.token(), entry.position()));
    }

    public SyntaxErrorException(String message) {
        super(message);
    }

}
