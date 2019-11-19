/**
 * @author : 孙留平
 * @since : 2019年5月7日 下午6:48:56
 * @see:
 */
package com.tianque.datadisplay.server.service;

import java.util.List;

import com.tianque.datadisplay.server.model.DatabaseType;

/**
 * @author : Administrator
 * @since : 2019年5月7日 下午6:48:56
 * @see :
 */
public interface DatabaseTypeService {
	/**
	 * 查询列表
	 * 
	 * @see : 获取列表
	 * @param :
	 * @return : List<DatabaseType>
	 * @return
	 */
	List<DatabaseType> getList();

	/**
	 * 添加全局参数
	 * 
	 * @see :
	 * @param :
	 * @return : DatabaseType
	 * @param databaseType
	 * @return
	 */
	DatabaseType addDatabaseType(DatabaseType databaseType);

	/**
	 * 修改数据
	 * 
	 * @see :
	 * @param :
	 * @return : DatabaseType
	 * @param databaseType
	 * @return
	 */
	DatabaseType updateDatabaseType(DatabaseType databaseType);

	/**
	 * 根据搜索内容查询列表
	 * 
	 * @see :
	 * @param :
	 * @return : List<DatabaseType>
	 * @param searchContent
	 * @return
	 */
	List<DatabaseType> searchDatabaseTypesBySearchContent(String searchContent);

	/**
	 * 根据typeEname查询
	 * 
	 * @see :
	 * @param :
	 * @return : List<DatabaseType>
	 * @param typeEname
	 * @return
	 */
	DatabaseType findDatabaseTypeByTypeEname(String typeEname);

	/**
	 * 单个删除
	 * 
	 * @see :
	 * @param :
	 * @return : void
	 * @param id
	 */
	Long deleteDatabaseType(Long id);

	/**
	 * 批量删除
	 * 
	 * @see :
	 * @param :
	 * @return : void
	 * @param ids
	 */
	Long[] deleteDatabaseType(Long[] ids);

	/**
	 * 根据主键查询
	 * 
	 * @see : 根据主键查询
	 * @param :
	 * @return : DatabaseType
	 * @param id
	 * @return
	 */
	DatabaseType selectByPrimaryKey(Long id);

	/**
	 * 根据对象查询
	 * 
	 * @see : 根据具体对象查询
	 * @param :
	 * @return : DatabaseType
	 * @param databaseType
	 * @return
	 */
	DatabaseType selectByObject(DatabaseType databaseType);
}
