<template>
    <div id="form-container">
        <div id="form-inner-container">
            <!-- Sign up form -->
            <div id="sign-up-container" v-if="upCliked">
                <h3>Get Started</h3>
                <form>
                    <label for="id">ID</label>
                    <input type="text" id="id" v-model="user.id" placeholder="ID"/>

                    <label for="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        v-model="user.password"
                        placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;"/>

                    <label for="passwordConfirmation">비밀번호 확인</label>
                    <input
                        type="password"
                        id="passwordConfirmation"
                        v-model="user.passwordConfirmation"
                        placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;"/>

                    <label for="email">Email</label>
                    <input type="email" id="email" v-model="user.email" placeholder="Email"/>

                    <label for="nick_name">Nickname</label>
                    <input
                        type="text"
                        id="nick_name"
                        v-model="user.nick_name"
                        placeholder="NickName"/>

                    <label for="age">Age</label>
                    <input type="text" id="age" v-model="user.age" placeholder="Age"/>

                    <label for="gender">Gender</label>
                    <span>
                        <label class="radio-label">
                            <input type="radio" value="0" name="gender" id="male" v-model="user.gender"/>
                            <span class="radio-text">남</span>
                        </label>
                        <label class="radio-label">
                            <input type="radio" value="1" name="gender" id="female" v-model="user.gender"/>
                            <span class="radio-text">여</span>
                        </label>
                    </span>

                    <div id="form-controls">
                        <button type="button" @click="createUser">Sign Up</button>
                        <button type="button" id="toggleSignIn" @click="changeForm">
                            Sign In
                        </button>
                    </div>
                </form>
            </div>

            <!-- Sign in form -->
            <div id="sign-in-container" v-if="!upCliked">
                <h3>Welcome Back</h3>
                <form>
                    <label for="id">Username</label>
                    <input type="text" id="id" placeholder="아이디" v-model="id"/>

                    <label for="password">Password</label>
                    <input type="password" id="password" v-model="password" placeholder="비밀번호"/>

                    <div id="form-controls">
                        <button type="button" @click="login">Sign In</button>
                        <button type="button" id="toggleSignUp" @click="changeForm">
                            Sign Up
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user.js";

const userStore = useUserStore();

const id = ref("");
const password = ref("");

const login = () => {
    if (!id.value || !password.value) {
        alert("로그인 정보를 정확하게 입력해주세요");
        return;
    }
    userStore.loginUser(id.value, password.value);
};

const user = ref({
    id: "",
    password: "",
    email: "",
    nick_name: "",
    age: "",
    gender: ""
});

const createUser = function () {
    if (!user.value.id || !user.value.password || !user.value.passwordConfirmation || !user.value.email || !user.value.nick_name || !user.value.age || user.value.gender === "") {
        alert("모든 필수 항목을 입력해주세요.");
        return;
    }

    if (user.value.password !== user.value.passwordConfirmation) {
        alert("비밀번호를 다시 확인해주시기 바랍니다.");
        return;
    }
    userStore.createUser(user.value);
};

//이 변수가 true이면 signup 배경 출력 이 변수가 false면 signin 출력
let upCliked = ref(false);

const changeForm = function () {
    upCliked.value = !upCliked.value;
};
</script>

<style scoped>
body {
    background-color: #f3f4f9;
    margin: 0;
    padding: 0;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
}

#form-container {
    display: grid;
    place-items: center;
    height: 100vh;
}

#form-inner-container {
    background-color: white;
    width: 70%;
    height: 1100px;
    border-radius: 8px;
    box-shadow: 0 0 20px gainsboro;
}

#sign-up-container {
    padding: 60px 80px;
    display: inline-block;
}

#sign-in-container {
    padding: 60px 80px;
    display: inline-block;
}

form input {
    display: block;
    margin-bottom: 20px;
    border: 1px solid #e5e9f5;
    background-color: #f6f7fa;
    padding: 20px;
    margin-top: 10px;
    border-radius: 10px;
    width: 100%;
}

#form-controls {
    margin-bottom: 20px;
}

h3 {
    font-size: 150%;
    font-weight: 500;
}

label {
    color: #7369ab;
    display: inline-block;
    width: 100px;
}

::placeholder {
    color: #c0c7db;
    font-size: larger;
    letter-spacing: 1.2px;
}

#form-controls button {
    border: none;
    font-size: 120%;
}

#form-controls button:hover {
    cursor: pointer;
}

button[type="button"] {
    padding: 16px 75px;
    background-color: rgb(71, 31, 4);
    border-radius: 10px;
    color: white;
}

button[type="button"]:hover {
    background-color: rgb(199, 198, 197);
}

button[type="button"] {
    padding: 16px 35px;
    background-color: transparent;
    color: #ed4b5e;
}

#terms {
    width: 30px;
    height: 30px;
    appearance: none;
    border: 2px solid #7369ab;
}

#terms:checked:after {
    content: "\2713";
    color: #7369ab;
    font-size: 22px;
    position: absolute;
    top: 0;
    left: 3px;
}

label[for="terms"] {
    display: inline-block;
    width: 80%;
    margin-left: 10px;
}

#animation-container {
    display: inline-block;
}

@media (max-width: 1438px) {
    lottie-player {
        width: 300px !important;
    }
}

@media (max-width: 1124px) {
    #animation-container {
        display: none;
    }

    #form-inner-container {
        display: flex;
        justify-content: center;
    }
}

@media (max-width: 684px) {
    #form-controls {
        text-align: center;
        margin: 0;
        padding: 0;
    }

    button {
        width: 100%;
    }

    form input:not(:last-of-type) {
        width: 85%;
    }

    #toggleSignIn,
    #toggleSignUp {
        padding: 16px 75px;
    }

    #terms {
        width: 20px;
        height: 20px;
    }

    label[for="terms"] {
        display: inline-block;
        font-size: smaller;
    }
}

span label {
    display: flex;
    align-items: center;
}
.radio-label {
    display: flex;
    align-items: center;
}
.radio-text {
    margin-left: 5px;
}
</style>