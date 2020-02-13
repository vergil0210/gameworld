<template>
    <div class="center">

        <img src="../assets/img/Logo.png" alt="Logo">

        <div>
            <h1>Welcome to Game World</h1>
            <el-divider/>
            <br/>
            <div class="line">
                <router-link to="homePage">主页</router-link><br/>
                <router-link to="regLogin">登录</router-link><br/>
                <router-link to="">成为会员</router-link><br/>
            </div>
        </div>

    </div>


</template>

<script>
export default {
    data() {
        return {

        }
    },
}
</script>

<style scoped>

div {
    font-family: -webkit-pictograph;
    color: antiquewhite
}

a {
    font-weight: normal;
    color: antiquewhite;
    text-decoration: none;
}
a:hover {
    color: #00B39E;
}
.line {
    line-height:35px
}

.center{
    top:100px;
    position:relative;
    text-align: center;
}
img {
    display: block;
    margin: 0 auto;
}
</style>
