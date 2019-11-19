layui.use([ 'form', 'layer', 'layedit', 'laydate', 'upload' ], function() {
	var form = layui.form;
	var layer = (parent.layer === undefined ? layui.layer : top.layer);
	var laypage = layui.laypage;
	var upload = layui.upload;

	var layedit = layui.layedit;
	var laydate = layui.laydate;
	var $ = layui.jquery;

	form.verify({
		appName : function(val) {
			var inputName = "应用名称"
			return checkInputEmpty(val, inputName)
					|| checkInputLength(val, null, 50, inputName)
		},

		appEname : function(val) {
			return checkInputLength(val, null, 500, "英文名称")
		},

		baseUrl : function(val) {
			return checkInputLength(val, null, 500, "基础请求路径")
		},

		description : function(val) {
			return checkInputLength(val, null, 100, "备注")
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
	 * 加载之后立即请求
	 */
	(function(){
		let editData = getCurrentTableData(getParentId());
		let edit = editData.data;
		console.log(edit);
		$("#id").val(edit.id);
        $("#appName").val(edit.appName);
        $("#appEname").val(edit.appEname);
        $("#baseUrl").val(edit.baseUrl);
        $("#createDate").val(edit.createDate);
        $("#updateDate").val(edit.updateDate);
        $("#description").val(edit.description);
        form.render();
	})();
	
	form.on("submit(editApplicationInfoSubmit)", function(data) {
		// 弹出loading
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});

		var postData = $("#editApplicationInfoForm").serialize();

		// 实际使用时的提交信息
		$.post("/applicationInfo/updateApplicationInfo", postData, function(
				response) {
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