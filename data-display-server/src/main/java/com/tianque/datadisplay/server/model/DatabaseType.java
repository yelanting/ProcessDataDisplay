/**
 * Project Name: datadisplay-server
 * File Name: DbType.java
 * Package Name: com.tianque.datadisplay.server.model
 * Date: 2019年11月7日 上午9:04:13
 * Copyright (c) 2019, qing121171@gmail.com All Rights Reserved.
 */
package com.tianque.datadisplay.server.model;

import com.tianque.datadisplay.server.model.base.BaseDomain;

/**
 * @author : 孙留平
 * @since : 2019年11月7日 上午9:04:13
 * @see :
 */
public class DatabaseType extends BaseDomain {
	/**
	 */
	private static final long serialVersionUID = 5223604871428474706L;
	private String typeName;
	private String typeEname;

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName
	 *            the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the typeEname
	 */
	public String getTypeEname() {
		return typeEname;
	}

	/**
	 * @param typeEname
	 *            the typeEname to set
	 */
	public void setTypeEname(String typeEname) {
		this.typeEname = typeEname;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString() {
		return "DatabaseType [typeName=" + typeName + ", typeEname=" + typeEname
		        + "]";
	}
}
