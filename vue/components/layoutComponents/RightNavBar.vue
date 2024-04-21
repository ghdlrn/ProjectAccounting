<script setup lang="ts">

</script>

<template>

    <v-navigation-drawer
        app
        location="right"
        floating
        elevation="0"
        expand-on-hover
        rail
        rail-width="50"
        rounded="s-lg"
        class="h-50 bg-grey-lighten-3 left-nav-bar" >

      <v-list>
        <v-list-item
            v-for="n in 5"
            :key="n"
            :title="`Item ${ n }`"
            link>
        </v-list-item>
        <v-pagination :length="1"></v-pagination>
      </v-list>
    </v-navigation-drawer>

</template>

<style scoped>
.left-nav-bar {
  margin: 200px 0 0 auto;
}
</style>