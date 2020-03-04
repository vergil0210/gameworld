import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);
const store = new Vuex.Store({
  //全局变量
  state: {
    regLogin: {
      isLogin: true,
    }
  },
  getters: {

  },
  mutations: {
    checkToRegister(state) {
      state.regLogin.isLogin = false;
    },
    checkToLogin(state) {
      state.regLogin.isLogin = true;
    }
  },
  actions: {

  }
});
export default store
