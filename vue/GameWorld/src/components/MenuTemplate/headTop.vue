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
                        <li v-for="(item,index) in getCategories" :key="index">
                            <router-link to="">{{item}}</router-link>
                        </li>
                    </ul>
                </div>
            </el-aside>
                <div class="total">
                    <el-main>
                        <div style="background:#13131357">
                            <el-carousel  :interval="4000"  class="carouselCss">
                                <el-carousel-item v-for="(item,index) in photo" :key="index" >
                                    <el-image :src="item.path" fit="cover" style="width:600px;height:330px"></el-image>
                                </el-carousel-item>
                            </el-carousel>
                        </div>
                        <div>
                            <h2 style="color:white">特别优惠</h2>
                            <!-- <table>
                                <tr v-for="(item,index) in photo" :key="index">
                                    <td>
                                        <div class="tableCss">
                                            <el-image :src="item.path" fit="cover" style="width:400px;height:220px"></el-image>
                                            <el-tag type="success" effect="dark" style="color: #4e6306;" size="medium ">-50%</el-tag>
                                        </div>
                                    </td>
                                    <td>
                                        
                                    </td>
                                </tr>
                            </table> -->
                            <div style="background:#13131357">
                                <el-row :gutter="20" v-for="(item,index) in photo" :key="index">
                                    <el-col :span="8" v-for="cols in 3" :key="cols">
                                        <div class="tableCss">
                                            <el-image :src="item.path" fit="cover" style="width:300px;height:165px"></el-image>
                                            <el-tag type="success" effect="dark" style="color: #4e6306;" size="medium ">-50%</el-tag>
                                        </div>
                                    </el-col>
                                </el-row>
                            </div>
                        </div>
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
            category: [],
            photo : [
                {path: require('../../assets/img/展示图片1.jpg'), name: '绝地武士'},
                {path: require('../../assets/img/展示图片2.jpg'), name: '怪物猎人：冰原'},
                {path: require('../../assets/img/展示图片3.jpg'), name: '人类必须死'},
                {path: require('../../assets/img/展示图片4.jpg'), name: '攻城'},
                {path: require('../../assets/img/展示图片5.jpg'), name: '打飞机'},
            ]
        }
    },
    methods: {
        // getCategories: function(){
        // // 为给定 ID 的 user 创建请求
        //     var categories = [];
        //     var _this = this;
        //     this.$axios.get('http://localhost:8080/homePage/getCategories')
        //         .then(function (response) {
        //             console.log('response.data'+response.data);
        //             categories = response.data;
        //             _this.category = response.data;
        //         })
        //         .catch(function (error) {
        //             console.log(error);
        //     });
        //     // console.log(categories);
        //     // debugger;
        //     return categories; 
        // } 
    },
    computed: {
        getCategories: function(){
        // 为给定 ID 的 user 创建请求
            this.$axios.get('http://localhost:8080/homePage/getCategories')
                .then(function (response) {
                    console.log(response.data);
                    return response.data;
                })
                .catch(function (error) {
                    console.log(error);
            });
            // console.log(categories); 
        } 
    },
    created() {
        this.getCategories();
        console.log("created"+this.category)
    },
}
</script>

<style scoped>
.whitefont {
    color: white
}
.carouselCss {
    width:600px;
    height:330px;
    margin: auto 200px;
    padding: 20px;
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