const express = require("express");
const cors = require("cors");
const dotenv = require("dotenv");
const connectDB = require("./config/db");

dotenv.config();
const app = express();
const PORT = process.env.PORT || 3003;

connectDB();

app.use(cors());
app.use(express.json());

app.use("/api/vehicles", require("./routes/vehicle.routes"));

app.listen(PORT, () => console.log(`Vehicle Service running on port ${PORT}`));


const eurekaClient = require('./eureka');

app.listen(PORT, () => {
    console.log(`🚀 Vehicle Service running on port ${PORT}`);
    eurekaClient.start((err) => {
        if (err) {
            console.error('Eureka registration failed:', err);
        } else {
            console.log('✅ Registered with Eureka');
        }
    });
});
