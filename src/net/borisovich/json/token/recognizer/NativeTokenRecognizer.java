package net.borisovich.json.token.recognizer;

import net.borisovich.common.recognizer.AbstractRecognizer;
import net.borisovich.json.token.JSONToken;
import net.borisovich.json.token.Token;

import java.util.Optional;

public class NativeTokenRecognizer extends AbstractRecognizer<Token, String> {

    private static final int PRIORITY = 100;

    public NativeTokenRecognizer() {
        super(PRIORITY);
    }

    @Override
    public Optional<Token> recognize(String piece) {
        Optional<Token> token = Optional.empty();

        for (JSONToken current : JSONToken.values()) {
            if (current.getValues().length > 0) {
                for (String example : current.getValues()) {
                    if (example.equalsIgnoreCase(piece)) {
                        token = Optional.of(current);
                        break;
                    }
                }
            }
        }

        return token;
    }

}
