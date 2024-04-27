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
    to: '/register/company-info',
  },
  {
    title: '기초 정보 등록',
    icon: EditIcon,
    to: '/asdas',
    children: [
      { title: '거래처 등록', icon: CircleIcon, to: '/asd' },
      { title: '계좌 등록', icon: CircleIcon, to: '/sad' },
      { title: '카드 등록', icon: CircleIcon, to: '/asd' },
      { title: '계정과목 및 적요 등록', icon: CircleIcon, to: '/asdsa' },
      { title: '고정자산 등록', icon: CircleIcon, to: '/dsadsa' },
      { title: '업무용 승용차 등록', icon: CircleIcon, to: '/dsadsa' },
    ]
  },
  {
    title: '전표 등록',
    icon: NotebookIcon,
    to: '/asd',
    children: [
      { title: '일반 전표', icon: CircleIcon, to: '/sad' },
      { title: '매입 매출 전표', icon: CircleIcon, to: '/asd' },
      { title: '현금 거래', icon: CircleIcon, to: '/dasd' },
      { title: '카드 거래', icon: CircleIcon, to: '/dsa' },
      { title: '어음 거래', icon: CircleIcon, to: '/asd' },
      { title: '차량 비용', icon: CircleIcon, to: '/asd' },
    ]
  },
  { divider: true },
  { header: '장부' },
  {
    title: '장부',
    icon: ClipboardDataIcon,
    to: '/adsa',
    children: [
      { title: '분개장', icon: CircleIcon, to: '/adsa' },
      { title: '일/월계표', icon: CircleIcon, to: '/dssa' },
      { title: '합계잔액시산표', icon: CircleIcon, to: '/asd' },
      { title: '계정별원장', icon: CircleIcon, to: '/asd' },
      { title: '거래처별원장', icon: CircleIcon, to: '/sad' },
      { title: '현금출납장', icon: CircleIcon, to: '/dasd' },
      { title: '매입매출장', icon: CircleIcon, to: '/asd' },
      { title: '차량비용현황', icon: CircleIcon, to: '/asd' },
      { title: '고정비용현황', icon: CircleIcon, to: '/dsa' },
    ]
  },
];

export default sidebarItem;
