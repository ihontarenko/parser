package pro.javadev.common.recognizer;

import pro.javadev.common.finder.EnumFinder;
import pro.javadev.common.token.DefaultToken;
import pro.javadev.common.token.Token;

import java.util.Optional;

public class NativeTokenRecognizer extends EnumFinder<DefaultToken> implements Recognizer<Token, String> {

    private static final int PRIORITY = 2000;

    @Override
    public int priority() {
        return PRIORITY;
    }

    @Override
    public Optional<Token> recognize(String piece) {
        return Optional.ofNullable(
                find(piece, DefaultToken.values()).orElse(null)
        );
    }

}
