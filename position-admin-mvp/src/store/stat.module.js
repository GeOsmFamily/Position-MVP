import CommercialService from "../service/commercial.service";
import Vue from "vue";

const initialState = {
  loading: null,
  weekDays: [],
  currentDay: new Date(),
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
    fetchStatByWeek({ commit }, data) {
      commit("toggleLoading", true);
      return CommercialService.getDailyStat(data.id, data.date).then(
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
    changeWeekDays({ commit }, date) {
      commit("changeWeekDays", date);
    },
    changeCurrentDay({ commit }, date) {
      commit("changeCurrentDay", date);
    },
  },
  mutations: {
    weekStatSuccess(state, data) {
      state.weekStat = data;
    },
    changeWeekDays(state, data) {
      state.weekDays = data;
    },
    changeCurrentDay(state, data) {
      state.currentDay = data;
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
    dayEtablissement: ({ weekStat, currentDay }) => {
      let data = [];
      if (weekStat.etablissements.length > 0) {
        weekStat.etablissements.forEach((dayStat) => {
          if (
            new Date(dayStat.created_at).getFullYear() ===
              currentDay.getFullYear() &&
            new Date(dayStat.created_at).getMonth() === currentDay.getMonth() &&
            new Date(dayStat.created_at).getDate() === currentDay.getDate()
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
            dayStat.created_at = date + " " + time;
            data.push(dayStat);
          }
        });
      }
      return data;
    },
    weekDays: ({ weekDays }) => {
      return weekDays;
    },
    weekStat: ({ weekStat, weekDays }) => {
      let data = [];
      let weekDates = [];
      if (weekDays.length === 0) {
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
                new Date(dayStat.created_at).getMonth() ===
                  currentDay.month() &&
                new Date(dayStat.created_at).getDate() === currentDay.date()
              )
                j++;
            });
          }
          data.push(j);
        });
        return data;
      } else {
        weekDays.forEach((currentDay) => {
          let j = 0;
          if (weekStat.etablissements.length > 0) {
            weekStat.etablissements.forEach((dayStat) => {
              if (
                new Date(dayStat.created_at).getFullYear() ===
                  currentDay.getFullYear() &&
                new Date(dayStat.created_at).getMonth() ===
                  currentDay.getMonth() &&
                new Date(dayStat.created_at).getDate() === currentDay.getDate()
              )
                j++;
            });
          }
          data.push(j);
        });
        return data;
      }
    },
  },
};
