<template>
    <div class="lets">
        <h1>반갑습니다</h1>
        <h1>깅깅이와 함께 조깅해볼까요?</h1>
        <div class="container">
            <router-link :to="{ name: 'beforeJogging' }">LETS GING</router-link>
        </div>
        <div class="image">
            <img
                src="@/assets/BackGround/running.jpg"
                class="background"
                style="height: 700px"/>
        </div>
    </div>
</template>

<script setup>
</script>

<style scoped>
a {
    top: 50%;
    background-color: #0a0a23;
    color: #fff;
    border: none;
    border-radius: 10px;
    text-decoration: none;
    padding: 2%;
    transition: background-color 0.3s ease;
}

a:hover {
    background-color: #56568d;
}
.container {
    margin-top: 2%;
}
.lets {
    padding: 10px 0 30px;
    position: relative;
}
.image {
    margin-top: 50px;
}
</style>