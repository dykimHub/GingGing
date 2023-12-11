<template>
    <div class="container-all">
        <div id="body-pd">
            <div class="l-navbar" id="navbar">
                <nav class="nav">
                    <div class="nav__brand">
                        <div v-if="checkLogin">
                            <router-link :to="{ name: 'hello' }" class="nav__logo">
                                <h2 class="title">GINGGING</h2>
                            </router-link >
                        </div>
                        <div v-else>
                            <router-link :to="{ name: 'home' }" class="nav__logo">
                                <h2 class="title">GINGGING</h2>
                            </router-link >
                        </div>
                        <br/>
                        <div class="social-menu">
                            <ul>
                                <li>
                                    <a href="https://github.com/djsumin?tab=repositories" target="blank">
                                        <i class="fab fa-github"></i >
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="nav__list">
                        <div v-if="checkLogin">
                            <WeatherBar/>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</template>

<script setup>
import { RouterLink } from "vue-router";
import { useUserStore } from "../../stores/user";
import { computed } from "vue";
import WeatherBar from "./WeatherBar.vue";

const store = useUserStore();

const checkLogin = computed(() => {
    return store.checkLogin;
});

</script>

<style scoped>
ul {
    margin: 0;
    padding: 0;
    list-style: none;
}

a {
    text-decoration: none;
}
.nav {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  overflow: hidden;
}
.l-navbar {
    position: fixed;
    top: 0;
    left: 0;
    width: var(--nav--width);
    height: 100vh;
    background-color: var(--bg-color);
    color: var(--white-color);
    padding: 1.5rem 1.5rem 2rem;
    transition: 0.5s;
    z-index: var(--z-fixed);
}
.nav__brand {
    display: grid;
    grid-template-columns: max-content max-content;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 2rem;
}
.nav__logo {
    color: var(--white-color);
    font-weight: 600;
}
.container-all {
    font-family: "GmarketSansMedium";
    margin-right: 0;
}
.title:hover {
    color: rgb(91, 109, 211);
    cursor: pointer;
    font-weight: 2000px;
}
.social-menu ul li {
    list-style: none;
    margin: 0 15px;
}
.social-menu ul li .fab {
    font-size: 30px;
    line-height: 60px;
    transition: 0.3s;
    color: #000;
}
.social-menu ul li .fab:hover {
    color: #fff;
}
.social-menu ul li a {
    position: relative;
    display: block;
    width: 60px;
    height: 60px;
    border-radius: 50%;
    background-color: #fff;
    text-align: center;
    transition: 0.6s;
    box-shadow: 0 5px 4px rgba(0, 0, 0, 0.5);
}
.social-menu ul li a:hover {
    transform: translate(0, -10%);
}
.social-menu ul li:nth-child(1) a:hover {
    background-color: rgba(0, 0, 0, 0.829);
}
</style>