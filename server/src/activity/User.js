const db = require('../../db/DbHelper')
const Logger = new require('../../logger/logger');
const logger = new Logger();
const TAG = "User";
const DataUtil = require('../../util/DataUtil');
const DateUtil = require('../../util/DateUtil');
const MD5Util = require('../../util/MD5Util');

module.exports = function (fastify, opts, next) {
    console.log('opts', opts);
    fastify.get('/', function (req, reply) {
        DataUtil.provideWithTokenFind(req, reply, () => {
            return new Promise((resolve, reject) => {
                db.find_One(TAG).then(v => {
                    resolve(v)
                }, (e) => {
                    reject(e)
                })
            })
        });
    });

    /**
     * 注册
     */
    fastify.post('/registered', (req, reply) => {
        console.log('/registered', req.body);
        db.drop(TAG)
        if (!req.body.psw) {
            DataUtil.setCode(230).provide(req, reply, {})
            return
        }
        if (!req.body.userName) {
            DataUtil.setCode(230).provide(req, reply, {})
            return
        }

        db.insertOne(TAG, {
            "userName": req.body.userName,
            "psw": req.body.psw,
            "createDate": new Date(),
        }).then((v) => {
            db.insertOne("token", {
                "token": MD5Util.md5Encrypt(DateUtil.getDateStamp()),
                "userId": v.ops[0]._id
            }).then((v) => {
                DataUtil.provide(req, reply, {})
            })

        });
    });

    /**
     * 登录
     */
    fastify.post('/login', (req, reply) => {
        if (!req.body.userName) {
            DataUtil.setCode(230)
                .provide(req, reply, {})
        }
        if (!req.body.psw) {
            DataUtil.setCode(230)
                .provide(req, reply, {})
        }

        DataUtil.provideFind(req, reply, () => {
            return new Promise((resolve, reject) => {
                db.find_One(TAG, {
                    "userName": req.body.userName,
                    "psw": req.body.psw
                }).then(v => {
                    resolve(v)
                })
            })
        })


    });
    next()
};

