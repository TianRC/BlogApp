const db = require('../db/DbHelper')
'use strict'

/**
 * 数据处理类
 * 200  请求成功
 *
 * 210  登录失败 用户名或密码错误
 * 211  token过期
 * 212  token 其他客户端登录
 *
 * 230 缺少字段
 */

class DataUtil {
    constructor() {
        this.init()
    }

    /**
     * 每个接口必须调用他
     */
    init() {
        this.code = 200;
        this.msg = "请求成功";
        this.data = {};
    }

    /**
     * 向外提供数据  有token
     * @param req
     * @param reply
     * @param data()  必须返回promise对象
     */
    provideWithTokenFind(req, reply, data) {
        this.init();
        this.checkToken(req)
            .then((value) => {
                    data().then((v) => {
                        this.deal(v, req.headers.token);
                        reply.send(this)
                    })
                },
                (e) => {
                    reply.send(this)
                })
    }

    provide(req, reply, data) {
        this.deal(data, req.headers.token);
        reply.send(this)
    }

    provideFind(req, reply, data) {
        data().then((v) => {
            if (v) {
                this.setCode(200)
                this.deal(v, req.headers.token);
                reply.send(this)
            }else {
                this.setCode(210)
                reply.send(this)

            }

        })
    }


    /**
     * 处理id
     * @param value
     * @returns {*}
     */
    deal(value, token) {
        console.log(" * 处理id\n" +
            "     * @param value\n" +
            "     * @returns {*}",value);
        this.data = value;
        this.data.id = value._id;
        delete this.data._id;
        delete this.data.psw;
        this.data.token = token;
        return this.data
    }

    checkToken(req) {
        var token = req.headers.token;
        return new Promise((resolve, reject) => {
            db.find("User", {"token": token})
                .then((v) => {
                    if (v.length) {
                        resolve(this.setCode(200))
                    } else {
                        reject(this.setCode(211))
                    }
                }, (e) => {
                    reject(this.setCode(211))
                })

        })

    }

    setCode(value) {
        this.init();
        this.code = value;
        switch (value) {
            case 210:
                this.setMsg("用户名或密码错误")
                break;
            case 211:
                this.setMsg("token过期")
                break;
            case 212:
                this.setMsg("其他客户端登录")
                break;
            case 230:
                this.setMsg("缺少字段")
                break;
        }
        return this
    }

    setMsg(value) {
        this.msg = value;
        return this
    }
}

dataUtil = new DataUtil();
module.exports = dataUtil;