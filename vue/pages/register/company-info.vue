<script setup lang="ts">
import { ref } from 'vue';

import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';

const page = ref({ title: '회사 등록/조회' });
const breadcrumbs = ref([
  {
    title: '회사 등록/조회',
    disabled: false,
    href: '/register/company-info'
  }
]);
const checkbox1 = ref(true);
const checkbox2 = ref(false);
const checkbox3 = ref(false);


</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
<!--조회, 검색-->
    <v-col cols="5">
      <UiParentCard>



      </UiParentCard>
    </v-col>



<!--회사등록-->
    <v-col>
      <UiParentCard title="회사등록">
        <v-row>
          <!-- column 1 -->
          <v-col cols="12" sm="6">
            <!-- row 1 -->
            <v-row>
              <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                <v-label class="mt-2">Name</v-label>
              </v-col>
              <v-col cols="12" lg="8" md="9">
                <v-text-field
                    hint="Please enter your full name"
                    persistent-hint
                    variant="outlined"
                    persistent-placeholder
                    placeholder="Enter full name"
                    color="primary"
                ></v-text-field>
              </v-col>
            </v-row>
            <!-- row 2 -->
            <v-row class="mt-3">
              <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                <v-label class="mt-2">Password</v-label>
              </v-col>
              <v-col cols="12" lg="8" md="9">
                <v-text-field
                    hint="Please enter Password"
                    persistent-hint
                    variant="outlined"
                    type="password"
                    persistent-placeholder
                    placeholder="Enter password"
                    color="primary">
                </v-text-field>
              </v-col>
            </v-row>
            <!-- row 3 -->
            <v-row class="mt-3">
              <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                <v-label class="mt-0">Language</v-label>
              </v-col>
              <v-col cols="12" lg="8" md="9">
                <div class="d-flex gap-3">
                  <v-checkbox
                      density="compact"
                      v-model="checkbox1"
                      hide-details
                      color="primary"
                      label="English"
                      class="flex-grow-0"
                  ></v-checkbox>
                  <v-checkbox
                      density="compact"
                      v-model="checkbox2"
                      hide-details
                      color="primary"
                      label="French"
                      class="flex-grow-0"
                  ></v-checkbox>
                  <v-checkbox
                      density="compact"
                      v-model="checkbox3"
                      hide-details
                      color="primary"
                      label="Dutch"
                      class="flex-grow-0"
                  ></v-checkbox>
                </div>
              </v-col>
            </v-row>
          </v-col>
          <!-- column 2 -->
          <v-col cols="12" sm="6">
            <!-- row 1 -->
            <v-row>
              <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                <v-label class="mt-2">Email</v-label>
              </v-col>
              <v-col cols="12" lg="8" md="9">
                <v-text-field
                    hint="Please enter your Email"
                    persistent-hint
                    variant="outlined"
                    persistent-placeholder
                    placeholder="Enter email"
                    color="primary"
                ></v-text-field>
              </v-col>
            </v-row>
            <!-- row 2 -->
            <v-row class="mt-3">
              <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                <v-label class="mt-2">Profile URL</v-label>
              </v-col>
              <v-col cols="12" lg="8" md="9">
                <v-text-field
                    hint="Please enter your Profile URL"
                    persistent-hint
                    variant="outlined"
                    type="password"
                    persistent-placeholder
                    placeholder="Please enter your Profile URL"
                    color="primary">
                </v-text-field>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>

<style scoped lang="scss">
.customize-table {
  --easy-table-header-background-color: 66CCFF;
}
</style>