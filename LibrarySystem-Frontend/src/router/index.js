import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login.vue'
import Signup from '@/components/Signup.vue'
import CreateItem from '@/components/CreateItem.vue'
import ViewItems from '@/components/ViewItems.vue'
import Profile from '@/components/Profile'
import LibrarianManagement from '@/components/LibrarianManagement'
import ScheduleAssignment from '@/components/ScheduleAssignment'
import SignupCustomerLibrarian from '@/components/SignupCustomerLibrarian.vue'
import Test from '@/components/Test'
import Holiday from '@/components/Holiday.vue'
import OpeningsHours from '@/components/OpeningsHours.vue'
import UpdateHeadLibrarian from '@/components/UpdateHeadLibrarian.vue'

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
      path: '/LibrarianManagement',
      name: 'LibrarianManagement',
      component: LibrarianManagement
    },
    {
      path: '/ScheduleAssignment/:id',
      name: 'ScheduleAssignment',
      component: ScheduleAssignment,
      props: true
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
      path: '/Holiday',
      name: 'Holiday',
      component: Holiday
    }
    ,
    {
      path: '/OpeningHours',
      name: 'OpeningsHours',
      component: OpeningsHours
    }
    ,
    {
      path: '/SignupCustomerLibrarian',
      name: 'SignupCustomerLibrarian',
      component: SignupCustomerLibrarian
    }
    ,
    {
      path: '/UpdateHeadLibrarian',
      name: 'UpdateHeadLibrarian',
      component: UpdateHeadLibrarian
    }
  ]
})
