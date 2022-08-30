package net.borisovich.json.token;

import net.borisovich.common.Expression;
import net.borisovich.json.ast.AbstractNode;

import java.util.regex.Pattern;

public class TokenizerExpression extends AbstractNode implements Expression<String> {

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
