import axios from "axios";
import { showToast,showFailToast } from "vant";
import router from "@/router";

console.log('import.meta.env',import.meta.env);
axios.defaults.baseURL = import.meta.env.MODE == 'development' ? '//localhost:8080/api/' : '//localhost:8080/api/'
axios.defaults.withCredentials = true
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
axios.defaults.headers['Authorization'] = localStorage.getItem('token') || ''
 // post 请求时，发送 json 形式的数据包
 axios.defaults.headers.post['Content-Type'] = 'application/json'

 axios.interceptors.response.use(res => {
    if(typeof res.data !== 'object'){
        showFailToast('服务端异常')
        return Promise.reject(res)
    }
    if(res.data.code != "00000"){
        if(res.data.message) showFailToast(res.data.message)
        if(res.data.code == "A0001" || res.data.code == "A0002"){
            router.push({path : '/login'})
        }
        if(res.data.data && window.location.hash == '#/login'){
            setLocal('Authorization',res.data.data)
            axios.defaults.headers['Authorization'] = res.data.data
        }
        return Promise.reject(res)  
    }

    return res.data
 })

 export default axios