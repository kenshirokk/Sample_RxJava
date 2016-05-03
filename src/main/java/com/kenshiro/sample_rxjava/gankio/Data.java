package com.kenshiro.sample_rxjava.gankio;

public class Data {

	private boolean error;
	private Result[] results;
	public Result[] getResults() {
		return results;
	}

	public void setResults(Result[] results) {
		this.results = results;
	}

	public boolean getError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
	
	class Result {
		private String who;
		private String url;
		public String getWho() {
			return who;
		}
		public void setWho(String who) {
			this.who = who;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}

	}
}
