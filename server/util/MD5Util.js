let CryptoJS = require('crypto-js')

class MD5Util {
    md5Encrypt(signStr) {
        return CryptoJS.MD5(signStr).toString()
    }
}

mD5Util = new MD5Util();
module.exports = mD5Util;