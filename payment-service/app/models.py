from . import db
import datetime

class Payment(db.Model):
    __tablename__ = 'payments'

    payment_id = db.Column(db.String(36), primary_key=True)
    user_id = db.Column(db.String(50), nullable=False)
    parking_space_id = db.Column(db.String(50), nullable=False)
    amount = db.Column(db.Float, nullable=False)
    payment_method = db.Column(db.String(50), nullable=False)
    status = db.Column(db.String(20), nullable=False)
    timestamp = db.Column(db.DateTime, default=datetime.datetime.utcnow)

    def to_dict(self):
        return {
            'payment_id': self.payment_id,
            'user_id': self.user_id,
            'parking_space_id': self.parking_space_id,
            'amount': self.amount,
            'payment_method': self.payment_method,
            'status': self.status,
            'timestamp': self.timestamp.isoformat() + 'Z'
        }
