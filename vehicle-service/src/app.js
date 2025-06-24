import mongoose from "mongoose";
import dotenv from "dotenv";
import cors from "cors";
import vehicleRoutes from "./routes/vehicleRoutes.js";
import { Eureka } from "eureka-js-client";
import express from "express";

dotenv.config();
const app = express();

app.use(cors());
app.use(express.json());

app.use("/api/vehicles", vehicleRoutes);

mongoose.connect(process.env.MONGO_URI)
    .then(() => console.log("MongoDB Connected"))
    .catch((err) => console.error("MongoDB Error:", err));

// Register with Eureka
const client = new Eureka({
    instance: {
        app: process.env.SERVICE_NAME.toUpperCase(),
        instanceId: `${process.env.SERVICE_NAME}:${process.env.PORT}`,
        hostName: 'localhost',
        ipAddr: '127.0.0.1',
        port: {
            '$': process.env.PORT,
            '@enabled': true,
        },
        vipAddress: process.env.SERVICE_NAME,
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

client.start(err => {
    if (err) {
        console.error('Eureka registration failed:', err);
    } else {
        console.log('Registered with Eureka');
    }
});

app.listen(process.env.PORT, () => {
    console.log(`Vehicle service running on port ${process.env.PORT}`);
});
