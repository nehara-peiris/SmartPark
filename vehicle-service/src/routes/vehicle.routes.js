import express from 'express';
import {
    createVehicle,
    getVehicles,
    getVehicleById,
    updateVehicle,
    deleteVehicle
} from '../controllers/vehicle.controller.js';
import { authenticate } from '../middleware/auth.js';

const router = express.Router();

router.use(authenticate); // secure all routes below

router.post('/', createVehicle);
router.get('/', getVehicles);
router.get('/:id', getVehicleById);
router.put('/:id', updateVehicle);
router.delete('/:id', deleteVehicle);

export default router;
