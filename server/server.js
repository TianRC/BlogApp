const MyMongoDb = require('./db/DbHelper')//连接数据库
const user = require('./src/activity/User');
const Logger =
    require('./logger/logger')
var logger = new Logger();

const fastify = require('fastify')({
    logger: false
})
const config = require('./config')
fastify.register(user, {prefix: '/user'})
fastify.listen(config.port, config.host, function (err) {
    if (err) throw err
    logger.v("___启动服务____", fastify.server.address())
})