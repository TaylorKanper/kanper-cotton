;
$(function () {
    backend.getSelectInfo();
    backend.initDom();
    backend.initEvent();
});
let backendFn = function () {
    return {
        initDom: function () {
            $("#goods-table").bootstrapTable({
                url: '/goods/getAllGoods',
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
                    field: 'buyPrice',
                    title: '进货价格',
                    sortable: true
                }, {
                    field: 'soldPrice',
                    title: '出售价格',
                    sortable: true
                }, {
                    field: 'number',
                    title: '库存',
                    sortable: true
                }, {
                    field: 'buyDate',
                    title: '进货时间',
                    sortable: true
                }, {
                    field: 'function',
                    title: '操作',
                    formatter: function (value, row) {
                        return "<button type='button' class='btn btn-xs btn-danger' data-id='" + row.id + "'>全部返货</button>";
                    }
                }],
                search: true,
                showRefresh: true,
                showFullscreen: true,
                showToggle: true,
                showExport: true,
                showPaginationSwitch: true,
                showColumns: true,
                pagination: true,
                cache: false,
                onDblClickRow: function (row) {
                    $('#update-product-frame').modal('show');
                    backend.initForm(row);
                },
                onLoadSuccess: function () {
                    $("#goods-table button.btn-danger").on('click', function () {
                        let goodsId = $(this).attr('data-id');
                        $.ajax({
                            url: '/goods/deleteGoods',
                            data: {goodsId: goodsId},
                            method: 'post',
                            success: function (data) {
                                if (data.code == 0) {
                                    $("#goods-table").bootstrapTable('refresh');
                                    toastr.success(data.msg);
                                } else {
                                    toastr.error(data.msg);
                                }
                            },
                            error: function (e) {
                                toastr.error(e.responseJSON.msg);
                            }
                        });
                    });
                }
            });
        },
        initForm: function (row) {
            $.ajax({
                url: '/secondCategory/getAllGoods',
                success: function (data) {
                    let options = '';
                    if (data && data.length != 0) {
                        for (const datum of data) {
                            options += '<option value=\'' + datum.id + '\'>' + datum.secondCategoryName + '</option>';
                        }
                        $('#update-secondCategory').html(options).selectpicker('refresh').selectpicker('val', row.secondCategory.id);
                    }
                }
            });
            $.ajax({
                url: '/supplier/allSuppliers',
                success: function (data) {
                    let options = '';
                    if (data && data.length != 0) {
                        for (const datum of data) {
                            options += '<option value=\'' + datum.id + '\'>' + datum.supplierName + '</option>';
                        }
                        $('#update-supplier').html(options).selectpicker('refresh').selectpicker('val', row.supplier.id);
                    }
                }
            });
            $('#update-buyPrice').val(row.buyPrice);
            $('#update-soldPrice').val(row.soldPrice);
            $('#update-number').val(row.number);
            $('#update-id').val(row.id);
        },
        secondCategory: undefined,
        supplier: undefined,
        getSelectInfo: function () {
            $.ajax({
                url: '/secondCategory/getAllGoods',
                success: function (data) {
                    let options = '';
                    if (data && data.length != 0) {
                        for (const datum of data) {
                            options += '<option value=\'' + datum.id + '\'>' + datum.secondCategoryName + '</option>';
                        }
                        $('#add-product-batch select[name=\'secondCategory.id\']').html(options).selectpicker('refresh');
                        backend.secondCategory = options;
                    }
                }
            });
            $.ajax({
                url: '/supplier/allSuppliers',
                success: function (data) {
                    let options = '';
                    if (data && data.length != 0) {
                        for (const datum of data) {
                            options += '<option value=\'' + datum.id + '\'>' + datum.supplierName + '</option>';
                        }
                        $('#add-product-batch select[name=\'supplier.id\']').html(options).selectpicker('refresh');
                        backend.supplier = options;
                    }
                }
            });
        },
        initEvent: function () {
            $('#add-product-row').on('click', function () {
                let row = "<tr>\n" +
                    "<td class=\"col-xs-1\"><select title=\"供应商\" name=\"supplier.id\" class=\"form-control supplier\" data-live-search=\"true\" data-size=\"5\"></select></td>\n" +
                    "<td class=\"col-xs-1\"><select title=\"商品名称\" name=\"secondCategory.id\" class=\"form-control secondCategory\" data-live-search=\"true\"\n" +
                    "                                                         data-size=\"5\"></select></td>\n" +
                    "<td class=\"col-xs-1\"><input title=\"进价\" name=\"buyPrice\" type=\"number\" class=\"form-control input-sm\"></td>\n" +
                    "<td class=\"col-xs-1\"><input title=\"售价\" name=\"soldPrice\" type=\"number\" class=\"form-control input-sm\"></td>\n" +
                    "<td class=\"col-xs-1\"><input title=\"进货数量\" name=\"number\" type=\"number\" class=\"form-control input-sm\" value=\"1\"></td>\n" +
                    "</tr>";
                $(row).appendTo('#add-product-batch');
                $('#add-product-batch tr').last().find('select[name=\'secondCategory.id\']').html(backend.secondCategory).selectpicker('refresh');
                $('#add-product-batch tr').last().find('select[name=\'supplier.id\']').html(backend.supplier).selectpicker('refresh');
            });
            $('#add-product-form').bootstrapValidator().on('success.form.bv', function (e) {
                // Prevent form submission
                e.preventDefault();
                // Get the form instance
                let form = utils.serializeTable($('#add-product-batch'));
                $.ajax({
                    url: '/goods/batchAdd',
                    data: JSON.stringify(form),
                    dataType: "json",
                    contentType: 'application/json',
                    method: 'post',
                    success: function (data) {
                        $('#add-product-frame').modal('hide');
                        $('#add-product-form')[0].reset();
                        if (data.code == 0) {
                            toastr.success(data.msg);
                            $('#add-product-batch tr').remove('.product-add-row');
                            $('#goods-table').bootstrapTable('refresh');
                        }
                    },
                    error: function (e) {
                        toastr.error(e.responseJSON.msg);
                    }
                });
            });
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
                        toastr.success(data.msg);
                    },
                    error: function (e) {
                        if (e.status == 401) {
                            toastr.error('你必须具备管理员权限才能进行操作');
                            return;
                        }
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
                        toastr.success(data.msg);
                    },
                    error: function (e) {
                        if (e.status == 401) {
                            toastr.error('你必须具备管理员权限才能进行操作');
                            return;
                        }
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
                        toastr.success(data.msg);
                    },
                    error: function (e) {
                        if (e.status == 401) {
                            toastr.error('你必须具备管理员权限才能进行操作');
                            return;
                        }
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
                    }, {
                        field: 'transactionAmount',
                        title: '进货总额'
                    }],
                    pagination: true,
                    cache: false,
                    url: '/supplier/allSuppliers'
                });
            });
            $('#update-product-form').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    'secondCategory.id': {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    'supplier.id': {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    buyPrice: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    soldPrice: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    number: {
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
                    url: '/goods/updateGoods',
                    data: $form.serialize(),
                    method: 'post',
                    success: function (data) {
                        $('#update-product-frame').modal('hide');
                        $('#goods-table').bootstrapTable('refresh');
                        toastr.success(data.msg);
                    },
                    error: function (e) {
                        if (e.status == 401) {
                            toastr.error('你必须具备管理员权限才能进行操作');
                            return;
                        }
                        toastr.error(e.responseJSON.msg);
                    }
                });
            });
        }
    }
};
let backend = backendFn();