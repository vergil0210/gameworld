<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="游戏名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="开发商">
        <el-select v-model="form.gameDeveloper" placeholder="请选择游戏开发商" >
          <el-option v-for="(item,index) in form.developers" :key="index" :label="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="发行商">
        <el-select v-model="form.gamePublishers" placeholder="请选择游戏发行商" >
          <el-option v-for="(item,index) in form.publisher" :key="index" :label="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="立即上线">
        <el-switch v-model="form.delivery"></el-switch>
      </el-form-item>
      <el-form-item label="上线时间" v-if="form.delivery==false">
        <el-date-picker type="date" placeholder="选择日期" v-model="form.publishDate"></el-date-picker>
      </el-form-item>
      <el-form-item label="游戏类型">
        <el-checkbox-group v-for="(item,index) in form.gameType" v-model="form.type" style="display: inline" :key="index">
          <el-checkbox :label="item" name="type" class="form_type_checkbox"></el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="游戏价格">
        <el-input v-model="form.name" class="form_price">
          <template slot="append">￥</template>
        </el-input>
      </el-form-item>
      <el-form-item label="描述">
          <el-input type="textarea" v-model="form.desc"></el-input>
      </el-form-item>
      <div style="text-align: center">
        <el-button type="primary">保存</el-button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <el-button>重置</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "AddProduct",
    data() {
      return {
        form: {
          name: '',
          gameDeveloper: '',
          developers:['索尼','微软','卡普空'],
          gamePublishers: '',
          publisher: ['发行商1','发行商2','发行商3','发行商4'],
          publishDate: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          gameType: ['动作','冒险','益智','策略'],
          resource: '',
          desc: ''
        }
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      },

    }
  }
</script>

<style scoped>
  .form_type_checkbox {
    padding-right: 30px;
  }
  .form_price {
    text-align: right;
    width: 150px;
  }
</style>
