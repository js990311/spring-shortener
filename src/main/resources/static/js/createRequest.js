$(document).ready(()=>{
    $('#form').submit((event)=>{
        event.preventDefault();

        let formData = {
            urls: [$('input[name="url"]').val()]
        };

        console.log(
            JSON.stringify(formData)
        );

        $.ajax({
            type: 'POST',
            url: '/create',
            contentType : 'application/json',
            dataType: 'json',
            data: JSON.stringify(formData),
            success: (resp)=>{
                resp.data.forEach((urlResponse)=>{
                    let result = $('<a>').attr('href', 'localhost:8080/' + urlResponse.shortUrl)
                        .text(`${urlResponse.originalUrl}, (localhost:8080/${urlResponse.shortUrl})`);
                    $("#result").append(result);
                });
            },
            error: (xhr, httpStatus, error) => {
                let result = $('<div>').text("Fail to create short url");
                $("#result").append(result);
            }
        });
    });
})
