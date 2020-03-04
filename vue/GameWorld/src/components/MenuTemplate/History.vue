<template>
  <div>
    <div>
      <h2 class="module_name">折扣商品</h2>
      <div class="content background">
        <div class="tab_content">
          <div v-for="(item,index) in photo" :key="index">
            <div>
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
    name: "History",
    data() {
      return {

      }
    }

  }
</script>

<style scoped>
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
    padding: 5px 0px;
  }
  .tab_item_name {
    color: #c7d5e0;
    font-size: 1.25em;
    line-height: 18px;
    text-overflow: ellipsis;
    white-space: nowrap;
    display: block;
    overflow: hidden;
    transition: color 0.25s;
    padding: 10px 5px;
  }
  .tab_item_type {
    height: 20px;
    white-space: normal;
    overflow: hidden;
    padding: 0px 5px;
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
