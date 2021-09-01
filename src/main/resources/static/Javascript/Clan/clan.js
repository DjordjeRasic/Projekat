function cutString(input, begin, end) {
    return input.substring(begin, end);
}

$(document).ready(function () {   
    let role = localStorage.getItem("role");
    if(role == null || role == 0){
        role == 0;
        localStorage.setItem("role", 0);
        window.location.href = "../../index.html";
    } else if (role == 2){
        window.location.href = "../Trener/terminiTrenera.html";
    } else if (role == 1){
        window.location.href = "../Admin/prikazTrenera.html";
    } 
    var zastita = localStorage.getItem("role");
    var id = localStorage.getItem("id");
    var provera = {
        zastita,
        id
    }
    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/clan/profil",                 
        dataType: "json",  
        contentType: "application/json",                            
        data: JSON.stringify(provera),                                              
        success: function (res) { 
            datum = cutString(res.datumRodjenja, 0, 10) + ' ' + cutString(res.datumRodjenja, 11, 16);
            let c1 = "<td>" + res.id + "</td>";       
            let c2 = "<td>" + res.korisnickoIme + "</td>";        
            let c3 = "<td>" + res.ime + "</td>";   
            let c4 = "<td>" + res.prezime + "</td>";    
            let c6 = "<td>" + res.email + "</td>";   
            let c7 = "<td>" + datum + "</td>";       
            let c8 = "<td>" + res.telefon + "</td>";        
            let c9 = "<td>" + res.ocene + "</td>";   
            let c10 = "<td>" + res.odradjeniTermini + "</td>";       
            let c11 = "<td>" + res.prijavljeniTermini + "</td>";  
            $('#id').append(c1);      
            $('#user').append(c2);  
            $('#ime').append(c3);  
            $('#prezime').append(c4);  
            $('#email').append(c6); 
            $('#datumRodjenja').append(c7);      
            $('#telefon').append(c8);  
            $('#ocene').append(c9);  
            $('#odradjeni').append(c10);  
            $('#prijavljeni').append(c11);   
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});

function odjaviSe() {
    window.location.href = "../../index.html";
    window.localStorage.setItem("role", 0); 
    window.localStorage.setItem("id", 0); 
}