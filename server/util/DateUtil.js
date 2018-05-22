class DateUtil {
    CheckDateTime(str) {
        var reg = /^(\d+)-(\d{ 1,2 })-(\d{ 1,2 }) (\d{ 1,2 }):(\d{ 1,2 }):(\d{ 1,2 })$/;
        var r = str.match(reg);
        if (r == null) return false;
        r[2] = r[2] - 1;
        var d = new Date(r[1], r[2], r[3], r[4], r[5], r[6]);
        if (d.getFullYear() != r[1]) return false;
        if (d.getMonth() != r[2]) return false;
        if (d.getDate() != r[3]) return false;
        if (d.getHours() != r[4]) return false;
        if (d.getMinutes() != r[5]) return false;
        if (d.getSeconds() != r[6]) return false;
        return true;
    }

    /**
     * 获取当前的日期时间 格式“yyyy-MM-dd HH:MM:SS”
     * @returns {string}
     */
    getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
        return currentdate;
    }

    /**
     * 获取时间戳
     * @returns {number}
     */
    getDateStamp() {
        var timestamp2 = Date.parse(new Date());
        timestamp2 = timestamp2 / 1000;
        console.log(timestamp2);
        return timestamp2
    }
}


dateUtil = new DateUtil();
module.exports = dateUtil;