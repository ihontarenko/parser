package pro.javadev.json.token;

import pro.javadev.common.Expression;

import java.util.regex.Pattern;

public class TokenizerExpression implements Expression<String> {

    protected final int     priority;
    protected final Pattern pattern;

    public TokenizerExpression(String expression, int priority) {
        this.pattern = Pattern.compile(expression);
        this.priority = priority;
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public String expression() {
        return pattern.pattern();
    }

}
