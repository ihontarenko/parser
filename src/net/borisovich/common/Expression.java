package net.borisovich.common;

public interface Expression<T> extends Priority {
    T expression();
}
