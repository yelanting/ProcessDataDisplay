/**
 * @author : 孙留平
 * @since : 2019年5月7日 下午7:15:13
 * @see:
 */
package com.tianque.datadisplay.server.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.commons.util.core.base.ValidationUtil;
import com.tianque.commons.util.exception.base.BusinessValidationException;
import com.tianque.datadisplay.server.mapper.DatabaseTypeMapper;
import com.tianque.datadisplay.server.model.DatabaseType;
import com.tianque.datadisplay.server.service.DatabaseTypeService;

/**
 * @author : Administrator
 * @since : 2019年5月7日 下午7:15:13
 * @see :
 */

@Service("databaseTypeService")
public class DatabaseTypeServiceImpl implements DatabaseTypeService {

	private static final Logger logger = LoggerFactory
	        .getLogger(DatabaseTypeServiceImpl.class);

	@Autowired
	private DatabaseTypeMapper databaseTypeMapper;

	/**
	 * @see 获取列表
	 */
	@Override
	public List<DatabaseType> getList() {

		logger.debug("获取数据库类型列表");
		return databaseTypeMapper.findAll();
	}

	/**
	 * 校验输入内容
	 * 
	 * @see :
	 * @param :
	 * @return : void
	 * @param databaseType
	 */
	private void validateInput(DatabaseType databaseType) {
		ValidationUtil.validateStringNullOrEmpty(databaseType.getTypeName(),
		        "数据库类型中文名不能为空");
		ValidationUtil.validateStringNullOrEmpty(databaseType.getTypeEname(),
		        "数据库类型英文名不能为空");
		ValidationUtil.validateNull(databaseType, "参数不能为null");
		ValidationUtil.validateStringAndLength(databaseType.getTypeName(), 1,
		        100, "数据库类型中文名称");
		ValidationUtil.validateStringAndLength(databaseType.getTypeEname(), 1,
		        30, "数据库类型英文名称");
	}

	/**
	 * @see 添加数据库类型
	 */
	@Override
	public DatabaseType addDatabaseType(DatabaseType databaseType) {
		logger.info("添加数据库类型:{}", databaseType);
		if (checkTypeEnameExists(databaseType.getTypeEname())) {
			throw new BusinessValidationException("该ename已经存在，不可重复添加");
		}

		validateInput(databaseType);
		try {
			int resultKey = databaseTypeMapper.insert(databaseType);
			databaseType.setId(Long.valueOf(resultKey));
			return databaseType;
		} catch (Exception e) {
			logger.error("添加数据库类型失败,错误信息:{}", e.getMessage());
			throw new BusinessValidationException("添加数据库类型失败");
		}
	}

	/**
	 * @see com.tianque.yunxiao.connect.server.service.DatabaseTypeService#updateDatabaseType(com.tianque.yunxiao.connect.server.model.DatabaseType)
	 */
	@Override
	public DatabaseType updateDatabaseType(DatabaseType databaseType) {
		logger.info("修改数据库类型");
		ValidationUtil.validateNull(databaseType.getId(), null);

		if (checkTypeEnameExists(databaseType)) {
			throw new BusinessValidationException("该ename已经存在，不可修改为此ename");
		}

		validateInput(databaseType);

		DatabaseType currentDatabaseType = selectByObject(databaseType);

		if (null == currentDatabaseType) {
			throw new BusinessValidationException("待修改对象不存在，不能修改");
		}

		try {
			this.databaseTypeMapper.updateByPrimaryKey(databaseType);
			return databaseType;
		} catch (Exception e) {
			throw new BusinessValidationException("修改失败");
		}

	}

	/**
	 * @see com.tianque.yunxiao.connect.server.service.DatabaseTypeService#searchDatabaseTypesBySearchContent(java.lang.String)
	 */
	@Override
	public List<DatabaseType> searchDatabaseTypesBySearchContent(
	        String searchContent) {
		return databaseTypeMapper
		        .findDatabaseTypesByTypeEnameLike(searchContent);
	}

	/**
	 * @see 单个删除
	 */
	@Override
	public Long deleteDatabaseType(Long id) {
		logger.info("删除id为:{}的数据库类型", id);
		ValidationUtil.validateNull(id, null);
		databaseTypeMapper.deleteByPrimaryKey(id);
		return id;
	}

	/**
	 * 批量删除
	 * 
	 * @see com.tianque.yunxiao.connect.server.service.DatabaseTypeService#deleteDatabaseType(java.lang.Long[])
	 */
	@Override
	public Long[] deleteDatabaseType(Long[] ids) {
		ValidationUtil.validateNull(ids, null);
		ValidationUtil.validateArrayNullOrEmpty(ids, null);
		logger.info("批量删除以下数据库类型:{}", Arrays.toString(ids));
		databaseTypeMapper.deleteByIds(ids);
		return ids;
	}

	/**
	 * @see com.tianque.yunxiao.connect.server.service.DatabaseTypeService#selectByPrimaryKey(java.lang.Long)
	 */
	@Override
	public DatabaseType selectByPrimaryKey(Long id) {
		logger.info("根据主键查询");
		ValidationUtil.validateNull(id, null);
		return databaseTypeMapper.selectByPrimaryKey(id);
	}

	/**
	 * @see
	 *      com.tianque.yunxiao.connect.server.service.DatabaseTypeService#selectByObject(com.tianque.yunxiao.connect.server.model
	 *      .DatabaseType)
	 */
	@Override
	public DatabaseType selectByObject(DatabaseType databaseType) {
		return selectByPrimaryKey(databaseType.getId());
	}

	/**
	 * @see :检查
	 * @param :
	 * @return : boolean
	 * @return
	 */
	private boolean checkTypeEnameExists(String paramKey) {
		List<DatabaseType> currentList = searchDatabaseTypesBySearchContent(
		        paramKey);

		return !currentList.isEmpty();
	}

	/**
	 * @see :检查
	 * @param :
	 * @return : boolean
	 * @return
	 */
	private boolean checkTypeEnameExists(DatabaseType databaseType) {
		List<DatabaseType> currentList = null;
		if (null != databaseType.getId()) {
			currentList = databaseTypeMapper
			        .findOtherDatabaseTypesByObject(databaseType);
		} else {
			currentList = searchDatabaseTypesBySearchContent(
			        databaseType.getTypeEname());
		}
		return !currentList.isEmpty();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.datadisplay.server.service.DatabaseTypeService#findDatabaseTypeByTypeEname(java.lang.String)
	 * @param typeEname
	 * @return
	 */
	@Override
	public DatabaseType findDatabaseTypeByTypeEname(String typeEname) {
		return databaseTypeMapper.findDatabaseTypeByTypeEname(typeEname);
	}
}
