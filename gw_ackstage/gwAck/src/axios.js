import axios from 'axios'
import router from './router/index'
import Cookie from 'js-cookie'

//axios配置
axios.defaults.timeout = 8000
axios.defaults.baseURL = 'http://localhost:8080'

// http request 拦截器
axios.interceptors.request.use(
  config => {
    if (Cookie.get('token')) { //判断token是否存在
      config.headers['token'] = Cookie.get('token')  //将token设置成请求头
    }
    return config;
  },
  err => {
    return Promise.reject(err);
  }
);

export default axios

