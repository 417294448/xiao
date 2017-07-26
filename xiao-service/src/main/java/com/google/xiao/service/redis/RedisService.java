package com.google.xiao.service.redis;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaoshuang on 2017/7/26.
 *
 * 利用 redisTemplate 方式
 */

@Service
public class RedisService {

        @Autowired
        private RedisTemplate<Serializable, Serializable> redisTemplate;

        private ListOperations<Serializable, Serializable> getListOps() {
            return redisTemplate.opsForList();
        }

        private HashOperations<Serializable, Serializable, Serializable> getHashOps() {
            return redisTemplate.opsForHash();
        }

        private ValueOperations<Serializable, Serializable>  getValueOps() {
            return redisTemplate.opsForValue();
        }

        private SetOperations<Serializable, Serializable> getSetOps() {
            return redisTemplate.opsForSet();
        }

        private ZSetOperations<Serializable, Serializable> getZsetOps() {
            return redisTemplate.opsForZSet();
        }

        private static Gson GSON = new Gson();

        /**
         *
         * @param pattern
         * @return
         * @deprecated
         */
        public Set<String> getKeys(String pattern) {
            Set<Serializable> keys = redisTemplate.keys(pattern);
            Set<String> set = new HashSet<String>(keys.size());
            for (Serializable s : keys) {
                set.add((String) s);
            }
            return set;
        }

        public <T> List<T> range(String key, long start, long end, Class<T> clazz) {
            List<Serializable> list = getListOps().range(key, start, end);
            List<T> results = new ArrayList<T>(list.size());
            for (Serializable e : list) {
                results.add(GSON.fromJson((String) e, clazz));
            }

            return results;
        }

        public void trim(Serializable key, long start, long end) {
            getListOps().trim(key, start, end);
        }

        public Long size(Serializable key) {
            return getListOps().size(key);
        }

        public Long leftPush(String key, Object value) {
            String json = GSON.toJson(value);
            return getListOps().leftPush(key, json);
        }

        public Long leftPushIfPresent(String key, Object value) {
            String json = GSON.toJson(value);
            return getListOps().leftPushIfPresent(key, json);
        }

        public Long leftPush(String key, Serializable pivot, Object value) {
            String json = GSON.toJson(value);
            return getListOps().leftPush(key, pivot, json);
        }

        public Long rightPush(String key, Object value) {
            String json = GSON.toJson(value);
            return getListOps().rightPush(key, json);
        }

        public Long rightPushIfPresent(String key, Object value) {
            String json = GSON.toJson(value);
            return getListOps().rightPushIfPresent(key, json);
        }

        public Long rightPush(String key, Serializable pivot, Object value) {
            String json = GSON.toJson(value);
            return getListOps().rightPush(key, pivot, json);
        }

        public void set(String key, long index, Object value) {
            String json = GSON.toJson(value);
            getListOps().set(key, index, json);
        }

        public Long remove(String key, long i, Object value) {
            String json = GSON.toJson(value);
            return getListOps().remove(key, i, json);
        }

        public <T> T index(String key, long index, Class<T> classOfT) {
            String json = (String) getListOps().index(key, index);
            return GSON.fromJson(json, classOfT);
        }

        public <T> T leftPop(String key, Class<T> classOfT) {
            String json = (String) getListOps().leftPop(key);
            return GSON.fromJson(json, classOfT);
        }

        public <T> T leftPop(String key, long timeout, TimeUnit unit,
                             Class<T> classOfT) {
            String json = (String) getListOps().leftPop(key, timeout, unit);
            return GSON.fromJson(json, classOfT);
        }

        public <T> T rightPop(Serializable key, Class<T> classOfT) {
            String json = (String) getListOps().rightPop(key);
            return GSON.fromJson(json, classOfT);
        }

        public <T> T rightPop(String key, long timeout, TimeUnit unit,
                              Class<T> classOfT) {
            String json = (String) getListOps().rightPop(key, timeout, unit);
            return GSON.fromJson(json, classOfT);
        }

        public <T> T rightPopAndLeftPush(String sourceKey, String destinationKey,
                                         Class<T> classOfT) {
            String json = (String) getListOps().rightPopAndLeftPush(sourceKey,
                    destinationKey);
            return GSON.fromJson(json, classOfT);
        }

        public <T> T rightPopAndLeftPush(Serializable sourceKey,
                                         Serializable destinationKey, long timeout, TimeUnit unit,
                                         Class<T> classOfT) {
            String json = (String) getListOps().rightPopAndLeftPush(sourceKey,
                    destinationKey, timeout, unit);
            return GSON.fromJson(json, classOfT);
        }

