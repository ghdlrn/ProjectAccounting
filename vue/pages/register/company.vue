<script setup lang="ts">


import CompanyReference from "~/components/company/CompanyReference.vue";


</script>

<template>
  <div class="align-container">
   <CompanyReference />
  </div>
</template>

<style scoped>
.align-container {
  display: flex;
  justify-content: center; /* 수평 중앙 정렬 */
  align-items: flex-start; /* 수직 상단 정렬 */
  height: 100%; /* 부모 컨테이너가 전체 높이를 차지하도록 설정하거나 적절한 높이를 지정 */
}
</style>