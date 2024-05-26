import vuetify, { transformAssetUrls } from 'vite-plugin-vuetify'

export default defineNuxtConfig({
  ssr: false,
  css: [
    '@mdi/font/css/materialdesignicons.css', // Material Design Icons
    'vuetify/styles', // Vuetify 스타일
  ],
  build: {
    transpile: ['vuetify'],
  },
  modules: [
    (_options, nuxt) => {
      nuxt.hooks.hook('vite:extendConfig', (config) => {
        // @ts-expect-error
        config.plugins.push(vuetify({ autoImport: true }))
      })
    },
  '@pinia/nuxt',
    'vue3-perfect-scrollbar/nuxt'
  ],
  vite: {
    vue: {
      template: {
        transformAssetUrls,
      },
    },
  },
  app: {    //페이지전환애니메이션 설정
    pageTransition: { name: 'page', mode: 'out-in' }
  },
  runtimeConfig: {
    public: {
      apiBaseUrl: process.env.VITE_API_URL || 'http://localhost:8080',
    },
  },
});
