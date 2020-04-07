<template>
  <div>
    <div  style="padding-bottom: 30px">
      <el-card shadow="never">
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
              <el-input style="width: 215px" v-model="tempList.name" placeholder="请输入游戏名" clearable></el-input>
            </el-form-item>
            <el-form-item label="游戏类型：">
              <el-select v-model="tempList.type[0]" placeholder="请选择游戏类型" clearable>
                <el-option
                  v-for="(item,index) in types"
                  :key="index"
                  :value="item">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="开发商：">
              <el-select v-model="tempList.developer" placeholder="请选择开发商" clearable>
                <el-option
                  v-for="item in developers"
                  :key="item.value"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="发行商：">
              <el-select v-model="tempList.publisher" placeholder="请选择发行商" clearable>
                <el-option
                  v-for="item in publishers"
                  :key="item.value"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </div>
      </el-card>
    </div>
    <div style="padding-bottom: 30px">
      <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
      <div style="float: right">
        <el-button
          class="btn-add"
          @click="handleAddProduct()"
          size="mini" style="text-align: right">
          添加
        </el-button>
      </div>

    </el-card>
    </div>
    <el-card shadow="never" v-if="showTable" >
      <el-table
        :data="tableData"
        style="width: 100%"
        :row-class-name="tableRowClassName"
        @row-click="changeRowIndex"
        v-loading.body="loading"
        border>
        <el-table-column
          prop="name"
          label="游戏名"
          width="180" align="center">
        </el-table-column>
        <el-table-column
          label="游戏类型"
          width="130px" align="center">
          <template slot-scope="scope">
            <span v-for="item in scope.row.type" v-if="item!=null">{{item}}&nbsp;</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="publisher"
          label="发行商"
          width="100px" align="center">
        </el-table-column>
        <el-table-column
          prop="developer"
          label="开发商"
          width="100px" align="center">
        </el-table-column>
        <el-table-column
          prop="desc"
          label="描述" align="center">
        </el-table-column>

        <!--    游戏信息修改      -->
        <el-table-column
          fixed="right"
          label="操作"
          width="100" align="center">
          <template slot-scope="scope">
            <el-popover
              placement="right"
              width="100%"
              trigger="click">
              <el-form ref="form" :model="tableData[row_index]" label-width="80px">
                <el-form-item label="游戏名称">
                  <el-input v-model="tableData[row_index].name"></el-input>
                </el-form-item>
                <el-form-item label="开发商">
                  <el-select v-model="tableData[row_index].developer" placeholder="请选择游戏开发商" >
                    <el-option v-for="(item,index) in developers" :key="index":value="item"></el-option>
<!--                    注： v-model的值为当前被选中的el-option的 value 属性值-->
                  </el-select>
                </el-form-item>
                <el-form-item label="发行商">
                  <el-select v-model="tableData[row_index].publisher" placeholder="请选择游戏发行商" >
                    <el-option v-for="(item,index) in publishers" :key="index":value="item"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="游戏类型">
                  <el-checkbox-group v-for="(item,index) in tableData.gameType" v-model="tableData[row_index].type" style="display: inline" :key="index">
                    <el-checkbox :label="item" name="type" class="form_type_checkbox"></el-checkbox>
                  </el-checkbox-group>
                </el-form-item>
                <el-form-item label="描述">
                  <el-input type="textarea" v-model="tableData.desc"></el-input>
                </el-form-item>
                <div style="text-align: center">
                  <el-button type="primary" @click="test(scope)">保存</el-button>
                  <el-button>重置</el-button>
                </div>
              </el-form>
              <el-button slot="reference" type="text" size="small" >修改</el-button>
            </el-popover>
            <el-button @click="deleteRow(scope.$index,scope.row)" type="text" size="small">删除</el-button>
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
  </div>
</template>

<script>
  export default {
    name: "DeleteProduct",
    data() {
      return {
        row_index: 0,
        //是否开启加载动画
        loading: false,
        developers:[],
        publishers: [],
        types:[],
        // 储存table中的数据信息
        tableData: [],
        showTable: false,
        //储存搜索表单中的信息
        listQuery: {
          name: '',
          type: [],
          publisher: '',
          developer: '',
        },
        //储存临时搜索表单中的信息
        tempList: {
          name: '',
          type: [],
          publisher: '',
          developer: '',
          page: 0,
          size: 3,
        },
        currentPage: 0
      }
    },
    methods: {
      /* 搜索框 */
      handleSearchList() {
        //展开表格
        this.showTable = true;
        this.loading = true;
        this.listQuery = this.tempList;
        var _this = this;
        console.log(this.listQuery);
        this.axios.post('/product/searchProduct',{
          product: _this.listQuery
        }).then(function (response) {
          _this.tableData = response.data;
          console.log({handleSearchList:response});
        })
          .catch(function (error) {
            console.log(error);
          });
        this.loading = false;
      },
      handleResetSearch() {
        this.tempList.name='';
        this.tempList.type=[];
        this.tempList.publisher='';
        this.tempList.developer='';
      },
      init(){
        this.getTypes();
        this.getPublishers();
        this.getDevelopers();
      },
      getTypes() {
        let _this = this;
        this.axios.get("product/getAllTypes")
          .then(body => {
            _this.types = body.data;
            console.log('type:'+_this.types);
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
            console.log(_this.publishers);
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
      /* 搜索框 */

      /* table表格 */
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

      //获取触发鼠标点击事件的该行行数
      changeRowIndex(row){
        // console.log(row);
        this.row_index = row.id;
        console.log({rowData: this.tableData[this.row_index]});
      },
      handleAddProduct() {
        this.$router.push({path:'/home/addProduct'});
      },
      // 分页
      handleCurrentChange(val) {
        this.tempList.page = val-1;
        this.handleSearchList();
        console.log('当前页:'+val);
      },
      deleteBox: function (row) {

        this.$confirm('确认是否删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // debugger
          console.log(row.name)
          let msg = deleteRow(row.name);
          this.$message({
            type: 'success',
            message: '已删除'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });

      },
      deleteRow: function(index,row){
        let msg = '';
        let _this = this;
        this.axios.post('/product/deleteOneByName',{
          product: row
        }).then(function (response) {
          if (response.data === '') {
            _this.$message({
              message: '删除成功',
              type: 'success'
            });
          }else {
            _this.$message.error(response.data)
          }
          _this.row.splice(index);
          console.log(msg)
        })
          .catch(function (error) {
            console.log(error);
          });
        return msg;
      }
      /* table 表格 */
    },
    mounted() {
      this.init();
    }
  }
</script>

<style scoped>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
  .page {
    text-align: center;
    padding-top: 20px;
  }
</style>
