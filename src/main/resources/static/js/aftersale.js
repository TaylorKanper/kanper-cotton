;$(function () {
    afterSale.initDom();
    afterSale.initEvent();
});
let afterSaleFn = function () {
    return {
        initDom: function () {
            $('#sold-goods-table').bootstrapTable({
                url: '/soldGoods/querySoldGoodsByDate',
                classes: "table table-hover table-striped table-bordered templatemo-user-table",
                columns: [{
                    field: 'id',
                    title: 'No.',
                    formatter: function (v, r, index) {
                        return index + 1;
                    }
                }, {
                    field: 'goodsBean.secondCategory.firstCategory.firstCategoryName',
                    title: '商品分类',
                    sortable: true
                }, {
                    field: 'goodsBean.secondCategory.secondCategoryName',
                    title: '商品名称',
                    sortable: true
                }, {
                    field: 'goodsBean.supplier.supplierName',
                    title: '供应商',
                    sortable: true
                }, {
                    field: 'soldNumber',
                    title: '数量',
                    sortable: true
                }, {
                    field: 'goodsBean.soldPrice',
                    title: '单价',
                    sortable: true
                }, {
                    field: 'soldPrice',
                    title: '成交单价',
                    sortable: true
                }, {
                    field: 'discount',
                    title: '折扣',
                    sortable: true
                }, {
                    field: 'memberBean.memberName',
                    title: '会员名',
                    sortable: true
                }, {
                    field: 'buyDate',
                    title: '购买时间'
                }, {
                    field: 'function',
                    title: '退货',
                    formatter: function (value, row) {
                        return "<button type='button' class='btn btn-xs btn-danger' data-id='" + row.id + "'>退货</button>";
                    }
                }],
                queryParams: function (params) {
                    params.queryDate = $('#query-date').val();
                    return params;
                },
                search: true,
                showRefresh: true,
                showFullscreen: true,
                showToggle: true,
                showExport: true,
                showPaginationSwitch: true,
                showColumns: true,
                pagination: true,
                cache: false,
                onClickRow: function (row, $el, field) {

                },
                onDblClickRow: function (row) {

                },
                onLoadSuccess: function (data) {
                    let total = 0;
                    for (const datum of data) {
                        total += datum.soldPrice * datum.soldNumber * datum.discount;
                    }
                    $('#sold-total').html(total.toFixed(1));
                }
            });
        },
        initEvent: function () {
            $('#query-btn').on('click', function () {
                $('#sold-goods-table').bootstrapTable('refresh', {query: {queryDate: $('#query-date').val()}})
            });

            $('#sold-goods-table').on('click', 'button.btn-danger', function () {
                let goodsId = $(this).attr('data-id');
                $.ajax({
                    url: '/soldGoods/returnGoods',
                    data: {goodsId: goodsId},
                    method: 'post',
                    success: function (data) {
                        if (data.code == 0) {
                            toastr.success(data.msg);
                            $('#sold-goods-table').bootstrapTable('refresh');
                        } else {
                            toastr.error(data.msg);
                        }
                    },
                    error: function (e) {
                        toastr.error(e.responseJSON.msg);
                    }
                });
            })
        }
    }
};
let afterSale = afterSaleFn();