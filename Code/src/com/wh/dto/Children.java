package com.wh.dto;

import java.util.List;

public class Children {
		private Integer id;
		private String state;
		private String text;
		private List<Children> children;
		
		
		public List<Children> getChildren() {
			return children;
		}
		public void setChildren(List<Children> children) {
			this.children = children;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
}
