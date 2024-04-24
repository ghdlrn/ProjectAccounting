<script setup lang="ts">
import { ref } from 'vue';

import masterCard from '@/assets/images/icons/master-card.png';
import paypal from '@/assets/images/icons/paypal.png';

import { CalendarOutlined, DeleteOutlined, PlusOutlined, EyeInvisibleOutlined, EyeOutlined } from '@ant-design/icons-vue';

const tab = ref('11');
function changeTab(e: string) {
  tab.value = e;
}

const show1 = ref(false);
</script>

<template>
  <v-card class="withbg" variant="outlined">
    <v-card-text>
      <h5 class="text-subtitle-1 mb-0">Payment</h5>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-item>
      <div class="d-flex justify-space-between flex-wrap">
        <v-tabs v-model="tab" color="primary">
          <v-tab value="11" variant="outlined" hide-slider density="compact" class="mr-2" rounded="md">
            <img :src="masterCard" class="mr-2" alt="payment icon" /> Card
          </v-tab>
          <v-tab value="22" variant="outlined" hide-slider density="compact" rounded="md">
            <img :src="paypal" class="mr-2" alt="payment icon" /> Paypal
          </v-tab>
        </v-tabs>
        <v-btn color="primary" variant="flat" @click="changeTab('33')">
          <template v-slot:prepend>
            <PlusOutlined />
          </template>
          Add new card
        </v-btn>
      </div>
      <v-window v-model="tab" class="mt-sm-3 mt-6">
        <v-window-item value="11">
          <v-radio-group hide-details>
            <v-row>
              <v-col cols="12" sm="6">
                <div class="py-5 px-4 border payment-method rounded-md">
                  <v-radio value="credit" color="primary" class="label-op-1">
                    <template v-slot:label>
                      <div class="d-flex align-center w-100">
                        <div class="flex-1-1">
                          <div class="d-flex justify-space-between">
                            <div>
                              <p class="text-h6 text-lightText mb-1">Selena Litten</p>
                              <span class="d-block text-h6 text-wrap">**** **** **** 3456</span>
                            </div>
                            <div class="ml-auto">
                              <img src="@/assets/images/icons/master-card.png" width="22" alt="img" />
                              <v-btn icon rounded color="secondary" variant="text" size="small" class="ml-1">
                                <DeleteOutlined />
                              </v-btn>
                            </div>
                          </div>
                        </div>
                      </div>
                    </template>
                  </v-radio>
                </div>
              </v-col>
              <v-col cols="12" sm="6">
                <div class="py-5 px-4 border payment-method rounded-md">
                  <v-radio value="paypal" color="primary" class="label-op-1">
                    <template v-slot:label>
                      <div class="d-flex align-start w-100">
                        <div>
                          <p class="text-h6 text-lightText mb-1">Stebin Ben</p>
                          <span class="d-block text-h6 text-wrap">**** **** **** 7654</span>
                        </div>
                        <div class="ml-auto">
                          <img src="@/assets/images/icons/visa-card.png" width="30" alt="img" />
                          <v-btn icon rounded color="secondary" variant="text" size="small" class="ml-1">
                            <DeleteOutlined />
                          </v-btn>
                        </div>
                      </div>
                    </template>
                  </v-radio>
                </div>
              </v-col>
            </v-row>
          </v-radio-group>
          <div class="text-right mt-6">
            <v-btn variant="outlined" color="secondary">Cancel</v-btn>
            <v-btn variant="flat" color="primary" class="ml-3">Save</v-btn>
          </div>
        </v-window-item>
        <v-window-item value="22">
          <v-label class="mb-2">Email Address</v-label>
          <v-text-field
            single-line
            aria-label="email"
            variant="outlined"
            hide-details
            type="email"
            model-value="stebin.ben@paypal.co"
          ></v-text-field>
          <div class="text-right mt-6">
            <v-btn variant="text" color="error">Cancel</v-btn>
            <v-btn variant="flat" color="primary" class="ml-3">Save</v-btn>
          </div>
        </v-window-item>
        <v-window-item value="33">
          <v-row>
            <v-col cols="12" sm="6">
              <v-label class="mb-2">Name on Card</v-label>
              <v-text-field single-line variant="outlined" color="primary" hide-details placeholder="Name on Card"></v-text-field>
            </v-col>
            <v-col cols="12" sm="6">
              <v-label class="mb-2">Card Number</v-label>
              <v-text-field
                single-line
                variant="outlined"
                type="number"
                color="primary"
                hide-details
                placeholder="Card Number"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="4">
              <v-label class="mb-2">Expiry Date</v-label>
              <v-text-field single-line aria-label="expiry date" variant="outlined" color="primary" hide-details modelValue="11/2023">
                <template v-slot:append-inner>
                  <CalendarOutlined class="text-lightText" />
                </template>
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="4">
              <v-label class="mb-2">CVV Number</v-label>
              <v-text-field single-line variant="outlined" type="number" color="primary" hide-details placeholder="CVV Number">
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="4">
              <v-label class="mb-2">Security Code</v-label>
              <v-text-field
                aria-label="password"
                required
                variant="outlined"
                color="primary"
                hide-details="auto"
                :type="show1 ? 'text' : 'password'"
                class="pwdInput"
              >
                <template v-slot:append-inner>
                  <v-btn color="secondary" icon rounded variant="text">
                    <EyeInvisibleOutlined
                      :style="{ color: 'rgb(var(--v-theme-secondary))' }"
                      v-if="show1 == false"
                      @click="show1 = !show1"
                    />
                    <EyeOutlined :style="{ color: 'rgb(var(--v-theme-secondary))' }" v-if="show1 == true" @click="show1 = !show1" />
                  </v-btn>
                </template>
              </v-text-field>
            </v-col>
            <v-col cols="12" class="text-right">
              <v-btn variant="outlined" color="secondary">Cancel</v-btn>
              <v-btn variant="flat" color="primary" class="ml-3">Save</v-btn>
            </v-col>
          </v-row>
        </v-window-item>
      </v-window>
    </v-card-item>
  </v-card>
</template>

<style lang="scss">
.payment-method {
  .v-label {
    width: 100%;
  }
}
</style>
