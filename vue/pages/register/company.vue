<script setup lang="ts">
import {ref} from "vue";

import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import CompanyRegister from "~/components/registerform/CompanyRegister.vue";
import UiParentCard from "~/components/shared/UiParentCard.vue";
import CompanyReference from "~/components/reference/CompanyReference.vue";


const page = ref({ title: '회사 등록/조회' });
const breadcrumbs = ref([
  {
    title: '회사 등록/조회',
    disabled: false,
    href: '/register/company-info'
  }
]);

</script>

<template>
  <div>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
<!--조회, 검색-->
    <v-col cols="5">
      <UiParentCard title="회사 조회">


      </UiParentCard>
    </v-col>



<!--회사등록-->
    <v-col>

      <CompanyRegister />

    </v-col>
  </v-row>
  </div>
</template>

