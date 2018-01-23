package com.koubei.cache.model;

public class m_CacheConfig {
    private String id;
    private String className;
    private String methodName;

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	private String paramType;
    /**
     * 缓存时间
     */
    private int expTime;
    /**
     * 缓存key前缀
     */
    private String pre;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public int getExpTime() {
		return expTime;
	}
	public void setExpTime(int expTime) {
		this.expTime = expTime;
	}
	public String getPre() {
		return pre;
	}
	public void setPre(String pre) {
		this.pre = pre;
	}
    
}
