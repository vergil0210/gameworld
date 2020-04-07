<template>
  <div  style="padding-bottom: 30px">
    <el-card shadow="never" style="margin-bottom: 20px">
      <div>
        <i class="el-icon-search"></i>
        <span>筛选搜索</span>
        <el-button
          style="float: right"
          @click="handleSearchList()"
          type="primary"
          size="small">
          查询结果
        </el-button>
        <el-button
          style="float: right;margin-right: 15px"
          @click="handleResetSearch()"
          size="small">
          重置
        </el-button>
      </div>
      <div style="margin-top: 15px">
        <el-form :inline="true" :model="tempList" size="small" label-width="140px">
          <el-form-item label="输入搜索：">
            <el-input style="width: 215px" v-model="tempList.orderId" placeholder="订单号" clearable></el-input>
          </el-form-item>
          <el-form-item label="订单状态：">
            <el-select v-model="tempList.status" placeholder="请选择订单状态" clearable>
              <el-option
                v-for="(item,index) in status"
                :key="index"
                :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="用户姓名：">
            <el-input style="width: 215px" v-model="tempList.username" placeholder="请输入购买用户姓名" clearable></el-input>
          </el-form-item>
          <el-form-item label="支付方式：">
            <el-select v-model="tempList.payPattern" placeholder="请输入支付方式" clearable>
              <el-option
                v-for="(item,index) in payPattern"
                :key="index"
                :value="item">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
    </el-card>

    <el-card shadow="never">
      <el-table
        :data="tableData"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        ref="multipleTable"
        :row-class-name="tableRowClassName"
        @row-click = "onRowClick"
        border>
        <el-table-column
          type="selection"
          width="60"
          align="center">
        </el-table-column>
        <el-table-column
          label="序号"
          width="50px"
          type="index"
          align="center">
        </el-table-column>
        <el-table-column
          label="订单id"
          width="80px"
          align="center"
          prop="orderId">
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="提交时间"
          width="190px" align="center">
        </el-table-column>
        <el-table-column
          prop="username"
          label="用户账号"
          width="100px" align="center">
        </el-table-column>
        <el-table-column
          prop="products"
          label="订单金额"
          align="center"
          width="160px">
          <template slot-scope="scope">
            {{ amount(scope.row.products)}}
          </template>
        </el-table-column>
        <el-table-column
          prop="status"
          label="订单状态" align="center">
        </el-table-column>
        <el-table-column
          prop="payPattern"
          label="支付方式" align="center">
        </el-table-column>
        <el-table-column
          label="操作"
          align="center">
          <template slot-scope="scope">
            <el-button @click.native.prevent="deleteRow(scope.row,scope.$index,tableData)" type="text" size="small">删除</el-button>
            <el-button @click.native.prevent="closeRow(scope.row,scope.$index,tableData)" type="text" size="small">关闭订单</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="page">
        <el-pagination
          background
          layout="prev, pager, next"
          :total="50"
          @current-change="handleCurrentChange"
          :current-page="currentPage">
        </el-pagination>
      </div>
      <div style="text-align: left">
        <el-select v-model="operate"  placeholder="选择批量操作" clearable>
          <el-option
            v-for="item in operates"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
        <el-button @click="execute()" type="primary">执行</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "OrderList",
    data() {
      return {
        list: {},
        tempList: {
          orderId: '',
          username: '',
          status: '',
          payPattern: '',
          page:0,
          size: 3
        },
        status: [
          '待付款','已完成','已关闭'
        ],
        payPattern: ['待付款','正在处理','拒绝退款','退款申请中','已退款','订单已关闭'],
        tableData: [],
        currentPage: 0,
        operates: ['删除订单','关闭订单'],
        operate: '',
        deleteSuccess: false
      }
    },
    methods: {
      /* 搜索框 */
      handleSearchList() {
        //展开表格
        let _this = this;
        this.axios.post('/order/searchOrders',_this.tempList)
          .then(function (response) {
            console.log(response)
          _this.tableData = response.data
        })
          .catch(function (error) {
            console.log(error);
          });
      },
      handleResetSearch() {
        this.tempList.orderId='';
        this.tempList.status='';
        this.tempList.username='';
        this.tempList.payPattern = '';

      },
      init() {

      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      amount(products) {
        let price = 0;
        for (let product of products) {
          price += product.originalPrice
        }
        return price + '元';
      },
      handleCurrentChange(val) {
        this.tempList.page = val-1;
        this.handleSearchList();
      },
      onRowClick (row, event, column) {
        this.currentRowIndex = row.row_index;
      },
      tableRowClassName({ row, rowIndex }) {	//行元素，行索引
        row.index = rowIndex;
      },
      deleteOne(order,index,rows){
        let _this = this;
        this.axios.post('/order/deleteOne',order)
          .then(function (response) {

            if (response.data === '') {
              _this.$message({
                message: '删除成功',
                type: 'success'
              });
              rows.splice(index,1)
            }else {
              _this.$message.error(response.msg);
            }
          })
          .catch(function (error) {
            console.log(error);
            this.$message.error("删除出错，请联系管理员");
          });
      },
      deleteRow(row,index,rows) {
        console.log('=============== delete begin =================')
        this.deleteOne(row,index,rows);
        console.log('=============== delete over =================')
      },
      closeRow(order,index,rows){
        let _this = this;
        this.axios.post('/order/closeOrder',order)
          .then(function (response) {
            if (response.data === '') {
              rows[index].status = "已关闭";
              _this.$message({
                message: '修改成功',
                type: 'success'
              });
            }else {
              _this.$message.error(response.msg);
            }
          })
          .catch(function (error) {
            console.log(error);
            this.$message.error("修改出错");
          });
      },
      execute(){
        console.log('============ 执行处理 ============')
        if (this.operate === '删除订单'){
          let selections = this.$refs.multipleTable.store.states.selection
          for (let selection of selections) {
            this.deleteOne(selection,selection.index,this.tableData);
          }
        }else if (this.operate === '关闭订单'){

        }
      }
    },
    computed: {

    }
  }
</script>

<style scoped>
  .page {
    text-align: center;
    padding-top: 20px;
  }
</style>
