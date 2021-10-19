from datetime import datetime
import os

import jwt
from jwt.exceptions import InvalidSignatureError

from pathlib import Path
from dotenv import load_dotenv

# Build paths inside the project like this: BASE_DIR / 'subdir'.
BASE_DIR = Path(__file__).resolve().parent
#load env file
load_dotenv(os.path.join(BASE_DIR,'.env'))

# to get a string like this run:
# openssl rand -hex 32
secret = os.getenv('SECRET_KEY')
algorithm = os.getenv("ALGORITHM")
access_token_expire_time = os.getenv('ACCESS_TOKEN_EXPIRE_MINUTES')


def token_required(f):
    def wrapper(*args, **kwargs):
        token = None

        if 'x-access-token' in args[0].headers:
            token = args[0].headers['x-access-token']

        if not token:
            return {'message': 'Token is missing!'}, 401

        try:
            data = jwt.decode(token, secret, algorithms=['HS256'], verify=False)
            current_user = data['user_id']
            if data['exp'] < int(datetime.utcnow()) & data['roles_id']:
                roles = data['roles_id']
        except InvalidSignatureError:
            return {'message': 'Token is invalid!'}, 401

        # return f(current_user, *args, **kwargs)
        return current_user, roles

    return wrapper


def verify_token(encoded_token):
    try:
        payload = jwt.decode(encoded_token, key=secret, algorithms=[algorithm],verify=True)
        return payload
    except jwt.ExpiredSignatureError:
        # Signature has expired
        return {'message': 'ExpiredSignatureError - Token is invalid!'}
    except jwt.exceptions.DecodeError:
        return {'message': 'DecodeError - Token is invalid!'}
    except InvalidSignatureError:
        return {'message': 'InvalidSignatureError - Token is invalid!'}

# COM = commerciaux, MAN = manager, ADM = Admin, ETS = Etablissements
def has_authority(roles:list(), access_type='r', target=''):
    # role: 1, 2
    # access_type : r, w
    # target :'COMM', 'MAN', 'ADM', 'ETS'
    access = {
        '1' : [('COMM', 'rw'), ('MAN', 'rw'), ('ADM', 'rw'), ('ETS', 'rw')],
        '2' : [('ETS', 'rw'),]
    }

    given_access = list()

    for index in roles:
        for item in access[str(index)]:
            given_access.append(item)

    for item in given_access:
        if str(target) is item[0] and str(access_type) in item[1]:
                return True

    return False

# print(has_authority([1], access_type='rw', target='ADM'))

