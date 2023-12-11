<template>
<div>
    <h2>운동 중 어떤 음악을 들으셨나요?</h2>

    <div>
        <input id="input" type="text" v-model="singer" placeholder="가수를 입력해주세요"/>
        <span>의</span><br/>
        <input type="text" v-model="song" placeholder="노래제목을 입력해주세요"/>
        <span>를 들었습니다</span><br/>
        <button type="button" @click="goNext()" class="submit-btn">입력</button>
    </div>
    <div class="music-chart">
        <h2>TOP 25</h2>
        <!--재생목록이 들어갈 위치-->
        <div>
            <div id="slider">
                <div class="image-box">
                    <div v-for="video in store.getVideos" :key="video.id">
                        <div>
                            <img :src="video.snippet.thumbnails.default.url" alt="비디오섬네일"/>
                        </div>
                    </div>
                    <div v-for="video in store.getVideos" :key="video.id">
                        <div class="clone">
                            <img :src="video.snippet.thumbnails.default.url" alt="비디오섬네일"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script setup>
import router from "../../router";
import { usePosterStore } from "../../stores/poster";
import { ref } from "vue";

const store = usePosterStore();

const song = ref();
const singer = ref();

const goNext = function () {
    store.poster.song = singer.value + " " + song.value
    router.push({name: "companion"});
};
store.youtubeList();
</script>

<style scoped>
input {
    width: 300px;
    height: 50px;
    font-size: 15px;
    border: 0;
    border-radius: 15px;
    outline: none;
    padding-left: 10px;
    background-color: rgb(233, 233, 233);
    margin-top: 5px;
}

span {
    font-size: 25px;
}

button {
    margin-top: 2%;
    font-size: 25px;
    border-radius: 10px;
    border: none;
    padding-right: 3%;
    padding-left: 3%;
}

button:hover {
    background-color: lightskyblue;
}
.submit-btn:active {
    background-color: lightblue;
}

.music-chart {
    margin-top: 10%;
}

#slider {
    overflow: hidden;
    position: relative;
    /* width: 100px; */
    max-width: 1440px;
    margin: 0 auto;
    height: 300px;
}
.image-box {
    width: 5280px;
    height: 100%;
    display: flex;
    flex-wrap: nowrap;
    animation: bannermove 40s linear infinite;
}

img {
    width: 350px;
    height: 250px;
}

@keyframes bannermove {
    0% {
        transform: translate(0, 0);
    }
    100% {
        transform: translate(-50%, 0);
    }
}
</style>