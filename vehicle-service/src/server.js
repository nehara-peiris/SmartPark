import app from './app.js';
import { connectDB } from './config/db.js';
import { registerWithEureka } from './config/eureka.js';

const PORT = process.env.PORT || 3003;

connectDB()
    .then(() => {
        app.listen(PORT, () => {
            console.log(`🚗 Vehicle Service running on port ${PORT}`);
            registerWithEureka();
        });
    })
    .catch((err) => {
        console.error('❌ Failed to connect to MongoDB:', err.message);
    });
