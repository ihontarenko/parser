package pro.javadev.common.recognizer;

import pro.javadev.common.finder.EnumFinder;
import pro.javadev.json.token.JSONToken;
import pro.javadev.common.token.Token;

import java.util.Optional;

public class NativeTokenRecognizer extends EnumFinder<JSONToken> implements Recognizer<Token, String> {

    private static final int PRIORITY = 100;

    @Override
    public int priority() {
        return PRIORITY;
    }

    @Override
    public Optional<Token> recognize(String piece) {
        return Optional.ofNullable(
                find(piece, JSONToken.values()).orElse(null)
        );
    }

}
