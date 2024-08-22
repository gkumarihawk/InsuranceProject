$(document).ready(function() {
    // Fetching all the provinces that is available in db
    $.get("http://localhost:9090/insure/provinces", function(provinces) {
        $('#province').empty();
        provinces.forEach(function(province) {
            $('#province').append(`<option value="${province}">${province}</option>`);
        });
    });

    // Fetching the city based on the province.
    $('#province').change(function() {
        var province = $(this).val();
        $.get(`http://localhost:9090/insure/cities?province=${province}`, function(cities) {
            $('#city').empty();
            cities.forEach(function(city) {
                $('#city').append(`<option value="${city.id}">${city.name}</option>`);
            });
        });
    });

    //When the submit button is clicked
    $('#saveButton').click(function() {
		
        var data = {
            name: $('#name').val(),
            age: $('#age').val(),
            salary: $('#salary').val(),
            noOfMembers: $('#noOfMembers').val(),
            city: {
            id: $('#city').val()
        }
        };

        $.ajax({
            url: 'http://localhost:9090/insure/save',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                alert('Insurance policy saved successfully!');
            },
            error: function() {
                alert('Failed to save insurance policy.');
            }
        });
    });
});
