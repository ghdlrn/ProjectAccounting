<template>
    <NuxtLayout>
      <NuxtPage />
    </NuxtLayout>
</template>

<script setup lang="ts">
useHead({
  titleTemplate: (titleChunk) => {
    return titleChunk ? `${titleChunk} - KMBJ회계` : 'KMBJ회계';  //사이트 타이틀명
  },
  script: [
    {
      src: '/images/BoxLogo.jpg',
      tagPosition: 'head'
    }
  ]
})
</script>

<style>
.page-enter-active,
.page-leave-active {
  transition: all 0.4s;
}
.page-enter-from,
.page-leave-to {
  opacity: 0;
  filter: blur(1rem);
  transform: rotate3d(1, 1, 1, 15deg);
}
</style>