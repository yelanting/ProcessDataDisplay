/**
 *
 */
layui.config({ // version : '1535898708509' // 为了更新 js 缓存，可忽略
});

layui.use(['laypage', 'layer', 'table', 'element', 'form'], function () {
	var form = layui.form;
	var layer = (parent.layer === undefined ? layui.layer : top.layer);
	var $ = layui.jquery;
	var laydate = layui.laydate;
	var laytpl = layui.laytpl;
	var table = layui.table;
	var laypage = layui.laypage;
	var element = layui.element;
	// 元素操作

	var widthPercent = "70%",
	heightPercent = "90%";
	
	
	// 监听头工具栏事件,批量删除操作
    $('#deleteApplicationInfoInBatch').click(function() {
        var checkStatus = table.checkStatus('applicationInfoListTable');
        var data = checkStatus.data;
        console.log(data);
        var applicationInfoId = [];
        if (data.length > 0) {
            for (var eachDataIndex in data) {
                applicationInfoId.push(data[eachDataIndex].id);
            }

            layer.confirm('确定删除选中的数据吗？', {
                icon: 3,
                title: '提示信息'
            },
            function(index) {
                var url = "/applicationInfo/deleteApplicationInfoInBatch";

                var postData = {
                    "ids": applicationInfoId
                };

                $.post(url, postData,
                function(responseData) {
                    if (responseData.success == "true" || responseData.success) {
                        setTimeout(function() {
                            top.layer.msg("批量删除成功！");
                            // applicationInfoTable.reload();
                            renderApplicationInfoTableDefault();
                            layer.close(index);
                        },
                        500);
                    } else {
                        layer.msg('批量删除失败：' + responseData.msg);
                    }
                });

            })
        } else {
            layer.msg("请选择需要删除的数据");
            return false;
        }
    });

    // 添加
    function addApplicationInfo() {
        layui.layer.open({
            title: "添加应用信息",
            type: 2,
            area: [widthPercent, heightPercent],
            content: "addApplicationInfo.html",
            success: function(layero, index) {
                setTimeout(function() {
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },
                500);
            }
        });
    }
    
    // 编辑
    function editApplicationInfo(edit) {
        var itemId = edit.id;
        layui.layer.open({
            title: "修改应用信息",
            type: 2,
            area: [widthPercent, heightPercent],
            content: "editApplicationInfo.html?id=" + itemId,
            success: function(layero, index) {
                setTimeout(function() {
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },
                500)
            }
        });
    }

    /**
     * 删除一条记录
     */
    function delteApplicationInfo(data, obj) {
        layer.confirm('真的要删除么?删除之后，该应用下的所有关联Api都将被移到默认应用下',
        function(index) {
            var url = "/applicationInfo/deleteApplicationInfo";
            var postData = {
                "id": data.id
            };
            $.post(url, postData,
            function(responseData) {
                if (responseData.success == "true" || responseData.success) {
                    layer.msg('删除成功！');
                    obj.del();
                    renderApplicationInfoTableDefault();
                    // 删除对应行（tr）的DOM结构
                } else {
                    layer.msg('删除失败：' + responseData.msg);
                }
            });
            layer.close(index);
            // 向服务端发送删除指令
        });
    }
    
	$("#addApplicationInfo_btn").click(function() {
	    addApplicationInfo();
	});

	 
	/**
	 * 获取所有数据
	 */
	function getApplicationInfoList() {
		var getApplicationInfoListUrl = '/applicationInfo/getList';
		var resultData = "";
		$.ajax({
			url: getApplicationInfoListUrl,
			async: false,
			method: "get",
			dataType: "json",
			success: function (result) {
				resultData = result;
				let errorMsg = result.msg;
				if(errorMsg){
					layer.msg("请求出错了," + errorMsg);
				}
			}
		});
		return resultData;
	}

	/**
	 * 条件搜索
	 */
	function getApplicationInfoListWithCondition(searchContent) {
		if (searchContent == "undefined" || searchContent == "") {
			return getApplicationInfoList();
		} else {
			var resultData = "",
			postData = {};
			$.ajax({
				url: "/applicationInfo/searchList?searchContent=" + searchContent,
				method: "get",
				data: postData,
				async: false,
				dataType: "json",
				success: function (result) {
					resultData = result;
				},
			});
			return resultData;
		}
	}

	/**
	 * 刷新表格
	 */
	function renderApplicationInfoTableDefault() {
		var searchContent = "";
		var applicationInfoListData = getApplicationInfoListWithCondition(searchContent);
		table.reload("applicationInfoListTable", {
			page: {
				curr: 1
			},
			data: applicationInfoListData.data
		})
	}

	// 执行一个 table 实例
	table.render({
		elem: '#applicationInfoListTable',
		height: 400,
		// 数据接口
		//        url : "/applicationInfo/getList/",
		//        method:"get",
		// 表头
		title: '应用信息',
		// 开启工具栏，此处显示默认图标，可以自定义模板，详见文档
		toolbar:true,
		defaultToolbar: ['filter','exports'],
		//是否显示加载条
		loading: true,
		//默认排序
		initSort: "appName",
		even: true,
		// 最窄宽度
		//cellMinWidth: 60,
		size: 'lg',
		skin: 'row',
		page: {
			layout: ['prev', 'page', 'next', 'limit', 'count', 'skip'],
			prev: "上一页",
			next: "下一页",
			first: "首页",
			last: "尾页",
			//每页条数的选择项
			limits: [5, 10, 20, 30, 40, 50, 100],
			limit: 50,
			groups: 3,
			skin: '#1E9FFF',
			// 自定义选中色值
			skip: true,
		},
		text: {
			none: '暂无相关数据' // 默认：无数据。
		},
		// 表头
		cols: [[{
					type: 'checkbox',
					fixed: 'left',
					unresize: true
				}, {
					type: 'numbers',
					title: "序号",
					fixed: "left",
					unresize: true
				}, {
					field: 'appName',
					title: '应用名称',
					sort: true,
					//	            width: 150
				}, {
					field: 'appEname',
					title: '英文名称',
					sort: true,
					//	            width: 150
				},{
					field: 'appCode',
					title: '应用编码',
					sort: true,
				}, {
					field: 'baseUrl',
					title: '基础Url',
				}, {
					field: 'description',
					title: '描述',
				},{
					fixed: 'right',
					align: 'center',
					title: '操作',
					toolbar: '#operationBar',
					width: '20%'
				}
			]],
		data: getApplicationInfoList().data
	});


	// 查看应用信息
	function viewApplicationInfo(edit) {
		layui.layer.open({
			title: "查看应用信息",
			type: 2,
			area: [widthPercent, heightPercent],
			content: "viewApplicationInfo.html",
			success: function (layero, index) {
				var body = layui.layer.getChildFrame('body', index);
				body.find("#id").val(edit.id);
				body.find("#appName").val(edit.appName);
				body.find("#appEname").val(edit.appEname)
				body.find("#baseUrl").val(edit.baseUrl);
				body.find("#appCode").val(edit.appCode);
				body.find("#createDate").val(edit.createDate);
				body.find("#updateDate").val(edit.updateDate);
				form.render();
				setTimeout(function () {
					layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				}, 500)
			}
		});
	}

	// 搜索应用信息
	// 搜索【此功能需要后台配合，所以暂时没有动态效果演示】
	$("#searchApplicationInfo").on("click", function () {
		var searchContent = $("#serachApplicationInfoContent").val();
		var applicationInfoListData = getApplicationInfoListWithCondition(searchContent);
		//搜索完之后清空搜索数据
		$("#serachApplicationInfoContent").val("")
		//表格重载
		table.reload("applicationInfoListTable", {
			data: applicationInfoListData.data
		});
	});

	//刷新功能
	$("#refreshApplicationInfoList").on("click", function () {
		$("#serachApplicationInfoContent").val("");
		renderApplicationInfoTableDefault();
	});

	// 监听行工具事件
	table.on('tool(applicationInfoListTable)', function (obj) {
		// 注：tool
		// 是工具条事件名，test 是
		// table 原始容器的属性
		// lay-filter="对应的值"
		var data = obj.data;
		// 获得当前行数据
		var layEvent = obj.event;
		
		// 获得 lay-event 对应的值
		if(layEvent === "edit"){
			editApplicationInfo(data);
		}else if(layEvent === "delete"){
			delteApplicationInfo(data);
		}
	});
	table.on('rowDouble(applicationInfoListTable)', function (obj) {
		viewApplicationInfo(obj.data);
	});
});
