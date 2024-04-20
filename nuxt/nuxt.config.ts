import { createResolver } from "@nuxt/kit";
import vuetify, { transformAssetUrls } from 'vite-plugin-vuetify'

const { resolve } = createResolver(import.meta.url);

export default defineNuxtConfig({
  ssr: false,
  css: [
    '@mdi/font/css/materialdesignicons.css', // Material Design Icons
    'vuetify/styles' // Vuetify 스타일
  ],
  build: {
    transpile: ["vuetify"],
  },
  modules: [
    (_options, nuxt) => {
      nuxt.hooks.hook('vite:extendConfig', (config) => {
        // @ts-expect-error
        config.plugins.push(vuetify({ autoImport: true }))
      })
    },
    '@pinia/nuxt',
  ],
  nitro: {
    serveStatic: true,
  },
  vite: {
    vue: {
      template: {
        transformAssetUrls,
      },
    }
  },
});
