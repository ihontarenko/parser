package pro.javadev;

@SuppressWarnings({"unused"})
public interface StuffKeeper {

    void remember();

    void forget(Object key);

    <T> T recall();

}
