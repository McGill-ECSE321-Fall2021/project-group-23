import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login'
import Signup from '@/components/Signup'
import CreateItem from '@/components/CreateItem.vue'
import ViewItems from '@/components/ViewItems.vue'
import Profile from '@/components/Profile'
import CustomerHomePage from '@/components/CustomerHomePage'
import LibrarianManagement from '@/components/LibrarianManagement'
import ScheduleAssignment from '@/components/ScheduleAssignment'
import CreateReservation from '@/components/CreateReservation'
import Test from '@/components/Test'

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
    },
    {
      path: '/Profile',
      name: 'Profile',
      component: Profile
    },
    {
      path: '/CustomerHomePage',
      name: 'CustomerHomePage',
      component: CustomerHomePage
    },
    {
      path: '/LibrarianManagement',
      name: 'LibrarianManagement',
      component: LibrarianManagement
    },
    {
      path: '/ScheduleAssignment',
      name: 'ScheduleAssignment',
      component: ScheduleAssignment
    },
    {
      path: '/Test/:name',
      name: 'Test',
      component: Test,
      props: true
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
    {
      path: '/CreateReservation',
      name: 'CreateReservation',
      component: CreateReservation
    }
  ]
})
