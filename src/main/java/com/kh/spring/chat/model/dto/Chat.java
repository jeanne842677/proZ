package com.kh.spring.chat.model.dto;

public class Chat {

	public String name;
	public String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Chat [name=" + name + ", content=" + content + "]";
	}

}
