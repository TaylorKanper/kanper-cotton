;$(function () {
    home.initDom();
});
let homeFn = function () {
    return {
        initDom: function () {
            $('#today-table').bootstrapTable({
                url: '/soldGoods/queryToday',
                classes: "table table-hover table-striped table-bordered templatemo-user-table table-responsive",
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
                }],
                search: true,
                showRefresh: true,
                showFullscreen: true,
                showToggle: true,
                showExport: true,
                showPaginationSwitch: false,
                showColumns: true,
                pagination: false,
                cache: false,
                onLoadSuccess: function (data) {
                    let total = 0;
                    for (const datum of data) {
                        total += datum.soldPrice * datum.soldNumber * datum.discount;
                    }
                    $('#total-sold').html(total.toFixed(1));
                }
            });
        }
    }
};
let home = homeFn();