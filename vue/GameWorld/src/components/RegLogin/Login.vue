<template>
    <div>
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
                <el-button type="primary" style="width: 100%" @click="submitClick('loginForm')">sign in</el-button>
            </el-form-item> 
        </el-form>
    </div>
</template>

<script>
export default{
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
        submitClick(formName){
            this.isLoginFail = false;
            let _this = this;
            this.$refs[formName].validate((valid)=>{
                if(valid){
                    // this.$axios.post('http://localhost:8080/regLogin/login',{
                    //     username: this.loginForm.username,
                    //     password: this.$md5(this.loginForm.password)
                    // })
                    //     .then(function(response){
                    //         // debugger;
                    //         if(response && response.data){
                    //             _this.$store.commit('SET_TOKEN', 'NMSL')
                    //             debugger;                                
                    //             _this.$store.commit('SET_USERNAME', response.data.username)
                    //             _this.$store.commit('SET_USERID', response.data.userId)
                    //             _this.$router.push('/Menu')
                    //         }else{
                    //             _this.isLoginFail = true;
                    //         }
                    //     })


                    //用户名已存在

                    //

                }else{
                    console.log('error submit')
                    return false;
                }
            })
        }
    },
}
</script>

<style scoped>
.login_remember {
margin: 0px 0px 35px 0px;
text-align: left;
}
</style>
