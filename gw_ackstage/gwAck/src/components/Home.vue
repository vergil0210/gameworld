<template>
  <div class="">
    <el-container>
      <el-header>
        <div class="title">
          <h1>GameWorld后台管理系统</h1>
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu
            default-active="this.$route.path"
            router
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>商品管理</span>
              </template>
              <el-submenu index="1-1">
                <template slot="title">添加/删除商品</template>
                <el-menu-item index="/home/addProduct">添加商品</el-menu-item>
                <el-menu-item index="/home/deleteProduct">删除/修改商品</el-menu-item>
              </el-submenu>
              <el-menu-item index="/home/onSale">上架/下架商品</el-menu-item>
              <el-menu-item index="1-4">促销管理</el-menu-item>
            </el-submenu>
            <el-menu-item index="2">
              <i class="el-icon-menu"></i>
              <span slot="title">权限管理</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
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
    }
  }
</script>
<style scoped>
  .title {
    text-align: center;
  }
</style>
