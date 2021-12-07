import api from "./api";
import form from "./form";

class ZoneService {
  getListZones() {
    return api.get("zones");
  }
  createZone(data) {
    return form.post("zones", data);
  }
  deleteZone(id) {
    return api.delete(`zones/${id}`);
  }
  editZone(id, data) {
    return form.post(`zones/${id}`, data);
  }
}

export default new ZoneService();
