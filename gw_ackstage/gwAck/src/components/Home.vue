<template>
  <div class="">
    <el-container>
      <el-aside width="200px">
        <el-menu
          default-active="this.$route.path"
          router
          overflow-y: scroll
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose">
          <el-submenu index="1" :disabled="validateProduct">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>商品管理</span>
            </template>
            <el-menu-item index="/home/deleteProduct">商品列表</el-menu-item>
            <el-menu-item index="/home/addProduct">添加商品</el-menu-item>
            <el-menu-item index="/home/onSale">上架/下架</el-menu-item>
            <el-menu-item index="/home/addType">商品类型</el-menu-item>
          </el-submenu>
          <el-submenu index="2" :disabled="validateOrder">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>订单管理</span>
            </template>
            <el-menu-item index="/home/orderList">订单列表</el-menu-item>
            <el-menu-item index="/home/orderReturn">退货申请处理</el-menu-item>
            <el-menu-item index="/home/addOrder">订单添加(数据源)</el-menu-item>
          </el-submenu>
          <el-menu-item index="/home/authControl" :disabled="role!=='T0'">权限管理</el-menu-item>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header>
          <el-card shadow="never" style="height: 55px">
<!--            <el-breadcrumb separator-class="el-icon-arrow-right">-->
<!--              <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>-->
<!--              <el-breadcrumb-item><a href="">活动管理</a></el-breadcrumb-item>-->
<!--              <el-breadcrumb-item><a href="">活动列表</a></el-breadcrumb-item>-->
<!--              <el-breadcrumb-item><a href="">活动详情</a></el-breadcrumb-item>-->
<!--            </el-breadcrumb>-->
            <div style="margin-top: -40px">
              <div  style="margin-top: 35px;text-align: center;">
                  <h1 style="font-family: -webkit-pictograph">GameWorld后台管理系统</h1>
              </div>
              <div style="dispaly: inline; margin-top: -50px; text-align: right;">
                欢迎您，尊敬的管理员：<span style="color: #7dabe4">{{user.username}}</span>
                <el-dropdown trigger="click">
                  <span class="el-dropdown-link">
                    <i class="el-icon-arrow-down el-icon--right"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item icon="el-icon-house">
                      <template>
                        <el-button type="text" @click="pushHome()">主页</el-button>
                      </template>
                    </el-dropdown-item>
                    <el-dropdown-item icon="el-icon-remove-outline">
                      <template>
                        <el-button type="text" @click="logout()">退出</el-button>
                      </template>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </div>
            </div>
          </el-card>

        </el-header>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
  import { mapState } from 'vuex'
  import Cookie from 'js-cookie'
  import { mapMutations } from 'vuex'
  export default{
    name: "Home",
    data() {
      return {

      }
    },
    methods: {
      ...mapMutations(["del_token","del_userInfo"]),
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      pushHome(){
        this.$router.push({path:'/home'});
      },
      logout(){
        Cookie.remove('token')
        Cookie.remove('userInfo')
        this.del_token();
        this.del_userInfo();
        this.$router.go(0)
      },
    },
    computed: {
      user(){
        console.log(JSON.parse(Cookie.get('userInfo')))
        return JSON.parse(Cookie.get('userInfo'))
      },
      role() {
        console.log('role:' +JSON.parse(Cookie.get('userInfo')).role )
        return JSON.parse(Cookie.get('userInfo')).role
      },
      validateProduct() {
        const _this = this
        const list = ['T0','T1']
        return !list.some(function a(value) {return value===_this.role})
      },
      validateOrder() {
        const _this = this
        const list = ['T0','T1','T2']
        return !list.some(function a(value) {return value===_this.role})
      }
      // ...mapState(['user'])
    }

  }
</script>
<style scoped>

  aside>ul {
    height: 100%;
  }
</style>
