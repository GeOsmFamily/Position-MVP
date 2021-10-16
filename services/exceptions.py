
#### Etablissements ####
class EtablissementInfoException(Exception):
    ...


class EtablissementInfoNotFoundError(EtablissementInfoException):
    def __init__(self):
        self.status_code = 404
        self.detail = "Etablissement Info Not Found"


class EtablissementInfoInfoAlreadyExistError(EtablissementInfoException):
    def __init__(self):
        self.status_code = 409
        self.detail = "Etablissement Info Already Exists"




#### Sous Categories ####
class SousCategoriesInfoException(Exception):
    ...


class SousCategoriesInfoNotFoundError(SousCategoriesInfoException):
    def __init__(self):
        self.status_code = 404
        self.detail = "Sub Category Info Not Found"


class SousCategoriesInfoInfoAlreadyExistError(SousCategoriesInfoException):
    def __init__(self):
        self.status_code = 409
        self.detail = "Sub Category Info Already Exists"



#### Categories ####
class CategoriesInfoException(Exception):
    ...


class CategoriesInfoNotFoundError(CategoriesInfoException):
    def __init__(self):
        self.status_code = 404
        self.detail = "Category Info Not Found"


class CategoriesInfoInfoAlreadyExistError(CategoriesInfoException):
    def __init__(self):
        self.status_code = 409
        self.detail = "Category Info Already Exists"
