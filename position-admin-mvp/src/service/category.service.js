import api from "./api";
import form from "./form";

class CategoryService {
  getListCategories() {
    return api.get("categories?limit=50");
  }
  createCategory(data) {
    return form.post("categories", data);
  }
  deleteCategory(id) {
    return api.delete(`categories/${id}`);
  }
  editCategory(id, data) {
    return form.post(`categories/${id}`, data);
  }
}

export default new CategoryService();
