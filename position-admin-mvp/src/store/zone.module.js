import ZoneService from "../service/zone.service";

const initialState = {
  loading: null,
  zones: null,
  currentzone: null,
};

export const zone = {
  namespaced: true,
  state: initialState,
  actions: {
    fetchZones({ commit }) {
      commit("toggleLoading", true);
      return ZoneService.getListZones().then(
        (zones) => {
          commit("toggleLoading", false);
          commit("zonesSuccess", zones.data.data);
          return Promise.resolve(zones);
        },
        (error) => {
          commit("toggleLoading", false);
          commit("zonesFailure");
          return Promise.reject(error);
        }
      );
    },
    createZone({ dispatch, commit }, data) {
      commit("toggleLoading", true);
      return ZoneService.createZone(data).then(
        (result) => {
          console.log(data);
          commit("toggleLoading", false);
          dispatch("fetchZones");
          return Promise.resolve(result);
        },
        (error) => {
          commit("toggleLoading", false);
          return Promise.reject(error);
        }
      );
    },
    deleteZone({ dispatch }, id) {
      return ZoneService.deleteZone(id).then(
        (result) => {
          dispatch("fetchZones");
          return Promise.resolve(result);
        },
        (error) => {
          return Promise.reject(error);
        }
      );
    },
    editZone({ dispatch }, data) {
      return ZoneService.editZone(data.id, data.zone).then(
        (result) => {
          dispatch("fetchZones");
          return Promise.resolve(result);
        },
        (error) => {
          return Promise.reject(error);
        }
      );
    },
  },
  mutations: {
    zonesSuccess(state, zones) {
      state.zones = zones;
      return zones;
    },
    zonesFailure(state) {
      state.zones = null;
    },
    toggleLoading(state, value) {
      state.loading = value;
    },
  },
  getters: {
    loading: ({ loading }) => {
      return loading;
    },
    zones: ({ zones }) => {
      return zones;
    },
    zonesOPtions: ({ zones }) => {
      let data = [{ value: null, text: "Selectionner une zone" }];
      if (zones != null) {
        zones.forEach((zone) => {
          data.push({
            value: zone.id,
            text: zone.nom + " " + zone.ville,
          });
        });
      }
      return data;
    },
  },
};
