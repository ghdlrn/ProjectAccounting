<script setup>
import { ref } from 'vue';
import axios from "axios";
const router = useRouter();

const checkbox = ref(false);
const show1 = ref(false);
const username = ref('');
const password = ref('');
const email = ref('');
import { emailRules, passwordRules, nameRules } from "~/utils/form";

const SignUp = async () => {
  if (!checkbox.value) {
    alert('이용약관에 동의하셔야 합니다.');
    return;
  }
  try {
    await axios.post('/auth/signup', { username: username.value, email: email.value, password: password.value });
    alert('Sign up successful');
    await router.push('/auth/login');
  } catch (error) {
    console.error('SignUp failed:', error);
    alert('회원가입 실패');
  }
};
</script>

<template>
  <br />
  <v-divider class="custom-devider" />
  <h5 class="text-h5 text-center my-4 mb-8">이메일 주소로 회원가입</h5>
  <v-form ref="Reform" class="mt-7 loginForm">

    <v-text-field
          v-model="username"
          :rules="nameRules"
          density="comfortable"
          hide-details="auto"
          variant="outlined"
          color="primary"
          label="이름"
     ></v-text-field>
    <v-text-field
        v-model="email"
        :rules="emailRules"
        label="이메일"
        class="mt-4 mb-4"
        required
        density="comfortable"
        hide-details="auto"
        variant="outlined"
        color="primary"
    ></v-text-field>
    <v-text-field
        v-model="password"
        :rules="passwordRules"
        label="비밀번호"
        hint="8이상 16이하, 숫자 및 특수문자 하나이상 포함"
        persistent-hint
        required
        density="comfortable"
        variant="outlined"
        color="primary"
        hide-details="auto"
        :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
        :type="show1 ? 'text' : 'password'"
        @click:append="show1 = !show1"
        class="pwdInput"
    ></v-text-field>

    <div class="d-sm-inline-flex align-center mt-2 mb-7 mb-sm-0 font-weight-bold">
      <v-checkbox
          v-model="checkbox"
          :rules="[(v: any) => !!v || 'You must agree to continue!']"
          label="이용약관에 동의하십니까?"
          required
          color="primary"
          class="ms-n2"
          hide-details
      ></v-checkbox>
      <a href="#" class="ml-1 text-lightText">이용 약관</a>
    </div>
    <v-btn append-icon="mdi-account-circle" block class="mt-2 bg-teal-accent-4" variant="flat" size="large" @click="SignUp()">회원가입</v-btn>
  </v-form>
  <div class="mt-5 text-right">
    <v-divider />
    <br/>
    <v-row>
      <v-col cols="12" sm="6">
        <v-btn to="/auth/login" class="mt-2 bg-blue-darken-2" append-icon="mdi-login" variant="flat" size="large"  block>로그인</v-btn>
      </v-col>
      <v-col cols="12" sm="6">
        <v-btn to="/" class="mt-2 bg-green-lighten-1" append-icon="mdi-home" variant="flat" size="large" block>홈</v-btn>
      </v-col>
    </v-row>
  </div>
</template>
<style lang="scss">
.custom-devider {
  border-color: rgba(0, 0, 0, 0.08) !important;
}
.googleBtn {
  border-color: rgba(0, 0, 0, 0.08);
  margin: 30px 0 20px 0;
}
.outlinedInput .v-field {
  border: 1px solid rgba(0, 0, 0, 0.08);
  box-shadow: none;
}
.orbtn {
  padding: 2px 40px;
  border-color: rgba(0, 0, 0, 0.08);
  margin: 20px 15px;
}
.pwdInput {
  position: relative;
  .v-input__append {
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
  }
}
</style>
