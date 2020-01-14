<template>
    <div class="HomePage_background">
        
        <div id="headMenu">
            <el-menu   mode="horizontal" 
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
            
        }
    },
    methods: {  
        axoisTest(){
            // 为给定 ID 的 user 创建请求
            this.$axios.get('http://localhost:8080/regLogin/test')
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        }
    },
    mounted () {
        this.axoisTest()
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

.HomePage_background {
    background: #060c276b;
    height: 1000px;
    overflow: auto;
}

#logo {
    
    display: inline;
}

#headMenu {
    position: relative;
    left: 220px;
}

.fontCss {
    font-weight: normal;
    color: rgb(169, 162, 153);
}
</style>