<script setup lang="ts">
import { useCustomizerStore } from '@/stores/customizer';
const customizer = useCustomizerStore();

// themes font Family option
</script>

<!------------------------------------->
<!-- Customizer -->
<!------------------------------------->
<template>
  <v-navigation-drawer app temporary elevation="10" location="right" v-model="customizer.Customizer_drawer" width="350">
    <perfect-scrollbar style="height: 100%">

    </perfect-scrollbar>
  </v-navigation-drawer>
</template>
<style lang="scss">
.custom-radio {
  .v-selection-control-group {
    flex-direction: row;
    .v-selection-control {
      width: 48px;
      height: 48px;
      align-items: center;
      justify-content: center;
      flex: unset;
      border: 2px solid rgba(var(--v-theme-borderLight), 0.36);
      border-radius: 4px;
      &.v-selection-control--dirty {
        border: 2px solid rgba(var(--v-theme-primary), 1);
      }
      .v-selection-control__wrapper {
        .v-selection-control__input {
          opacity: 0;
        }
        img {
          position: absolute;
        }
      }
      .v-label {
        width: unset;
        height: unset;
      }
    }
  }
}
.input-bg {
  background-color: rgb(var(--v-theme-gray100)) !important;
}
.input-radio {
  .v-selection-control-group {
    .v-selection-control {
      height: 30px;
    }
  }
}
.custom-font {
  .v-selection-control-group {
    .v-selection-control__wrapper {
      display: none;
    }
    .v-selection-control {
      border: 2px solid rgba(var(--v-theme-borderLight), 0.36);
      outline: 6px solid rgba(var(--v-theme-borderLight), 0.1);
      border-radius: 4px;
      margin: 6px;
      padding: 12px 16px;
      &.v-selection-control--dirty {
        border: 1px solid rgba(var(--v-theme-primary), 1);
        outline: 6px solid rgba(var(--v-theme-primary), 0.1);
      }
    }
  }
}
</style>
