<template>
    <div>
        <div class="select">
            <hr>
            <h4>내 위치를 고르고 버튼을 눌러주세요!</h4>
            <hr>
        </div>
        <div>
            <div>
                <div class="map-container">
                    <div id="map"></div>
                </div>
                <div id="clickLatlng"></div>
                <!-- 위치 설정 버튼 누르면 현재 curLoc에 저장된 좌표로 setLocation함수를 실행 -->
                <div id="infowindow">
                    <button @click="store.setLocation(store.curLoc)">
                        이곳인가요?
                    </button>
                </div>
            </div>
        </div>
        <div class="mapping">
            <hr>
            <h4>원하시는 조깅 루트를 골라보세요</h4>
            <hr>
            <br>
            <!-- store에 주소 저장되었다면 위치 설정 버튼 눌렀다는 뜻-->
            <div class="components">
                <div class="maps" v-if="store.address != null">
                    <!-- jogList에 있는 정보 하나씩 넘겨주면서 지도 생성 -->
                    <SelectMap
                        v-for="jog in store.jogList"
                        varStatus="vs"
                        :key="jog.id"
                        :jog="jog"
                        @regist-jog="registJog"
                        @go-my-page="goMyPage"></SelectMap>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import {onMounted} from "vue";
import {useJoggingStore} from "@/stores/jogging";
import {usePosterStore} from "../../stores/poster";
import router from "../../router";
import SelectMap from "./SelectMap.vue";

const store = useJoggingStore();
const postore = usePosterStore();

const registJog = (jogging) => {
    // postore.jog_name = jogging.jog_name;
    store.registJog(jogging);
};

const goMyPage = (user_id, jogging) => {
    // postore.jog_name = jogging.jog_name;
    store.skip(jogging, user_id);
};

const initMap = function () {
    var myCenter = new kakao
        .maps
        .LatLng(33.450701, 126.570667); // 카카오본사
    var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
            center: myCenter,
            level: 2, // 지도의 확대 레벨
        };

    if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator
            .geolocation
            .getCurrentPosition(function (position) {
                var lat = position.coords.latitude, // 위도
                    lon = position.coords.longitude; // 경도

                myCenter = new kakao
                    .maps
                    .LatLng(lat, lon);
                // myCenter = new kakao.maps.LatLng(36.359936477609736, 127.39772750154555);  자꾸
                // 무주를 잡아서 임시 위치
                map.setCenter(myCenter);
            });

        var map = new kakao
            .maps
            .Map(mapContainer, mapOption); // 지도를 생성합니다

        // 지도를 클릭한 위치에 표출할 마커입니다
        var marker = new kakao
            .maps
            .Marker({
                // 지도 중심좌표에 마커를 생성합니다
                position: map.getCenter()
            });
        // 지도에 마커를 표시합니다
        marker.setMap(map);

        // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        var iwContent = document.getElementById("infowindow");
        // iwContent.innerHTML = message;

        var infowindow = new kakao
            .maps
            .InfoWindow({position: map.getCenter(), content: iwContent});

        // 지도에 클릭 이벤트를 등록합니다 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
        kakao
            .maps
            .event
            .addListener(map, "click", function (mouseEvent) {
                // 클릭한 위도, 경도 정보를 가져옵니다
                var latlng = mouseEvent.latLng;

                // 마커 위치를 클릭한 위치로 옮깁니다
                marker.setPosition(latlng);
                infowindow.open(map, marker);

                // var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, '; message += '경도는 ' +
                // latlng.getLng() + ' 입니다'; var resultDiv =
                // document.getElementById('clickLatlng'); resultDiv.innerHTML = message;

                store.curLoc = latlng; // 마커 위치 바꾸면 위치 저장
            });
    }
};

onMounted(() => {
    if (window.kakao && window.kakao.maps) {
        console.log("already exist");
        initMap();
    } else {
        const script = document.createElement("script"); // autoload=false 스크립트를 동적으로 로드하기 위해서 사용
        script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
        import.meta.env.VITE_KAKAOMAP_API_KEY}`;
        script.addEventListener("load", () => {
            kakao
                .maps
                .load(initMap);
        }); //헤드태그에 추가
        document
            .head
            .appendChild(script);
        console.log("Successed to load kakao");
    }
});
</script>

<style scoped>
#map {
    width: 500px;
    height: 400px;
    margin: 5%;
}
.map-container {
    display: flex;
    justify-content: center;
}

button {
    width: 200px;
    background-color: aliceblue;
    border-radius: 10px;
    transition: background-color 0.3s ease;
}

button:hover {
    background-color: lightblue;
}
.maps {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 10px;
}
.components {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    margin-top: 10px;
}
.components > * {
    margin: 5px;
}
.select {
    width: 80%;
    margin: auto;
}
.mapping {
    margin: 100px 0;
    width: 80%;
    margin: auto;
}
</style>