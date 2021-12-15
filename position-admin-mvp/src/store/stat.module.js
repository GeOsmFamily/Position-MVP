import CommercialService from "../service/commercial.service";
import Vue from "vue";

const initialState = {
  loading: null,
  weekStat: {
    cash: 0,
    count: 0,
    etablissements: [],
  },
};

export const stat = {
  namespaced: true,
  state: initialState,
  actions: {
    fetchStatByWeek({ commit }, id) {
      commit("toggleLoading", true);
      return CommercialService.getDailyStat(id).then(
        (data) => {
          commit("toggleLoading", false);
          commit("weekStatSuccess", data.data);
          return Promise.resolve(data.data);
        },
        (error) => {
          commit("toggleLoading", false);
          commit("weekStatFailure");
          return Promise.reject(error);
        }
      );
    },
  },
  mutations: {
    weekStatSuccess(state, data) {
      state.weekStat = data;
    },
    weekStatFailure(state) {
      state.weekStat = {
        cash: 0,
        count: 0,
        etablissements: [],
      };
    },
    toggleLoading(state, value) {
      state.loading = value;
    },
  },
  getters: {
    loading: ({ loading }) => {
      return loading;
    },
    weekCount: ({ weekStat }) => {
      return weekStat.count;
    },
    cashWeek: ({ weekStat }) => {
      return weekStat.cash;
    },
    dayEtablissement: ({ weekStat }) => {
      let data = [];
      if (weekStat.etablissements.length > 0) {
        weekStat.etablissements.forEach((dayStat) => {
          if (
            new Date(dayStat.created_at).getFullYear() ===
              new Date().getFullYear() &&
            new Date(dayStat.created_at).getMonth() === new Date().getMonth() &&
            new Date(dayStat.created_at).getDate() === new Date().getDate()
          ) {
            const today = new Date(dayStat.created_at);
            const date =
              today.getFullYear() +
              "-" +
              (today.getMonth() + 1) +
              "-" +
              today.getDate();
            const time =
              today.getHours() +
              ":" +
              today.getMinutes() +
              ":" +
              today.getSeconds();
            const dateTime = date + " " + time;
            dayStat.created_at = dateTime;
            data.push(dayStat);
          }
        });
      }
      console.log(data);
      return data;
    },
    weekStat: ({ weekStat }) => {
      let data = [];
      let weekDates = [];
      for (let i = 1; i <= 7; i++) {
        weekDates.push(Vue.moment().day(i));
      }
      weekDates.forEach((currentDay) => {
        let j = 0;
        if (weekStat.etablissements.length > 0) {
          weekStat.etablissements.forEach((dayStat) => {
            if (
              new Date(dayStat.created_at).getFullYear() ===
                currentDay.year() &&
              new Date(dayStat.created_at).getMonth() === currentDay.month() &&
              new Date(dayStat.created_at).getDate() === currentDay.date()
            )
              j++;
          });
        }
        data.push(j);
      });
      console.log(data);
      return data;
    },
  },
};
