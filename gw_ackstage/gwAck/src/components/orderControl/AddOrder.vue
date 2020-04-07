<template>
  <div>
    <el-card shadow="never">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="">
        </el-form-item>
        <div class="transfer">
          <el-transfer
            v-model="form.gameNames"
            :data="gameNames"
            :titles="['所有游戏', '购买游戏']"
          ></el-transfer>
        </div>
        <el-form-item label="用户id">
          <el-input v-model="form.username" class="form_price" style="width: 217px">
          </el-input>
        </el-form-item>
        <el-form-item label="支付方式">
          <el-select v-model="form.payPattern" placeholder="请选择">
            <el-option
              v-for="(item,index) in payPattern"
              :key="index"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
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
    name: "addOrder",
    data() {
      return {
        form: {
          payPattern: '',
          gameNames: []
        },
        payPattern: ['支付宝','微信','银行卡'],
        gameNames: [
        ]
      }
    },
    methods: {
      init() {
        this.getAllNames()
      },
      getAllNames(){
        let _this = this;
        this.axios.get("product/getAllNames")
          .then(body => {
            for (var i=0;i<body.data.length;i++){
              _this.gameNames.push({key:body.data[i], label: body.data[i],disabled: false});
            }
            console.log(_this.gameNames);
          }).catch(function (error) {
          console.log(error);
        });
      },
      save() {
        let _this = this;
        this.axios.post("order/addOrder", _this.form)
          .then(body => {
            if (body.data=== ""){
              _this.$message({
                type: 'success',
                message: '插入成功！'
              })
            }else {
              _this.$message.error(body.data);
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      },

      openBox: function () {
        let _this = this;
        this.$confirm('是否确定提交订单?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          _this.save();
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消添加'
          });
        });
      },
      reset() {
        this.form = [];
      },
    },
    mounted() {
      this.init();
    }
  }
</script>

<style scoped>
.transfer {
  padding: 0px 0px 30px 180px
}
</style>
