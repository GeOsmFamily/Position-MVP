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
  getDailyStat(id) {
    const today = new Date();
    const date =
      today.getFullYear() +
      "-" +
      (today.getMonth() + 1) +
      "-" +
      today.getDate();
    const time =
      today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    const dateTime = date + " " + time;
    const url = `commercials/statistics/get/ets/by_week/?commercials_id=${id}&aDayOfTheWeek=${dateTime}`;
    return stat.get(url);
  }
}

export default new CommercialService();
