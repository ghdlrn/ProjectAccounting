<script setup lang="ts">


import CompanyRegister from "~/components/company/CompanyRegister.vue";
import UiParentCard from "~/components/shared/UiParentCard.vue";
import CompanyReference from "~/components/company/CompanyReference.vue";


</script>

<template>
  <div>
  <v-row>
<!--조회, 검색-->
    <v-col cols="5">
      <UiParentCard title="회사 조회">

        <CompanyReference />

      </UiParentCard>
    </v-col>



<!--회사등록-->
    <v-col>

      <CompanyRegister />

    </v-col>
  </v-row>
  </div>
</template>

