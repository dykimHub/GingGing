<template>
    <div class="body">
        <header>
            <div class="container">
                <h1 class="title">아프지말고 오래오래 깅깅하자</h1>
            </div>
            <div class="welcome">
                <span v-if="checkLogin">
                    {{ userName }}
                    님 안녕하세요.😍</span>
                <span v-else>
                    로그인을 진행해주세요.
                </span>
            </div>
            <SideNavBar/>
        </header>

        <div class="main">
            <SideBar/>
        </div>

        <footer>
            ©GINGGING. All Rights Reserved
        </footer>
    </div>
    <link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"/>
</template>

<script setup>
import SideBar from "./components/common/SideBar.vue";
import { useUserStore } from "@/stores/user";
import { computed } from "vue";
import SideNavBar from "./components/common/SideNavBar.vue";

const store = useUserStore();
const checkLogin = computed(() => {
    return store.checkLogin;
});

const userName = computed(() => {
    if (checkLogin.value) {
        return localStorage.getItem("id");
    } else {
        return "";
    }
});
</script>

<style scoped>
.body,
template {
    height: 100px;
}
h2 {
    font-family: "GmarketSansMedium";
}
header {
    margin: 3%;
}
.welcome {
    margin-bottom: 5px;
}
.body {
    display: flex;
    flex-direction: column;
    text-align: center;
    margin: 0;
    font-family: "GmarketSansMedium";
}
.main {
    flex: 1;
}

span {
    font-size: 25px;
}
.container {
    max-width: 960px;
    margin: 0 auto;
    padding: 20px;
    text-align: center;
}
.title:hover {
    color: rgb(41, 57, 146);
    cursor: pointer;
    font-weight: 2000px;
}
h1.title {
    position: relative;
}
h1.title:after,
h1.title:before {
    content: "[";
    display: inline-block;
    position: relative;
    top: 1px;
    height: 100%;
    font-size: 1.25em;
    color: tomato;

    transition: all 0.5s ease;
}
h1.title:after {
    content: "]";
}
h1.title:hover:before {
    transform: translateX(-10px);
}
h1.title:hover:after {
    transform: translateX(10px);
}

footer {
    bottom: 0;
    position: fixed;
}

/*전체적용폰트*/
@font-face {
    font-family: "GmarketSansMedium";
    src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff") format("woff");
    font-weight: normal;
    font-style: normal;
}

/*일기적용폰트*/
@font-face {
    font-family: "UhBeeSe_hyun";
    src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_five@.2.0/UhBeeSe_hyun.woff") format("woff");
    font-weight: normal;
    font-style: normal;
}
</style>