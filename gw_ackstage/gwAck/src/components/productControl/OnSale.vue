<template>
    <div>
      <div>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-form-item label="游戏名称">
            <el-input v-model="formInline.name" placeholder="游戏名称"></el-input>
          </el-form-item>
          <el-form-item label="游戏类型">
            <el-select v-model="formInline.type" placeholder="游戏类型">
              <el-option v-for="(item,index) in formList.type" :key="index" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" >查询</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div>
        <el-table
          :data="gameData"
          style="width: 100%"
          :row-class-name="tableRowClassName"
          >
          <el-table-column
            prop="name"
            label="游戏名"
            width="180">
          </el-table-column>
          <el-table-column
            prop="type"
            label="游戏类型"
            width="100px">
          </el-table-column>
          <el-table-column
            prop="publisher"
            label="发行商"
            width="100px">
          </el-table-column>
          <el-table-column
            prop="developer"
            label="开发商"
            width="100px">
          </el-table-column>
          <el-table-column
            prop="onSaleTime"
            label="上线时间"
            width="250px">
            <template slot-scope="scope">
              <el-date-picker
                v-model="scope.row.onSaleTime"
                type="date"
                placeholder="选择日期"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </template>
          </el-table-column>
          <el-table-column
            prop="isSale"
            label="是否上线">
            <template slot-scope="scope">
              <el-switch v-model="scope.row.isOnSale" @change=change(scope.$index,scope.row)></el-switch>
            </template>
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small">保存</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
</template>

<script>
  export default {
    name: "OnSale",
    data() {
      return {
        formInline: {
          name: '',
          type: ''
        },
        formList: {
          name: '',
          type: ['动作','冒险','益智','策略']
        },
        row_index: 0,
        gameData:[
          {
            name: '游戏名1',
            type: '游戏类型1',
            publisher: '发行商1',
            developer: '索尼',
            onSaleTime: '2020-02-05',
            isOnSale: false,
          },
          {
            name: '游戏名2',
            type: '游戏类型2',
            publisher: '发行商2',
            developer: '微软',
            onSaleTime: '2020-02-05',
            isOnSale: false,
          },
          {
            name: '游戏名3',
            type: '游戏类型3',
            publisher: '发行商3',
            developer: '卡普空',
            onSaleTime: '2020-02-05',
            isOnSale: false,
          },
          {
            name: '游戏名4',
            type: '游戏类型4',
            publisher: '发行商4',
            developer: '卡普空',
            onSaleTime: '2020-02-05',
            isOnSale: false,
          }
        ],
      }
    },
    methods: {
      tableRowClassName({row, rowIndex}) {
        row.id = rowIndex;
        if (rowIndex === 1) {
          return 'warning-row';
        } else if (rowIndex === 3) {
          return 'success-row';
        }
        return '';
      },
      changeRowIndex(row){
        // console.log(row);
        this.row_index = row.id;
        console.log(this.gameData[this.row_index]);
      }
    }
  }
</script>

<style scoped>

</style>
