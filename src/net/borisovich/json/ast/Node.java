package net.borisovich.json.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Node {

    boolean hasChildren();

    boolean hasParent();

    default boolean isRoot() {
        return !hasParent();
    }

    Node parent();

    void parent(Node node);

    Node[] children();

    void add(Node node);

    boolean delete(Node node);

    default boolean exist(Node node) {
        return Objects.nonNull(find(node));
    }

    default boolean exist(Class<?> type) {
        return !find(type).isEmpty();
    }

    default List<Node> find(Class<?> type) {
        List<Node>      result = new ArrayList<>();
        Predicate<Node> tester = type::isInstance;

        if (hasChildren()) {
            for (Node child : children()) {
                if (tester.test(child)) {
                    result.add(child);
                }
                result.addAll(child.find(type));
            }
        }

        return result;
    }

    default Node find(Node node) {
        Node result = null;

        if (!equals(node)) {
            if (hasChildren()) {
                for (Node child : children()) {
                    if ((result = child.equals(node) ? child : child.find(node)) != null) {
                        break;
                    }
                }
            }
        } else {
            result = this;
        }

        return result;
    }

    default void execute(Consumer<Node> executor) {
        executor.accept(this);

        if (hasChildren()) {
            for (Node child : children()) {
                child.execute(executor);
            }
        }
    }

}
