/**
 * @author : 孙留平
 * @since : 2019年5月7日 下午6:48:56
 * @see:
 */
package com.tianque.datadisplay.server.service;

import java.util.List;

import com.tianque.datadisplay.server.model.DatabaseInfo;

/**
 * @author : Administrator
 * @since : 2019年5月7日 下午6:48:56
 * @see :
 */
public interface DatabaseInfoService {
	/**
	 * 查询列表
	 * 
	 * @see : 获取列表
	 * @param :
	 * @return : List<DatabaseInfo>
	 * @return
	 */
	List<DatabaseInfo> getList();

	/**
	 * 添加全局参数
	 * 
	 * @see :
	 * @param :
	 * @return : DatabaseInfo
	 * @param databaseInfo
	 * @return
	 */
	DatabaseInfo addDatabaseInfo(DatabaseInfo databaseInfo);

	/**
	 * 修改数据
	 * 
	 * @see :
	 * @param :
	 * @return : DatabaseInfo
	 * @param databaseInfo
	 * @return
	 */
	DatabaseInfo updateDatabaseInfo(DatabaseInfo databaseInfo);

	/**
	 * 根据搜索内容查询列表
	 * 
	 * @see :
	 * @param :
	 * @return : List<DatabaseInfo>
	 * @param searchContent
	 * @return
	 */
	List<DatabaseInfo> searchDatabaseInfosBySearchContent(String searchContent);

	/**
	 * 根据databaseName查询
	 * 
	 * @see :
	 * @param :
	 * @return : List<DatabaseInfo>
	 * @param databaseName
	 * @return
	 */
	DatabaseInfo findDatabaseInfoByDatabaseName(String databaseName);

	/**
	 * 单个删除
	 * 
	 * @see :
	 * @param :
	 * @return : void
	 * @param id
	 */
	Long deleteDatabaseInfo(Long id);

	/**
	 * 批量删除
	 * 
	 * @see :
	 * @param :
	 * @return : void
	 * @param ids
	 */
	Long[] deleteDatabaseInfo(Long[] ids);

	/**
	 * 根据主键查询
	 * 
	 * @see : 根据主键查询
	 * @param :
	 * @return : DatabaseInfo
	 * @param id
	 * @return
	 */
	DatabaseInfo selectByPrimaryKey(Long id);

	/**
	 * 根据对象查询
	 * 
	 * @see : 根据具体对象查询
	 * @param :
	 * @return : DatabaseInfo
	 * @param databaseInfo
	 * @return
	 */
	DatabaseInfo selectByObject(DatabaseInfo databaseInfo);
}
