<template>
  <div id="CreateItem">
    <table>
      <h2>Create Item</h2>
      <tr>
        <td>
          <input type="text" placeholder="Title" />
        </td>
        <td>
          ​<select>
            ​<option disabled selected value="">Select Type</option>
            <option >Book</option>
            <option >Movie</option>
            <option >Album</option>
            <option >Newspapers</option>
            <option >Archive</option>
          </select>
        </td>
      </tr>
      <tr>
          <button>Create Item</button>
      </tr>
    </table>
    <p>
        <span style="color:red">Error: Message text comes here</span>
    </p>
  </div>
</template>

<script>
</script>

<style>
</style>