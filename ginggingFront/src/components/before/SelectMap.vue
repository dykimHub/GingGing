<template>
    <div class="route">
        <div ref="mapContainer" class="map_wrap3"></div>
        <div class="map_act_btn_wrap clear_box"></div>
        <div class="btns">
            <button
                type="button"
                class="btn btn-danger"
                data-bs-toggle="modal"
                data-bs-target="#exampleModal">
                START
            </button>
            <button
                type="button"
                class="btn btn-warning"
                data-bs-dismiss="modal"
                @click="goMyPage">SKIP</button>
            <button
                type="button"
                class="btn btn-primary"
                data-bs-dismiss="modal"
                @click="registJog">POSTER</button>
        </div>
        <table class="table">
            <tbody>
                <tr>
                    <td>
                        <h4>{{ jogging.jog_name }}</h4>
                    </td>
                </tr>
                <tr>
                    <p id="result">{{ resultText }}</p>
                </tr>
            </tbody>
        </table>
        <div
            id="exampleModal"
            class="modal fade"
            tabindex="-1"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">GINGGING</h1>
                        <button
                            type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div>
                            <div style="margin: 50px">
                                <div>같이 달려볼까요?</div>
                                <h2>오늘 내가 건강해진 시간</h2>
                                <h1 id="stopwatch">00:00:00</h1>
                                <div>
                                    <button @click="startClock">start</button>
                                    <button @click="stopClock">stop</button>
                                    <button @click="resetClock">reset</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer"></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import {ref, onMounted, computed} from "vue";
import {useJoggingStore} from "@/stores/jogging";
import router from "../../router";

const store = useJoggingStore();

const prop = defineProps(["jog"]);
const emits = defineEmits(['registJog', 'goMyPage']);

const jogging = ref({
    jog_id: null,
    user_id: localStorage.getItem("user_id"),
    jog_date: null,
    jog_name: prop
        .jog
        .title
        .replace(/<\/?b[^>]*>/g, ""),
    address: prop.jog.address,
    length: null,
    time: null
});

const registJog = () => {
    emits("registJog", jogging.value);
};

const goMyPage = () => {
    emits('goMyPage', jogging.value.user_id, jogging.value)
}

//timer
let timerId;
let time = 0;
let stopwatch;
let hour,
    min,
    sec;

const printTime = function () {
    time++;
    stopwatch.innerText = getTimeFormatString();
};

//시계 시작 - 재귀호출로 반복실행
const startClock = function () {
    printTime();
    stopClock();
    timerId = setTimeout(startClock, 1000);
};

//시계 중지
const stopClock = function () {
    if (timerId != null) {
        clearTimeout(timerId);
    }
};

// 시계 초기화
const resetClock = function () {
    stopClock();
    stopwatch.innerText = "00:00:00";
    time = 0;
};

// 시간(int)을 시, 분, 초 문자열로 변환
const getTimeFormatString = function () {
    hour = parseInt(String(time / (60 * 60)));
    min = parseInt(String((time - hour * 60 * 60) / 60));
    sec = time % 60;

    return (
        String(hour).padStart(2, "0") + ":" + String(min).padStart(2, "0") + ":" +
            String(sec).padStart(2, "0")
    );
};

const mapContainer = ref(null);
const resultText = ref("");
let map;
let marker_s,
    marker_e;
const drawInfoArr = [];
const resultdrawArr = [];

onMounted(() => {
    initTmap();
    stopwatch = document.getElementById("stopwatch");
});

