import os

class Config:
    # MySQL connection URI format:
    # mysql+pymysql://username:password@host:port/database_name
    SQLALCHEMY_DATABASE_URI = os.getenv(
        'DATABASE_URL',
        'mysql+pymysql://root:0909@localhost:3306/paymentdb'
    )
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    USER_SERVICE_URL = os.getenv('USER_SERVICE_URL', 'http://localhost:8082/users')
    PARKING_SERVICE_URL = os.getenv('PARKING_SERVICE_URL', 'http://localhost:8081/parking-spaces')
    DEBUG = True
