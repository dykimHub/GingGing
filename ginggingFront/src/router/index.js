import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import Login from "@/components/Login.vue";

import Hello from "@/components/before/Hello.vue";
import Map from "@/components/before/Map.vue";
import SelectMap from "@/components/before/SelectMap.vue";
import MyPage from "@/components/before/MyPage.vue";
import MyPoster from "@/components/before/MyPoster.vue";

import Mood from "@/components/after/Mood.vue";
import Weather from "@/components/after/Weather.vue";
import Music from "@/components/after/Music.vue";
import Companion from "@/components/after/Companion.vue";
import Journal from "@/components/after/Journal.vue";
import Poster from "@/components/after/Poster.vue";

import AfterLogout from "@/components/AfterLogout.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/login",
      name: "login",
      component: Login,
    },
    {
      path: "/hello",
      name: "hello",
      component: Hello,
    },
    {
      path: "/jogging",
      name: "beforeJogging",
      component: Map,
    },
    {
      path: "/selectMap/:curLoc",
      name: "selectMap",
      component: SelectMap,
      props: true,
    },
    {
      path: "/myPage/:user_id",
      name: "myPage",
      component: MyPage,
      props: true,
    },
    {
      path: "/myPoster/:user_id",
      name: "myPoster",
      component: MyPoster,
      props: true,
    },
    {
      path: "/poster/:jog_id",
      name: "afterJogging",
      component: Mood,
      props: true,
    },
    {
      path: "/poster",
      name: "weather",
      component: Weather,
    },
    {
      path: "/poster",
      name: "music",
      component: Music,
    },
    {
      path: "/poster",
      name: "companion",
      component: Companion,
    },
    {
      path: "/poster",
      name: "journal",
      component: Journal,
    },
    {
      path: "/poster",
      name: "poster",
      component: Poster,
    },
    {
      path: "/bye",
      name: "afterLogout",
      component: AfterLogout,
    },
  ],
});

export default router;
