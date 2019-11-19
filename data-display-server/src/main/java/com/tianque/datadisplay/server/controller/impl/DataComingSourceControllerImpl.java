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
import com.tianque.datadisplay.server.model.DataComingSource;
import com.tianque.datadisplay.server.service.DataComingSourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author : Administrator
 * @since : 2019年11月12日 09:53:56
 * @see :
 */
@Controller
@Api("数据来源相关API")
@RequestMapping("/dataComingSource")
public class DataComingSourceControllerImpl {
	@Autowired
	private DataComingSourceService dataComingSourceService;

	@GetMapping(value = "/getList")
	@ResponseBody
	@ApiOperation(value = "查询数据来源列表")
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没有填好"),
	        @ApiResponse(code = 404, message = "页面查找失败，路径不对") })
	public ResponseData getList() {
		List<DataComingSource> dataComingSourceList = dataComingSourceService
		        .getList();
		return ResponseData.getSuccessResult(dataComingSourceList,
		        dataComingSourceList.size());
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
	@ApiOperation(value = "根据ename名称查询数据来源")
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没有填好"),
	        @ApiResponse(code = 404, message = "页面查找失败，路径不对") })
	public ResponseData searchList(
	        @RequestParam("searchContent") String searchContent) {
		List<DataComingSource> dataComingSourcePage = dataComingSourceService
		        .searchDataComingSourcesBySearchContent(searchContent);
		return ResponseData.getSuccessResult(dataComingSourcePage,
		        dataComingSourcePage.size());
	}

	/**
	 * 添加数据来源
	 * 
	 * @see :
	 * @param :
	 * @return : ResponseData
	 * @param dataComingSource
	 */
	@PostMapping(value = "/addDataComingSource")
	@ApiOperation(value = "添加数据来源")
	@ResponseBody
	public ResponseData addDataComingSource(
	        @RequestBody DataComingSource dataComingSource) {
		return ResponseData.getSuccessResult(
		        dataComingSourceService.addDataComingSource(dataComingSource));
	}

	/**
	 * 更新数据来源
	 * 
	 * @see :
	 * @param :
	 * @return : ResponseData
	 * @param dataComingSource
	 */
	@PostMapping(value = "/updateDataComingSource")
	@ApiOperation(value = "修改数据来源")
	@ResponseBody
	public ResponseData updateDataComingSource(
	        @RequestBody DataComingSource dataComingSource) {
		return ResponseData.getSuccessResult(dataComingSourceService
		        .updateDataComingSource(dataComingSource));
	}

	/**
	 * 更新数据来源
	 * 
	 * @see :
	 * @param :
	 * @return : ResponseData
	 * @param dataComingSource
	 */
	@PostMapping(value = "/executeSql")
	@ApiOperation(value = "执行数据库查询")
	@ResponseBody
	public ResponseData executeSql(
	        @RequestBody DataComingSource dataComingSource) {
		return ResponseData.getSuccessResult(
		        dataComingSourceService.executeSql(dataComingSource));
	}

	/**
	 * 删除数据来源-单个
	 * 
	 * @see :
	 * @param :
	 * @return : ResponseData
	 * @param id
	 */
	@DeleteMapping(value = "/deleteDataComingSource/{id}")
	@ApiOperation(value = "删除数据来源")
	@ResponseBody
	public ResponseData deleteDataComingSource(@PathVariable("id") Long id) {
		return ResponseData.getSuccessResult(
		        dataComingSourceService.deleteDataComingSource(id));
	}

	/**
	 * 批量删除数据来源
	 * 
	 * @see :
	 * @param :
	 * @return : ResponseData
	 * @param ids
	 * @return
	 */
	@PostMapping(value = "/deleteDataComingSourceInBatch/{ids}")
	@ApiOperation(value = "批量删除数据来源")
	@ResponseBody
	public ResponseData deleteDataComingSourceInBatch(
	        @PathVariable("ids") Long[] ids) {
		dataComingSourceService.deleteDataComingSource(ids);
		return ResponseData.getSuccessResult(ids);
	}
}