from flask import Flask, request
from models import db
from config import SQLALCHEMY_DATABASE_URI
from routes import routes
from flask_cors import CORS
from auth import require_auth

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

@app.before_first_request
def create_tables():
    db.create_all()

if __name__ == "__main__":
    app.run(port=5005, debug=True)
