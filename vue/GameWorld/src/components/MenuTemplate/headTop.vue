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
                        <li v-for="(item,index) in categories" :key="index">
                            <router-link to="">{{item}}</router-link>
                        </li>
                    </ul>
                </div>
            </el-aside>
                <div class="total">
                    <el-main>
                        <router-view></router-view>
                    </el-main>
                </div>
        </el-container>
    </div>
</template>
<script>
export default {
    data() {
        return {
            store: ['商店1','商店2','商店3'],
            categories: ['动作','冒险','模拟','角色扮演','休闲','沙盒'],
            photo : [
                {path: require('../../assets/img/展示图片1.jpg'), name: '绝地武士'},
                {path: require('../../assets/img/展示图片2.jpg'), name: '怪物猎人：冰原'},
                {path: require('../../assets/img/展示图片3.jpg'), name: '人类必须死'},
                {path: require('../../assets/img/展示图片4.jpg'), name: '攻城'},
                {path: require('../../assets/img/展示图片5.jpg'), name: '打飞机'},
            ]
        }
    },
    
}
</script>

<style scoped>
.whitefont {
    color: white
}

.tableCss {
    background-clip: padding-box;
    margin: auto auto;
    width: 300px;
    height: 200px;
    padding: 0px 0px 0px 0px;
    background: #13131357;
    box-shadow: 0 0 25px #13131357;
    
}


a {
    font-weight: normal;
    color: rgb(92, 176, 255);
    text-decoration: none;
    
}
a:hover {
    color: white
}
ul {
  list-style-type: none;
  padding: 0;
}
#headMenu ul li ,#headMenu ul li div{
    color: white
}

#asideMenu {
    background-clip: padding-box;
    position:absolute;
    left:50px;
    top:200px;
    font-family: -webkit-pictograph;
    color: antiquewhite
}
/* #main {
    width: 1000px;
    height: 1000px;
    background-color: black

} */
#h3 {
    font-size: large
}
</style>