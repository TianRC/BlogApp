const dateUtil = require('../util/DateUtil')
(function () {
    "use strict"
    var _global;

    function result(args, type) {
        var argsArr = Array.prototype.slice.call(args);
        if (argsArr.length == 0) return 0;
        switch (type) {
            case 1:
                return argsArr.reduce(function (tag, str) {
                    console.log(tag, dateUtil.getNowFormatDate() + `:`, strFormat(str))
                    console.log("\n\n")
                });
            case 2:
                return argsArr.reduce(function (tag, str) {
                    console.error(tag, dateUtil.getNowFormatDate() + `:`, strFormat(str))
                    console.log("\n\n")
                });
            default:
                return 0;
        }
    }

    /**
     * 格式化
     * @param str
     * @returns {*}
     */
    function strFormat(str) {
        var context = str
        if ((typeof str === 'object')) {
           context =   "\n" +JSON.stringify(str, null, "\t")
        }
        return context;
    }

    function Logger() {
    }

    Logger.prototype.v = function () {
        result(arguments, 1)
        return this;
    };
    Logger.prototype.error = function () {
        result(arguments, 2);
        return this;
    };

    _global = (function () {
        return this || (0, eval)('this');
    }());
    if (typeof module !== "undefined" && module.exports) {
        module.exports = Logger;
    } else if (typeof define === "function" && define.amd) {
        define(function () {
            return Logger;
        });
    } else {
        !('Logger' in _global) && (_global.Logger = Logger);
    }
}());


