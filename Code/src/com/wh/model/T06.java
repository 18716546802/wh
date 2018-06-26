package com.wh.model;

public class T06 {
    private Integer f0601;

    private String f0602;

    private Integer f0603;

    private Integer f0604;

    
    
    public T06() {
		super();
	}

	public T06(Integer f0601, String f0602, Integer f0603, Integer f0604) {
		super();
		this.f0601 = f0601;
		this.f0602 = f0602;
		this.f0603 = f0603;
		this.f0604 = f0604;
	}

	public Integer getF0601() {
        return f0601;
    }

    public void setF0601(Integer f0601) {
        this.f0601 = f0601;
    }

    public String getF0602() {
        return f0602;
    }

    public void setF0602(String f0602) {
        this.f0602 = f0602 == null ? null : f0602.trim();
    }

    public Integer getF0603() {
        return f0603;
    }

    public void setF0603(Integer f0603) {
        this.f0603 = f0603;
    }

    public Integer getF0604() {
        return f0604;
    }

    public void setF0604(Integer f0604) {
        this.f0604 = f0604;
    }
}