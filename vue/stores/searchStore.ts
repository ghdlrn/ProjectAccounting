import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useSearchStore = defineStore('searchStore', () => {
    const searchList = ref(['이규민'])

    function addSearchList(value: string) {
        searchList.value.push(value)
    }
    return { addSearchList, searchList }
})