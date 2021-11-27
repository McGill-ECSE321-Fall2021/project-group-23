import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login'
import Signup from '@/components/Signup'
import CreateItem from '@/components/CreateItem.vue'
import ViewItems from '@/components/ViewItems.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    }
    ,
    {
      path: '/Login',
      name: 'Login',
      component: Login
    }
    ,
    {
      path: '/Signup',
      name: 'Signup',
      component: Signup
    }
    ,
    {
      path: '/CreateItem',
      name: 'CreateItem',
      component: CreateItem
    }
    ,
    {
      path: '/ViewItems',
      name: 'ViewItems',
      component: ViewItems
    }
    ,
  ]
})
