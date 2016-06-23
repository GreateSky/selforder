package com.automobile.bean;

public class WeiXin extends baseBean{
	private static final long serialVersionUID = 5975504430251599369L;
	private String url ;
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	private String token;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	

}
