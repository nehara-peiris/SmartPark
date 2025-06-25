from flask import Blueprint, request, jsonify
from models import db, Payment
from config import GATEWAY_URL
import requests

routes = Blueprint('routes', __name__)

@routes.route("/api/payments", methods=["POST"])
def create_payment():
    data = request.get_json()
    user_id = request.headers.get("x-user-id")
    token = request.headers.get("Authorization")

    reservation_id = data.get("reservation_id")

    try:
        res = requests.get(
            f"{GATEWAY_URL}/api/reservations/{reservation_id}",
            headers={"Authorization": token}
        )
        if res.status_code != 200:
            return jsonify({"error": "Invalid reservation"}), 400

        reservation_data = res.json()
        if reservation_data.get("userId") != user_id:
            return jsonify({"error": "Reservation does not belong to user"}), 403

    except Exception as e:
        return jsonify({"error": "Could not contact reservation service", "details": str(e)}), 500

    payment = Payment(
        user_id=user_id,
        reservation_id=reservation_id,
        amount=data.get("amount"),
        status=data.get("status", "PAID")
    )

    db.session.add(payment)
    db.session.commit()
    return jsonify({"message": "Payment created", "payment_id": payment.id}), 201

@routes.route("/api/payments", methods=["GET"])
def get_all():
    payments = Payment.query.all()
    return jsonify([p.__dict__ for p in payments if '_sa_instance_state' not in p.__dict__])

@routes.route("/api/payments/user", methods=["GET"])
def get_by_user():
    user_id = request.headers.get("x-user-id")
    payments = Payment.query.filter_by(user_id=user_id).all()
    return jsonify([p.__dict__ for p in payments if '_sa_instance_state' not in p.__dict__])

@routes.route("/api/payments/<string:payment_id>", methods=["GET"])
def get_by_id(payment_id):
    token = request.headers.get("Authorization")
    payment = Payment.query.get(payment_id)
    if not payment:
        return jsonify({"error": "Payment not found"}), 404

    try:
        res = requests.get(
            f"{GATEWAY_URL}/api/reservations/{payment.reservation_id}",
            headers={"Authorization": token}
        )
        reservation = res.json() if res.status_code == 200 else None
    except Exception as e:
        reservation = {"error": "Reservation fetch failed", "details": str(e)}

    return jsonify({
        "id": payment.id,
        "user_id": payment.user_id,
        "reservation_id": payment.reservation_id,
        "amount": payment.amount,
        "status": payment.status,
        "timestamp": payment.timestamp,
        "reservation": reservation
    })
