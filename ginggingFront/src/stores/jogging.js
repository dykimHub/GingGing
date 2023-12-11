import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "../router";

const REST_JOGGING_API = `http://localhost:8080/api`;

export const useJoggingStore = defineStore("jogging", () => {

    const curLoc = ref(); // 지도 클릭으로 받은 현재 좌표
    const lat = ref();
    const lon = ref();
    const address = ref(null); // 좌표로 얻어낸 주소 저장
    const jogList = ref([]);
    const parkList = ref([]);
    const riverList = ref([]);
    const jogUserList = ref([]);
    // 조깅 리스트 수 확인
    const jog_id = ref();
    const myPoster = ref([]);

    const setLocation = () => {
        lat.value = curLoc.value.La;
        lon.value = curLoc.value.Ma;

        axios
            .get(
                `${REST_JOGGING_API}/jogging/change/${curLoc.value.La}/${curLoc.value.Ma}`
            )
            .then((res) => {
                address.value = res
                    .data
                    .documents[0]
                    .address_name,
                getParkList(address.value)
                getRiverList(address.value)
            })
            .catch((err) => console.log(err));
    }

    const getParkList = (address) => { // 주소 근처 공원 찾기
        axios
            .get(`${REST_JOGGING_API}/jogging/search/${address} 공원`)
            .then((res) => {
                parkList.value = res.data.items
                updateJogList();
            })
            .catch((err) => console.log(err))
        }

    const getRiverList = (address) => { // 주소 근처 하천 찾기
        axios
            .get(`${REST_JOGGING_API}/jogging/search/${address} 하천`)
            .then((res) => {
                riverList.value = res.data.items
                updateJogList();
            })
            .catch((err) => console.log(err))
        }

    const updateJogList = () => {
        const combinedList = [
            ...parkList.value,
            ...riverList.value
        ];
        //중복 제거
        const uniqueTitles = Array.from(
            new Set(combinedList.map(item => item.title.trim()))
        );

        jogList.value = combinedList.filter(
            item => uniqueTitles.includes(item.title.trim()) && !item.title.includes('주차장') && !item.title.includes('입구')
        );

    }

    const registJog = (jogging) => {
        axios
            .post(`${REST_JOGGING_API}/jogging`, jogging)
            .then((res) => {
                jog_id.value = res.data;
                router.push({
                    name: 'afterJogging',
                    params: {
                        'jog_id': jog_id.value
                    }
                })
            })
            .catch((err) => console.log(err));
    };

    const skip = (jogging, user_id) => {
        axios
            .post(`${REST_JOGGING_API}/jogging`, jogging)
            .then((res) => {
                router.push({
                    name: 'myPage',
                    params: {
                        'user_id': user_id
                    }
                })
            })
            .catch((err) => console.log(err));
    };

    const getJoggingByUser = (user_id) => {
        axios
            .get(`${REST_JOGGING_API}/jogging/user/${user_id}`)
            .then((res) => {
                jogUserList.value = res.data;
            })
            .catch((err) => console.log(err))
        }

    const deleteJogging = (jog_id) => {
        axios
            .delete(`${REST_JOGGING_API}/jogging/${jog_id}`)
            .catch((err) => console.log(err))
        }

    const getPosterByUser = (user_id) => {
        axios
            .get(`${REST_JOGGING_API}/poster/user/${user_id}`)
            .then((res) => {
                myPoster.value = res.data;
            })
            .catch((err) => console.log(err))
        }

    const deletePoster = (poster_id) => {
        axios
            .delete(`${REST_JOGGING_API}/poster/${poster_id}`)
            .catch((err) => console.log(err))
        }

    return {
        curLoc,
        lat,
        lon,
        address,
        setLocation,
        jogList,
        registJog,
        getJoggingByUser,
        jogUserList,
        deleteJogging,
        getPosterByUser,
        jog_id,
        skip,
        deletePoster,
    };
});
