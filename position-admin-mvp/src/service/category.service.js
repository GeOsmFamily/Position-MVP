import api from "./api";

class CategoryService {
  getListCategories() {
    return api.get("categories?limit=50");
  }
  createCategory(data) {
    return api.post("categories", data);
  }
  deleteCategory(id) {
    return api.delete("categories/", {
      categorie_id: id,
    });
  }
}

export default new CategoryService();
