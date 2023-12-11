import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap/dist/js/bootstrap.js";

import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";
import SelectMap from "../src/components/before/SelectMap.vue";
const app = createApp(App);

app.use(createPinia());
app.use(router);
//티맵
app.component("SelectMap", SelectMap);

app.mount("#app");