function initTmap() {
    map = new Tmapv2.Map(mapContainer.value, {
        center: new Tmapv2.LatLng(store.lon, store.lat),
        width: "600px",
        height: "400px",
        zoom: 15,
        zoomControl: true,
        scrollwheel: true
    });

    marker_s = new Tmapv2.Marker({
        position: new Tmapv2.LatLng(store.lon, store.lat), // 출발 경도, 위도
        icon: "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png",
        iconSize: new Tmapv2.Size(24, 38),
        map: map
    });

    // 도착 위치
    marker_e = new Tmapv2.Marker({
        position: new Tmapv2.LatLng(prop.jog.mapy / 10000000, prop.jog.mapx / 10000000), // 도착 위도, 경도
        icon: "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png",
        iconSize: new Tmapv2.Size(24, 38),
        map: map
    });

    const headers = {};
    headers["appKey"] = import.meta.env.VITE_TMAP_API_KEY;;

    $.ajax({
        method: "POST",
        headers: headers,
        url: "https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json&callb" +
                "ack=result",
        async: false,
        data: {
            startX: `${store.lat}`, // 시작 위도
            startY: `${store.lon}`, // 시작 경도
            endX: prop.jog.mapx / 10000000, // 도착 위도
            endY: prop.jog.mapy / 10000000, // 도착 경도
            // endX: jog.mapx / 10000000, endY: jog.mapy / 10000000,
            reqCoordType: "WGS84GEO",
            resCoordType: "EPSG3857",
            startName: "출발지",
            endName: "도착지"
        },
        success: function (response) {
            const resultData = response.features;

            const tDistance = "총 거리 : " + (
                resultData[0].properties.totalDistance / 1000
            ).toFixed(1) + "km,";
            const tTime = " 총 시간 : " + (
                resultData[0].properties.totalTime / 60
            ).toFixed(0) + "분";

            //$("#result").text(tDistance + tTime);
            resultText.value = tDistance + tTime;

            // 조깅 객체에 저장
            jogging.value.length = (resultData[0].properties.totalDistance / 1000).toFixed(
                1
            );
            jogging.value.time = (resultData[0].properties.totalTime / 60).toFixed(0);

            if (resultdrawArr.length > 0) {
                for (const i in resultdrawArr) {
                    resultdrawArr[i].setMap(null);
                }
                resultdrawArr.length = 0;
            }

            drawInfoArr.length = 0;

            for (const i in resultData) {
                const geometry = resultData[i].geometry;
                const properties = resultData[i].properties;

                if (geometry.type == "LineString") {
                    for (const j in geometry.coordinates) {
                        const latlng = new Tmapv2.Point(
                            geometry.coordinates[j][0],
                            geometry.coordinates[j][1]
                        );
                        const convertPoint = new Tmapv2
                            .Projection
                            .convertEPSG3857ToWGS84GEO(latlng);
                        const convertChange = new Tmapv2.LatLng(convertPoint._lat, convertPoint._lng);
                        drawInfoArr.push(convertChange);
                    }
                } else {
                    let markerImg = "";
                    let size;

                    if (properties.pointType == "S") {
                        markerImg = "/upload/tmap/marker/pin_r_m_s.png";
                        size = new Tmapv2.Size(24, 38);
                    } else if (properties.pointType == "E") {
                        markerImg = "/upload/tmap/marker/pin_r_m_e.png";
                        size = new Tmapv2.Size(24, 38);
                    } else {
                        markerImg = "http://topopen.tmap.co.kr/imgs/point.png";
                        size = new Tmapv2.Size(8, 8);
                    }

                    const latlon = new Tmapv2.Point(
                        geometry.coordinates[0],
                        geometry.coordinates[1]
                    );
                    const convertPoint = new Tmapv2
                        .Projection
                        .convertEPSG3857ToWGS84GEO(latlon);

                    const routeInfoObj = {
                        markerImage: markerImg,
                        lng: convertPoint._lng,
                        lat: convertPoint._lat
                    };

                    const marker_p = new Tmapv2.Marker({
                        position: new Tmapv2.LatLng(routeInfoObj.lat, routeInfoObj.lng),
                        icon: routeInfoObj.markerImage,
                        iconSize: size,
                        map: map
                    });
                }
            }

            drawLine(drawInfoArr);
        },
        error: function (request, status, error) {
            console.log(
                "code:" + request.status + "\nmessage:" + request.responseText + "\nerror:" +
                error
            );
        }
    });

    function addComma(num) {
        const regexp = /\B(?=(\d{3})+(?!\d))/g;
        return num
            .toString()
            .replace(regexp, ",");
    }

    function drawLine(arrPoint) {
        let polyline_;

        polyline_ = new Tmapv2.Polyline(
            {path: arrPoint, strokeColor: "#DD0000", strokeWeight: 6, map: map}
        );
        resultdrawArr.push(polyline_);
    }
}
</script>

<style scoped>
.modal fade {
    height: 100px;
}
.btns {
    margin: 0 0 10px;
}

button {
    margin: 5px;
}
</style>