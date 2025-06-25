import { Eureka } from 'eureka-js-client';
import dotenv from 'dotenv';
dotenv.config();

export const registerWithEureka = () => {
    const client = new Eureka({
        instance: {
            app: 'vehicle-service',
            hostName: 'localhost',
            ipAddr: '127.0.0.1',
            port: {
                '$': process.env.PORT || 3003,
                '@enabled': true
            },
            vipAddress: 'vehicle-service',
            dataCenterInfo: {
                '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
                name: 'MyOwn'
            }
        },
        eureka: {
            host: 'localhost',
            port: 8761,
            servicePath: '/eureka/apps/'
        }
    });

    client.start(err => {
        console.log(err || "✅ Registered with Eureka");
    });
};
