<template>
<div v-if="isLoading">Loading...</div>
<div v-else>
    <div v-if="userPoster">
        <div class="poster-container" v-for="pos in userPoster" :key="pos.id">
            <div>
                <button
                    type="button"
                    class="btn btn-danger"
                    @click="deletePoster(pos.poster_id)"
                    style="margin-left: 750px;">삭제
                </button>
            </div>
            <img src="@/assets/poster.png" style="width: 800px"/>
            <div class="diary">
                <div class="header">
                    <h1 class="date">{{ formatDate(pos.reg_date) }}</h1>
                    <h1 class="weather">{{ pos.weather }}</h1>
                </div>
                <img
                    v-if="pos.user_photo"
                    :src="`/src/assets/${pos.user_org_photo}`"
                    style="width: 300px"/>
                <h1 class="article">
                    오늘 기분은
                    {{ pos.mood }}
                    <br/>
                    {{ pos.song }}를 들었어요.
                    <br/>
                    룰루~ 오늘의 운동메이트는 바로
                    {{ pos.companion }}!!
                    <br/>
                    오늘운동의 한줄평은
                    {{ pos.review }}
                </h1>
            </div>
        </div>
    </div>
    <div v-else>No data available</div>
</div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useJoggingStore } from "@/stores/jogging";

const store = useJoggingStore();
const prop = defineProps(["user_id"]);

const userPoster = ref(null);
const isLoading = ref(true);

const fetchUserPoster = async () => {
    try {
        const userId = prop.user_id;
        const response = await axios.get(
            `http://localhost:8080/api/poster/user/${userId}`
        );
        userPoster.value = response.data;
    } catch (error) {
        console.error("사용자 포스터를 가져오는 중 에러 발생:", error);
    } finally {
        isLoading.value = false;
    }
};

onMounted(() => {
    fetchUserPoster();
});

const formatDate = (dateString) => {
    const date = new Date(dateString);
    return `${date.getFullYear()}-${ (date.getMonth() + 1)
        .toString()
        .padStart(2, "0")}-${date
        .getDate()
        .toString()
        .padStart(2, "0")}`;
};

const deletePoster = async (poster_id) => {
    await store.deletePoster(poster_id);
    fetchUserPoster(); // 포스터 삭제 후 다시 가져오기
}

</script>

<style scoped>
.poster-container {
    position: relative;
    margin-bottom: 100px;
}
.diary {
    position: absolute;
    top: 45%;
    left: 50%;
    transform: translate(-50%, -50%);
    font-family: "UhBeeSe_hyun";
}
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.date,
.weather {
    margin: 0;
    position: relative;
    bottom: 115px;
    font-size: 25px;
}
.weather {
    right: -100px;
}
.article {
    position: relative;
    top: 100px;
    font-size: 25px;
    line-height: 2.5rem;
}
</style>