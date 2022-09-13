package pro.javadev.sql;

import pro.javadev.common.Regexps;
import pro.javadev.common.token.Tokenizer;
import pro.javadev.common.token.TokenizerConfigurator;
import pro.javadev.common.token.TokenizerPattern;

public class SQLTokenizerConfigurator extends TokenizerConfigurator {

    @Override
    public void configure(Tokenizer tokenizer) {
        super.configure(tokenizer);
        tokenizer.configure(new TokenizerPattern(Regexps.R_LOGICAL_OPERATOR_1.expression(), 400));
        tokenizer.configure(new TokenizerPattern(Regexps.R_LOGICAL_OPERATOR_2.expression(), 500));
    }

}
