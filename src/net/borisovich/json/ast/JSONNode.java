package net.borisovich.json.ast;

import net.borisovich.json.token.Token;

import java.util.Objects;

public class JSONNode extends AbstractNode {

    private Token.Entry entry;

    public JSONNode(Token.Entry entry) {
        this(entry, null);
    }

    public JSONNode(Token.Entry entry, Node parent) {
        super(parent);
        this.entry = entry;
    }

    public Token.Entry getEntry() {
        return entry;
    }

    public String getValue() {
        return entry.getValue();
    }

    public Token getToken() {
        return entry.getToken();
    }

    @Override
    public int hashCode() {
        return entry.hashCode();
    }

    @Override
    public boolean equals(Object that) {
        if(this == that) {
            return true;
        }

        if(that == null || getClass() != that.getClass()) {
            return false;
        }

        return Objects.equals(((JSONNode)that).entry, this.entry);
    }

}
