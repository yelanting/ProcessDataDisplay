/**
 * @author : 孙留平
 * @since : 2019年5月7日 下午7:15:13
 * @see:
 */
package com.tianque.datadisplay.server.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.commons.util.core.base.ValidationUtil;
import com.tianque.commons.util.exception.base.BusinessValidationException;
import com.tianque.datadisplay.server.mapper.DataComingSourceMapper;
import com.tianque.datadisplay.server.model.DataComingSource;
import com.tianque.datadisplay.server.model.DatabaseInfo;
import com.tianque.datadisplay.server.model.DatabaseType;
import com.tianque.datadisplay.server.service.DataComingSourceService;
import com.tianque.datadisplay.server.service.DatabaseInfoService;
import com.tianque.datadisplay.server.service.DatabaseTypeService;
import com.tianque.datadisplay.server.util.database.AbstractDatabaseOperation;
import com.tianque.datadisplay.server.util.database.DataBaseClientUtil;

/**
 * @author : Administrator
 * @since : 2019年5月7日 下午7:15:13
 * @see :
 */

@Service("dataComingSourceService")
public class DataComingSourceServiceImpl implements DataComingSourceService {

	private static final Logger logger = LoggerFactory
	        .getLogger(DataComingSourceServiceImpl.class);

	@Autowired
	private DataComingSourceMapper dataComingSourceMapper;

	@Autowired
	private DatabaseInfoService databaseInfoService;

	@Autowired
	private DatabaseTypeService databaseTypeService;

	/**
	 * @see 获取列表
	 */
	@Override
	public List<DataComingSource> getList() {
		logger.debug("获取数据来源信息列表");
		List<DataComingSource> foundList = dataComingSourceMapper.findAll();

		foundList.forEach(eachItem -> {
			if (null != eachItem.getDatabaseInfoId()) {
				eachItem.setDatabaseInfo(databaseInfoService
				        .selectByPrimaryKey(eachItem.getDatabaseInfoId()));
			}
		});
		return foundList;
	}

	/**
	 * @see 添加数据来源信息
	 */
	@Override
	public DataComingSource addDataComingSource(
	        DataComingSource dataComingSource) {
		logger.info("添加数据来源信息:{}", dataComingSource);
		if (checkSourceNameExists(dataComingSource.getSourceName())) {
			throw new BusinessValidationException("该sourceName已经存在，不可重复添加");
		}

		validateInput(dataComingSource);

		try {

			int resultKey = dataComingSourceMapper.insert(dataComingSource);
			dataComingSource.setId(Long.valueOf(resultKey));
			return dataComingSource;
		} catch (Exception e) {
			logger.error("添加数据来源信息失败,错误信息:{}", e.getMessage());
			throw new BusinessValidationException("添加数据来源信息失败");
		}
	}

	/**
	 * 校验输入内容
	 * 
	 * @see :
	 * @param :
	 * @return : void
	 * @param dataComingSource
	 */
	private void validateInput(DataComingSource dataComingSource) {
		ValidationUtil.validateStringNullOrEmpty(
		        dataComingSource.getSourceName(), "数据来源名称不能为空");

		ValidationUtil.validateStringNullOrEmpty(
		        dataComingSource.getExecuteSql(), "待执行的sql不能为空");

		if (null == dataComingSource.getDatabaseInfoId()) {
			throw new BusinessValidationException("数据来源关联的数据库信息id不能为空");
		}
	}

	/**
	 * @see com.tianque.yunxiao.connect.server.service.DataComingSourceService#updateDataComingSource(com.tianque.yunxiao.connect.server.model.DataComingSource)
	 */
	@Override
	public DataComingSource updateDataComingSource(
	        DataComingSource dataComingSource) {
		logger.info("修改数据来源信息");
		ValidationUtil.validateNull(dataComingSource.getId(), null);

		if (checkSourceNameExists(dataComingSource)) {
			throw new BusinessValidationException(
			        "该sourceName已经存在，不可修改为此sourceName");
		}

		DataComingSource currentDataComingSource = selectByObject(
		        dataComingSource);

		if (null == currentDataComingSource) {
			throw new BusinessValidationException("待修改对象不存在，不能修改");
		}

		validateInput(currentDataComingSource);
		try {
			this.dataComingSourceMapper.updateByPrimaryKey(dataComingSource);
			return dataComingSource;
		} catch (Exception e) {
			throw new BusinessValidationException("修改失败");
		}

	}

