package pro.javadev.common.configurer;

public interface Configurator<T> {
    void configure(T object);
}
