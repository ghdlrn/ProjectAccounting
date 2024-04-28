<script setup lang="ts">
import {ref} from "vue";

import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import CompanyRegister from "~/components/registerform/CompanyRegister.vue";

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
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
<!--조회, 검색-->
    <v-col cols="5">
      <UiParentCard title="회사 조회">

        <v-card>
            <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                variant="outlined"
                label="Search"
                single-line
                hide-details></v-text-field>
          <v-data-table
              :headers="headers"
              :items="items"
              :search="search"
              :loading="loading"
              loading-text="Loading... Please wait"
              :page.sync="page"
              :items-per-page="itemsPerPage"
              hide-default-footer
              class="elevation-1"
              @page-count="pageCount = $event">
          </v-data-table>
          <div class="text-center pt-2">
            <v-pagination
                v-model="page"
                :length="pageCount"
                :total-visible="totalVisible"
                next-icon="mdi-menu-right"
                prev-icon="mdi-menu-left"></v-pagination>
          </div>
        </v-card>


      </UiParentCard>
    </v-col>



<!--회사등록-->
    <v-col>

      <CompanyRegister />

    </v-col>
  </v-row>
</template>

