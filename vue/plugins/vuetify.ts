import { createVuetify } from 'vuetify'
import '@mdi/font/css/materialdesignicons.css'
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";
import {PurpleTheme} from "@/theme/LightTheme";
import '@/assets/scss/style.scss';
import {PerfectScrollbar} from 'vue3-perfect-scrollbar';
import VueApexCharts from 'vue3-apexcharts';
import VueTablerIcons from 'vue-tabler-icons';
// print
import print from 'vue3-print-nb';
// Table
import Vue3EasyDataTable from 'vue3-easy-data-table';
import 'vue3-easy-data-table/dist/style.css';
//calendar
import { setupCalendar, Calendar, DatePicker } from 'v-calendar';
import 'v-calendar/style.css';
//언어

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
    app.vueApp.component('EasyDataTable', Vue3EasyDataTable);
    app.vueApp.use(PerfectScrollbar);
    app.vueApp.use(VueTablerIcons);
    app.vueApp.use(print);
    app.vueApp.use(VueApexCharts);
    app.vueApp.use(vuetify);
    app.vueApp.use(setupCalendar, {});
    app.vueApp.component('VCalendar', Calendar)
    app.vueApp.component('VDatePicker', DatePicker)
});