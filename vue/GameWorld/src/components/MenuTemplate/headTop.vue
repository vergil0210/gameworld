<template>
    <div >
        <el-container>
            <el-aside width="200px">
                <!-- Aside content -->
                <div id="asideMenu">
                    <ul>
                        <li>
                            <h3>浏览分类</h3>
                        </li>
                        <li>
                            <router-link to="">热销商品</router-link>
                        </li>
                        <li>
                            <router-link to="">新品</router-link>
                        </li>
                        <li>
                            <router-link to="">优惠</router-link>
                        </li>
                        <li>
                            <h3>按类型浏览</h3>
                        </li>
                        <li v-for="(item,index) in category" :key="index">
                            <router-link to="">{{item}}</router-link>
                        </li>
                    </ul>
                </div>
            </el-aside>

                <el-container>
                    <div class="total">
                    <el-header height="100">
                        <!-- Header content -->
                        <div id="headMenu">
                            <el-menu :default-active="activeIndex" class="front" mode="horizontal" 
                            background-color="transparent">
                                <el-menu-item index="1">处理中心</el-menu-item>
                                <el-submenu index="2">
                                    <template slot="title">我的工作台</template>
                                    <el-menu-item index="2-1">选项1</el-menu-item>
                                    <el-menu-item index="2-2">选项2</el-menu-item>
                                    <el-menu-item index="2-3">选项3</el-menu-item>                  
                                </el-submenu>
                                <el-menu-item index="3" >消息中心</el-menu-item>
                                <el-menu-item index="4" >消息中心</el-menu-item>
                            </el-menu> 
                        </div>
                    </el-header>
                    <el-main height="">
                        <div id="main"> 
                            <router-view></router-view>
                        </div>
                    </el-main>
                    </div>
                </el-container>
        </el-container>
    </div>
</template>
<script>
export default {
    data() {
        return {
            store: ['商店1','商店2','商店3'],
            category: ['休闲','体育','冒险','动作','策略','竞速']
        }
    },
}
</script>

<style>
.front {
    color: white
}

.total {
    background-color: #303030;
}

a {
    font-weight: normal;
    color: rgb(4, 115, 219);
    text-decoration: none;
    
}
a:hover {
    color: white
}
ul {
  list-style-type: none;
  padding: 0;
}

#asideMenu {
    background-clip: padding-box;
    position:absolute;
    left:50px;
    top:200px;
    font-family: -webkit-pictograph;
    color: antiquewhite
}
#main {
    width: 2000px;
    height: 1000px;
    background-color: black

}
#h3 {
    font-size: large
}
</style>