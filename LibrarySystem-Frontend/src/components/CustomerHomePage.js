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

};