const Vehicle = require('../models/Vehicle');

exports.createVehicle = async (req, res) => {
    const userId = req.headers['x-user-id'];
    if (!userId) return res.status(401).json({ error: 'Unauthorized' });

    try {
        const vehicle = await Vehicle.create({ ...req.body, ownerId: userId });
        res.status(201).json(vehicle);
    } catch (err) {
        res.status(400).json({ error: err.message });
    }
};

exports.getVehicles = async (req, res) => {
    const vehicles = await Vehicle.find();
    res.json(vehicles);
};

exports.getVehicleById = async (req, res) => {
    const vehicle = await Vehicle.findById(req.params.id);
    if (!vehicle) return res.status(404).json({ error: 'Not found' });
    res.json(vehicle);
};
