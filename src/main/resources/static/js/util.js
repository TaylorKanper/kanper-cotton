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
        serializeTable: function (form) {
            let o = [];
            form.find('tr').each(function () {
                let obj = {};
                $(this).find('input, select').each(function () {
                    if (this.name != null && this.name.length != 0) {
                        if (this.name.indexOf('.')!=-1) {
                            let ss = this.name.split('.');
                            let o = {};
                            o[ss[1]] = $(this).val();
                            obj[ss[0]] = o;
                        } else {
                            obj[this.name] = $(this).val();
                        }
                    }
                });
                o.push(obj);
            });
            return o;
        }
    }
};
/**
 * 工具类
 * @type {{}}
 */
let utils = utilsFn();