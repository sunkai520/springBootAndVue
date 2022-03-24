/**
 * 时间戳转换
 * @param {number} date 时间戳 
 * @param {string} fmt 转换格式（例如：'yyyy.MM.dd'、'hh:mm:ss'、'yyyy-MM-dd hh:mm:ss'...）
 */
export function formatDate(date, fmt = "yyyy-MM-dd hh:mm:ss") {
    if (!date) return ''
    const newDate = new Date(date)
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (newDate.getFullYear() + '').substr(4 - RegExp.$1.length))
    }
    const o = {
        'M+': newDate.getMonth() + 1,
        'd+': newDate.getDate(),
        'h+': newDate.getHours(),
        'm+': newDate.getMinutes(),
        's+': newDate.getSeconds()
    }
    for (const k in o) {
        if (new RegExp(`(${k})`).test(fmt)) {
            const str = o[k] + ''
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : padLeftZero(str))
        }
    }
    return fmt
}
export let myRegExp = {
    // 检查字符串是否为合法QQ号码
    isQQ: function (str) {
        // 1 首位不能是0 ^[1-9]
        // 2 必须是 [5, 11] 位的数字 \d{4, 9}
        let reg = /^[1-9][0-9]{4,9}$/gim;
        if (reg.test(str)) {
            return true;
        } else {
            return false;
        }
    },
    // 检查字符串是否为合法手机号码
    isPhone: function (str) {
        let reg = /^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57]|17[678])[0-9]{8}$/;
        if (reg.test(str)) {
            return true;
        } else {
            return false;
        }
    },
    // 检查字符串是否为合法Email地址
    isEmail: function (str) {
        let reg = /^\w+((-\w+)|(\.\w+))*@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        if (reg.test(str)) {
            return true;
        } else {
            return false;
        }
    },
    // 检查字符串是否是数字
    isNumber: function (str) {
        let reg = /^\d+$/;
        if (reg.test(str)) {
            return true;
        } else {
            return false;
        }
    },
    // 去掉前后空格
    trim: function (str) {
        let reg = /^\s+|\s+$/g;
        return str.replace(reg, '');
    },
    // 检查字符串是否存在中文
    isChinese: function (str) {
        let reg = /[\u4e00-\u9fa5]/gm;
        if (reg.test(str)) {
            return true;
        } else {
            return false;
        }
    },
    //检查是否全是中文
    isAllChinese: function (str) {
        let reg = /^([\u4E00-\u9FA5])*$/
        if (reg.test(str)) {
            return true;
        } else {
            return false;
        }
    },
    // 检查字符串是否为合法邮政编码
    isPostcode: function (str) {
        // 起始数字不能为0，然后是5个数字 [1-9]\d{5}
        let reg = /^[1-9]\d{5}$/g;
        if (reg.test(str)) {
            return true;
        } else {
            return false;
        }
    },
    // 检查字符串是否为合法身份证号码
    isIDcard: function (str) {
        let reg = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
        if (reg.test(str)) {
            return true;
        } else {
            return false;
        }
    },
    // 检查字符串是否为合法URL
    isURL: function (str) {
        let reg = /^https?:\/\/(([a-zA-Z0-9_-])+(\.)?)*(:\d+)?(\/((\.)?(\?)?=?&?[a-zA-Z0-9_-](\?)?)*)*$/i;
        if (reg.test(str)) {
            return true;
        } else {
            return false;
        }
    },
    // 检查字符串是否为合法日期格式 yyyy-mm-dd
    isDate: function (str) {
        let reg = /^[1-2][0-9][0-9][0-9]-[0-1]{0,1}[0-9]-[0-3]{0,1}[0-9]$/;
        if (reg.test(str)) {
            return true;
        } else {
            return false;
        }
    },
    // 检查字符串是否为合法IP地址
    isIP: function (str) {
        // 1.1.1.1 四段 [0 , 255]
        // 第一段不能为0
        // 每个段不能以0开头
        //
        // 本机IP: 58.50.120.18 湖北省荆州市 电信
        let reg = /^([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\.([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3}$/gi;
        if (reg.test(str)) {
            return true;
        } else {
            return false;
        }
    }

}
//判断上传文件类型
export function getFileType(filePath) {
    var startIndex = filePath.lastIndexOf(".");
    if (startIndex != -1)
        return filePath.substring(startIndex + 1, filePath.length).toLowerCase();
    else return "";
}

export function bytesToSize(bytes) {
    var size = '8164674';
    if (bytes === 0) return '0 B';
    var k = 1000, // or 1024
        sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
        i = Math.floor(Math.log(bytes) / Math.log(k));

    return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
}