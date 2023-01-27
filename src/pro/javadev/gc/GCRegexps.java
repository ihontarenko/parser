package pro.javadev.gc;

import java.util.function.Predicate;

public enum GCRegexps {

    R_GCODE_COMMAND("[O|S|T|G|M]\\d+");

    private final String expression;

    GCRegexps(String expression) {
        this.expression = expression;
    }

    public Predicate<String> predicate() {
        return input -> input.matches(this.expression);
    }

    public String expression() {
        return this.expression;
    }

}
