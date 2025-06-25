import express from 'express';
import mongoose from 'mongoose';
import vehicleRoutes from './routes/vehicle.routes.js';
import eurekaClient from './eureka.js';
import dotenv from 'dotenv';

dotenv.config();
const app = express();
const PORT = process.env.PORT || 3003;

// Middleware
app.use(express.json());
app.use('/api/vehicles', vehicleRoutes);

app.get('/info', (req, res) => {
    res.send('Vehicle Service is running...');
});

// Connect to MongoDB
mongoose.connect('mongodb+srv://shewmipeiris123:e2BhTSL98zk08Ifn@vehicle-cluster.q1q7s3d.mongodb.net/vehicle-db?retryWrites=true&w=majority&appName=vehicle-cluster')
    .then(() => {
        console.log('Connected to MongoDB');

        app.listen(PORT, () => {
            console.log(`Vehicle Service running on port ${PORT}`);
            eurekaClient.start((err) => {
                console.log(err || 'Vehicle service registered with Eureka');
            });
        });
    })
    .catch(err => console.error('MongoDB connection error:', err));
