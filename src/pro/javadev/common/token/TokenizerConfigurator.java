package pro.javadev.common.token;

import pro.javadev.common.Regexps;
import pro.javadev.common.configurer.Configurator;
import pro.javadev.common.recognizer.EnumTokenRecognizer;
import pro.javadev.common.recognizer.JavaTypeTokenRecognizer;
import pro.javadev.gc.token.GCodeToken;

public class TokenizerConfigurator implements Configurator<Tokenizer> {

    @Override
    public void configure(Tokenizer tokenizer) {
        // recognizers
        tokenizer.configure(new JavaTypeTokenRecognizer());
        tokenizer.configure(new EnumTokenRecognizer<>(GCodeToken.values(), 1500));
        tokenizer.configure(new EnumTokenRecognizer<>(DefaultToken.values(), 2000));
        // expressions
        tokenizer.configure(new TokenizerPattern(Regexps.R_QUOTED_STRING_1.expression(), 100));
        tokenizer.configure(new TokenizerPattern(Regexps.R_FLOAT_1.expression(), 150));
        tokenizer.configure(new TokenizerPattern(Regexps.R_S_INT.expression(), 200));
        tokenizer.configure(new TokenizerPattern(Regexps.R_IDENTIFIER.expression(), 300));
        tokenizer.configure(new TokenizerPattern(Regexps.R_SPECIAL_SYMBOLS.expression(), 1000));
    }

}
