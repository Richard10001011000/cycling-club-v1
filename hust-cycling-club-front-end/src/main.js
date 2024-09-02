import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router  from '@/router'
import 'lib-flexible/flexible'
import 'vant/es/toast/style'
import '@/common/style/theme.css'

import {Button, Form, Field, Toast,Picker,DropdownMenu, DropdownItem,CellGroup,RadioGroup, Radio } from 'vant'

const app = createApp(App)
app.use(router)
app.use(Form).use(Field).use(Toast).use(Button).use(Picker).use(DropdownMenu).use(DropdownItem).use(CellGroup).use(RadioGroup).use(Radio)
app.mount('#app')
