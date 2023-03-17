package fit.wenchao.kotlinplayground.utils.cache;

public
interface ICache<K, V> {
    void del(String... key);

    V get(K key);

    void set(K key, V value, long expire);

    void set(K key, V value);
}
