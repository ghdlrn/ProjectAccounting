<script setup lang="ts">
import { ref } from 'vue';
import { useAuthStore } from '~/stores/auth/auth';
import Google from '/images/social-google.svg';

const router = useRouter();
const authStore = useAuthStore();

const checkbox = ref(false);
const show1 = ref(false);
const email = ref('');
const password = ref('');
import { emailRules, passwordRules } from "../../utils/form";

const login = async (event: any) => {
  event.preventDefault();  // Prevent default form submission
  try {
    await authStore.login(email.value, password.value);
    await router.push('/');
  } catch (error) {
    console.error('Login failed:', error);
    alert('로그인 실패: ' + error);
  }
}
</script>

<template>
  <v-btn block color="primary" variant="outlined" class="text-lightText googleBtn">
    <img :src="Google" alt="google" />
    <span class="ml-2">구글 계정으로 로그인</span>
  </v-btn>
  <v-row>
    <v-col class="d-flex align-center">
      <v-divider class="custom-devider" />
      <v-btn variant="outlined" class="orbtn" rounded="md" size="small">OR</v-btn>
      <v-divider class="custom-devider" />
    </v-col>
  </v-row>
  <h5 class="text-h5 text-center my-4 mb-8">이메일로 로그인</h5>


  <v-form @submit.prevent="login" ref="Reform" class="mt-7 loginForm">
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
        hint="8이상 16이하, 숫자 및 특수문자 하나이상 포함"
        persistent-hint
        label="비밀번호"
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


    <div class="d-sm-flex align-center mt-2 mb-7 mb-sm-0">
      <v-checkbox
          v-model="checkbox"
          :rules="[(v: any) => !!v || 'You must agree to continue!']"
          label="입력 양식을 기억할까요?"
          required
          color="primary"
          class="ms-n2"
          hide-details
      ></v-checkbox>
      <div class="ml-auto">
        <a href="javascript:void(0)" class="text-primary text-decoration-none">비밀번호 찾기</a>
      </div>
    </div>
    <v-btn block class="mt-2 bg-blue-darken-2" variant="flat" size="large" type="submit" append-icon="mdi-login">로그인</v-btn>
  </v-form>

  <div class="mt-5 text-right">
    <v-divider />
    <br/>
    <v-row>
      <v-col cols="12" sm="6">
        <v-btn to="/auth/signup" class="mt-2 bg-deep-purple-accent-4" append-icon="mdi-account-plus" variant="flat" size="large" block>회원가입</v-btn>
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
.loginForm {
  .v-text-field .v-field--active input {
    font-weight: 500;
  }
}
</style>