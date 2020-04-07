<template>
  <div>
    <div style="padding-bottom: 30px">
      <el-card class="operate-container" shadow="never" >
          <i class="el-icon-tickets"></i>
          <span>数据列表</span>
          <div style="float: right">
            <el-button
              class="btn-add"
              @click="handleAddType()"
              size="mini" style="text-align: right">
              添加
            </el-button>
          </div>
        </el-card>
    </div>
    <div>
      <el-card shadow="never" >
        <span>已有的游戏类型 {{gameType}}</span>
      </el-card>
    </div>
  </div>
</template>

<script>
      export default {
      name: "AddType",
      data() {
        return {
          gameType: []
        }
      },
      methods: {
          handleAddType() {
            let _this = this;
            let msg = '';
            {
              this.$prompt('类型名称', '添加类型', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
              }).then(({ value }) => {
                _this.axios.post('product/addType',
                  { type: value})
                  .then(function (response) {
                    msg = response.data;
                  });
                console.log(msg);
                if (!msg=='添加成功'){
                  _this.$message.error('添加失败');
                }else {
                  this.$message({
                    type: 'success',
                    message: '您添加的类型是: ' + value
                  });
                }
                _this.$router.go(0);
              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '取消添加'
                });
              });
            }

          },
        //查询结果table的回调方法
        tableRowClassName({row, rowIndex}) {
          row.id = rowIndex;
          if (rowIndex === 1) {
            return 'warning-row';
          } else if (rowIndex === 3) {
            return 'success-row';
          }
          return '';
        },
        getTypes() {
          let _this = this;
          this.axios.get("product/getAllTypes")
            .then(body => {
              _this.gameType = body.data;
              console.log(body.data);
            }).catch(function (error) {
            console.log(error);
          });
        }
      },
      mounted() {
        this.getTypes();
      },
    }
</script>

<style scoped>

</style>
