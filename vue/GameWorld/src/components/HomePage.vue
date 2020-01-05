<template>
    <div class="HomePage_background">
        <div class="top">
            <div class="rightTop">
                <span class="fontCss">欢迎来到游戏世界</span>
                <router-link to="">请登录</router-link>
                <span class="fontCss">|</span>
                <router-link to="">免费注册</router-link>
            </div>
            <div class="leftTop">
                <el-image :src="logoPath" id="logo" style="width: 30px;height: 30px;" @click="skipHomePage"></el-image>
                <h1 style="display: inline">GameWorld</h1>&nbsp;&nbsp;
                <router-link to="">商店</router-link>&nbsp;&nbsp;
                <router-link to="">社区</router-link>&nbsp;&nbsp;
                <router-link to="">关于</router-link>&nbsp;&nbsp;
                <router-link to="">客服</router-link>
            </div>
        </div>
        <div id="headMenu">
            <el-menu :default-active="activeIndex"  mode="horizontal" 
            background-color="transparent" style="border:0px"  text-color="white">
                <el-menu-item index="1">处理中心</el-menu-item>
                <el-submenu index="2">
                    <template slot="title">您的商店</template>
                    <el-menu-item index="2-1">商店主页</el-menu-item>
                    <el-menu-item index="2-2">最近浏览</el-menu-item>
                    <el-menu-item index="2-3">最近更新</el-menu-item>                  
                </el-submenu>
                <el-menu-item index="3" >消息中心</el-menu-item>
                <el-menu-item index="4" >消息中心</el-menu-item>
            </el-menu> 
        </div>        
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    data () {
        return {
            logoPath: require("../assets/img/Logo1.png"),
        }
    },
    methods: {
        skipHomePage(){
            this.$router.push("/homePage");
        }
    }

}
</script>

<style scoped>
h1 {
    font-family: -webkit-pictograph;
    color: antiquewhite;
}
.el-menu--collapse .el-menu .el-submenu, .el-menu--popup {
    min-width: 260px;
    max-height: 50px;
}
.top {
    width: 100%;
    height: 100px;
    background-color: #373636c4;
    background-clip: padding-box;
    padding: 5px 5px 5px 5px;
    box-shadow: 0 0 25px #000000;
}
.HomePage_background {
    background: #060c276b;
    height: 1000px;
    overflow: auto;
}
a {
    font-weight: normal;
    color: antiquewhite;
    text-decoration: none;
}
a:hover {
    color: #00B39E;
}
#logo {
    
    display: inline;
}

#headMenu {
    position: relative;
    left: 220px;
}
.leftTop {
    position: relative;
    left: 20%;
    top: 10%;
}
.rightTop {
    position: relative;
    left: 80%;
    top: 10%;
}
.fontCss {
    font-weight: normal;
    color: rgb(169, 162, 153);
}
</style>