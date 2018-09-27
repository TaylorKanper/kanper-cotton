;
$(function () {
    backend.initEvent();
});
let backendFn = function () {
    return {
        initDom: function () {

        },
        initEvent: function () {
            // 添加供应商
            $('#add-supplier').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    supplierName: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    phone: {
                        validators: {
                            notEmpty: {},
                            phone: {
                                country: 'CN'
                            }
                        }
                    }
                }
            }).on('success.form.bv', function (e) {
                // Prevent form submission
                e.preventDefault();
                // Get the form instance
                let $form = $(e.target);
                $.ajax({
                    url: '/supplier/add',
                    data: $form.serialize(),
                    method: 'post',
                    success: function (data) {
                        $('#add-supplier-frame').modal('hide');
                        $('#add-supplier').bootstrapValidator('resetForm', true);
                        toastr.success('添加供应商' + data.data.supplierName + '成功');
                    },
                    error: function (e) {
                        toastr.error(e.responseJSON.msg);
                    }
                });
            });
            // 添加商品
            $('#add-goods').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    id: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    secondCategoryName: {
                        validators: {
                            notEmpty: {}
                        }
                    }
                }
            }).on('success.form.bv', function (e) {
                // Prevent form submission
                e.preventDefault();
                // Get the form instance
                let $form = $(e.target);
                $.ajax({
                    url: '/secondCategory/add',
                    data: $form.serialize(),
                    method: 'post',
                    success: function (data) {
                        $('#add-goods-frame').modal('hide');
                        $('#add-goods').bootstrapValidator('resetForm', true);
                        toastr.success('添加商品' + data.data.secondCategoryName + '成功');
                    },
                    error: function (e) {
                        toastr.error(e.responseJSON.msg);
                    }
                });
            });
            // 添加分类
            $('#addSort').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    firstCategoryName: {
                        validators: {
                            notEmpty: {}
                        }
                    }
                }
            }).on('success.form.bv', function (e) {
                // Prevent form submission
                e.preventDefault();
                // Get the form instance
                let $form = $(e.target);
                $.ajax({
                    url: '/firstCategory/add',
                    data: $form.serialize(),
                    method: 'post',
                    success: function (data) {
                        $('#add-category-frame').modal('hide');
                        $('#addSort').bootstrapValidator('resetForm', true);
                        toastr.success('添加分类' + data.data.firstCategoryName + '成功');
                    },
                    error: function (e) {
                        toastr.error(e.responseJSON.msg);
                    }
                });
            });
            $('#add-goods-frame').on('show.bs.modal', function () {
                $.ajax({
                    url: '/firstCategory/getAllCategory',
                    success: function (data) {
                        let options = '';
                        if (data && data.length != 0) {
                            for (const datum of data) {
                                options += '<option value=\'' + datum.id + '\'>' + datum.firstCategoryName + '</option>';
                            }
                            $('#category-select').html(options).selectpicker('refresh');
                        }
                    }
                })
            });
            $('#all-category-frame').on('show.bs.modal', function () {
                $('#all-category-table').bootstrapTable({
                    classes: "table table-hover table-striped table-bordered templatemo-user-table",
                    columns: [{
                        field: 'id',
                        title: '分类ID'
                    }, {
                        field: 'firstCategoryName',
                        title: '分类名称'
                    }],
                    cache: false,
                    url: '/firstCategory/getAllCategory'
                });
            });
            $('#all-goods-frame').on('show.bs.modal', function () {
                $('#all-goods-table').bootstrapTable({
                    classes: "table table-hover table-striped table-bordered templatemo-user-table",
                    columns: [{
                        field: 'id',
                        title: '商品ID'
                    }, {
                        field: 'secondCategoryName',
                        title: '商品名称'
                    }, {
                        field: 'firstCategory.firstCategoryName',
                        title: '分类名称'
                    }],
                    pagination: true,
                    cache: false,
                    url: '/secondCategory/getAllGoods'
                });
            });
            $('#all-supplier-frame').on('show.bs.modal', function () {
                $('#all-suppliers-table').bootstrapTable({
                    classes: "table table-hover table-striped table-bordered templatemo-user-table",
                    columns: [{
                        field: 'id',
                        title: '供应商ID'
                    }, {
                        field: 'supplierName',
                        title: '供应商名称'
                    }, {
                        field: 'phone',
                        title: '供应商电话'
                    }],
                    pagination: true,
                    cache: false,
                    url: '/supplier/allSuppliers'
                });
            });
        }
    }
};
let backend = backendFn();