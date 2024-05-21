<!-- pages/index.vue -->

<template>
  <div>
    <h1>Companies</h1>
    <div v-if="authStore.member">
      <p>Welcome, {{ authStore.member.username }}</p>
      <button @click="authStore.logout">Logout</button>
      <div>
        <h2>Your Companies</h2>
        <ul>
          <li v-for="company in companyStore.companies" :key="company.id">
            {{ company.name }}
          </li>
        </ul>
      </div>
      <div v-if="companyStore.selectedCompany">
        <h2>Selected Company: {{ companyStore.selectedCompany.name }}</h2>
      </div>
    </div>
    <div v-else>
      <p>Please log in.</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useAuthStore } from '~/stores/auth/auth';
import { useCompanyStore } from "~/stores/accounting/company";
const authStore = useAuthStore();
const companyStore = useCompanyStore();
</script>
