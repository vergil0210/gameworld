<template>
    <div>
        <el-alert v-if="isRegFail" type="error" :title="alertTitle" show-icon closable ></el-alert>
        <el-form :rules="rules" status-icon class="register-container" label-position="left" :model="registerForm"
           label-width="0px" ref="registerForm">
            <el-form-item prop="username">
                <el-input type="text" v-model="registerForm.username" auto-complete="off" placeholder="用户名"/>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="text" v-model="registerForm.password" auto-complete="off" placeholder="密码"/>
            </el-form-item>
            <el-form-item prop="checkPassword">
                <el-input type="password" v-model="registerForm.checkPassword" auto-complete="off" placeholder="确认密码"/>
            </el-form-item>
            <el-form-item prop="email">
                <el-input type="text" v-model="registerForm.email" auto-complete="off" placeholder="邮箱"/>
            </el-form-item>
            <el-form-item prop="telphone">
                <el-input type="text" v-model="registerForm.telphone" auto-complete="off" placeholder="电话号码"/>
            </el-form-item>
            <el-form-item style="width: 100%">
                <el-button type="primary" style="width: 100%" @click="submitClick('registerForm')">sign up</el-button>
            </el-form-item> 
        </el-form>
    </div>
</template>

<script>


export default{
    data(){
        //自定义表单验证
        var validateCheckPass = (rule,value,callback) =>{
            if(value !== this.registerForm.password){
                callback(new Error('两次输入密码不一致！'));
            }else{
                callback();
            }
        };
        return {
            rules: {
                username: [{required: true, message: '请输入用户名'}],
                password: [
                    {required: true, message: '请输入密码', trigger: 'blur'},
                    {min: 6,max: 20, message: '密码应该在6-20位之间', trigger: 'blur'}
                ],
                checkPassword:[{validator: validateCheckPass, trigger: 'blur'}],
                email: [{required: true, message: '请输入用户名'}],
                telphone: [{required: true, message: '请输入用户名'}],
            },
            registerForm: {
                username: '',
                password: '',
                checkPassword: '',
                email: '',
                telphone: '',
            },
            alertTitle: '',
            isRegFail: false
        }
    },
    methods: {
        // 约定注册状态码：
        // 100： 注册成功
        // 200： 用户名已存在
        submitClick(formName){
            //提交时先关闭错误警告
            this.isRegFail = false;
            let _this = this;
            this.$refs[formName].validate((valid)=>{
                if(valid){
                    // this.$axios.post( 'http://localhost:8080/regLogin/regCheck',
                    //     {
                    //         username: this.registerForm.username,
                    //         password: this.$md5(this.registerForm.password)
                    //     }
                    // ).then(function(response){
                    //     if(response.data){
                    //         alert('注册成功');
                    //     }else{
                    //         //提交失败 打开错误警告
                    //         _this.alertTitle = '注册失败，用户名已存在';
                    //         _this.isRegFail = true;
                    //     }
                    // })
                }else{
                    console.error('error submit');
                    return false;
                }
            })
        },
        isReged(bol){
            if(bol){
                alert("该账号已存在")
            }else{
                alert("注册成功！")
            }
        }
    },
    
}
</script>

<style scoped>
.register_remember {
margin: 0px 0px 35px 0px;
text-align: left;
}
</style>
