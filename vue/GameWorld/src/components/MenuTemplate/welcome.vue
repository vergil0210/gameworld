<template>
  <div>
    <div class="background">
      <el-carousel  :interval="4000"  class="carouselCss">
        <el-carousel-item v-for="(item,index) in photo" :key="index" >
          <h3>展示图片</h3>
          <el-image :src="item.path" fit="fill" class="photoDiv1"></el-image>
        </el-carousel-item>
      </el-carousel>
    </div>
    <div>
      <h2 class="module_name">热销游戏</h2>
      <div class="background">
        <div class="gameDiv1 ba" v-for="(item,index) in photo" :key="index">
          <div class="photoList">
            <el-image :src="item.path" fit="cover" class="photoDiv2"></el-image>
            <el-tag v-if="item.disc!=1"  effect="dark">￥{{item.price}}</el-tag>
            <span>价格</span>
            <el-tag type="success" effect="dark" style="color: #4e6306;" size="medium ">-50%</el-tag>
            &nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;
            <span>游戏名</span>
          </div>
        </div>
      </div>
      <h2 class="module_name">折扣商品</h2>
      <div class="content background">
        <div class="tab_content">
        <div v-for="(item,index) in photo" :key="index">
          <div @mouseover="">
<!--            <a>-->
              <div class="tab_item">
                <div class="tab_item_img">
                  <el-image :src="item.path" fit="cover" class="photoDiv3"></el-image>
                </div>
                <div class="tab_item_name">{{item.name}}</div>
                <div class="tab_item_type">{{item.type}}</div>
                <div class="tab_item_discount">
                  <div class="discount_pct">-{{(1-item.disc)*100}}%</div>
                  <div class="discount_price">
                    <div class="discount_original_price">{{item.price}}</div>
                    <div class="discount_final_price">{{item.price*item.disc}}</div>
                  </div>
                </div>
              </div>
<!--            </a>-->
          </div>
        </div>
      </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      photo : [
        {path: require('../../assets/img/展示图片1.jpg'), name: '绝地武士' , disc: 1, price: 200 ,type: 'nmsl'},
        {path: require('../../assets/img/展示图片2.jpg'), name: '怪物猎人：冰原', disc: 0.2, price: 250, type: 'nmsl'},
        {path: require('../../assets/img/展示图片3.jpg'), name: '人类必须死', disc: 1, price: 300, type: 'nmsl'},
        {path: require('../../assets/img/展示图片4.jpg'), name: '攻城', disc: 0.5, price: 260, type: 'nmsl'},
        {path: require('../../assets/img/展示图片5.jpg'), name: '打飞机', disc: 0.3, price: 150, type: 'nmsl'},
      ]
    }
  }
}
</script>

<style scope>
  .background {
    background:#13131357;
  }
  .photoDiv1 {
    width: 600px;
    height: 350px;
  }
  .carouselCss {
    width: 600px;
    height: 370px;
    margin: auto 200px;
    padding: 20px;
  }
  .gameDiv1 {

    display:inline-block;
  }
  .photoList {
    width: 300px;
    height: 200px;
    /*设置颜色渐变*/
    background: linear-gradient(to right,#31516d,#407999);
  }
  .photoDiv2 {
    width: 300px;
    height: 165px;
  }
  .module_name {
    color: white;
  }
  .content {
    width: 100%;
  }
  .tab_content {
    width: 60%;
  }
  img {
    border: none;
  }
  .photoDiv3 {
    width: 184px;
    height: 60px;
  }
  .tab_item {
    position: relative;
    display: block;
    background: #202d39;
    /*background: linear-gradient(to right,#31516d,#407999);*/
    height: 69px;
    margin-bottom: 5px;
    padding-left: 198px;
    text-size-adjust: none;
    -webkit-text-size-adjust: none;
  }
  .tab_item_img {
    position: absolute;
    left: 0;
    top: 0;
    z-index: 3;
    line-height: 69px;
    transition: opacity 0.25s;
  }
  .tab_item_type {
    height: 20px;
    white-space: normal;
    overflow: hidden;
  }
  .tab_item_discount {
    position: relative;
    display: block;
    float: right;
    margin-right: 16px;
    background: none;
    margin-top: -23px;
    width: 120px;
    text-align: right;
  }
  .discount_price {
    display: inline-block;
    vertical-align: bottom;
    text-align: right;
  }
  .discount_pct {
    display: block;
    float: left;
    font-size: 14px;
    line-height: 18px;
    padding: 0 4px;
    margin-top: 8px;
    border-radius: 1px;
    color: #a4d007;
    background: #4c6b22;
  }
  .discount_original_price {
    text-decoration: line-through;
    font-size: 11px;
    font-family: Tahoma, Arial, Helvetica, sans-serif;
  }
  .discount_final_price {
    font-size: 13px;
    font-family: Tahoma, Arial, Helvetica, sans-serif;
  }

</style>
