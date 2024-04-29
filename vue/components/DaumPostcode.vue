<template>
  <v-row>
    <v-col cols="12" sm="5">
      <v-row>
        <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
          <v-label class="mt-2">우편번호</v-label>
        </v-col>
        <v-col cols="12" lg="5" md="9">
          <v-text-field
              v-model="postcode"
              readonly
              hint="오른쪽 버튼으로 조회가능합니다"
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
  </v-row>

  <v-row>
    <v-col cols="12" sm="7">
      <v-row>
        <v-col cols="12" lg="2" md="3" class="pb-md-3 pb-0">
          <v-label class="mt-2">도로명 주소</v-label>
        </v-col>
        <v-col cols="12" lg="9" md="9" class="ml-1">
          <v-text-field
              v-model="roadAddress"
              readonly
              hint="도로명 주소"
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
              v-model="jibunAddress"
              readonly
              hint="지번 주소"
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
    <v-col cols="12" sm="8">
      <v-row>
        <v-col cols="12" lg="1" md="3" class="pb-md-3 pb-0">
          <v-label class="mt-2">상세 주소</v-label>
        </v-col>
        <v-col cols="12" lg="9" md="9" class="ml-11">
          <v-text-field
              v-model="extraAddress"
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
    <v-col cols="12" sm="4">
      <v-row>
        <v-col cols="12" lg="12" md="9">
          <v-text-field
              v-model="guideText"
              hint="참고 항목"
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

<script setup>
import {ref, onMounted} from 'vue';

const postcode = ref('');
const roadAddress = ref('');
const jibunAddress = ref('');
const extraAddress = ref('');
const guideText = ref('');

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
  new daum.Postcode({
    oncomplete: function(data) {
      const extraRoadAddr = getExtraAddress(data);
      postcode.value = data.zonecode;
      roadAddress.value = data.roadAddress;
      jibunAddress.value = data.jibunAddress;
      extraAddress.value = data.roadAddress ? extraRoadAddr : '';
      guideText.value = getGuideText(data, extraRoadAddr);
    }
  }).open();
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
    const expRoadAddr = data.autoRoadAddress + extraAddr;
    return `(예상 도로명 주소 : ${expRoadAddr})`;
  } else if (data.autoJibunAddress) {
    const expJibunAddr = data.autoJibunAddress;
    return `(예상 지번 주소 : ${expJibunAddr})`;
  }
  return '';
};
</script>

<style scoped lang="scss">
.address{
  font-size: small;
}
</style>