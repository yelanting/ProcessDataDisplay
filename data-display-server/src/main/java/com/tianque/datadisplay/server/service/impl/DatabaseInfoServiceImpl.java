/**
 * @author : 孙留平
 * @since : 2019年5月7日 下午7:15:13
 * @see:
 */
package com.tianque.datadisplay.server.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.commons.util.core.base.ValidationUtil;
import com.tianque.commons.util.exception.base.BusinessValidationException;
import com.tianque.datadisplay.server.mapper.DatabaseInfoMapper;
import com.tianque.datadisplay.server.model.DatabaseInfo;
import com.tianque.datadisplay.server.service.DatabaseInfoService;
import com.tianque.datadisplay.server.service.DatabaseTypeService;

/**
 * @author : Administrator
 * @since : 2019年5月7日 下午7:15:13
 * @see :
 */

@Service("databaseInfoService")
public class DatabaseInfoServiceImpl implements DatabaseInfoService {

	private static final Logger logger = LoggerFactory
	        .getLogger(DatabaseInfoServiceImpl.class);

	@Autowired
	private DatabaseInfoMapper databaseInfoMapper;

	@Autowired
	private DatabaseTypeService databaseTypeService;

	/**
	 * @see 获取列表
	 */
	@Override
	public List<DatabaseInfo> getList() {
		logger.debug("获取数据库信息列表");
		List<DatabaseInfo> databaseInfos = databaseInfoMapper.findAll();

		if (databaseInfos.isEmpty()) {
			return new ArrayList<>();
		}

		logger.debug("数据库信息列表:{}", databaseInfos);
		databaseInfos.forEach(eachItem -> {
			if (null != eachItem.getDatabaseTypeId()) {
				eachItem.setDatabaseType(databaseTypeService
				        .selectByPrimaryKey(eachItem.getDatabaseTypeId()));
			}
		});
		return databaseInfos;
	}

	/**
	 * @see 添加数据库信息
	 */
	@Override
	public DatabaseInfo addDatabaseInfo(DatabaseInfo databaseInfo) {
		logger.info("添加数据库信息:{}", databaseInfo);
		if (checkDatabaseNameExists(databaseInfo.getDatabaseName())) {
			throw new BusinessValidationException("该ename已经存在，不可重复添加");
		}

		validateInput(databaseInfo);
		try {

			int resultKey = databaseInfoMapper.insert(databaseInfo);
			databaseInfo.setId(Long.valueOf(resultKey));
			return databaseInfo;
		} catch (Exception e) {
			logger.error("添加数据库信息失败,错误信息:{}", e.getMessage());
			throw new BusinessValidationException("添加数据库信息失败");
		}
	}

	/**
	 * 校验传入的表单信息
	 * 
	 * @see :
	 * @param :
	 * @return : void
	 * @param databaseInfo
	 */
	private void validateInput(DatabaseInfo databaseInfo) {

		ValidationUtil.validateStringNullOrEmpty(databaseInfo.getDatabaseName(),
		        "数据库信息名称不能为空");

		ValidationUtil.validateStringNullOrEmpty(databaseInfo.getDatabaseIp(),
		        "数据库ip不能为空");

		ValidationUtil.validateStringNullOrEmpty(databaseInfo.getInstanceName(),
		        "数据库实例名称不能为空");

		if (null == databaseInfo.getDatabaseTypeId()) {
			throw new BusinessValidationException("必须为该数据库信息，选择一个数据库类型");
		}
	}

	/**
	 * @see com.tianque.yunxiao.connect.server.service.DatabaseInfoService#updateDatabaseInfo(com.tianque.yunxiao.connect.server.model.DatabaseInfo)
	 */
	@Override
	public DatabaseInfo updateDatabaseInfo(DatabaseInfo databaseInfo) {
		logger.info("修改数据库信息");
		ValidationUtil.validateNull(databaseInfo.getId(), null);

		if (checkDatabaseNameExists(databaseInfo)) {
			throw new BusinessValidationException("该数据库名已经存在，不可修改为该名字");
		}

		validateInput(databaseInfo);

		DatabaseInfo currentDatabaseInfo = selectByObject(databaseInfo);

		if (null == currentDatabaseInfo) {
			throw new BusinessValidationException("待修改对象不存在，不能修改");
		}

		try {
			this.databaseInfoMapper.updateByPrimaryKey(databaseInfo);
			return databaseInfo;
		} catch (Exception e) {
			throw new BusinessValidationException("修改失败");
		}

	}

