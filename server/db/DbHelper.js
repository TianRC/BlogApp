"use strict"
const MongoDb = require('mongodb');
const config = require('../config.json');
const Logger = new require('../logger/logger');
var logger = new Logger()
const TAG = "数据库工具类日志：";

var dbo;
/**
 * 连接次数
 * @type {number}
 */
let connectionCount = 0;
/**
 * 最大连接次数
 * @type {number}
 */
const MAX_CONNECT_COUNT = 3;
/**
 * 从新连接间隔时长
 * @type {number}
 */
const RECONNECT_TIME = 3000;
/**
 * 连接状态
 * @type {boolean}
 */
let connectionState = false;

class MyMongoClient {
    constructor() {
        this.init();
    }

    /**
     * 初始化
     */
    init() {
        if (connectionCount === MAX_CONNECT_COUNT) {
            logger.error(TAG, "——————————数据库挂了，唯物唯物唯物~~~~~~~~~~~");
            return;
        }
        connectionCount++;
        this.connect().then((v) => {
            logger.v(TAG, "——————————数据库连接成功——————————");
            connectionState = true;
            connectionCount = 0;
            dbo = v;
        }, (e) => {
            logger.error(TAG, "——————————数据库连接失败 3s后重新连接——————————");
            connectionState = false;
            setTimeout(() => {
                this.init()
            }, RECONNECT_TIME)
        });
    }

    /**
     * 连接
     * @returns {Promise}
     */
    connect(dbTable = config.mongoDbName) {
        return new Promise((resolve, reject) => {
            var MongoClient = MongoDb.MongoClient;
            MongoClient.connect(config.mongoDb, function (err, db) {
                if (err) reject(err);
                var dbo = db.db(dbTable);
                resolve(dbo)
            });
        });
    };

    /**
     *提供dbo
     */
    getDbo() {
        return dbo;
    }

    /**
     * 提供连接状态
     * @returns {boolean}
     */
    provideConnectionState() {
        return connectionState;
    }

    /**
     * 创建集合
     * @param name
     */
    createCollection(name) {
        return new Promise((resolve, reject) => {
            this.getDbo().createCollection(name, function (err, res) {
                if (err) reject(err);
                resolve()
                logger.v(TAG, `创建${name}集合!`);
            });
        })
    }


    /**
     * 删除集合
     * @param listName
     */
    drop(listName) {
        return new Promise((resolve, reject) => {
            this.getDbo().collection(listName).drop(function (err, delOK) {  // 执行成功 delOK 返回 true，否则返回 false
                if (err) reject(err);
                if (delOK) {
                    resolve(delOK)
                    logger.v(TAG, listName + "集合已删除");
                }
            });
        })


    }

    /**
     * 插入一条数据
     * @param listName
     * @param obj
     * { name: "田荣闯"}
     */
    insertOne(listName, obj) {
        return new Promise((resolve, reject) => {
            this.getDbo().collection(listName).insertOne(obj, function (err, res) {
                if (err) reject(err);
                logger.v(TAG, listName + "文档插入成功");
                resolve(res)
            });
        });
    }

    /**
     * 插入多条数据
     * @param listName
     * @param obj
     * [{ name: "田荣闯"},
     * { name: "习大大"}]
     */
    insertMany(listName, obj) {
        return new Promise((resolve, reject) => {
            this.getDbo().collection(listName)
                .insertMany(obj, function (err, res) {
                    if (err) reject(err);
                    logger.v(TAG, listName + "插入的文档数量为: " + res.insertedCount);
                    resolve(res.insertedCount)
                });
        });
    }

    /**
     * 查询数据
     * @param listName
     * @param whereStr
     */
    find(listName, whereStr = {}) {
        return new Promise((resolve, reject) => {
            this.getDbo().collection(listName)
                .find(whereStr)
                .toArray(function (err, result) { // 返回集合中所有数据
                    if (err) reject(err);
                    logger.v(TAG, listName + ":" + result);
                    resolve(result)
                });
        });
    }

    find_One(listName, whereStr = {}) {
        return new Promise((resolve, reject) => {
            this.getDbo().collection(listName)
                .findOne(whereStr, (err, result) => {
                    if (err) return reject(err);
                    logger.v(TAG, listName + ":" + result);
                    return resolve(result);
                })
        });
    }

    /**
     *更新一条数据
     * @param listName
     * @param whereStr
     * @param updateStr
     *
     * whereStr = {"name":'田荣闯'}
     * updateStr = {$set: { "url" : "https://tianronghcuang.com" }}
     * 实例将 name 为 "田荣闯" 的 url 改为 https://tianronghcuang.com
     */
    updateOne(listName, whereStr, updateStr) {
        return new Promise((resolve, reject) => {
            this.getDbo().collection(listName)
                .updateOne(whereStr, updateStr, function (err, res) {
                    if (err) reject(err);
                    logger.v(TAG, listName + "文档更新成功");
                    resolve(res)
                });
        });
    }

    /**
     * 更新多条数据
     * @param listName
     * @param whereStr
     * @param updateStr
     */
    updateMany(listName, whereStr, updateStr) {
        return new Promise((resolve, reject) => {
            this.getDbo().collection(listName)
                .updateMany(whereStr, updateStr, function (err, res) {
                    if (err) reject(err);
                    resolve(res)
                    console.log(TAG, listName + ":" + res.result.nModified + " 条文档被更新");
                });
        });
    }

    /**
     * 删除一条消息
     * @param listName
     * @param whereStr
     */
    deleteOne(listName, whereStr) {
        return new Promise((resolve, reject) => {
            this.getDbo().collection(listName)
                .deleteOne(whereStr, function (err, obj) {
                    if (err) reject(err);
                    resolve(obj)
                    console.log(TAG, listName + ":" + "文档删除成功");
                });
        });
    }

    /**
     * 删除多条消息
     * @param listName
     * @param whereStr
     * @returns {Promise}
     */
    deleteMany(listName, whereStr) {
        return new Promise((resolve, reject) => {
            this.getDbo()
                .collection(listName)
                .deleteMany(whereStr, function (err, obj) {
                    if (err) reject(err);
                    resolve(obj)
                    console.log(TAG, listName + ":" + obj.result.n + " 条文档被删除");
                });
        });
    }

    /**
     *排序查询
     * @param listName
     * @param sort
     * { type: 1 }  // 按 type 字段升序
     { type: -1 } // 按 type 字段降序
     */
    findSort(listName, sort, whereStr = {}) {
        return new Promise((resolve, reject) => {
            this.getDbo().collection(listName)
                .find(whereStr)
                .sort(sort)
                .toArray(function (err, result) {
                    if (err) reject(err);
                    resolve(result)
                    console.log(TAG, listName + ":" + result);
                });
        });
    }

    /**
     * 分页查询
     * @param listName
     * @param page
     * @param limit
     */
    findLimit(listName, page, limit) {
        return new Promise((resolve, reject) => {
            this.getDbo().collection(listName)
                .find()
                .skip((page - 1) * limit)
                .limit(limit)
                .toArray(function (err, result) {
                    if (err) reject(err);
                    resolve(result)
                    console.log(TAG, listName + ":" + result);
                });
        });
    }

    count(listName, where = {}) {
        return new Promise((resolve, reject) => {
            this.getDbo().collection(listName).find(where).count((err, num) => {
                if (err) return reject(err);
                console.log(TAG, listName + ":count=" + num);
                return resolve(num);
            });
        });
    }


}

const db = new MyMongoClient();

module.exports = db;

