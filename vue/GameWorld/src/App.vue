<template>
  <div id="app">
    <div class="top">
      <div class="rightTop">
          <span class="fontCss">欢迎来到游戏世界</span>
          <router-link to="/regLogin" @click.native="checkToLogin">请登录</router-link>
          <span class="fontCss">|</span>
          <router-link to="/regLogin/register" @click.native="checkToRegister">免费注册</router-link>
      </div>
      <div class="leftTop">
          <el-image :src="logoPath" id="logo" style="width: 30px;height: 30px;" @click="skipHomePage"></el-image>
          <h1 style="display: inline">GameWorld</h1>&nbsp;&nbsp;
          <router-link to="/homePage">商店</router-link>&nbsp;&nbsp;
          <router-link to="/index">主页</router-link>&nbsp;&nbsp;
          <router-link to="">我的游戏</router-link>&nbsp;&nbsp;
          <router-link to="">关于</router-link>&nbsp;&nbsp;
          <router-link to="">客服</router-link>
      </div>
    </div>
    <router-view/>
  </div>
</template>

<script>
  import { mapState } from 'vuex';
  export default {
    name: 'App',
    data() {
      return {
        logoPath: require("./assets/img/Logo1.png"),
      }
    },
    methods: {
      skipHomePage(){
        this.$router.push("/homePage");
      },
      checkToLogin(){
        console.log(this.isLogin);
        this.$store.commit('checkToLogin');
      },
      checkToRegister(){
        console.log(this.isLogin);
        this.$store.commit('checkToRegister');
      },
    },
    computed: {
      ...mapState(['regLogin']),
      isLogin: function () {
        return this.regLogin.isLogin;
      }
    }
  }
</script>

<style>
h1{
  font-family: -webkit-pictograph;
  color: antiquewhite;
}
#app {
  background: url('./assets/img/background.jpg');
  background-size: 100% 100%;
  height: 100%;
  position: fixed;
  width: 100%;
  right: 0;
  top: 0;
  overflow-y: scroll;
  overflow-x: hidden;
}
.leftTop {
    position: relative;
    left: 20%;
    top: 10%;
}
.rightTop {
    position: relative;
    left: 79%;
    top: 10%;
}
.top {
    width: 100%;
    height: 100px;
    background-color: #373636c4;
    background-clip: padding-box;
    padding: 5px 5px 5px 5px;
    box-shadow: 0 0 25px #000000;
}
a {
    font-weight: normal;
    color: antiquewhite;
    text-decoration: none;
}
a:hover {
    color: #00B39E;
}
</style>
