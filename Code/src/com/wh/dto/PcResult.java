package com.wh.dto;

public class PcResult<T>{


	private String tree;
	private boolean success;
	private T data;
	private String error;
	
	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public PcResult(boolean success, T data,String t,String error) {
		this.tree = t;
		this.success = success;
		this.data = data;
	}
	
}
