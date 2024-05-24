import {
  CircleIcon, BuildingIcon, ClipboardDataIcon, EditIcon, NotebookIcon
} from 'vue-tabler-icons';

export interface menu {
  header?: string;
  title?: string;
  icon?: object;
  to?: string;
  divider?: boolean;
  chip?: string;
  chipColor?: string;
  chipVariant?: string;
  chipIcon?: string;
  children?: menu[];
  disabled?: boolean;
  type?: string;
  subCaption?: string;
}

const sidebarItem: menu[] = [
  { header: '등록' },
  {
    title: '회사 등록',
    icon: BuildingIcon,
    to: '/register/company',
  },
  {
    title: '기초 정보 등록',
    icon: EditIcon,
    to: '/',
    children: [
      { title: '거래처 등록', icon: CircleIcon, to: '/register/customer' },
      { title: '계좌 등록', icon: CircleIcon, to: '/register/finance' },
      { title: '카드 등록', icon: CircleIcon, to: '/register/card' },
      { title: '계정과목 및 적요 등록', icon: CircleIcon, to: '/register/account-title' },
    ]
  },
  {
    title: '전표 등록',
    icon: NotebookIcon,
    to: '/',
    children: [
      { title: '일반 전표', icon: CircleIcon, to: '/document/normal' },
      { title: '매입 매출 전표', icon: CircleIcon, to: '/' },
    ]
  },
  { divider: true },
  { header: '장부' },
  {
    title: '장부',
    icon: ClipboardDataIcon,
    to: '/',
    children: [
      { title: '분개장', icon: CircleIcon, to: '/' },
      { title: '일/월계표', icon: CircleIcon, to: '/' },
      { title: '재무상태표', icon: CircleIcon, to: '/' },
      { title: '손익계산서', icon: CircleIcon, to: '/' },
      { title: '합계잔액시산표', icon: CircleIcon, to: '/' },
      { title: '현금출납장', icon: CircleIcon, to: '/' },
      { title: '매입매출장', icon: CircleIcon, to: '/' },
    ]
  },
];

export default sidebarItem;
