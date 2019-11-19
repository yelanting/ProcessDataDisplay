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
import com.tianque.datadisplay.server.model.DatabaseInfo;
import com.tianque.datadisplay.server.service.DatabaseInfoService;

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
@Api("数据库信息相关API")
@RequestMapping("/databaseInfo")
public class DatabaseInfoControllerImpl {
	@Autowired
	private DatabaseInfoService databaseInfoService;

	@GetMapping(value = "/getList")
	@ResponseBody
	@ApiOperation(value = "查询数据库信息列表")
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没有填好"),
	        @ApiResponse(code = 404, message = "页面查找失败，路径不对") })
	public ResponseData getList() {
		List<DatabaseInfo> databaseInfoList = databaseInfoService.getList();
		return ResponseData.getSuccessResult(databaseInfoList,
		        databaseInfoList.size());
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
	@ApiOperation(value = "根据ename名称查询数据库信息")
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没有填好"),
	        @ApiResponse(code = 404, message = "页面查找失败，路径不对") })
	public ResponseData searchList(
	        @RequestParam("searchContent") String searchContent) {
		List<DatabaseInfo> databaseInfoPage = databaseInfoService
		        .searchDatabaseInfosBySearchContent(searchContent);
		return ResponseData.getSuccessResult(databaseInfoPage,
		        databaseInfoPage.size());
	}

	/**
	 * 添加数据库信息
	 * 
	 * @see :
	 * @param :
	 * @return : ResponseData
	 * @param databaseInfo
	 */
	@PostMapping(value = "/addDatabaseInfo")
	@ApiOperation(value = "添加数据库信息")
	@ResponseBody
	public ResponseData addDatabaseInfo(
	        @RequestBody DatabaseInfo databaseInfo) {
		return ResponseData.getSuccessResult(
		        databaseInfoService.addDatabaseInfo(databaseInfo));
	}

	/**
	 * 更新数据库信息
	 * 
	 * @see :
	 * @param :
	 * @return : ResponseData
	 * @param databaseInfo
	 */
	@PostMapping(value = "/updateDatabaseInfo")
	@ApiOperation(value = "修改数据库信息")
	@ResponseBody
	public ResponseData updateDatabaseInfo(
	        @RequestBody DatabaseInfo databaseInfo) {
		return ResponseData.getSuccessResult(
		        databaseInfoService.updateDatabaseInfo(databaseInfo));
	}

	/**
	 * 删除数据库信息-单个
	 * 
	 * @see :
	 * @param :
	 * @return : ResponseData
	 * @param id
	 */
	@DeleteMapping(value = "/deleteDatabaseInfo/{id}")
	@ApiOperation(value = "删除数据库信息")
	@ResponseBody
	public ResponseData deleteDatabaseInfo(@PathVariable("id") Long id) {
		return ResponseData
		        .getSuccessResult(databaseInfoService.deleteDatabaseInfo(id));
	}

	/**
	 * 批量删除数据库信息
	 * 
	 * @see :
	 * @param :
	 * @return : ResponseData
	 * @param ids
	 * @return
	 */
	@PostMapping(value = "/deleteDatabaseInfoInBatch/{ids}")
	@ApiOperation(value = "批量删除数据库信息")
	@ResponseBody
	public ResponseData deleteDatabaseInfoInBatch(
	        @PathVariable("ids") Long[] ids) {
		databaseInfoService.deleteDatabaseInfo(ids);
		return ResponseData.getSuccessResult(ids);
	}
}