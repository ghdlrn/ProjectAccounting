<script setup lang="ts">
</script>

<template>
  <v-row no-gutters class="h-screen">
    <v-col class="d-flex align-center justify-center">
      <div class="text-center">
        <div class="CardMediaWrapper">
          <img src="/images/error/img-error-bg.svg" alt="grid" class="w-100" />
          <img src="/images/error/img-error-blue.svg" alt="grid" class="CardMediaParts" />
          <img src="/images/error/img-error-text.svg" alt="build" class="CardMediaBuild" />
          <img src="/images/error/img-error-purple.svg" alt="build" class="CardMediaBuild" />
        </div>
        <h1 class="text-h1">오류 페이지</h1>
        <br />
        <p>접근 시도한 페이지는 현존하지 않습니다 <br /> 아래 홈버튼을 통해 메인화면으로 돌아가세요</p>
        <v-btn variant="flat" color="primary" class="mt-4" to="/" prepend-icon="mdi-home"> Home</v-btn>
      </div>
    </v-col>
  </v-row>
</template>
<style lang="scss">
.CardMediaWrapper {
  max-width: 720px;
  margin: 0 auto;
  position: relative;
}
.CardMediaBuild {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  animation: 5s bounce ease-in-out infinite;
}
.CardMediaParts {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  animation: 10s blink ease-in-out infinite;
}
</style>
