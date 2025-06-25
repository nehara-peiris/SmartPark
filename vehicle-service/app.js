import express from 'express';
import mongoose from 'mongoose';
import vehicleRoutes from './src/routes/vehicle.routes.js';

const app = express();
app.use(express.json());

mongoose.connect('mongodb://localhost:27017/vehicle-db', {
    useNewUrlParser: true,
    useUnifiedTopology: true
}).then(() => console.log('MongoDB connected'))
    .catch(err => console.error('MongoDB connection error:', err));

// Routes
app.use('/api/vehicles', vehicleRoutes);

const PORT = process.env.PORT || 3003;
app.listen(PORT, () => {
    console.log(`Vehicle Service running on port ${PORT}`);
});

export default app;
