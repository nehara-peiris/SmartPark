import mongoose from "mongoose";

const vehicleSchema = new mongoose.Schema({
    userId: { type: String, required: true },
    plateNumber: { type: String, required: true, unique: true },
    type: { type: String, enum: ["car", "bike", "truck"], required: true },
    brand: String,
    model: String,
});

export default mongoose.model("Vehicle", vehicleSchema);