	/**
	 * @see com.tianque.yunxiao.connect.server.service.DataComingSourceService#searchDataComingSourcesBySearchContent(java.lang.String)
	 */
	@Override
	public List<DataComingSource> searchDataComingSourcesBySearchContent(
	        String searchContent) {
		logger.info("根据搜索条件:{}查询数据来源", searchContent);
		List<DataComingSource> foundList = dataComingSourceMapper
		        .findDataComingSourcesBySourceNameLike(searchContent);
		foundList.forEach(eachItem -> {
			if (null != eachItem.getDatabaseInfoId()) {
				eachItem.setDatabaseInfo(databaseInfoService
				        .selectByPrimaryKey(eachItem.getDatabaseInfoId()));
			}
		});

		return foundList;
	}

	/**
	 * @see 单个删除
	 */
	@Override
	public Long deleteDataComingSource(Long id) {
		logger.info("删除id为:{}的数据来源信息", id);
		ValidationUtil.validateNull(id, null);
		dataComingSourceMapper.deleteByPrimaryKey(id);
		return id;
	}

	/**
	 * 批量删除
	 * 
	 * @see com.tianque.yunxiao.connect.server.service.DataComingSourceService#deleteDataComingSource(java.lang.Long[])
	 */
	@Override
	public Long[] deleteDataComingSource(Long[] ids) {
		ValidationUtil.validateNull(ids, null);
		ValidationUtil.validateArrayNullOrEmpty(ids, null);
		logger.info("批量删除以下数据来源信息:{}", Arrays.toString(ids));
		dataComingSourceMapper.deleteByIds(ids);
		return ids;
	}

	/**
	 * @see com.tianque.yunxiao.connect.server.service.DataComingSourceService#selectByPrimaryKey(java.lang.Long)
	 */
	@Override
	public DataComingSource selectByPrimaryKey(Long id) {
		logger.info("根据主键查询");
		ValidationUtil.validateNull(id, null);
		return dataComingSourceMapper.selectByPrimaryKey(id);
	}

	/**
	 * @see
	 *      com.tianque.yunxiao.connect.server.service.DataComingSourceService#selectByObject(com.tianque.yunxiao.connect.server.model
	 *      .DataComingSource)
	 */
	@Override
	public DataComingSource selectByObject(DataComingSource dataComingSource) {
		return selectByPrimaryKey(dataComingSource.getId());
	}

	/**
	 * @see :检查
	 * @param :
	 * @return : boolean
	 * @return
	 */
	private boolean checkSourceNameExists(String paramKey) {
		List<DataComingSource> currentList = searchDataComingSourcesBySearchContent(
		        paramKey);

		return !currentList.isEmpty();
	}

	/**
	 * @see :检查
	 * @param :
	 * @return : boolean
	 * @return
	 */
	private boolean checkSourceNameExists(DataComingSource dataComingSource) {
		List<DataComingSource> currentList = null;
		if (null != dataComingSource.getId()) {
			currentList = dataComingSourceMapper
			        .findOtherDataComingSourcesByObject(dataComingSource);
		} else {
			currentList = searchDataComingSourcesBySearchContent(
			        dataComingSource.getSourceName());
		}
		return !currentList.isEmpty();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.datadisplay.server.service.DataComingSourceService#findDataComingSourceBySourceName(java.lang.String)
	 * @param sourceName
	 * @return
	 */
	@Override
	public DataComingSource findDataComingSourceBySourceName(
	        String sourceName) {
		logger.info("根据来源名称:{}查询来源信息", sourceName);
		return dataComingSourceMapper
		        .findDataComingSourceBySourceName(sourceName);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.datadisplay.server.service.DataComingSourceService#executeSql(com.tianque.datadisplay.server.model.DataComingSource)
	 * @param dataComingSource
	 * @return
	 */
	@Override
	public List<HashMap<String, Object>> executeSql(
	        DataComingSource dataComingSource) {
		logger.info("开始执行sql:{}", dataComingSource);
		validateInput(dataComingSource);

		Long databaseInfoId = dataComingSource.getDatabaseInfoId();
		DatabaseInfo databaseInfo = databaseInfoService
		        .selectByPrimaryKey(databaseInfoId);
		Long databaseTypeId = databaseInfo.getDatabaseTypeId();
		DatabaseType databaseType = databaseTypeService
		        .selectByPrimaryKey(databaseTypeId);
		AbstractDatabaseOperation abstractDatabaseOperation = DataBaseClientUtil
		        .parseDatabaseOperation(databaseInfo, databaseType);

		List<HashMap<String, Object>> executeResultList = abstractDatabaseOperation
		        .executeQueryWithJsonObjectResult(
		                dataComingSource.getExecuteSql());

		return executeResultList;
	}
}
