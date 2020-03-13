<template>
    <div>
      <div style="padding-bottom: 30px">
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
      <div>
        <el-card shadow="never">
          <el-table
          :data="tableData"
          ref="multipleTable"
          style="width: 100%"
          :row-class-name="tableRowClassName"
          @selection-change="handleSelectionChange"
          border>
            <el-table-column type="selection" width="60" align="center"></el-table-column>
            <el-table-column
              prop="name"
              label="游戏名"
              width="180"
              align="center">
            </el-table-column>
            <el-table-column
              prop="publisher"
              label="发行商"
              width="100px"
              align="center">
            </el-table-column>
            <el-table-column
              prop="developer"
              label="开发商"
              width="100px"
              align="center">
            </el-table-column>
            <el-table-column
              prop="publishDate"
              label="上线时间"
              width="250px"
              align="center">
              <template slot-scope="scope">
                <el-date-picker
                  v-model="scope.row.publishDate"
                  type="date"
                  placeholder="选择日期"
                  format="yyyy 年 MM 月 dd 日"
                  value-format="yyyy-MM-dd hh:mm:ss">
                </el-date-picker>
              </template>
            </el-table-column>
            <el-table-column
              label="是否上线"
              align="center">
              <template slot-scope="scope">
                <el-switch v-model="scope.row.delivery" ></el-switch>
              </template>
            </el-table-column>
            <el-table-column
              label="操作"
              align="center">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">保存</el-button>
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
          <div class="selectButton">
            <el-button @click="toggleSelection([tableData[1], tableData[2]])">保存</el-button>
            <el-button @click="toggleSelection()">取消选择</el-button>
          </div>
        </el-card>
      </div>
    </div>
</template>

<script>
  export default {
    name: "OnSale",
    data() {
      return {
        row_index: 0,
        publishers:[],
        developers: [],
        types: [],
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
          size: 5,
        },
        //是否开启加载动画
        loading: false,
        // 储存table中的数据信息
        tableData: [],
        showTable: false,
        //储存搜索表单中的信息
        currentPage: 0,
        multipleSelection: [],
        stopToggleSelection: false
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

      tableRowClassName({row, rowIndex}) {
        row.id = rowIndex;
        if (rowIndex === 1) {
          return 'warning-row';
        } else if (rowIndex === 3) {
          return 'success-row';
        }
        return '';
      },
      changeRowIndex(row) {
        // console.log(row);
        this.row_index = row.id;
        console.log(this.gameData[this.row_index]);
      },
      toggleSelection(rows){
        this.stopToggleSelection = false;
        // this.$refs.multipleTable.toggleRowSelection(row); 改变选矿的选择状态
        console.log(this.multipleSelection)
        let _this = this;
        if (rows) {
          this.multipleSelection.forEach(selection => {
            //如果指定上线，必须同时确认发行日期，否则告警提示
            if (selection.delivery && selection.publishDate==null){
              this.$message.error('请确认上线日期！');
              _this.stopToggleSelection = true
            }
          });

          if (this.stopToggleSelection==true){
            return;
          }
          console.log("pass check")
          this.axios.post('/product/updateProduct',
            _this.multipleSelection
          ).then(function (response) {
            _this.$message({
              type: 'success',
              message: '修改成功！'
            })
          })
            .catch(function (error) {
              _this.$message({
                type: 'warning',
                message: '对不起，修改失败。如核对修改信息无误，请联系管理员'
              })
            });
          // rows.forEach(row => {
          //
          // });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      // 分页
      handleCurrentChange(val) {
        this.tempList.page = val-1;
        this.handleSearchList();
        console.log('当前页:'+val);
      },
    },
    mounted() {
      this.init();
    }
  }
</script>

<style scoped>
  .page {
    text-align: center;
    padding-top: 20px;
  }
  .selectButton {
    text-align: left;
  }
</style>
