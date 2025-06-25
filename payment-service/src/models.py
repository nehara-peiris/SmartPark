from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class Payment(db.Model):
    __tablename__ = 'payments'

    id = db.Column(db.String(255), primary_key=True)
    reservation_id = db.Column(db.String(255), nullable=False)
    amount = db.Column(db.Float, nullable=False)
