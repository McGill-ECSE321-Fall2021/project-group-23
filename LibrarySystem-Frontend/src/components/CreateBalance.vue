<template>
  <body>
    <div class="balance">
      <h1>Balance: {{balance}}$</h1>
    </div>
    <div class="container">
      <div class="payment">
        <section class="row options first">
          <div class="item">
            <label class="label">
              <input class="radio" type="radio" name="channel" />
              <span class="radioInput"></span><span>PayPal</span>
            </label>
          </div>
        </section>
        <section class="row options no-border-bottom">
          <div class="item">
            <label class="label">
              <input class="radio" type="radio" name="channel" checked />
              <span class="radioInput"></span><span>Debit/Credit card</span>
            </label>
          </div>
        </section>
        <section class="row blanks">
          <div class="col">
            <div class="item">
              <span class="title">Card number</span>
              <input
                class="blank card-number"
                placeholder="XXXX XXXX XXXX XXXX"
                onFocus="this.value = ''"
                onkeyup="if (this.value.length === 4 || this.value.length === 4 * 2 + 1 || this.value.length === 4 * 3 + 2) { this.value += ' ' }"
                maxlength="19"
                required
              />
            </div>
          </div>
        </section>
        <section class="row blanks">
          <div class="col">
            <div class="item">
              <span class="title">Cardholder Name</span>
              <input
                class="blank cardholder-name"
                placeholder="John Doe"
                required
              />
            </div>
          </div>
        </section>
        <section class="row blanks last">
          <div class="col">
            <div class="item">
              <span class="title">Expiry</span>
              <input
                class="blank"
                placeholder="MM/YY"
                onkeyup="if (this.value.length === 2) { this.value += '/' }"
                maxlength="5"
                required
              />
            </div>
          </div>
          <div class="col">
            <div class="item">
              <span class="title">CVC</span>
              <input
                class="blank"
                placeholder="123"
                maxlength="3"
                required
              />
            </div>
          </div>
        </section>
      </div>
      <button class="btn btn-light" style="margin-top: 10px" type="button" onclick="location.href='https://www.youtube.com/watch?v=X_8Nh5XfRw0';">
        Pay Balance Amount Due
      </button>
    </div>
  </body>
</template>
<script src="./CreateBalance.js">
</script>
<style scoped>
  h1 {text-align: center;}
</style>