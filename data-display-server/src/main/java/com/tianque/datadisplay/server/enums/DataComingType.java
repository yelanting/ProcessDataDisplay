/**
 * Project Name: datadisplay-server
 * File Name: DataComingType.java
 * Package Name: com.tianque.datadisplay.server.enums
 * Date: 2019年11月11日 下午9:17:56
 * Copyright (c) 2019, qing121171@gmail.com All Rights Reserved.
 */
package com.tianque.datadisplay.server.enums;

/**
 * @author : 孙留平
 * @since : 2019年11月11日 下午9:17:56
 * @see : 数据来源类型
 */
public enum DataComingType {
	/**
	 * 数据库
	 */
	DATABASE,
	/**
	 * 版本控制
	 */
	VCS;
	private DataComingType() {

	}

	private DataComingType(String comingTypeName, String comingTypeDesc) {
		this.comingTypeDesc = comingTypeDesc;
		this.comingTypeName = comingTypeName;
	}

	private String comingTypeName;

	private String comingTypeDesc;

	/**
	 * @return the comingTypeName
	 */
	public String getComingTypeName() {
		return comingTypeName;
	}

	/**
	 * @param comingTypeName
	 *            the comingTypeName to set
	 */
	public void setComingTypeName(String comingTypeName) {
		this.comingTypeName = comingTypeName;
	}

	/**
	 * @return the comingTypeDesc
	 */
	public String getComingTypeDesc() {
		return comingTypeDesc;
	}

	/**
	 * @param comingTypeDesc
	 *            the comingTypeDesc to set
	 */
	public void setComingTypeDesc(String comingTypeDesc) {
		this.comingTypeDesc = comingTypeDesc;
	}

}
