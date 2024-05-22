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
    { header: '자산' },
    {
        title: '회사 등록',
        icon: BuildingIcon,
        to: '/register/company',
    },
    {
        title: '유동자산',
        icon: EditIcon,
        to: '/',
        children: [
            { title: '당좌자산', icon: CircleIcon, to: '/register/customer' },
            { title: '재고자산', icon: CircleIcon, to: '/register/finance' },
        ]
    },
    {
        title: '비유동자산',
        icon: NotebookIcon,
        to: '/',
        children: [
            { title: '투자자산', icon: CircleIcon, to: '/' },
            { title: '유형자산', icon: CircleIcon, to: '/' },
            { title: '무형자산', icon: CircleIcon, to: '/' },
            { title: '기타비유동자산', icon: CircleIcon, to: '/' },
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
            { title: '합계잔액시산표', icon: CircleIcon, to: '/' },
            { title: '계정별원장', icon: CircleIcon, to: '/' },
            { title: '거래처별원장', icon: CircleIcon, to: '/' },
            { title: '현금출납장', icon: CircleIcon, to: '/' },
            { title: '매입매출장', icon: CircleIcon, to: '/' },
        ]
    },
];

export default sidebarItem;
