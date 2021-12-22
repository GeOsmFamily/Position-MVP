import api from "./api";
import form from "./form";

class BusinessService {
  getListBusiness() {
    return api.get("etablissements");
  }
  getBusiness(id) {
    return api.get(`etablissements/${id}`);
  }
  createBusiness(data) {
    return form.post("etablissements", data);
  }
  deleteBusiness(id) {
    return api.delete(`etablissements/${id}`);
  }
  editBusiness(id, data) {
    return form.post(`etablissements/${id}`, data);
  }
}

export default new BusinessService();
