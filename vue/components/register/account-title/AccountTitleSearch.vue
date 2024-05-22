<script setup lang="ts">
import { shallowRef } from 'vue';
import sidebarItems from '~/components/register/account-title/AccountTitleItem';

import NavGroup from '~/components/layoutComponents/vertical-sidebar/NavGroup/NavGroup.vue';
import NavItem from '~/components/layoutComponents/vertical-sidebar/NavItem/NavItem.vue';
import NavCollapse from '~/components/layoutComponents/vertical-sidebar/NavCollapse/NavCollapse.vue';

const sidebarMenu = shallowRef(sidebarItems);
</script>

<template>
  <v-card>
    <perfect-scrollbar class="scrollnavbar">
      <v-list class="pa-4">
        <!---Menu Loop -->
        <template v-for="(item, i) in sidebarMenu" :key="i">
          <!---Item Sub Header -->
          <NavGroup :item="item" v-if="item.header" :key="item.title" />
          <!---Item Divider -->
          <v-divider class="my-3" v-else-if="item.divider" />
          <!---If Has Child -->
          <NavCollapse class="leftPadding" :item="item" :level="0" v-else-if="item.children" />
          <!---Single Item-->
          <NavItem :item="item" v-else class="leftPadding" />
          <!---End Single Item-->
        </template>
      </v-list>
    </perfect-scrollbar>
  </v-card>
</template>

<style lang="scss" scoped>

</style>