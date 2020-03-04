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
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div>
      <el-table
        :data="gameData"
        style="width: 100%"
        :row-class-name="tableRowClassName"
        @row-click="changeRowIndex"	>
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
          prop="desc"
          label="描述">
        </el-table-column>

        <!--    游戏信息修改      -->
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-popover
              placement="right"
              width="100%"
              trigger="click">
              <el-form ref="form" :model="gameData[row_index]" label-width="80px">
                <el-form-item label="游戏名称">
                  <el-input v-model="gameData[row_index].name"></el-input>
                </el-form-item>
                <el-form-item label="开发商">
                  <el-select v-model="gameData[row_index].developer" placeholder="请选择游戏开发商" >
                    <el-option v-for="(item,index) in developers" :key="index" :label="item" :value="item"></el-option>
<!--                    注： v-model的值为当前被选中的el-option的 value 属性值-->
                  </el-select>
                </el-form-item>
                <el-form-item label="发行商">
                  <el-select v-model="gameData[row_index].publisher" placeholder="请选择游戏发行商" >
                    <el-option v-for="(item,index) in publishers" :key="index" :label="item" :value="item"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="游戏类型">
                  <el-checkbox-group v-for="(item,index) in gameData.gameType" v-model="gameData[row_index].type" style="display: inline" :key="index">
                    <el-checkbox :label="item" name="type" class="form_type_checkbox"></el-checkbox>
                  </el-checkbox-group>
                </el-form-item>
                <el-form-item label="描述">
                  <el-input type="textarea" v-model="gameData.desc"></el-input>
                </el-form-item>
                <div style="text-align: center">
                  <el-button type="primary" @click="test(scope)">保存</el-button>
                  <el-button>重置</el-button>
                </div>
              </el-form>
              <el-button slot="reference" type="text" size="small" >修改</el-button>
            </el-popover>
            <el-button @click="handleClick(scope.row)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="page">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="1000">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  export default {
    name: "DeleteProduct",
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
        developers:['索尼','微软','卡普空'],
        publishers: ['发行商1','发行商2','发行商3','发行商4'],
        gameData:[
          {
            name: '游戏名1',
            type: '游戏类型1',
            publisher: '发行商1',
            developer: '索尼',
            delivery: false,
            desc: '的法律杰拉德开始放假啊看来是的放假啊算了答复发大水发大水'
          },
          {
            name: '游戏名2',
            type: '游戏类型2',
            publisher: '发行商2',
            developer: '微软',
            delivery: false,
            desc: '放大发来的卡就是分厘卡圣诞节发的看来是'
          },
          {
            name: '游戏名3',
            type: '游戏类型3',
            publisher: '发行商3',
            developer: '卡普空',
            delivery: false,
            desc: '恶趣味软件客气多发点发起为人人'
          },
          {
            name: '游戏名4',
            type: '游戏类型4',
            publisher: '发行商4',
            developer: '卡普空',
            delivery: false,
            desc: '请问人家为了抢救提欧哦啊是'
          }
        ],
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
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
      handleClick(row) {
        console.log(row);
      },
      //获取触发鼠标点击事件的该行行数
      changeRowIndex(row){
        // console.log(row);
        this.row_index = row.id;
        console.log(this.gameData[this.row_index]);
      },
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
