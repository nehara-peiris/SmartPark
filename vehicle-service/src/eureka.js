const { Eureka } = require('eureka-js-client');
require('dotenv').config();

const port = process.env.PORT || 3003;

const client = new Eureka({
    instance: {
        app: process.env.SERVICE_NAME,
        instanceId: `${process.env.SERVICE_NAME}:${port}`,
        hostName: 'localhost',
        ipAddr: '127.0.0.1',
        port: {
            '$': port,
            '@enabled': true,
        },
        vipAddress: process.env.SERVICE_NAME,
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn',
        },
    },
    eureka: {
        host: process.env.EUREKA_CLIENT_HOST,
        port: process.env.EUREKA_CLIENT_PORT,
        servicePath: '/eureka/apps/',
    },
});

module.exports = client;
