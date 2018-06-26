package com.wh.model;

import java.util.Date;

public class T51 {
    private Integer f5101;

    private Date f5102;

    private String f5103;

    private String f5104;

    private String f5105;

    
    public T51() {
		super();
	}

	public T51(Integer f5101, Date f5102, String f5103, String f5104, String f5105) {
		super();
		this.f5101 = f5101;
		this.f5102 = f5102;
		this.f5103 = f5103;
		this.f5104 = f5104;
		this.f5105 = f5105;
	}

	public Integer getF5101() {
        return f5101;
    }

    public void setF5101(Integer f5101) {
        this.f5101 = f5101;
    }

    public Date getF5102() {
        return f5102;
    }

    public void setF5102(Date f5102) {
        this.f5102 = f5102;
    }

    public String getF5103() {
        return f5103;
    }

    public void setF5103(String f5103) {
        this.f5103 = f5103 == null ? null : f5103.trim();
    }

    public String getF5104() {
        return f5104;
    }

    public void setF5104(String f5104) {
        this.f5104 = f5104 == null ? null : f5104.trim();
    }

    public String getF5105() {
        return f5105;
    }

    public void setF5105(String f5105) {
        this.f5105 = f5105 == null ? null : f5105.trim();
    }
}