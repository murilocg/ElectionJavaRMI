package server.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Cache<T> {

	private long timeToLive;
	private Map<String, CacheItem<T>> cacheMap;
	private boolean close;
	private Thread job;

	public Cache(long timeToLive) {
		this.timeToLive = timeToLive;
		this.cacheMap = new HashMap<String, CacheItem<T>>();
		this.close = false;
		this.job = new Thread(() -> periodicCleaner());
		this.job.setDaemon(true);
		this.job.start();
	}

	public void put(String key, T value) {
		synchronized (this.cacheMap) {
			CacheItem<T> item = new CacheItem<T>(value);
			this.cacheMap.put(key, item);
		}
	}

	public T get(String key) {
		synchronized (this.cacheMap) {
			CacheItem<T> item = this.cacheMap.get(key);
			if (item == null)
				return null;
			return item.getValue();
		}
	}

	private void periodicCleaner() {
		while (!isClose()) {
			try {
				Thread.sleep(this.timeToLive);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.clean();
		}
	}

	private void clean() {

		Iterator<Entry<String, CacheItem<T>>> iterator = this.cacheMap.entrySet().stream()
				.filter(e -> isExpired(e.getValue(), this.timeToLive)).iterator();
		
		while(iterator.hasNext())
			this.remove(iterator.next().getKey());
		
	}

	private boolean remove(String key) {
		synchronized (cacheMap) {
			return this.cacheMap.remove(key) != null;
		}
	}

	private boolean isExpired(CacheItem<T> item, long timeToLive) {
		return item.getCreatedAt() + timeToLive >= System.currentTimeMillis();
	}

	private boolean isClose() {
		return close;
	}

	public void close() {
		this.close = true;
	}
}