import ViewItems from './ViewItems.vue'

export default {
  props: ["firstNameP", "lastNameP", "idP", "addressP", "emailP", "passwordP", "accountTypeP"],
  
  components: {
    ViewItems
  },


  data() {
    return {
      firstName: this.firstNameP,
      lastName: this.lastNameP,
      address: this.addressP,
      email: this.emailP,
      password: this.passwordP,
      accountType: this.accountTypeP,
      id: this.idP,
      isShow: false,
      isError: false,
    };
  },
  methods: {
    goToProfile: function () {
      this.$router.push({ path: `/Profile/` + this.firstName + '/' + this.lastName + '/' + this.id + '/' + this.address + '/' + this.email + '/' + this.password + '/' + 'Customer' })
    },
    goToReservations: function () {
      this.$router.push({ path: `/CreateReservation/` + this.id })
    },
    goToBookings: function () {
      this.$router.push({ path: `/LibraryBooking/` + this.id })
    },
    goToBalance: function () {
      this.$router.push({ path: `/CreateBalance/` + this.id })
    }
  }

};