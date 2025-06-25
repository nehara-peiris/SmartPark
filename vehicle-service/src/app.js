import express from 'express';
import vehicleRoutes from './routes/vehicle.routes.js';
import dotenv from 'dotenv';
import cors from 'cors';

dotenv.config();

const app = express();
app.use(cors());
app.use(express.json());

app.use('/api/vehicles', vehicleRoutes);

export default app;
