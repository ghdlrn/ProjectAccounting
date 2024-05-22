<script setup lang="ts">
import AccountTitleReference from "~/components/register/account-title/AccountTitleReference.vue";
import CompendiumReference from "~/components/register/compendium/CompendiumReference.vue";
import AccountTitleSearch from "~/components/register/account-title/AccountTitleSearch.vue";

</script>

<template>
  <div class="align-container">
    <v-row>
      <v-col cols="2">
        <AccountTitleSearch />
      </v-col>
      <v-col cols="5">
        <AccountTitleReference />
      </v-col>
      <v-col cols="5">
        <CompendiumReference />
      </v-col>
    </v-row>
  </div>
</template>

<style scoped lang="scss">

</style>