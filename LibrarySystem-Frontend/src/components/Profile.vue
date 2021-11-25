<template>
    <div id="profile">
        <customer-profile username='habibi' password='lol123' id="12345" address="lol Street" email=habibi@lol.com  accountType="habibi type" />
    </div>
</template>
<script>
import CustomerProfile from "@/components/CustomerProfile.vue"
export default{
    components : {
        CustomerProfile
    }
}
</script>
<style>
    
</style>