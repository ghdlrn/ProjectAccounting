<template>
    <v-autocomplete
        :items="searchList"
        @input="addSearchList(inputVal)"
        label="Search"
        clearable
        clear-icon="mdi-close-circle"
        autofocus
        auto-select-first
        prepend-inner-icon="mdi-magnify"
        color="primary"
        class="mx-auto"
        menu-icon="mdi-chevron-down"
        placeholder="Search Google or type a URL"
        variant="outlined"
        rounded="sm"
    ></v-autocomplete>
</template>

<script setup>
import { ref } from 'vue';
import { useSearchStore } from '~/stores/searchStore.ts';

const inputVal = ref('');

const searchStore = useSearchStore();
const { addSearchList, searchList } = searchStore;
</script>

<style scoped>

</style>