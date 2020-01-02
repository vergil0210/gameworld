<template >
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      // backgroundDiv: {
      //   backgroundImage:'url(' + require('./assets/img/background.jpg') + ')',
      //   backgroundRepeat:'no-repeat',
      //   backgroundSize:'100% 100%'
      // },
    }
  },
}
</script>

<style scope>
#app {
  background: url('./assets/img/background.jpg');
  background-size: 100% 100%;
  height: 100%;
  position: fixed;
  width: 100%;
  right: 0px;
  top: 0px;
}

</style>
