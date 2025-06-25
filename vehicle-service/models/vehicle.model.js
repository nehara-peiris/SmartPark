import mongoose from 'mongoose';

const vehicleSchema = new mongoose.Schema({
    plateNumber: {
        type: String,
        required: true,
        unique: true,
    },
    type: {
        type: String,
        enum: ['CAR', 'BIKE', 'TRUCK'],
        required: true,
    },
    ownerId: {
        type: String,
        required: true,
    },
    brand: String,
    model: String,
    color: String,
}, { timestamps: true });


export default mongoose.model('Vehicle', vehicleSchema);
