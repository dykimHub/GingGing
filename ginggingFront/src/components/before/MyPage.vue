<template>
<table class="table" v-if="jogUserList">
    <thead>
        <tr>
            <th scope="col">조깅 날짜</th>
            <th scope="col">장소 이름</th>
            <th scope="col">주소</th>
            <th scope="col">길이(km)</th>
            <th scope="col">소요 시간(분)</th>
        </tr>
    </thead>
    <tbody>
        <tr v-for="jog in jogUserList" :key="jog.jog_id">
            <td>{{ jog.jog_date }}</td>
            <td>{{ jog.jog_name }}</td>
            <td>{{ jog.address }}</td>
            <td>{{ jog.length }}</td>
            <td>{{ jog.time }}</td>
            <button
                type="button"
                class="btn btn-outline-danger"
                @click="store.deleteJogging(jog.jog_id)">삭제</button>
        </tr>
    </tbody>
</table>
<div v-else>No data available</div>
</template>

<script setup>
import {ref, computed} from "vue";
import {useJoggingStore} from "@/stores/jogging";

const store = useJoggingStore();

const prop = defineProps(["user_id"]);

const jogUserList = computed(() => {
    store.getJoggingByUser(prop.user_id);
    return store.jogUserList;
})
</script>

<style scoped>
table {
    width: 80%;
    margin: auto;
}
</style>