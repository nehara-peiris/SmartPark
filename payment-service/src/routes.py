from flask import Blueprint, request, jsonify
from models import db, Payment
import uuid
import requests
import os

routes = Blueprint("routes", __name__)
GATEWAY_URL = os.getenv("GATEWAY_URL", "http://localhost:8080")

@routes.route("/api/payments", methods=["POST"])
def create_payment():
    data = request.json
    token = request.headers.get("Authorization")
    reservation_id = data.get("reservation_id")
    amount = data.get("amount")

    res = requests.get(
        f"{GATEWAY_URL}/api/reservations/{reservation_id}",
        headers={"Authorization": token}
    )
    if res.status_code != 200:
        return jsonify({"error": "Invalid reservation"}), 400

    payment_id = str(uuid.uuid4())
    payment = Payment(id=payment_id, reservation_id=reservation_id, amount=amount)
    db.session.add(payment)
    db.session.commit()

    return jsonify({"id": payment_id, "reservation_id": reservation_id, "amount": amount}), 201

@routes.route("/api/payments", methods=["GET"])
def get_all_payments():
    payments = Payment.query.all()
    return jsonify([{
        "id": p.id,
        "reservation_id": p.reservation_id,
        "amount": p.amount
    } for p in payments])

@routes.route("/api/payments/user", methods=["GET"])
def get_by_user():
    token = request.headers.get("Authorization")
    user_id = request.headers.get("x-user-id")

    all_payments = Payment.query.all()
    filtered = []

    for p in all_payments:
        res = requests.get(
            f"{GATEWAY_URL}/api/reservations/{p.reservation_id}",
            headers={"Authorization": token}
        )
        if res.status_code == 200 and res.json().get("userId") == user_id:
            filtered.append({
                "id": p.id,
                "reservation_id": p.reservation_id,
                "amount": p.amount
            })

    return jsonify(filtered)

@routes.route("/api/payments/<string:id>", methods=["GET"])
def get_by_id(id):
    token = request.headers.get("Authorization")
    payment = Payment.query.get(id)
    if not payment:
        return jsonify({"error": "Not found"}), 404

    res = requests.get(
        f"{GATEWAY_URL}/api/reservations/{payment.reservation_id}",
        headers={"Authorization": token}
    )
    reservation = res.json() if res.status_code == 200 else None

    return jsonify({
        "id": payment.id,
        "reservation_id": payment.reservation_id,
        "amount": payment.amount,
        "reservation": reservation
    })
