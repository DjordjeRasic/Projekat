$(document).on("submit", "form", function (event) {           
    event.preventDefault();                                   

    var username = $("#username").val();
    var lozinka = $("#password").val();
    var loginInfo = {
        username,
        lozinka,
    }

    $.ajax({
        type: "POST",                                               // HTTP metoda je POST
        url: "http://localhost:8080/api/login",                 // URL na koji se šalju podaci
        dataType: "json",                                           // tip povratne vrednosti
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(loginInfo),                          
        success: function (res) {                                   // ova f-ja se izvršava posle uspešnog zahteva
            console.log(res);
            if(res.uloga === "admin") {
                if(res.retVal === 0) {
                    localStorage.setItem("id", res.id);
                    localStorage.setItem("role", 1);
                    alert(`${res.username} se uspešno prijavio!`);
                    window.location.href = "Admin/fitnessCentar.html";
                } else {
                    console.log(res);
                    alert(res.poruka);
                }
            } else if(res.uloga === "trener") {
                if(res.retVal === 0) {
                    localStorage.setItem("id", res.id);
                    localStorage.setItem("role", 2);
                    alert(`${res.username} se uspešno prijavio!`);
                    window.location.href = "Trener/terminiTrenera.html";
                } else {
                    console.log(res);
                    alert(res.poruka);
                }
            } else if(res.uloga === "clan") {
                if(res.retVal === 0) {
                    localStorage.setItem("id", res.id);
                    localStorage.setItem("role", 3);
                    alert(`${res.username} se uspešno prijavio!`);
                    window.location.href = "Clan/clan.html";
                } else {
                    console.log(res);
                    alert(res.poruka);
                }
            } else {
                alert(res.poruka);
            }
        },
        error: function () {                                        // ova f-ja se izvršava posle neuspešnog zahteva
            alert("Greška!");
        }
    });
    
});

$(document).ready(function () { 
    let role = localStorage.getItem("role");
    if(role == null){
        role == 0;
        localStorage.setItem("role", 0);
    } else if (role == 1){
        alert("Vec ste ulogovani!");
        window.location.href = "Admin/prikazTrenera.html";
    } else if (role == 2){
        alert("Vec ste ulogovani!");
        window.location.href = "Trener/terminiTrenera.html";
    } else if (role == 3){
        alert("Vec ste ulogovani!");
        window.location.href = "Clan/clan.html";
    }
});    