<template>
  <div>
    <el-card shadow="never">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="游戏名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="开发商">
          <el-autocomplete
            v-model="form.developer"
            :fetch-suggestions="querySearch"
            placeholder="请输入游戏开发商"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="发行商">
<!--          <el-select v-model="form.gamePublishers" placeholder="请输入游戏发行商" >-->
<!--            <el-option v-for="(item,index) in form.publisher" :key="index" :label="item" :value="item"></el-option>-->
<!--          </el-select>-->
          <el-autocomplete
            v-model="form.publisher"
            :fetch-suggestions="querySearch2"
            placeholder="请输入游戏开发商"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="立即上线">
          <el-switch v-model="form.delivery"></el-switch>
        </el-form-item>
        <el-form-item label="上线时间" v-if="form.delivery==false">
          <el-date-picker
            type="date"
            placeholder="选择日期"
            v-model="form.publishDate"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd hh:mm:ss">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="游戏类型">
          <el-checkbox-group v-if="gameType!=[]" v-for="(item,index) in gameType" v-model="form.type" style="display: inline" :key="index">
            <el-checkbox :label="item" name="type" class="form_type_checkbox"></el-checkbox>
          </el-checkbox-group>
          <span style="color: red" v-else>数据获取异常</span>
        </el-form-item>
        <el-form-item label="游戏价格">
          <el-input v-model="form.price" class="form_price">
            <template slot="append">￥</template>
          </el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" v-model="form.desc"></el-input>
        </el-form-item>
        <div style="text-align: center">
          <el-button type="primary" @click="openBox">保存</el-button>
          <el-button @click="reset()">重置</el-button>
        </div>
      </el-form>
    </el-card>

  </div>
</template>

<script>
  export default {
    name: "AddProduct",
    data() {
      return {
        form: {
          name: '',
          developer:'',
          // developers:[{value:'索尼'},{value:'微软'},{value:'卡普空'},],
          publisher: '',
          publishDate: '',
          delivery: false,
          type: [],
          price: '',
          desc: ''
        },
        gameType: [],
        developers: [],
        publishers: [],
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      },
      save() {
        var _this = this;
        this.axios.post('/product/addProduct', {
          product: _this.form
        })
          .then(function (response) {
            console.log(response);

          })
          .catch(function (error) {
            console.log(error);
          });

        // this.axios.post('/product/testPost1', {
        //   paramName: 'nmsl'
        // })
        //   .then(function (response) {
        //     console.log(response);
        //   })
        //   .catch(function (error) {
        //     console.log(error);
        //   });

        // this.axios.get('/product/getTest', {
        //   params: {
        //     str: 12345
        //   }
        // })
        //   .then(function (response) {
        //     console.log(response);
        //   })
        //   .catch(function (error) {
        //     console.log(error);
        //   });
      },
      reset() {
        this.form = [];
      },
      /*搜索引擎*/
      init(){
        this.getTypes();
        this.getPublishers();
        this.getDevelopers();
      },
      getTypes() {
        let _this = this;
        this.axios.get("product/getAllTypes")
          .then(body => {
            _this.gameType = body.data;
            // console.log(_this.gameType);
          }).catch(function (error) {
          console.log(error);
        });
      },
      getPublishers() {
        let _this = this;
        this.axios.get("product/getPublishers")
          .then(body => {
            for (var i=0;i<body.data.length;i++){
              _this.publishers.push({"value":body.data[i]});
            }
          }).catch(function (error) {
          console.log(error);
        });
      },
      getDevelopers() {
        let _this = this;
        this.axios.get("product/getDevelopers")
          .then(body => {
            for (var i=0;i<body.data.length;i++){
              _this.developers.push({"value":body.data[i]});
            }
          }).catch(function (error) {
          console.log(error);
        });
      },
      querySearch(queryString, cb) {
        var developers = this.developers;
        var results = queryString ? developers.filter(this.createFilter(queryString)) : developers;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
      createFilter(queryString) {
        return (developers) => {
          return (developers.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      querySearch2(queryString, cb) {
        var publisher = this.publishers;
        var results = queryString ? publisher.filter(this.createFilter(queryString)) : publisher;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
      /*搜索引擎*/
      openBox: function () {
        let _this = this;
        this.$confirm('是否确定保存信息?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let msg = _this.save();
          this.$message({
            type: 'success',
            message: '已完成插入'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消添加'
          });
        });
      }
    },
    mounted() {
      this.init();
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
