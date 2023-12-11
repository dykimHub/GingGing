<template>
    <div>
        <img
            class="emoji"
            src="@/assets/weathers/sunny.png"
            style="width: 100px"
            @click="goNext('맑음')"/>
        <img
            class="emoji"
            src="@/assets/weathers/cloudy.png"
            style="width: 100px"
            @click="goNext('흐림')"/>
        <img
            class="emoji"
            src="@/assets/weathers/foggy.png"
            style="width: 100px"
            @click="goNext('안개')"/>

        <h2>운동 중 날씨는 어땠나요?</h2>

        <img
            class="emoji"
            src="@/assets/weathers/rainy.png"
            style="width: 100px"
            @click="goNext('비')"/>
        <img
            class="emoji"
            src="@/assets/weathers/snowy.png"
            style="width: 100px"
            @click="goNext('눈')"/>
        <img
            class="emoji"
            src="@/assets/weathers/thunder.png"
            style="width: 100px"
            @click="goNext('천둥번개')"/>
        <br/>
    </div>
</template>

<script setup>
import router from "../../router";
import { usePosterStore } from "../../stores/poster";

const store = usePosterStore();

const goNext = function (weather_id) {
    if (weather_id) {
        store.poster.weather = weather_id;
        router.push({name: "music"});
    } 
};
</script>

<style scoped>
.emoji {
    margin: 7%;
}
</style>