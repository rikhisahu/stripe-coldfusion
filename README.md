# stripe-coldfusion
Coldfusion stripe transaction using stripe-java library

1. Using java code created Runnable jar stripe-git.jar
2. put the same jar inside coldfusion lib folder
3. call chargeStripe method from coldfusion

<cfset stripe = createObject("java","stripe.payment.StripeCharge")>
<cfset txn = stripe.chargeStripe("<stripe_key></cfse>", "4111111111111111", 12, 2024, 333, 21.34, "873487")>


