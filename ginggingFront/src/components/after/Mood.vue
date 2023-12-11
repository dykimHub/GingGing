<template>
    <div>
        <div class="container">
            <img
                class="emoji"
                src="@/assets/emotions/happy1.png"
                style="width: 100px"
                @click="goNext('기뻐요')"/>
            <img
                class="emoji"
                src="@/assets/emotions/happy2.png"
                style="width: 100px"
                @click="goNext('신나요')"/>
            <img
                class="emoji"
                src="@/assets/emotions/angry.png"
                style="width: 100px"
                @click="goNext('화나요')"/>
            <img
                class="emoji"
                src="@/assets/emotions/happy3.png"
                style="width: 100px"
                @click="goNext('행복해요')"/>
            <h2>운동을 하면서 느낀</h2>
            <h2>당신의 감정을 선택해주세요</h2>
            <img
                class="emoji"
                src="@/assets/emotions/hmm1.png"
                style="width: 100px"
                @click="goNext('그냥 그래요')"/>
            <img
                class="emoji"
                src="@/assets/emotions/sleeping.webp"
                style="width: 100px"
                @click="goNext('졸려요')"/>
            <img
                class="emoji"
                src="@/assets/emotions/smile1.png"
                style="width: 100px"
                @click="goNext('웃음이 나요')"/>
            <img
                class="emoji"
                src="@/assets/emotions/sad1.png"
                style="width: 100px"
                @click="goNext('슬퍼요')"/>
        </div>
    </div>
</template>

<script setup>
import router from "../../router";
import { usePosterStore } from "../../stores/poster";

const store = usePosterStore();
const prop = defineProps(["jog_id"]);

const goNext = function (mood_id) {
    if (mood_id) {
        store.poster.mood = mood_id;
        //jog_id 추가
        store.poster.jog_id = prop.jog_id;
        router.push({name: "weather"});
    }
};
</script>

<style scoped>
h2 {
    margin: 3%;
}
.emoji {
    margin: 7%;
}
</style>