<template>
  <div>
    <Login />
  </div>
</template>

<script setup>
import Login from "../components/Login.vue";
</script>

<style scoped>
h1 {
  text-align: center;
}
img {
  width: 500px;
}

.img-container {
  display: flex;
  justify-content: center;
}

@font-face {
  font-family: "GmarketSansMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
</style>
