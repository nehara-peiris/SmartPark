import express from 'express';
import { authenticate } from '../middleware/auth.js';
import {
    createVehicle,
    getVehicles,
    getVehicleById,
    updateVehicle,
    deleteVehicle
} from '../controllers/vehicle.controller.js';

const router = express.Router();

// Secure routes with middleware here
router.post('/', authenticate, createVehicle);
router.get('/', authenticate, getVehicles);
router.get('/:id', authenticate, getVehicleById);
router.put('/:id', authenticate, updateVehicle);
router.delete('/:id', authenticate, deleteVehicle);


export default router;
