import Vehicle from '../models/vehicle.model.js';

export const createVehicle = async (req, res) => {
    try {
        const vehicle = await Vehicle.create({
            ...req.body,
            ownerId: req.user.id // From token
        });
        res.status(201).json(vehicle);
    } catch (err) {
        res.status(400).json({ error: err.message });
    }
};

export const getVehicles = async (req, res) => {
    const vehicles = await Vehicle.find({ ownerId: req.user.id });
    res.json(vehicles);
};

export const getVehicleById = async (req, res) => {
    const vehicle = await Vehicle.findOne({ _id: req.params.id, ownerId: req.user.id });
    if (!vehicle) return res.status(404).json({ error: 'Not found' });
    res.json(vehicle);
};
