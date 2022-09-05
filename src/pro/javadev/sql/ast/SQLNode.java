package pro.javadev.sql.ast;

import pro.javadev.common.node.AbstractNode;
import pro.javadev.common.token.Token;

import java.util.StringJoiner;

public class SQLNode extends AbstractNode {

    private final String name;
    private final Token  token;

    public SQLNode(String name, Token token) {
        this.name = name;
        this.token = token;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "NODE: [", "]")
                .add(name).toString();
    }

}
