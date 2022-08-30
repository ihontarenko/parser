package net.borisovich.json.token;

import net.borisovich.common.Expression;
import net.borisovich.common.PriorityComparator;
import net.borisovich.common.recognizer.Recognizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;

public class JSONTokenizer implements Tokenizer<Recognizer<Token, String>, Expression<String>> {

    private static final int                             COMPILER_FLAGS = CASE_INSENSITIVE;
    private final        List<Expression<String>>        expressions;
    private final        List<Recognizer<Token, String>> recognizers;

    public JSONTokenizer() {
        this.expressions = new ArrayList<>();
        this.recognizers = new ArrayList<>();
    }

    @Override
    public List<Token.Entry> tokenize(CharSequence sequence) {
        ensureExpressions();
        ensureRecognizers();

        expressions.sort(new PriorityComparator<>());
        recognizers.sort(new PriorityComparator<>());

        Collector<CharSequence, ?, String> collector  = Collectors.joining("|");
        List<Token.Entry>                  tokens     = new ArrayList<>();
        String                             expression = "(" + expressions.stream().map(Expression::expression).collect(collector) + ")";
        Matcher                            matcher    = compile(expression, COMPILER_FLAGS).matcher(sequence);

        System.out.println("expression: " + expression);

        while (matcher.find()) {
            String                              value    = matcher.group(1);
            int                                 position = matcher.end() - value.length();
            Optional<Token>                     token    = Optional.empty();
            Iterator<Recognizer<Token, String>> iterator = recognizers.iterator();

            while (iterator.hasNext() && !token.isPresent()) {
                token = iterator.next().recognize(value);
            }

            if (!token.isPresent()) {
                throw new JSONTokenizerException(String.format("CAN NOT RECOGNIZE TOKEN [%s] TYPE", value));
            }

            if (token.get().equals(JSONToken.T_STRING)) {
                value = value.substring(1, value.length() - 1);
            }

            tokens.add(new Token.EntryImplementation(token.get(), value, position));
        }

        return tokens;
    }

    @Override
    public void with(Expression<String> expression) {
        this.expressions.add(expression);
    }

    @Override
    public void with(Recognizer<Token, String> recognizer) {
        this.recognizers.add(recognizer);
    }

    private void ensureExpressions() {
        if (expressions.isEmpty()) {
            throw new JSONTokenizerException("EXPRESSIONS LIST IS EMPTY");
        }
    }

    private void ensureRecognizers() {
        if (expressions.isEmpty()) {
            throw new JSONTokenizerException("RECOGNIZERS LIST IS EMPTY");
        }
    }

}
