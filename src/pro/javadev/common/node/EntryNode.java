package pro.javadev.common.node;

import pro.javadev.common.token.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

public class EntryNode extends AbstractNode {

    private final Token.Entry         entry;
    private final Map<String, Object> properties = new HashMap<>();

    public EntryNode(Token.Entry entry) {
        this.entry = entry;
    }

    public Map<String, Object> properties() {
        return this.properties;
    }

    public Object property(String key) {
        return this.properties.get(key);
    }

    public Token.Entry entry() {
        return this.entry;
    }

    @Override
    public String toString() {
        return format("NODE V:[%s] T[%s]", entry, properties);
    }

}
