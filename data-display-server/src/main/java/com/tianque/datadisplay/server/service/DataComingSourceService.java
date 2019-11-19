/**
 * @author : 孙留平
 * @since : 2019年5月7日 下午6:48:56
 * @see:
 */
package com.tianque.datadisplay.server.service;

import java.util.HashMap;
import java.util.List;

import com.tianque.datadisplay.server.model.DataComingSource;

/**
 * @author : Administrator
 * @since : 2019年5月7日 下午6:48:56
 * @see :
 */
public interface DataComingSourceService {
	/**
	 * 查询列表
	 * 
	 * @see : 获取列表
	 * @param :
	 * @return : List<DataComingSource>
	 * @return
	 */
	List<DataComingSource> getList();

	/**
	 * 添加全局参数
	 * 
	 * @see :
	 * @param :
	 * @return : DataComingSource
	 * @param dataComingSource
	 * @return
	 */
	DataComingSource addDataComingSource(DataComingSource dataComingSource);

	/**
	 * 修改数据
	 * 
	 * @see :
	 * @param :
	 * @return : DataComingSource
	 * @param dataComingSource
	 * @return
	 */
	DataComingSource updateDataComingSource(DataComingSource dataComingSource);

	/**
	 * 根据搜索内容查询列表
	 * 
	 * @see :
	 * @param :
	 * @return : List<DataComingSource>
	 * @param searchContent
	 * @return
	 */
	List<DataComingSource> searchDataComingSourcesBySearchContent(
	        String searchContent);

	/**
	 * 根据sourceName查询
	 * 
	 * @see :
	 * @param :
	 * @return : List<DataComingSource>
	 * @param sourceName
	 * @return
	 */
	DataComingSource findDataComingSourceBySourceName(String sourceName);

	/**
	 * 单个删除
	 * 
	 * @see :
	 * @param :
	 * @return : void
	 * @param id
	 */
	Long deleteDataComingSource(Long id);

	/**
	 * 批量删除
	 * 
	 * @see :
	 * @param :
	 * @return : void
	 * @param ids
	 */
	Long[] deleteDataComingSource(Long[] ids);

	/**
	 * 根据主键查询
	 * 
	 * @see : 根据主键查询
	 * @param :
	 * @return : DataComingSource
	 * @param id
	 * @return
	 */
	DataComingSource selectByPrimaryKey(Long id);

	/**
	 * 根据对象查询
	 * 
	 * @see : 根据具体对象查询
	 * @param :
	 * @return : DataComingSource
	 * @param dataComingSource
	 * @return
	 */
	DataComingSource selectByObject(DataComingSource dataComingSource);

	/**
	 * 执行数据库查询
	 * 
	 * @see :
	 * @param :
	 * @return : List<JSONObject>
	 * @param dataComingSource
	 * @return
	 */
	List<HashMap<String, Object>> executeSql(DataComingSource dataComingSource);
}
