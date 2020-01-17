<template>
  <div>
    <div style="background:#13131357">
        <el-carousel  :interval="4000"  class="carouselCss">
            <el-carousel-item v-for="(item,index) in photo" :key="index" >
                <el-image :src="item.path" fit="cover" style="width:600px;height:330px"></el-image>
            </el-carousel-item>
        </el-carousel>
    </div>
    <div>
        <h2 style="color:white">特别优惠</h2>
        <!-- <table>
            <tr v-for="(item,index) in photo" :key="index">
                <td>
                    <div class="tableCss">
                        <el-image :src="item.path" fit="cover" style="width:400px;height:220px"></el-image>
                        <el-tag type="success" effect="dark" style="color: #4e6306;" size="medium ">-50%</el-tag>
                    </div>
                </td>
                <td>
                    
                </td>
            </tr>
        </table> -->
        <div style="background:#13131357">
            <el-row :gutter="20" v-for="(item,index) in photo" :key="index">
                <el-col :span="8" v-for="cols in 3" :key="cols">
                    <div class="tableCss">
                        <el-image :src="item.path" fit="cover" style="width:300px;height:165px"></el-image>
                        <el-tag type="success" effect="dark" style="color: #4e6306;" size="medium ">-50%</el-tag>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scope>
.carouselCss {
    width:600px;
    height:330px;
    margin: auto 200px;
    padding: 20px;
}
</style>