package pro.javadev.common;

public interface Expression<T> extends Priority {
    T expression();
}
