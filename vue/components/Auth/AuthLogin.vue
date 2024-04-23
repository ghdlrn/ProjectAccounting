<script setup lang="ts">
import { ref } from 'vue';
import Google from '/images/social-google.svg';
import { useAuthStore } from '~/stores/auth';
import { Form } from 'vee-validate'
import { useRouter } from 'vue-router';

const router = useRouter();
const authStore = useAuthStore();
const valid = ref(false);
const checkbox = ref(false);
const show1 = ref(false);
const email = ref('');
const password = ref('');
const dialog = ref(false); // 로그인성공메시지 상태

const passwordRules = ref([
  (v: string) => !!v || '비밀번호 입력은 필수입니다',
  (v: string) => (v && v.length <= 16) || '패스워드는 16자리 수를 넘을 수 없습니다'
]);
const emailRules = ref([(v: string) => !!v || '이메일 입력은 필수입니다', (v: string) => /.+@.+\..+/.test(v) || '이메일 양식이 아닙니다']);

const handleLogin = async () => {
  if (valid.value) {  // Assuming 'valid' is a computed ref based on form validation
    try {
      const success = await authStore.login(email.value, password.value);
      if (success) {
        router.push(authStore.returnUrl || '/');
      } else {
        console.error('Login failed: Invalid credentials');
        // Handle showing an error message
      }
    } catch (error) {
      console.error('Login failed:', error);
      // Handle showing an error message
    }
  }
}
</script>

<template>
  <v-btn block color="primary" variant="outlined" class="text-lightText googleBtn">
    <img :src="Google" alt="google" />
    <span class="ml-2">구글 계정으로 로그인</span></v-btn
  >
  <v-row>
    <v-col class="d-flex align-center">
      <v-divider class="custom-devider" />
      <v-btn variant="outlined" class="orbtn" rounded="md" size="small">OR</v-btn>
      <v-divider class="custom-devider" />
    </v-col>
  </v-row>
  <h5 class="text-h5 text-center my-4 mb-8">이메일로 로그인</h5>

  <Form @submit.prevent="handleLogin" class="mt-7 loginForm" v-slot="{ errors, isSubmitting }">
    <v-text-field
        v-model="email"
        :rules="emailRules"
        label="이메일"
        class="mt-4 mb-8"
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
    <v-btn :loading="isSubmitting" block class="mt-2 bg-blue-darken-2" variant="flat" size="large" type="submit" append-icon="mdi-login">로그인</v-btn>
    <div v-if="errors.apiError" class="mt-2">
      <v-alert color="error">{{ errors.apiError }}</v-alert>
    </div>
  </Form>

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
  <v-dialog v-model="dialog" max-width="500">
    <v-card>
      <v-card-title class="headline">로그인 성공</v-card-title>
      <v-card-text>로그인에 성공했습니다!</v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="primary" text @click="dialog = false">확인</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
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
