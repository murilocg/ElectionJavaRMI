package server.cache;

public class CacheItem<T> {

	private long createdAt;
	private T value;

	public CacheItem(T value) {
		this.setCreatedAt(System.currentTimeMillis());
		this.setValue(value);
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
