main = {
    showSwal: function(type) {
        if (type == 'warning-message-and-confirmation') {
            swal({
                title: 'Are you accept?',
                text: "City Data App will use your IP to get your coordinates",
                type: 'warning',
                showCancelButton: true,
                confirmButtonClass: 'btn btn-success',
                cancelButtonClass: 'btn btn-danger',
                confirmButtonText: 'Accept',
                buttonsStyling: false
            }).then(function () {
                swal({
                    title: 'Done!',
                    text: 'We got your coordinates.',
                    type: 'success',
                    confirmButtonClass: "btn btn-success",
                    buttonsStyling: false
                })
            }).catch(swal.noop)
        } else {
        }
    }
}
