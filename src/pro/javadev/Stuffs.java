package pro.javadev;

import java.util.Map;

public enum Stuffs implements StuffKeeper {

    A;



    private Map<Class<?>, Storage<String, Object>> storages;


    @Override
    public void remember() {
        new Object() {
           public void test() {

           }
        }.test();
    }

    @Override
    public void forget(Object key) {

    }

    @Override
    public <T> T recall() {
        return null;
    }

}
