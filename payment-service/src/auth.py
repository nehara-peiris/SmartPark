from flask import request, jsonify

def require_auth():
    token = request.headers.get("Authorization")
    user_id = request.headers.get("x-user-id")

    if not token or not token.startswith("Bearer ") or not user_id:
        return jsonify({"error": "Unauthorized"}), 401
