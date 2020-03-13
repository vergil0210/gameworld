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
                    <el-submenu index="1">
                      <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>商品管理</span>
            </template>
            <el-menu-item index="/home/deleteProduct">商品列表</el-menu-item>
            <el-menu-item index="/home/addProduct">添加商品</el-menu-item>
            <el-menu-item index="/home/onSale">上架/下架</el-menu-item>
            <el-menu-item index="/home/addType">商品类型</el-menu-item>
            <el-menu-item index="1-4">促销管理</el-menu-item>
          </el-submenu>
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">权限管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header>
          <el-card shadow="never" style="height: 55px">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item><a href="">活动管理</a></el-breadcrumb-item>
              <el-breadcrumb-item><a href="">活动列表</a></el-breadcrumb-item>
              <el-breadcrumb-item><a href="">活动详情</a></el-breadcrumb-item>
            </el-breadcrumb>
            <div class="title">
                <h1 style="font-family: -webkit-pictograph">GameWorld后台管理系统</h1>
                {{getRouter}}
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
  let map = new Map();
  map.set("key",this.$route);
  export default{
    name: "Home",
    data() {
      return {

      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    },
    computed: {
      getRouter() {
        console.log(this.$route)
      }
    }

  }
</script>
<style scoped>
  .title {
    margin: -40px 400px;
  }
  aside>ul {
    height: 100%;
  }
</style>
