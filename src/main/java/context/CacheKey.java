package context;

import java.util.HashMap;
import java.util.Map;

public enum CacheKey {

    RESPONSE;

    private static final Map<CacheKey, Object> cacheMap = new HashMap<>();

    public static void putData(CacheKey key, Object data) {
        cacheMap.put(key, data);
    }

    public static Object getCacheData(CacheKey key) {
        return cacheMap.get(key);
    }

    public static void clearCache(CacheKey key) {
        cacheMap.put(key, null);
    }

}
