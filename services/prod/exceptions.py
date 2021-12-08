
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



#### Managers ####
class ManagersInfoException(Exception):
    ...

class ManagersInfoNotFoundError(ManagersInfoException):
    def __init__(self):
        self.status_code = 404
        self.detail = "Managers Info Not Found"

class ManagersInfoInfoAlreadyExistError(ManagersInfoException):
    def __init__(self):
        self.status_code = 409
        self.detail = "Managers Info Already Exists"


#### Commercials ####
class CommercialsInfoException(Exception):
    ...

class CommercialsInfoNotFoundError(CommercialsInfoException):
    def __init__(self):
        self.status_code = 404
        self.detail = "Commercials Info Not Found"

class CommercialsInfoInfoAlreadyExistError(CommercialsInfoException):
    def __init__(self):
        self.status_code = 409
        self.detail = "Commercials Info Already Exists"


#### Horaires ####
class HorairesInfoException(Exception):
    ...

class HorairesInfoNotFoundError(HorairesInfoException):
    def __init__(self):
        self.status_code = 404
        self.detail = "Horaires Info Not Found"

class HorairesInfoInfoAlreadyExistError(HorairesInfoException):
    def __init__(self):
        self.status_code = 409
        self.detail = "Horaires Info Already Exists"


#### Images ####
class ImagesInfoException(Exception):
    ...

class ImagesInfoNotFoundError(ImagesInfoException):
    def __init__(self):
        self.status_code = 404
        self.detail = "Images Info Not Found"

class ImagesInfoInfoAlreadyExistError(ImagesInfoException):
    def __init__(self):
        self.status_code = 409
        self.detail = "Images Info Already Exists"


#### Telephones ####
class TelephonesInfoException(Exception):
    ...

class TelephonesInfoNotFoundError(TelephonesInfoException):
    def __init__(self):
        self.status_code = 404
        self.detail = "Telephones Info Not Found"

class TelephonesInfoInfoAlreadyExistError(TelephonesInfoException):
    def __init__(self):
        self.status_code = 409
        self.detail = "Telephones Info Already Exists"


#### FTP exceptions  ####
class FTPImagesException(Exception):
    ...

class FTPImagesNotFoundError(FTPImagesException):
    def __init__(self):
        self.status_code = 404
        self.detail = "Image Not Found"

class FTPImagesAlreadyExistError(FTPImagesException):
    def __init__(self):
        self.status_code = 409
        self.detail = "Image Already Exists"



#### Batiments ####
class BatimentsInfoException(Exception):
    ...

class BatimentsInfoNotFoundError(BatimentsInfoException):
    def __init__(self):
        self.status_code = 404
        self.detail = "Batiments Info Not Found"

class BatimentsInfoInfoAlreadyExistError(BatimentsInfoException):
    def __init__(self):
        self.status_code = 409
        self.detail = "Batiments Info Already Exists"


#### FailedJobs ####
class FailedJobsInfoException(Exception):
    ...

class FailedJobsInfoNotFoundError(FailedJobsInfoException):
    def __init__(self):
        self.status_code = 404
        self.detail = "FailedJobs Info Not Found"

class FailedJobsInfoInfoAlreadyExistError(FailedJobsInfoException):
    def __init__(self):
        self.status_code = 409
        self.detail = "FailedJobs Info Already Exists"


#### Users ####
class UsersInfoException(Exception):
    ...

class UsersInfoNotFoundError(UsersInfoException):
    def __init__(self):
        self.status_code = 404
        self.detail = "Users Info Not Found"

class UsersInfoInfoAlreadyExistError(UsersInfoException):
    def __init__(self):
        self.status_code = 409
        self.detail = "Users Info Already Exists"


#### Trackings ####
class TrackingsInfoException(Exception):
    ...

class TrackingsInfoNotFoundError(TrackingsInfoException):
    def __init__(self):
        self.status_code = 404
        self.detail = "Trackings Info Not Found"

class TrackingsInfoInfoAlreadyExistError(TrackingsInfoException):
    def __init__(self):
        self.status_code = 409
        self.detail = "Trackings Info Already Exists"


#### Zones ####
class ZonesInfoException(Exception):
    ...

class ZonesInfoNotFoundError(ZonesInfoException):
    def __init__(self):
        self.status_code = 404
        self.detail = "Zones Info Not Found"

class ZonesInfoInfoAlreadyExistError(ZonesInfoException):
    def __init__(self):
        self.status_code = 409
        self.detail = "Zones Info Already Exists"


#### SousCategorieEtablissements ####
class SousCategorieEtablissementsInfoException(Exception):
    ...

class SousCategorieEtablissementsInfoNotFoundError(SousCategorieEtablissementsInfoException):
    def __init__(self):
        self.status_code = 404
        self.detail = "SousCategorieEtablissements Info Not Found"

class SousCategorieEtablissementsInfoInfoAlreadyExistError(SousCategorieEtablissementsInfoException):
    def __init__(self):
        self.status_code = 409
        self.detail = "SousCategorieEtablissements Info Already Exists"




