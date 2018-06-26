package com.wh.model;

public class T09 {
    private Integer f0901;

    private Integer f0101;

    private String f0902;

    private String f0903;

    private String f0904;

    private String f0905;

    private String f0906;

    private String f0907;

    private T01 t01;
    
    public T01 getT01() {
		return t01;
	}

	public void setT01(T01 t01) {
		this.t01 = t01;
	}

	public T09() {
		super();
	}

	public T09(Integer f0901, Integer f0101, String f0902, String f0903, String f0904, String f0905, String f0906,
			String f0907) {
		super();
		this.f0901 = f0901;
		this.f0101 = f0101;
		this.f0902 = f0902;
		this.f0903 = f0903;
		this.f0904 = f0904;
		this.f0905 = f0905;
		this.f0906 = f0906;
		this.f0907 = f0907;
	}

	public Integer getF0901() {
        return f0901;
    }

    public void setF0901(Integer f0901) {
        this.f0901 = f0901;
    }

    public Integer getF0101() {
        return f0101;
    }

    public void setF0101(Integer f0101) {
        this.f0101 = f0101;
    }

    public String getF0902() {
        return f0902;
    }

    public void setF0902(String f0902) {
        this.f0902 = f0902 == null ? null : f0902.trim();
    }

    public String getF0903() {
        return f0903;
    }

    public void setF0903(String f0903) {
        this.f0903 = f0903 == null ? null : f0903.trim();
    }

    public String getF0904() {
        return f0904;
    }

    public void setF0904(String f0904) {
        this.f0904 = f0904 == null ? null : f0904.trim();
    }

    public String getF0905() {
        return f0905;
    }

    public void setF0905(String f0905) {
        this.f0905 = f0905 == null ? null : f0905.trim();
    }

    public String getF0906() {
        return f0906;
    }

    public void setF0906(String f0906) {
        this.f0906 = f0906 == null ? null : f0906.trim();
    }

    public String getF0907() {
        return f0907;
    }

    public void setF0907(String f0907) {
        this.f0907 = f0907 == null ? null : f0907.trim();
    }
}