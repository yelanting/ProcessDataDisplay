layui.use([
		'form', 'layer', 'layedit', 'laydate', 'upload'
], function() {
	var form = layui.form;
	var	layer = (parent.layer === undefined ? layui.layer : top.layer);
	var	laypage = layui.laypage;
	var upload = layui.upload;
	var layedit = layui.layedit;
	var laydate = layui.laydate;
	var $ = layui.jquery;

	form.verify({
		apiName : function(val) {
			var inputName = "接口名称"
			return checkInputEmpty(val, inputName)
					|| checkInputLength(val, null, 50, inputName)
		},

		apiPath : function(val) {
			var inputName =  "接口路径";
			return checkInputEmpty(val, inputName) ||checkInputLength(val, null, 500,inputName)
		},
		
		apiParams : function(val) {
			return checkInputLength(val, null, 500, "传入参数")
		},
	});
	
	/**
	 * 获取页面的id
	 * 
	 */
	function getParentId() {
		let searchContent = window.location.search;
		let targetId = getValueOfKeyFromWindowLocationSearch("id" , searchContent);
		return targetId;
	}
	
	/**
	 * 渲染应用信息
	 */
	(function(){
		let detail = getCurrentTableData(getParentId());
		let edit = detail.data;
		$("#id").val(edit.id);
		$("#apiName").val(edit.apiName);
		$("#apiPath").val(edit.apiPath)
		$("#apiParams").val(edit.apiParams);
		$("#createDate").val(edit.createDate);
		$("#updateDate").val(edit.updateDate);
		$("#requestMethodType").val(edit.requestMethodType);
		$("#requestMimeType").val(edit.requestMimeType);
		$("#applicationId").val(edit.applicationId);
		$("#expectedResponse").val(edit.expectedResponse);
		$("#description").val(edit.description);
		form.render();
		
		if (!detail.data.applicationId) {
			initApplicationInfoSelect();
		}else{
			initApplicationInfoSelectById(detail.data.applicationId);
		}
		
	})()
	
	
	form.on("submit(editMockApiSubmit)", function(data) {
		// 弹出loading
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16, time : false, shade : 0.8
		});
		
		var postData = $("#editMockApiForm").serialize();
		
		// 实际使用时的提交信息
		$.post("/mockApi/updateMockApi", postData, function(response) {
			if (response.success == "false" || !response.success) {
				if (response.msg != "null") {
					top.layer.msg("修改失败:" + response.msg);
				} else {
					top.layer.msg("修改失败,原因未知！");
				}
			} else {
				setTimeout(function() {
					top.layer.close(index);
					top.layer.msg("修改成功！");
					layer.closeAll("iframe");
					// 刷新父页面
					parent.location.reload();
				}, 500);
			}
		});
		return false;
	})
});