package pro.javadev.gc;

import pro.javadev.common.Regexps;
import pro.javadev.common.configurer.Configurator;
import pro.javadev.common.recognizer.PatternTokenRecognizer;
import pro.javadev.common.recognizer.TokenRecognizerDecorator;
import pro.javadev.common.token.Tokenizer;
import pro.javadev.common.token.TokenizerPattern;

import static pro.javadev.common.Regexps.*;
import static pro.javadev.common.Regexps.R_SEMICOLON_COMMENT;
import static pro.javadev.gc.token.GCodeToken.*;

public class GCodeTokenizerConfigurator implements Configurator<Tokenizer> {

    @Override
    public void configure(Tokenizer tokenizer) {
        tokenizer.configure(new TokenizerPattern(GCRegexps.R_GCODE_COMMAND.expression(), 50));
        tokenizer.configure(new TokenizerPattern(Regexps.R_ALPHA.expression(), 100));
        tokenizer.configure(new TokenizerPattern(Regexps.R_S_INT.expression(), 200));
        tokenizer.configure(new TokenizerPattern(Regexps.R_NEW_LINE.expression(), 200));
        tokenizer.configure(new TokenizerPattern(Regexps.R_SEMICOLON_COMMENT.expression(), 500));

        tokenizer.configure(new TokenRecognizerDecorator() {{
            addRecognizer(new PatternTokenRecognizer(GCRegexps.R_GCODE_COMMAND.expression(), T_GCODE_COMMAND, 50));
            addRecognizer(new PatternTokenRecognizer(R_ALPHA.expression(), T_GCODE_KEY, 100));
            addRecognizer(new PatternTokenRecognizer(R_S_INT.expression(), T_GCODE_VALUE, 150));
            addRecognizer(new PatternTokenRecognizer(R_NEW_LINE.expression(), T_GCODE_EOL, 200));
            addRecognizer(new PatternTokenRecognizer(R_SEMICOLON_COMMENT.expression(), T_GCODE_COMMENT_TEXT, 300));
        }});
    }

}