        public Boolean setIfAbsent(String key, Serializable value) {
            return getValueOps().setIfAbsent(key, value);
        }

        public RedisOperations<Serializable, Serializable> getOperations() {
            return getListOps().getOperations();
        }

        /**
         *
         * @param key
         * @param hkey
         * @return
         */
        public <T> T mget(String key, Object hkey, Class<T> classOfT) {
            String json = (String) getHashOps().get(key, hkey);
            return GSON.fromJson(json, classOfT);
        }

        /**
         *
         * @param key
         * @param hkey
         * @return
         */
        public Integer mgetInteger(String key, Object hkey) {
            String json = (String) getHashOps().get(key, hkey);
            String value = GSON.fromJson(json, String.class);
            return Integer.parseInt(value);
        }

        /**
         *
         * @param key
         * @param hkey
         * @return
         */
        public Long mgetLong(String key, Object hkey) {
            String json = (String) getHashOps().get(key, hkey);
            String value = GSON.fromJson(json, String.class);
            return Long.parseLong(value);
        }

        /**
         *
         * @param key
         * @param hkey
         * @return
         */
        public Double mgetDouble(String key, Object hkey) {
            String json = (String) getHashOps().get(key, hkey);
            String value = GSON.fromJson(json, String.class);
            return Double.parseDouble(value);
        }

        /**
         *
         * @param key
         * @param hkey
         * @return
         */
        public Float mgetFloat(String key, Object hkey) {
            String json = (String) getHashOps().get(key, hkey);
            String value = GSON.fromJson(json, String.class);
            return Float.parseFloat(value);
        }

        /**
         *
         * @param key
         * @param hkey
         * @param hvalue
         */
        public void mput(String key, Serializable hkey, Object hvalue) {
            String json = GSON.toJson(String.valueOf(hvalue));
            getHashOps().put(key, hkey, json);
        }

	/*
	 * public void mputAll(Serializable key, Map<? extends Serializable, ?
	 * extends Serializable> map) { hashOps.putAll(key, map); }
	 */

        public void putIfAbsent(Serializable key, Serializable hkey, Object hvalue) {
            String json = GSON.toJson(hvalue);
            getHashOps().putIfAbsent(key, hkey, json);
        }

        /**
         *
         * @param key
         * @param hkey
         */
        public void mdelete(Serializable key, Object hkey) {
            getHashOps().delete(key, hkey);
        }

        /**
         *
         * @param key
         * @param hkeys
         * @return
         */
        public <T> List<T> mmultiGet(Serializable key,
                                     Collection<Serializable> hkeys, Class<T> classOfT) {
            List<Serializable> list = getHashOps().multiGet(key, hkeys);
            List<T> results = new ArrayList<T>();
            for (Serializable e : list) {
                results.add(GSON.fromJson((String) e, classOfT));
            }
            return results;
        }

        /**
         *
         * @param key
         * @return
         */
        public <T> Map<Serializable, T> mentries(Serializable key, Class<T> classOfT) {
            Map<Serializable, Serializable> map = getHashOps().entries(key);

            Map<Serializable, T> resultMap = new HashMap<Serializable, T>();
            for (Map.Entry<Serializable, Serializable> e : map.entrySet()) {
                resultMap.put(e.getKey(),
                        GSON.fromJson((String) e.getValue(), classOfT));
            }

            return resultMap;
        }

        /**
         *
         * @param key
         * @return
         */
        public Map<Serializable, Integer> mentriesInteger(Serializable key) {
            Map<Serializable, Serializable> map = getHashOps().entries(key);

            Map<Serializable, Integer> resultMap = new HashMap<Serializable, Integer>();
            String json = null;
            for (Map.Entry<Serializable, Serializable> e : map.entrySet()) {
                json = GSON.fromJson((String) e.getValue(), String.class);
                resultMap.put(e.getKey(), Integer.parseInt(json));
            }

            return resultMap;
        }

        /**
         *
         * @param key
         * @return
         */
        public Map<Serializable, Long> mentriesLong(Serializable key) {
            Map<Serializable, Serializable> map = getHashOps().entries(key);

            Map<Serializable, Long> resultMap = new HashMap<Serializable, Long>();
            String json = null;
            for (Map.Entry<Serializable, Serializable> e : map.entrySet()) {
                json = GSON.fromJson((String) e.getValue(), String.class);
                resultMap.put(e.getKey(), Long.parseLong(json));
            }

            return resultMap;
        }

        /**
         *
         * @param key
         * @return
         */
        public Map<Serializable, Double> mentriesDouble(Serializable key) {
            Map<Serializable, Serializable> map = getHashOps().entries(key);

            Map<Serializable, Double> resultMap = new HashMap<Serializable, Double>();
            String json = null;
            for (Map.Entry<Serializable, Serializable> e : map.entrySet()) {
                json = GSON.fromJson((String) e.getValue(), String.class);
                resultMap.put(e.getKey(), Double.parseDouble(json));
            }

            return resultMap;
        }

