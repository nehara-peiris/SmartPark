from flask import Blueprint, request, jsonify
from .services import process_payment, get_payment, get_payments_by_user

payment_bp = Blueprint('payment', __name__)

@payment_bp.route('', methods=['POST'])
def create_payment():
    data = request.get_json()
    result = process_payment(data)
    if 'error' in result:
        return jsonify(result), 400
    return jsonify(result), 201

@payment_bp.route('/<payment_id>', methods=['GET'])
def payment_detail(payment_id):
    result = get_payment(payment_id)
    if not result:
        return jsonify({'error': 'Payment not found'}), 404
    return jsonify(result)

@payment_bp.route('/user/<user_id>', methods=['GET'])
def payments_for_user(user_id):
    results = get_payments_by_user(user_id)
    return jsonify(results)
