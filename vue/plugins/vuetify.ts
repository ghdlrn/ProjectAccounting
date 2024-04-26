import { createVuetify } from 'vuetify'
import '@mdi/font/css/materialdesignicons.css'
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";
import {PurpleTheme} from "@/theme/LightTheme";
import '@/assets/scss/style.scss';
import VueApexCharts from 'vue3-apexcharts';
import VueTablerIcons from 'vue-tabler-icons';
import {PerfectScrollbar} from "vue3-perfect-scrollbar";


export default defineNuxtPlugin((app) => {
    const vuetify = createVuetify({
        components,
        directives,
        theme: {
            defaultTheme: "PurpleTheme",
            themes: {
                PurpleTheme,
            },
        },
        defaults: {
            VBtn: {},
            VCard: {rounded: 'lg'},
            VTextField: {rounded: 'lg'},
            VTooltip: {location: 'top'},
        },
    })

    app.vueApp.use(vuetify);
    app.vueApp.use(VueApexCharts);
    app.vueApp.use(VueTablerIcons);
    app.vueApp.use(PerfectScrollbar);
});