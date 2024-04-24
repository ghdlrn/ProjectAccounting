<script setup lang="ts">
import { ref, onMounted, computed, shallowRef } from 'vue';
import Banner from '../../../../components/apps/socialprofile/BannerSection.vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import { useFollowersStore } from '@/stores/apps/followers';
import UiParentCard from '@/components/shared/UiParentCard.vue';

// icons
import {
  SearchOutlined,
  EnvironmentOutlined,
  EllipsisOutlined,
  HeartOutlined,
  TeamOutlined,
  DeleteOutlined,
  UserAddOutlined
} from '@ant-design/icons-vue';

const store = useFollowersStore();

onMounted(() => {
  store.fetchFollowers();
});

type FriendItem = {
  avatar: string;
  name: string;
  location: string;
  follow: number;
  // Add other properties as needed
};
const getfollowers = computed<FriendItem[]>(() => {
  return store.followers;
});
const searchValue = ref('');
// dropdown data
const actionDD = shallowRef([
  { title: 'Favorite', icon: HeartOutlined },
  { title: 'Edit Friend List', icon: TeamOutlined },
  { title: 'Remove', icon: DeleteOutlined }
]);
const page = ref({ title: 'Social Profile' });

const filteredCards = computed<FriendItem[]>(() => {
  return getfollowers.value.filter((card) => {
    return card.name.toLowerCase().includes(searchValue.value.toLowerCase());
  });
});

const breadcrumbs = ref([
  {
    title: 'Users',
    disabled: false,
    href: '/'
  },
  {
    title: 'Social Profile',
    disabled: true,
    href: '#'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <Banner />
    </v-col>
  </v-row>
  <v-row class="justify-content-end mt-5">
    <v-col cols="12">
      <UiParentCard title="Followers">
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
          <v-col cols="12" lg="3" md="4" sm="6" v-for="(card, i) in filteredCards" :key="i">
            <v-card variant="outlined" class="card-hover-border bg-containerBg">
              <v-card-text>
                <div class="d-flex align-center gap-3">
                  <img :src="card.avatar" :alt="card.avatar" class="rounded-md" width="40" />
                  <div class="w-50">
                    <h4 class="text-h5 mb-0">{{ card.name }}</h4>
                    <small class="opacity-50 text-truncate d-flex align-center gap-2">
                      <EnvironmentOutlined :style="{ fontSize: '12px' }" />
                      <span class="text-truncate w-50">{{ card.location }}</span>
                    </small>
                  </div>
                  <div class="ml-auto">
                    <v-menu>
                      <template v-slot:activator="{ props }">
                        <v-btn size="x-small" v-bind="props" variant="text">
                          <EllipsisOutlined :style="{ fontSize: '12px' }" />
                        </v-btn>
                      </template>
                      <v-list elevation="24" density="compact" class="py-0">
                        <v-list-item v-for="(item, index) in actionDD" :key="index" :value="index" color="secondary">
                          <template v-slot:prepend>
                            <component :is="item.icon" class="v-icon--start opacity-50" />
                          </template>
                          <v-list-item-title class="text-h6">{{ item.title }}</v-list-item-title>
                        </v-list-item>
                      </v-list>
                    </v-menu>
                  </div>
                </div>
                <div class="mt-5">
                  <v-btn block v-if="card.follow == 1" variant="outlined" color="secondary">
                    <template v-slot:prepend>
                      <TeamOutlined />
                    </template>
                    Followed
                  </v-btn>
                  <v-btn block v-else variant="flat" color="primary">
                    <template v-slot:prepend>
                      <UserAddOutlined />
                    </template>
                    Follow Back
                  </v-btn>
                </div>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
