from flask import request, jsonify

def require_auth():
    token = request.headers.get("Authorization")
    if not token or not token.startswith("Bearer "):
        return jsonify({"error": "Unauthorized"}), 401
    # No deep validation; API Gateway handles it
    return None
