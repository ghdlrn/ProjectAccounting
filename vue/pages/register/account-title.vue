<script setup>
import AccountTitleReference from "~/components/register/account-title/AccountTitleReference.vue";
import CompendiumReference from "~/components/register/compendium/CompendiumReference.vue";
import AccountTitleSearch from "~/components/register/account-title/AccountTitleSearch.vue";

const selectedAccountTitleId = ref(null);

const handleAccountTitleSelect = (id) => {
  selectedAccountTitleId.value = id;
};

</script>

<template>
  <div class="align-container">
    <v-row>
      <v-col cols="2">
        <AccountTitleSearch />
      </v-col>
      <v-col cols="6">
        <AccountTitleReference @account-title-select="handleAccountTitleSelect" />
      </v-col>
      <v-col cols="4">
        <CompendiumReference :accountTitleId="selectedAccountTitleId" />
      </v-col>
    </v-row>
  </div>
</template>

<style scoped lang="scss">

</style>