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
        <el-table
          :data="formData"
          style="width: 100%"
          :row-class-name="tableRowClassName"
          border>
          <el-table-column label="编号"  align="center" width="200px">
            <template slot-scope="scope">{{ scope.row.id + 1 }}</template>
          </el-table-column>
          <el-table-column
            prop="type"
            label="类型"
            align="center"
            width="200px">
          </el-table-column>
          <el-table-column
            prop="number"
            label="游戏数量"
            align="center">
          </el-table-column>
          <el-table-column label="标签" width="300" align="center" >
            <template slot-scope="scope">
              <p>隐藏类型：
                <el-switch
                  @change="handlePublishStatusChange(scope.$index, scope.row)"
                  :active-value="1"
                  :inactive-value="0"
                  v-model="scope.row.publishStatus">
                </el-switch>
              </p>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script>
      export default {
      name: "AddType",
      data() {
        return {
          formData: [
            { type: '动作', number: '100' }
          ]
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
                  this.$message.error('添加失败');
                }else {
                  this.$message({
                    type: 'success',
                    message: '您添加的类型是: ' + value
                  });
                }

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
      }
    }
</script>

<style scoped>

</style>
