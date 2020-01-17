<template>
  <div>
    <div class="regLoginCss" :style="isLogin ? null : floatUp">
        <h3 class="login_title" v-if="isLogin">用户登录</h3>
        <h3 class="login_title" v-else>用户注册</h3>
        <el-button-group class="regLogin_button_group">
                <el-button class="regLogin_button" @click="checkToLogin">登录</el-button>
                <el-button class="regLogin_button" @click="checkToRegister">注册</el-button>
        </el-button-group>
        <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      isLogin: true,
      //style绑定，向上浮动
      floatUp: 'margin: 100px auto'
    }
  },
  methods: {
    checkToLogin(){
      this.isLogin = true;
      this.$router.push('/regLogin');
    },
    checkToRegister(){
      this.isLogin = false;
      this.$router.push('/regLogin/register');
    },
  },
}
</script>

<style>
.regLoginCss{
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #042240ad;
    /* border: 1px solid #FAFBFC; */
    box-shadow: 0 0 25px #000000;
    
}
.regLogin_button{
  width: 50%;
}
.regLogin_button_group{
  width: 100%;
  position:relative;
  top: -20px;
}
.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: white;
}
.backgroundCss{
  background-image: '../assets/regLogin-background.jpg';
  background: no-repeat center top;
  background-size: contain;
}
</style>