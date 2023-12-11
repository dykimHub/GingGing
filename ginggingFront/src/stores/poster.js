import {ref, computed} from "vue";
import {defineStore} from "pinia";
import router from "@/router";
import axios from "../util/http-common.js";

const REST_POSTER_API = `http://localhost:8080/api`;

export const usePosterStore = defineStore("poster", () => {
    let poster = ref({
        mood: "",
        weather: "",
        song: "",
        companion: "",
        review: "",
        jog_id: ""
    });

    const thisPoster = ref();

    // 유튜브 재생목록 반환 (인기음악들)
    const videos = ref([]);

    //getter
    const getVideos = computed(() => {
        return videos.value;
    });

    //poster 등록
    const createPoster = function (file) {
        axios({
            url: `${REST_POSTER_API}/poster`,
            method: "POST",
            data: file,
            headers: {
                "Content-Type": "multipart/form-data"
            }
        })
        .then((response) => {
            // 포스터가 성공적으로 업로드되면 해당 포스터를 가져옴
            return axios.get(`http://localhost:8080/api/poster/${response.data}`);
        })
        .then((getPosterResponse) => {
            // 가져온 포스터 정보를 thisPoster.value에 할당
            thisPoster.value = getPosterResponse.data;
            
            // 라우터를 사용하여 포스터 페이지로 이동
            router.push({ name: "poster"});
        })
        .catch((error) => {
            console.error("에러 발생:", error);
        });
    };

    // 유튜브 재생목록 플레이리스트
    const youtubeList = function () {
        const URL = "https://www.googleapis.com/youtube/v3/playlistItems";
        axios({
            url: URL,
            method: "GET",
            params: {
                key: import.meta.env.VITE_YOUTUBE_API_KEY,
                part: "snippet",
                maxResults: 25,
                playlistId: "PL2HEDIx6Li8jGsqCiXUq9fzCqpH99qqHV"
            }
        })
            .then((response) => {
                videos.value = response.data.items;
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return { poster, youtubeList, videos, getVideos, createPoster, thisPoster };
});
