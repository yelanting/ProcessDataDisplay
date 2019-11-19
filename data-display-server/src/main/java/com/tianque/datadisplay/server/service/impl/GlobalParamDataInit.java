/**
 * @author 作者: 孙留平
 * @since 创建时间: 2019年8月2日 下午2:54:03
 * @see:
 */
package com.tianque.datadisplay.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.datadisplay.server.model.GlobalParam;
import com.tianque.datadisplay.server.service.GlobalParamService;

@Service("globalParamDataInit")
public class GlobalParamDataInit {

	@Autowired
	private GlobalParamService globalParamService;

	/**
	 * 判断一下，如果当前key不存在，就初始化一下
	 * 
	 * @see :
	 * @param :
	 * @return : void
	 * @param paramKey
	 * @param globalParam
	 */
	private void checkAndInitGlobalParam(String paramKey,
	        GlobalParam globalParam) {
		if (null == globalParamService.findGlobalParamByParamKey(paramKey)) {
			globalParamService.addGlobalParam(globalParam);
		}
	}
}
