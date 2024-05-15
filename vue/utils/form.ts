export const passwordRules = ref([
    (v: string) => !!v || '비밀번호 입력은 필수입니다',
    (v: string) => v.length >= 8 || '비밀번호는 최소 8자리 이상이어야 합니다',
    (v: string) => v.length <= 16 || '비밀번호는 최대 16자리를 넘을 수 없습니다',
    (v: string) => /\d/.test(v) || '비밀번호에는 최소 하나의 숫자가 포함되어야 합니다',
    (v: string) => /[!@#$%^&*(),.?":{}|<>]/.test(v) || '비밀번호에는 최소 하나의 특수 문자가 포함되어야 합니다'
]);

export const emailRules = ref([
    (v: string) => !!v || '이메일 입력은 필수입니다',
    (v: string) => /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(v) || '이메일 양식이 아닙니다'
    // @포함, 도메인 이름이 최소 두글자 알파벳으로 끝남
]);

export const nameRules = ref([
    (v: string) => !!v || '이름 입력은 필수입니다',
    (v: string) => v.length >= 2 || '이름은 최소 2자 이상이어야 합니다',
]);

export const businessRegistrationNumberRules = ref([
    (v: string) => !!v || '비즈니스 등록 번호 입력은 필수입니다',
    (v: string) => /^\d{3}-\d{2}-\d{5}$/.test(v) || '비즈니스 등록 번호는 xxx-xx-xxxxx 형식이어야 합니다'
]);

export const registrationNumberRules = ref([
    (v: string) => !!v || '등록 번호 입력은 필수입니다',
    (v: string) => {
        const pattern1 = /^\d{3}-\d{2}-\d{5}$/;  // xxx-xx-xxxxx 형식
        const pattern2 = /^\d{6}-\d{7}$/;        // xxxxxx-xxxxxxx 형식
        return pattern1.test(v) || pattern2.test(v) || '등록 번호는 xxx-xx-xxxxx 또는 xxxxxx-xxxxxxx 형식이어야 합니다';
    }
]);

export const nullableRules = ref([
    (v: string) => !!v || '필수 입력 칸 입니다',
])