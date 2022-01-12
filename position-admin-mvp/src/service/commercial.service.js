import api from "./api";
import form from "./form";
import stat from "./stat";

class CommercialService {
  getCommerciaux() {
    return api.get("commercial");
  }
  saveCommercial(data) {
    return form.post("commercial", data);
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
  createQrCode(id) {
    return stat.post(`commercial/generateQrCode?commercial_id=${id}`);
  }
  getDailyStat(id, date) {
    console.log(date);
    const url = `commercials/statistics/get/ets/by_week/?commercials_id=${id}&aDayOfTheWeek=${date}`;
    return stat.get(url);
  }
}

export default new CommercialService();
