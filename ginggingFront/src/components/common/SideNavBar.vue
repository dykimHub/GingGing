<template>
    <div class="container-all">
        <div id="body-pd">
            <div v-if="checkLogin">
                <div class="navs">
                    <div class="nav__link">
                        <router-link :to="{ name: 'myPage', params: { user_id: user_id } }">
                            <a href="#" class="nav__link active">
                                <h3><i class="bi bi-person-lines-fill"></i></h3>
                            </a>
                        </router-link>
                    </div>
                    <div class="nav__link">
                        <router-link :to="{ name: 'myPoster', params: { user_id: user_id } }">
                            <a href="#" class="nav__link active">
                                <h3><i class="bi bi-person-badge"></i></h3>
                            </a>
                        </router-link>
                    </div>
                    <div @click="logout" class="nav__link">
                        <a href="#" class="nav__link active">
                            <h3><i class="bi bi-person-dash"></i></h3>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <RouterView/>
    </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { useUserStore } from "../../stores/user";
import { ref, computed, watch, onMounted } from "vue";

const store = useUserStore();
const router = useRouter();

const logout = function () {
    store.logout();
    user_id.value = null
    router.push({name: "afterLogout"});
};

const checkLogin = computed(() => {
    return store.checkLogin;
});

const user_id = computed(() => localStorage.getItem('user_id'));
</script>

<style scoped>
a {
    text-decoration: none;
}
.navs {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
}
.nav__link {
    display: flex;
    grid-template-columns: max-content max-content;
    align-items: center;
    padding: 0.2rem;
    color: var(--white-color);
    cursor: pointer;
} 
.nav__link:hover {
    background-color: var(--first-color);
}
.active {
    background-color: var(--first-color);
}
.container-all {
    font-family: "GmarketSansMedium";
    margin-right: 0;
}
</style>