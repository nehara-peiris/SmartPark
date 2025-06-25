from flask import current_app
from . import db
from .models import Payment
import uuid
import datetime
import requests

def validate_user(user_id):
    url = f"{current_app.config['USER_SERVICE_URL']}/{user_id}"
    try:
        response = requests.get(url, timeout=3)
        return response.status_code == 200
    except requests.RequestException:
        return False

def validate_parking_space(parking_space_id, user_id):
    url = f"{current_app.config['PARKING_SERVICE_URL']}/{parking_space_id}"
    try:
        response = requests.get(url, timeout=3)
        if response.status_code != 200:
            return False
        space = response.json()
        # Adjust validation logic depending on your Parking Service response
        return space.get("status") == "RESERVED" and space.get("user_id") == user_id
    except requests.RequestException:
        return False

def process_payment(data):
    required_fields = ['user_id', 'parking_space_id', 'amount', 'payment_method']
    for field in required_fields:
        if field not in data:
            return {'error': f'Missing field: {field}'}

    user_id = data['user_id']
    parking_space_id = data['parking_space_id']

    if not validate_user(user_id):
        return {'error': 'Invalid or non-existent user'}

    if not validate_parking_space(parking_space_id, user_id):
        return {'error': 'Invalid or unreserved parking space'}

    payment_id = str(uuid.uuid4())
    timestamp = datetime.datetime.utcnow()

    payment = Payment(
        payment_id=payment_id,
        user_id=user_id,
        parking_space_id=parking_space_id,
        amount=data['amount'],
        payment_method=data['payment_method'],
        status='SUCCESS',
        timestamp=timestamp
    )

    db.session.add(payment)
    db.session.commit()

    return payment.to_dict()

def get_payment(payment_id):
    payment = Payment.query.get(payment_id)
    return payment.to_dict() if payment else None

def get_payments_by_user(user_id):
    payments = Payment.query.filter_by(user_id=user_id).all()
    return [p.to_dict() for p in payments]
