import Vehicle from '../models/vehicle.model.js';

export const registerVehicle = async (data) => {
    return await Vehicle.create(data);
};

export const getAllVehicles = async () => {
    return await Vehicle.find();
};

export const getVehicleById = async (id) => {
    return await Vehicle.findById(id);
};

export const updateVehicle = async (id, data) => {
    return await Vehicle.findByIdAndUpdate(id, data, { new: true });
};

export const deleteVehicle = async (id) => {
    return await Vehicle.findByIdAndDelete(id);
};
