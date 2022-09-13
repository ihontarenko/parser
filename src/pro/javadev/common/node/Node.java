package pro.javadev.common.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static pro.javadev.common.node.Node.Direction.DOWN;
import static pro.javadev.common.node.Node.Direction.UP;

@SuppressWarnings({"unused"})
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
        return find(node, DOWN);
    }

    default Node find(Node node, Direction direction) {
        Node result = null;

        if (!this.equals(node)) {
            switch (direction) {
                case UP:
                    if (!isRoot()) {
                        Node parent = parent();
                        if (parent.equals(node)) {
                            result = parent;
                        } else {
                            result = parent.find(node, UP);
                        }
                    }
                    break;
                case DOWN:
                    if (hasChildren()) {
                        for (Node child : children()) {
                            if ((result = child.equals(node) ? child : child.find(node)) != null) {
                                break;
                            }
                        }
                    }
                    break;
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

    enum Direction {DOWN, UP}

}
