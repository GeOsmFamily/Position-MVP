import api from "./api";
import form from "./form";

class ZoneService {
  getListZones() {
    return api.get("categories?limit=50");
  }
  createZone(data) {
    return form.post("categories", data);
  }
  deleteZone(id) {
    return api.delete(`categories/${id}`);
  }
  editZone(id, data) {
    return form.post(`categories/${id}`, data);
  }
}

export default new ZoneService();
