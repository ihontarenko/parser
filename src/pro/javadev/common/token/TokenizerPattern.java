package pro.javadev.common.token;

import pro.javadev.common.Pattern;

public class TokenizerPattern implements Pattern<String> {

    protected final int                     priority;
    protected final java.util.regex.Pattern pattern;

    public TokenizerPattern(String expression, int priority) {
        this.pattern = java.util.regex.Pattern.compile(expression);
        this.priority = priority;
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public String pattern() {
        return pattern.pattern();
    }

}
