<script setup lang="ts">
import AccountTitleReference from "~/components/register/account-title-compendium/AccountTitleReference.vue";
import CompendiumReference from "~/components/register/Compendium/CompendiumReference.vue";
</script>

<template>
  <div class="align-container">
    <v-row>
      <v-col cols="7">
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