export default defineAppConfig({
    title: 'Hello Nuxt',
    theme: {
        dark: true,
        colors: {
            primary: '#ff0000'
        }
    },
})
export type ConfigProps = {
    Sidebar_drawer: boolean;
    Customizer_drawer: boolean;
    mini_sidebar: boolean;
    fontTheme: string;
    inputBg: boolean;
};

const config: ConfigProps = {
    Sidebar_drawer: true,
    Customizer_drawer: false,
    mini_sidebar: false,
    fontTheme: 'Roboto',
    inputBg: false
};
