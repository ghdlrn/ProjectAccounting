<template>
  <v-form @submit.prevent="openPostcodePopup">
  <v-row>
    <v-col cols="12" sm="5">
      <v-row>
        <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
          <v-label class="mt-2">우편번호</v-label>
        </v-col>
        <v-col cols="12" lg="5" md="9">
          <v-text-field
              v-model="address.postcode"
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
          <v-btn size="large" flat color="primary mt-1" class="address" type="submit">우편번호 조회</v-btn>
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
              v-model="address.roadAddress"
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
              v-model="address.jibunAddress"
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
              v-model="address.extraAddress"
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
              v-model="address.guideText"
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
  </v-form>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';

const props = defineProps({
  modelValue: {
    type: Object,
    required: true,
    default: () => ({
      postcode: '',
      roadAddress: '',
      jibunAddress: '',
      extraAddress: '',
      guideText: ''
    })
  }
});

const emit = defineEmits(['update:modelValue']);

const address = ref({ ...props.modelValue });

watch(() => props.modelValue, (newValue) => {
  address.value = { ...newValue };
}, { immediate: true });

watch(address, (newAddress, oldAddress) => {
  if (JSON.stringify(newAddress) !== JSON.stringify(oldAddress)) {
    emit('update:modelValue', newAddress);
  }
}, { deep: true });

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
    oncomplete: function(data) {
      const newAddress = {
        postcode: data.zonecode,
        roadAddress: data.roadAddress,
        jibunAddress: data.jibunAddress,
        extraAddress: getExtraAddress(data),
        guideText: getGuideText(data, getExtraAddress(data))
      };
      address.value = newAddress;
      emit('update:modelValue', newAddress);
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

const getExtraAddress = (data) => {
  let extraAddr = '';
  if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
    extraAddr += data.bname;
  }
  if (data.buildingName !== '' && data.apartment === 'Y') {
    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
  }
  return extraAddr !== '' ? `(${extraAddr})` : '';
};

const getGuideText = (data, extraAddr) => {
  if (data.autoRoadAddress) {
    return `(예상 도로명 주소 : ${data.autoRoadAddress + extraAddr})`;
  } else if (data.autoJibunAddress) {
    return `(예상 지번 주소 : ${data.autoJibunAddress})`;
  }
  return '';
};
</script>

<style scoped lang="scss">
.address{
  font-size: small;
}
</style>