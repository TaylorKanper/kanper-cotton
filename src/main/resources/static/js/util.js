let utilsFn = function () {
    return {
        serializeObject: function (form) {
            let o = {};
            $.each(form.serializeArray(), function () {

                if (o[this['name']]) {

                    o[this['name']] = o[this['name']] + "," + this['value'];

                } else {
                    o[this['name']] = this['value'];
                }
            });
            return o;
        },
        handleActionResult: function (result) {
            if (result.code == 0) {
                toastr.success('操作成功');
                return true;
            } else if (result.code == 1) {
                toastr.error('操作失败');
                return false;
            }
            return false;
        }
    }
};
/**
 * 工具类
 * @type {{}}
 */
let utils = utilsFn();