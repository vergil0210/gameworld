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
          label="购买游戏"
          align="center"
          width="240px">
          <template slot-scope="scope">
            <div v-html="productNames(scope.row.products)"> </div>
          </template>
        </el-table-column>
        <el-table-column
          prop="products"
          label="订单金额"
          align="center"
          width="140px">
          <template slot-scope="scope">
            {{ amount(scope.row.products)}}
          </template>
        </el-table-column>
        <el-table-column
          prop="status"
          label="订单状态" align="center">
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          width="160px">
          <template slot-scope="scope">
            <el-button @click="openDialog(scope.row)" type="primary" size="small" plain>详细信息</el-button>
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
    </el-card>
    <el-dialog :visible.sync="dialogVisible" title="订单详细信息">
      <el-card shadow="never" style="padding-bottom: 10px;">
        <el-table :data="dialogTable" border style="width: 100%">
          <el-table-column prop="name" label="游戏名称" align="center" width="180px"/>
          <el-table-column prop="originalPrice" label="价格" align="center">
            <template slot-scope="scope">
              {{scope.row.originalPrice}}￥
            </template>
          </el-table-column>
          <el-table-column label="数量" align="center">
            <template slot-scope="scope">
              1
            </template>
          </el-table-column>
          <el-table-column prop="originalPrice" label="小计" align="center">
            <template slot-scope="scope">
              {{scope.row.originalPrice}}￥
            </template>
          </el-table-column>
        </el-table>
        <span style="float: right;font-size: 16px">
          总计： <span style="color: red">{{ amount(dialogTable) }}</span>
        </span>
      </el-card>
      <br/>
      <br/>
      <el-card shadow="never">
        <span style="font-size:  16px"> 订单信息 </span>
        <div class="form-container-border">
          <el-row>
            <el-col :span="6" class="form-border form-left-bg font-small">服务单号</el-col>
            <el-col class="form-border font-small" :span="18">{{dialogOrder.orderId}}</el-col>
          </el-row>
          <el-row>
            <el-col :span="6" class="form-border form-left-bg font-small">申请状态</el-col>
            <el-col class="form-border font-small" :span="18">{{dialogOrder.status}}</el-col>
          </el-row>
          <el-row>
            <el-col :span="6" class="form-border form-left-bg font-small">创建时间</el-col>
            <el-col class="form-border font-small" :span="18">{{dialogOrder.createTime}}</el-col>
          </el-row>
          <el-row>
            <el-col :span="6" class="form-border form-left-bg font-small">用户名</el-col>
            <el-col class="form-border font-small" :span="18">{{dialogOrder.user.username}}</el-col>
          </el-row>
          <el-row>
            <el-col :span="6" class="form-border form-left-bg font-small">服务单号</el-col>
            <el-col class="form-border font-small" :span="18">{{dialogOrder.orderId}}</el-col>
          </el-row>
          <el-row>
            <el-col :span="6" class="form-border form-left-bg font-small">申请原因</el-col>
            <el-col class="form-border font-small" :span="18">{{dialogOrder.returnReason ? dialogOrder.returnReason : '未填写原因'}}</el-col>
          </el-row>
        </div>
        <br/>
        <div style="text-align: center">
          <el-button type="primary" plain @click="agreeReturn()">同意退款</el-button>
          <el-button type="warning" plain @click="refuseReturn()">拒绝退款</el-button>
        </div>
      </el-card>
    </el-dialog>

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
          '待付款','正在处理','拒绝退款','退款申请中','已退款','订单已关闭'
        ],
        payPattern: ['支付宝','微信','银行卡'],
        tableData: [],
        currentPage: 0,
        operates: ['删除订单','关闭订单'],
        operate: '',
        deleteSuccess: false,
        // dialog
        dialogTable: [],
        dialogVisible: false,
        dialogTableVisible: false,
        dialogOrder: {user: {username: ''}}
      }
    },
    methods: {
      /* 搜索框 */
      handleSearchList() {
        //展开表格
        let _this = this;
        this.axios.post('/order/searchReturnOrders',_this.tempList)
          .then(function (response) {
            console.log('====================search data====================')
            console.log(response)
            console.log('====================search data====================')
          _this.tableData = response.data
        })
          .catch(function (error) {
            console.log(error);
          });
      },
      handleResetSearch() {
        this.tempList.name='';
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
        return '￥'+price;
      },
      productNames(products) {
        let gameNames = ''
        for (let product of products) {
          gameNames = gameNames + '<br/>' +product.name
        }
        return gameNames
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
            _this.$message.error("修改出错");
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
      },
      openDialog(row) {
        this.dialogVisible = true
        this.dialogOrder = row
        this.dialogTable = row.products
        console.log({"dialogTable: " : this.dialogOrder})
      },
      refuseReturn(){
        let _this = this
        this.axios.post('/order/refuseOrder',
          _this.dialogOrder
        ).then(function (response) {
          if (response.data === ''){
            _this.$message({
              type: 'success',
              message: '已拒绝退款'
            })
            _this.dialogOrder.status = "拒绝退款"
            _this.dialogVisible = false
          }else {
            _this.$message.error(response.data)
          }
        }).catch(function (error) {
            _this.$message({
              type: 'warning',
              message: '对不起，修改失败。如核对修改信息无误，请联系管理员'
            })
          });
      },
      agreeReturn(){
        let _this = this
        this.axios.post('/order/agreeOrder',
          _this.dialogOrder
        ).then(function (response) {
          if (response.data === ''){
            _this.$message({
              type: 'success',
              message: '退款成功'
            })
            _this.dialogOrder.status = "已退款"
            _this.dialogVisible = false
          }else {
            _this.$message.error(response.data)
          }
        }).catch(function (error) {
          _this.$message({
            type: 'warning',
            message: '对不起，退款失败。如核对修改信息无误，请联系管理员'
          })
        });
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
  .form-container-border {
    border-left: 1px solid #DCDFE6;
    border-top: 1px solid #DCDFE6;
    margin-top: 15px;
  }
  .form-border {
    border-right: 1px solid #DCDFE6;
    border-bottom: 1px solid #DCDFE6;
    padding: 10px;
  }
  .form-left-bg {
    background: #F2F6FC;
  }
</style>
