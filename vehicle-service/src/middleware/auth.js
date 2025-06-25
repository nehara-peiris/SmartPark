import jwt from 'jsonwebtoken';

export const authenticate = (req, res, next) => {
    const authHeader = req.headers.authorization;

    if (!authHeader || !authHeader.startsWith('Bearer ')) {
        return res.status(401).json({ error: 'Token missing or invalid' });
    }

    const token = authHeader.split(' ')[1];

    try {
        const decoded = jwt.verify(token, process.env.JWT_SECRET);

        // ✅ Fix here
        req.user = {
            id: decoded.sub,
            role: decoded.role
        };

        next();
    } catch (err) {
        return res.status(401).json({ error: 'Invalid or expired token' });
    }
};
