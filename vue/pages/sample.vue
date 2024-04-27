<script setup lang="ts">
import { ref, shallowRef } from 'vue';

import BaseBreadcrumb from '~/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '~/components/shared/UiParentCard.vue';
import UiChildCard from "~/components/shared/UiChildCard.vue";

const page = ref({ title: 'Sample Page' });
const breadcrumbs = shallowRef([
  {
    title: 'Others',
    disabled: false,
    href: '#'
  },
  {
    title: 'Sample Page',
    disabled: true,
    href: '#'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs" />
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="UI부모카드">
       예시텍스트1
       예시텍스트2
       예시텍스트3
      </UiParentCard>
      <UiChildCard title="UI자식카드">
        예시텍스트1
        예시텍스트2
        예시텍스트3
      </UiChildCard>
    </v-col>
  </v-row>
</template>
