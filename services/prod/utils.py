# ! pip3 install qrcode

import os
from lib2to3.pytree import convert

import qrcode
from PIL import Image

import pandas as pd

BASE_URL_QRCODE = os.getenv('BASE_URL_QRCODE')
BASE_URL_QRCODE="https://api.position.cm/commercial/identity?commercials_id="


def read_last_image_identification(table: str):
    filename = "images_" + table + ".txt" 
    file = open(filename,"r")
    _id = file.read()
    file.close()
    return int(_id) + 1

def save_last_image_identification(table: str):
    _lastId = read_last_image_identification()
    filename = "images_" + table + ".txt" 
    file = open(filename,"w")
    _id = int(_lastId) 
    file.write(str(_id))
    file.close()

def create_qr(commercial_id):
    logo = Image.open('images/static/position.png')
    basewidth = 75
    wpercent = (basewidth/float(logo.size[0]))
    hsize = int((float(logo.size[1])*float(wpercent)))
    logo = logo.resize((basewidth,hsize), Image.ANTIALIAS)
    qr_big = qrcode.QRCode(error_correction=qrcode.constants.ERROR_CORRECT_H, box_size=10, border=4)
    qr_big.add_data(BASE_URL_QRCODE + str(commercial_id))
    qr_big.make(fit=True)
    img_qr_big = qr_big.make_image().convert('RGBA')
    pos = ((img_qr_big.size[0] - logo.size[0]) // 2, (img_qr_big.size[1] - logo.size[1]) // 2)
    img_qr_big.paste(logo, pos)
    name = "images/qrcodes/qr_" + str(commercial_id) + ".png"
    img_qr_big.save(name)

def automate_from_csv():
    data = pd.read_csv('commerciaux.csv')
    for index, row in data.iterrows():
        create_qr(row['id'])
    
if __name__ == '__main__':
    # read_last_image_identification()
    # automate_from_csv()
    create_qr(69)