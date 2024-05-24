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
        title: '유동자산',
        icon: BuildingIcon,
        children: [
            { title: '당좌자산(1010)', icon: CircleIcon },
            { title: '재고자산(1460)', icon: CircleIcon },
        ]
    },
    {
        title: '비유동자산',
        icon: EditIcon,
        to: '/',
        children: [
            { title: '투자자산(1760)', icon: CircleIcon },
            { title: '유형자산(2010)', icon: CircleIcon },
            { title: '무형자산(2310)', icon: CircleIcon },
            { title: '기타비유동자산(9610)', icon: CircleIcon },
        ]
    },
    { divider: true },
    { header: '부채' },
    {
        title: '유동부채(2510)',
        icon: ClipboardDataIcon,
    },
    {
        title: '비유동부채(2910)',
        icon: ClipboardDataIcon,
    },
    { divider: true },
    { header: '자본' },
    {
        title: '자본금(3310)',
        icon: ClipboardDataIcon,
    },
    {
        title: '자본잉여금(3410)',
        icon: ClipboardDataIcon,
    },
    {
        title: '이익잉여금(3510)',
        icon: ClipboardDataIcon,
    },
    {
        title: '자본조정(3810)',
        icon: ClipboardDataIcon,
    },
    {
        title: '기타포괄손익(9810)',
        icon: ClipboardDataIcon,
    },
    { divider: true },
    { header: '매출' },
    {
        title: '매출(4010)',
        icon: ClipboardDataIcon,
    },
    {
        title: '매출원가(4510)',
        icon: ClipboardDataIcon,
    },
    { divider: true },
    { header: '제조원가(5010)' },
    { divider: true },
    { header: '도급원가(6010)' },
    { divider: true },
    { header: '분양원가(7010)' },
    { divider: true },
    { header: '판관비(8010)' },
    { divider: true },
    { header: '기타' },
    {
        title: '영업외수익(9010)',
        icon: ClipboardDataIcon,
    },
    {
        title: '영업외비용(9310)',
        icon: ClipboardDataIcon,
    },
    {
        title: '중단사업손익(9910)',
        icon: ClipboardDataIcon,
    },
    {
        title: '법인(개인)(9980)',
        icon: ClipboardDataIcon,
    },
];

export default sidebarItem;
