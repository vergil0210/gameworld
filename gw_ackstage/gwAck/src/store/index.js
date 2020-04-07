import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: '',
    user:{username:'', id: ''}
  },
  mutations: {
    set_token(state,token) {
      state.token = token
      console.log('token已保存： ' + state.token)
    },
    del_token(state) {
      state.token = ''
      console.log('vuex已删除token')
    },
    set_userInfo(state,user) {
      state.user = user
      console.log('用户名已保存： ' + state.user)
    },
    del_userInfo(state) {
      state.user = {}
      console.log('vuex已删除用户信息')
    }
  },
  actions: {
    set_token(content,token){
      content.commit('set_token', token)
    },
    set_userInfo(content,user) {
      content.commit('set_userInfo', user)
    }
  }
})
