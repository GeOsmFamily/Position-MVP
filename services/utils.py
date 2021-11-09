


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


if __name__ == '__main__':
    read_last_image_identification()