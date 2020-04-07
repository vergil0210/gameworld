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
          <el-form-item prop="id">
            <el-input  type="text" oninput="value=value.replace(/[^\d]/g,'')" v-model="loginForm.id" auto-complete="off" placeholder="账号"/>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"/>
          </el-form-item>
          <el-checkbox class="login_remember" v-model="checked" label-position="left">
            记住密码
          </el-checkbox>
          <br/>
          <el-button type="primary" style="width: 65%" @click="submitClick('loginForm')">登陆</el-button>
          <el-button type="info" style="width: 30%" @click="checkToReg()">注册</el-button>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import Cookie from 'js-cookie'
  export default {
    name: "Login",
    data(){
      return {
        rules: {
          id: [
            {required: true, message: '请输入用户名' , trigger: 'blur'},
            {max: 8, message: '账号应该小于8位', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 6,max: 20, message: '密码应该在6-20位之间', trigger: 'blur'},
          ]
        },
        loading: false,
        loginForm: {
          id: '',
          password: '',
        },
        isLoginFail: false,
        checked: true
      }
    },
    methods: {
      submitClick(formName) {
        let _this = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.axios.post('user/adminLogin',_this.loginForm)
            .then(function (response){
              if (response.data.msg){
                _this.$message.error(response.data.msg)
                return false;
              }
              _this.$store.dispatch('set_token',response.data.token)
              _this.$store.dispatch('set_userInfo',response.data.data)
              Cookie.set('token',response.data.token)
              Cookie.set('userInfo',response.data.data)
              // console.log(Cookie.get('userInfo'))
              // console.log(JSON.parse(Cookie.get('userInfo')))

              _this.$router.push({ path: '/home' });
            }).catch(function (error) {
              console.log(error)
              _this.$message.error('出错啦，请联系管理员');
            })

          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      checkToReg() {
        this.$router.push('register')
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
