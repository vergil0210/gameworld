<template>
  <div id="login">
    <div class="title">
      <h1>GameWorld后台管理系统</h1>
    </div>
    <div class="template">
      <div class="title">
        <h3>管理员登录</h3>
      </div>
      <div class="login">
        <el-alert v-if="isLoginFail" type="error" title="登录失败，账号密码不符请重新确认" closable show-icon></el-alert>
        <el-form :rules="rules" class="login-container" label-position="left" :model="loginForm"
                 label-width="0px" ref="loginForm">
          <el-form-item prop="username">
            <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="用户名或邮箱"/>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"/>
          </el-form-item>
          <el-checkbox class="login_remember" v-model="checked" label-position="left">
            记住密码
          </el-checkbox>
          <el-form-item style="width: 100%">
            <el-button type="primary" style="width: 100%" @click="submitClick()">sign in</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Login",
    data(){
      return {
        rules: {
          username: [{required: true, message: '请输入用户名' , trigger: 'blur'}],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 6,max: 20, message: '密码应该在6-20位之间', trigger: 'blur'},
          ]
        },
        loading: false,
        loginForm: {
          username: '',
          password: '',
        },
        isLoginFail: false,
        checked: true
      }
    },
    methods: {
      submitClick() {
        this.$router.push({path: '/home'});
      }
    }
  }
</script>

<style scoped>
  #login {
    background: url('../assets/background.jpg');
    background-size: 100% 100%;
    height: 100%;
    position: fixed;
    width: 100%;
    right: 0;
    top: 0;
    /*overflow-y: scroll;*/
    overflow-x: hidden;
  }
  .template {
    margin: 80px 60%;
    width: 420px;
  }
  .title {
    font-family: -webkit-pictograph;
    color: antiquewhite;
    text-align: center
  }
  .login {
    border-radius: 15px;
    background-clip: padding-box;
    width: 350px;
    padding: 45px 35px 15px 35px;
    background: #042240ad;
    /* border: 1px solid #FAFBFC; */
    box-shadow: 0 0 25px #000000;
  }
  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
</style>