	/**
	 * @see com.tianque.yunxiao.connect.server.service.DatabaseInfoService#searchDatabaseInfosBySearchContent(java.lang.String)
	 */
	@Override
	public List<DatabaseInfo> searchDatabaseInfosBySearchContent(
	        String searchContent) {
		logger.info("根据查询条件:{}查询数据库信息", searchContent);
		List<DatabaseInfo> foundList = databaseInfoMapper
		        .findDatabaseInfosByDatabaseNameLike(searchContent);

		if (foundList.isEmpty()) {
			return foundList;
		}

		foundList.forEach(eachItem -> {
			if (null != eachItem.getDatabaseTypeId()) {
				eachItem.setDatabaseType(databaseTypeService
				        .selectByPrimaryKey(eachItem.getDatabaseTypeId()));
			}
		});

		return foundList;
	}

	/**
	 * @see 单个删除
	 */
	@Override
	public Long deleteDatabaseInfo(Long id) {
		logger.info("删除id为:{}的数据库信息", id);
		ValidationUtil.validateNull(id, null);
		databaseInfoMapper.deleteByPrimaryKey(id);
		return id;
	}

	/**
	 * 批量删除
	 * 
	 * @see com.tianque.yunxiao.connect.server.service.DatabaseInfoService#deleteDatabaseInfo(java.lang.Long[])
	 */
	@Override
	public Long[] deleteDatabaseInfo(Long[] ids) {
		ValidationUtil.validateNull(ids, null);
		ValidationUtil.validateArrayNullOrEmpty(ids, null);
		logger.info("批量删除以下数据库信息:{}", Arrays.toString(ids));
		databaseInfoMapper.deleteByIds(ids);
		return ids;
	}

	/**
	 * @see com.tianque.yunxiao.connect.server.service.DatabaseInfoService#selectByPrimaryKey(java.lang.Long)
	 */
	@Override
	public DatabaseInfo selectByPrimaryKey(Long id) {
		logger.info("根据主键查询");
		ValidationUtil.validateNull(id, null);
		return databaseInfoMapper.selectByPrimaryKey(id);
	}

	/**
	 * @see
	 *      com.tianque.yunxiao.connect.server.service.DatabaseInfoService#selectByObject(com.tianque.yunxiao.connect.server.model
	 *      .DatabaseInfo)
	 */
	@Override
	public DatabaseInfo selectByObject(DatabaseInfo databaseInfo) {
		return selectByPrimaryKey(databaseInfo.getId());
	}

	/**
	 * @see :检查
	 * @param :
	 * @return : boolean
	 * @return
	 */
	private boolean checkDatabaseNameExists(String paramKey) {
		List<DatabaseInfo> currentList = searchDatabaseInfosBySearchContent(
		        paramKey);

		return !currentList.isEmpty();
	}

	/**
	 * @see :检查
	 * @param :
	 * @return : boolean
	 * @return
	 */
	private boolean checkDatabaseNameExists(DatabaseInfo databaseInfo) {
		List<DatabaseInfo> currentList = null;
		if (null != databaseInfo.getId()) {
			currentList = databaseInfoMapper
			        .findOtherDatabaseInfosByObject(databaseInfo);
		} else {
			currentList = searchDatabaseInfosBySearchContent(
			        databaseInfo.getDatabaseName());
		}
		return !currentList.isEmpty();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.datadisplay.server.service.DatabaseInfoService#findDatabaseInfoByDatabaseName(java.lang.String)
	 * @param databaseName
	 * @return
	 */
	@Override
	public DatabaseInfo findDatabaseInfoByDatabaseName(String databaseName) {
		return databaseInfoMapper.findDatabaseInfoByDatabaseName(databaseName);
	}
}
