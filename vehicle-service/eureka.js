import { Eureka } from 'eureka-js-client';

const client = new Eureka({
    instance: {
        app: 'vehicle-service',
        instanceId: 'vehicle-service',
        hostName: 'localhost',
        ipAddr: '127.0.0.1',
        statusPageUrl: 'http://localhost:3003/info',
        port: {
            '$': 3003,
            '@enabled': true,
        },
        vipAddress: 'vehicle-service',
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn',
        },
    },
    eureka: {
        host: 'localhost',
        port: 8761,
        servicePath: '/eureka/apps/',
    },
});

export default client;
