from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from .routes import payment_bp
from .config import Config

# Create the SQLAlchemy db instance here
db = SQLAlchemy()

def create_app():
    app = Flask(__name__)
    app.config.from_object(Config)

    # Initialize db with app
    db.init_app(app)

    # Import models here to register with SQLAlchemy
    with app.app_context():
        from . import models
        db.create_all()

    # Register blueprint with base path
    app.register_blueprint(payment_bp, url_prefix='/api/payments')

    return app
