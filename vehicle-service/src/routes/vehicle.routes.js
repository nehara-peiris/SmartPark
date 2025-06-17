const express = require('express');
const { createVehicle, getVehicles, getVehicleById } = require('../controllers/vehicle.controller');

const router = express.Router();

router.post('/', createVehicle);
router.get('/', getVehicles);
router.get('/:id', getVehicleById);

module.exports = router;
