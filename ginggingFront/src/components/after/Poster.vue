<template>
    <div>
        <h2>오늘 하루 건강해진 당신</h2>
        <h2>같이 건강하고 싶은 친구에게 공유해주세요.</h2>
        <div class="poster-container">
            <img src="@/assets/poster.png" style="width: 800px"/>
            <div class="diary">
                <div class="header">
                    <h1 class="date">{{ formatDate(store.thisPoster.reg_date) }}</h1>
                    <h1 class="weather">{{ store.thisPoster.weather }}</h1>
                </div>
                <img
                    v-if="store.thisPoster.user_photo"
                    :src="`/src/assets/${store.thisPoster.user_org_photo}`"
                    style="width: 500px"/>
                <h1 class="article">
                    오늘 기분은
                    {{ store.thisPoster.mood }}
                    <br/>
                    {{ store.thisPoster.song }}를 들었어요.
                    <br/>
                    룰루~ 오늘의 운동 메이트는 바로
                    {{ store.thisPoster.companion }}!!
                    <br/>
                    오늘운동의 한줄평은
                    {{ store.thisPoster.review }}
                </h1>
            </div>
        </div>
        <!--공유하기 링크 걸기-->
        <ul class="sns">
            <li class="kakaotalk">
                <a
                    href="#n"
                    id="btnKakao"
                    @click.prevent="fn_sendFB('kakaotalk')"
                    class="kakaotalk"
                    target="_self"
                    title="카카오톡 새창열림">
                    <span class="skip"><img src="@/assets/kakao.webp" style="width: 40px; height: 40px"/>카카오톡으로 공유하기</span >
                </a>
            </li>
        </ul>
    </div>
</template>

<script setup>
import axios from "axios";
import { ref, onMounted } from "vue";
import { usePosterStore } from "../../stores/poster";

const store = usePosterStore();

const formatDate = (dateString) => {
    const date = new Date(dateString);
    return `${date.getFullYear()}-${ (date.getMonth() + 1)
        .toString()
        .padStart(2, "0")}-${date
        .getDate()
        .toString()
        .padStart(2, "0")}`;
};

const fn_sendFB = function (sns) {
    var thisUrl = document.URL;
    var snsTitle = "gingging 같이 하자!";

    if (sns == "kakaotalk") {
        Kakao.init("e50423800f434bd3ce167704cd230779");
        Kakao
            .Link
            .createDefaultButton({
                container: "#btnKakao",
                objectType: "feed",
                content: {
                    title: "GINGGING 같이 할 사람 구함, 바로 너!",
                    description: "건강할 사람 구함",
                    imageUrl: thisUrl,
                    link: {
                        mobileWebUrl: thisUrl,
                        webUrl: thisUrl
                    }
                }
            });
    }
};
</script>

<style scoped>
.poster-container {
    position: relative;
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
    bottom: 70px;
    font-size: 25px;
}
.weather {
    right: -50px;
}
.article {
    position: relative;
    top: 60px;
    font-size: 25px;
    line-height: 2.5rem;
}
</style>