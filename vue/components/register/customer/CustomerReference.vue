<script setup>

</script>

<template>

  <UiParentCard title="회사 조회" class="reference" >
  <PerfectScrollbar>
      <v-card>
        <v-card-item>
          <v-row justify="space-between" class="align-center">
            <v-col cols="6">
              <v-text-field
                  type="text"
                  variant="outlined"
                  color="primary"
                  persistent-placeholder
                  placeholder="회사명 검색"
                  v-model="searchValue"
                  hide-details>
                <template v-slot:prepend-inner>
                  <SearchOutlined :style="{ fontSize: '14px' }" />
                </template>
              </v-text-field>
            </v-col>
            <v-col cols="3" offset="3">
              <div class="d-flex gap-2 justify-end">
                <v-dialog v-model="dialog" class="register">
                  <template v-slot:activator="{ props }">
                    <v-btn variant="flat" color="primary" v-bind="props" size="large">
                      <template v-slot:prepend>
                        <PlusOutlined />
                      </template>
                      회사 등록
                    </v-btn>
                  </template>
                  <CompanyRegister @closeDialog="dialog = false" />
                </v-dialog>
              </div>
            </v-col>
          </v-row>

        </v-card-item>

        <v-divider></v-divider>

        <v-card-text class="pa-0">
          <v-menu
              v-model="menu"
              :close-on-content-click="false"
              lazy
              transition="scale-transition"
              origin="top right">
            <template v-slot:activator="{ props }">
              <EasyDataTable
                  v-bind="props"
                  :headers="headers"
                  :items="company"
                  table-class-name="customize-table"
                  :theme-color="themeColor"
                  :search-field="searchField"
                  :search-value="searchValue"
                  @click-row="getCompany"
                  :rows-per-page="10">
                <template #item-operation="item">
                  <div class="operation-wrapper">
                    <v-btn icon color="error" variant="text" @click.stop="deleteCompany(item)" rounded>
                      <DeleteOutlined />
                    </v-btn>
                  </div>
                </template>
              </EasyDataTable>
            </template>
            <CompanyUpdate />
          </v-menu>
        </v-card-text>
      </v-card>
    </PerfectScrollbar>
  </UiParentCard>
</template>

<style lang="scss">
.reference {
  width: 40%;
  min-width: 900px;
  min-height: 850px;
}
.register {
  width: 50%;
  height: 80%;
  min-width: 1000px;
  min-height: 1300px;
}
</style>
