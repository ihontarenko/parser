package pro.javadev.sql;

import pro.javadev.common.Regexps;
import pro.javadev.common.recognizer.EnumTokenRecognizer;
import pro.javadev.common.token.Tokenizer;
import pro.javadev.common.token.TokenizerConfigurator;
import pro.javadev.common.token.TokenizerPattern;
import pro.javadev.sql.token.SQLToken;

public class SQLTokenizerConfigurator extends TokenizerConfigurator {

    @Override
    public void configure(Tokenizer tokenizer) {
        super.configure(tokenizer);
        tokenizer.configure(new TokenizerPattern(Regexps.R_LOGICAL_OPERATOR_1.expression(), 400));
        tokenizer.configure(new TokenizerPattern(Regexps.R_LOGICAL_OPERATOR_2.expression(), 500));

        tokenizer.configure(new EnumTokenRecognizer<>(SQLToken.values(), 1000));
    }

}
