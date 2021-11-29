export default {
  props: ["firstNameP", "lastNameP", "idP", "addressP", "emailP", "passwordP", "accountTypeP"],
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
    goToProfile: function() {
      this.$router.push({ path: `/Profile/` + this.firstName + '/' + this.lastName + '/' + this.id + '/' + this.address + '/' + this.email + '/' + this.password + '/' + 'Customer'})
    },
    goToReservations: function() {
      this.$router.push({ path: `/CreateReservation/` + this.id })
    }
  }

};