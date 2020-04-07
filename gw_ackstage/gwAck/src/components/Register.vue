<template>
  <div id="login">
    <div class="title">
      <h1>GameWorld后台管理系统</h1>
    </div>
    <div class="template">
      <div class="title">
        <h3>管理员注册</h3>
      </div>
      <div class="login">
        <el-form :rules="rules" class="login-container" label-position="left" :model="regForm"
                 label-width="0px" ref="regForm">
          <el-form-item prop="username">
            <el-input  type="text" v-model="regForm.username" auto-complete="off" placeholder="用户名"/>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="regForm.password" auto-complete="off" placeholder="密码"/>
          </el-form-item>
          <el-form-item prop="checkPassword">
            <el-input type="password" v-model="regForm.checkPassword" auto-complete="off" placeholder="确认密码"/>
          </el-form-item>
          <el-button type="primary" style="width: 65%" @click="submitClick('regForm')">注册</el-button>
          <el-button type="info" style="width: 30%" @click="checkToLog()">登陆</el-button>
        </el-form>
      </div>
    </div>
    <el-dialog :visible.sync="dialogVisible" title="注册成功"  :modal-append-to-body='false' width="30%">
      <span>请记住您的账号： <span style="color: red">{{ id }}</span></span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogClick()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "Register.vue",
    data(){
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.regForm.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        rules: {
          username: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 6, max: 20, message: '密码应该在6-20位之间', trigger: 'blur'},
          ],
          checkPassword: [{ validator: validatePass, trigger: 'blur' }]
        },
        regForm: {
          username: '',
          password: '',
          checkPassword: ''
        },
        id: 0,
        dialogVisible: false
      }
    },
    methods: {
      checkToLog() {
        this.$router.push('/')
      },
      submitClick(formName) {
        const _this = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.axios.post('user/adminRegister',_this.regForm)
              .then(function (response){
                console.log(response)
                if (response.data.msg){
                  _this.$message.error(response.data.msg)
                  return false
                }
                _this.id = response.data.data
                _this.dialogVisible = true
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
      dialogClick(){
        this.dialogVisible = false
        this.$router.push('/')
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
