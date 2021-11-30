import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login.vue'
import Signup from '@/components/Signup.vue'
import CreateItem from '@/components/CreateItem.vue'
import ViewItems from '@/components/ViewItems.vue'
import Profile from '@/components/Profile.vue'
import LibrarianManagement from '@/components/LibrarianManagement.vue'
import ScheduleAssignment from '@/components/ScheduleAssignment.vue'
import CreateReservation from '@/components/CreateReservation.vue'
import CustomerHomePage from '@/components/CustomerHomePage.vue'
import SignupCustomerLibrarian from '@/components/SignupCustomerLibrarian.vue'
import Holiday from '@/components/Holiday.vue'
import OpeningsHours from '@/components/OpeningsHours.vue'
import UpdateHeadLibrarian from '@/components/UpdateHeadLibrarian.vue'
import LibraryBooking from '@/components/LibraryBooking.vue'
import LibrarianPage from '@/components/LibrarianPage.vue'

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
      path: '/Profile/:nameP/:familynameP/:idP/:addressP/:emailP/:passwordP/:accountTypeP',
      name: 'Profile',
      component: Profile,
      props: true
    }
    ,
    {
      path: '/LibrarianManagement',
      name: 'LibrarianManagement',
      component: LibrarianManagement
    }
    ,
    {
      path: '/ScheduleAssignment/:id',
      name: 'ScheduleAssignment',
      component: ScheduleAssignment,
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
      path: '/CreateReservation/:customerIdP',
      name: 'CreateReservation',
      component: CreateReservation,
      props: true
    }
    ,
    {
      path: '/CustomerHomePage/:firstNameP/:lastNameP/:idP/:addressP/:emailP/:passwordP/:accountTypeP',
      name: 'CustomerHomePage',
      component: CustomerHomePage,
      props: true
    },
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
    ,
    {
      path: '/LibraryBooking/:customerIdP',
      name: 'LibraryBooking',
      component: LibraryBooking,
      props: true
    },
    {
      path: '/LibrarianPage/:librarianIdP',
      name: 'LibrarianPage',
      component: LibrarianPage,
      props: true
    },
  ]
})