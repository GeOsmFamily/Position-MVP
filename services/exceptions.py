
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
