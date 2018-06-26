package com.wh.model;

public class T02 {
    private Integer f0201;

    private String f0202;

    private String f0203;

    private String f0204;

    private String f0205;

    private String f0206;

    private String f0207;

    private String f0208;

    private Integer f0209;

    public T02(){}
    public T02(Integer f0201, String f0202, String f0203, String f0204, String f0205, String f0206, String f0207,
			String f0208, Integer f0209) {
		super();
		this.f0201 = f0201;
		this.f0202 = f0202;
		this.f0203 = f0203;
		this.f0204 = f0204;
		this.f0205 = f0205;
		this.f0206 = f0206;
		this.f0207 = f0207;
		this.f0208 = f0208;
		this.f0209 = f0209;
	}

	public Integer getF0201() {
        return f0201;
    }

    public void setF0201(Integer f0201) {
        this.f0201 = f0201;
    }

    public String getF0202() {
        return f0202;
    }

    public void setF0202(String f0202) {
        this.f0202 = f0202 == null ? null : f0202.trim();
    }

    public String getF0203() {
        return f0203;
    }

    public void setF0203(String f0203) {
        this.f0203 = f0203 == null ? null : f0203.trim();
    }

    public String getF0204() {
        return f0204;
    }

    public void setF0204(String f0204) {
        this.f0204 = f0204 == null ? null : f0204.trim();
    }

    public String getF0205() {
        return f0205;
    }

    public void setF0205(String f0205) {
        this.f0205 = f0205 == null ? null : f0205.trim();
    }

    public String getF0206() {
        return f0206;
    }

    public void setF0206(String f0206) {
        this.f0206 = f0206 == null ? null : f0206.trim();
    }

    public String getF0207() {
        return f0207;
    }

    public void setF0207(String f0207) {
        this.f0207 = f0207 == null ? null : f0207.trim();
    }

    public String getF0208() {
        return f0208;
    }

    public void setF0208(String f0208) {
        this.f0208 = f0208 == null ? null : f0208.trim();
    }

    public Integer getF0209() {
        return f0209;
    }

    public void setF0209(Integer f0209) {
        this.f0209 = f0209;
    }
    
    private T01 t01;
    private T06 t06;
    
    public T01 getT01() {
		return t01;
	}
	public void setT01(T01 t01) {
		this.t01 = t01;
	}
	public T06 getT06() {
		return t06;
	}
	public void setT06(T06 t06) {
		this.t06 = t06;
	}
}