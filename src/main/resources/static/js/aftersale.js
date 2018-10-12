;$(function () {
    afterSale.initEvent();
});
let afterSaleFn = function () {
    return {
        initEvent: function () {
            $('#query-btn').on('click', function () {
                $('#sold-goods-table').bootstrapTable({
                    url: '/soldGoods/querySoldGoodsByDate',
                    classes: "table table-hover table-striped table-bordered templatemo-user-table",
                    columns: [{
                        field: 'id',
                        title: '商品ID'
                    }, {
                        field: 'secondCategory.firstCategory.firstCategoryName',
                        title: '商品分类',
                        sortable: true
                    }, {
                        field: 'secondCategory.secondCategoryName',
                        title: '商品名称',
                        sortable: true
                    }, {
                        field: 'supplier.supplierName',
                        title: '供应商名称',
                        sortable: true
                    }, {
                        field: 'soldPrice',
                        title: '出售价格',
                        sortable: true
                    }, {
                        field: 'number',
                        title: '库存',
                        sortable: true
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
                    onLoadSuccess: function () {

                    }
                });
            }).trigger('click');
        }
    }
};
let afterSale = afterSaleFn();