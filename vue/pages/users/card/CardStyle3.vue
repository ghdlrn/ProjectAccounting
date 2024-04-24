<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { useUserCardStore } from '@/stores/apps/UserCard';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';

// icons
import { SearchOutlined, FacebookFilled, TwitterCircleFilled, LinkedinFilled, MessageFilled, DownOutlined } from '@ant-design/icons-vue';

const store = useUserCardStore();

onMounted(() => {
  store.fetchCards();
});

// theme breadcrumb
const page = ref({ title: 'Style 03' });
const breadcrumbs = ref([
  {
    title: 'Users',
    disabled: false,
    href: '#'
  },
  {
    title: 'Cards',
    disabled: false,
    href: '#'
  },
  {
    title: 'Style 03',
    disabled: true,
    href: '#'
  }
]);

// cards data

const page1 = ref(1);
const rows = ref([{ title: '10 Rows' }, { title: '20 Rows' }, { title: '30 Rows' }]);
const searchValue = ref('');

type Card = {
  name: string;
  value: number;
  profile: string;
  avatar: string;
  post: string;
  status: string;
  // Add other properties as needed
};
const cards = computed<Card[]>(() => {
  return store.cards;
});

const filteredCards = computed<Card[]>(() => {
  return cards.value.filter((card) => {
    return card.name.toLowerCase().includes(searchValue.value.toLowerCase());
  });
});
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row class="justify-content-end">
    <v-col cols="12">
      <UiParentCard title="Cards">
        <template v-slot:action>
          <v-row class="justify-end">
            <v-col cols="12" lg="3" md="4" sm="5">
              <v-text-field
                color="primary"
                hide-details
                width="200"
                variant="outlined"
                persistent-placeholder
                placeholder="Search"
                v-model="searchValue"
                class="mt-sm-0 mt-2"
              >
                <template v-slot:prepend-inner>
                  <SearchOutlined class="text-lightText" />
                </template>
              </v-text-field>
            </v-col>
          </v-row>
        </template>

        <v-row>
          <v-col cols="12" md="4" xl="3" sm="6" v-for="(card, i) in filteredCards" :key="i">
            <v-card variant="outlined" class="overflow-hidden">
              <v-img :src="card.profile" :alt="card.profile" height="125px" cover> </v-img>

              <div class="text-center mt-n8 position-relative">
                <div class="d-flex justify-center">
                  <img :src="card.avatar" :alt="card.avatar" width="72" class="rounded-circle" />
                </div>
                <h4 class="text-h4 mt-4 mb-3">{{ card.name }}</h4>
                <span class="text-subtitle-1 text-medium-emphasis font-weight-medium d-block mb-2">{{ card.post }}</span>
                <v-chip color="success" size="small" label v-if="card.status">Active</v-chip>
                <v-chip color="error" size="small" label v-else>Rejected</v-chip>
              </div>
              <v-card-text>
                <div class="d-flex gap-3 mt-4 justify-center">
                  <v-btn variant="tonal" icon color="facebook">
                    <FacebookFilled :style="{ fontSize: '18px' }" />
                  </v-btn>
                  <v-btn variant="tonal" icon color="twitter">
                    <TwitterCircleFilled :style="{ fontSize: '18px' }" />
                  </v-btn>
                  <v-btn variant="tonal" icon color="linkedin">
                    <LinkedinFilled :style="{ fontSize: '18px' }" />
                  </v-btn>
                </div>
                <v-btn variant="outlined" color="primary" class="flex-fill w-100 mt-4">
                  <template v-slot:prepend>
                    <MessageFilled />
                  </template>
                  Message
                </v-btn>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>

        <v-row class="justify-space-between mt-5">
          <v-col cols="12" lg="5" class="text-left">
            <v-pagination class="left-pagination" v-model="page1" density="compact" active-color="primary" :length="6"> </v-pagination>
          </v-col>
          <v-col cols="12" lg="5" class="text-right">
            <v-menu transition="scale-transition">
              <template v-slot:activator="{ props }">
                <v-btn color="inherit" variant="text" v-bind="props">
                  10 Rows
                  <template v-slot:append>
                    <DownOutlined :style="{ fontSize: '10px' }" />
                  </template>
                </v-btn>
              </template>

              <v-list>
                <v-list-item v-for="(row, i) in rows" :key="i" :value="i">
                  <v-list-item-title>{{ row.title }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
<style>
.left-pagination .v-pagination__list {
  justify-content: start;
}
</style>
