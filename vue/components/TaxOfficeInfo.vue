
<template>
  <div class="text-center">
    <v-menu
        :close-on-content-click="false"
        :nudge-right="40"
        lazy
        transition="scale-transition"
        offset-y
        full-width
        min-width="290px">
      <template v-slot:activator="{ props }">
        <v-text-field
            v-bind="props"
            readonly
            :value="taxOffices.length ? taxOffices[0].name : 'OO 세무서'"
            persistent-placeholder
            prepend-icon="mdi-office-building"
            variant="outlined"
            color="primary">
        </v-text-field>
      </template>
      <v-card title="사업장 세무서 조회" flat>

        <EasyDatatable
          :headers="headers"
          :items="items"
        ></EasyDatatable>

      </v-card>
    </v-menu>
  </div>
</template>



<script setup>
import { ref, computed, onMounted, shallowRef } from 'vue';

import { useTaxOfficeStore } from '~/stores/accounting/basicdata/taxOffice.js';
const taxOfficeStore = useTaxOfficeStore();

onMounted(() => {
  taxOfficeStore.fetchTaxOffices();
});

const taxOffices = computed(() => taxOfficeStore.taxOffices);

import type { Header, Item } from "vue3-easy-data-table";

const headers: Header[] = [
  { text: "PLAYER", value: "player" },
  { text: "TEAM", value: "team"},
  { text: "NUMBER", value: "number"},
  { text: "POSITION", value: "position"},
  { text: "HEIGHT", value: "indicator.height"},
  { text: "WEIGHT (lbs)", value: "indicator.weight", sortable: true},
  { text: "LAST ATTENDED", value: "lastAttended", width: 200},
  { text: "COUNTRY", value: "country"},
];

const items: Item[] = [
  { player: "Stephen Curry", team: "GSW", number: 30, position: 'G', indicator: {"height": '6-2', "weight": 185}, lastAttended: "Davidson", country: "USA"},
  { player: "Lebron James", team: "LAL", number: 6, position: 'F', indicator: {"height": '6-9', "weight": 250}, lastAttended: "St. Vincent-St. Mary HS (OH)", country: "USA"},
  { player: "Kevin Durant", team: "BKN", number: 7, position: 'F', indicator: {"height": '6-10', "weight": 240}, lastAttended: "Texas-Austin", country: "USA"},
  { player: "Giannis Antetokounmpo", team: "MIL", number: 34, position: 'F', indicator: {"height": '6-11', "weight": 242}, lastAttended: "Filathlitikos", country: "Greece"},
];
</script>


<style lang="scss">
.customer-modal {
  width: calc(100% - 48px);
  min-width: 340px;
  max-width: 880px;
}
</style>
