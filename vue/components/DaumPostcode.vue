<template>
  <v-row>
    <v-col cols="12" sm="5">
      <v-row>
        <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
          <v-label class="mt-2">우편번호</v-label>
        </v-col>
        <v-col cols="12" lg="5" md="9">
          <v-text-field
              v-model="localPostcode"
              @change="emitUpdate"
              readonly
              hint="오른쪽 버튼으로 조회"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="ex) 16677"
              color="primary">
          </v-text-field>
        </v-col>
        <v-col cols="12" lg="4" md="9">
          <v-btn size="large" flat color="primary mt-1" class="address" @click="openPostcodePopup">우편번호 조회</v-btn>
        </v-col>
      </v-row>
    </v-col>
    <v-col cols="12" sm="7">
    </v-col>
  </v-row>

  <v-row>
    <v-col cols="12" sm="7">
      <v-row>
        <v-col cols="12" lg="2" md="3" class="pb-md-3 pb-0">
          <v-label class="mt-2">도로명 주소</v-label>
        </v-col>
        <v-col cols="12" lg="9" md="9" class="ml-1">
          <v-text-field
              v-model="localRoadAddress"
              @change="emitUpdate"
              readonly
              hint="도로명 주소(읽기 전용입니다)"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="ex) 경기도 수원시 영통구 삼성로 129 (매탄동)"
              color="primary"
          ></v-text-field>
        </v-col>
      </v-row>
    </v-col>
    <v-col cols="12" sm="5">
      <v-row>
        <v-col cols="12" lg="12" md="9">
          <v-text-field
              v-model="localJibunAddress"
              @change="emitUpdate"
              readonly
              hint="지번 주소(읽기 전용입니다)"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="ex) 경기 수원시 영통구 매탄동 416"
              color="primary"
          ></v-text-field>
        </v-col>
      </v-row>
    </v-col>
  </v-row>
  <!---------------------------------------------------------->
  <v-row>
    <v-col cols="12" sm="7">
      <v-row>
        <v-col cols="12" lg="2" md="3" class="pb-md-3 pb-0">
          <v-label class="mt-2">상세 주소</v-label>
        </v-col>
        <v-col cols="12" lg="9" md="9">
          <v-text-field
              v-model="localExtraAddress"
              @change="emitUpdate"
              hint="상세 주소를 추가해 주세요"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="ex) OO동 OO호"
              color="primary"
          ></v-text-field>
        </v-col>
      </v-row>
    </v-col>
    <v-col cols="12" sm="5">
      <v-row>
        <v-col cols="12" lg="12" md="9">
          <v-text-field
              v-model="localGuideText"
              @change="emitUpdate"
              hint="참고 항목(읽기 전용입니다)"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              readonly
              placeholder="ex) (메탄동)"
              color="primary"
          ></v-text-field>
        </v-col>
      </v-row>
    </v-col>
  </v-row>
</template>

<script setup lang="ts">
import { useAddressStore } from "~/stores/address";
const store = useAddressStore();

import { onMounted, ref, watch } from 'vue';
import type { Address, AddressData } from '~/types/accounting/basicdata/address.ts';

const loadScript = () => {
  return new Promise((resolve, reject) => {
    const script = document.createElement('script');
    script.src = "//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js";
    script.onload = () => resolve();
    script.onerror = (e) => reject(e);
    document.head.appendChild(script);
  });
};

onMounted(async () => {
  await loadScript();
});

const openPostcodePopup = () => {
  var width = 500; //팝업의 너비
  var height = 600; //팝업의 높이
  new daum.Postcode({
    oncomplete: function(data: AddressData) {
      const newAddress: Address = {
        postcode: data.zonecode,
        roadAddress: data.roadAddress,
        jibunAddress: data.jibunAddress,
        extraAddress: store.getExtraAddress(data),
        guideText: store.getGuideText(data, store.getExtraAddress(data))
      };
      store.setAddress(newAddress);
      localPostcode.value = newAddress.postcode;
      localRoadAddress.value = newAddress.roadAddress;
      localJibunAddress.value = newAddress.jibunAddress;
      localExtraAddress.value = newAddress.extraAddress;
      localGuideText.value = newAddress.guideText;
      emitUpdate();
    },
    theme: {
      searchBgColor: "#0091EA", //검색창 배경색
      queryTextColor: "#FFFFFF" //검색창 글자색
    },
    width: width, //생성자에 크기 값을 명시적으로 지정해야 합니다.
    height: height,
  }).open({
    left: (window.screen.width / 2) - (width / 2),
    top: (window.screen.height / 2) - (height / 2)
  });
};

const props = defineProps({
  initialPostcode: String,
  initialRoadAddress: String,
  initialJibunAddress: String,
  initialExtraAddress: String,
  initialGuideText: String
});

const emit = defineEmits(['updateAddress']);

const localPostcode = ref(props.initialPostcode);
const localRoadAddress = ref(props.initialRoadAddress);
const localJibunAddress = ref(props.initialJibunAddress);
const localExtraAddress = ref(props.initialExtraAddress);
const localGuideText = ref(props.initialGuideText);

function emitUpdate() {
  emit('updateAddress', {
    postcode: localPostcode.value,
    roadAddress: localRoadAddress.value,
    jibunAddress: localJibunAddress.value,
    extraAddress: localExtraAddress.value,
    guideText: localGuideText.value,
  });
}

watch(props, (newProps) => {
  localPostcode.value = newProps.initialPostcode;
  localRoadAddress.value = newProps.initialRoadAddress;
  localJibunAddress.value = newProps.initialJibunAddress;
  localExtraAddress.value = newProps.initialExtraAddress;
  localGuideText.value = newProps.initialGuideText;
}, { deep: true });


</script>

<style scoped lang="scss">
.address{
  font-size: small;
}
</style>