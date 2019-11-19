/**
 * 获取页面的表格内容
 */
function getCurrentTableData(id) {
	var getDetailUrl = "/mockApi/getDetail/" + id;
	let resultData = "";

	$.ajax({
		url : getDetailUrl,
		async : false,
		type : "get",
		dataType : "json",
		success : function(result) {
			resultData = result;
		}
	});
	proccessObject(resultData);
	console.log(`id为${id}的记录详情为:${JSON.stringify(resultData)}`);

	return resultData;
}

/**
 * 获取服务器信息数据
 */
function getApplicationInfoList() {
	var getServerInfoListUrl = '/applicationInfo/getList';
	var resultData = "";
	$.ajax({
		url : getServerInfoListUrl,
		async : false,
		method : "get",
		dataType : "json",
		success : function(result) {
			resultData = result;
		}
	});
	return resultData.data;
}

/**
 * 重新渲染select
 */
function initApplicationInfoSelect() {
	let applicationInfoList = getApplicationInfoList();
	let $applicationInfoSelect = $("#applicationId");
	$applicationInfoSelect.children().remove();
	for (let i = 0; i < applicationInfoList.length; i++) {
		let selected = (i == 0 ? "selected" : "");
		var element = "<option value='" + applicationInfoList[i].id + "'"
				+ selected + ">" + applicationInfoList[i].appName + "</option>";
		$applicationInfoSelect.append(element);
		
		layui.use([ 'form']);
		layui.form.render("select");
	}
}

/**
 * 重新渲染select
 */
function initApplicationInfoSelectById( applicationId) {
	let applicationInfoList = getApplicationInfoList();
	let $applicationInfoSelect = $("#applicationId");
	$applicationInfoSelect.children().remove();
	
	let selectedIp;
	for (let i = 0; i < applicationInfoList.length; i++) {
		let selected = (applicationInfoList[i].id == applicationId ? "selected" : "");
		
		if (selected) {
			selectedIp = applicationInfoList[i].applicationInfo;
		}
		var element = "<option value='" + applicationInfoList[i].id + "'"
				+ selected + ">" + applicationInfoList[i].appName + "</option>";
		
		$applicationInfoSelect.append(element);
		layui.use([ 'form']);
		layui.form.render("select");
	}
	return selectedIp;
}