package com.wh.model;

public class T50 {
    private Integer f5001;

    private String f5002;

    private String f5003;

    private String f5004;

    private String f5005;

    
    public T50() {
		super();
	}

	public T50(Integer f5001, String f5002, String f5003, String f5004, String f5005) {
		super();
		this.f5001 = f5001;
		this.f5002 = f5002;
		this.f5003 = f5003;
		this.f5004 = f5004;
		this.f5005 = f5005;
	}

	public Integer getF5001() {
        return f5001;
    }

    public void setF5001(Integer f5001) {
        this.f5001 = f5001;
    }

    public String getF5002() {
        return f5002;
    }

    public void setF5002(String f5002) {
        this.f5002 = f5002 == null ? null : f5002.trim();
    }

    public String getF5003() {
        return f5003;
    }

    public void setF5003(String f5003) {
        this.f5003 = f5003 == null ? null : f5003.trim();
    }

    public String getF5004() {
        return f5004;
    }

    public void setF5004(String f5004) {
        this.f5004 = f5004 == null ? null : f5004.trim();
    }

    public String getF5005() {
        return f5005;
    }

    public void setF5005(String f5005) {
        this.f5005 = f5005 == null ? null : f5005.trim();
    }
}