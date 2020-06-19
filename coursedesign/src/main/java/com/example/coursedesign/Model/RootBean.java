package com.example.coursedesign.Model;


public class RootBean {
	private String rootId;
	private String rootName;

	public RootBean() {
	}

	public RootBean(String rootId, String rootName) {
		this.rootId = rootId;
		this.rootName = rootName;
	}

	public String getRootId() {
		return rootId;
	}

	public void setRootId(String rootId) {
		this.rootId = rootId;
	}

	public String getRootName() {
		return rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}
}
