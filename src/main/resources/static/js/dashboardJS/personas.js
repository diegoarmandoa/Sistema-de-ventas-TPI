function personaEstado(number,estado){

    let url = "/dashboard/usuarios/estado";

    const formData = new FormData();

    formData.append("id", number);
    formData.append("estado", estado);

    console.log(formData);

    sendData(url, formData).then((response) => response.text())
        .then(text => {
                alert("enviado");
        })
        .catch(function (err) {
            console.log(err);
            alert("A ocurrido un error"); // si ocurrio un error
        });

}