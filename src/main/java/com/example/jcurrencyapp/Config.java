package com.example.jcurrencyapp;

public class Config {
	public static final int MAX_BACK_DAYS = 365;
	private int maxBackDays;
	private boolean useCache;
	private boolean forceRead;

	public Config() {
		this.setMaxBackDays(10);
		this.useCache = true;
		this.setForceRead(false);
	}

	public int getMaxBackDays() {
		return maxBackDays;
	}

	public void setMaxBackDays(int backDays) {
		if (backDays >= 0 && backDays <= MAX_BACK_DAYS) {
			this.maxBackDays = backDays;
		}
	}

	public boolean isUseCache() {
		return useCache;
	}

	public void setUseCache(boolean useCache) {
		this.useCache = useCache;
	}

	public boolean isForceRead() {
		return forceRead;
	}

	public void setForceRead(boolean forceRead) {
		this.forceRead = forceRead;
	}

}
