<template>
  <div class="video">
      <div id="backgd"></div>
    <video controls>
        <source src="../../assets/video/btc.mp4" type="video/mp4">
        Your browser does not support the video tag.
    </video>
  </div>
</template>

<script>

export default {

    name: 'btcvideo',
    created()
    {
        this.$parent.watchParty=true;
    },
    beforeDestroy()
    {
        this.$parent.watchParty=false;
    }

}
</script>

<style scoped>
#backgd
{
    background-color: #000;
}


.video
{
    display: flex;
    justify-content: center;
}

video
{
    z-index: -1;
    position: fixed;
    max-width: 100vw;
    max-height: 100vh;
    outline: none;

}
source
{
    max-width: 100vw;
    max-height: 100vh;
    width: 100%;
}
</style>