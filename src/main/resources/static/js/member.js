$(function () {
    member.initDom();
    member.initEvent();
});
let memberFn = function () {
    return {
        initDom: function () {
            $('#member-table').bootstrapTable({
                url: '/member/getAllMember',
                classes: "table table-hover table-striped table-bordered templatemo-user-table",
                columns: [{
                    field: 'id',
                    title: '会员ID'
                }, {
                    field: 'memberName',
                    title: '会员名称',
                    sortable: true
                }, {
                    field: 'phone',
                    title: '会员手机',
                    sortable: true
                }, {
                    field: 'integral',
                    title: '会员积分',
                    sortable: true
                }, {
                    field: 'birthday',
                    title: '会员生日',
                    sortable: true
                }, {
                    field: 'createDate',
                    title: '会员创建时间',
                    sortable: true
                }, {
                    field: 'function',
                    title: '操作',
                    formatter: function (value, row) {
                        return "<button type='button' class='btn btn-xs btn-danger' data-id='" + row.id + "'>删除</button>";
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
                onClickRow: function (row, $el, field) {


                },
                onDblClickRow: function (row) {

                    $('#updatemyModalLabel').html("修改会员==>" + row.memberName);
                    $('#update-member-name').val(row.memberName);
                    $('#update-member-id').val(row.id);
                    $('#update-member-phone').val(row.phone);
                    $('#update-member-birth').val(row.birthday);
                    $('#update-member-integral').val(row.integral);

                    $('#update-member').modal('show');
                },
                onLoadSuccess: function () {
                    $("#member-table button.btn-danger").on('click', function () {
                        let memberId = $(this).attr('data-id');
                        $.ajax({
                            url: '/member/delMember',
                            data: {memberId: memberId},
                            method: 'post',
                            success: function (data) {
                                if (data.code == 0) {
                                    $("#member-table").bootstrapTable('refresh');
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
        initEvent: function () {
            $('#update-member-form').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    integral: {
                        validators: {
                            notEmpty: {},
                            greaterThan: {
                                value: 0,
                                inclusive: false,
                                message: '积分不能低于0'
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
                    url: '/member/updateMember',
                    data: $form.serialize(),
                    method: 'post',
                    success: function (data) {
                        if (data.code == 0) {
                            $('#update-member').modal('hide');
                            $('#update-member-form').bootstrapValidator('resetForm', true);
                            $('#member-table').bootstrapTable('refresh');
                            toastr.success(data.msg);
                        } else {
                            toastr.error(data.msg);
                        }

                    },
                    error: function (e) {
                        toastr.error(e.responseJSON.message);
                    }
                });
            });
            $('#add-member-form').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    memberName: {
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
                    },
                    birthday: {
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
                    url: '/member/addMember',
                    data: $form.serialize(),
                    method: 'post',
                    success: function (data) {
                        if (data.code == 0) {
                            $('#add-member').modal('hide');
                            $('#add-member-form').bootstrapValidator('resetForm', true);
                            $('#member-table').bootstrapTable('refresh');
                            toastr.success(data.msg);
                        } else {
                            toastr.error(data.msg);
                        }

                    },
                    error: function (e) {
                        toastr.error(e.responseJSON.message);
                    }
                });
            });
        }
    }
};
let member = memberFn();