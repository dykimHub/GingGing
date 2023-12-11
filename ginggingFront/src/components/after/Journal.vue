<template>
    <div>
        <div class="flex-container">
            <div class="container_review">
                <h2>한 줄 일기를 작성해주세요</h2>
                <input id="input" type="text" v-model="review" placeholder="오늘 나의 조깅은.."/><br/>
                <button @click="write">입력</button>
            </div>
            <div class="container_photo">
                <h2>오늘 조깅 중에 찍은 사진을 첨부해주세요</h2>
                <div>
                    <form ref="posterForm">
                        <div class="file-container">
                            <input
                                type="file"
                                id="user_photo"
                                name="user_photo"
                                accept="image/*"
                                ref="fileInput"
                                v-on="selectFile"
                                @change="handleFileUpload"/>
                        </div>
                    </form>
                </div>
                <button @click.prevent="createPoster">입력</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { usePosterStore } from "../../stores/poster";
import { ref } from "vue";

const store = usePosterStore();

let review = "";
const fileInput = ref("");

const write = function () {
    store.poster.review = review;
};

const handleFileUpload = function (event) {
    const file = event
        .target
        .files[0];
    store.poster.user_photo = file;
};

const selectFile = () => {
    store.poster.user_photo = fileInput
        .value
        .files[0];
};

const createPoster = () => {
    const formData = new FormData();
    formData.append("companion", store.poster.companion);
    formData.append("jog_id", store.poster.jog_id);
    formData.append("mood", store.poster.mood);
    formData.append("review", store.poster.review);
    formData.append("song", store.poster.song);
    formData.append("weather", store.poster.weather);
    formData.append("file", store.poster.user_photo);
    store.createPoster(formData);
};
</script>

<style scoped>
.flex-container {
    display: flex;
    justify-content: space-between;
    margin-left: 200px;
}
.container_review {
    height: 500px;
    width: 700px;
    background-color: rgba(170, 168, 168, 0.603);
    margin: 100px;
    border-radius: 20px;
    padding: 2%;
}
.container_photo {
    height: 500px;
    width: 700px;
    background-color: rgba(170, 168, 168, 0.603);
    margin: 100px 200px 100px 10px;
    border-radius: 20px;
    padding: 2%;
}

h2 {
    padding-top: 20px;
}

input {
    width: 500px;
    height: 200px;
    font-size: 20px;
    border: 0;
    border-radius: 15px;
    outline: none;
    padding-left: 10px;
    background-color: rgb(233, 233, 233);
    margin-top: 5px;
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