<script setup lang="ts">
// icons
import { PaperClipOutlined, BlockOutlined } from '@ant-design/icons-vue';
</script>

<template>
  <v-card variant="outlined" class="withbg pa-5" elevation="0">
    <v-textarea
      variant="outlined"
      single-line
      color="primary"
      density="comfortable"
      name="input-7-4"
      placeholder="Whats on your mind, Larry ?"
      rows="4"
    ></v-textarea>
    <div class="d-flex">
      <v-btn color="secondary" variant="text">
        <template v-slot:prepend>
          <PaperClipOutlined />
        </template>
        Gallery
      </v-btn>
      <v-btn color="secondary" variant="flat" class="ml-auto">
        <template v-slot:prepend>
          <BlockOutlined :style="{ fontSize: '18px' }" />
        </template>
        Post
      </v-btn>
    </div>
  </v-card>
</template>
