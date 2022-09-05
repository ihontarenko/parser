package pro.javadev.sql.recognizer;

import pro.javadev.common.finder.EnumFinder;
import pro.javadev.common.recognizer.Recognizer;
import pro.javadev.common.token.Token;
import pro.javadev.sql.token.SQLToken;

import java.util.Optional;

public class SQLRecognizer extends EnumFinder<SQLToken> implements Recognizer<Token, String> {

    private static final int PRIORITY = 1500;

    @Override
    public int priority() {
        return PRIORITY;
    }

    @Override
    public Optional<Token> recognize(String subject) {
        return Optional.ofNullable(
                find(subject, SQLToken.values()).orElse(null)
        );
    }

}
