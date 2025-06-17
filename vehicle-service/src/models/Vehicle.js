const mongoose = require('mongoose');

const vehicleSchema = new mongoose.Schema({
    ownerId: { type: String, required: true },
    plateNumber: { type: String, required: true, unique: true },
    type: { type: String, enum: ['car', 'bike', 'van'], required: true },
    color: String,
    registeredAt: { type: Date, default: Date.now }
});

module.exports = mongoose.model('Vehicle', vehicleSchema);
