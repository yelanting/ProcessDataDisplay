/**
 * @author : 孙留平
 * @since : 2019年5月10日 下午5:59:08
 * @see:
 */
package com.tianque.datadisplay.server.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianque.commons.util.ResponseData;
import com.tianque.datadisplay.server.model.DatabaseType;
import com.tianque.datadisplay.server.service.DatabaseTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author : Administrator
 * @since : 2019年11月12日 09:54:05
 * @see :
 */
@Controller
@Api("数据库类型相关API")
@RequestMapping("/databaseType")
public class DatabaseTypeControllerImpl {
	@Autowired
	private DatabaseTypeService databaseTypeService;

	@GetMapping(value = "/getList")
	@ResponseBody
	@ApiOperation(value = "查询数据库类型列表")
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没有填好"),
	        @ApiResponse(code = 404, message = "页面查找失败，路径不对") })
	public ResponseData getList() {
		List<DatabaseType> databaseTypeList = databaseTypeService.getList();
		return ResponseData.getSuccessResult(databaseTypeList,
		        databaseTypeList.size());
	}

	/**
	 * 根据项目名称查询
	 * 
	 * @see :
	 * @param :
	 * @return : ResponseData
	 * @param searchContent
	 * @return
	 */
	@GetMapping(value = "/searchList")
	@ResponseBody
	@ApiOperation(value = "根据ename名称查询数据库类型")
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没有填好"),
	        @ApiResponse(code = 404, message = "页面查找失败，路径不对") })
	public ResponseData searchList(
	        @RequestParam("searchContent") String searchContent) {
		List<DatabaseType> databaseTypePage = databaseTypeService
		        .searchDatabaseTypesBySearchContent(searchContent);
		return ResponseData.getSuccessResult(databaseTypePage,
		        databaseTypePage.size());
	}

	/**
	 * 添加数据库类型
	 * 
	 * @see :
	 * @param :
	 * @return : ResponseData
	 * @param databaseType
	 */
	@PostMapping(value = "/addDatabaseType")
	@ApiOperation(value = "添加数据库类型")
	@ResponseBody
	public ResponseData addDatabaseType(
	        @RequestBody DatabaseType databaseType) {
		return ResponseData.getSuccessResult(
		        databaseTypeService.addDatabaseType(databaseType));
	}

	/**
	 * 更新数据库类型
	 * 
	 * @see :
	 * @param :
	 * @return : ResponseData
	 * @param databaseType
	 */
	@PostMapping(value = "/updateDatabaseType")
	@ApiOperation(value = "修改数据库类型")
	@ResponseBody
	public ResponseData updateDatabaseType(
	        @RequestBody DatabaseType databaseType) {
		return ResponseData.getSuccessResult(
		        databaseTypeService.updateDatabaseType(databaseType));
	}

	/**
	 * 删除数据库类型-单个
	 * 
	 * @see :
	 * @param :
	 * @return : ResponseData
	 * @param id
	 */
	@DeleteMapping(value = "/deleteDatabaseType/{id}")
	@ApiOperation(value = "删除数据库类型")
	@ResponseBody
	public ResponseData deleteDatabaseType(@PathVariable("id") Long id) {
		return ResponseData
		        .getSuccessResult(databaseTypeService.deleteDatabaseType(id));
	}

	/**
	 * 批量删除数据库类型
	 * 
	 * @see :
	 * @param :
	 * @return : ResponseData
	 * @param ids
	 * @return
	 */
	@PostMapping(value = "/deleteDatabaseTypeInBatch/{ids}")
	@ApiOperation(value = "批量删除数据库类型")
	@ResponseBody
	public ResponseData deleteDatabaseTypeInBatch(
	        @PathVariable("ids") Long[] ids) {
		databaseTypeService.deleteDatabaseType(ids);
		return ResponseData.getSuccessResult(ids);
	}
}