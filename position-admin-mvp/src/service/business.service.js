import api from "./api";
import form from "./form";
import stat from "./stat";

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
  getMonthBusinness(month, year) {
    return stat.get(
      `position/statistics/get/ets/by_month/?month=${month}&year=${year}`
    );
  }
  editBusiness(id, data) {
    return form.post(`etablissements/${id}`, data);
  }
}

export default new BusinessService();
