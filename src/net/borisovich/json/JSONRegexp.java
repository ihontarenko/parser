package net.borisovich.json;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@SuppressWarnings({"unused"})
public enum JSONRegexp {

    R_QUOTED_STRING_1("\"[^\"]*\""),
    R_QUOTED_STRING_2("'[^']*'"),
    R_LOGICAL_OPERATOR_1("&{2}|\\|{2}"),
    R_LOGICAL_OPERATOR_2("[><!=]+=?"),
    R_NUMBER("[.]?\\d+[.]?\\d*"),
    R_INT("\\d+"),
    R_FLOAT("\\d*\\.\\d*"),
    R_IDENTIFIER("\\w+"),
    R_SPECIAL_SYMBOLS("\\S+?");

    private final String expression;
    private final Pattern pattern;

    JSONRegexp(String expression) {
        this.expression = expression;
        this.pattern = Pattern.compile(expression);
    }

    public Predicate<String> predicate() {
        return input -> input.matches(this.expression);
    }

    public Pattern pattern() {
        return this.pattern;
    }

    public String expression() {
        return this.expression;
    }

    @Override
    public String toString() {
        return expression;
    }

}
