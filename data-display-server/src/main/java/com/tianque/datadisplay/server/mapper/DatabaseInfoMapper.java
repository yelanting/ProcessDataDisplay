/**
 * @author : 孙留平
 * @since : 2019年5月7日 下午6:43:30
 * @see:
 */
package com.tianque.datadisplay.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tianque.datadisplay.server.model.DatabaseInfo;

/**
 * @author : Administrator
 * @since : 2019年11月7日 09:06:38
 * @see :
 */
@Mapper
public interface DatabaseInfoMapper {

	/**
	 * 所有
	 * 
	 * @see :
	 * @param :
	 * @return : List<DatabaseInfo>
	 * @return
	 */
	List<DatabaseInfo> findAll();

	/**
	 * 插入
	 * 
	 * @see :
	 * @param :
	 * @return : int
	 * @param record
	 * @return
	 */
	int insert(DatabaseInfo record);

	/**
	 * 根据对象更新
	 * 
	 * @see :
	 * @param :
	 * @return : int
	 * @param record
	 * @return
	 */

	int updateByPrimaryKey(DatabaseInfo record);

	/**
	 * 根据主键单个删除
	 * 
	 * @see :
	 * @param :
	 * @return : int
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * 根据主键批量删除
	 * 
	 * @see :
	 * @param :
	 * @return : int
	 * @param ids
	 * @return
	 */
	int deleteByIds(Long[] ids);

	/**
	 * 根据主键查询单个实体
	 * 
	 * @see :
	 * @param :
	 * @return : DatabaseInfo
	 * @param id
	 * @return
	 */
	DatabaseInfo selectByPrimaryKey(Long id);

	/**
	 * 根据单个实体查询
	 * 
	 * @see :
	 * @param :
	 * @return : DatabaseInfo
	 * @param record
	 * @return
	 */
	DatabaseInfo selectByObject(DatabaseInfo record);

	/**
	 * 根据key查询列表-模糊
	 * 
	 * @see :
	 * @param :
	 * @return : List<DatabaseInfo>
	 * @param searchContent
	 * @return
	 */
	List<DatabaseInfo> findDatabaseInfosByDatabaseNameLike(
	        String searchContent);

	/**
	 * 根据主键查询，精确
	 * 
	 * @see :
	 * @param :
	 * @return : DatabaseInfo
	 * @param databaseName
	 * @return
	 */
	DatabaseInfo findDatabaseInfoByDatabaseName(String databaseName);

	/**
	 * 查询非当前实体列表
	 * 
	 * @see :
	 * @param :
	 * @return : List<DatabaseInfo>
	 * @param dataBaseType
	 * @return
	 */
	List<DatabaseInfo> findOtherDatabaseInfosByObject(
	        DatabaseInfo dataBaseType);
}
