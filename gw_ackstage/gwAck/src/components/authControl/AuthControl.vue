<template>
  <div>
<!--    <el-card shadow="never" style="margin-bottom: 20px">-->
<!--      <div>-->
<!--        <i class="el-icon-search"></i>-->
<!--        <span>筛选搜索</span>-->
<!--      </div>-->
<!--      <div style="margin-top: 15px">-->
<!--        <el-input style="width: 215px" v-model="username" placeholder="用户名" clearable></el-input>-->
<!--        <el-button-->
<!--          style="float: right"-->
<!--          @click="handleSearchList()"-->
<!--          type="primary"-->
<!--          size="small">-->
<!--          查询结果-->
<!--        </el-button>-->
<!--      </div>-->
<!--    </el-card>-->

    <el-card shadow="never">
      <el-table
        :data="tableData"
        style="width: 100%"
        ref="multipleTable"
        border>
        <el-table-column
          label="序号"
          width="80px"
          type="index"
          align="center"
          sortable>
        </el-table-column>
        <el-table-column
          label="管理员id"
          width="300px"
          align="center"
          prop="id" sortable>
        </el-table-column>
        <el-table-column
          prop="username"
          label="昵称"
          width="300px" align="center" sortable>
        </el-table-column>
        <el-table-column
          prop="role"
          label="权限等级" align="center" width="250px" sortable>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center" >
          <template slot-scope="scope">
            <el-button @click.native.prevent="setRole(scope.row,scope.$index)" type="text" size="small">修改等级</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="权限管控" :visible.sync="dialogVisible" width="30%">
      <el-select v-model="role" placeholder="选择等级">
        <el-option
          v-for="(item,index) in roles" :key="index" :label="item" :value="item">
        </el-option>
      </el-select>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogClick()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "AuthControl",
    data() {
      return {
        username: '',
        dialogVisible: false,
        role: '',
        roles: ['T0','T1','T2','T3'],
        tempRow: {},
        tableData: []
      }
    },
    methods: {
      setRole(row,index) {
        this.dialogVisible = true
        this.tempRow = row
      },
      handleSearchList(){
        //post
        const _this = this
        this.axios.post('/admin/searchAdmin',{username: _this.username})
          .then(function (response) {
            console.log(response)
            _this.tableData = response.data
            //处理
          })
          .catch(function (error) {
            console.log(error);
          });
      },
      dialogClick(){
        console.log("------")
        const _this = this
        _this.tempRow.role = _this.role
        //post
        this.axios.post('/admin/updateAuth',_this.tempRow)
          .then(function (response) {
            if (response.data) {
              _this.$message.error(response.data)
              return false
            }
            _this.$message({
              message: '修改成功',
              type: 'success'
            });
            _this.tempRow.role = _this.role
          })
          .catch(function (error) {
            console.log(error);
          });
        this.dialogVisible = false
      }
    },
    mounted() {
      this.handleSearchList()
    }
  }
</script>

<style scoped>

</style>