        /**
         *
         * @param key
         * @return
         */
        public Map<Serializable, Float> mentriesFloat(Serializable key) {
            Map<Serializable, Serializable> map = getHashOps().entries(key);

            Map<Serializable, Float> resultMap = new HashMap<Serializable, Float>();
            String json = null;
            for (Map.Entry<Serializable, Serializable> e : map.entrySet()) {
                json = GSON.fromJson((String) e.getValue(), String.class);
                resultMap.put(e.getKey(), Float.parseFloat(json));
            }

            return resultMap;
        }

        /**
         *
         * @param key
         * @param hkey
         * @return
         */
        public boolean mhasKey(Serializable key, Object hkey) {
            return getHashOps().hasKey(key, hkey);
        }

        /**
         *
         * @param key
         * @param value
         */
        public void set(String key, Object value) {
            String json = GSON.toJson(value);
            getValueOps().set(key, json);
        }

        /**
         *
         * @param key
         * @param value
         * @param timeout
         * @param unit
         */
        public void set(String key, Object value, long timeout, TimeUnit unit) {
            getValueOps().set(key, GSON.toJson(value), timeout, unit);
        }

        public String get(String key) {
            Serializable value = getValueOps().get(key);
            if(value == null) {
                return null;
            }

            String v = (String)value;

            return v;
        }

        public <T> T get(String key, Class<T> classOfT) {
            Serializable value = getValueOps().get(key);
            if (value == null) {
                return null;
            }

            String v = (String) value;

            return GSON.fromJson(v, classOfT);
        }

        /**
         *
         * @param key
         * @param v
         */
        public void increment(String key, long v) {
            this.getValueOps().increment(key, v);
        }

        public void sadd(String key, String value) {
            getSetOps().add(key, value);
        }

        public void sremove(String key, String value) {
            getSetOps().remove(key, value);
        }

        public Set<Serializable> smembers(String key) {
            return getSetOps().members(key);
        }

        public boolean zadd(String key, Serializable value, Double order) {
            return getZsetOps().add(key, value, order);
        }

        public long zcount(String key, Double start, Double end) {
            return getZsetOps().count(key, start, end);
        }

        public Set<Serializable> zrange(String key, long start, long end) {
            return getZsetOps().range(key, start, end);
        }

        public Set<Serializable> zrange(String key, double start, double end) {
            return getZsetOps().rangeByScore(key, start, end);
        }

        public void zremove(String key, Object obj) {
            getZsetOps().remove(key, obj);
        }

        /**
         *
         * @param key
         */
        public void delete(Serializable key) {
            this.redisTemplate.delete(key);
        }

        /**
         *
         * @param key
         * @param timeout
         * @param unit
         */
        public void expire(Serializable key, long timeout, TimeUnit unit) {
            this.redisTemplate.expire(key, timeout, unit);
        }

        /**
         *
         * @param key
         * @param date
         */
        public void expireAt(Serializable key, Date date) {
            this.redisTemplate.expireAt(key, date);
        }

        public Boolean hasKey(Serializable key) {
            return redisTemplate.hasKey(key);
        }

        public RedisTemplate<Serializable, Serializable> getTemplate() {
            return redisTemplate;
        }

        public void setTemplate(RedisTemplate<Serializable, Serializable> template) {
            this.redisTemplate = template;
        }

        public Set<Serializable> members(String key) {
            return getSetOps().members(key);
        }

        public void delete(String key) {
            redisTemplate.delete(key);
        }

        public <T> T hget(String key, String hashKey, Class<T> classOfT) {
            Serializable value = getHashOps().get(key, hashKey);
            if (value == null) {
                return null;
            }

            return GSON.fromJson((String) value, classOfT);
        }

        public <T> T hget(String key, String hashKey, Type type) {
            Serializable value = getHashOps().get(key, hashKey);
            if (value == null) {
                return null;
            }

            return GSON.fromJson((String) value, type);
        }

        public void hput(String key, Map<? extends Serializable, String> map) {
            if (CollectionUtils.isEmpty(map))
                return;
            getHashOps().putAll(key, map);
        }

        public void hset(String key, String hashKey, Object value) {
            getHashOps().put(key, hashKey, GSON.toJson(value));
        }

        public void expire(String key, long timeout, TimeUnit timeUnit) {
            redisTemplate.expire(key, timeout, timeUnit);
        }

        public boolean exists(String key) {
            return false;
        }
}
