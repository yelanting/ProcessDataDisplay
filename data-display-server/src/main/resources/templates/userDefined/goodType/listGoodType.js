/**
 * 
 */
layui.config({// version : '1535898708509' // 为了更新 js 缓存，可忽略
});

/**
 * 获取所有数据
 */
function getGoodTypeList(){
	var getGoodTypeListUrl = '/goodType/getList';
	var resultData = "";
    $.ajax({
    	url:getGoodTypeListUrl,
    	async:false,
    	method:"get",
    	dataType:"json",
    	success:function(result){
    		resultData = result;
    }});
    return resultData;
}

/**
 * 根据ID查找名称
 * @returns
 */
function getGoodTypeNameById(id , responseData){
	for (var i in responseData.data) {
		if (id == responseData.data[i].id) {
			return responseData.data[i].typeName;
		}
	}
}

layui.use(['laypage', 'layer', 'table', 'element', 'form'], function() {
    var form = layui.form
      , layer = parent.layer === undefined ? layui.layer : top.layer
      , $ = layui.jquery
      , laydate = layui.laydate
      , laytpl = layui.laytpl
      , table = layui.table
      , laypage = layui.laypage
      , element = layui.element;
    // 元素操作

    var widthPercent = "70%"
      , heightPercent = "90%";
    
    
    
    /**
     * 条件搜索
     */
    function getGoodTypeListWithCondition(searchContent){
    	if(searchContent == "undefined" || searchContent == ""){
    		return getGoodTypeList();
    	}else{
    		var resultData = "",
    		postData = {};
    		$.ajax({
    			url: "/goodType/searchList?searchContent=" + searchContent,
                method: "get",
        		data : postData ,
        		async : false ,
        		dataType : "json",
        		success:function(result){
        			resultData = result;
        		}
        	});
    		return resultData;
    	}
    }
    
    /**
     * 刷新表格
     */
    function renderGoodTypeTableDefault(){
    	var searchContent = "";
    	var goodTypeListData = getGoodTypeListWithCondition(searchContent);
        table.reload("goodTypeListTable", {
        	page : {curr : 1},
        	data : goodTypeListData.data
        })
    }
    
    // 执行一个 table 实例
    var goodTypeTable = table.render({
        elem: '#goodTypeListTable',
        height: 400,
        // 数据接口
//        url : "/goodType/getList/",
//        method:"get",
        // 表头
        title: '物品种类',
        // 开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        toolbar: true,
        defaultToolbar: ["filter"],
        //是否显示加载条
        loading: true,
        //默认排序
        initSort: "id",
        even: true,
        // 最窄宽度
        cellMinWidth: 80,
        size:"lg",
        page:{
        	layout: ['prev', 'page', 'next', 'limit', 'count', 'skip'],
	        prev: "上一页" ,
	        next: "下一页" ,
	        first: "首页",
	        last: "尾页",
	        //每页条数的选择项
	        limits: [5, 10, 20, 30, 40, 50, 100],
	        limit : 5,
	        groups: 3,
	        skin: '#1E9FFF',
	        // 自定义选中色值
	        skip: true,
        },
        text: {
            none: '暂无相关数据'// 默认：无数据。
        },
        // 表头
        cols: [[
        	{
	            type: 'checkbox',
	            fixed: 'left',
	            unresize :true
	        },{
            	type:'numbers',
            	title:"序号",
            	fixed: "left",
            	unresize :true
            },{
	            field: 'typeName',
	            title: '类型名称',
	            sort: true,
//	            width: 170,
//	            fixed: "left"
	        }, {
	            field: 'typeDesc',
	            title: '类型描述',
	            sort: true,
	        }, 
	        {
	            field: 'createDate',
	            title: '创建日期',
	            sort: true,
	        }, {
	            field: 'updateDate',
	            title: '修改日期',
	            sort: true,
	        }, 
	        {
	            fixed: 'right',
	            align: 'center',
	            title: '操作',
	            toolbar: '#operationBar',
	        }]],
        data : getGoodTypeList().data
    });

    // 监听头工具栏事件,批量删除操作
    $('#deleteInBatch').click(function() {
        var checkStatus = table.checkStatus('goodTypeListTable');
        var data = checkStatus.data;
        var goodTypeId = [];
        if (data.length > 0) {
            for (var eachDataIndex in data) {
                goodTypeId.push(data[eachDataIndex].id);
            }

            layer.confirm('确定删除选中的数据吗？将会删除其下关联的所有物品信息！', {
                icon: 3,
                title: '提示信息'
            }, function(index) {
                var url = "/goodType/deleteGoodTypeInBatch";

                var postData = {
                    "ids": goodTypeId
                };

                $.post(url, postData, function(responseData) {
                    if (responseData.success == "true" || responseData.success) {
                        setTimeout(function() {
                            top.layer.msg("批量删除成功！");
                            renderGoodTypeTableDefault()
                            layer.close(index);
                        }, 500);
                    } else {
                        layer.msg('批量删除失败：' + responseData.msg);
                    }
                });

            })
        } else {
            layer.msg("请选择需要删除的数据");
        }
    });

    // 添加物品种类
    function addGoodType() {
        layui.layer.open({
            title: "添加物品种类",
            type: 2,
            area: [widthPercent, heightPercent],
            content: "addGoodType.html",
            success: function(layero, index) {
                setTimeout(function() {
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
    }

    // 编辑物品种类
    function editGoodType(edit) {
        layui.layer.open({
            title: "修改物品种类",
            type: 2,
            area: [widthPercent, heightPercent],
            content: "editGoodType.html",
            success: function(layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                body.find("#id").val(edit.id);
                body.find("#typeName").val(edit.typeName);
                body.find("#typeDesc").val(edit.typeDesc);
                body.find("#createDate").val(edit.createDate);
                body.find("#updateDate").val(edit.updateDate);
                form.render();
                setTimeout(function() {
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
    }

    // 查看物品种类
    function viewGoodType(edit) {
        layui.layer.open({
            title: "查看物品种类",
            type: 2,
            area: [widthPercent, heightPercent],
            content: "viewGoodType.html",
            success: function(layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                body.find("#id").val(edit.id);
                body.find("#typeName").val(edit.typeName);
                body.find("#typeDesc").val(edit.typeDesc);
                body.find("#createDate").val(edit.createDate);
                body.find("#updateDate").val(edit.updateDate);
                form.render();
                setTimeout(function() {
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
    }
    $("#addGoodType_btn").click(function() {
        addGoodType();
    });

    // 搜索物品种类
    // 搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $("#searchGoodType").on("click", function() {
        var searchContent = $("#serachGoodTypeContent").val();
        var goodTypeListData = getGoodTypeListWithCondition(searchContent);
        //搜索完之后清空搜索数据
        $("#serachGoodTypeContent").val("")
        //表格重载
        table.reload("goodTypeListTable",{data : goodTypeListData.data});
    });

    //刷新功能
    $("#refreshGoodTypeList").on("click", function() {
    	$("#serachGoodTypeContent").val("");
    	renderGoodTypeTableDefault();
    });

    // 监听行工具事件
    table.on('tool(goodTypeListTable)', function(obj) {
        // 注：tool
        // 是工具条事件名，test 是
        // table 原始容器的属性
        // lay-filter="对应的值"
        var data = obj.data;
        // 获得当前行数据
        var layEvent = obj.event;
        // 获得 lay-event 对应的值
        if (layEvent === 'detail') {
            viewGoodType(data);
        } else if (layEvent === 'del') {
            layer.confirm('真的删除行么？其下关联的物品信息也会删除！', function(index) {
                var url = "/goodType/deleteGoodType";
                var postData = {
                    "id": data.id
                };
                $.post(url, postData, function(responseData) {
                    if (responseData.success == "true" || responseData.success) {
                        layer.msg('删除成功！');
                        obj.del();
                        renderGoodTypeTableDefault();
                        // 删除对应行（tr）的DOM结构
                    } else {
                        layer.msg('删除失败：' + responseData.msg);
                    }
                });
                layer.close(index);
                // 向服务端发送删除指令
            });
        } else if (layEvent === 'edit') {
            editGoodType(data);
        }
    });
    
    table.on('rowDouble(goodTypeListTable)', function(obj){
    	layer.msg("你想干啥！别瞎点好不好",{time: 300});
    	});
    
});
