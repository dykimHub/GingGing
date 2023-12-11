import {ref, onMounted} from "vue";
import {defineStore} from "pinia";
import axios from "../util/http-common.js";
import router from "@/router";

const REST_USER_API = `http://localhost:8080/api/user`;
const LOGIN_FLAG_KEY = "login";

export const useUserStore = defineStore("user", () => {
    const checkLogin = ref(false);

    const createUser = function (user) {
        axios({
            url: REST_USER_API + `/signup`,
            method: "POST",
            data: user
        })
            .then(() => {
                window.alert("당신도 이제는 gingging의 회원!");
                router.push({name: "login"});
            })
            .catch((err) => {
                window.alert("이미 존재하는 아이디 입니다.");
                console.log(err);
            });
    };

    const loginUser = function (id, password) {
        axios
            .post(`${REST_USER_API}/login`, null, {
                params: {
                    id: id,
                    password: password
                }
            })
            .then((res) => {
                localStorage.setItem("id", res.data.nick_name);
                localStorage.setItem("user_id", res.data.user_id);

                localStorage.setItem(LOGIN_FLAG_KEY, "true");
                checkLogin.value = true;

                location.href = `/hello`
            })
            .catch((e) => {
                console.error("Error logging in:", e);
                window.alert("로그인 실패");
            });
    };

    const restoreSession = function () {
        const loggedIn = localStorage.getItem(LOGIN_FLAG_KEY) === "true";
        if (loggedIn) {
            checkLogin.value = true;
        }
    };

    onMounted(() => {
        restoreSession();
    });

    const logout = function () {
        window.alert("로그아웃");
        checkLogin.value = false;
        localStorage.removeItem(LOGIN_FLAG_KEY);
        localStorage.removeItem("id");
        localStorage.removeItem("user_id");

        router.push({name: "home"});
    };

    return {createUser, checkLogin, loginUser, logout};
});
