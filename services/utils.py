


def read_last_image_identification():
    file = open("images.txt","r")
    _id = file.read()
    file.close()
    return int(_id) + 1

def save_last_image_identification():
    _lastId = read_last_image_identification()
    file = open("images.txt","w")
    _id = int(_lastId) 
    file.write(str(_id))
    file.close()


if __name__ == '__main__':
    read_last_image_identification()