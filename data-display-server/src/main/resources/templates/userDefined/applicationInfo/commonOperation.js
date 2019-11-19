/**
 * 获取页面的表格内容
 */
function getCurrentTableData(id) {
	var getCodeCoverageDetailUrl = "/applicationInfo/getDetail/" + id;
	let resultData = "";

	$.ajax({
		url : getCodeCoverageDetailUrl,
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
