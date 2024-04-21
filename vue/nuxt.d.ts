import { NuxtApp } from 'nuxt3';

declare module '#app' {
    interface NuxtApp {
        $fetch: typeof fetch;
    }
}
