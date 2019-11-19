/**
 * 
 */
layui.config({ // version : '1535898708509' // 为了更新 js 缓存，可忽略
});

layui
		.use(
				[ 'laypage', 'layer', 'table', 'element', 'form' ],
				function() {
					var form = layui.form;
					var layer = (parent.layer === undefined ? layui.layer
							: top.layer);
					var $ = layui.jquery;
					var laydate = layui.laydate;
					var laytpl = layui.laytpl;
					var table = layui.table;
					var laypage = layui.laypage;
					var element = layui.element;
					// 元素操作

					var widthPercent = "70%", heightPercent = "90%";

					/**
					 * 获取所有数据
					 */
					function getMockApiList() {
						var getMockApiListUrl = '/mockApi/getList';
						var resultData = "";
						$.ajax({
							url : getMockApiListUrl,
							async : false,
							method : "get",
							dataType : "json",
							success : function(result) {
								resultData = result;
							}
						});
						return resultData;
					}

					/**
					 * 条件搜索
					 */
					function getMockApiListWithCondition(searchContent) {
						if (searchContent == "undefined" || searchContent == "") {
							return getMockApiList();
						} else {
							var resultData = "", postData = {};
							$.ajax({
								url : "/mockApi/searchList?searchContent="
										+ searchContent,
								method : "get",
								data : postData,
								async : false,
								dataType : "json",
								success : function(result) {
									resultData = result;
								}
							});
							return resultData;
						}
					}

					/**
					 * 根据应用信息搜索
					 */
					function getMockApiListWithAppInfo(searchContent) {
						if (searchContent == "undefined" || searchContent == "") {
							return getMockApiList();
						} else {
							var resultData = "", postData = {};
							$
									.ajax({
										url : "/mockApi/searchListWithAppInfo?searchContent="
												+ searchContent,
										method : "get",
										data : postData,
										async : false,
										dataType : "json",
										success : function(result) {
											resultData = result;
										}
									});
							return resultData;
						}
					}

					/**
					 * 根据应用信息搜索该应用下的信息
					 */
					function getMockApiListWithConditionAndAppInfo(
							searchContent, appInfo) {
						if (searchContent == "undefined" || searchContent == "") {
							return getMockApiList();
						} else {
							var resultData = "", postData = {};
							$
									.ajax({
										url : "/mockApi/searchListWithConditionAndAppInfo?searchContent="
												+ searchContent
												+ "&appInfo="
												+ appInfo,
										method : "get",
										data : postData,
										async : false,
										dataType : "json",
										success : function(result) {
											resultData = result;
										}
									});
							return resultData;
						}
					}

					/**
					 * 刷新表格
					 */
					function renderMockApiTableDefault() {
						var searchContent = "";
						var mockApiListData = getMockApiListWithCondition(searchContent);
						table.reload("mockApiListTable", {
							page : {
								curr : 1
							},
							data : mockApiListData.data
						})

						$("#serachMockApiContent").val("");
						$("#serachMockApiContentApp").val("");
					}

					// 执行一个 table 实例
					table.render({
						elem : '#mockApiListTable',
						height : 400,
						// 数据接口
						// 表头
						title : 'MockApi列表',
						// 开启工具栏，此处显示默认图标，可以自定义模板，详见文档
						toolbar : true,
						defaultToolbar : [ 'filter', 'exports' ],
						// 是否显示加载条
						loading : true,
						// 默认排序
						initSort : "apiName",
						even : true,
						// 最窄宽度
						// cellMinWidth: 60,
						size : 'lg',
						skin : 'row',
						page : {
							layout : [ 'prev', 'page', 'next', 'limit',
									'count', 'skip' ],
							prev : "上一页",
							next : "下一页",
							first : "首页",
							last : "尾页",
							// 每页条数的选择项
							limits : [ 5, 10, 20, 30, 40, 50, 100 ],
							limit : 50,
							groups : 3,
							skin : '#1E9FFF',
							// 自定义选中色值
							skip : true,
						},
						text : {
							none : '暂无相关数据' // 默认：无数据。
						},
						// 表头
						cols : [ [ {
							type : 'checkbox',
							fixed : 'left',
							unresize : true
						}, {
							type : 'numbers',
							title : "序号",
							fixed : "left",
							unresize : true
						}, {
							field : 'apiName',
							title : '接口名称',
							sort : true,
						// width: 150
						}, {
							field : 'apiPath',
							title : '接口路径',
							sort : true,
						// width: 220
						}, {
							field : 'applicationId',
							title : '所属应用',
							sort : true,
							templet : "#applicationName"
						}, {
							field : 'apiParams',
							title : '接口参数',
						// width: 220
						}, {
							field : 'expectedResponse',
							title : '预期返回',
						// width: 220
						}, {
							field : 'requestMethodType',
							title : '请求类型',
						// width: 220
						}, {
							fixed : 'right',
							align : 'center',
							title : '操作',
							toolbar : '#operationBar',
							width : '30%'
						} ] ],
						data : getMockApiList().data
					});

					// 监听头工具栏事件,批量删除操作
					$('#deleteInBatch')
							.click(
									function() {
										var checkStatus = table
												.checkStatus('mockApiListTable');
										var data = checkStatus.data;
										console.log(data);
										var mockApiId = [];
										if (data.length > 0) {
											for ( var eachDataIndex in data) {
												mockApiId
														.push(data[eachDataIndex].id);
											}

											layer
													.confirm(
															'确定删除选中的数据吗？',
															{
																icon : 3,
																title : '提示信息'
															},
															function(index) {
																var url = "/mockApi/deleteMockApiInBatch";

																var postData = {
																	"ids" : mockApiId
																};

																$
																		.post(
																				url,
																				postData,
																				function(
																						responseData) {
																					if (responseData.success == "true"
																							|| responseData.success) {
																						setTimeout(
																								function() {
																									top.layer
																											.msg("批量删除成功！");
																									// mockApiTable.reload();
																									renderMockApiTableDefault()
																									layer
																											.close(index);
																								},
																								500);
																					} else {
																						layer
																								.msg('批量删除失败：'
																										+ responseData.msg);
																					}
																				});

															})
										} else {
											layer.msg("请选择需要删除的数据");
										}
									});

					// 添加MockApi
					function addMockApi() {
						layui.layer
								.open({
									title : "添加MockApi",
									type : 2,
									area : [ widthPercent, heightPercent ],
									content : "addMockApi.html",
									success : function(layero, index) {
										setTimeout(
												function() {
													layui.layer
															.tips(
																	'点击此处返回列表',
																	'.layui-layer-setwin .layui-layer-close',
																	{
																		tips : 3
																	});
												}, 500)
									}
								});
					}

					// 复制MockApi
					function copyMockApi(edit) {
						// let copySuffix = "_copy";
						// let copySuffixChinese = "_复制";
						layui.layer
								.open({
									title : "复制MockApi",
									type : 2,
									area : [ widthPercent, heightPercent ],
									content : "copyMockApi.html?id=" + edit.id,
									success : function(layero, index) {
										// var body =
										// layui.layer.getChildFrame('body',
										// index);
										// body.find("#apiName").val(edit.apiName
										// + copySuffixChinese);
										// body.find("#apiPath").val(edit.apiPath
										// + copySuffix)
										// body.find("#apiParams").val(edit.apiParams);
										// body.find("#createDate").val(edit.createDate);
										// body.find("#updateDate").val(edit.updateDate);
										// body.find("#requestMethodType").val(edit.requestMethodType);
										// body.find("#requestMimeType").val(edit.requestMimeType);
										// body.find("#applicationId").val(edit.applicationId);
										// body.find("#expectedResponse").val(edit.expectedResponse);
										// body.find("#description").val(edit.description);
										// form.render();
										setTimeout(
												function() {
													layui.layer
															.tips(
																	'点击此处返回列表',
																	'.layui-layer-setwin .layui-layer-close',
																	{
																		tips : 3
																	});
												}, 500)
									}
								});
					}

					// 编辑MockApi
					function editMockApi(edit) {
						layui.layer
								.open({
									title : "修改MockApi",
									type : 2,
									area : [ widthPercent, heightPercent ],
									content : "editMockApi.html?id=" + edit.id,
									success : function(layero, index) {
										// var body =
										// layui.layer.getChildFrame('body',
										// index);
										// body.find("#id").val(edit.id);
										// body.find("#apiName").val(edit.apiName);
										// body.find("#apiPath").val(edit.apiPath)
										// body.find("#apiParams").val(edit.apiParams);
										// body.find("#createDate").val(edit.createDate);
										// body.find("#updateDate").val(edit.updateDate);
										// body.find("#requestMethodType").val(edit.requestMethodType);
										// body.find("#requestMimeType").val(edit.requestMimeType);
										// body.find("#applicationId").val(edit.applicationId);
										// body.find("#expectedResponse").val(edit.expectedResponse);
										// body.find("#description").val(edit.description);
										// form.render();
										setTimeout(
												function() {
													layui.layer
															.tips(
																	'点击此处返回列表',
																	'.layui-layer-setwin .layui-layer-close',
																	{
																		tips : 3
																	});
												}, 500)
									}
								});
					}

					// 查看MockApi
					function viewMockApi(edit) {
						layui.layer
								.open({
									title : "查看MockApi",
									type : 2,
									area : [ widthPercent, heightPercent ],
									content : "viewMockApi.html",
									success : function(layero, index) {
										var body = layui.layer.getChildFrame(
												'body', index);
										body.find("#id").val(edit.id);
										body.find("#apiName").val(edit.apiName);
										body.find("#apiPath").val(edit.apiPath)
										body.find("#apiParams").val(
												edit.apiParams);
										body.find("#createDate").val(
												edit.createDate);
										body.find("#updateDate").val(
												edit.updateDate);
										body.find("#requestMethodType").val(
												edit.requestMethodType);
										body.find("#requestMimeType").val(
												edit.requestMimeType);
										body.find("#applicationId").val(
												edit.applicationInfo.appName);
										body.find("#expectedResponse").val(
												edit.expectedResponse);
										body.find("#description").val(
												edit.description);
										form.render();
										setTimeout(
												function() {
													layui.layer
															.tips(
																	'点击此处返回列表',
																	'.layui-layer-setwin .layui-layer-close',
																	{
																		tips : 3
																	});
												}, 500)
									}
								});
					}

					$("#addMockApi_btn").click(function() {
						addMockApi();
					});

					// 搜索MockApi
					$("#searchMockApi")
							.on(
									"click",
									function() {
										var searchContent = $(
												"#serachMockApiContent").val()
												.trim();
										var searchContentOfApp = $(
												"#serachMockApiContentApp")
												.val().trim();

										// 如果两个只有一个有内容，则单个搜索
										// 如果两个都有内容，则组合搜索
										var mockApiListData = [];

										if (searchContent && searchContentOfApp) {
											mockApiListData = getMockApiListWithConditionAndAppInfo(
													searchContent,
													searchContentOfApp);
										} else if (searchContent) {
											mockApiListData = getMockApiListWithCondition(searchContent);
										} else if (searchContentOfApp) {
											mockApiListData = getMockApiListWithAppInfo(searchContentOfApp);
										} else {
											mockApiListData = getMockApiListWithCondition("");
										}

										// 搜索完之后清空搜索数据
										// 表格重载
										table.reload("mockApiListTable", {
											data : mockApiListData.data
										});
									});

					// 刷新功能
					$("#refreshMockApiList").on("click", function() {
						$("#serachMockApiContent").val("");
						renderMockApiTableDefault();
					});

					// 监听行工具事件
					table.on('tool(mockApiListTable)', function(obj) {
						// 注：tool
						// 是工具条事件名，test 是
						// table 原始容器的属性
						// lay-filter="对应的值"
						var data = obj.data;
						// 获得当前行数据
						var layEvent = obj.event;

						console.log(obj);
						// 获得 lay-event 对应的值
						if (layEvent === 'del') {
							layer.confirm('真的删除行么', function(index) {
								var url = "/mockApi/deleteMockApi";
								var postData = {
									"id" : data.id
								};
								$.post(url, postData, function(responseData) {
									if (responseData.success == "true"
											|| responseData.success) {
										layer.msg('删除成功！');
										obj.del();
										renderMockApiTableDefault();
										// 删除对应行（tr）的DOM结构
									} else {
										layer.msg('删除失败：' + responseData.msg);
									}
								});
								layer.close(index);
								// 向服务端发送删除指令
							});
						} else if (layEvent === 'edit') {
							editMockApi(data);
						} else if (layEvent === 'copy') {
							copyMockApi(data);
						}
					});

					table.on('rowDouble(mockApiListTable)', function(obj) {
						viewMockApi(obj.data);
					});
				});
