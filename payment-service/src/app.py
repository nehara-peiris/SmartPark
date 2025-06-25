from flask import Flask, request
from models import db
from config import SQLALCHEMY_DATABASE_URI
from routes import routes
from flask_cors import CORS
from auth import require_auth
import threading

# Eureka client import
from py_eureka_client import eureka_client

app = Flask(__name__)
CORS(app)

app.config["SQLALCHEMY_DATABASE_URI"] = SQLALCHEMY_DATABASE_URI
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False

db.init_app(app)
app.register_blueprint(routes)

@app.before_request
def check_auth():
    if request.path.startswith("/api/payments"):
        auth_result = require_auth()
        if auth_result:
            return auth_result

with app.app_context():
    db.create_all()

# Register with Eureka (in a background thread to avoid blocking Flask)
def register_with_eureka():
    eureka_client.init(
        eureka_server="http://localhost:8761/eureka/",
        app_name="payment-service",
        instance_port=5005,
        ip_addr="127.0.0.1",
        host_name="localhost"
    )

# Run Flask app
if __name__ == "__main__":
    threading.Thread(target=register_with_eureka).start()
    app.run(port=5005, debug=True)
