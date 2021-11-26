import api from "./api";

class CommercialService {
  getCommerciaux() {
    return api.get("commercial");
  }
  saveCommercial(data) {
    return api.post("commercial", data);
  }
  getCommercial(id) {
    return api.get(`commercial/${id}`);
  }
  updateCommercial(id, data) {
    return api.put(`commercial/${id}`, data);
  }
  deleteCommercial(id) {
    return api.delete(`commercial/${id}`);
  }
}

export default new CommercialService();
