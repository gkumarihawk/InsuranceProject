<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="./js/insurance.js"></script>
</head>
<body>
    <form action='/charge' method='POST' id='checkout-form'>
        <input type='hidden' name='amount' id='amount' value='<%= request.getAttribute("amount") %>' />
        <label>Price: $<span id='price'><%= (int)request.getAttribute("amount")/100 %></span></label>
        <!-- NOTE: data-key/data-amount/data-currency will be rendered by Thymeleaf -->
        <script
            src='https://checkout.stripe.com/checkout.js' 
            class='stripe-button'
            data-key='<%= request.getAttribute("stripePublicKey") %>'
            data-amount='<%= request.getAttribute("amount") %>'
            data-currency='<%= request.getAttribute("currency") %>'
            data-name='Baeldung'
            data-description='Spring course checkout'
            data-image='https://www.baeldung.com/wp-content/themes/baeldung/favicon/android-chrome-192x192.png'
            data-locale='auto'
            data-zip-code='false'>
        </script>
    </form>

    <script>
        // Function to get URL parameter by name
        function getUrlParameter(name) {
            name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
            var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
            var results = regex.exec(location.search);
            return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
        }

        $(document).ready(function() {
            // Extract the monthly payment from the URL
            var monthlyPayment = getUrlParameter('monthlyPayment');
            if (monthlyPayment) {
                // Convert monthlyPayment to cents for Stripe
                var amountInCents = parseFloat(monthlyPayment) * 100;

                // Update the form's hidden input and label
                $('#amount').val(amountInCents);
                $('#price').text(monthlyPayment);

                // Update Stripe button's data-amount attribute
                $('.stripe-button').attr('data-amount', amountInCents);
            } else {
                alert('Monthly payment amount is missing.');
            }
        });
    </script>
</body>
</html>
