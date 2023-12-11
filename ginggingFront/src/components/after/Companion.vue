<template>
<div>
    <h2>누구랑 같이 운동하셨나요</h2>
    <input id="input" type="text" v-model="companion" placeholder="조깅 메이트를 입력해주세요"/>
    <span>와(과) 운동했어요</span><br/>
    <button @click="goNext()" class="submit-btn">입력</button>
</div>
</template>

<script setup>
import router from "../../router";
import {usePosterStore} from "../../stores/poster";

const store = usePosterStore();

let companion = "";

const goNext = function () {
    store.poster.companion = companion;
    router.push({name: "journal"});
};
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
</style>